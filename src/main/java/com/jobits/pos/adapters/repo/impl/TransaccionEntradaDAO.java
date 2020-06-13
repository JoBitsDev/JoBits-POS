/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.adapters.repo.impl;


import com.jobits.pos.domain.models.Transaccion;
import com.jobits.pos.domain.models.TransaccionEntrada;
/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class TransaccionEntradaDAO extends AbstractRepository<TransaccionEntrada> {

    private static TransaccionEntradaDAO INSTANCE = null;

    private TransaccionEntradaDAO() {
        super(TransaccionEntrada.class);
    }

    public static TransaccionEntradaDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TransaccionEntradaDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }

    
   
    
}
