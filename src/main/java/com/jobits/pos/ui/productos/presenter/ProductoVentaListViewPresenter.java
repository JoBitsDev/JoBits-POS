/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.productos.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.productos.ProductoVentaDetailController;
import com.jobits.pos.controller.productos.ProductoVentaListController;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.ui.presenters.AbstractListViewPresenter;
import com.jobits.pos.ui.productos.ProductoVentaDetailView;
import com.jobits.pos.ui.productos.ProductoVentaListView;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class ProductoVentaListViewPresenter extends AbstractListViewPresenter<ProductoVentaListViewModel> {

    private ProductoVentaListController controller;

    public ProductoVentaListViewPresenter(ProductoVentaListController controller) {
        super(new ProductoVentaListViewModel(), ProductoVentaListView.VIEW_NAME);
        this.controller = controller;
        setListToBean();
    }

    @Override
    protected void onAgregarClick() {
        NavigationService.getInstance().navigateTo(ProductoVentaDetailView.VIEW_NAME,
                new ProductoVentaDetailPresenter(
                        new ProductoVentaDetailController(), null));
    }

    @Override
    protected void onEditarClick() {
        NavigationService.getInstance().navigateTo(ProductoVentaDetailView.VIEW_NAME,
                new ProductoVentaDetailPresenter(
                        new ProductoVentaDetailController(
                                getBean().getElemento_seleccionado()),
                        getBean().getElemento_seleccionado()));
    }

    @Override
    protected void onEliminarClick() {
        controller.destroy(getBean().getElemento_seleccionado());
        setListToBean();
    }

    @Override
    protected void setListToBean() {
        getBean().setLista_elementos(new ArrayListModel<>(controller.getItems()));
    }

}
