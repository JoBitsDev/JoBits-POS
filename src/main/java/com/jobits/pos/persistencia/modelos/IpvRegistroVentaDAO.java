/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.persistencia.modelos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import com.jobits.pos.persistencia.Cocina;
import com.jobits.pos.persistencia.Insumo;
import com.jobits.pos.persistencia.IpvRegistro;
import com.jobits.pos.persistencia.IpvVentaRegistro;

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

    public List<IpvVentaRegistro> getIpvVentaRegistroList(Date fecha) {
        List<IpvVentaRegistro> ret = new ArrayList<>(getEntityManager().createNamedQuery("IpvVentaRegistro.findByVentafecha")
                .setParameter("ventafecha", fecha)
                .getResultList());
        for (IpvVentaRegistro x : ret) {
            getEntityManager().refresh(x);
        }
        return ret;
    }

}
