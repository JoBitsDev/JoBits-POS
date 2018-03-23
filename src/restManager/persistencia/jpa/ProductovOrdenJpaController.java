/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restManager.persistencia.jpa;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import restManager.persistencia.Orden;
import restManager.persistencia.ProductoVenta;
import restManager.persistencia.ProductovOrden;
import restManager.persistencia.ProductovOrdenPK;
import restManager.persistencia.jpa.exceptions.NonexistentEntityException;
import restManager.persistencia.jpa.exceptions.PreexistingEntityException;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class ProductovOrdenJpaController implements Serializable {

    public ProductovOrdenJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProductovOrden productovOrden) throws PreexistingEntityException, Exception {
        if (productovOrden.getProductovOrdenPK() == null) {
            productovOrden.setProductovOrdenPK(new ProductovOrdenPK());
        }
        productovOrden.getProductovOrdenPK().setProductoVentapCod(productovOrden.getProductoVenta().getPCod());
        productovOrden.getProductovOrdenPK().setOrdencodOrden(productovOrden.getOrden().getCodOrden());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Orden orden = productovOrden.getOrden();
            if (orden != null) {
                orden = em.getReference(orden.getClass(), orden.getCodOrden());
                productovOrden.setOrden(orden);
            }
            ProductoVenta productoVenta = productovOrden.getProductoVenta();
            if (productoVenta != null) {
                productoVenta = em.getReference(productoVenta.getClass(), productoVenta.getPCod());
                productovOrden.setProductoVenta(productoVenta);
            }
            em.persist(productovOrden);
            if (orden != null) {
                orden.getProductovOrdenList().add(productovOrden);
                orden = em.merge(orden);
            }
            if (productoVenta != null) {
                productoVenta.getProductovOrdenList().add(productovOrden);
                productoVenta = em.merge(productoVenta);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProductovOrden(productovOrden.getProductovOrdenPK()) != null) {
                throw new PreexistingEntityException("ProductovOrden " + productovOrden + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProductovOrden productovOrden) throws NonexistentEntityException, Exception {
        productovOrden.getProductovOrdenPK().setProductoVentapCod(productovOrden.getProductoVenta().getPCod());
        productovOrden.getProductovOrdenPK().setOrdencodOrden(productovOrden.getOrden().getCodOrden());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProductovOrden persistentProductovOrden = em.find(ProductovOrden.class, productovOrden.getProductovOrdenPK());
            Orden ordenOld = persistentProductovOrden.getOrden();
            Orden ordenNew = productovOrden.getOrden();
            ProductoVenta productoVentaOld = persistentProductovOrden.getProductoVenta();
            ProductoVenta productoVentaNew = productovOrden.getProductoVenta();
            if (ordenNew != null) {
                ordenNew = em.getReference(ordenNew.getClass(), ordenNew.getCodOrden());
                productovOrden.setOrden(ordenNew);
            }
            if (productoVentaNew != null) {
                productoVentaNew = em.getReference(productoVentaNew.getClass(), productoVentaNew.getPCod());
                productovOrden.setProductoVenta(productoVentaNew);
            }
            productovOrden = em.merge(productovOrden);
            if (ordenOld != null && !ordenOld.equals(ordenNew)) {
                ordenOld.getProductovOrdenList().remove(productovOrden);
                ordenOld = em.merge(ordenOld);
            }
            if (ordenNew != null && !ordenNew.equals(ordenOld)) {
                ordenNew.getProductovOrdenList().add(productovOrden);
                ordenNew = em.merge(ordenNew);
            }
            if (productoVentaOld != null && !productoVentaOld.equals(productoVentaNew)) {
                productoVentaOld.getProductovOrdenList().remove(productovOrden);
                productoVentaOld = em.merge(productoVentaOld);
            }
            if (productoVentaNew != null && !productoVentaNew.equals(productoVentaOld)) {
                productoVentaNew.getProductovOrdenList().add(productovOrden);
                productoVentaNew = em.merge(productoVentaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ProductovOrdenPK id = productovOrden.getProductovOrdenPK();
                if (findProductovOrden(id) == null) {
                    throw new NonexistentEntityException("The productovOrden with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ProductovOrdenPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProductovOrden productovOrden;
            try {
                productovOrden = em.getReference(ProductovOrden.class, id);
                productovOrden.getProductovOrdenPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productovOrden with id " + id + " no longer exists.", enfe);
            }
            Orden orden = productovOrden.getOrden();
            if (orden != null) {
                orden.getProductovOrdenList().remove(productovOrden);
                orden = em.merge(orden);
            }
            ProductoVenta productoVenta = productovOrden.getProductoVenta();
            if (productoVenta != null) {
                productoVenta.getProductovOrdenList().remove(productovOrden);
                productoVenta = em.merge(productoVenta);
            }
            em.remove(productovOrden);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProductovOrden> findProductovOrdenEntities() {
        return findProductovOrdenEntities(true, -1, -1);
    }

    public List<ProductovOrden> findProductovOrdenEntities(int maxResults, int firstResult) {
        return findProductovOrdenEntities(false, maxResults, firstResult);
    }

    private List<ProductovOrden> findProductovOrdenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProductovOrden.class));
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

    public ProductovOrden findProductovOrden(ProductovOrdenPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProductovOrden.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductovOrdenCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProductovOrden> rt = cq.from(ProductovOrden.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
