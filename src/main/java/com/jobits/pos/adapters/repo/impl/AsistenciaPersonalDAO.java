/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.adapters.repo.impl;


import java.util.Date;
import java.util.List;
import com.jobits.pos.exceptions.DevelopingOperationException;
import com.jobits.pos.domain.models.AsistenciaPersonal;
/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class AsistenciaPersonalDAO extends AbstractRepository<AsistenciaPersonal> {

    private static AsistenciaPersonalDAO INSTANCE = null;

    private AsistenciaPersonalDAO() {
        super(AsistenciaPersonal.class);
    }

    public static AsistenciaPersonalDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AsistenciaPersonalDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }

    public List<AsistenciaPersonal> getPersonalTrabajando(Date fecha) {
          return  getEntityManager().createNamedQuery("AsistenciaPersonal.findByVentafecha")
                .setParameter("ventafecha", fecha)
                .getResultList();
    }
       
}
