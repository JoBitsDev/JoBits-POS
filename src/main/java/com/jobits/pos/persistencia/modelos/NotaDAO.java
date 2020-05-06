/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.persistencia.modelos;

import com.jobits.pos.persistencia.Nota;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class NotaDAO extends AbstractModel<Nota> {

    private static NotaDAO INSTANCE = null;

    private NotaDAO() {
        super(Nota.class);
    }

    public static NotaDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NotaDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }
}
