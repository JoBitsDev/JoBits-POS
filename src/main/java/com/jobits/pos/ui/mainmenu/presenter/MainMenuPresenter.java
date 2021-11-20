/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.mainmenu.presenter;

import com.jobits.pos.controller.licencia.LicenceService;
import com.jobits.pos.controller.login.impl.LogInController;
import com.jobits.pos.controller.venta.OrdenService;
import com.jobits.pos.controller.venta.VentaDetailService;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.core.domain.models.Venta;
import com.jobits.pos.main.Application;
import com.root101.clean.core.app.services.utils.TipoNotificacion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.mainmenu.MenuButtons;
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

    VentaDetailService vService = PosDesktopUiModule.getInstance().getImplementation(VentaDetailService.class);
    private int nivelDeAccesoAutenticado = 0;

    public MainMenuPresenter() {
        super(new MainMenuViewModel());
        nivelDeAccesoAutenticado = Application.getInstance().getLoggedUser()
                .getPuestoTrabajonombrePuesto().getNivelAcceso();

    }

    @Override
    protected void registerOperations() {
        for (MenuButtons v : MenuButtons.values()) {
            if (v == MenuButtons.COMENZAR_VENTAS) {
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
                                Optional<String> ret;
                                ret = Application.getInstance().getNotificationService().
                                        showDialog("Introduzca el dia para abrir las ventas en el formato dd/mm/aa",
                                                TipoNotificacion.DIALOG_INPUT);
                                Venta ventaIniciada;
                                if (ret.get().isEmpty() || ret.get().isBlank()) {
                                    ventaIniciada = vService.inicializarVentas(null, false);
                                } else {
                                    ventaIniciada = vService.inicializarVentas(R.DATE_FORMAT.parse(ret.get()), false);
                                }
                                Application.getInstance().getNavigator().navigateTo(VentaDetailView.VIEW_NAME,
                                        new VentaDetailViewPresenter(vService, PosDesktopUiModule.getInstance().getImplementation(OrdenService.class), ventaIniciada.getId()));
                            } catch (ParseException ex) {
                                throw new IllegalArgumentException("Formato incorrecto");
                            }
                        }
                    }.performAction(null);
                } else if (nivelDeAccesoAutenticado >= R.NivelAcceso.CAJERO.getNivel()) {
                    new LongProcessActionServiceImpl("Creando IPVs.........") {
                        @Override
                        protected void longProcessMethod() {
                            var ventaIniciada = vService.inicializarVentas(null, false);
                            Application.getInstance().getNavigator().navigateTo(VentaDetailView.VIEW_NAME,
                                    new VentaDetailViewPresenter(vService, PosDesktopUiModule.getInstance().getImplementation(OrdenService.class), ventaIniciada.getId()));
                        }
                    }.performAction(null);
                } else {
                    new LongProcessActionServiceImpl("Creando IPVs.........") {
                        @Override
                        protected void longProcessMethod() {
                            VentaDetailService control = null;
                            var ventaIniciada = vService.inicializarVentas(null, false);
                            Application.getInstance().getNavigator().navigateTo(VentaDetailView.VIEW_NAME,
                                    new VentaDetailViewPresenter(vService, PosDesktopUiModule.getInstance().getImplementation(OrdenService.class), ventaIniciada.getId()));
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
        return new LogInController().constructoAuthorizationView(nivelDeAcceso);//TODO: inyectar
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
            return PosDesktopUiModule.getInstance().getImplementation(LicenceService.class).estaActivaLaLicencia();
        }

    }
}
