/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.almacen;

import java.awt.Window;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import restManager.persistencia.Almacen;
import restManager.resources.R;

/**
 *
 * @author Jorge
 */
public class AlmacenManageControllerTest {
    
    public AlmacenManageControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        R.PERIRSTENCE_UNIT_NAME = R.RESOURCE_BUNDLE.getString("unidad_persistencia_local");
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
     * Test of imprimirReporteParaCompras method, of class AlmacenManageController.
     */
    @Test
    public void testImprimirReporteParaCompras() {
        System.out.println("imprimirReporteParaCompras");
        Almacen a = null;
        AlmacenManageController instance = new AlmacenManageController(a);
        instance.imprimirReporteParaCompras(a);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
