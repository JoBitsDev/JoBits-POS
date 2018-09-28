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
import restManager.persistencia.Almacen;
import restManager.persistencia.InsumoFicha;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import restManager.persistencia.Ficha;
import restManager.persistencia.jpa.exceptions.IllegalOrphanException;
import restManager.persistencia.jpa.exceptions.NonexistentEntityException;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class FichaJpaController implements Serializable {

    public FichaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ficha ficha) {
        if (ficha.getInsumoFichaList() == null) {
            ficha.setInsumoFichaList(new ArrayList<InsumoFicha>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Almacen almacencodAlmacen = ficha.getAlmacencodAlmacen();
            if (almacencodAlmacen != null) {
                almacencodAlmacen = em.getReference(almacencodAlmacen.getClass(), almacencodAlmacen.getCodAlmacen());
                ficha.setAlmacencodAlmacen(almacencodAlmacen);
            }
            List<InsumoFicha> attachedInsumoFichaList = new ArrayList<InsumoFicha>();
            for (InsumoFicha insumoFichaListInsumoFichaToAttach : ficha.getInsumoFichaList()) {
                insumoFichaListInsumoFichaToAttach = em.getReference(insumoFichaListInsumoFichaToAttach.getClass(), insumoFichaListInsumoFichaToAttach.getInsumoFichaPK());
                attachedInsumoFichaList.add(insumoFichaListInsumoFichaToAttach);
            }
            ficha.setInsumoFichaList(attachedInsumoFichaList);
            em.persist(ficha);
            if (almacencodAlmacen != null) {
                almacencodAlmacen.getFichaList().add(ficha);
                almacencodAlmacen = em.merge(almacencodAlmacen);
            }
            for (InsumoFicha insumoFichaListInsumoFicha : ficha.getInsumoFichaList()) {
                Ficha oldFichaOfInsumoFichaListInsumoFicha = insumoFichaListInsumoFicha.getFicha();
                insumoFichaListInsumoFicha.setFicha(ficha);
                insumoFichaListInsumoFicha = em.merge(insumoFichaListInsumoFicha);
                if (oldFichaOfInsumoFichaListInsumoFicha != null) {
                    oldFichaOfInsumoFichaListInsumoFicha.getInsumoFichaList().remove(insumoFichaListInsumoFicha);
                    oldFichaOfInsumoFichaListInsumoFicha = em.merge(oldFichaOfInsumoFichaListInsumoFicha);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ficha ficha) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ficha persistentFicha = em.find(Ficha.class, ficha.getIdFicha());
            Almacen almacencodAlmacenOld = persistentFicha.getAlmacencodAlmacen();
            Almacen almacencodAlmacenNew = ficha.getAlmacencodAlmacen();
            List<InsumoFicha> insumoFichaListOld = persistentFicha.getInsumoFichaList();
            List<InsumoFicha> insumoFichaListNew = ficha.getInsumoFichaList();
            List<String> illegalOrphanMessages = null;
            for (InsumoFicha insumoFichaListOldInsumoFicha : insumoFichaListOld) {
                if (!insumoFichaListNew.contains(insumoFichaListOldInsumoFicha)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain InsumoFicha " + insumoFichaListOldInsumoFicha + " since its ficha field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (almacencodAlmacenNew != null) {
                almacencodAlmacenNew = em.getReference(almacencodAlmacenNew.getClass(), almacencodAlmacenNew.getCodAlmacen());
                ficha.setAlmacencodAlmacen(almacencodAlmacenNew);
            }
            List<InsumoFicha> attachedInsumoFichaListNew = new ArrayList<InsumoFicha>();
            for (InsumoFicha insumoFichaListNewInsumoFichaToAttach : insumoFichaListNew) {
                insumoFichaListNewInsumoFichaToAttach = em.getReference(insumoFichaListNewInsumoFichaToAttach.getClass(), insumoFichaListNewInsumoFichaToAttach.getInsumoFichaPK());
                attachedInsumoFichaListNew.add(insumoFichaListNewInsumoFichaToAttach);
            }
            insumoFichaListNew = attachedInsumoFichaListNew;
            ficha.setInsumoFichaList(insumoFichaListNew);
            ficha = em.merge(ficha);
            if (almacencodAlmacenOld != null && !almacencodAlmacenOld.equals(almacencodAlmacenNew)) {
                almacencodAlmacenOld.getFichaList().remove(ficha);
                almacencodAlmacenOld = em.merge(almacencodAlmacenOld);
            }
            if (almacencodAlmacenNew != null && !almacencodAlmacenNew.equals(almacencodAlmacenOld)) {
                almacencodAlmacenNew.getFichaList().add(ficha);
                almacencodAlmacenNew = em.merge(almacencodAlmacenNew);
            }
            for (InsumoFicha insumoFichaListNewInsumoFicha : insumoFichaListNew) {
                if (!insumoFichaListOld.contains(insumoFichaListNewInsumoFicha)) {
                    Ficha oldFichaOfInsumoFichaListNewInsumoFicha = insumoFichaListNewInsumoFicha.getFicha();
                    insumoFichaListNewInsumoFicha.setFicha(ficha);
                    insumoFichaListNewInsumoFicha = em.merge(insumoFichaListNewInsumoFicha);
                    if (oldFichaOfInsumoFichaListNewInsumoFicha != null && !oldFichaOfInsumoFichaListNewInsumoFicha.equals(ficha)) {
                        oldFichaOfInsumoFichaListNewInsumoFicha.getInsumoFichaList().remove(insumoFichaListNewInsumoFicha);
                        oldFichaOfInsumoFichaListNewInsumoFicha = em.merge(oldFichaOfInsumoFichaListNewInsumoFicha);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ficha.getIdFicha();
                if (findFicha(id) == null) {
                    throw new NonexistentEntityException("The ficha with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ficha ficha;
            try {
                ficha = em.getReference(Ficha.class, id);
                ficha.getIdFicha();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ficha with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<InsumoFicha> insumoFichaListOrphanCheck = ficha.getInsumoFichaList();
            for (InsumoFicha insumoFichaListOrphanCheckInsumoFicha : insumoFichaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ficha (" + ficha + ") cannot be destroyed since the InsumoFicha " + insumoFichaListOrphanCheckInsumoFicha + " in its insumoFichaList field has a non-nullable ficha field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Almacen almacencodAlmacen = ficha.getAlmacencodAlmacen();
            if (almacencodAlmacen != null) {
                almacencodAlmacen.getFichaList().remove(ficha);
                almacencodAlmacen = em.merge(almacencodAlmacen);
            }
            em.remove(ficha);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ficha> findFichaEntities() {
        return findFichaEntities(true, -1, -1);
    }

    public List<Ficha> findFichaEntities(int maxResults, int firstResult) {
        return findFichaEntities(false, maxResults, firstResult);
    }

    private List<Ficha> findFichaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ficha.class));
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

    public Ficha findFicha(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ficha.class, id);
        } finally {
            em.close();
        }
    }

    public int getFichaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ficha> rt = cq.from(Ficha.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
