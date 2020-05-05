/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.trabajadores;

import java.awt.Window;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import restManager.persistencia.Personal;
import restManager.resources.R;

/**
 *
 * @author Jorge
 */
public class PersonalListControllerTest {

    public PersonalListControllerTest() {
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
     * Test of constructView method, of class PersonalListController.
     */
    @Test
    public void testConstructView() {
        System.out.println("constructView");
      PersonalListController instance = new PersonalListController(null);

    }

}
