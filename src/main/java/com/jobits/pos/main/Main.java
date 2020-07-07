/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.main;

import com.jobits.pos.controller.configuracion.ConfiguracionController;
import java.util.Locale;
import com.jobits.pos.controller.login.LogInController;
import com.jobits.pos.persistencia.AsistenciaPersonal;
import com.jobits.pos.persistencia.Cocina;
import com.jobits.pos.recursos.DBConnector;
import com.jobits.pos.recursos.R;
import com.jobits.pos.servicios.impresion.Impresion;
import static com.jobits.pos.servicios.impresion.Impresion.DEFAULT_PRINT_LOCATION;
import static com.jobits.pos.servicios.impresion.Impresion.TipoImpresion.COCINA;
import com.jobits.pos.servicios.impresion.ImpresoraUseCase;
import com.jobits.pos.servicios.impresion.Ticket;
import com.jobits.pos.ui.utils.LoadingWindow;
import java.util.ArrayList;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class Main {

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(() -> {
            Locale.setDefault(Locale.Category.FORMAT, Locale.ENGLISH); // But the formatting in English
            LogInController loginController = new LogInController();
            loginController.constructView(null);
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
