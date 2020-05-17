/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.insumo.presenter;

import com.jobits.pos.controller.insumo.InsumoCreateEditController;
import com.jobits.pos.controller.insumo.InsumoListController;
import com.jobits.pos.main.Application;
import com.jobits.pos.ui.presenters.AbstractListViewPresenter;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class InsumoListViewPresenter extends AbstractListViewPresenter<InsumoListViewModel> {

    InsumoListController controller;

    public InsumoListViewPresenter(InsumoListController controller) {
        super(new InsumoListViewModel(), "Insumos");
        this.controller = controller;
        setListToBean();
    }

    @Override
    protected void onAgregarClick() {
        InsumoCreateEditController newController = new InsumoCreateEditController(Application.getInstance().getMainWindow());
        setListToBean();
    }

    @Override
    protected void onEditarClick() {
        InsumoCreateEditController newController = new InsumoCreateEditController(
                getBean().getElemento_seleccionado(), Application.getInstance().getMainWindow());
        setListToBean();

    }

    @Override
    protected void onEliminarClick() {
        controller.destroy(getBean().getElemento_seleccionado());
        getBean().setElemento_seleccionado(null);
        setListToBean();
    }

    @Override
    protected void setListToBean() {
        getBean().getLista_elementos().clear();
        getBean().getLista_elementos().addAll(controller.getItems());
    }

}
