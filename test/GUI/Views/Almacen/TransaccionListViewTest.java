/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views.Almacen;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import restManager.controller.almacen.TransaccionesListController;
import restManager.persistencia.models.AlmacenDAO;

/**
 *
 * @author Jorge
 */
public class TransaccionListViewTest {
    
    public TransaccionListViewTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of generateTableModel method, of class TransaccionListView.
     */
    @Test
    public void testGenerateTableModel() {
    }

    /**
     * Test of updateView method, of class TransaccionListView.
     */
    @Test
    public void testUpdateView() {
        new TransaccionesListController(null,
        AlmacenDAO.getInstance().findAll().get(0));
    }
    
}
