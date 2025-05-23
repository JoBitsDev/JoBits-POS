/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.main;

import com.jobits.pos.client.webconnection.exception.ServerErrorException;
import com.jobits.pos.controller.login.AuthorizerHandler;
import com.jobits.pos.cordinator.CoordinatorService;
import com.jobits.pos.cordinator.DisplayType;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.core.domain.models.Personal;
import com.jobits.pos.core.module.UserResolverImpl;
import com.jobits.pos.core.repo.impl.TransactionCounter;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.MainWindow;
import com.jobits.pos.ui.autorizo.AuthorizerImpl;
import com.jobits.pos.ui.login.ChangeUserView;
import com.jobits.pos.ui.login.presenter.ChangeUserViewPresenter;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.utils.ConfigLoaderController;
import com.jobits.pos.ui.utils.ConfigLoaderService;
import com.jobits.pos.ui.utils.LongProcessActionServiceImpl;
import com.jobits.pos.ui.utils.PopUpDialog;
import com.jobits.ui.components.swing.notifications.NotificationHandler;
import com.jobits.ui.swing.LongProcessActionService;
import com.jobits.ui.themes.ThemeHandler;
import com.jobits.ui.themes.ThemeType;
import com.jobits.ui.themes.impl.MaterialTheme;
import com.jobits.ui.themes.impl.NimbusTheme;
import com.jobits.ui.themes.impl.SimpleMaterialTheme;
import com.root101.clean.core.app.services.NotificationService;
import com.root101.clean.core.app.services.UserResolver;
import com.root101.clean.core.app.services.UserResolverService;
import com.root101.clean.core.app.services.utils.TipoNotificacion;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * JoBits
 *
 * @author Jorge
 */
public class Application {

    public static final int MAJOR_VERSION = 4;
    public static final int MINOR_VERSION = 0;
    public static final int PATCH_VERSION = 1;

    public static final String VERSION_NOTE = "(BETA)";
    //
    //Log
    //
    private static final String CURRENT_DATE = LocalDate.now().format(DateTimeFormatter.ofPattern("dd_MM_yyyy"));
    private static final String LOG_FILE_PATH = "LOGS/AppLogs/";
    private static final String ERR_FILE_PATH = "LOGS/AppLogsErr/";
    private static final String LOG_ERR_FILE_NAME = CURRENT_DATE + ".log";
    public static String PROP_LOGGED_USER = "Logged User";
    public static String RELEASE_VERSION = "Version " + MAJOR_VERSION + "." + MINOR_VERSION + "." + PATCH_VERSION + " " + VERSION_NOTE;
    private static Application application;
    //
    // Name
    //
    private final String APP_NAME = "JoBits POS";
    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private UserResolverService<Personal> userResolver = new UserResolverImpl();
    private LongProcessActionService backgroundWorker;
    private CoordinatorService coordinator;
    //
    // App
    //
    private Personal loggedUser;
    //
    // UI
    //
    private MainWindow mainWindow;
    private NavigationService navigator;
    private NotificationService notificationService = new NotificationHandler();

    private Application() {
    }

    //    public static Application createApplication() {
//        if (application == null) {
//            application = new Application();
//        }
//        return application;
//    }
    public static Application getInstance() {
        if (application == null) {
            application = new Application();
        }
        return application;
    }

