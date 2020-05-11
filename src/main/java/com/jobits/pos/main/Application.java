/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.main;

import com.jobits.pos.cordinator.MainCoordinator;
import com.jobits.pos.cordinator.MainNavigator;
import com.jobits.pos.domain.models.Personal;
import com.jobits.pos.notification.NotificationService;
import com.jobits.pos.ui.RootView;
import com.jobits.pos.ui.View;
import com.jobits.pos.ui.utils.utils;
import com.jobits.ui.components.swing.notifications.NotificationHandler;
import java.util.HashMap;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import ui.MaterialLookAndFeel;
import util.Utils;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class Application {

    private final String APP_NAME = "APP";

    private MainWindow mainWindow;

    private RootView rootView;

    private MainCoordinator coordinator;

    private MainNavigator navigator;

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
        mainWindow = new MainWindow();
        //mainWindow.setTitle(APP_NAME);
        mainWindow.setWelcomeHeader(true);
        rootView = RootView.getInstance();
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainWindow.getContentPane().add(rootView);
        mainWindow.pack();
        navigator = MainNavigator.getInstance();
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
    }

}
