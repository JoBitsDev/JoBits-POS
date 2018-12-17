/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.venta;

import GUI.Views.venta.OrdenDetailFragmentView;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import org.junit.Before;
import org.junit.Test;
import restManager.persistencia.Orden;
import restManager.persistencia.models.OrdenDAO;

/**
 *
 * @author Jorge
 */
public class OrdenControllerTest {

    public OrdenControllerTest() {
    }

    @Before
    public void setUp() {
    }

    /**
     * Test of createNewInstance method, of class OrdenController.
     */
    @Test
    public void test() {
        System.out.println("testing");
        Orden o = OrdenDAO.getInstance().find("O-2762");
        OrdenController instance = new OrdenController(o);
        OrdenDetailFragmentView view = new OrdenDetailFragmentView(instance, o);
        JDialog dialog = new JDialog();
        dialog.add(view);
        JButton boton = new JButton("hola");
        boton.addActionListener((ActionEvent e) -> {
            dialog.dispose();
        });
        dialog.add(boton);
        dialog.pack();

        java.awt.EventQueue.invokeLater(() -> {
            dialog.setVisible(true);
        });

    }

}
