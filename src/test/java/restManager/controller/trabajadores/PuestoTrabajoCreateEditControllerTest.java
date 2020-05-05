/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.trabajadores;

import java.awt.Window;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import restManager.persistencia.PuestoTrabajo;
import restManager.persistencia.models.PuestoTrabajoDAO;
import restManager.resources.R;

/**
 *
 * @author Jorge
 */
public class PuestoTrabajoCreateEditControllerTest {

    static PuestoTrabajoCreateEditController instance;
    static PuestoTrabajo p;

    public PuestoTrabajoCreateEditControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
       
        R.PERIRSTENCE_UNIT_NAME = R.RESOURCE_BUNDLE.getString("unidad_persistencia_local");
        PuestoTrabajoDAO dataaccess = PuestoTrabajoDAO.getInstance();
        p = dataaccess.findAll().get(1);
        
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
     * Test of constructView method, of class PuestoTrabajoCreateEditController.
     */
    @Test
    public void testConstructView() {
        System.out.println("createInstance");
        Window parent = null;
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(PuestoTrabajoCreateEditControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        instance = new PuestoTrabajoCreateEditController(parent);
        
    }


}
