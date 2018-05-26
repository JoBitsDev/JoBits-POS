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
import restManager.persistencia.DatosPersonales;
import restManager.persistencia.jpa.exceptions.IllegalOrphanException;
import restManager.persistencia.jpa.exceptions.NonexistentEntityException;
import restManager.persistencia.jpa.exceptions.PreexistingEntityException;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class DatosPersonalesJpaController implements Serializable {

    public DatosPersonalesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DatosPersonales datosPersonales) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Personal personalOrphanCheck = datosPersonales.getPersonal();
        if (personalOrphanCheck != null) {
            DatosPersonales oldDatosPersonalesOfPersonal = personalOrphanCheck.getDatosPersonales();
            if (oldDatosPersonalesOfPersonal != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Personal " + personalOrphanCheck + " already has an item of type DatosPersonales whose personal column cannot be null. Please make another selection for the personal field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Personal personal = datosPersonales.getPersonal();
            if (personal != null) {
                personal = em.getReference(personal.getClass(), personal.getUsuario());
                datosPersonales.setPersonal(personal);
            }
            em.persist(datosPersonales);
            if (personal != null) {
                personal.setDatosPersonales(datosPersonales);
                personal = em.merge(personal);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDatosPersonales(datosPersonales.getPersonalusuario()) != null) {
                throw new PreexistingEntityException("DatosPersonales " + datosPersonales + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DatosPersonales datosPersonales) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DatosPersonales persistentDatosPersonales = em.find(DatosPersonales.class, datosPersonales.getPersonalusuario());
            Personal personalOld = persistentDatosPersonales.getPersonal();
            Personal personalNew = datosPersonales.getPersonal();
            List<String> illegalOrphanMessages = null;
            if (personalNew != null && !personalNew.equals(personalOld)) {
                DatosPersonales oldDatosPersonalesOfPersonal = personalNew.getDatosPersonales();
                if (oldDatosPersonalesOfPersonal != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Personal " + personalNew + " already has an item of type DatosPersonales whose personal column cannot be null. Please make another selection for the personal field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (personalNew != null) {
                personalNew = em.getReference(personalNew.getClass(), personalNew.getUsuario());
                datosPersonales.setPersonal(personalNew);
            }
            datosPersonales = em.merge(datosPersonales);
            if (personalOld != null && !personalOld.equals(personalNew)) {
                personalOld.setDatosPersonales(null);
                personalOld = em.merge(personalOld);
            }
            if (personalNew != null && !personalNew.equals(personalOld)) {
                personalNew.setDatosPersonales(datosPersonales);
                personalNew = em.merge(personalNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = datosPersonales.getPersonalusuario();
                if (findDatosPersonales(id) == null) {
                    throw new NonexistentEntityException("The datosPersonales with id " + id + " no longer exists.");
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
            DatosPersonales datosPersonales;
            try {
                datosPersonales = em.getReference(DatosPersonales.class, id);
                datosPersonales.getPersonalusuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The datosPersonales with id " + id + " no longer exists.", enfe);
            }
            Personal personal = datosPersonales.getPersonal();
            if (personal != null) {
                personal.setDatosPersonales(null);
                personal = em.merge(personal);
            }
            em.remove(datosPersonales);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DatosPersonales> findDatosPersonalesEntities() {
        return findDatosPersonalesEntities(true, -1, -1);
    }

    public List<DatosPersonales> findDatosPersonalesEntities(int maxResults, int firstResult) {
        return findDatosPersonalesEntities(false, maxResults, firstResult);
    }

    private List<DatosPersonales> findDatosPersonalesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DatosPersonales.class));
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

    public DatosPersonales findDatosPersonales(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DatosPersonales.class, id);
        } finally {
            em.close();
        }
    }

    public int getDatosPersonalesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DatosPersonales> rt = cq.from(DatosPersonales.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
