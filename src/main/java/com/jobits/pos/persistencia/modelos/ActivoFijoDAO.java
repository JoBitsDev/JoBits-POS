/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.persistencia.modelos;


import com.jobits.pos.persistencia.ActivoFijo;
import com.jobits.pos.persistencia.Almacen;
import com.jobits.pos.persistencia.InsumoAlmacen;
/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class ActivoFijoDAO extends AbstractModel<ActivoFijo> {

    private static ActivoFijoDAO INSTANCE = null;

    private ActivoFijoDAO() {
        super(ActivoFijo.class);
    }

    public static ActivoFijoDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ActivoFijoDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }

   
    
}
