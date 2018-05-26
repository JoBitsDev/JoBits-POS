/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restManager.persistencia.jpa;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import restManager.persistencia.Area;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import restManager.persistencia.Carta;
import restManager.persistencia.jpa.exceptions.NonexistentEntityException;
import restManager.persistencia.jpa.exceptions.PreexistingEntityException;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class CartaJpaController implements Serializable {

    public CartaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Carta carta) throws PreexistingEntityException, Exception {
        if (carta.getAreaList() == null) {
            carta.setAreaList(new ArrayList<Area>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Area> attachedAreaList = new ArrayList<Area>();
            for (Area areaListAreaToAttach : carta.getAreaList()) {
                areaListAreaToAttach = em.getReference(areaListAreaToAttach.getClass(), areaListAreaToAttach.getCodArea());
                attachedAreaList.add(areaListAreaToAttach);
            }
            carta.setAreaList(attachedAreaList);
            em.persist(carta);
            for (Area areaListArea : carta.getAreaList()) {
                areaListArea.getCartaList().add(carta);
                areaListArea = em.merge(areaListArea);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCarta(carta.getCodCarta()) != null) {
                throw new PreexistingEntityException("Carta " + carta + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Carta carta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Carta persistentCarta = em.find(Carta.class, carta.getCodCarta());
            List<Area> areaListOld = persistentCarta.getAreaList();
            List<Area> areaListNew = carta.getAreaList();
            List<Area> attachedAreaListNew = new ArrayList<Area>();
            for (Area areaListNewAreaToAttach : areaListNew) {
                areaListNewAreaToAttach = em.getReference(areaListNewAreaToAttach.getClass(), areaListNewAreaToAttach.getCodArea());
                attachedAreaListNew.add(areaListNewAreaToAttach);
            }
            areaListNew = attachedAreaListNew;
            carta.setAreaList(areaListNew);
            carta = em.merge(carta);
            for (Area areaListOldArea : areaListOld) {
                if (!areaListNew.contains(areaListOldArea)) {
                    areaListOldArea.getCartaList().remove(carta);
                    areaListOldArea = em.merge(areaListOldArea);
                }
            }
            for (Area areaListNewArea : areaListNew) {
                if (!areaListOld.contains(areaListNewArea)) {
                    areaListNewArea.getCartaList().add(carta);
                    areaListNewArea = em.merge(areaListNewArea);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = carta.getCodCarta();
                if (findCarta(id) == null) {
                    throw new NonexistentEntityException("The carta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Carta carta;
            try {
                carta = em.getReference(Carta.class, id);
                carta.getCodCarta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The carta with id " + id + " no longer exists.", enfe);
            }
            List<Area> areaList = carta.getAreaList();
            for (Area areaListArea : areaList) {
                areaListArea.getCartaList().remove(carta);
                areaListArea = em.merge(areaListArea);
            }
            em.remove(carta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Carta> findCartaEntities() {
        return findCartaEntities(true, -1, -1);
    }

    public List<Carta> findCartaEntities(int maxResults, int firstResult) {
        return findCartaEntities(false, maxResults, firstResult);
    }

    private List<Carta> findCartaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Carta.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Carta findCarta(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Carta.class, id);
        } finally {
            em.close();
        }
    }

    public int getCartaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Carta> rt = cq.from(Carta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
