/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.trabajadores.presenter;

import com.jobits.pos.controller.trabajadores.PuestoTrabajoDetailController;
import com.jobits.pos.controller.trabajadores.PuestoTrabajoListController;
import com.jobits.pos.main.Application;
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

    PuestoTrabajoListController controller;

    public PuestoTrabajoListViewPresenter(PuestoTrabajoListController controller) {
        super(new PuestoTrabajoListViewModel(), PuestoTrabajoListView.VIEW_NAME);
        this.controller = controller;
        setListToBean();
    }

    @Override
    protected void onAgregarClick() {
        PuestoTrabajoDetailController newController = new PuestoTrabajoDetailController(Application.getInstance().getMainWindow());
        setListToBean();

    }

    @Override
    protected void onEditarClick() {
        PuestoTrabajoDetailController newController = new PuestoTrabajoDetailController(getBean().getElemento_seleccionado(), Application.getInstance().getMainWindow());
        setListToBean();
    }

    @Override
    protected void onEliminarClick() {
        controller.destroy(getBean().getElemento_seleccionado());
        setListToBean();
    }

    @Override
    protected void setListToBean() {
        getBean().getLista_elementos().clear();
        getBean().getLista_elementos().addAll(controller.getItems());
    }

}
