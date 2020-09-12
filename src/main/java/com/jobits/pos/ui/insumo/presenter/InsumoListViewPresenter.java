/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.insumo.presenter;

import com.jobits.pos.controller.insumo.InsumoDetailController;
import com.jobits.pos.controller.insumo.InsumoListController;
import com.jobits.pos.controller.insumo.InsumoListService;
import com.jobits.pos.controller.productos.ProductoVentaDetailController;
import com.jobits.pos.cordinator.DisplayType;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.main.Application;
import com.jobits.pos.ui.insumo.InsumoDetailView;
import com.jobits.pos.ui.presenters.AbstractListViewPresenter;
import com.jobits.pos.ui.productos.ProductoVentaDetailView;
import com.jobits.pos.ui.productos.presenter.ProductoVentaDetailPresenter;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class InsumoListViewPresenter extends AbstractListViewPresenter<InsumoListViewModel> {

    private InsumoListService controller;

    public InsumoListViewPresenter(InsumoListService controller) {
        super(new InsumoListViewModel(), "Insumos");
        this.controller = controller;
        setListToBean();
    }

    @Override
    protected void onAgregarClick() {
        NavigationService.getInstance().navigateTo(InsumoDetailView.VIEW_NAME,
                new InsumoDetailViewPresenter(
                        new InsumoDetailController()), DisplayType.POPUP);
//        InsumoDetailController newController = new InsumoDetailController(Application.getInstance().getMainWindow());
        setListToBean();
    }

    @Override
    protected void onEditarClick() {
        NavigationService.getInstance().navigateTo(InsumoDetailView.VIEW_NAME,
                new InsumoDetailViewPresenter(
                        new InsumoDetailController(
                                getBean().getElemento_seleccionado())), DisplayType.POPUP);
//        InsumoDetailController newController = new InsumoDetailController(
//                getBean().getElemento_seleccionado(), Application.getInstance().getMainWindow());
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
