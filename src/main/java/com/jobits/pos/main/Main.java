/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.main;

import com.jobits.pos.controller.configuracion.ConfiguracionController;
import java.util.Locale;
import com.jobits.pos.controller.login.LogInController;
import com.jobits.pos.ui.RootView;
import com.jobits.pos.ui.login.LogInView;
import com.jobits.pos.ui.login.presenter.LoginViewPresenter;
import com.jobits.pos.ui.utils.LoadingWindow;
import java.awt.Window;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class Main {

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(() -> {
            Application app = Application.createApplication(true);
            try {
                app.start();
            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
       
       /*
        
        DBConnector dbconnect = new DBConnector(null);
        ConfiguracionController config = new ConfiguracionController();
        config.cargarConfiguracion();
        
        
        ImpresoraUseCase impUC = new ImpresoraUseCase();
        Impresion imp = new Impresion();
        Ticket t = new Ticket();
        
        impUC.AddNewPrinter("Test1", new ArrayList<Cocina>(), true);
        
        imp.addHeader(t);

        t.addLineSeperator();
        t.newLine();
        t.alignCenter();
        t.setText("HOLA MUNDO");
        t.newLine();
        t.alignRight();
        t.addLineSeperator();
        t.newLine();

        imp.addFinal(t);

        imp.feedPrinter(t.finalCommandSet().getBytes(),null,null);*/
       
       
    }

}
