/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.logs;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import restManager.logs.RestManagerHandler.Action;
import restManager.persistencia.Venta;

/**
 *
 * @author Jorge
 */
public class RestManagerHandlerTest {
    
    public RestManagerHandlerTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of Log method, of class RestManagerHandler.
     */
    @Test
    public void testLog() {
        System.out.println("Log");
        Class cls = Venta.class;
        String sms = "Deleting O-2345" ;
        Level lv = Level.WARNING;
        Action a = RestManagerHandler.Action.ENVIAR_COCINA;
        RestManagerHandler.Log(Logger.getLogger(cls.getName()),a,lv,sms);
    }
    
}
