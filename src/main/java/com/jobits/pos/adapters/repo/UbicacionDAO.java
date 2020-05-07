/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.adapters.repo;


import com.jobits.pos.domain.models.ActivoFijo;
import com.jobits.pos.domain.models.Almacen;
import com.jobits.pos.domain.models.InsumoAlmacen;
import com.jobits.pos.domain.models.Ubicacion;
/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class UbicacionDAO extends AbstractRepository<Ubicacion> {

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
