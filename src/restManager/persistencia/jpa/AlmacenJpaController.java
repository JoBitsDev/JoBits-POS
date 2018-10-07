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
import restManager.persistencia.Insumo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import restManager.persistencia.Almacen;
import restManager.persistencia.Transaccion;
import restManager.persistencia.jpa.exceptions.NonexistentEntityException;
import restManager.persistencia.jpa.exceptions.PreexistingEntityException;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class AlmacenJpaController implements Serializable {

    public AlmacenJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Almacen almacen) throws PreexistingEntityException, Exception {
        if (almacen.getInsumoList() == null) {
            almacen.setInsumoList(new ArrayList<Insumo>());
        }
        if (almacen.getTransaccionList() == null) {
            almacen.setTransaccionList(new ArrayList<Transaccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Insumo> attachedInsumoList = new ArrayList<Insumo>();
            for (Insumo insumoListInsumoToAttach : almacen.getInsumoList()) {
                insumoListInsumoToAttach = em.getReference(insumoListInsumoToAttach.getClass(), insumoListInsumoToAttach.getCodInsumo());
                attachedInsumoList.add(insumoListInsumoToAttach);
            }
            almacen.setInsumoList(attachedInsumoList);
            List<Transaccion> attachedTransaccionList = new ArrayList<Transaccion>();
            for (Transaccion transaccionListTransaccionToAttach : almacen.getTransaccionList()) {
                transaccionListTransaccionToAttach = em.getReference(transaccionListTransaccionToAttach.getClass(), transaccionListTransaccionToAttach.getFechaTransaccion());
                attachedTransaccionList.add(transaccionListTransaccionToAttach);
            }
            almacen.setTransaccionList(attachedTransaccionList);
            em.persist(almacen);
            for (Insumo insumoListInsumo : almacen.getInsumoList()) {
                Almacen oldAlmacencodAlmacenOfInsumoListInsumo = insumoListInsumo.getAlmacencodAlmacen();
                insumoListInsumo.setAlmacencodAlmacen(almacen);
                insumoListInsumo = em.merge(insumoListInsumo);
                if (oldAlmacencodAlmacenOfInsumoListInsumo != null) {
                    oldAlmacencodAlmacenOfInsumoListInsumo.getInsumoList().remove(insumoListInsumo);
                    oldAlmacencodAlmacenOfInsumoListInsumo = em.merge(oldAlmacencodAlmacenOfInsumoListInsumo);
                }
            }
            for (Transaccion transaccionListTransaccion : almacen.getTransaccionList()) {
                Almacen oldAlmacencodAlmacenOfTransaccionListTransaccion = transaccionListTransaccion.getAlmacencodAlmacen();
                transaccionListTransaccion.setAlmacencodAlmacen(almacen);
                transaccionListTransaccion = em.merge(transaccionListTransaccion);
                if (oldAlmacencodAlmacenOfTransaccionListTransaccion != null) {
                    oldAlmacencodAlmacenOfTransaccionListTransaccion.getTransaccionList().remove(transaccionListTransaccion);
                    oldAlmacencodAlmacenOfTransaccionListTransaccion = em.merge(oldAlmacencodAlmacenOfTransaccionListTransaccion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAlmacen(almacen.getCodAlmacen()) != null) {
                throw new PreexistingEntityException("Almacen " + almacen + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Almacen almacen) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Almacen persistentAlmacen = em.find(Almacen.class, almacen.getCodAlmacen());
            List<Insumo> insumoListOld = persistentAlmacen.getInsumoList();
            List<Insumo> insumoListNew = almacen.getInsumoList();
            List<Transaccion> transaccionListOld = persistentAlmacen.getTransaccionList();
            List<Transaccion> transaccionListNew = almacen.getTransaccionList();
            List<Insumo> attachedInsumoListNew = new ArrayList<Insumo>();
            for (Insumo insumoListNewInsumoToAttach : insumoListNew) {
                insumoListNewInsumoToAttach = em.getReference(insumoListNewInsumoToAttach.getClass(), insumoListNewInsumoToAttach.getCodInsumo());
                attachedInsumoListNew.add(insumoListNewInsumoToAttach);
            }
            insumoListNew = attachedInsumoListNew;
            almacen.setInsumoList(insumoListNew);
            List<Transaccion> attachedTransaccionListNew = new ArrayList<Transaccion>();
            for (Transaccion transaccionListNewTransaccionToAttach : transaccionListNew) {
                transaccionListNewTransaccionToAttach = em.getReference(transaccionListNewTransaccionToAttach.getClass(), transaccionListNewTransaccionToAttach.getFechaTransaccion());
                attachedTransaccionListNew.add(transaccionListNewTransaccionToAttach);
            }
            transaccionListNew = attachedTransaccionListNew;
            almacen.setTransaccionList(transaccionListNew);
            almacen = em.merge(almacen);
            for (Insumo insumoListOldInsumo : insumoListOld) {
                if (!insumoListNew.contains(insumoListOldInsumo)) {
                    insumoListOldInsumo.setAlmacencodAlmacen(null);
                    insumoListOldInsumo = em.merge(insumoListOldInsumo);
                }
            }
            for (Insumo insumoListNewInsumo : insumoListNew) {
                if (!insumoListOld.contains(insumoListNewInsumo)) {
                    Almacen oldAlmacencodAlmacenOfInsumoListNewInsumo = insumoListNewInsumo.getAlmacencodAlmacen();
                    insumoListNewInsumo.setAlmacencodAlmacen(almacen);
                    insumoListNewInsumo = em.merge(insumoListNewInsumo);
                    if (oldAlmacencodAlmacenOfInsumoListNewInsumo != null && !oldAlmacencodAlmacenOfInsumoListNewInsumo.equals(almacen)) {
                        oldAlmacencodAlmacenOfInsumoListNewInsumo.getInsumoList().remove(insumoListNewInsumo);
                        oldAlmacencodAlmacenOfInsumoListNewInsumo = em.merge(oldAlmacencodAlmacenOfInsumoListNewInsumo);
                    }
                }
            }
            for (Transaccion transaccionListOldTransaccion : transaccionListOld) {
                if (!transaccionListNew.contains(transaccionListOldTransaccion)) {
                    transaccionListOldTransaccion.setAlmacencodAlmacen(null);
                    transaccionListOldTransaccion = em.merge(transaccionListOldTransaccion);
                }
            }
            for (Transaccion transaccionListNewTransaccion : transaccionListNew) {
                if (!transaccionListOld.contains(transaccionListNewTransaccion)) {
                    Almacen oldAlmacencodAlmacenOfTransaccionListNewTransaccion = transaccionListNewTransaccion.getAlmacencodAlmacen();
                    transaccionListNewTransaccion.setAlmacencodAlmacen(almacen);
                    transaccionListNewTransaccion = em.merge(transaccionListNewTransaccion);
                    if (oldAlmacencodAlmacenOfTransaccionListNewTransaccion != null && !oldAlmacencodAlmacenOfTransaccionListNewTransaccion.equals(almacen)) {
                        oldAlmacencodAlmacenOfTransaccionListNewTransaccion.getTransaccionList().remove(transaccionListNewTransaccion);
                        oldAlmacencodAlmacenOfTransaccionListNewTransaccion = em.merge(oldAlmacencodAlmacenOfTransaccionListNewTransaccion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = almacen.getCodAlmacen();
                if (findAlmacen(id) == null) {
                    throw new NonexistentEntityException("The almacen with id " + id + " no longer exists.");
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
            Almacen almacen;
            try {
                almacen = em.getReference(Almacen.class, id);
                almacen.getCodAlmacen();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The almacen with id " + id + " no longer exists.", enfe);
            }
            List<Insumo> insumoList = almacen.getInsumoList();
            for (Insumo insumoListInsumo : insumoList) {
                insumoListInsumo.setAlmacencodAlmacen(null);
                insumoListInsumo = em.merge(insumoListInsumo);
            }
            List<Transaccion> transaccionList = almacen.getTransaccionList();
            for (Transaccion transaccionListTransaccion : transaccionList) {
                transaccionListTransaccion.setAlmacencodAlmacen(null);
                transaccionListTransaccion = em.merge(transaccionListTransaccion);
            }
            em.remove(almacen);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Almacen> findAlmacenEntities() {
        return findAlmacenEntities(true, -1, -1);
    }

    public List<Almacen> findAlmacenEntities(int maxResults, int firstResult) {
        return findAlmacenEntities(false, maxResults, firstResult);
    }

    private List<Almacen> findAlmacenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Almacen.class));
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

    public Almacen findAlmacen(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Almacen.class, id);
        } finally {
            em.close();
        }
    }

    public int getAlmacenCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Almacen> rt = cq.from(Almacen.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
