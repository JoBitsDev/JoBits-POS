/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.mainmenu.presenter;

import com.jobits.pos.controller.login.LogInService;
import com.jobits.pos.ui.about.AcercaDeViewPresenter;
import com.jobits.pos.ui.about.AcercaDeView;
import com.jobits.pos.controller.login.impl.LogInController;
import com.jobits.pos.cordinator.DisplayType;
import com.jobits.pos.main.Application;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.RootView;
import com.jobits.pos.ui.autorizo.AuthorizerImpl;
import com.jobits.pos.ui.backup.BackUpView;
import com.jobits.pos.ui.backup.presenter.BackUpViewPresenter;
import com.jobits.pos.ui.configuracion.ConfiguracionView;
import com.jobits.pos.ui.configuracion.presenter.ConfigurationViewPresenter;
import com.jobits.pos.ui.licencia.LicenceDialogView;
import com.jobits.pos.ui.login.LogInView;
import com.jobits.pos.ui.login.UbicacionView;
import com.jobits.pos.ui.login.presenter.LoginViewPresenter;
import com.jobits.pos.ui.login.presenter.UbicacionViewPresenter;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.reportes.ReportarBugView;
import com.jobits.pos.ui.reportes.presenter.ReportarBugViewPresenter;
import java.util.Optional;
import javax.swing.JOptionPane;
import org.jobits.app.repo.UbicacionConexionService;

/**
 *
 * @author Home
 */
public class MenuBarClassPresenter extends AbstractViewPresenter<MenuBarClassViewModel> {

    //JoBits POS
    public static final String ACTION_SHOW_ACERCA_DE = "Acerca De";
    public static final String ACTION_SHOW_PREFERENCIAS = "Preferencias";
    public static final String ACTION_SHOW_REPORTAR_BUG = "Reportar Bug";
    public static final String ACTION_SALIR = "Salir";
    public static final String ACTION_SHOW_LOGIN = "Cambiar Usuario";
    public static final String ACTION_CERRAR_SESION = "Cerrar Sesion";
    //Vista
    public static final String ACTION_OCULTAR_STATUS_BAR = "Ocultar Barra de Estado";
    public static final String ACTION_OCULTAR_MENU_LATERAL = "Ocultar Menu Lateral";
    public static final String ACTION_SIEMPRE_PRIMER_PLANO = "Siempre Primer Plano";
    //Herramientas
    public static final String ACTION_SHOW_COPIAS_SEGURIDAD = "Copias de Seguridad";
    public static final String ACTION_SHOW_UBICACIONES = "Ubicaciones";
    public static final String ACTION_SHOW_ACTIVAR_LICENCIA = "Activar Licencia";
    //Ayuda
    public static final String ACTION_SHOW_MANUAL_USUARIO = "Manual de Usuario";

    LogInService loginService = PosDesktopUiModule.getInstance().getImplementation(LogInService.class);

    public MenuBarClassPresenter() {
        super(new MenuBarClassViewModel());
    }

    @Override
    protected void registerOperations() {

        registerOperation(new AbstractViewAction(ACTION_SHOW_ACERCA_DE) {
            @Override
            public Optional doAction() {
                Application.getInstance().getNavigator().navigateTo(
                        AcercaDeView.VIEW_NAME, new AcercaDeViewPresenter(), DisplayType.POPUP);
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_SHOW_PREFERENCIAS) {
            @Override
            public Optional doAction() {
                if (loginService.autorizarCurrentUser(R.NivelAcceso.ADMINISTRADOR)) {
                    Application.getInstance().getNavigator().navigateTo(
                            ConfiguracionView.VIEW_NAME, new ConfigurationViewPresenter(), DisplayType.POPUP);
                }
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_SHOW_REPORTAR_BUG) {
            @Override
            public Optional doAction() {
                Application.getInstance().getNavigator().navigateTo(
                        ReportarBugView.VIEW_NAME, new ReportarBugViewPresenter(), DisplayType.POPUP);
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_SHOW_LOGIN) {
            @Override
            public Optional doAction() {
                Application.getInstance().getNavigator().navigateTo(
                        LogInView.VIEW_NAME, new LoginViewPresenter(new LogInController(new AuthorizerImpl())), DisplayType.POPUP);
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_CERRAR_SESION) {
            @Override
            public Optional doAction() {
                JOptionPane.showMessageDialog(Application.getInstance().getMainWindow(), "En Desarrollo");
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_SALIR) {
            @Override
            public Optional doAction() {
                Object[] options = {"Si", "No"};
                int confirm = JOptionPane.showOptionDialog(
                        Application.getInstance().getMainWindow(),
                        R.RESOURCE_BUNDLE.getString("label_dialogo_salir_sistema"),
                        null,
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);

                if (confirm == JOptionPane.YES_OPTION) {
                    Application.getInstance().getMainWindow().dispose();
                }
                return Optional.empty();
            }
        });

        registerOperation(new AbstractViewAction(ACTION_OCULTAR_STATUS_BAR) {
            @Override
            public Optional doAction() {
                boolean a = !RootView.getInstance().isStatusBarVisibility();
                RootView.getInstance().getjPanelStatus().setVisible(a);
                RootView.getInstance().setStatusBarVisibility(a);
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_OCULTAR_MENU_LATERAL) {
            @Override
            public Optional doAction() {
                RootView.getInstance().getDashboard().getTaskPane().getComponent(1).setVisible(
                        !RootView.getInstance().getDashboard().getTaskPane().getComponent(1).isVisible());
                //TODO: pedir el nombre del componente, no el componente por indice
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_SIEMPRE_PRIMER_PLANO) {
            @Override
            public Optional doAction() {
                Application.getInstance().getMainWindow().setAlwaysOnTop(
                        !Application.getInstance().getMainWindow().isAlwaysOnTop());
                return Optional.empty();
            }
        });

        registerOperation(new AbstractViewAction(ACTION_SHOW_COPIAS_SEGURIDAD) {
            @Override
            public Optional doAction() {
                Application.getInstance().getNavigator().navigateTo(
                        BackUpView.VIEW_NAME, new BackUpViewPresenter(), DisplayType.POPUP);
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_SHOW_UBICACIONES) {
            @Override
            public Optional doAction() {
                Application.getInstance().getNavigator().navigateTo(
                        UbicacionView.VIEW_NAME, new UbicacionViewPresenter(PosDesktopUiModule.getInstance().getImplementation(UbicacionConexionService.class)), DisplayType.POPUP);
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_SHOW_ACTIVAR_LICENCIA) {
            @Override
            public Optional doAction() {
                Application.getInstance().getNavigator().navigateTo(
                        LicenceDialogView.VIEW_NAME, DisplayType.POPUP);
                return Optional.empty();
            }
        });

        registerOperation(new AbstractViewAction(ACTION_SHOW_MANUAL_USUARIO) {
            @Override
            public Optional doAction() {
                JOptionPane.showMessageDialog(Application.getInstance().getMainWindow(), "En Desarrollo");
                return Optional.empty();
            }
        });

    }

}
