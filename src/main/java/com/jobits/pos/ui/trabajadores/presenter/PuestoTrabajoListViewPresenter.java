/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.trabajadores.presenter;

import com.jobits.pos.controller.trabajadores.PuestoTrabajoDetailController;
import com.jobits.pos.controller.trabajadores.PuestoTrabajoListController;
import com.jobits.pos.controller.trabajadores.PuestoTrabajoListService;
import com.jobits.pos.cordinator.DisplayType;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.TipoNotificacion;
import com.jobits.pos.ui.presenters.AbstractListViewPresenter;
import com.jobits.pos.ui.trabajadores.PuestoTrabajoListView;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class PuestoTrabajoListViewPresenter extends AbstractListViewPresenter<PuestoTrabajoListViewModel> {

    PuestoTrabajoListService service;

    public PuestoTrabajoListViewPresenter(PuestoTrabajoListController controller) {
        super(new PuestoTrabajoListViewModel(), PuestoTrabajoListView.VIEW_NAME);
        this.service = controller;
        setListToBean();
    }

    @Override
    protected void onAgregarClick() {
        Application.getInstance().getNavigator().navigateTo("Crear Puesto de Trabajo", null, DisplayType.POPUP);
        setListToBean();

    }

    @Override
    protected void onEditarClick() {
        if (getBean().getElemento_seleccionado() == null) {
            throw new IllegalArgumentException("Debe seleccionar una trabajador");
        }
        Application.getInstance().getNavigator().navigateTo(
                "Crear Puesto de Trabajo",
                new PuestoTrabajoDetailViewPresenter(
                        new PuestoTrabajoDetailController(
                                getBean().getElemento_seleccionado())),
                DisplayType.POPUP);

        setListToBean();
    }

    @Override
    protected void onEliminarClick() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Esta seguro que desea eliminar el Puesto seleccionado?",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            service.destroy(getBean().getElemento_seleccionado());
            setListToBean();
        }
    }

    @Override
    protected void setListToBean() {
        getBean().getLista_elementos().clear();
        getBean().getLista_elementos().addAll(service.getItems());
    }

}
