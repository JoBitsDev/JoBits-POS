/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.adapters.repo;


import com.jobits.pos.domain.models.Almacen;
import com.jobits.pos.domain.models.InsumoAlmacen;
/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class AlmacenDAO extends AbstractRepository<Almacen> {

    private static AlmacenDAO INSTANCE = null;

    private AlmacenDAO() {
        super(Almacen.class);
    }

    public static AlmacenDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AlmacenDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }

    public InsumoAlmacen findInsumo(String codAlmacen, String codInsumo) {
        return InsumoAlmacenDAO.getInstance().getInsumoAlmacen(codInsumo, codAlmacen);
    }

   
    
}
