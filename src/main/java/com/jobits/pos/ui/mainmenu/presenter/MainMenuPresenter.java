/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.mainmenu.presenter;

import com.jobits.pos.controller.login.MainMenuController;
import com.jobits.pos.controller.login.MainMenuService;
import com.jobits.pos.controller.venta.OrdenController;
import com.jobits.pos.controller.venta.VentaDetailController;
import com.jobits.pos.cordinator.DisplayType;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.TipoNotificacion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.licencia.LicenceDialogView;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.venta.VentaDetailView;
import com.jobits.pos.ui.venta.presenter.VentaResumenViewPresenter;
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
            if (v == MainMenuController.MenuButtons.LICENCIA) {
                registerOperation(onLicenciaClick(v.toString()));
                continue;
            }
            registerOperation(onMenuClick(v.toString(), v.getNivelMinimoAcceso()));

        }
    }

    private AbstractViewAction onLicenciaClick(String actionName) {
        return new AbstractViewAction(actionName) {
            @Override
            public Optional doAction() {
                NavigationService.getInstance().navigateTo(actionName, null, DisplayType.POPUP);
                return Optional.empty();//TODO: pifia
            }
        };
    }

    private AbstractViewAction onComenzarVentaClick(String actionName) {
        return new AbstractViewAction(actionName) {
            @Override
            public Optional doAction() {
                Optional<String> ret = Optional.empty();
                VentaDetailController control = null;
                if (nivelDeAccesoAutenticado >= R.NivelAcceso.ECONOMICO.getNivel()) {
                    ret = Application.getInstance().getNotificationService().
                            showDialog("Introduzca el dia para abrir las ventas en el formato dd/mm/aa",
                                    TipoNotificacion.DIALOG_INPUT);
                    try {
                        control = service.comenzarVentasEconomico(R.DATE_FORMAT.parse(ret.get()));
                    } catch (ParseException ex) {
                        Application.getInstance().getNotificationService().showDialog("Formato incorrecto", TipoNotificacion.ERROR);
                        return Optional.empty();
                    }
                } else if (nivelDeAccesoAutenticado >= R.NivelAcceso.CAJERO.getNivel()) {
                    control = service.comenzarVentasCajero();
                } else {
                    control = service.comenzarVentasDependiente();

                }
                Application.getInstance().getNavigator().navigateTo(VentaDetailView.VIEW_NAME,
                        new VentaResumenViewPresenter(control, new OrdenController()));
                return Optional.empty();
            }
        };
    }

    private AbstractViewAction onMenuClick(String actionName, int nivelDeAcceso) {
        return new AbstractViewAction(actionName) {
            @Override
            public Optional doAction() {
                if (nivelDeAccesoAutenticado >= nivelDeAcceso) {
                    NavigationService.getInstance().navigateTo(actionName);//TODO: pifia
                } else {
                    Application.getInstance().getNotificationService().
                            showDialog("El usuario no tiene permisos",
                                    TipoNotificacion.ERROR);
                }
                return Optional.empty();
            }
        };
    }

    @Override
    protected Optional refreshState() {
        nivelDeAccesoAutenticado = Application.getInstance().getLoggedUser()
                .getPuestoTrabajonombrePuesto().getNivelAcceso();
        return Optional.empty();
    }

}
