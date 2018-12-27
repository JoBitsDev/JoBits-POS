/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.venta;


import java.util.Date;
import org.junit.BeforeClass;
import org.junit.Test;
import restManager.persistencia.models.VentaDAO;
import restManager.resources.R;

/**
 *
 * @author Jorge
 */
public class OrdenControllerTest {

    public OrdenControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        R.PERIRSTENCE_UNIT_NAME = R.RESOURCE_BUNDLE.getString("unidad_persistencia_local");
        R.coinSuffix = " CUC";
    }

    @Test
    public void test() {
        VentaDetailController controller = new VentaDetailController(VentaDAO.getInstance().findAll().get(0),null);
        controller.getView().setVisible(true);
    }

}
