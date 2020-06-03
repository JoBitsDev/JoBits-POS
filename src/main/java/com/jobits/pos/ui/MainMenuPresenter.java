/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui;

import com.jobits.pos.controller.login.MainMenuController;
import com.jobits.pos.controller.venta.OrdenController;
import com.jobits.pos.controller.venta.VentaDetailController;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.main.Application;
import com.jobits.pos.main.ViewFacade;
import com.jobits.pos.notification.TipoNotificacion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.licencia.LicenceDialogView;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.venta.VentaDetailView;
import com.jobits.pos.ui.venta.presenter.VentaResumenViewPresenter;
import java.text.ParseException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class MainMenuPresenter extends AbstractViewPresenter<MainMenuViewModel> {

    MainMenuController controller;

    public MainMenuPresenter(MainMenuController controller) {
        super(new MainMenuViewModel());
        this.controller = controller;

    }

    @Override
    protected void registerOperations() {
        for (MainMenuController.MenuButtons v : MainMenuController.MenuButtons.values()) {
            if (v == MainMenuController.MenuButtons.LICENCIA) {
                registerOperation(new AbstractViewAction(v.toString()) {
                    @Override
                    public Optional doAction() {
                        ViewFacade.getView(LicenceDialogView.VIEW_NAME, null);
                        return Optional.empty();
                    }
                });
                continue;
            }
            if (v == MainMenuController.MenuButtons.COMENZAR_VENTAS) {
                registerOperation(new AbstractViewAction(v.toString()) {
                    @Override
                    public Optional doAction() {
                        Optional<String> ret = Application.getInstance().getNotificationService().
                                showDialog("Introduzca el dia para abrir las ventas en el formato dd/mm/aa",
                                        TipoNotificacion.DIALOG_INPUT);
                        if (ret.isPresent()) {
                            try {
                                VentaDetailController control = controller.comenzarVentas(R.DATE_FORMAT.parse(ret.get()));
                                Application.getInstance().getNavigator().navigateTo(VentaDetailView.VIEW_NAME,
                                        new VentaResumenViewPresenter(control, new OrdenController()));
                            } catch (ParseException ex) {
                                Application.getInstance().getNotificationService().showDialog("Formato incorrecto", TipoNotificacion.ERROR);
                            }

                        }
                        return Optional.empty();
                    }
                });
                continue;
            }
            registerOperation(new AbstractViewAction(v.toString()) {
                @Override
                public Optional doAction() {
                    NavigationService.getInstance().navigateTo(v.toString());
                    return Optional.empty();
                }
            });

        }
    }

}
