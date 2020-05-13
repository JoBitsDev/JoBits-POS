/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.insumo.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.insumo.InsumoListController;
import com.jobits.pos.ui.insumo.InsumoListView;
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
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void onEditarClick() {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void onEliminarClick() {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void setListToBean() {
        getBean().setLista_elementos(new ArrayListModel<>(controller.getItems()));
    }

}
