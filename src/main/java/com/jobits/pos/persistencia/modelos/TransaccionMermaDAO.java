/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.persistencia.modelos;


import com.jobits.pos.persistencia.Transaccion;
import com.jobits.pos.persistencia.TransaccionEntrada;
import com.jobits.pos.persistencia.TransaccionMerma;
/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class TransaccionMermaDAO extends AbstractModel<TransaccionMerma> {

    private static TransaccionMermaDAO INSTANCE = null;

    private TransaccionMermaDAO() {
        super(TransaccionMerma.class);
    }

    public static TransaccionMermaDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TransaccionMermaDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }

    
   
    
}
