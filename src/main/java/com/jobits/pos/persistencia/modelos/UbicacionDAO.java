/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.persistencia.modelos;


import com.jobits.pos.persistencia.ActivoFijo;
import com.jobits.pos.persistencia.Almacen;
import com.jobits.pos.persistencia.InsumoAlmacen;
import com.jobits.pos.persistencia.Ubicacion;
/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class UbicacionDAO extends AbstractModel<Ubicacion> {

    private static UbicacionDAO INSTANCE = null;

    private UbicacionDAO() {
        super(Ubicacion.class);
    }

    public static UbicacionDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UbicacionDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }

   
    
}
