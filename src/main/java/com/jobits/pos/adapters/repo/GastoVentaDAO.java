/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.adapters.repo;

import java.util.Date;
import java.util.List;
import com.jobits.pos.domain.models.GastoVenta;



/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class GastoVentaDAO extends AbstractRepository<GastoVenta> {

    private static GastoVentaDAO INSTANCE = null;

    private GastoVentaDAO() {
        super(GastoVenta.class);
    }

    public static GastoVentaDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GastoVentaDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }
    
     public List getGastosByFecha(Date fecha){
    return getEntityManager().createNamedQuery("GastoVenta.findByVentafecha")
            .setParameter("ventafecha", fecha).getResultList();
    }
       
}
