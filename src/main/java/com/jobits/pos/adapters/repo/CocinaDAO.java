/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.adapters.repo;

import com.jobits.pos.domain.models.Cocina;
import com.jobits.pos.domain.models.Personal;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class CocinaDAO extends AbstractRepository<Cocina> {

    private static CocinaDAO INSTANCE;

    private CocinaDAO() {
        super(Cocina.class);
    }

    public static CocinaDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CocinaDAO();
        }
        return INSTANCE;
    }

}
