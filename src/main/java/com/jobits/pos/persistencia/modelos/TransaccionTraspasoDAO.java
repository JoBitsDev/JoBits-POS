/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.persistencia.modelos;


import com.jobits.pos.persistencia.Transaccion;
import com.jobits.pos.persistencia.TransaccionTraspaso;
/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class TransaccionTraspasoDAO extends AbstractModel<TransaccionTraspaso> {

    private static TransaccionTraspasoDAO INSTANCE = null;

    private TransaccionTraspasoDAO() {
        super(TransaccionTraspaso.class);
    }

    public static TransaccionTraspasoDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TransaccionTraspasoDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }

   
    
}
