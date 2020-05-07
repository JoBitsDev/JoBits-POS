/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.adapters.repo;

import com.jobits.pos.domain.models.Mesa;
import com.jobits.pos.domain.models.Nota;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class MesaDAO extends AbstractRepository<Mesa> {

    private static MesaDAO INSTANCE = null;

    private MesaDAO() {
        super(Mesa.class);
    }

    public static MesaDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MesaDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }
}
