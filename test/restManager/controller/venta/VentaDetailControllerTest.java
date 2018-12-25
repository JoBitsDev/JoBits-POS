/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.venta;

import java.awt.Container;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import restManager.persistencia.Orden;
import restManager.persistencia.Venta;

/**
 *
 * @author Jorge
 */
public class VentaDetailControllerTest {
    
    public VentaDetailControllerTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of createNewInstance method, of class VentaDetailController.
     */
    @Test
    public void testCreateNewInstance() {
        System.out.println("createNewInstance");
        VentaDetailController instance = new VentaDetailController();
        Venta expResult = null;
        Venta result = instance.createNewInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of constructView method, of class VentaDetailController.
     */
    @Test
    public void testConstructView() {
        System.out.println("constructView");
        Container parent = null;
        VentaDetailController instance = new VentaDetailController();
        instance.constructView(parent);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateOrdenDialog method, of class VentaDetailController.
     */
    @Test
    public void testUpdateOrdenDialog() {
        System.out.println("updateOrdenDialog");
        Orden objectAtSelectedRow = null;
        VentaDetailController instance = new VentaDetailController();
        instance.updateOrdenDialog(objectAtSelectedRow);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createNewOrden method, of class VentaDetailController.
     */
    @Test
    public void testCreateNewOrden() {
        System.out.println("createNewOrden");
        VentaDetailController instance = new VentaDetailController();
        instance.createNewOrden();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
