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
import restManager.persistencia.Ipv;
import restManager.persistencia.IpvRegistro;
import restManager.persistencia.IpvRegistroPK;
import restManager.persistencia.jpa.exceptions.NonexistentEntityException;
import restManager.persistencia.jpa.exceptions.PreexistingEntityException;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class IpvRegistroJpaController implements Serializable {

    public IpvRegistroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(IpvRegistro ipvRegistro) throws PreexistingEntityException, Exception {
        if (ipvRegistro.getIpvRegistroPK() == null) {
            ipvRegistro.setIpvRegistroPK(new IpvRegistroPK());
        }
        ipvRegistro.getIpvRegistroPK().setIpvinsumocodInsumo(ipvRegistro.getIpv().getIpvPK().getInsumocodInsumo());
        ipvRegistro.getIpvRegistroPK().setIpvcocinacodCocina(ipvRegistro.getIpv().getIpvPK().getCocinacodCocina());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ipv ipv = ipvRegistro.getIpv();
            if (ipv != null) {
                ipv = em.getReference(ipv.getClass(), ipv.getIpvPK());
                ipvRegistro.setIpv(ipv);
            }
            em.persist(ipvRegistro);
            if (ipv != null) {
                ipv.getIpvRegistroList().add(ipvRegistro);
                ipv = em.merge(ipv);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findIpvRegistro(ipvRegistro.getIpvRegistroPK()) != null) {
                throw new PreexistingEntityException("IpvRegistro " + ipvRegistro + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(IpvRegistro ipvRegistro) throws NonexistentEntityException, Exception {
        ipvRegistro.getIpvRegistroPK().setIpvinsumocodInsumo(ipvRegistro.getIpv().getIpvPK().getInsumocodInsumo());
        ipvRegistro.getIpvRegistroPK().setIpvcocinacodCocina(ipvRegistro.getIpv().getIpvPK().getCocinacodCocina());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            IpvRegistro persistentIpvRegistro = em.find(IpvRegistro.class, ipvRegistro.getIpvRegistroPK());
            Ipv ipvOld = persistentIpvRegistro.getIpv();
            Ipv ipvNew = ipvRegistro.getIpv();
            if (ipvNew != null) {
                ipvNew = em.getReference(ipvNew.getClass(), ipvNew.getIpvPK());
                ipvRegistro.setIpv(ipvNew);
            }
            ipvRegistro = em.merge(ipvRegistro);
            if (ipvOld != null && !ipvOld.equals(ipvNew)) {
                ipvOld.getIpvRegistroList().remove(ipvRegistro);
                ipvOld = em.merge(ipvOld);
            }
            if (ipvNew != null && !ipvNew.equals(ipvOld)) {
                ipvNew.getIpvRegistroList().add(ipvRegistro);
                ipvNew = em.merge(ipvNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                IpvRegistroPK id = ipvRegistro.getIpvRegistroPK();
                if (findIpvRegistro(id) == null) {
                    throw new NonexistentEntityException("The ipvRegistro with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(IpvRegistroPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            IpvRegistro ipvRegistro;
            try {
                ipvRegistro = em.getReference(IpvRegistro.class, id);
                ipvRegistro.getIpvRegistroPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ipvRegistro with id " + id + " no longer exists.", enfe);
            }
            Ipv ipv = ipvRegistro.getIpv();
            if (ipv != null) {
                ipv.getIpvRegistroList().remove(ipvRegistro);
                ipv = em.merge(ipv);
            }
            em.remove(ipvRegistro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<IpvRegistro> findIpvRegistroEntities() {
        return findIpvRegistroEntities(true, -1, -1);
    }

    public List<IpvRegistro> findIpvRegistroEntities(int maxResults, int firstResult) {
        return findIpvRegistroEntities(false, maxResults, firstResult);
    }

    private List<IpvRegistro> findIpvRegistroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(IpvRegistro.class));
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

    public IpvRegistro findIpvRegistro(IpvRegistroPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(IpvRegistro.class, id);
        } finally {
            em.close();
        }
    }

    public int getIpvRegistroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<IpvRegistro> rt = cq.from(IpvRegistro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
