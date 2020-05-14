/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.productos.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.productos.ProductoVentaDetailController;
import com.jobits.pos.controller.productos.ProductoVentaListController;
import com.jobits.pos.cordinator.MainNavigator;
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
public class ProductoVentaViewPresenter extends AbstractListViewPresenter<ProductoVentaListViewModel> {

    private ProductoVentaListController controller;

    public ProductoVentaViewPresenter(ProductoVentaListController controller) {
        super(new ProductoVentaListViewModel(), ProductoVentaListView.VIEW_NAME);
        this.controller = controller;
        setListToBean();
    }

    @Override
    protected void onAgregarClick() {
        MainNavigator.getInstance().navigateTo(ProductoVentaDetailView.VIEW_NAME,
                new ProductoVentaDetailPresenter(
                        new ProductoVentaDetailController(), null));
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
