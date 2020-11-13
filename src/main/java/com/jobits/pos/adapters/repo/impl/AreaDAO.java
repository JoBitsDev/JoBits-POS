/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.adapters.repo.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import com.jobits.pos.domain.models.Almacen;
import com.jobits.pos.domain.models.Area;
import com.jobits.pos.domain.models.InsumoAlmacen;
import com.jobits.pos.domain.models.Mesa;
import com.jobits.pos.domain.models.Orden;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class AreaDAO extends AbstractRepository<Area> {

    private static AreaDAO INSTANCE = null;

    private AreaDAO() {
        super(Area.class);
    }

    public static AreaDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AreaDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }

    @Override
    public void remove(Area entity) {
        for (Mesa m : entity.getMesaList()) {
            for (Orden o : m.getOrdenList()) {
                o.setMesacodMesa(null);
            }
        }
        super.remove(entity); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
