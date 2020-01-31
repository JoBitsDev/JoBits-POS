/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.venta;

import java.io.File;
import javax.swing.JDialog;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import restManager.controller.configuracion.ConfiguracionController;
import restManager.persistencia.models.PersonalDAO;
import restManager.persistencia.models.VentaDAO;
import restManager.resources.R;

/**
 *
 * @author Jorge
 */
public class VentaDetailControllerTest {

    private VentaDetailController controller;

    public VentaDetailControllerTest() {
    }

    @Before
    public void setUp() {
        R.PERIRSTENCE_UNIT_NAME = "Servidor_Local";
        R.loggedUser = PersonalDAO.getInstance().find("admin");
        ConfiguracionController cont = new ConfiguracionController();
        cont.cargarConfiguracion();
        controller = new VentaDetailController();
    }

    @Ignore
    public void testVisualResumen() {
        controller = new VentaDetailController(VentaDAO.getInstance().find("2020-01-01"), new JDialog());
    }

    /**
     * Test of getOrdenesActivas method, of class VentaDetailController.
     */
    @Ignore
    public void testExportar() {
        controller = new VentaDetailController(VentaDAO.getInstance().findAll().get(0));
        assertTrue(controller.terminarYExportar());
        VentaDAO.getInstance().startTransaction();
        VentaDAO.getInstance().remove(VentaDAO.getInstance().findAll().get(0));
        VentaDAO.getInstance().commitTransaction();
        assertTrue(true);
    }

    /**
     * Test of fetchNewDataFromServer method, of class VentaDetailController.
     */
    @Test
    public void testImportar() {
        controller = new VentaDetailController(VentaDAO.getInstance().findAll().get(0));
        assertTrue(controller.importarVenta(new File("export.data")));

    }

}
