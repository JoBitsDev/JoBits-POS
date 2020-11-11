/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.adapters.repo.impl;


import com.jobits.pos.domain.models.Almacen;
import com.jobits.pos.domain.models.Area;
import com.jobits.pos.domain.models.Carta;
import com.jobits.pos.domain.models.Cliente;
import com.jobits.pos.domain.models.InsumoAlmacen;
/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class ClienteDAO extends AbstractRepository<Cliente> {

    private static ClienteDAO INSTANCE = null;

    private ClienteDAO() {
        super(Cliente.class);
    }

    public static ClienteDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ClienteDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }
       
}
