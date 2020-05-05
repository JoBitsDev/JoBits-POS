/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.almacen;

import java.awt.Frame;
import javax.swing.JDialog;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jorge
 */
public class AlmacenListControllerTest {
    
    public AlmacenListControllerTest() {
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
     * Test of createNewStorage method, of class AlmacenListController.
     */
    @Test
    public void testCreateNewStorage() {
        System.out.println("createNewStorage");
        AlmacenListController instance = null;
        instance = new AlmacenListController();
        instance.constructView(null);
    }
    
}
