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
import restManager.persistencia.Insumo;
import restManager.persistencia.ProductoInsumo;
import restManager.persistencia.ProductoInsumoPK;
import restManager.persistencia.ProductoVenta;
import restManager.persistencia.jpa.exceptions.NonexistentEntityException;
import restManager.persistencia.jpa.exceptions.PreexistingEntityException;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class ProductoInsumoJpaController implements Serializable {

    public ProductoInsumoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProductoInsumo productoInsumo) throws PreexistingEntityException, Exception {
        if (productoInsumo.getProductoInsumoPK() == null) {
            productoInsumo.setProductoInsumoPK(new ProductoInsumoPK());
        }
        productoInsumo.getProductoInsumoPK().setProductoVentapCod(productoInsumo.getProductoVenta().getPCod());
        productoInsumo.getProductoInsumoPK().setInsumocodInsumo(productoInsumo.getInsumo().getCodInsumo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Insumo insumo = productoInsumo.getInsumo();
            if (insumo != null) {
                insumo = em.getReference(insumo.getClass(), insumo.getCodInsumo());
                productoInsumo.setInsumo(insumo);
            }
            ProductoVenta productoVenta = productoInsumo.getProductoVenta();
            if (productoVenta != null) {
                productoVenta = em.getReference(productoVenta.getClass(), productoVenta.getPCod());
                productoInsumo.setProductoVenta(productoVenta);
            }
            em.persist(productoInsumo);
            if (insumo != null) {
                insumo.getProductoInsumoList().add(productoInsumo);
                insumo = em.merge(insumo);
            }
            if (productoVenta != null) {
                productoVenta.getProductoInsumoList().add(productoInsumo);
                productoVenta = em.merge(productoVenta);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProductoInsumo(productoInsumo.getProductoInsumoPK()) != null) {
                throw new PreexistingEntityException("ProductoInsumo " + productoInsumo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProductoInsumo productoInsumo) throws NonexistentEntityException, Exception {
        productoInsumo.getProductoInsumoPK().setProductoVentapCod(productoInsumo.getProductoVenta().getPCod());
        productoInsumo.getProductoInsumoPK().setInsumocodInsumo(productoInsumo.getInsumo().getCodInsumo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProductoInsumo persistentProductoInsumo = em.find(ProductoInsumo.class, productoInsumo.getProductoInsumoPK());
            Insumo insumoOld = persistentProductoInsumo.getInsumo();
            Insumo insumoNew = productoInsumo.getInsumo();
            ProductoVenta productoVentaOld = persistentProductoInsumo.getProductoVenta();
            ProductoVenta productoVentaNew = productoInsumo.getProductoVenta();
            if (insumoNew != null) {
                insumoNew = em.getReference(insumoNew.getClass(), insumoNew.getCodInsumo());
                productoInsumo.setInsumo(insumoNew);
            }
            if (productoVentaNew != null) {
                productoVentaNew = em.getReference(productoVentaNew.getClass(), productoVentaNew.getPCod());
                productoInsumo.setProductoVenta(productoVentaNew);
            }
            productoInsumo = em.merge(productoInsumo);
            if (insumoOld != null && !insumoOld.equals(insumoNew)) {
                insumoOld.getProductoInsumoList().remove(productoInsumo);
                insumoOld = em.merge(insumoOld);
            }
            if (insumoNew != null && !insumoNew.equals(insumoOld)) {
                insumoNew.getProductoInsumoList().add(productoInsumo);
                insumoNew = em.merge(insumoNew);
            }
            if (productoVentaOld != null && !productoVentaOld.equals(productoVentaNew)) {
                productoVentaOld.getProductoInsumoList().remove(productoInsumo);
                productoVentaOld = em.merge(productoVentaOld);
            }
            if (productoVentaNew != null && !productoVentaNew.equals(productoVentaOld)) {
                productoVentaNew.getProductoInsumoList().add(productoInsumo);
                productoVentaNew = em.merge(productoVentaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ProductoInsumoPK id = productoInsumo.getProductoInsumoPK();
                if (findProductoInsumo(id) == null) {
                    throw new NonexistentEntityException("The productoInsumo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ProductoInsumoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProductoInsumo productoInsumo;
            try {
                productoInsumo = em.getReference(ProductoInsumo.class, id);
                productoInsumo.getProductoInsumoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productoInsumo with id " + id + " no longer exists.", enfe);
            }
            Insumo insumo = productoInsumo.getInsumo();
            if (insumo != null) {
                insumo.getProductoInsumoList().remove(productoInsumo);
                insumo = em.merge(insumo);
            }
            ProductoVenta productoVenta = productoInsumo.getProductoVenta();
            if (productoVenta != null) {
                productoVenta.getProductoInsumoList().remove(productoInsumo);
                productoVenta = em.merge(productoVenta);
            }
            em.remove(productoInsumo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProductoInsumo> findProductoInsumoEntities() {
        return findProductoInsumoEntities(true, -1, -1);
    }

    public List<ProductoInsumo> findProductoInsumoEntities(int maxResults, int firstResult) {
        return findProductoInsumoEntities(false, maxResults, firstResult);
    }

    private List<ProductoInsumo> findProductoInsumoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProductoInsumo.class));
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

    public ProductoInsumo findProductoInsumo(ProductoInsumoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProductoInsumo.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductoInsumoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProductoInsumo> rt = cq.from(ProductoInsumo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
