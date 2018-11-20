/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.trabajadores;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceBusinessLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceOfficeBlack2007LookAndFeel;
import static restManager.controller.trabajadores.PuestoTrabajoCreateEditControllerTest.p;
import restManager.persistencia.models.PuestoTrabajoDAO;
import restManager.resources.R;

/**
 *
 * @author Jorge
 */
public class PuestoTrabajoListControllerTest {
    
    public PuestoTrabajoListControllerTest() {
    }

    
    
    @BeforeClass
    public static void setUpClass() {
       
        R.PERIRSTENCE_UNIT_NAME = R.RESOURCE_BUNDLE.getString("unidad_persistencia_local");
        PuestoTrabajoDAO dataaccess = new PuestoTrabajoDAO();
       // p = dataaccess.findAll().get(1);
        
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
     * Test of constructView method, of class PuestoTrabajoListController.
     */
    @Test
    public void testConstructView() {
        
        try {
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(PuestoTrabajoListControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        PuestoTrabajoListController instance = new PuestoTrabajoListController(null);
    }
    
}
