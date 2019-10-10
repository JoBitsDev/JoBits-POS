/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.persistencia.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import restManager.persistencia.Cocina;
import restManager.persistencia.Insumo;
import restManager.persistencia.IpvRegistro;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class IpvRegistroDAO extends AbstractModel<IpvRegistro> {

    private static IpvRegistroDAO INSTANCE = null;

    private IpvRegistroDAO() {
        super(IpvRegistro.class);
    }

    public static IpvRegistroDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new IpvRegistroDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }

    public List<Date> getIpvRegistroList(Cocina cocina) {
        return getEntityManager().createNamedQuery("IpvRegistro.findByIpvcocinacodCocina")
                .setParameter("ipvcocinacodCocina", cocina.getCodCocina())
                .getResultList();
    }

    public List<IpvRegistro> getIpvRegistroList(Cocina cocina, Date fecha) {
        getEntityManager().getEntityManagerFactory().getCache().evict(IpvRegistro.class);
        return new ArrayList<>(getEntityManager().createNamedQuery("IpvRegistro.findByIpvcocinacodCocinaAndFecha")
                .setParameter("ipvcocinacodCocina", cocina.getCodCocina())
                .setParameter("fecha", fecha)
                .getResultList());

    }

    public IpvRegistro getIpvRegistro(Cocina c, Date fecha, Insumo i)throws NoResultException,PersistenceException{
        return (IpvRegistro) getEntityManager().createNamedQuery("IpvRegistro.findByIpvcocinacodCocinaAndFechaAndInsumo")
                .setParameter("ipvcocinacodCocina", c.getCodCocina())
                .setParameter("fecha", fecha)
                .setParameter("codinsumo", i.getCodInsumo())
                .getSingleResult();

    }
//    @Override
//    public void edit(IpvRegistro entity) {
// entity.getIpvRegistroPK().setIpvinsumocodInsumo(entity.getIpv().getIpvPK().getInsumocodInsumo());
//        entity.getIpvRegistroPK().setIpvcocinacodCocina(entity.getIpv().getIpvPK().getCocinacodCocina());
//        EntityManager em = null;
//        try {
//            em = getEntityManager();
//            em.getTransaction().begin();
//            IpvRegistro persistentIpvRegistro = em.find(IpvRegistro.class, entity.getIpvRegistroPK());
//            Ipv ipvOld = persistentIpvRegistro.getIpv();
//            Ipv ipvNew = entity.getIpv();
//            if (ipvNew != null) {
//                ipvNew = em.getReference(ipvNew.getClass(), ipvNew.getIpvPK());
//                entity.setIpv(ipvNew);
//            }
//            entity = em.merge(entity);
//            if (ipvOld != null && !ipvOld.equals(ipvNew)) {
//                ipvOld.getIpvRegistro().remove(entity);
//                ipvOld = em.merge(ipvOld);
//            }
//            if (ipvNew != null && !ipvNew.equals(ipvOld)) {
//                ipvNew.getIpvRegistro().add(entity);
//                ipvNew = em.merge(ipvNew);
//            }
//            em.getTransaction().commit();
//        } catch (Exception ex) {
//            String msg = ex.getLocalizedMessage();
//            if (msg == null || msg.length() == 0) {
//                IpvRegistroPK id = entity.getIpvRegistroPK();
//                if (findIpvRegistro(id) == null) {
//                    System.out.println("The ipvRegistro with id " + id + " no longer exists.");
//                }
//            }
//            throw ex;
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }
//    

}
