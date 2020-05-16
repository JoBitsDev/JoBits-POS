/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.main;

import com.jobits.pos.controller.licencia.LicenceController;
import com.jobits.pos.cordinator.CoordinatorService;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.domain.models.Personal;
import com.jobits.pos.notification.NotificationService;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.RootView;
import com.jobits.ui.components.swing.notifications.NotificationHandler;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import ui.MaterialLookAndFeel;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class Application {

    //
    // Name
    //
    private final String APP_NAME = "APP";

    //
    // UI
    //
    private MainWindow mainWindow;

    private RootView rootView;

    private CoordinatorService coordinator;

    private NavigationService navigator;

    //
    // App
    //
    private LicenceController licenceController = new LicenceController();

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
        setNotificationChannel();
        calculateLicenceLeft();
        mainWindow = new MainWindow();
        //mainWindow.setTitle(APP_NAME);
        mainWindow.setWelcomeHeader(true);
        rootView = RootView.getInstance();
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainWindow.getContentPane().add(rootView);
        mainWindow.pack();
        navigator = NavigationService.getInstance();
        mainWindow.setVisible(true);

    }

    private void setApplicationLooks() {
        try {
            UIManager.setLookAndFeel(new MaterialLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void setLocale() {
        Locale.setDefault(Locale.Category.FORMAT, Locale.ENGLISH); // But the formatting in English
    }

    private void setNotificationChannel() {
        NotificationService.registerNotificationChannel(new NotificationHandler());
    }

    public Personal getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(Personal loggedUser) {
        this.loggedUser = loggedUser;
        R.loggedUser = loggedUser;
    }

    public LicenceController getLicenceController() {
        return licenceController;
    }

    private void calculateLicenceLeft() {
    }

}
