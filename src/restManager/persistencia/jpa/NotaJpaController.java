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
import restManager.persistencia.ProductovOrden;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import restManager.persistencia.Nota;
import restManager.persistencia.NotaPK;
import restManager.persistencia.jpa.exceptions.IllegalOrphanException;
import restManager.persistencia.jpa.exceptions.NonexistentEntityException;
import restManager.persistencia.jpa.exceptions.PreexistingEntityException;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class NotaJpaController implements Serializable {

    public NotaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Nota nota) throws IllegalOrphanException, PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProductovOrden productovOrden = nota.getProductovOrden();
            if (productovOrden != null) {
                productovOrden = em.getReference(productovOrden.getClass(), productovOrden.getProductovOrdenPK());
                nota.setProductovOrden(productovOrden);
            }
            em.persist(nota);
            if (productovOrden != null) {
                productovOrden.setNota(nota);
                productovOrden = em.merge(productovOrden);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findNota(nota.getNotaPK()) != null) {
                throw new PreexistingEntityException("Nota " + nota + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Nota nota) throws IllegalOrphanException, NonexistentEntityException, Exception {
        nota.getNotaPK().setProductovOrdenproductoVentapCod(nota.getProductovOrden().getProductovOrdenPK().getProductoVentapCod());
        nota.getNotaPK().setProductovOrdenordencodOrden(nota.getProductovOrden().getProductovOrdenPK().getOrdencodOrden());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Nota persistentNota = em.find(Nota.class, nota.getNotaPK());
            ProductovOrden productovOrdenOld = persistentNota.getProductovOrden();
            ProductovOrden productovOrdenNew = nota.getProductovOrden();
            List<String> illegalOrphanMessages = null;
            if (productovOrdenNew != null && !productovOrdenNew.equals(productovOrdenOld)) {
                Nota oldNotaOfProductovOrden = productovOrdenNew.getNota();
                if (oldNotaOfProductovOrden != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The ProductovOrden " + productovOrdenNew + " already has an item of type Nota whose productovOrden column cannot be null. Please make another selection for the productovOrden field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (productovOrdenNew != null) {
                productovOrdenNew = em.getReference(productovOrdenNew.getClass(), productovOrdenNew.getProductovOrdenPK());
                nota.setProductovOrden(productovOrdenNew);
            }
            nota = em.merge(nota);
            if (productovOrdenOld != null && !productovOrdenOld.equals(productovOrdenNew)) {
                productovOrdenOld.setNota(null);
                productovOrdenOld = em.merge(productovOrdenOld);
            }
            if (productovOrdenNew != null && !productovOrdenNew.equals(productovOrdenOld)) {
                productovOrdenNew.setNota(nota);
                productovOrdenNew = em.merge(productovOrdenNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                NotaPK id = nota.getNotaPK();
                if (findNota(id) == null) {
                    throw new NonexistentEntityException("The nota with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(NotaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Nota nota;
            try {
                nota = em.getReference(Nota.class, id);
                nota.getNotaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nota with id " + id + " no longer exists.", enfe);
            }
            ProductovOrden productovOrden = nota.getProductovOrden();
            if (productovOrden != null) {
                productovOrden.setNota(null);
                productovOrden = em.merge(productovOrden);
            }
            em.remove(nota);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Nota> findNotaEntities() {
        return findNotaEntities(true, -1, -1);
    }

    public List<Nota> findNotaEntities(int maxResults, int firstResult) {
        return findNotaEntities(false, maxResults, firstResult);
    }

    private List<Nota> findNotaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Nota.class));
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

    public Nota findNota(NotaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Nota.class, id);
        } finally {
            em.close();
        }
    }

    public int getNotaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Nota> rt = cq.from(Nota.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
