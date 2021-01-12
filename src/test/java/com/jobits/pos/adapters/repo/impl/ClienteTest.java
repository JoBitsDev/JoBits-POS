/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.adapters.repo.impl;

import com.jobits.pos.core.repo.impl.ClienteDAO;
import com.jobits.pos.controller.login.UbicacionConexionController;
import com.jobits.pos.core.domain.models.Cliente;
import com.jobits.pos.core.domain.models.ProductovOrden;
import com.jobits.pos.recursos.DBConnector;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jorge
 */
public class ClienteTest {

    public ClienteTest() {
    }

    @Before
    public void setUp() {
        DBConnector.init(new UbicacionConexionController().getUbicaciones().getUbicacionActiva());
    }

    /**
     * Test of getInstance method, of class ProductovOrdenDAO.
     */
    @Test
    public void testGetInstance() {
       
        ClienteDAO.getInstance().startTransaction();
        Cliente a = new Cliente();
        a.setNombreCliente("hola");
        a.setTelefonoCliente("asdasdasd");
        ClienteDAO.getInstance().create(a);
        ClienteDAO.getInstance().commitTransaction();
        
    }

}
