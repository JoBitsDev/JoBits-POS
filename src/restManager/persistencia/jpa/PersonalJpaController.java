package restManager.persistencia.jpa;

import java.io.Serializable;

import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import restManager.persistencia.DatosPersonales;
import restManager.persistencia.PuestoTrabajo;

import java.util.ArrayList;
import java.util.List;


import restManager.persistencia.Orden;
import restManager.persistencia.Personal;
import restManager.persistencia.jpa.exceptions.IllegalOrphanException;
import restManager.persistencia.jpa.exceptions.NonexistentEntityException;
import restManager.persistencia.jpa.exceptions.PreexistingEntityException;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class PersonalJpaController implements Serializable {

    public PersonalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Personal personal) throws PreexistingEntityException, Exception {
        if (personal.getPuestoTrabajoList() == null) {
            personal.setPuestoTrabajoList(new ArrayList<PuestoTrabajo>());
        }
        if (personal.getOrdenList() == null) {
            personal.setOrdenList(new ArrayList<Orden>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DatosPersonales datosPersonales = personal.getDatosPersonales();
            if (datosPersonales != null) {
                datosPersonales = em.getReference(datosPersonales.getClass(), datosPersonales.getPersonalusuario());
                personal.setDatosPersonales(datosPersonales);
            }
            List<PuestoTrabajo> attachedPuestoTrabajoList = new ArrayList<PuestoTrabajo>();
            for (PuestoTrabajo puestoTrabajoListPuestoTrabajoToAttach : personal.getPuestoTrabajoList()) {
                puestoTrabajoListPuestoTrabajoToAttach = em.getReference(puestoTrabajoListPuestoTrabajoToAttach.getClass(), puestoTrabajoListPuestoTrabajoToAttach.getNombrePuesto());
                attachedPuestoTrabajoList.add(puestoTrabajoListPuestoTrabajoToAttach);
            }
            personal.setPuestoTrabajoList(attachedPuestoTrabajoList);
            List<Orden> attachedOrdenList = new ArrayList<Orden>();
            for (Orden ordenListOrdenToAttach : personal.getOrdenList()) {
                ordenListOrdenToAttach = em.getReference(ordenListOrdenToAttach.getClass(), ordenListOrdenToAttach.getCodOrden());
                attachedOrdenList.add(ordenListOrdenToAttach);
            }
            personal.setOrdenList(attachedOrdenList);
            em.persist(personal);
            if (datosPersonales != null) {
                Personal oldPersonalOfDatosPersonales = datosPersonales.getPersonal();
                if (oldPersonalOfDatosPersonales != null) {
                    oldPersonalOfDatosPersonales.setDatosPersonales(null);
                    oldPersonalOfDatosPersonales = em.merge(oldPersonalOfDatosPersonales);
                }
                datosPersonales.setPersonal(personal);
                datosPersonales = em.merge(datosPersonales);
            }
            for (PuestoTrabajo puestoTrabajoListPuestoTrabajo : personal.getPuestoTrabajoList()) {
                puestoTrabajoListPuestoTrabajo.getPersonalList().add(personal);
                puestoTrabajoListPuestoTrabajo = em.merge(puestoTrabajoListPuestoTrabajo);
            }
            for (Orden ordenListOrden : personal.getOrdenList()) {
                Personal oldPersonalusuarioOfOrdenListOrden = ordenListOrden.getPersonalusuario();
                ordenListOrden.setPersonalusuario(personal);
                ordenListOrden = em.merge(ordenListOrden);
                if (oldPersonalusuarioOfOrdenListOrden != null) {
                    oldPersonalusuarioOfOrdenListOrden.getOrdenList().remove(ordenListOrden);
                    oldPersonalusuarioOfOrdenListOrden = em.merge(oldPersonalusuarioOfOrdenListOrden);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPersonal(personal.getUsuario()) != null) {
                throw new PreexistingEntityException("Personal " + personal + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Personal personal) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Personal persistentPersonal = em.find(Personal.class, personal.getUsuario());
            DatosPersonales datosPersonalesOld = persistentPersonal.getDatosPersonales();
            DatosPersonales datosPersonalesNew = personal.getDatosPersonales();
            List<PuestoTrabajo> puestoTrabajoListOld = persistentPersonal.getPuestoTrabajoList();
            List<PuestoTrabajo> puestoTrabajoListNew = personal.getPuestoTrabajoList();
            List<Orden> ordenListOld = persistentPersonal.getOrdenList();
            List<Orden> ordenListNew = personal.getOrdenList();
            List<String> illegalOrphanMessages = null;
            if (datosPersonalesOld != null && !datosPersonalesOld.equals(datosPersonalesNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain DatosPersonales " + datosPersonalesOld + " since its personal field is not nullable.");
            }
            for (Orden ordenListOldOrden : ordenListOld) {
                if (!ordenListNew.contains(ordenListOldOrden)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Orden " + ordenListOldOrden + " since its personalusuario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (datosPersonalesNew != null) {
                datosPersonalesNew = em.getReference(datosPersonalesNew.getClass(), datosPersonalesNew.getPersonalusuario());
                personal.setDatosPersonales(datosPersonalesNew);
            }
            List<PuestoTrabajo> attachedPuestoTrabajoListNew = new ArrayList<PuestoTrabajo>();
            for (PuestoTrabajo puestoTrabajoListNewPuestoTrabajoToAttach : puestoTrabajoListNew) {
                puestoTrabajoListNewPuestoTrabajoToAttach = em.getReference(puestoTrabajoListNewPuestoTrabajoToAttach.getClass(), puestoTrabajoListNewPuestoTrabajoToAttach.getNombrePuesto());
                attachedPuestoTrabajoListNew.add(puestoTrabajoListNewPuestoTrabajoToAttach);
            }
            puestoTrabajoListNew = attachedPuestoTrabajoListNew;
            personal.setPuestoTrabajoList(puestoTrabajoListNew);
            List<Orden> attachedOrdenListNew = new ArrayList<Orden>();
            for (Orden ordenListNewOrdenToAttach : ordenListNew) {
                ordenListNewOrdenToAttach = em.getReference(ordenListNewOrdenToAttach.getClass(), ordenListNewOrdenToAttach.getCodOrden());
                attachedOrdenListNew.add(ordenListNewOrdenToAttach);
            }
            ordenListNew = attachedOrdenListNew;
            personal.setOrdenList(ordenListNew);
            personal = em.merge(personal);
            if (datosPersonalesNew != null && !datosPersonalesNew.equals(datosPersonalesOld)) {
                Personal oldPersonalOfDatosPersonales = datosPersonalesNew.getPersonal();
                if (oldPersonalOfDatosPersonales != null) {
                    oldPersonalOfDatosPersonales.setDatosPersonales(null);
                    oldPersonalOfDatosPersonales = em.merge(oldPersonalOfDatosPersonales);
                }
                datosPersonalesNew.setPersonal(personal);
                datosPersonalesNew = em.merge(datosPersonalesNew);
            }
            for (PuestoTrabajo puestoTrabajoListOldPuestoTrabajo : puestoTrabajoListOld) {
                if (!puestoTrabajoListNew.contains(puestoTrabajoListOldPuestoTrabajo)) {
                    puestoTrabajoListOldPuestoTrabajo.getPersonalList().remove(personal);
                    puestoTrabajoListOldPuestoTrabajo = em.merge(puestoTrabajoListOldPuestoTrabajo);
                }
            }
            for (PuestoTrabajo puestoTrabajoListNewPuestoTrabajo : puestoTrabajoListNew) {
                if (!puestoTrabajoListOld.contains(puestoTrabajoListNewPuestoTrabajo)) {
                    puestoTrabajoListNewPuestoTrabajo.getPersonalList().add(personal);
                    puestoTrabajoListNewPuestoTrabajo = em.merge(puestoTrabajoListNewPuestoTrabajo);
                }
            }
            for (Orden ordenListNewOrden : ordenListNew) {
                if (!ordenListOld.contains(ordenListNewOrden)) {
                    Personal oldPersonalusuarioOfOrdenListNewOrden = ordenListNewOrden.getPersonalusuario();
                    ordenListNewOrden.setPersonalusuario(personal);
                    ordenListNewOrden = em.merge(ordenListNewOrden);
                    if (oldPersonalusuarioOfOrdenListNewOrden != null && !oldPersonalusuarioOfOrdenListNewOrden.equals(personal)) {
                        oldPersonalusuarioOfOrdenListNewOrden.getOrdenList().remove(ordenListNewOrden);
                        oldPersonalusuarioOfOrdenListNewOrden = em.merge(oldPersonalusuarioOfOrdenListNewOrden);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = personal.getUsuario();
                if (findPersonal(id) == null) {
                    throw new NonexistentEntityException("The personal with id " + id + " no longer exists.");
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
            Personal personal;
            try {
                personal = em.getReference(Personal.class, id);
                personal.getUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The personal with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            DatosPersonales datosPersonalesOrphanCheck = personal.getDatosPersonales();
            if (datosPersonalesOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Personal (" + personal + ") cannot be destroyed since the DatosPersonales " + datosPersonalesOrphanCheck + " in its datosPersonales field has a non-nullable personal field.");
            }
            List<Orden> ordenListOrphanCheck = personal.getOrdenList();
            for (Orden ordenListOrphanCheckOrden : ordenListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Personal (" + personal + ") cannot be destroyed since the Orden " + ordenListOrphanCheckOrden + " in its ordenList field has a non-nullable personalusuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<PuestoTrabajo> puestoTrabajoList = personal.getPuestoTrabajoList();
            for (PuestoTrabajo puestoTrabajoListPuestoTrabajo : puestoTrabajoList) {
                puestoTrabajoListPuestoTrabajo.getPersonalList().remove(personal);
                puestoTrabajoListPuestoTrabajo = em.merge(puestoTrabajoListPuestoTrabajo);
            }
            em.remove(personal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Personal> findPersonalEntities() {
        return findPersonalEntities(true, -1, -1);
    }

    public List<Personal> findPersonalEntities(int maxResults, int firstResult) {
        return findPersonalEntities(false, maxResults, firstResult);
    }

    private List<Personal> findPersonalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Personal.class));
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

    public Personal findPersonal(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Personal.class, id);
        } finally {
            em.close();
        }
    }

    public int getPersonalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Personal> rt = cq.from(Personal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
