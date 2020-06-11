/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.almacen.presenter;

import com.jobits.pos.controller.almacen.AlmacenListController;
import com.jobits.pos.ui.almacen.AlmacenListView;
import com.jobits.pos.ui.presenters.AbstractListViewPresenter;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class AlmacenListPresenter extends AbstractListViewPresenter<AlmacenListViewModel> {

    private AlmacenListController controller;

    public AlmacenListPresenter(AlmacenListController controller) {
        super(new AlmacenListViewModel(), AlmacenListView.VIEW_NAME);
        this.controller = controller;
        setListToBean();
    }

    @Override
    protected void onAgregarClick() {
        controller.createNewStorage();
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
        getBean().setLista_elementos(controller.getItems());
    }

}
