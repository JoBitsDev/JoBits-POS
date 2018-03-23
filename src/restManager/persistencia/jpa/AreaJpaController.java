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
import restManager.persistencia.Carta;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import restManager.persistencia.Area;
import restManager.persistencia.Mesa;
import restManager.persistencia.jpa.exceptions.NonexistentEntityException;
import restManager.persistencia.jpa.exceptions.PreexistingEntityException;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class AreaJpaController implements Serializable {

    public AreaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Area area) throws PreexistingEntityException, Exception {
        if (area.getCartaList() == null) {
            area.setCartaList(new ArrayList<Carta>());
        }
        if (area.getMesaList() == null) {
            area.setMesaList(new ArrayList<Mesa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Carta> attachedCartaList = new ArrayList<Carta>();
            for (Carta cartaListCartaToAttach : area.getCartaList()) {
                cartaListCartaToAttach = em.getReference(cartaListCartaToAttach.getClass(), cartaListCartaToAttach.getCodCarta());
                attachedCartaList.add(cartaListCartaToAttach);
            }
            area.setCartaList(attachedCartaList);
            List<Mesa> attachedMesaList = new ArrayList<Mesa>();
            for (Mesa mesaListMesaToAttach : area.getMesaList()) {
                mesaListMesaToAttach = em.getReference(mesaListMesaToAttach.getClass(), mesaListMesaToAttach.getCodMesa());
                attachedMesaList.add(mesaListMesaToAttach);
            }
            area.setMesaList(attachedMesaList);
            em.persist(area);
            for (Carta cartaListCarta : area.getCartaList()) {
                cartaListCarta.getAreaList().add(area);
                cartaListCarta = em.merge(cartaListCarta);
            }
            for (Mesa mesaListMesa : area.getMesaList()) {
                Area oldAreacodAreaOfMesaListMesa = mesaListMesa.getAreacodArea();
                mesaListMesa.setAreacodArea(area);
                mesaListMesa = em.merge(mesaListMesa);
                if (oldAreacodAreaOfMesaListMesa != null) {
                    oldAreacodAreaOfMesaListMesa.getMesaList().remove(mesaListMesa);
                    oldAreacodAreaOfMesaListMesa = em.merge(oldAreacodAreaOfMesaListMesa);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findArea(area.getCodArea()) != null) {
                throw new PreexistingEntityException("Area " + area + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Area area) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Area persistentArea = em.find(Area.class, area.getCodArea());
            List<Carta> cartaListOld = persistentArea.getCartaList();
            List<Carta> cartaListNew = area.getCartaList();
            List<Mesa> mesaListOld = persistentArea.getMesaList();
            List<Mesa> mesaListNew = area.getMesaList();
            List<Carta> attachedCartaListNew = new ArrayList<Carta>();
            for (Carta cartaListNewCartaToAttach : cartaListNew) {
                cartaListNewCartaToAttach = em.getReference(cartaListNewCartaToAttach.getClass(), cartaListNewCartaToAttach.getCodCarta());
                attachedCartaListNew.add(cartaListNewCartaToAttach);
            }
            cartaListNew = attachedCartaListNew;
            area.setCartaList(cartaListNew);
            List<Mesa> attachedMesaListNew = new ArrayList<Mesa>();
            for (Mesa mesaListNewMesaToAttach : mesaListNew) {
                mesaListNewMesaToAttach = em.getReference(mesaListNewMesaToAttach.getClass(), mesaListNewMesaToAttach.getCodMesa());
                attachedMesaListNew.add(mesaListNewMesaToAttach);
            }
            mesaListNew = attachedMesaListNew;
            area.setMesaList(mesaListNew);
            area = em.merge(area);
            for (Carta cartaListOldCarta : cartaListOld) {
                if (!cartaListNew.contains(cartaListOldCarta)) {
                    cartaListOldCarta.getAreaList().remove(area);
                    cartaListOldCarta = em.merge(cartaListOldCarta);
                }
            }
            for (Carta cartaListNewCarta : cartaListNew) {
                if (!cartaListOld.contains(cartaListNewCarta)) {
                    cartaListNewCarta.getAreaList().add(area);
                    cartaListNewCarta = em.merge(cartaListNewCarta);
                }
            }
            for (Mesa mesaListOldMesa : mesaListOld) {
                if (!mesaListNew.contains(mesaListOldMesa)) {
                    mesaListOldMesa.setAreacodArea(null);
                    mesaListOldMesa = em.merge(mesaListOldMesa);
                }
            }
            for (Mesa mesaListNewMesa : mesaListNew) {
                if (!mesaListOld.contains(mesaListNewMesa)) {
                    Area oldAreacodAreaOfMesaListNewMesa = mesaListNewMesa.getAreacodArea();
                    mesaListNewMesa.setAreacodArea(area);
                    mesaListNewMesa = em.merge(mesaListNewMesa);
                    if (oldAreacodAreaOfMesaListNewMesa != null && !oldAreacodAreaOfMesaListNewMesa.equals(area)) {
                        oldAreacodAreaOfMesaListNewMesa.getMesaList().remove(mesaListNewMesa);
                        oldAreacodAreaOfMesaListNewMesa = em.merge(oldAreacodAreaOfMesaListNewMesa);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = area.getCodArea();
                if (findArea(id) == null) {
                    throw new NonexistentEntityException("The area with id " + id + " no longer exists.");
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
            Area area;
            try {
                area = em.getReference(Area.class, id);
                area.getCodArea();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The area with id " + id + " no longer exists.", enfe);
            }
            List<Carta> cartaList = area.getCartaList();
            for (Carta cartaListCarta : cartaList) {
                cartaListCarta.getAreaList().remove(area);
                cartaListCarta = em.merge(cartaListCarta);
            }
            List<Mesa> mesaList = area.getMesaList();
            for (Mesa mesaListMesa : mesaList) {
                mesaListMesa.setAreacodArea(null);
                mesaListMesa = em.merge(mesaListMesa);
            }
            em.remove(area);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Area> findAreaEntities() {
        return findAreaEntities(true, -1, -1);
    }

    public List<Area> findAreaEntities(int maxResults, int firstResult) {
        return findAreaEntities(false, maxResults, firstResult);
    }

    private List<Area> findAreaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Area.class));
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

    public Area findArea(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Area.class, id);
        } finally {
            em.close();
        }
    }

    public int getAreaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Area> rt = cq.from(Area.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
