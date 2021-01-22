/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.mainmenu.presenter;

import com.jobits.pos.controller.login.LogInController;
import com.jobits.pos.controller.login.MainMenuController;
import com.jobits.pos.controller.login.MainMenuService;
import com.jobits.pos.controller.venta.OrdenService;
import com.jobits.pos.controller.venta.VentaDetailService;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.TipoNotificacion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.autorizo.AuthorizerImpl;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.utils.LongProcessActionServiceImpl;
import com.jobits.pos.ui.venta.VentaDetailView;
import com.jobits.pos.ui.venta.presenter.VentaDetailViewPresenter;
import java.text.ParseException;
import java.util.Optional;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class MainMenuPresenter extends AbstractViewPresenter<MainMenuViewModel> {

    MainMenuService service;
    private int nivelDeAccesoAutenticado = 0;

    public MainMenuPresenter(MainMenuService service) {
        super(new MainMenuViewModel());
        this.service = service;
        nivelDeAccesoAutenticado = Application.getInstance().getLoggedUser()
                .getPuestoTrabajonombrePuesto().getNivelAcceso();

    }

    @Override
    protected void registerOperations() {
        for (MainMenuController.MenuButtons v : MainMenuController.MenuButtons.values()) {
            if (v == MainMenuController.MenuButtons.COMENZAR_VENTAS) {
                registerOperation(onComenzarVentaClick(v.toString()));
                continue;
            }
            registerOperation(onMenuClick(v.toString(), R.NivelAcceso.values()[v.getNivelMinimoAcceso()]));

        }
    }

    private AbstractViewAction onComenzarVentaClick(String actionName) {
        return new LicencedViewAction(actionName) {
            @Override
            public Optional doAction() {
                refreshState();
                if (nivelDeAccesoAutenticado >= R.NivelAcceso.ECONOMICO.getNivel()) {
                    new LongProcessActionServiceImpl("Creando IPVs.........") {
                        @Override
                        protected void longProcessMethod() {
                            try {
                                Optional<String> ret = Optional.empty();
                                ret = Application.getInstance().getNotificationService().
                                        showDialog("Introduzca el dia para abrir las ventas en el formato dd/mm/aa",
                                                TipoNotificacion.DIALOG_INPUT);
                                VentaDetailService control = null;
                                control = service.comenzarVentasEconomico(R.DATE_FORMAT.parse(ret.get()));
                                Application.getInstance().getNavigator().navigateTo(VentaDetailView.VIEW_NAME,
                                        new VentaDetailViewPresenter(control, PosDesktopUiModule.getInstance().getImplementation(OrdenService.class), service.getDiaVentaSeleccionado()));
                            } catch (ParseException ex) {
                                throw new IllegalArgumentException("Formato incorrecto");
                            }
                        }
                    }.performAction(null);
                } else if (nivelDeAccesoAutenticado >= R.NivelAcceso.CAJERO.getNivel()) {
                    new LongProcessActionServiceImpl("Creando IPVs.........") {
                        @Override
                        protected void longProcessMethod() {
                            VentaDetailService control = null;
                            control = service.comenzarVentasCajero();
                            Application.getInstance().getNavigator().navigateTo(VentaDetailView.VIEW_NAME,
                                    new VentaDetailViewPresenter(control, PosDesktopUiModule.getInstance().getImplementation(OrdenService.class), service.getDiaVentaSeleccionado()));
                        }
                    }.performAction(null);
                } else {
                    new LongProcessActionServiceImpl("Creando IPVs.........") {
                        @Override
                        protected void longProcessMethod() {
                            VentaDetailService control = null;
                            control = service.comenzarVentasDependiente();
                            Application.getInstance().getNavigator().navigateTo(VentaDetailView.VIEW_NAME,
                                    new VentaDetailViewPresenter(control, PosDesktopUiModule.getInstance().getImplementation(OrdenService.class), service.getDiaVentaSeleccionado()));
                        }
                    }.performAction(null);
                }
                return Optional.empty();
            }
        };
    }

    private AbstractViewAction onMenuClick(String actionName, R.NivelAcceso nivelDeAcceso) {
        return new LicencedViewAction(actionName) {
            @Override
            public Optional doAction() {
                refreshState();
                if (nivelDeAccesoAutenticado >= nivelDeAcceso.getNivel()) {
                    NavigationService.getInstance().navigateTo(actionName);//TODO: pifia
                } else {
                    if (autorize(nivelDeAcceso)) {
                        NavigationService.getInstance().navigateTo(actionName);//TODO: pifia
                    }
                }
                return Optional.empty();
            }
        };
    }

    private boolean autorize(R.NivelAcceso nivelDeAcceso) {
        return new LogInController(new AuthorizerImpl()).constructoAuthorizationView(nivelDeAcceso);
    }

    @Override
    protected Optional refreshState() {
        Application.getInstance().getBackgroundWorker().processInBackground("Cargando...", () -> {
            nivelDeAccesoAutenticado = Application.getInstance().getLoggedUser()
                    .getPuestoTrabajonombrePuesto().getNivelAcceso();
        });
        return Optional.empty();
    }

    private abstract class LicencedViewAction extends AbstractViewAction {//TODO: falta actualizar la licencia en tiempo real

        public LicencedViewAction(String actionName) {
            super(actionName);
        }

        @Override
        public boolean isEnabled() {
            return service.estaActivaLaLicencia();
        }

    }
}
