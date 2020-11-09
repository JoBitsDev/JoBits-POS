/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.clientes.presenter;

import com.jobits.pos.ui.productos.presenter.*;
import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.productos.ProductoVentaDetailController;
import com.jobits.pos.controller.productos.ProductoVentaDetailService;
import com.jobits.pos.controller.puntoelaboracion.PuntoElaboracionListController;
import com.jobits.pos.controller.seccion.SeccionListController;
import com.jobits.pos.controller.trabajadores.PersonalDetailController;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.domain.models.Personal;
import com.jobits.pos.domain.models.ProductoInsumo;
import com.jobits.pos.domain.models.ProductoVenta;
import com.jobits.pos.domain.models.PuestoTrabajo;
import com.jobits.pos.exceptions.DevelopingOperationException;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.NotificationService;
import com.jobits.pos.notification.TipoNotificacion;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.trabajadores.presenter.PersonalDetailViewModel;
import com.jobits.pos.ui.utils.utils;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import javax.swing.JOptionPane;

/**
 *
 * JoBits
 *
 * @author Home
 *
 */
public class ClientesDetailViewPresenter extends AbstractViewPresenter<ClientesDetailViewModel> {

    public static final String ACTION_CANCELAR = "Cancelar";
    public static String ACTION_AGREGAR = "";


    public ClientesDetailViewPresenter() {
        super(new ClientesDetailViewModel());

//        if (service.isCreatingMode()) {
//            personal = service.createNewInstance();
//        } else {
//            personal = service.getInstance();
//        }
        fillForm();
        addListeners();
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_CANCELAR) {
            @Override
            public Optional doAction() {
                onCancelarClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_AGREGAR) {
            @Override
            public Optional doAction() {
                onAgregarClick();
                return Optional.empty();
            }
        });
    }

    private void addListeners() {
    }

    private void onAgregarClick() {

    }

    private void onCancelarClick() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Desea descartar los cambios?",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            NavigationService.getInstance().navigateUp();
        }
    }

    private void fillForm() {
    }
}
