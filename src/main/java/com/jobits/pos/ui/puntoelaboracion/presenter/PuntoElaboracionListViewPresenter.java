/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.puntoelaboracion.presenter;

import com.jobits.pos.controller.puntoelaboracion.PuntoElaboracionListController;
import com.jobits.pos.ui.presenters.AbstractListViewPresenter;
import com.jobits.pos.ui.puntoelaboracion.PuntoElaboracionListView;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class PuntoElaboracionListViewPresenter extends AbstractListViewPresenter<PuntoElaboracionListViewModel> {

    private PuntoElaboracionListController controller;

    public PuntoElaboracionListViewPresenter(PuntoElaboracionListController controller) {
        super(new PuntoElaboracionListViewModel(), PuntoElaboracionListView.VIEW_NAME);
        this.controller = controller;
        setListToBean();
    }

    @Override
    protected void onAgregarClick() {
        controller.createInstance();
        setListToBean();
    }

    @Override
    protected void onEditarClick() {
        controller.update(getBean().getElemento_seleccionado());
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
