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
import restManager.persistencia.Personal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import restManager.persistencia.PuestoTrabajo;
import restManager.persistencia.jpa.exceptions.NonexistentEntityException;
import restManager.persistencia.jpa.exceptions.PreexistingEntityException;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class PuestoTrabajoJpaController implements Serializable {

    public PuestoTrabajoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PuestoTrabajo puestoTrabajo) throws PreexistingEntityException, Exception {
        if (puestoTrabajo.getPersonalList() == null) {
            puestoTrabajo.setPersonalList(new ArrayList<Personal>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Personal> attachedPersonalList = new ArrayList<Personal>();
            for (Personal personalListPersonalToAttach : puestoTrabajo.getPersonalList()) {
                personalListPersonalToAttach = em.getReference(personalListPersonalToAttach.getClass(), personalListPersonalToAttach.getUsuario());
                attachedPersonalList.add(personalListPersonalToAttach);
            }
            puestoTrabajo.setPersonalList(attachedPersonalList);
            em.persist(puestoTrabajo);
            for (Personal personalListPersonal : puestoTrabajo.getPersonalList()) {
                personalListPersonal.getPuestoTrabajoList().add(puestoTrabajo);
                personalListPersonal = em.merge(personalListPersonal);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPuestoTrabajo(puestoTrabajo.getNombrePuesto()) != null) {
                throw new PreexistingEntityException("PuestoTrabajo " + puestoTrabajo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PuestoTrabajo puestoTrabajo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PuestoTrabajo persistentPuestoTrabajo = em.find(PuestoTrabajo.class, puestoTrabajo.getNombrePuesto());
            List<Personal> personalListOld = persistentPuestoTrabajo.getPersonalList();
            List<Personal> personalListNew = puestoTrabajo.getPersonalList();
            List<Personal> attachedPersonalListNew = new ArrayList<Personal>();
            for (Personal personalListNewPersonalToAttach : personalListNew) {
                personalListNewPersonalToAttach = em.getReference(personalListNewPersonalToAttach.getClass(), personalListNewPersonalToAttach.getUsuario());
                attachedPersonalListNew.add(personalListNewPersonalToAttach);
            }
            personalListNew = attachedPersonalListNew;
            puestoTrabajo.setPersonalList(personalListNew);
            puestoTrabajo = em.merge(puestoTrabajo);
            for (Personal personalListOldPersonal : personalListOld) {
                if (!personalListNew.contains(personalListOldPersonal)) {
                    personalListOldPersonal.getPuestoTrabajoList().remove(puestoTrabajo);
                    personalListOldPersonal = em.merge(personalListOldPersonal);
                }
            }
            for (Personal personalListNewPersonal : personalListNew) {
                if (!personalListOld.contains(personalListNewPersonal)) {
                    personalListNewPersonal.getPuestoTrabajoList().add(puestoTrabajo);
                    personalListNewPersonal = em.merge(personalListNewPersonal);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = puestoTrabajo.getNombrePuesto();
                if (findPuestoTrabajo(id) == null) {
                    throw new NonexistentEntityException("The puestoTrabajo with id " + id + " no longer exists.");
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
            PuestoTrabajo puestoTrabajo;
            try {
                puestoTrabajo = em.getReference(PuestoTrabajo.class, id);
                puestoTrabajo.getNombrePuesto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The puestoTrabajo with id " + id + " no longer exists.", enfe);
            }
            List<Personal> personalList = puestoTrabajo.getPersonalList();
            for (Personal personalListPersonal : personalList) {
                personalListPersonal.getPuestoTrabajoList().remove(puestoTrabajo);
                personalListPersonal = em.merge(personalListPersonal);
            }
            em.remove(puestoTrabajo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PuestoTrabajo> findPuestoTrabajoEntities() {
        return findPuestoTrabajoEntities(true, -1, -1);
    }

    public List<PuestoTrabajo> findPuestoTrabajoEntities(int maxResults, int firstResult) {
        return findPuestoTrabajoEntities(false, maxResults, firstResult);
    }

    private List<PuestoTrabajo> findPuestoTrabajoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PuestoTrabajo.class));
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

    public PuestoTrabajo findPuestoTrabajo(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PuestoTrabajo.class, id);
        } finally {
            em.close();
        }
    }

    public int getPuestoTrabajoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PuestoTrabajo> rt = cq.from(PuestoTrabajo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
