/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.insumo;

import GUI.Views.AbstractView;
import com.jidesoft.dialog.JideOptionPane;
import com.jidesoft.swing.JideSwingUtilities;
import com.jidesoft.utils.LoggerUtils;
import java.awt.Window;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import restManager.persistencia.Almacen;
import restManager.persistencia.Insumo;
import restManager.resources.R;

/**
 *
 * @author Jorge
 */
public class InsumoListControllerTest {
    
    public InsumoListControllerTest() {
        
    }
    /**
     * Test of createInstance method, of class InsumoListController.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        //JideOptionPane.showMessageDialog(null, "Prueba");
        InsumoListController instance = new InsumoListController(null);
        
      
    }

    
}
