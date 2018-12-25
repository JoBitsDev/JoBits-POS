/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import restManager.controller.venta.OrdenControllerTest;
import restManager.resources.R;

/**
 *
 * @author Jorge
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({OrdenControllerTest.class})
public class TestSuiteInit {

    @BeforeClass
    public static void setUpClass() throws Exception {
        R.PERIRSTENCE_UNIT_NAME = R.RESOURCE_BUNDLE.getString("unidad_persistencia_local");
        R.coinSuffix = " CUC";
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
