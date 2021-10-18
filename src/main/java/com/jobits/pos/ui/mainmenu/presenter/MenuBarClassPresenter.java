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
import com.root101.clean.core.app.services.utils.TipoNotificacion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.RootView;
import com.jobits.pos.ui.autorizo.AuthorizerImpl;
import com.jobits.pos.ui.backup.BackUpView;
import com.jobits.pos.ui.backup.presenter.BackUpViewPresenter;
import com.jobits.pos.ui.configuracion.ConfiguracionView;
import com.jobits.pos.ui.configuracion.presenter.ConfigurationViewPresenter;
import com.jobits.pos.ui.licencia.LicenceDialogView;
import com.jobits.pos.ui.login.ChangeUserView;
import com.jobits.pos.ui.login.LogInView;
import com.jobits.pos.ui.login.UbicacionView;
import com.jobits.pos.ui.login.presenter.ChangeUserViewPresenter;
import com.jobits.pos.ui.login.presenter.LoginViewPresenter;
import com.jobits.pos.ui.login.presenter.UbicacionViewPresenter;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.reportes.ReportarBugView;
import com.jobits.pos.ui.reportes.presenter.ReportarBugViewPresenter;
import com.jobits.pos.ui.venta.VentaDetailView;
import static com.jobits.pos.ui.venta.presenter.VentaDetailViewPresenter.*;
import com.root101.clean.core.domain.services.ResourceHandler;
import java.util.Optional;
import javax.swing.JOptionPane;
import org.jobits.db.core.usecase.UbicacionConexionService;

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
    public static final String ACTION_REFRESCAR_VISTA = "Refrescar Vista";
    public static final String ACTION_OCULTAR_STATUS_BAR = "Ocultar Barra de Estado";
    public static final String ACTION_OCULTAR_MENU_LATERAL = "Ocultar Menu Lateral";
    public static final String ACTION_SIEMPRE_PRIMER_PLANO = "Siempre Primer Plano";
    //Venta
    public static final String ACTION_CAMBIAR_TURNO = "Cambiar Turno";
    public static final String ACTION_NUEVO_TURNO = "Nuevo Turno";
    public static final String ACTION_REABRIR_VENTA = "Reabrir Venta";
    public static final String ACTION_TERMINAR_VENTA = "Terminar Venta";
    public static final String ACTION_TERMINAR_EXPORTAR_VENTA = "Terminar y Exportar Venta";
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
                        ChangeUserView.VIEW_NAME, new ChangeUserViewPresenter(new LogInController()), DisplayType.POPUP);//TODO: inyectar
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_CERRAR_SESION) {
            @Override
            public Optional doAction() {
                Application.getInstance().setLoggedUser(null);
                RootView.getInstance().clearView();
                Application.getInstance().getNavigator().navigateTo(
                        LogInView.VIEW_NAME, new LoginViewPresenter(new LogInController()), DisplayType.POPUP);
                Application.getInstance().getNotificationService().showDialog("Sesion Cerrada", TipoNotificacion.INFO);
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_SALIR) {
            @Override
            public Optional doAction() {
                Object[] options = {"Si", "No"};
                int confirm = JOptionPane.showOptionDialog(
                        Application.getInstance().getMainWindow(),
                        ResourceHandler.getString("label_dialogo_salir_sistema"),
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

        registerOperation(new AbstractViewAction(ACTION_REFRESCAR_VISTA) {
            @Override
            public Optional doAction() {
                RootView.getInstance().getCurrentView().getPresenter()
                        .getOperation(AbstractViewPresenter.ACTION_REFRESH_STATE).doAction();
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
        registerOperation(new AbstractViewAction(ACTION_NUEVO_TURNO) {
            @Override
            public Optional doAction() {
                executeVentaAction(ACTION_CREAR_NUEVO_TURNO);
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_CAMBIAR_TURNO) {
            @Override
            public Optional doAction() {
                executeVentaAction(ACTION_CAMBIAR_TURNO);
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_REABRIR_VENTA) {
            @Override
            public Optional doAction() {
                executeVentaAction(ACTION_REABRIR_VENTA);
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_TERMINAR_VENTA) {
            @Override
            public Optional doAction() {
                executeVentaAction(ACTION_TERMINAR_VENTAS);
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_TERMINAR_EXPORTAR_VENTA) {
            @Override
            public Optional doAction() {
                executeVentaAction(ACTION_TERMINAR_EXPORTAR);
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

    private void executeVentaAction(String operationName) {
        String currentViewName = RootView.getInstance().getCurrentViewName(null);
        if (currentViewName != null) {
            if (currentViewName.equals(VentaDetailView.VIEW_NAME)) {
                VentaDetailView vdv = (VentaDetailView) RootView.getInstance().getCurrentView();
                vdv.getPresenter().getOperation(operationName).doAction();
            }
        }
    }

}
