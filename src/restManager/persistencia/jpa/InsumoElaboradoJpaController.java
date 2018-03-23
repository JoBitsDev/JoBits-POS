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
import restManager.persistencia.InsumoElaborado;
import restManager.persistencia.InsumoElaboradoPK;
import restManager.persistencia.jpa.exceptions.NonexistentEntityException;
import restManager.persistencia.jpa.exceptions.PreexistingEntityException;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class InsumoElaboradoJpaController implements Serializable {

    public InsumoElaboradoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InsumoElaborado insumoElaborado) throws PreexistingEntityException, Exception {
        if (insumoElaborado.getInsumoElaboradoPK() == null) {
            insumoElaborado.setInsumoElaboradoPK(new InsumoElaboradoPK());
        }
        insumoElaborado.getInsumoElaboradoPK().setInsumocodNombre(insumoElaborado.getInsumo().getCodInsumo());
        insumoElaborado.getInsumoElaboradoPK().setInsumocodInsumo(insumoElaborado.getInsumo1().getCodInsumo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Insumo insumo = insumoElaborado.getInsumo();
            if (insumo != null) {
                insumo = em.getReference(insumo.getClass(), insumo.getCodInsumo());
                insumoElaborado.setInsumo(insumo);
            }
            Insumo insumo1 = insumoElaborado.getInsumo1();
            if (insumo1 != null) {
                insumo1 = em.getReference(insumo1.getClass(), insumo1.getCodInsumo());
                insumoElaborado.setInsumo1(insumo1);
            }
            em.persist(insumoElaborado);
            if (insumo != null) {
                insumo.getInsumoElaboradoList().add(insumoElaborado);
                insumo = em.merge(insumo);
            }
            if (insumo1 != null) {
                insumo1.getInsumoElaboradoList().add(insumoElaborado);
                insumo1 = em.merge(insumo1);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findInsumoElaborado(insumoElaborado.getInsumoElaboradoPK()) != null) {
                throw new PreexistingEntityException("InsumoElaborado " + insumoElaborado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InsumoElaborado insumoElaborado) throws NonexistentEntityException, Exception {
        insumoElaborado.getInsumoElaboradoPK().setInsumocodNombre(insumoElaborado.getInsumo().getCodInsumo());
        insumoElaborado.getInsumoElaboradoPK().setInsumocodInsumo(insumoElaborado.getInsumo1().getCodInsumo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InsumoElaborado persistentInsumoElaborado = em.find(InsumoElaborado.class, insumoElaborado.getInsumoElaboradoPK());
            Insumo insumoOld = persistentInsumoElaborado.getInsumo();
            Insumo insumoNew = insumoElaborado.getInsumo();
            Insumo insumo1Old = persistentInsumoElaborado.getInsumo1();
            Insumo insumo1New = insumoElaborado.getInsumo1();
            if (insumoNew != null) {
                insumoNew = em.getReference(insumoNew.getClass(), insumoNew.getCodInsumo());
                insumoElaborado.setInsumo(insumoNew);
            }
            if (insumo1New != null) {
                insumo1New = em.getReference(insumo1New.getClass(), insumo1New.getCodInsumo());
                insumoElaborado.setInsumo1(insumo1New);
            }
            insumoElaborado = em.merge(insumoElaborado);
            if (insumoOld != null && !insumoOld.equals(insumoNew)) {
                insumoOld.getInsumoElaboradoList().remove(insumoElaborado);
                insumoOld = em.merge(insumoOld);
            }
            if (insumoNew != null && !insumoNew.equals(insumoOld)) {
                insumoNew.getInsumoElaboradoList().add(insumoElaborado);
                insumoNew = em.merge(insumoNew);
            }
            if (insumo1Old != null && !insumo1Old.equals(insumo1New)) {
                insumo1Old.getInsumoElaboradoList().remove(insumoElaborado);
                insumo1Old = em.merge(insumo1Old);
            }
            if (insumo1New != null && !insumo1New.equals(insumo1Old)) {
                insumo1New.getInsumoElaboradoList().add(insumoElaborado);
                insumo1New = em.merge(insumo1New);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                InsumoElaboradoPK id = insumoElaborado.getInsumoElaboradoPK();
                if (findInsumoElaborado(id) == null) {
                    throw new NonexistentEntityException("The insumoElaborado with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(InsumoElaboradoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InsumoElaborado insumoElaborado;
            try {
                insumoElaborado = em.getReference(InsumoElaborado.class, id);
                insumoElaborado.getInsumoElaboradoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The insumoElaborado with id " + id + " no longer exists.", enfe);
            }
            Insumo insumo = insumoElaborado.getInsumo();
            if (insumo != null) {
                insumo.getInsumoElaboradoList().remove(insumoElaborado);
                insumo = em.merge(insumo);
            }
            Insumo insumo1 = insumoElaborado.getInsumo1();
            if (insumo1 != null) {
                insumo1.getInsumoElaboradoList().remove(insumoElaborado);
                insumo1 = em.merge(insumo1);
            }
            em.remove(insumoElaborado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InsumoElaborado> findInsumoElaboradoEntities() {
        return findInsumoElaboradoEntities(true, -1, -1);
    }

    public List<InsumoElaborado> findInsumoElaboradoEntities(int maxResults, int firstResult) {
        return findInsumoElaboradoEntities(false, maxResults, firstResult);
    }

    private List<InsumoElaborado> findInsumoElaboradoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InsumoElaborado.class));
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

    public InsumoElaborado findInsumoElaborado(InsumoElaboradoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InsumoElaborado.class, id);
        } finally {
            em.close();
        }
    }

    public int getInsumoElaboradoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InsumoElaborado> rt = cq.from(InsumoElaborado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
