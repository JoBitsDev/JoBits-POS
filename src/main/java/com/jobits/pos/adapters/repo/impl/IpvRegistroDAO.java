/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.adapters.repo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import com.jobits.pos.domain.models.Cocina;
import com.jobits.pos.domain.models.Insumo;
import com.jobits.pos.domain.models.IpvRegistro;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class IpvRegistroDAO extends AbstractRepository<IpvRegistro> {

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

      public List<IpvRegistro> getIpvRegistroList(Date fecha) {
        List<IpvRegistro> ret = new ArrayList<>(
                getEntityManager().createNamedQuery("IpvRegistro.findByFecha")
                .setParameter("fecha", fecha)
                .getResultList());
        for (IpvRegistro x : ret) {
            getEntityManager().refresh(x);
        }
        return ret;
    }
    
    public List<Date> getIpvRegistroList(Cocina cocina) {
        return getEntityManager().createNamedQuery("IpvRegistro.findByIpvcocinacodCocina")
                .setParameter("ipvcocinacodCocina", cocina.getCodCocina())
                .getResultList();
    }

    public List<IpvRegistro> getIpvRegistroList(Cocina cocina, Date fecha) {
        List<IpvRegistro> ret = new ArrayList<>(getEntityManager().createNamedQuery("IpvRegistro.findByIpvcocinacodCocinaAndFecha")
                .setParameter("ipvcocinacodCocina", cocina.getCodCocina())
                .setParameter("fecha", fecha)
                .getResultList());
        for (IpvRegistro x : ret) {
            getEntityManager().refresh(x);
        }
        return ret;
    }

    public IpvRegistro getIpvRegistro(Cocina c, Date fecha, Insumo i)throws NoResultException,PersistenceException{
        IpvRegistro ret =  (IpvRegistro) getEntityManager().createNamedQuery("IpvRegistro.findByIpvcocinacodCocinaAndFechaAndInsumo")
                .setParameter("ipvcocinacodCocina", c.getCodCocina())
                .setParameter("fecha", fecha)
                .setParameter("codinsumo", i.getCodInsumo())
                .getSingleResult();

        getEntityManager().refresh(ret);
        return ret;
    }

}
