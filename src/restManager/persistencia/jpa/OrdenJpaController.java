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
import restManager.persistencia.Cliente;
import restManager.persistencia.Mesa;
import restManager.persistencia.Personal;
import restManager.persistencia.Venta;
import restManager.persistencia.ProductovOrden;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import restManager.persistencia.Orden;
import restManager.persistencia.jpa.exceptions.IllegalOrphanException;
import restManager.persistencia.jpa.exceptions.NonexistentEntityException;
import restManager.persistencia.jpa.exceptions.PreexistingEntityException;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class OrdenJpaController implements Serializable {

    public OrdenJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Orden orden) throws PreexistingEntityException, Exception {
        if (orden.getProductovOrdenList() == null) {
            orden.setProductovOrdenList(new ArrayList<ProductovOrden>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente clientecodCliente = orden.getClientecodCliente();
            if (clientecodCliente != null) {
                clientecodCliente = em.getReference(clientecodCliente.getClass(), clientecodCliente.getCodCliente());
                orden.setClientecodCliente(clientecodCliente);
            }
            Mesa mesacodMesa = orden.getMesacodMesa();
            if (mesacodMesa != null) {
                mesacodMesa = em.getReference(mesacodMesa.getClass(), mesacodMesa.getCodMesa());
                orden.setMesacodMesa(mesacodMesa);
            }
            Personal personalusuario = orden.getPersonalusuario();
            if (personalusuario != null) {
                personalusuario = em.getReference(personalusuario.getClass(), personalusuario.getUsuario());
                orden.setPersonalusuario(personalusuario);
            }
            Venta ventafecha = orden.getVentafecha();
            if (ventafecha != null) {
                ventafecha = em.getReference(ventafecha.getClass(), ventafecha.getFecha());
                orden.setVentafecha(ventafecha);
            }
            List<ProductovOrden> attachedProductovOrdenList = new ArrayList<ProductovOrden>();
            for (ProductovOrden productovOrdenListProductovOrdenToAttach : orden.getProductovOrdenList()) {
                productovOrdenListProductovOrdenToAttach = em.getReference(productovOrdenListProductovOrdenToAttach.getClass(), productovOrdenListProductovOrdenToAttach.getProductovOrdenPK());
                attachedProductovOrdenList.add(productovOrdenListProductovOrdenToAttach);
            }
            orden.setProductovOrdenList(attachedProductovOrdenList);
            em.persist(orden);
            if (clientecodCliente != null) {
                clientecodCliente.getOrdenList().add(orden);
                clientecodCliente = em.merge(clientecodCliente);
            }
            if (mesacodMesa != null) {
                mesacodMesa.getOrdenList().add(orden);
                mesacodMesa = em.merge(mesacodMesa);
            }
            if (personalusuario != null) {
                personalusuario.getOrdenList().add(orden);
                personalusuario = em.merge(personalusuario);
            }
            if (ventafecha != null) {
                ventafecha.getOrdenList().add(orden);
                ventafecha = em.merge(ventafecha);
            }
            for (ProductovOrden productovOrdenListProductovOrden : orden.getProductovOrdenList()) {
                Orden oldOrdenOfProductovOrdenListProductovOrden = productovOrdenListProductovOrden.getOrden();
                productovOrdenListProductovOrden.setOrden(orden);
                productovOrdenListProductovOrden = em.merge(productovOrdenListProductovOrden);
                if (oldOrdenOfProductovOrdenListProductovOrden != null) {
                    oldOrdenOfProductovOrdenListProductovOrden.getProductovOrdenList().remove(productovOrdenListProductovOrden);
                    oldOrdenOfProductovOrdenListProductovOrden = em.merge(oldOrdenOfProductovOrdenListProductovOrden);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOrden(orden.getCodOrden()) != null) {
                throw new PreexistingEntityException("Orden " + orden + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Orden orden) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Orden persistentOrden = em.find(Orden.class, orden.getCodOrden());
            Cliente clientecodClienteOld = persistentOrden.getClientecodCliente();
            Cliente clientecodClienteNew = orden.getClientecodCliente();
            Mesa mesacodMesaOld = persistentOrden.getMesacodMesa();
            Mesa mesacodMesaNew = orden.getMesacodMesa();
            Personal personalusuarioOld = persistentOrden.getPersonalusuario();
            Personal personalusuarioNew = orden.getPersonalusuario();
            Venta ventafechaOld = persistentOrden.getVentafecha();
            Venta ventafechaNew = orden.getVentafecha();
            List<ProductovOrden> productovOrdenListOld = persistentOrden.getProductovOrdenList();
            List<ProductovOrden> productovOrdenListNew = orden.getProductovOrdenList();
            List<String> illegalOrphanMessages = null;
            for (ProductovOrden productovOrdenListOldProductovOrden : productovOrdenListOld) {
                if (!productovOrdenListNew.contains(productovOrdenListOldProductovOrden)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProductovOrden " + productovOrdenListOldProductovOrden + " since its orden field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (clientecodClienteNew != null) {
                clientecodClienteNew = em.getReference(clientecodClienteNew.getClass(), clientecodClienteNew.getCodCliente());
                orden.setClientecodCliente(clientecodClienteNew);
            }
            if (mesacodMesaNew != null) {
                mesacodMesaNew = em.getReference(mesacodMesaNew.getClass(), mesacodMesaNew.getCodMesa());
                orden.setMesacodMesa(mesacodMesaNew);
            }
            if (personalusuarioNew != null) {
                personalusuarioNew = em.getReference(personalusuarioNew.getClass(), personalusuarioNew.getUsuario());
                orden.setPersonalusuario(personalusuarioNew);
            }
            if (ventafechaNew != null) {
                ventafechaNew = em.getReference(ventafechaNew.getClass(), ventafechaNew.getFecha());
                orden.setVentafecha(ventafechaNew);
            }
            List<ProductovOrden> attachedProductovOrdenListNew = new ArrayList<ProductovOrden>();
            for (ProductovOrden productovOrdenListNewProductovOrdenToAttach : productovOrdenListNew) {
                productovOrdenListNewProductovOrdenToAttach = em.getReference(productovOrdenListNewProductovOrdenToAttach.getClass(), productovOrdenListNewProductovOrdenToAttach.getProductovOrdenPK());
                attachedProductovOrdenListNew.add(productovOrdenListNewProductovOrdenToAttach);
            }
            productovOrdenListNew = attachedProductovOrdenListNew;
            orden.setProductovOrdenList(productovOrdenListNew);
            orden = em.merge(orden);
            if (clientecodClienteOld != null && !clientecodClienteOld.equals(clientecodClienteNew)) {
                clientecodClienteOld.getOrdenList().remove(orden);
                clientecodClienteOld = em.merge(clientecodClienteOld);
            }
            if (clientecodClienteNew != null && !clientecodClienteNew.equals(clientecodClienteOld)) {
                clientecodClienteNew.getOrdenList().add(orden);
                clientecodClienteNew = em.merge(clientecodClienteNew);
            }
            if (mesacodMesaOld != null && !mesacodMesaOld.equals(mesacodMesaNew)) {
                mesacodMesaOld.getOrdenList().remove(orden);
                mesacodMesaOld = em.merge(mesacodMesaOld);
            }
            if (mesacodMesaNew != null && !mesacodMesaNew.equals(mesacodMesaOld)) {
                mesacodMesaNew.getOrdenList().add(orden);
                mesacodMesaNew = em.merge(mesacodMesaNew);
            }
            if (personalusuarioOld != null && !personalusuarioOld.equals(personalusuarioNew)) {
                personalusuarioOld.getOrdenList().remove(orden);
                personalusuarioOld = em.merge(personalusuarioOld);
            }
            if (personalusuarioNew != null && !personalusuarioNew.equals(personalusuarioOld)) {
                personalusuarioNew.getOrdenList().add(orden);
                personalusuarioNew = em.merge(personalusuarioNew);
            }
            if (ventafechaOld != null && !ventafechaOld.equals(ventafechaNew)) {
                ventafechaOld.getOrdenList().remove(orden);
                ventafechaOld = em.merge(ventafechaOld);
            }
            if (ventafechaNew != null && !ventafechaNew.equals(ventafechaOld)) {
                ventafechaNew.getOrdenList().add(orden);
                ventafechaNew = em.merge(ventafechaNew);
            }
            for (ProductovOrden productovOrdenListNewProductovOrden : productovOrdenListNew) {
                if (!productovOrdenListOld.contains(productovOrdenListNewProductovOrden)) {
                    Orden oldOrdenOfProductovOrdenListNewProductovOrden = productovOrdenListNewProductovOrden.getOrden();
                    productovOrdenListNewProductovOrden.setOrden(orden);
                    productovOrdenListNewProductovOrden = em.merge(productovOrdenListNewProductovOrden);
                    if (oldOrdenOfProductovOrdenListNewProductovOrden != null && !oldOrdenOfProductovOrdenListNewProductovOrden.equals(orden)) {
                        oldOrdenOfProductovOrdenListNewProductovOrden.getProductovOrdenList().remove(productovOrdenListNewProductovOrden);
                        oldOrdenOfProductovOrdenListNewProductovOrden = em.merge(oldOrdenOfProductovOrdenListNewProductovOrden);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = orden.getCodOrden();
                if (findOrden(id) == null) {
                    throw new NonexistentEntityException("The orden with id " + id + " no longer exists.");
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
            Orden orden;
            try {
                orden = em.getReference(Orden.class, id);
                orden.getCodOrden();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The orden with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<ProductovOrden> productovOrdenList = orden.getProductovOrdenList();
            for (ProductovOrden productovOrden : productovOrdenList) {
                staticContent.productovOrdenJpa.destroy(productovOrden.getProductovOrdenPK());
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cliente clientecodCliente = orden.getClientecodCliente();
            if (clientecodCliente != null) {
                clientecodCliente.getOrdenList().remove(orden);
                clientecodCliente = em.merge(clientecodCliente);
            }
            Mesa mesacodMesa = orden.getMesacodMesa();
            if (mesacodMesa != null) {
                mesacodMesa.getOrdenList().remove(orden);
                mesacodMesa = em.merge(mesacodMesa);
            }
            Personal personalusuario = orden.getPersonalusuario();
            if (personalusuario != null) {
                personalusuario.getOrdenList().remove(orden);
                personalusuario = em.merge(personalusuario);
            }
            Venta ventafecha = orden.getVentafecha();
            if (ventafecha != null) {
                ventafecha.getOrdenList().remove(orden);
                ventafecha = em.merge(ventafecha);
            }
            em.remove(orden);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Orden> findOrdenEntities() {
        return findOrdenEntities(true, -1, -1);
    }

    public List<Orden> findOrdenEntities(int maxResults, int firstResult) {
        return findOrdenEntities(false, maxResults, firstResult);
    }

    private List<Orden> findOrdenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Orden.class));
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

    public Orden findOrden(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Orden.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrdenCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Orden> rt = cq.from(Orden.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
