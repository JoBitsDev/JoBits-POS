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
import restManager.persistencia.ProductoInsumo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import restManager.persistencia.Insumo;
import restManager.persistencia.Ipv;
import restManager.persistencia.InsumoElaborado;
import restManager.persistencia.jpa.exceptions.IllegalOrphanException;
import restManager.persistencia.jpa.exceptions.NonexistentEntityException;
import restManager.persistencia.jpa.exceptions.PreexistingEntityException;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class InsumoJpaController implements Serializable {

    public InsumoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Insumo insumo) throws PreexistingEntityException, Exception {
        if (insumo.getProductoInsumoList() == null) {
            insumo.setProductoInsumoList(new ArrayList<ProductoInsumo>());
        }
        if (insumo.getIpvList() == null) {
            insumo.setIpvList(new ArrayList<Ipv>());
        }
        if (insumo.getInsumoElaboradoList() == null) {
            insumo.setInsumoElaboradoList(new ArrayList<InsumoElaborado>());
        }
        if (insumo.getInsumoElaboradoList1() == null) {
            insumo.setInsumoElaboradoList1(new ArrayList<InsumoElaborado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Almacen almacencodAlmacen = insumo.getAlmacencodAlmacen();
            if (almacencodAlmacen != null) {
                almacencodAlmacen = em.getReference(almacencodAlmacen.getClass(), almacencodAlmacen.getCodAlmacen());
                insumo.setAlmacencodAlmacen(almacencodAlmacen);
            }
            List<ProductoInsumo> attachedProductoInsumoList = new ArrayList<ProductoInsumo>();
            for (ProductoInsumo productoInsumoListProductoInsumoToAttach : insumo.getProductoInsumoList()) {
                productoInsumoListProductoInsumoToAttach = em.getReference(productoInsumoListProductoInsumoToAttach.getClass(), productoInsumoListProductoInsumoToAttach.getProductoInsumoPK());
                attachedProductoInsumoList.add(productoInsumoListProductoInsumoToAttach);
            }
            insumo.setProductoInsumoList(attachedProductoInsumoList);
            List<Ipv> attachedIpvList = new ArrayList<Ipv>();
            for (Ipv ipvListIpvToAttach : insumo.getIpvList()) {
                ipvListIpvToAttach = em.getReference(ipvListIpvToAttach.getClass(), ipvListIpvToAttach.getIpvPK());
                attachedIpvList.add(ipvListIpvToAttach);
            }
            insumo.setIpvList(attachedIpvList);
            List<InsumoElaborado> attachedInsumoElaboradoList = new ArrayList<InsumoElaborado>();
            for (InsumoElaborado insumoElaboradoListInsumoElaboradoToAttach : insumo.getInsumoElaboradoList()) {
                insumoElaboradoListInsumoElaboradoToAttach = em.getReference(insumoElaboradoListInsumoElaboradoToAttach.getClass(), insumoElaboradoListInsumoElaboradoToAttach.getInsumoElaboradoPK());
                attachedInsumoElaboradoList.add(insumoElaboradoListInsumoElaboradoToAttach);
            }
            insumo.setInsumoElaboradoList(attachedInsumoElaboradoList);
            List<InsumoElaborado> attachedInsumoElaboradoList1 = new ArrayList<InsumoElaborado>();
            for (InsumoElaborado insumoElaboradoList1InsumoElaboradoToAttach : insumo.getInsumoElaboradoList1()) {
                insumoElaboradoList1InsumoElaboradoToAttach = em.getReference(insumoElaboradoList1InsumoElaboradoToAttach.getClass(), insumoElaboradoList1InsumoElaboradoToAttach.getInsumoElaboradoPK());
                attachedInsumoElaboradoList1.add(insumoElaboradoList1InsumoElaboradoToAttach);
            }
            insumo.setInsumoElaboradoList1(attachedInsumoElaboradoList1);
            em.persist(insumo);
            if (almacencodAlmacen != null) {
                almacencodAlmacen.getInsumoList().add(insumo);
                almacencodAlmacen = em.merge(almacencodAlmacen);
            }
            for (ProductoInsumo productoInsumoListProductoInsumo : insumo.getProductoInsumoList()) {
                Insumo oldInsumoOfProductoInsumoListProductoInsumo = productoInsumoListProductoInsumo.getInsumo();
                productoInsumoListProductoInsumo.setInsumo(insumo);
                productoInsumoListProductoInsumo = em.merge(productoInsumoListProductoInsumo);
                if (oldInsumoOfProductoInsumoListProductoInsumo != null) {
                    oldInsumoOfProductoInsumoListProductoInsumo.getProductoInsumoList().remove(productoInsumoListProductoInsumo);
                    oldInsumoOfProductoInsumoListProductoInsumo = em.merge(oldInsumoOfProductoInsumoListProductoInsumo);
                }
            }
            for (Ipv ipvListIpv : insumo.getIpvList()) {
                Insumo oldInsumoOfIpvListIpv = ipvListIpv.getInsumo();
                ipvListIpv.setInsumo(insumo);
                ipvListIpv = em.merge(ipvListIpv);
                if (oldInsumoOfIpvListIpv != null) {
                    oldInsumoOfIpvListIpv.getIpvList().remove(ipvListIpv);
                    oldInsumoOfIpvListIpv = em.merge(oldInsumoOfIpvListIpv);
                }
            }
            for (InsumoElaborado insumoElaboradoListInsumoElaborado : insumo.getInsumoElaboradoList()) {
                Insumo oldInsumoOfInsumoElaboradoListInsumoElaborado = insumoElaboradoListInsumoElaborado.getInsumo();
                insumoElaboradoListInsumoElaborado.setInsumo(insumo);
                insumoElaboradoListInsumoElaborado = em.merge(insumoElaboradoListInsumoElaborado);
                if (oldInsumoOfInsumoElaboradoListInsumoElaborado != null) {
                    oldInsumoOfInsumoElaboradoListInsumoElaborado.getInsumoElaboradoList().remove(insumoElaboradoListInsumoElaborado);
                    oldInsumoOfInsumoElaboradoListInsumoElaborado = em.merge(oldInsumoOfInsumoElaboradoListInsumoElaborado);
                }
            }
            for (InsumoElaborado insumoElaboradoList1InsumoElaborado : insumo.getInsumoElaboradoList1()) {
                Insumo oldInsumo1OfInsumoElaboradoList1InsumoElaborado = insumoElaboradoList1InsumoElaborado.getInsumo1();
                insumoElaboradoList1InsumoElaborado.setInsumo1(insumo);
                insumoElaboradoList1InsumoElaborado = em.merge(insumoElaboradoList1InsumoElaborado);
                if (oldInsumo1OfInsumoElaboradoList1InsumoElaborado != null) {
                    oldInsumo1OfInsumoElaboradoList1InsumoElaborado.getInsumoElaboradoList1().remove(insumoElaboradoList1InsumoElaborado);
                    oldInsumo1OfInsumoElaboradoList1InsumoElaborado = em.merge(oldInsumo1OfInsumoElaboradoList1InsumoElaborado);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findInsumo(insumo.getCodInsumo()) != null) {
                throw new PreexistingEntityException("Insumo " + insumo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Insumo insumo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Insumo persistentInsumo = em.find(Insumo.class, insumo.getCodInsumo());
            Almacen almacencodAlmacenOld = persistentInsumo.getAlmacencodAlmacen();
            Almacen almacencodAlmacenNew = insumo.getAlmacencodAlmacen();
            List<ProductoInsumo> productoInsumoListOld = persistentInsumo.getProductoInsumoList();
            List<ProductoInsumo> productoInsumoListNew = insumo.getProductoInsumoList();
            List<Ipv> ipvListOld = persistentInsumo.getIpvList();
            List<Ipv> ipvListNew = insumo.getIpvList();
            List<InsumoElaborado> insumoElaboradoListOld = persistentInsumo.getInsumoElaboradoList();
            List<InsumoElaborado> insumoElaboradoListNew = insumo.getInsumoElaboradoList();
            List<InsumoElaborado> insumoElaboradoList1Old = persistentInsumo.getInsumoElaboradoList1();
            List<InsumoElaborado> insumoElaboradoList1New = insumo.getInsumoElaboradoList1();
            List<String> illegalOrphanMessages = null;
            for (ProductoInsumo productoInsumoListOldProductoInsumo : productoInsumoListOld) {
                if (!productoInsumoListNew.contains(productoInsumoListOldProductoInsumo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProductoInsumo " + productoInsumoListOldProductoInsumo + " since its insumo field is not nullable.");
                }
            }
            for (Ipv ipvListOldIpv : ipvListOld) {
                if (!ipvListNew.contains(ipvListOldIpv)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ipv " + ipvListOldIpv + " since its insumo field is not nullable.");
                }
            }
            for (InsumoElaborado insumoElaboradoListOldInsumoElaborado : insumoElaboradoListOld) {
                if (!insumoElaboradoListNew.contains(insumoElaboradoListOldInsumoElaborado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain InsumoElaborado " + insumoElaboradoListOldInsumoElaborado + " since its insumo field is not nullable.");
                }
            }
            for (InsumoElaborado insumoElaboradoList1OldInsumoElaborado : insumoElaboradoList1Old) {
                if (!insumoElaboradoList1New.contains(insumoElaboradoList1OldInsumoElaborado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain InsumoElaborado " + insumoElaboradoList1OldInsumoElaborado + " since its insumo1 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (almacencodAlmacenNew != null) {
                almacencodAlmacenNew = em.getReference(almacencodAlmacenNew.getClass(), almacencodAlmacenNew.getCodAlmacen());
                insumo.setAlmacencodAlmacen(almacencodAlmacenNew);
            }
            List<ProductoInsumo> attachedProductoInsumoListNew = new ArrayList<ProductoInsumo>();
            for (ProductoInsumo productoInsumoListNewProductoInsumoToAttach : productoInsumoListNew) {
                productoInsumoListNewProductoInsumoToAttach = em.getReference(productoInsumoListNewProductoInsumoToAttach.getClass(), productoInsumoListNewProductoInsumoToAttach.getProductoInsumoPK());
                attachedProductoInsumoListNew.add(productoInsumoListNewProductoInsumoToAttach);
            }
            productoInsumoListNew = attachedProductoInsumoListNew;
            insumo.setProductoInsumoList(productoInsumoListNew);
            List<Ipv> attachedIpvListNew = new ArrayList<Ipv>();
            for (Ipv ipvListNewIpvToAttach : ipvListNew) {
                ipvListNewIpvToAttach = em.getReference(ipvListNewIpvToAttach.getClass(), ipvListNewIpvToAttach.getIpvPK());
                attachedIpvListNew.add(ipvListNewIpvToAttach);
            }
            ipvListNew = attachedIpvListNew;
            insumo.setIpvList(ipvListNew);
            List<InsumoElaborado> attachedInsumoElaboradoListNew = new ArrayList<InsumoElaborado>();
            for (InsumoElaborado insumoElaboradoListNewInsumoElaboradoToAttach : insumoElaboradoListNew) {
                insumoElaboradoListNewInsumoElaboradoToAttach = em.getReference(insumoElaboradoListNewInsumoElaboradoToAttach.getClass(), insumoElaboradoListNewInsumoElaboradoToAttach.getInsumoElaboradoPK());
                attachedInsumoElaboradoListNew.add(insumoElaboradoListNewInsumoElaboradoToAttach);
            }
            insumoElaboradoListNew = attachedInsumoElaboradoListNew;
            insumo.setInsumoElaboradoList(insumoElaboradoListNew);
            List<InsumoElaborado> attachedInsumoElaboradoList1New = new ArrayList<InsumoElaborado>();
            for (InsumoElaborado insumoElaboradoList1NewInsumoElaboradoToAttach : insumoElaboradoList1New) {
                insumoElaboradoList1NewInsumoElaboradoToAttach = em.getReference(insumoElaboradoList1NewInsumoElaboradoToAttach.getClass(), insumoElaboradoList1NewInsumoElaboradoToAttach.getInsumoElaboradoPK());
                attachedInsumoElaboradoList1New.add(insumoElaboradoList1NewInsumoElaboradoToAttach);
            }
            insumoElaboradoList1New = attachedInsumoElaboradoList1New;
            insumo.setInsumoElaboradoList1(insumoElaboradoList1New);
            insumo = em.merge(insumo);
            if (almacencodAlmacenOld != null && !almacencodAlmacenOld.equals(almacencodAlmacenNew)) {
                almacencodAlmacenOld.getInsumoList().remove(insumo);
                almacencodAlmacenOld = em.merge(almacencodAlmacenOld);
            }
            if (almacencodAlmacenNew != null && !almacencodAlmacenNew.equals(almacencodAlmacenOld)) {
                almacencodAlmacenNew.getInsumoList().add(insumo);
                almacencodAlmacenNew = em.merge(almacencodAlmacenNew);
            }
            for (ProductoInsumo productoInsumoListNewProductoInsumo : productoInsumoListNew) {
                if (!productoInsumoListOld.contains(productoInsumoListNewProductoInsumo)) {
                    Insumo oldInsumoOfProductoInsumoListNewProductoInsumo = productoInsumoListNewProductoInsumo.getInsumo();
                    productoInsumoListNewProductoInsumo.setInsumo(insumo);
                    productoInsumoListNewProductoInsumo = em.merge(productoInsumoListNewProductoInsumo);
                    if (oldInsumoOfProductoInsumoListNewProductoInsumo != null && !oldInsumoOfProductoInsumoListNewProductoInsumo.equals(insumo)) {
                        oldInsumoOfProductoInsumoListNewProductoInsumo.getProductoInsumoList().remove(productoInsumoListNewProductoInsumo);
                        oldInsumoOfProductoInsumoListNewProductoInsumo = em.merge(oldInsumoOfProductoInsumoListNewProductoInsumo);
                    }
                }
            }
            for (Ipv ipvListNewIpv : ipvListNew) {
                if (!ipvListOld.contains(ipvListNewIpv)) {
                    Insumo oldInsumoOfIpvListNewIpv = ipvListNewIpv.getInsumo();
                    ipvListNewIpv.setInsumo(insumo);
                    ipvListNewIpv = em.merge(ipvListNewIpv);
                    if (oldInsumoOfIpvListNewIpv != null && !oldInsumoOfIpvListNewIpv.equals(insumo)) {
                        oldInsumoOfIpvListNewIpv.getIpvList().remove(ipvListNewIpv);
                        oldInsumoOfIpvListNewIpv = em.merge(oldInsumoOfIpvListNewIpv);
                    }
                }
            }
            for (InsumoElaborado insumoElaboradoListNewInsumoElaborado : insumoElaboradoListNew) {
                if (!insumoElaboradoListOld.contains(insumoElaboradoListNewInsumoElaborado)) {
                    Insumo oldInsumoOfInsumoElaboradoListNewInsumoElaborado = insumoElaboradoListNewInsumoElaborado.getInsumo();
                    insumoElaboradoListNewInsumoElaborado.setInsumo(insumo);
                    insumoElaboradoListNewInsumoElaborado = em.merge(insumoElaboradoListNewInsumoElaborado);
                    if (oldInsumoOfInsumoElaboradoListNewInsumoElaborado != null && !oldInsumoOfInsumoElaboradoListNewInsumoElaborado.equals(insumo)) {
                        oldInsumoOfInsumoElaboradoListNewInsumoElaborado.getInsumoElaboradoList().remove(insumoElaboradoListNewInsumoElaborado);
                        oldInsumoOfInsumoElaboradoListNewInsumoElaborado = em.merge(oldInsumoOfInsumoElaboradoListNewInsumoElaborado);
                    }
                }
            }
            for (InsumoElaborado insumoElaboradoList1NewInsumoElaborado : insumoElaboradoList1New) {
                if (!insumoElaboradoList1Old.contains(insumoElaboradoList1NewInsumoElaborado)) {
                    Insumo oldInsumo1OfInsumoElaboradoList1NewInsumoElaborado = insumoElaboradoList1NewInsumoElaborado.getInsumo1();
                    insumoElaboradoList1NewInsumoElaborado.setInsumo1(insumo);
                    insumoElaboradoList1NewInsumoElaborado = em.merge(insumoElaboradoList1NewInsumoElaborado);
                    if (oldInsumo1OfInsumoElaboradoList1NewInsumoElaborado != null && !oldInsumo1OfInsumoElaboradoList1NewInsumoElaborado.equals(insumo)) {
                        oldInsumo1OfInsumoElaboradoList1NewInsumoElaborado.getInsumoElaboradoList1().remove(insumoElaboradoList1NewInsumoElaborado);
                        oldInsumo1OfInsumoElaboradoList1NewInsumoElaborado = em.merge(oldInsumo1OfInsumoElaboradoList1NewInsumoElaborado);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = insumo.getCodInsumo();
                if (findInsumo(id) == null) {
                    throw new NonexistentEntityException("The insumo with id " + id + " no longer exists.");
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
            Insumo insumo;
            try {
                insumo = em.getReference(Insumo.class, id);
                insumo.getCodInsumo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The insumo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<ProductoInsumo> productoInsumoListOrphanCheck = insumo.getProductoInsumoList();
            for (ProductoInsumo productoInsumoListOrphanCheckProductoInsumo : productoInsumoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Insumo (" + insumo + ") cannot be destroyed since the ProductoInsumo " + productoInsumoListOrphanCheckProductoInsumo + " in its productoInsumoList field has a non-nullable insumo field.");
            }
            List<Ipv> ipvListOrphanCheck = insumo.getIpvList();
            for (Ipv ipvListOrphanCheckIpv : ipvListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Insumo (" + insumo + ") cannot be destroyed since the Ipv " + ipvListOrphanCheckIpv + " in its ipvList field has a non-nullable insumo field.");
            }
            List<InsumoElaborado> insumoElaboradoListOrphanCheck = insumo.getInsumoElaboradoList();
            for (InsumoElaborado insumoElaboradoListOrphanCheckInsumoElaborado : insumoElaboradoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Insumo (" + insumo + ") cannot be destroyed since the InsumoElaborado " + insumoElaboradoListOrphanCheckInsumoElaborado + " in its insumoElaboradoList field has a non-nullable insumo field.");
            }
            List<InsumoElaborado> insumoElaboradoList1OrphanCheck = insumo.getInsumoElaboradoList1();
            for (InsumoElaborado insumoElaboradoList1OrphanCheckInsumoElaborado : insumoElaboradoList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Insumo (" + insumo + ") cannot be destroyed since the InsumoElaborado " + insumoElaboradoList1OrphanCheckInsumoElaborado + " in its insumoElaboradoList1 field has a non-nullable insumo1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Almacen almacencodAlmacen = insumo.getAlmacencodAlmacen();
            if (almacencodAlmacen != null) {
                almacencodAlmacen.getInsumoList().remove(insumo);
                almacencodAlmacen = em.merge(almacencodAlmacen);
            }
            em.remove(insumo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Insumo> findInsumoEntities() {
        return findInsumoEntities(true, -1, -1);
    }

    public List<Insumo> findInsumoEntities(int maxResults, int firstResult) {
        return findInsumoEntities(false, maxResults, firstResult);
    }

    private List<Insumo> findInsumoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Insumo.class));
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

    public Insumo findInsumo(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Insumo.class, id);
        } finally {
            em.close();
        }
    }

    public int getInsumoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Insumo> rt = cq.from(Insumo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
