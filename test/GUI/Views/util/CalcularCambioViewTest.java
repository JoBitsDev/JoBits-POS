/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views.util;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import restManager.persistencia.Orden;
import restManager.printservice.Impresion;
import static restManager.printservice.Impresion.DEFAULT_PRINT_LOCATION;
import restManager.printservice.Ticket;
import restManager.resources.R;

/**
 *
 * @author Jorge
 */
public class CalcularCambioViewTest {

    Orden o;

    public CalcularCambioViewTest() {
    }

    @Before
    public void setUp() {
        o = new Orden("O-1111");
        o.setOrdenvalorMonetario((float) 22);
        R.COIN_SUFFIX = " CUC";
    }

    /**
     * Test of init method, of class CalcularCambioView.
     */
    @Test
    public void testInit() {
        CalcularCambioView view = new CalcularCambioView(null, true, o);

    }

    @Ignore
    public void TestOpenDrawer() {
        Impresion.getDefaultInstance().forceDrawerKick();
    }

}
