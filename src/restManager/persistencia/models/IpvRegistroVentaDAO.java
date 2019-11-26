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
import restManager.persistencia.IpvVentaRegistro;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class IpvRegistroVentaDAO extends AbstractModel<IpvVentaRegistro> {

    private static IpvRegistroVentaDAO INSTANCE = null;

    private IpvRegistroVentaDAO() {
        super(IpvVentaRegistro.class);
    }

    public static IpvRegistroVentaDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new IpvRegistroVentaDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }


    public List<IpvVentaRegistro> getIpvVentaRegistroList( Date fecha) {
        getEntityManager().getEntityManagerFactory().getCache().evict(IpvRegistro.class);
        return new ArrayList<>(getEntityManager().createNamedQuery("IpvVentaRegistro.findByVentafecha")
                .setParameter("ventafecha", fecha)
                .getResultList());

    }

}
