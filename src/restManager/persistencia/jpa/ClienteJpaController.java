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
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import restManager.persistencia.Cliente;
import restManager.persistencia.jpa.exceptions.NonexistentEntityException;
import restManager.persistencia.jpa.exceptions.PreexistingEntityException;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class ClienteJpaController implements Serializable {

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) throws PreexistingEntityException, Exception {
        if (cliente.getOrdenList() == null) {
            cliente.setOrdenList(new ArrayList<Orden>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Orden> attachedOrdenList = new ArrayList<Orden>();
            for (Orden ordenListOrdenToAttach : cliente.getOrdenList()) {
                ordenListOrdenToAttach = em.getReference(ordenListOrdenToAttach.getClass(), ordenListOrdenToAttach.getCodOrden());
                attachedOrdenList.add(ordenListOrdenToAttach);
            }
            cliente.setOrdenList(attachedOrdenList);
            em.persist(cliente);
            for (Orden ordenListOrden : cliente.getOrdenList()) {
                Cliente oldClientecodClienteOfOrdenListOrden = ordenListOrden.getClientecodCliente();
                ordenListOrden.setClientecodCliente(cliente);
                ordenListOrden = em.merge(ordenListOrden);
                if (oldClientecodClienteOfOrdenListOrden != null) {
                    oldClientecodClienteOfOrdenListOrden.getOrdenList().remove(ordenListOrden);
                    oldClientecodClienteOfOrdenListOrden = em.merge(oldClientecodClienteOfOrdenListOrden);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCliente(cliente.getCodCliente()) != null) {
                throw new PreexistingEntityException("Cliente " + cliente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getCodCliente());
            List<Orden> ordenListOld = persistentCliente.getOrdenList();
            List<Orden> ordenListNew = cliente.getOrdenList();
            List<Orden> attachedOrdenListNew = new ArrayList<Orden>();
            for (Orden ordenListNewOrdenToAttach : ordenListNew) {
                ordenListNewOrdenToAttach = em.getReference(ordenListNewOrdenToAttach.getClass(), ordenListNewOrdenToAttach.getCodOrden());
                attachedOrdenListNew.add(ordenListNewOrdenToAttach);
            }
            ordenListNew = attachedOrdenListNew;
            cliente.setOrdenList(ordenListNew);
            cliente = em.merge(cliente);
            for (Orden ordenListOldOrden : ordenListOld) {
                if (!ordenListNew.contains(ordenListOldOrden)) {
                    ordenListOldOrden.setClientecodCliente(null);
                    ordenListOldOrden = em.merge(ordenListOldOrden);
                }
            }
            for (Orden ordenListNewOrden : ordenListNew) {
                if (!ordenListOld.contains(ordenListNewOrden)) {
                    Cliente oldClientecodClienteOfOrdenListNewOrden = ordenListNewOrden.getClientecodCliente();
                    ordenListNewOrden.setClientecodCliente(cliente);
                    ordenListNewOrden = em.merge(ordenListNewOrden);
                    if (oldClientecodClienteOfOrdenListNewOrden != null && !oldClientecodClienteOfOrdenListNewOrden.equals(cliente)) {
                        oldClientecodClienteOfOrdenListNewOrden.getOrdenList().remove(ordenListNewOrden);
                        oldClientecodClienteOfOrdenListNewOrden = em.merge(oldClientecodClienteOfOrdenListNewOrden);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = cliente.getCodCliente();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
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
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getCodCliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            List<Orden> ordenList = cliente.getOrdenList();
            for (Orden ordenListOrden : ordenList) {
                ordenListOrden.setClientecodCliente(null);
                ordenListOrden = em.merge(ordenListOrden);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
