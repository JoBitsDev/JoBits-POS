/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.adapters.repo;


import com.jobits.pos.domain.models.Transaccion;
/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class TransaccionDAO extends AbstractRepository<Transaccion> {

    private static TransaccionDAO INSTANCE = null;

    private TransaccionDAO() {
        super(Transaccion.class);
    }

    public static TransaccionDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TransaccionDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }

   
    
}
