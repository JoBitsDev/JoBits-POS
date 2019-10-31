/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.gastos_pagos;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import restManager.persistencia.models.PersonalDAO;
import restManager.resources.R;

/**
 *
 * @author Jorge
 */
public class CuentaControllerTest {

    public CuentaControllerTest() {
    }

    @Before
    public void setUp() {
        R.PERIRSTENCE_UNIT_NAME = R.RESOURCE_BUNDLE.getString("unidad_persistencia_remota");
        R.loggedUser = PersonalDAO.getInstance().find("admin");
        R.COIN_SUFFIX = " CUC";
    }

    /**
     * Test of create method, of class CuentaController.
     */
    @Test
    public void testCreate() {
        CuentaController controller = new CuentaController();
        controller.constructView(null);
        
    }

    /**
     * Test of constructView method, of class CuentaController.
     */
    @Test
    public void testConstructView() {
    }

    /**
     * Test of getParent method, of class CuentaController.
     */
    @Test
    public void testGetParent() {
    }

    /**
     * Test of setParent method, of class CuentaController.
     */
    @Test
    public void testSetParent() {
    }

    /**
     * Test of getCuentasList method, of class CuentaController.
     */
    @Test
    public void testGetCuentasList() {
    }

}
