/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.persistencia.modelos;


import com.jobits.pos.persistencia.Cocina;
import com.jobits.pos.persistencia.Personal;
/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class CocinaDAO extends AbstractModel<Cocina> {

    private static CocinaDAO INSTANCE = null;

    private CocinaDAO() {
        super(Cocina.class);
    }

    public static CocinaDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CocinaDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }

   
    
}
