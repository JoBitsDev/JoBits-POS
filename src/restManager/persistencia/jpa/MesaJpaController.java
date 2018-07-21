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
import restManager.persistencia.Orden;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import restManager.persistencia.Mesa;
import restManager.persistencia.jpa.exceptions.NonexistentEntityException;
import restManager.persistencia.jpa.exceptions.PreexistingEntityException;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class MesaJpaController implements Serializable {

    public MesaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mesa mesa) throws PreexistingEntityException, Exception {
        if (mesa.getOrdenList() == null) {
            mesa.setOrdenList(new ArrayList<Orden>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Area areacodArea = mesa.getAreacodArea();
            if (areacodArea != null) {
                areacodArea = em.getReference(areacodArea.getClass(), areacodArea.getCodArea());
                mesa.setAreacodArea(areacodArea);
            }
            List<Orden> attachedOrdenList = new ArrayList<Orden>();
            for (Orden ordenListOrdenToAttach : mesa.getOrdenList()) {
                ordenListOrdenToAttach = em.getReference(ordenListOrdenToAttach.getClass(), ordenListOrdenToAttach.getCodOrden());
                attachedOrdenList.add(ordenListOrdenToAttach);
            }
            mesa.setOrdenList(attachedOrdenList);
            em.persist(mesa);
            if (areacodArea != null) {
                areacodArea.getMesaList().add(mesa);
                areacodArea = em.merge(areacodArea);
            }
            for (Orden ordenListOrden : mesa.getOrdenList()) {
                Mesa oldMesacodMesaOfOrdenListOrden = ordenListOrden.getMesacodMesa();
                ordenListOrden.setMesacodMesa(mesa);
                ordenListOrden = em.merge(ordenListOrden);
                if (oldMesacodMesaOfOrdenListOrden != null) {
                    oldMesacodMesaOfOrdenListOrden.getOrdenList().remove(ordenListOrden);
                    oldMesacodMesaOfOrdenListOrden = em.merge(oldMesacodMesaOfOrdenListOrden);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMesa(mesa.getCodMesa()) != null) {
                throw new PreexistingEntityException("Mesa " + mesa + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mesa mesa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mesa persistentMesa = em.find(Mesa.class, mesa.getCodMesa());
            Area areacodAreaOld = persistentMesa.getAreacodArea();
            Area areacodAreaNew = mesa.getAreacodArea();
            List<Orden> ordenListOld = persistentMesa.getOrdenList();
            List<Orden> ordenListNew = mesa.getOrdenList();
            if (areacodAreaNew != null) {
                areacodAreaNew = em.getReference(areacodAreaNew.getClass(), areacodAreaNew.getCodArea());
                mesa.setAreacodArea(areacodAreaNew);
            }
            List<Orden> attachedOrdenListNew = new ArrayList<Orden>();
            for (Orden ordenListNewOrdenToAttach : ordenListNew) {
                ordenListNewOrdenToAttach = em.getReference(ordenListNewOrdenToAttach.getClass(), ordenListNewOrdenToAttach.getCodOrden());
                attachedOrdenListNew.add(ordenListNewOrdenToAttach);
            }
            ordenListNew = attachedOrdenListNew;
            mesa.setOrdenList(ordenListNew);
            mesa = em.merge(mesa);
            if (areacodAreaOld != null && !areacodAreaOld.equals(areacodAreaNew)) {
                areacodAreaOld.getMesaList().remove(mesa);
                areacodAreaOld = em.merge(areacodAreaOld);
            }
            if (areacodAreaNew != null && !areacodAreaNew.equals(areacodAreaOld)) {
                areacodAreaNew.getMesaList().add(mesa);
                areacodAreaNew = em.merge(areacodAreaNew);
            }
            for (Orden ordenListOldOrden : ordenListOld) {
                if (!ordenListNew.contains(ordenListOldOrden)) {
                    ordenListOldOrden.setMesacodMesa(null);
                    ordenListOldOrden = em.merge(ordenListOldOrden);
                }
            }
            for (Orden ordenListNewOrden : ordenListNew) {
                if (!ordenListOld.contains(ordenListNewOrden)) {
                    Mesa oldMesacodMesaOfOrdenListNewOrden = ordenListNewOrden.getMesacodMesa();
                    ordenListNewOrden.setMesacodMesa(mesa);
                    ordenListNewOrden = em.merge(ordenListNewOrden);
                    if (oldMesacodMesaOfOrdenListNewOrden != null && !oldMesacodMesaOfOrdenListNewOrden.equals(mesa)) {
                        oldMesacodMesaOfOrdenListNewOrden.getOrdenList().remove(ordenListNewOrden);
                        oldMesacodMesaOfOrdenListNewOrden = em.merge(oldMesacodMesaOfOrdenListNewOrden);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = mesa.getCodMesa();
                if (findMesa(id) == null) {
                    throw new NonexistentEntityException("The mesa with id " + id + " no longer exists.");
                }
            }
            throw ex;
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mesa mesa;
            try {
                mesa = em.getReference(Mesa.class, id);
                mesa.getCodMesa();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mesa with id " + id + " no longer exists.", enfe);
            }
            Area areacodArea = mesa.getAreacodArea();
            if (areacodArea != null) {
                areacodArea.getMesaList().remove(mesa);
                areacodArea = em.merge(areacodArea);
            }
            List<Orden> ordenList = mesa.getOrdenList();
            for (Orden ordenListOrden : ordenList) {
                ordenListOrden.setMesacodMesa(null);
                ordenListOrden = em.merge(ordenListOrden);
            }
            em.remove(mesa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mesa> findMesaEntities() {
        return findMesaEntities(true, -1, -1);
    }

    public List<Mesa> findMesaEntities(int maxResults, int firstResult) {
        return findMesaEntities(false, maxResults, firstResult);
    }

    private List<Mesa> findMesaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mesa.class));
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

    public Mesa findMesa(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mesa.class, id);
        } finally {
            em.close();
        }
    }

    public int getMesaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mesa> rt = cq.from(Mesa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
