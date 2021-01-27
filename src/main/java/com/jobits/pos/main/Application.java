/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.main;

import com.jobits.pos.ui.MainWindow;
import com.jobits.pos.controller.licencia.LicenceController;
import com.jobits.pos.controller.login.UbicacionConexionController;
import com.jobits.pos.cordinator.CoordinatorService;
import com.jobits.pos.cordinator.DisplayType;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.core.domain.models.Personal;
import com.jobits.pos.core.module.PosCoreModule;
import com.jobits.pos.notification.NotificationService;
import com.jobits.pos.notification.TipoNotificacion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.reserva.core.module.ReservaCoreModule;
import com.jobits.pos.reserva.repo.module.ReservaRepoModule;
import com.jobits.pos.ui.LongProcessActionService;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.utils.LongProcessActionServiceImpl;
import com.jobits.pos.ui.utils.PopUpDialog;
import com.jobits.pos.utils.UbicacionResourceServiceImpl;
import com.jobits.ui.components.MaterialComponentsFactory;
import com.jobits.ui.components.swing.notifications.NotificationHandler;
import com.root101.clean.core.app.services.UserResolver;
import com.root101.clean.core.app.services.UserResolverService;
import com.root101.clean.core.domain.services.ResourceHandler;
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

    public static String PROP_LOGGED_USER = "Logged User";
    //
    //Log
    //
    private static final String LOG_FILE_PATH = "LOGS/AppLogs";
    private static final String ERR_FILE_PATH = "LOGS/AppLogsErr";
    private static Application application;
    private UserResolverService<Personal> userResolver = new UserResolverServiceImpl();

    public static Application createApplication(boolean debugMode) {
        if (application == null) {
            application = new Application(debugMode);
        }
        return application;
    }

    public static Application getInstance() {
        if (application == null) {
            application = new Application();
        }
        return application;
    }

    public static void setupLogging() throws Exception {
        PrintStream out = new PrintStream(
                new FileOutputStream(LOG_FILE_PATH, false), true);
        PrintStream err = new PrintStream(
                new FileOutputStream(ERR_FILE_PATH, false), true);
        System.setOut(out);
        System.setErr(err);

    }

    //
    // Name
    //
    private final String APP_NAME = "JoBits POS";
    private LongProcessActionService backgroundWorker;

    private CoordinatorService coordinator;
    private boolean debugMode = true;

    //
    // App
    //
    private LicenceController licenceController = new LicenceController();

    private Personal loggedUser;
    //
    // UI
    //
    private MainWindow mainWindow;
    private NavigationService navigator;
    private NotificationService notificationService = NotificationService.getInstance();
    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    private Application() {
    }

    private Application(boolean debugMode) {
        this.debugMode = debugMode;
    }

    /**
     * Add PropertyChangeListener.
     *
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public boolean authorizeUser(R.NivelAcceso nivelAcceso) {
        if (getLoggedUser().getPuestoTrabajonombrePuesto().getNivelAcceso() == nivelAcceso.getNivel()) {
            getNotificationService().showDialog("No posees permisos para acceder a este recurso", TipoNotificacion.ERROR);
            throw new IllegalAccessError("Acceso denegado.");
        }
        return true;

    }

    public LongProcessActionService getBackgroundWorker() {
        return backgroundWorker;
    }

    public CoordinatorService getCoordinator() {
        return coordinator;
    }

    public LicenceController getLicenceController() {
        return licenceController;
    }

    public Personal getLoggedUser() {
        return userResolver.resolveUser();
    }

    public void setLoggedUser(Personal loggedUser) {
        this.loggedUser = loggedUser;
        R.loggedUser = loggedUser;
        propertyChangeSupport.firePropertyChange(PROP_LOGGED_USER, null, this.loggedUser);
    }

    public MainWindow getMainWindow() {
        return mainWindow;
    }

    public NavigationService getNavigator() {
        return navigator;
    }

    public NotificationService getNotificationService() {
        return notificationService;
    }

    /**
     * Remove PropertyChangeListener.
     *
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public boolean showView(String viewUIDName, AbstractViewPresenter presenter, DisplayType displayType) {
        PopUpDialog.disposeCurrentDisplayingPopUp();
        return mainWindow.showView(viewUIDName, presenter, displayType);
    }

    public void init() {
        if (!debugMode) {
            try {
                setupLogging();
            } catch (Exception ex) {
                Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        setLocale();
        setApplicationLooks();
        setNotificationChannel();
        calculateLicenceLeft();
        setExceptionHandling();
        registerResources();
        initModules();
        UserResolver.registerUserResolverService(userResolver);
    }

    public void start() {
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

    private void initModules() {
        ReservaRepoModule.init();
        ReservaCoreModule.init(ReservaRepoModule.getInstance());
        PosCoreModule.init(null);
        PosDesktopUiModule.init(
                ReservaCoreModule.getInstance(),
                PosCoreModule.getInstance());
    }

    private void registerResources() {
        ResourceHandler.registerResourceService(new UbicacionResourceServiceImpl(new UbicacionConexionController()));
    }

    private void calculateLicenceLeft() {
    }

    private void setApplicationLooks() {
        try {
            UIManager.setLookAndFeel(MaterialComponentsFactory.UI.getLooks());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void setExceptionHandling() {
        Thread.setDefaultUncaughtExceptionHandler((Thread t, Throwable e) -> {
            getNotificationService().showDialog(e.getMessage(), TipoNotificacion.ERROR);
            e.printStackTrace();
        });
    }

    private void setLocale() {
        Locale.setDefault(Locale.Category.FORMAT, Locale.ENGLISH); // But the formatting in English
    }

    private void setNotificationChannel() {
        notificationService.registerNotificationChannel(new NotificationHandler());
    }

}
