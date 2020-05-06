/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.adapters.repo;


import com.jobits.pos.domain.models.Almacen;
import com.jobits.pos.domain.models.Area;
import com.jobits.pos.domain.models.InsumoAlmacen;
import com.jobits.pos.domain.models.Negocio;
/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class NegocioDAO extends AbstractModel<Negocio> {

    private static NegocioDAO INSTANCE = null;

    private NegocioDAO() {
        super(Negocio.class);
    }

    public static NegocioDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NegocioDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }
       
}
