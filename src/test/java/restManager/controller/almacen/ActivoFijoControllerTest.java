/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.almacen;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import restManager.persistencia.models.PersonalDAO;
import restManager.resources.R;

/**
 *
 * @author Jorge
 */
public class ActivoFijoControllerTest {

    public ActivoFijoControllerTest() {
    }

    @Before
    public void setUp() {
        R.PERIRSTENCE_UNIT_NAME = R.RESOURCE_BUNDLE.getString("unidad_persistencia_local");
        R.COIN_SUFFIX = " CUC";
        R.loggedUser = PersonalDAO.getInstance().find("admin");
    }

    /**
     * Test of constructView method, of class ActivoFijoController.
     */
    @Test
    public void testConstructView() {
        ActivoFijoController controller = new ActivoFijoController();
        controller.constructView(null);
    }

}
