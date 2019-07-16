/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views.util;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import restManager.persistencia.Orden;
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
        o.setOrdenvalorMonetario((float)22);
        R.COIN_SUFFIX = " CUC";
    }

    /**
     * Test of init method, of class CalcularCambioView.
     */
    @Test
    public void testInit() {
        CalcularCambioView view = new CalcularCambioView(null, true, o);
        
        
    }
    
}
