/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.persistencia.Control;

import java.util.List;
import javax.swing.JTable;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import restManager.persistencia.Cocina;
import restManager.persistencia.Personal;
import restManager.persistencia.ProductoInsumo;
import restManager.persistencia.ProductovOrden;
import restManager.persistencia.Venta;

/**
 *
 * @author Jorge
 */
public class VentaDAOTest {
    
    public VentaDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getResumenVentasOnTable method, of class VentaDAO.
     */
    @Test
    public void testGetResumenVentasOnTable() {
        System.out.println("getResumenVentasOnTable");
        JTable tabla = null;
        Venta v = null;
        VentaDAO.getResumenVentasOnTable(tabla, v);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getResumenVentasDeLaCasaOnTable method, of class VentaDAO.
     */
    @Test
    public void testGetResumenVentasDeLaCasaOnTable() {
        System.out.println("getResumenVentasDeLaCasaOnTable");
        JTable tabla = null;
        Venta v = null;
        VentaDAO.getResumenVentasDeLaCasaOnTable(tabla, v);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getResumenVentasCamareroOnTable method, of class VentaDAO.
     */
    @Test
    public void testGetResumenVentasCamareroOnTable() {
        System.out.println("getResumenVentasCamareroOnTable");
        JTable tabla = null;
        Venta v = null;
        Personal p = null;
        VentaDAO.getResumenVentasCamareroOnTable(tabla, v, p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getResumenVentasCocinaOnTable method, of class VentaDAO.
     */
    @Test
    public void testGetResumenVentasCocinaOnTable() {
        System.out.println("getResumenVentasCocinaOnTable");
        JTable tabla = null;
        Venta v = null;
        Cocina c = null;
        VentaDAO.getResumenVentasCocinaOnTable(tabla, v, c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getResumenDetalladoVentasCocinaOnTable method, of class VentaDAO.
     */
    @Test
    public void testGetResumenDetalladoVentasCocinaOnTable() {
        System.out.println("getResumenDetalladoVentasCocinaOnTable");
        JTable tabla = null;
        Venta v = null;
        Cocina c = null;
        VentaDAO.getResumenDetalladoVentasCocinaOnTable(tabla, v, c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getResumenVentasCamarero method, of class VentaDAO.
     */
    @Test
    public void testGetResumenVentasCamarero() {
        System.out.println("getResumenVentasCamarero");
        Venta v = null;
        Personal p = null;
        List<ProductovOrden> expResult = null;
        List<ProductovOrden> result = VentaDAO.getResumenVentasCamarero(v, p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getResumenVentasCocina method, of class VentaDAO.
     */
    @Test
    public void testGetResumenVentasCocina() {
        System.out.println("getResumenVentasCocina");
        Venta v = null;
        Cocina c = null;
        List<ProductovOrden> expResult = null;
        List<ProductovOrden> result = VentaDAO.getResumenVentasCocina(v, c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getResumenGastosCocina method, of class VentaDAO.
     */
    @Test
    public void testGetResumenGastosCocina() {
        System.out.println("getResumenGastosCocina");
        Cocina c = null;
        Venta v = null;
        List<ProductoInsumo> expResult = null;
        List<ProductoInsumo> result = VentaDAO.getResumenGastosCocina(c, v);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getResumenGastosOnTable method, of class VentaDAO.
     */
    @Test
    public void testGetResumenGastosOnTable() {
        System.out.println("getResumenGastosOnTable");
        JTable tabla = null;
        Venta v = null;
        VentaDAO.getResumenGastosOnTable(tabla, v);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getResumenGastosCocinaOnTable method, of class VentaDAO.
     */
    @Test
    public void testGetResumenGastosCocinaOnTable() {
        System.out.println("getResumenGastosCocinaOnTable");
        JTable tabla = null;
        Venta v = null;
        Cocina c = null;
        VentaDAO.getResumenGastosCocinaOnTable(tabla, v, c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
