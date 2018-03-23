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
import restManager.persistencia.Cocina;
import restManager.persistencia.Insumo;
import restManager.persistencia.IpvRegistro;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import restManager.persistencia.Ipv;
import restManager.persistencia.IpvPK;
import restManager.persistencia.jpa.exceptions.IllegalOrphanException;
import restManager.persistencia.jpa.exceptions.NonexistentEntityException;
import restManager.persistencia.jpa.exceptions.PreexistingEntityException;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class IpvJpaController implements Serializable {

    public IpvJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ipv ipv) throws PreexistingEntityException, Exception {
        if (ipv.getIpvPK() == null) {
            ipv.setIpvPK(new IpvPK());
        }
        if (ipv.getIpvRegistroList() == null) {
            ipv.setIpvRegistroList(new ArrayList<IpvRegistro>());
        }
        ipv.getIpvPK().setCocinacodCocina(ipv.getCocina().getCodCocina());
        ipv.getIpvPK().setInsumocodInsumo(ipv.getInsumo().getCodInsumo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cocina cocina = ipv.getCocina();
            if (cocina != null) {
                cocina = em.getReference(cocina.getClass(), cocina.getCodCocina());
                ipv.setCocina(cocina);
            }
            Insumo insumo = ipv.getInsumo();
            if (insumo != null) {
                insumo = em.getReference(insumo.getClass(), insumo.getCodInsumo());
                ipv.setInsumo(insumo);
            }
            List<IpvRegistro> attachedIpvRegistroList = new ArrayList<IpvRegistro>();
            for (IpvRegistro ipvRegistroListIpvRegistroToAttach : ipv.getIpvRegistroList()) {
                ipvRegistroListIpvRegistroToAttach = em.getReference(ipvRegistroListIpvRegistroToAttach.getClass(), ipvRegistroListIpvRegistroToAttach.getIpvRegistroPK());
                attachedIpvRegistroList.add(ipvRegistroListIpvRegistroToAttach);
            }
            ipv.setIpvRegistroList(attachedIpvRegistroList);
            em.persist(ipv);
            if (cocina != null) {
                cocina.getIpvList().add(ipv);
                cocina = em.merge(cocina);
            }
            if (insumo != null) {
                insumo.getIpvList().add(ipv);
                insumo = em.merge(insumo);
            }
            for (IpvRegistro ipvRegistroListIpvRegistro : ipv.getIpvRegistroList()) {
                Ipv oldIpvOfIpvRegistroListIpvRegistro = ipvRegistroListIpvRegistro.getIpv();
                ipvRegistroListIpvRegistro.setIpv(ipv);
                ipvRegistroListIpvRegistro = em.merge(ipvRegistroListIpvRegistro);
                if (oldIpvOfIpvRegistroListIpvRegistro != null) {
                    oldIpvOfIpvRegistroListIpvRegistro.getIpvRegistroList().remove(ipvRegistroListIpvRegistro);
                    oldIpvOfIpvRegistroListIpvRegistro = em.merge(oldIpvOfIpvRegistroListIpvRegistro);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findIpv(ipv.getIpvPK()) != null) {
                throw new PreexistingEntityException("Ipv " + ipv + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ipv ipv) throws IllegalOrphanException, NonexistentEntityException, Exception {
        ipv.getIpvPK().setCocinacodCocina(ipv.getCocina().getCodCocina());
        ipv.getIpvPK().setInsumocodInsumo(ipv.getInsumo().getCodInsumo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ipv persistentIpv = em.find(Ipv.class, ipv.getIpvPK());
            Cocina cocinaOld = persistentIpv.getCocina();
            Cocina cocinaNew = ipv.getCocina();
            Insumo insumoOld = persistentIpv.getInsumo();
            Insumo insumoNew = ipv.getInsumo();
            List<IpvRegistro> ipvRegistroListOld = persistentIpv.getIpvRegistroList();
            List<IpvRegistro> ipvRegistroListNew = ipv.getIpvRegistroList();
            List<String> illegalOrphanMessages = null;
            for (IpvRegistro ipvRegistroListOldIpvRegistro : ipvRegistroListOld) {
                if (!ipvRegistroListNew.contains(ipvRegistroListOldIpvRegistro)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain IpvRegistro " + ipvRegistroListOldIpvRegistro + " since its ipv field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (cocinaNew != null) {
                cocinaNew = em.getReference(cocinaNew.getClass(), cocinaNew.getCodCocina());
                ipv.setCocina(cocinaNew);
            }
            if (insumoNew != null) {
                insumoNew = em.getReference(insumoNew.getClass(), insumoNew.getCodInsumo());
                ipv.setInsumo(insumoNew);
            }
            List<IpvRegistro> attachedIpvRegistroListNew = new ArrayList<IpvRegistro>();
            for (IpvRegistro ipvRegistroListNewIpvRegistroToAttach : ipvRegistroListNew) {
                ipvRegistroListNewIpvRegistroToAttach = em.getReference(ipvRegistroListNewIpvRegistroToAttach.getClass(), ipvRegistroListNewIpvRegistroToAttach.getIpvRegistroPK());
                attachedIpvRegistroListNew.add(ipvRegistroListNewIpvRegistroToAttach);
            }
            ipvRegistroListNew = attachedIpvRegistroListNew;
            ipv.setIpvRegistroList(ipvRegistroListNew);
            ipv = em.merge(ipv);
            if (cocinaOld != null && !cocinaOld.equals(cocinaNew)) {
                cocinaOld.getIpvList().remove(ipv);
                cocinaOld = em.merge(cocinaOld);
            }
            if (cocinaNew != null && !cocinaNew.equals(cocinaOld)) {
                cocinaNew.getIpvList().add(ipv);
                cocinaNew = em.merge(cocinaNew);
            }
            if (insumoOld != null && !insumoOld.equals(insumoNew)) {
                insumoOld.getIpvList().remove(ipv);
                insumoOld = em.merge(insumoOld);
            }
            if (insumoNew != null && !insumoNew.equals(insumoOld)) {
                insumoNew.getIpvList().add(ipv);
                insumoNew = em.merge(insumoNew);
            }
            for (IpvRegistro ipvRegistroListNewIpvRegistro : ipvRegistroListNew) {
                if (!ipvRegistroListOld.contains(ipvRegistroListNewIpvRegistro)) {
                    Ipv oldIpvOfIpvRegistroListNewIpvRegistro = ipvRegistroListNewIpvRegistro.getIpv();
                    ipvRegistroListNewIpvRegistro.setIpv(ipv);
                    ipvRegistroListNewIpvRegistro = em.merge(ipvRegistroListNewIpvRegistro);
                    if (oldIpvOfIpvRegistroListNewIpvRegistro != null && !oldIpvOfIpvRegistroListNewIpvRegistro.equals(ipv)) {
                        oldIpvOfIpvRegistroListNewIpvRegistro.getIpvRegistroList().remove(ipvRegistroListNewIpvRegistro);
                        oldIpvOfIpvRegistroListNewIpvRegistro = em.merge(oldIpvOfIpvRegistroListNewIpvRegistro);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                IpvPK id = ipv.getIpvPK();
                if (findIpv(id) == null) {
                    throw new NonexistentEntityException("The ipv with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(IpvPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ipv ipv;
            try {
                ipv = em.getReference(Ipv.class, id);
                ipv.getIpvPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ipv with id " + id + " no longer exists.", enfe);
            }
            List<IpvRegistro> reg = ipv.getIpvRegistroList();
            for (IpvRegistro r : reg) {
               staticContent.ipvregJPA.destroy(r.getIpvRegistroPK());
            }
            
            em.remove(ipv);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ipv> findIpvEntities() {
        return findIpvEntities(true, -1, -1);
    }

    public List<Ipv> findIpvEntities(int maxResults, int firstResult) {
        return findIpvEntities(false, maxResults, firstResult);
    }

    private List<Ipv> findIpvEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ipv.class));
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

    public Ipv findIpv(IpvPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ipv.class, id);
        } finally {
            em.close();
        }
    }

    public int getIpvCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ipv> rt = cq.from(Ipv.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
