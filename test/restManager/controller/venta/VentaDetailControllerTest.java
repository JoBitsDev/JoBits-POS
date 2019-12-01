/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.venta;

import java.awt.Container;
import javax.swing.JDialog;
import javax.swing.JFrame;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import restManager.controller.configuracion.ConfiguracionController;
import restManager.persistencia.Orden;
import restManager.persistencia.Venta;
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

    @Test
    public void testVisualResumen() {
        controller = new VentaDetailController(VentaDAO.getInstance().findAll().get(0), new JDialog());
    }

    /**
     * Test of getOrdenesActivas method, of class VentaDetailController.
     */
    @Test
    public void testGetOrdenesActivas() {
    }

    /**
     * Test of fetchNewDataFromServer method, of class VentaDetailController.
     */
    @Test
    public void testFetchNewDataFromServer() {
    }

    /**
     * Test of terminarVentas method, of class VentaDetailController.
     */
    @Test
    public void testTerminarVentas() {
    }

    /**
     * Test of calcularCambio method, of class VentaDetailController.
     */
    @Test
    public void testCalcularCambio() {
    }

    /**
     * Test of getDiaDeVenta method, of class VentaDetailController.
     */
    @Test
    public void testGetDiaDeVenta() {
    }

    /**
     * Test of getTotalVendido method, of class VentaDetailController.
     */
    @Test
    public void testGetTotalVendido() {
    }

    /**
     * Test of getTotalGastadoInsumos method, of class VentaDetailController.
     */
    @Test
    public void testGetTotalGastadoInsumos() {
    }

    /**
     * Test of getTotalPagoTrabajadores method, of class VentaDetailController.
     */
    @Test
    public void testGetTotalPagoTrabajadores() {
    }

    /**
     * Test of getPersonalList method, of class VentaDetailController.
     */
    @Test
    public void testGetPersonalList() {
    }

    /**
     * Test of getCocinaList method, of class VentaDetailController.
     */
    @Test
    public void testGetCocinaList() {
    }

    /**
     * Test of printPersonalResumenRow method, of class VentaDetailController.
     */
    @Test
    public void testPrintPersonalResumenRow() {
    }

    /**
     * Test of printZ method, of class VentaDetailController.
     */
    @Test
    public void testPrintZ() {
    }

    /**
     * Test of printCocinaResumen method, of class VentaDetailController.
     */
    @Test
    public void testPrintCocinaResumen() {
    }

    /**
     * Test of getGastoTotalDeInsumo method, of class VentaDetailController.
     */
    @Test
    public void testGetGastoTotalDeInsumo() {
    }

    /**
     * Test of printGastosCasa method, of class VentaDetailController.
     */
    @Test
    public void testPrintGastosCasa() {
    }

    /**
     * Test of cerrarOrdenRapido method, of class VentaDetailController.
     */
    @Test
    public void testCerrarOrdenRapido() {
    }

    /**
     * Test of getTotalVendidoNeto method, of class VentaDetailController.
     */
    @Test
    public void testGetTotalVendidoNeto() {
    }

    /**
     * Test of getInstance method, of class VentaDetailController.
     */
    @Test
    public void testGetInstance() {
    }

    /**
     * Test of cambiarTurno method, of class VentaDetailController.
     */
    @Test
    public void testCambiarTurno() {
    }

    /**
     * Test of printPagoPorVentaPersonal method, of class VentaDetailController.
     */
    @Test
    public void testPrintPagoPorVentaPersonal() {
    }

    /**
     * Test of mostrarVenta method, of class VentaDetailController.
     */
    @Test
    public void testMostrarVenta() {
    }

    /**
     * Test of getPagoTrabajador method, of class VentaDetailController.
     */
    @Test
    public void testGetPagoTrabajador() {
    }

    /**
     * Test of getTotalAutorizos method, of class VentaDetailController.
     */
    @Test
    public void testGetTotalAutorizos() {
    }

    /**
     * Test of getAreaList method, of class VentaDetailController.
     */
    @Test
    public void testGetAreaList() {
    }

    /**
     * Test of printAreaResumen method, of class VentaDetailController.
     */
    @Test
    public void testPrintAreaResumen() {
    }

    /**
     * Test of setPropina method, of class VentaDetailController.
     */
    @Test
    public void testSetPropina() {
    }

    /**
     * Test of getPropinaTrabajador method, of class VentaDetailController.
     */
    @Test
    public void testGetPropinaTrabajador() {
    }

}
