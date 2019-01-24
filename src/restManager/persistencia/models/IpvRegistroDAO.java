/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.persistencia.models;

import javax.persistence.EntityManager;
import restManager.persistencia.Carta;
import restManager.persistencia.Ipv;
import restManager.persistencia.IpvRegistro;
import restManager.persistencia.IpvRegistroPK;
import restManager.persistencia.Mesa;
import restManager.persistencia.Nota;
import restManager.persistencia.jpa.exceptions.NonexistentEntityException;

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
//                ipvOld.getIpvRegistroList().remove(entity);
//                ipvOld = em.merge(ipvOld);
//            }
//            if (ipvNew != null && !ipvNew.equals(ipvOld)) {
//                ipvNew.getIpvRegistroList().add(entity);
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
