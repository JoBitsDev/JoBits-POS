/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.main;

import com.jobits.pos.cordinator.MainCoordinator;
import com.jobits.pos.domain.models.Personal;
import com.jobits.pos.ui.RootView;
import com.jobits.pos.ui.View;
import java.util.HashMap;
import java.util.Locale;
import javax.swing.JFrame;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class Application {

    private final String APP_NAME = "JoBits POS";

    private MainWindow mainWindow;

    private RootView rootView;

    private MainCoordinator coordinator;

    private Personal loggedUser;

    private static Application application;

    private Application() {
    }

    public static Application getInstance() {
        if (application == null) {
            application = new Application();
        }
        return application;
    }

    public void start() {
        setLocale();
        setApplicationLooks();
        mainWindow = new MainWindow();
        mainWindow.setTitle(APP_NAME);
        mainWindow.setWelcomeHeader(true);
        rootView = RootView.getInstance();
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainWindow.getContentPane().add(rootView);
        mainWindow.pack();
        mainWindow.setVisible(true);
        
        rootView.showDefaultView();
    }

    private void setApplicationLooks() {

    }

    private void setLocale() {
        Locale.setDefault(Locale.Category.FORMAT, Locale.ENGLISH); // But the formatting in English

    }

}
