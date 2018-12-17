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
import restManager.persistencia.Cocina;
import restManager.persistencia.Seccion;
import restManager.persistencia.ProductoInsumo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import restManager.persistencia.ProductoVenta;
import restManager.persistencia.ProductovOrden;
import restManager.persistencia.jpa.exceptions.IllegalOrphanException;
import restManager.persistencia.jpa.exceptions.NonexistentEntityException;
import restManager.persistencia.jpa.exceptions.PreexistingEntityException;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class ProductoVentaJpaController implements Serializable {

    public ProductoVentaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProductoVenta productoVenta) throws PreexistingEntityException, Exception {
        if (productoVenta.getProductoInsumoList() == null) {
            productoVenta.setProductoInsumoList(new ArrayList<ProductoInsumo>());
        }
        if (productoVenta.getProductovOrdenList() == null) {
            productoVenta.setProductovOrdenList(new ArrayList<ProductovOrden>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cocina cocinacodCocina = productoVenta.getCocinacodCocina();
            if (cocinacodCocina != null) {
                cocinacodCocina = em.getReference(cocinacodCocina.getClass(), cocinacodCocina.getCodCocina());
                productoVenta.setCocinacodCocina(cocinacodCocina);
            }
            Seccion seccionnombreSeccion = productoVenta.getSeccionnombreSeccion();
            if (seccionnombreSeccion != null) {
                seccionnombreSeccion = em.getReference(seccionnombreSeccion.getClass(), seccionnombreSeccion.getNombreSeccion());
                productoVenta.setSeccionnombreSeccion(seccionnombreSeccion);
            }
            List<ProductoInsumo> attachedProductoInsumoList = new ArrayList<ProductoInsumo>();
            for (ProductoInsumo productoInsumoListProductoInsumoToAttach : productoVenta.getProductoInsumoList()) {
                productoInsumoListProductoInsumoToAttach = em.getReference(productoInsumoListProductoInsumoToAttach.getClass(), productoInsumoListProductoInsumoToAttach.getProductoInsumoPK());
                attachedProductoInsumoList.add(productoInsumoListProductoInsumoToAttach);
            }
            productoVenta.setProductoInsumoList(attachedProductoInsumoList);
            List<ProductovOrden> attachedProductovOrdenList = new ArrayList<ProductovOrden>();
            for (ProductovOrden productovOrdenListProductovOrdenToAttach : productoVenta.getProductovOrdenList()) {
                productovOrdenListProductovOrdenToAttach = em.getReference(productovOrdenListProductovOrdenToAttach.getClass(), productovOrdenListProductovOrdenToAttach.getProductovOrdenPK());
                attachedProductovOrdenList.add(productovOrdenListProductovOrdenToAttach);
            }
            productoVenta.setProductovOrdenList(attachedProductovOrdenList);
            em.persist(productoVenta);
            if (cocinacodCocina != null) {
                cocinacodCocina.getProductoVentaList().add(productoVenta);
                cocinacodCocina = em.merge(cocinacodCocina);
            }
            if (seccionnombreSeccion != null) {
                seccionnombreSeccion.getProductoVentaList().add(productoVenta);
                seccionnombreSeccion = em.merge(seccionnombreSeccion);
            }
            for (ProductoInsumo productoInsumoListProductoInsumo : productoVenta.getProductoInsumoList()) {
                ProductoVenta oldProductoVentaOfProductoInsumoListProductoInsumo = productoInsumoListProductoInsumo.getProductoVenta();
                productoInsumoListProductoInsumo.setProductoVenta(productoVenta);
                productoInsumoListProductoInsumo = em.merge(productoInsumoListProductoInsumo);
                if (oldProductoVentaOfProductoInsumoListProductoInsumo != null) {
                    oldProductoVentaOfProductoInsumoListProductoInsumo.getProductoInsumoList().remove(productoInsumoListProductoInsumo);
                    oldProductoVentaOfProductoInsumoListProductoInsumo = em.merge(oldProductoVentaOfProductoInsumoListProductoInsumo);
                }
            }
            for (ProductovOrden productovOrdenListProductovOrden : productoVenta.getProductovOrdenList()) {
                ProductoVenta oldProductoVentaOfProductovOrdenListProductovOrden = productovOrdenListProductovOrden.getProductoVenta();
                productovOrdenListProductovOrden.setProductoVenta(productoVenta);
                productovOrdenListProductovOrden = em.merge(productovOrdenListProductovOrden);
                if (oldProductoVentaOfProductovOrdenListProductovOrden != null) {
                    oldProductoVentaOfProductovOrdenListProductovOrden.getProductovOrdenList().remove(productovOrdenListProductovOrden);
                    oldProductoVentaOfProductovOrdenListProductovOrden = em.merge(oldProductoVentaOfProductovOrdenListProductovOrden);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProductoVenta(productoVenta.getPCod()) != null) {
                throw new PreexistingEntityException("ProductoVenta " + productoVenta + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProductoVenta productoVenta) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProductoVenta persistentProductoVenta = em.find(ProductoVenta.class, productoVenta.getPCod());
            Cocina cocinacodCocinaOld = persistentProductoVenta.getCocinacodCocina();
            Cocina cocinacodCocinaNew = productoVenta.getCocinacodCocina();
            Seccion seccionnombreSeccionOld = persistentProductoVenta.getSeccionnombreSeccion();
            Seccion seccionnombreSeccionNew = productoVenta.getSeccionnombreSeccion();
            List<ProductoInsumo> productoInsumoListOld = persistentProductoVenta.getProductoInsumoList();
            List<ProductoInsumo> productoInsumoListNew = productoVenta.getProductoInsumoList();
            List<ProductovOrden> productovOrdenListOld = persistentProductoVenta.getProductovOrdenList();
            List<ProductovOrden> productovOrdenListNew = productoVenta.getProductovOrdenList();
            List<String> illegalOrphanMessages = null;
            for (ProductoInsumo productoInsumoListOldProductoInsumo : productoInsumoListOld) {
                if (!productoInsumoListNew.contains(productoInsumoListOldProductoInsumo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProductoInsumo " + productoInsumoListOldProductoInsumo + " since its productoVenta field is not nullable.");
                }
            }
            for (ProductovOrden productovOrdenListOldProductovOrden : productovOrdenListOld) {
                if (!productovOrdenListNew.contains(productovOrdenListOldProductovOrden)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProductovOrden " + productovOrdenListOldProductovOrden + " since its productoVenta field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (cocinacodCocinaNew != null) {
                cocinacodCocinaNew = em.getReference(cocinacodCocinaNew.getClass(), cocinacodCocinaNew.getCodCocina());
                productoVenta.setCocinacodCocina(cocinacodCocinaNew);
            }
            if (seccionnombreSeccionNew != null) {
                seccionnombreSeccionNew = em.getReference(seccionnombreSeccionNew.getClass(), seccionnombreSeccionNew.getNombreSeccion());
                productoVenta.setSeccionnombreSeccion(seccionnombreSeccionNew);
            }
            List<ProductoInsumo> attachedProductoInsumoListNew = new ArrayList<ProductoInsumo>();
            for (ProductoInsumo productoInsumoListNewProductoInsumoToAttach : productoInsumoListNew) {
                productoInsumoListNewProductoInsumoToAttach = em.getReference(productoInsumoListNewProductoInsumoToAttach.getClass(), productoInsumoListNewProductoInsumoToAttach.getProductoInsumoPK());
                attachedProductoInsumoListNew.add(productoInsumoListNewProductoInsumoToAttach);
            }
            productoInsumoListNew = attachedProductoInsumoListNew;
            productoVenta.setProductoInsumoList(productoInsumoListNew);
            List<ProductovOrden> attachedProductovOrdenListNew = new ArrayList<ProductovOrden>();
            for (ProductovOrden x : productovOrdenListNew) {
                x = em.getReference(x.getClass(), x.getProductovOrdenPK());
                attachedProductovOrdenListNew.add(x);
            }
            productovOrdenListNew = attachedProductovOrdenListNew;
            productoVenta.setProductovOrdenList(productovOrdenListNew);
            productoVenta = em.merge(productoVenta);
            if (cocinacodCocinaOld != null && !cocinacodCocinaOld.equals(cocinacodCocinaNew)) {
                cocinacodCocinaOld.getProductoVentaList().remove(productoVenta);
                cocinacodCocinaOld = em.merge(cocinacodCocinaOld);
            }
            if (cocinacodCocinaNew != null && !cocinacodCocinaNew.equals(cocinacodCocinaOld)) {
                cocinacodCocinaNew.getProductoVentaList().add(productoVenta);
                cocinacodCocinaNew = em.merge(cocinacodCocinaNew);
            }
            if (seccionnombreSeccionOld != null && !seccionnombreSeccionOld.equals(seccionnombreSeccionNew)) {
                seccionnombreSeccionOld.getProductoVentaList().remove(productoVenta);
                seccionnombreSeccionOld = em.merge(seccionnombreSeccionOld);
            }
            if (seccionnombreSeccionNew != null && !seccionnombreSeccionNew.equals(seccionnombreSeccionOld)) {
                seccionnombreSeccionNew.getProductoVentaList().add(productoVenta);
                seccionnombreSeccionNew = em.merge(seccionnombreSeccionNew);
            }
            for (ProductoInsumo productoInsumoListNewProductoInsumo : productoInsumoListNew) {
                if (!productoInsumoListOld.contains(productoInsumoListNewProductoInsumo)) {
                    ProductoVenta oldProductoVentaOfProductoInsumoListNewProductoInsumo = productoInsumoListNewProductoInsumo.getProductoVenta();
                    productoInsumoListNewProductoInsumo.setProductoVenta(productoVenta);
                    productoInsumoListNewProductoInsumo = em.merge(productoInsumoListNewProductoInsumo);
                    if (oldProductoVentaOfProductoInsumoListNewProductoInsumo != null && !oldProductoVentaOfProductoInsumoListNewProductoInsumo.equals(productoVenta)) {
                        oldProductoVentaOfProductoInsumoListNewProductoInsumo.getProductoInsumoList().remove(productoInsumoListNewProductoInsumo);
                        oldProductoVentaOfProductoInsumoListNewProductoInsumo = em.merge(oldProductoVentaOfProductoInsumoListNewProductoInsumo);
                    }
                }
            }
            for (ProductovOrden productovOrdenListNewProductovOrden : productovOrdenListNew) {
                if (!productovOrdenListOld.contains(productovOrdenListNewProductovOrden)) {
                    ProductoVenta oldProductoVentaOfProductovOrdenListNewProductovOrden = productovOrdenListNewProductovOrden.getProductoVenta();
                    productovOrdenListNewProductovOrden.setProductoVenta(productoVenta);
                    productovOrdenListNewProductovOrden = em.merge(productovOrdenListNewProductovOrden);
                    if (oldProductoVentaOfProductovOrdenListNewProductovOrden != null && !oldProductoVentaOfProductovOrdenListNewProductovOrden.equals(productoVenta)) {
                        oldProductoVentaOfProductovOrdenListNewProductovOrden.getProductovOrdenList().remove(productovOrdenListNewProductovOrden);
                        oldProductoVentaOfProductovOrdenListNewProductovOrden = em.merge(oldProductoVentaOfProductovOrdenListNewProductovOrden);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = productoVenta.getPCod();
                if (findProductoVenta(id) == null) {
                    throw new NonexistentEntityException("The productoVenta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
         EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProductoVenta productoVenta;
            try {
                productoVenta = em.getReference(ProductoVenta.class, id);
                productoVenta.getPCod();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productoVenta with id " + id + " no longer exists.", enfe);
            }
            for (ProductoInsumo x : productoVenta.getProductoInsumoList()) {
                em.remove(x);
            }
            for (ProductovOrden x : productoVenta.getProductovOrdenList()) {
                em.remove(x);
            }
            em.remove(productoVenta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProductoVenta> findProductoVentaEntities() {
        return findProductoVentaEntities(true, -1, -1);
    }

    public List<ProductoVenta> findProductoVentaEntities(int maxResults, int firstResult) {
        return findProductoVentaEntities(false, maxResults, firstResult);
    }

    private List<ProductoVenta> findProductoVentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProductoVenta.class));
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

    public ProductoVenta findProductoVenta(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProductoVenta.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductoVentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProductoVenta> rt = cq.from(ProductoVenta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