    public static void setupLogging() throws Exception {
        File log_path = new File(LOG_FILE_PATH);
        File err_path = new File(ERR_FILE_PATH);
        if (!log_path.exists()) {
            log_path.mkdirs();
        }
        if (!err_path.exists()) {
            err_path.mkdirs();
        }
        File log_file = new File(LOG_FILE_PATH + LOG_ERR_FILE_NAME);
        File err_file = new File(ERR_FILE_PATH + LOG_ERR_FILE_NAME);
        if (!log_file.exists()) {
            log_file.createNewFile();
        }
        if (!err_file.exists()) {
            err_file.createNewFile();
        }
        PrintStream out = new PrintStream(
                new FileOutputStream(log_file, true), true);
        PrintStream error = new PrintStream(
                new FileOutputStream(err_file, true), true);
        System.setOut(out);
        System.setErr(error);

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

    public Personal getLoggedUser() {//TODO: implement userresolver
        return userResolver.resolveUser();
    }

    public void setLoggedUser(Personal loggedUser) {
        this.loggedUser = loggedUser;
        UserResolverImpl.setCurrentLoggedUser(loggedUser);
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
        setLocale();
        setNotificationChannel();
        calculateLicenceLeft();
        setExceptionHandling();
        registerResources();
        initModules();
        UserResolver.registerUserResolverService(userResolver);
        setupDebugMode();
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

    public void setTheme() {
        ConfigLoaderService service = new ConfigLoaderController();
        String themeName = service.getConfigValue("app.theme");
        if (themeName.equals(ThemeType.MATERIAL.getThemeName())) {
            ThemeHandler.registerThemeService(new MaterialTheme());
        } else if (themeName.equals(ThemeType.SIMPLE_MATERIAL.getThemeName())) {
            ThemeHandler.registerThemeService(new SimpleMaterialTheme());
//        } else if (themeName.equals(ThemeType.DARK_MATERIAL.getThemeName())) { TODO: en progreso tema oscuro
//            ThemeHandler.registerThemeService(new DarkMaterialTheme());
        } else {
            ThemeHandler.registerThemeService(new NimbusTheme());
        }
    }

    private void initModules() {
        // DataVersionControlModule.init();
        //MODULO CLIENTE
        //ClienteRepoModule.init(DataVersionControlModule.getInstance());
        //ClienteCoreModule.init(ClienteRepoModule.getInstance());

        //MODULO RESERVA
        //ReservaRepoModule.init(DataVersionControlModule.getInstance());
        //ReservaCoreModule.init(ReservaRepoModule.getInstance());

        //MODULO POS-CORE
        //  PosCoreModule.init(DataVersionControlModule.getInstance());

        //MODULO DESKTOP-UI
        PosDesktopUiModule.init();
        // PosCoreModule.getInstance(),
        //ReservaCoreModule.getInstance(),
        //ClienteCoreModule.getInstance());

    }

    private void registerResources() {
        AuthorizerHandler.registerAuthorizer(new AuthorizerImpl());
    }


    private void calculateLicenceLeft() {
    }

    private void setExceptionHandling() {
        Thread.setDefaultUncaughtExceptionHandler((Thread t, Throwable e) -> {
            if (checkIfIts401(e)) return;
            getNotificationService().showDialog(e.getMessage(), TipoNotificacion.ERROR);
            TransactionCounter.errorListener().propertyChange(new PropertyChangeEvent(this, "ERROR", 0, 1));
            e.printStackTrace();
        });
    }

    private boolean checkIfIts401(Throwable e) {
        if (e instanceof ServerErrorException) {
            ServerErrorException ex = (ServerErrorException) e;
            if (ex.getApiError().getStatus() == 401) {
                var presenter = new ChangeUserViewPresenter();
                presenter.addPropertyChangeListener(ChangeUserViewPresenter.PROP_LOGIN_FAILED, evt ->
                        getNotificationService().showDialog("Su sesión ha expirado. Por favor ingrese nuevamente.", TipoNotificacion.ERROR));
                Application.getInstance().getNavigator().navigateTo(
                        ChangeUserView.VIEW_NAME, presenter, DisplayType.POPUP);
                return true;
            }
        }
        return false;
    }

    private void setLocale() {
        Locale.setDefault(Locale.Category.FORMAT, Locale.ENGLISH); // But the formatting in English
    }

    private void setNotificationChannel() {
        //  NotificationService notificationService = new com.jobits.ui.components.swing.notifications.NotificationHandler();
    }

    private void setupDebugMode() {
        ConfigLoaderService service = new ConfigLoaderController();
        boolean debugMode = Boolean.valueOf(service.getConfigValue("app.debug"));
        if (!debugMode) {
            try {
                setupLogging();
            } catch (Exception ex) {
                Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
