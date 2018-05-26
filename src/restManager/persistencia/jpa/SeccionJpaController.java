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
import restManager.persistencia.ProductoVenta;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import restManager.persistencia.Seccion;
import restManager.persistencia.jpa.exceptions.NonexistentEntityException;
import restManager.persistencia.jpa.exceptions.PreexistingEntityException;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class SeccionJpaController implements Serializable {

    public SeccionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Seccion seccion) throws PreexistingEntityException, Exception {
        if (seccion.getProductoVentaList() == null) {
            seccion.setProductoVentaList(new ArrayList<ProductoVenta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<ProductoVenta> attachedProductoVentaList = new ArrayList<ProductoVenta>();
            for (ProductoVenta productoVentaListProductoVentaToAttach : seccion.getProductoVentaList()) {
                productoVentaListProductoVentaToAttach = em.getReference(productoVentaListProductoVentaToAttach.getClass(), productoVentaListProductoVentaToAttach.getPCod());
                attachedProductoVentaList.add(productoVentaListProductoVentaToAttach);
            }
            seccion.setProductoVentaList(attachedProductoVentaList);
            em.persist(seccion);
            for (ProductoVenta productoVentaListProductoVenta : seccion.getProductoVentaList()) {
                Seccion oldSeccionnombreSeccionOfProductoVentaListProductoVenta = productoVentaListProductoVenta.getSeccionnombreSeccion();
                productoVentaListProductoVenta.setSeccionnombreSeccion(seccion);
                productoVentaListProductoVenta = em.merge(productoVentaListProductoVenta);
                if (oldSeccionnombreSeccionOfProductoVentaListProductoVenta != null) {
                    oldSeccionnombreSeccionOfProductoVentaListProductoVenta.getProductoVentaList().remove(productoVentaListProductoVenta);
                    oldSeccionnombreSeccionOfProductoVentaListProductoVenta = em.merge(oldSeccionnombreSeccionOfProductoVentaListProductoVenta);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSeccion(seccion.getNombreSeccion()) != null) {
                throw new PreexistingEntityException("Seccion " + seccion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Seccion seccion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Seccion persistentSeccion = em.find(Seccion.class, seccion.getNombreSeccion());
            List<ProductoVenta> productoVentaListOld = persistentSeccion.getProductoVentaList();
            List<ProductoVenta> productoVentaListNew = seccion.getProductoVentaList();
            List<ProductoVenta> attachedProductoVentaListNew = new ArrayList<ProductoVenta>();
            for (ProductoVenta productoVentaListNewProductoVentaToAttach : productoVentaListNew) {
                productoVentaListNewProductoVentaToAttach = em.getReference(productoVentaListNewProductoVentaToAttach.getClass(), productoVentaListNewProductoVentaToAttach.getPCod());
                attachedProductoVentaListNew.add(productoVentaListNewProductoVentaToAttach);
            }
            productoVentaListNew = attachedProductoVentaListNew;
            seccion.setProductoVentaList(productoVentaListNew);
            seccion = em.merge(seccion);
            for (ProductoVenta productoVentaListOldProductoVenta : productoVentaListOld) {
                if (!productoVentaListNew.contains(productoVentaListOldProductoVenta)) {
                    productoVentaListOldProductoVenta.setSeccionnombreSeccion(null);
                    productoVentaListOldProductoVenta = em.merge(productoVentaListOldProductoVenta);
                }
            }
            for (ProductoVenta productoVentaListNewProductoVenta : productoVentaListNew) {
                if (!productoVentaListOld.contains(productoVentaListNewProductoVenta)) {
                    Seccion oldSeccionnombreSeccionOfProductoVentaListNewProductoVenta = productoVentaListNewProductoVenta.getSeccionnombreSeccion();
                    productoVentaListNewProductoVenta.setSeccionnombreSeccion(seccion);
                    productoVentaListNewProductoVenta = em.merge(productoVentaListNewProductoVenta);
                    if (oldSeccionnombreSeccionOfProductoVentaListNewProductoVenta != null && !oldSeccionnombreSeccionOfProductoVentaListNewProductoVenta.equals(seccion)) {
                        oldSeccionnombreSeccionOfProductoVentaListNewProductoVenta.getProductoVentaList().remove(productoVentaListNewProductoVenta);
                        oldSeccionnombreSeccionOfProductoVentaListNewProductoVenta = em.merge(oldSeccionnombreSeccionOfProductoVentaListNewProductoVenta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = seccion.getNombreSeccion();
                if (findSeccion(id) == null) {
                    throw new NonexistentEntityException("The seccion with id " + id + " no longer exists.");
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
            Seccion seccion;
            try {
                seccion = em.getReference(Seccion.class, id);
                seccion.getNombreSeccion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The seccion with id " + id + " no longer exists.", enfe);
            }
            List<ProductoVenta> productoVentaList = seccion.getProductoVentaList();
            for (ProductoVenta productoVentaListProductoVenta : productoVentaList) {
                productoVentaListProductoVenta.setSeccionnombreSeccion(null);
                productoVentaListProductoVenta = em.merge(productoVentaListProductoVenta);
            }
            em.remove(seccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Seccion> findSeccionEntities() {
        return findSeccionEntities(true, -1, -1);
    }

    public List<Seccion> findSeccionEntities(int maxResults, int firstResult) {
        return findSeccionEntities(false, maxResults, firstResult);
    }

    private List<Seccion> findSeccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Seccion.class));
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

    public Seccion findSeccion(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Seccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getSeccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Seccion> rt = cq.from(Seccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
