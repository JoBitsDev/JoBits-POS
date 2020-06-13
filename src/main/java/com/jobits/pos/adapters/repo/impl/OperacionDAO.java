/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.adapters.repo.impl;


import com.jobits.pos.domain.models.Operacion;
/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class OperacionDAO extends AbstractRepository<Operacion> {

    private static OperacionDAO INSTANCE = null;

    private OperacionDAO() {
        super(Operacion.class);
    }

    public static OperacionDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OperacionDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }
       
}
