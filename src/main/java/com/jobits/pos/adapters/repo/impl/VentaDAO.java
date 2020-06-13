/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.adapters.repo.impl;

import com.jobits.pos.domain.models.Mesa;
import com.jobits.pos.domain.models.Nota;
import com.jobits.pos.domain.models.Venta;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class VentaDAO extends AbstractRepository<Venta> {

    private static VentaDAO INSTANCE = null;

    private VentaDAO() {
        super(Venta.class);
    }

    public static VentaDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new VentaDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }
}
