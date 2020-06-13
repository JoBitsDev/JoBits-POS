/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.adapters.repo.impl;


import com.jobits.pos.domain.models.Transaccion;
import com.jobits.pos.domain.models.TransaccionEntrada;
import com.jobits.pos.domain.models.TransaccionSalida;
/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class TransaccionSalidaDAO extends AbstractRepository<TransaccionSalida> {

    private static TransaccionSalidaDAO INSTANCE = null;

    private TransaccionSalidaDAO() {
        super(TransaccionSalida.class);
    }

    public static TransaccionSalidaDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TransaccionSalidaDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }

    
   
    
}
