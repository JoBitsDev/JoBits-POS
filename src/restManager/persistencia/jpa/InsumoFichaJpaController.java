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
import restManager.persistencia.Ficha;
import restManager.persistencia.Insumo;
import restManager.persistencia.InsumoFicha;
import restManager.persistencia.InsumoFichaPK;
import restManager.persistencia.jpa.exceptions.NonexistentEntityException;
import restManager.persistencia.jpa.exceptions.PreexistingEntityException;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class InsumoFichaJpaController implements Serializable {

    public InsumoFichaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InsumoFicha insumoFicha) throws PreexistingEntityException, Exception {
        if (insumoFicha.getInsumoFichaPK() == null) {
            insumoFicha.setInsumoFichaPK(new InsumoFichaPK());
        }
        insumoFicha.getInsumoFichaPK().setFichaidFicha(insumoFicha.getFicha().getIdFicha());
        insumoFicha.getInsumoFichaPK().setInsumocodInsumo(insumoFicha.getInsumo().getCodInsumo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ficha ficha = insumoFicha.getFicha();
            if (ficha != null) {
                ficha = em.getReference(ficha.getClass(), ficha.getIdFicha());
                insumoFicha.setFicha(ficha);
            }
            Insumo insumo = insumoFicha.getInsumo();
            if (insumo != null) {
                insumo = em.getReference(insumo.getClass(), insumo.getCodInsumo());
                insumoFicha.setInsumo(insumo);
            }
            em.persist(insumoFicha);
            if (ficha != null) {
                ficha.getInsumoFichaList().add(insumoFicha);
                ficha = em.merge(ficha);
            }
            if (insumo != null) {
                insumo.getInsumoFichaList().add(insumoFicha);
                insumo = em.merge(insumo);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findInsumoFicha(insumoFicha.getInsumoFichaPK()) != null) {
                throw new PreexistingEntityException("InsumoFicha " + insumoFicha + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InsumoFicha insumoFicha) throws NonexistentEntityException, Exception {
        insumoFicha.getInsumoFichaPK().setFichaidFicha(insumoFicha.getFicha().getIdFicha());
        insumoFicha.getInsumoFichaPK().setInsumocodInsumo(insumoFicha.getInsumo().getCodInsumo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InsumoFicha persistentInsumoFicha = em.find(InsumoFicha.class, insumoFicha.getInsumoFichaPK());
            Ficha fichaOld = persistentInsumoFicha.getFicha();
            Ficha fichaNew = insumoFicha.getFicha();
            Insumo insumoOld = persistentInsumoFicha.getInsumo();
            Insumo insumoNew = insumoFicha.getInsumo();
            if (fichaNew != null) {
                fichaNew = em.getReference(fichaNew.getClass(), fichaNew.getIdFicha());
                insumoFicha.setFicha(fichaNew);
            }
            if (insumoNew != null) {
                insumoNew = em.getReference(insumoNew.getClass(), insumoNew.getCodInsumo());
                insumoFicha.setInsumo(insumoNew);
            }
            insumoFicha = em.merge(insumoFicha);
            if (fichaOld != null && !fichaOld.equals(fichaNew)) {
                fichaOld.getInsumoFichaList().remove(insumoFicha);
                fichaOld = em.merge(fichaOld);
            }
            if (fichaNew != null && !fichaNew.equals(fichaOld)) {
                fichaNew.getInsumoFichaList().add(insumoFicha);
                fichaNew = em.merge(fichaNew);
            }
            if (insumoOld != null && !insumoOld.equals(insumoNew)) {
                insumoOld.getInsumoFichaList().remove(insumoFicha);
                insumoOld = em.merge(insumoOld);
            }
            if (insumoNew != null && !insumoNew.equals(insumoOld)) {
                insumoNew.getInsumoFichaList().add(insumoFicha);
                insumoNew = em.merge(insumoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                InsumoFichaPK id = insumoFicha.getInsumoFichaPK();
                if (findInsumoFicha(id) == null) {
                    throw new NonexistentEntityException("The insumoFicha with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(InsumoFichaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InsumoFicha insumoFicha;
            try {
                insumoFicha = em.getReference(InsumoFicha.class, id);
                insumoFicha.getInsumoFichaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The insumoFicha with id " + id + " no longer exists.", enfe);
            }
            Ficha ficha = insumoFicha.getFicha();
            if (ficha != null) {
                ficha.getInsumoFichaList().remove(insumoFicha);
                ficha = em.merge(ficha);
            }
            Insumo insumo = insumoFicha.getInsumo();
            if (insumo != null) {
                insumo.getInsumoFichaList().remove(insumoFicha);
                insumo = em.merge(insumo);
            }
            em.remove(insumoFicha);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InsumoFicha> findInsumoFichaEntities() {
        return findInsumoFichaEntities(true, -1, -1);
    }

    public List<InsumoFicha> findInsumoFichaEntities(int maxResults, int firstResult) {
        return findInsumoFichaEntities(false, maxResults, firstResult);
    }

    private List<InsumoFicha> findInsumoFichaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InsumoFicha.class));
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

    public InsumoFicha findInsumoFicha(InsumoFichaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InsumoFicha.class, id);
        } finally {
            em.close();
        }
    }

    public int getInsumoFichaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InsumoFicha> rt = cq.from(InsumoFicha.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
