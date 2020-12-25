/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.main;

import com.jobits.pos.ui.MainWindow;
import com.jobits.pos.controller.licencia.LicenceController;
import com.jobits.pos.cordinator.CoordinatorService;
import com.jobits.pos.cordinator.DisplayType;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.domain.models.Personal;
import com.jobits.pos.notification.NotificationService;
import com.jobits.pos.notification.TipoNotificacion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.LongProcessActionService;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.utils.LongProcessActionServiceImpl;
import com.jobits.pos.ui.utils.PopUpDialog;
import com.jobits.ui.components.MaterialComponentsFactory;
import com.jobits.ui.components.swing.notifications.NotificationHandler;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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
    private final String APP_NAME = "JoBits POS";

    //
    // UI
    //
    private MainWindow mainWindow;

    private CoordinatorService coordinator;

    private NavigationService navigator;

    private LongProcessActionService backgroundWorker;

    private NotificationService notificationService = NotificationService.getInstance();

    //
    //Log
    //
    private static String LOG_FILE_PATH = "LOGS/AppLogs";
    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    //
    // App
    //
    private LicenceController licenceController = new LicenceController();

    private Personal loggedUser;

    public static String PROP_LOGGED_USER = "Logged User";

    private static Application application;

    private boolean debugMode = true;

    private Application() {
    }

    private Application(boolean debugMode) {
        this.debugMode = debugMode;
    }

    public static Application getInstance() {
        if (application == null) {
            application = new Application();
        }
        return application;
    }

    public static Application createApplication(boolean debugMode) {
        if (application == null) {
            application = new Application(debugMode);
        }
        return application;
    }

    public boolean authorizeUser(R.NivelAcceso nivelAcceso) {
        if (getLoggedUser().getPuestoTrabajonombrePuesto().getNivelAcceso() == nivelAcceso.getNivel()) {
            getNotificationService().showDialog("No posees permisos para acceder a este recurso", TipoNotificacion.ERROR);
            throw new IllegalAccessError("Acceso denegado.");
        }
        return true;

    }

    public void start() throws Exception {
        if (!debugMode) {
            setupLogging();
        }
        setLocale();
        setApplicationLooks();
        setNotificationChannel();
        calculateLicenceLeft();
        mainWindow = new MainWindow();
        mainWindow.setTitle(APP_NAME);
        mainWindow.setWelcomeHeader(true);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainWindow.pack();
        navigator = NavigationService.getInstance();
        backgroundWorker = LongProcessActionServiceImpl.getInstance();
        mainWindow.setVisible(true);
        navigator.startNavigation();

    }

    private void setApplicationLooks() {
        try {
            UIManager.setLookAndFeel(MaterialComponentsFactory.UI.getLooks());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void setLocale() {
        Locale.setDefault(Locale.Category.FORMAT, Locale.ENGLISH); // But the formatting in English
    }

    private void setNotificationChannel() {
        notificationService.registerNotificationChannel(new NotificationHandler());
    }

    public Personal getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(Personal loggedUser) {
        this.loggedUser = loggedUser;
        R.loggedUser = loggedUser;
        propertyChangeSupport.firePropertyChange(PROP_LOGGED_USER, null, this.loggedUser);
    }

    public LicenceController getLicenceController() {
        return licenceController;
    }

    private void calculateLicenceLeft() {
    }

    public MainWindow getMainWindow() {
        return mainWindow;
    }

    public CoordinatorService getCoordinator() {
        return coordinator;
    }

    public NavigationService getNavigator() {
        return navigator;
    }

    public LongProcessActionService getBackgroundWorker() {
        return backgroundWorker;
    }

    public NotificationService getNotificationService() {
        return notificationService;
    }

    public boolean showView(String viewUIDName, AbstractViewPresenter presenter, DisplayType displayType) {
        PopUpDialog.disposeCurrentDisplayingPopUp();
        return mainWindow.showView(viewUIDName, presenter, displayType);
    }

    public static void setupLogging() throws Exception {
        PrintStream out = new PrintStream(
                new FileOutputStream(LOG_FILE_PATH, false), true);
        System.setOut(out);
        System.setErr(out);

    }

    /**
     * Add PropertyChangeListener.
     *
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Remove PropertyChangeListener.
     *
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

}
