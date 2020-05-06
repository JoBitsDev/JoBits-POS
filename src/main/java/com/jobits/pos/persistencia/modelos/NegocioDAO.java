/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.persistencia.modelos;


import com.jobits.pos.persistencia.Almacen;
import com.jobits.pos.persistencia.Area;
import com.jobits.pos.persistencia.InsumoAlmacen;
import com.jobits.pos.persistencia.Negocio;
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
