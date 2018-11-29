/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.printservice;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import restManager.persistencia.Cocina;
import restManager.persistencia.Insumo;
import restManager.persistencia.IpvRegistro;
import restManager.persistencia.Orden;
import restManager.persistencia.Personal;
import restManager.persistencia.ProductovOrden;
import restManager.persistencia.Venta;
import restManager.persistencia.jpa.staticContent;
import restManager.persistencia.models.OrdenDAO;
import restManager.resources.R;

/**
 *
 * @author Jorge
 */
public class ImpresionTest {
    
    private static Impresion instance;
    
    public ImpresionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
         R.PERIRSTENCE_UNIT_NAME = R.RESOURCE_BUNDLE.getString("unidad_persistencia_local");
         staticContent.init(R.PERIRSTENCE_UNIT_NAME);
          instance = Impresion.getDefaultInstance();
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
     * Test of print method, of class Impresion.
     */
    @Test
    public void testPrint() throws Exception {
        System.out.println("print");
        Orden o = new OrdenDAO().find("O-2529");
        boolean preview = true;
        //instance.forceBell();
      instance.print(o, preview);
       // print();
        // TODO review the generated test code and remove the default call to fail.
       
    }

    private void print() {
        Ticket t = new Ticket();
        Impresion i = new Impresion();
        
      
        
    }
    
    
    

   
}
