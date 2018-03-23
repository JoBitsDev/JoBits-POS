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
import restManager.persistencia.Orden;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import restManager.persistencia.Venta;
import restManager.persistencia.jpa.exceptions.IllegalOrphanException;
import restManager.persistencia.jpa.exceptions.NonexistentEntityException;
import restManager.persistencia.jpa.exceptions.PreexistingEntityException;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class VentaJpaController implements Serializable {

    public VentaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Venta venta) throws PreexistingEntityException, Exception {
        if (venta.getOrdenList() == null) {
            venta.setOrdenList(new ArrayList<Orden>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Orden> attachedOrdenList = new ArrayList<Orden>();
            for (Orden ordenListOrdenToAttach : venta.getOrdenList()) {
                ordenListOrdenToAttach = em.getReference(ordenListOrdenToAttach.getClass(), ordenListOrdenToAttach.getCodOrden());
                attachedOrdenList.add(ordenListOrdenToAttach);
            }
            venta.setOrdenList(attachedOrdenList);
            em.persist(venta);
            for (Orden ordenListOrden : venta.getOrdenList()) {
                Venta oldVentafechaOfOrdenListOrden = ordenListOrden.getVentafecha();
                ordenListOrden.setVentafecha(venta);
                ordenListOrden = em.merge(ordenListOrden);
                if (oldVentafechaOfOrdenListOrden != null) {
                    oldVentafechaOfOrdenListOrden.getOrdenList().remove(ordenListOrden);
                    oldVentafechaOfOrdenListOrden = em.merge(oldVentafechaOfOrdenListOrden);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVenta(venta.getFecha()) != null) {
                throw new PreexistingEntityException("Venta " + venta + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Venta venta) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venta persistentVenta = em.find(Venta.class, venta.getFecha());
            List<Orden> ordenListOld = persistentVenta.getOrdenList();
            List<Orden> ordenListNew = venta.getOrdenList();
            List<String> illegalOrphanMessages = null;
            for (Orden ordenListOldOrden : ordenListOld) {
                if (!ordenListNew.contains(ordenListOldOrden)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Orden " + ordenListOldOrden + " since its ventafecha field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Orden> attachedOrdenListNew = new ArrayList<Orden>();
            for (Orden ordenListNewOrdenToAttach : ordenListNew) {
                ordenListNewOrdenToAttach = em.getReference(ordenListNewOrdenToAttach.getClass(), ordenListNewOrdenToAttach.getCodOrden());
                attachedOrdenListNew.add(ordenListNewOrdenToAttach);
            }
            ordenListNew = attachedOrdenListNew;
            venta.setOrdenList(ordenListNew);
            venta = em.merge(venta);
            for (Orden ordenListNewOrden : ordenListNew) {
                if (!ordenListOld.contains(ordenListNewOrden)) {
                    Venta oldVentafechaOfOrdenListNewOrden = ordenListNewOrden.getVentafecha();
                    ordenListNewOrden.setVentafecha(venta);
                    ordenListNewOrden = em.merge(ordenListNewOrden);
                    if (oldVentafechaOfOrdenListNewOrden != null && !oldVentafechaOfOrdenListNewOrden.equals(venta)) {
                        oldVentafechaOfOrdenListNewOrden.getOrdenList().remove(ordenListNewOrden);
                        oldVentafechaOfOrdenListNewOrden = em.merge(oldVentafechaOfOrdenListNewOrden);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Date id = venta.getFecha();
                if (findVenta(id) == null) {
                    throw new NonexistentEntityException("The venta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Date id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venta venta;
            try {
                venta = em.getReference(Venta.class, id);
                venta.getFecha();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The venta with id " + id + " no longer exists.", enfe);
            }
            List<Orden> ordenList = venta.getOrdenList();
            
            for (Orden ordenListToDelete : ordenList) {
                staticContent.ordenJPA.destroy(ordenListToDelete.getCodOrden());
             }
            em.remove(venta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Venta> findVentaEntities() {
        return findVentaEntities(true, -1, -1);
    }

    public List<Venta> findVentaEntities(int maxResults, int firstResult) {
        return findVentaEntities(false, maxResults, firstResult);
    }

    private List<Venta> findVentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Venta.class));
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

    public Venta findVenta(Date id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Venta.class, id);
        } finally {
            em.close();
        }
    }

    public int getVentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Venta> rt = cq.from(Venta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
