/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.main;

import com.jobits.pos.cliente.core.module.ClienteCoreModule;
import com.jobits.pos.cliente.repo.module.ClienteRepoModule;
import com.jobits.pos.ui.utils.ConfigLoaderService;
import com.jobits.pos.ui.MainWindow;
import com.jobits.pos.controller.licencia.impl.LicenceController;
import com.jobits.pos.controller.login.AuthorizerHandler;
import com.jobits.pos.cordinator.CoordinatorService;
import com.jobits.pos.cordinator.DisplayType;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.core.domain.models.Personal;
import com.jobits.pos.core.module.PosCoreModule;
import com.jobits.pos.core.module.UserResolverImpl;
import com.root101.clean.core.app.services.utils.TipoNotificacion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.reserva.core.module.ReservaCoreModule;
import com.jobits.pos.reserva.repo.module.ReservaRepoModule;
import com.jobits.ui.swing.LongProcessActionService;
import com.jobits.pos.ui.autorizo.AuthorizerImpl;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.utils.ConfigLoaderController;
import com.jobits.pos.ui.utils.LongProcessActionServiceImpl;
import com.jobits.pos.ui.utils.PopUpDialog;
import com.jobits.ui.components.swing.notifications.NotificationHandler;
import com.jobits.ui.themes.ThemeHandler;
import com.jobits.ui.themes.ThemeType;
import com.jobits.ui.themes.impl.NimbusTheme;
import com.jobits.ui.themes.impl.MaterialTheme;
import com.jobits.ui.themes.impl.SimpleMaterialTheme;
import com.root101.clean.core.app.services.NotificationService;
import com.root101.clean.core.app.services.UserResolver;
import com.root101.clean.core.app.services.UserResolverService;
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
import javax.swing.JFrame;
import org.jobits.db.core.module.DataVersionControlModule;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class Application {

    public static String PROP_LOGGED_USER = "Logged User";
    public static final int MAJOR_VERSION = 3;
    public static final int MINOR_VERSION = 12;
    public static final int PATCH_VERSION = 1;
    public static String RELEASE_VERSION = "Version " + MAJOR_VERSION + "." + MINOR_VERSION + "." + PATCH_VERSION;
    //
    //Log
    //
    private static final String CURRENT_DATE = LocalDate.now().format(DateTimeFormatter.ofPattern("dd_MM_yyyy"));
    private static final String LOG_FILE_PATH = "LOGS/AppLogs/";
    private static final String ERR_FILE_PATH = "LOGS/AppLogsErr/";
    private static final String LOG_ERR_FILE_NAME = CURRENT_DATE + ".log";
    private static Application application;

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
    private UserResolverService<Personal> userResolver = new UserResolverImpl();

    //
    // Name
    //
    private final String APP_NAME = "JoBits POS";
    private LongProcessActionService backgroundWorker;

    private CoordinatorService coordinator;

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
    private NotificationService notificationService = new NotificationHandler();
    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    private Application() {
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
        DataVersionControlModule.init();
        //MODULO CLIENTE
        ClienteRepoModule.init(DataVersionControlModule.getInstance());
        ClienteCoreModule.init(ClienteRepoModule.getInstance());

        //MODULO RESERVA
        ReservaRepoModule.init(DataVersionControlModule.getInstance());
        ReservaCoreModule.init(ReservaRepoModule.getInstance());

        //MODULO POS-CORE
        PosCoreModule.init(DataVersionControlModule.getInstance());

        //MODULO DESKTOP-UI
        PosDesktopUiModule.init(
                PosCoreModule.getInstance(),
                ReservaCoreModule.getInstance(),
                ClienteCoreModule.getInstance());

    }

    private void registerResources() {
        AuthorizerHandler.registerAuthorizer(new AuthorizerImpl());
    }


    private void calculateLicenceLeft() {
    }

    private void setExceptionHandling() {
        Thread.setDefaultUncaughtExceptionHandler((Thread t, Throwable e) -> {
            getNotificationService().showDialog(e.getMessage(), TipoNotificacion.ERROR);
            com.jobits.pos.core.repo.impl.AbstractRepository.transactionErrorListener.propertyChange(new PropertyChangeEvent(this, "ERROR", 0, 1));
            e.printStackTrace();
        });
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
