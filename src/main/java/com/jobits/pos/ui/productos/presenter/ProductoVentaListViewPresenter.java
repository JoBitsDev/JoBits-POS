/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.productos.presenter;

import com.jobits.pos.controller.productos.ProductoVentaDetailController;
import com.jobits.pos.controller.productos.ProductoVentaListService;
import com.jobits.pos.cordinator.DisplayType;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.domain.models.ProductoVenta;
import com.jobits.pos.main.Application;
import com.jobits.pos.ui.presenters.AbstractListViewPresenter;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.productos.ProductoVentaDetailView;
import com.jobits.pos.ui.productos.ProductoVentaListView;
import java.util.Optional;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class ProductoVentaListViewPresenter extends AbstractListViewPresenter<ProductoVentaListViewModel> {

    public static String ACTION_CHANGE_VISIBLE;

    private ProductoVentaListService controller;

    public ProductoVentaListViewPresenter(ProductoVentaListService controller) {
        super(new ProductoVentaListViewModel(), ProductoVentaListView.VIEW_NAME);
        this.controller = controller;
        setListToBean();
    }

    @Override
    protected void registerOperations() {
        super.registerOperations(); //To change body of generated methods, choose Tools | Templates.
        registerOperation(new AbstractViewAction(ACTION_CHANGE_VISIBLE) {
            @Override
            public Optional doAction() {
                ProductoVenta producto = getBean().getElemento_seleccionado();//TODO: logica en presenter
                producto.setVisible(!producto.getVisible());
                controller.setSelected(producto);
                controller.update();//TODO: activar comportamiento
                getBean().getLista_elementos().fireContentsChanged(getBean().getLista_elementos().indexOf(producto));
                return Optional.empty();
            }
        });
    }

    @Override
    protected void onAgregarClick() {
        NavigationService.getInstance().navigateTo(ProductoVentaDetailView.VIEW_NAME,
                new ProductoVentaDetailPresenter(
                        new ProductoVentaDetailController(), null),DisplayType.POPUP);
        setListToBean();
    }

    @Override
    protected void onEditarClick() {

        NavigationService.getInstance().navigateTo(ProductoVentaDetailView.VIEW_NAME,
                new ProductoVentaDetailPresenter(
                        new ProductoVentaDetailController(
                                getBean().getElemento_seleccionado()),
                        getBean().getElemento_seleccionado()),DisplayType.POPUP);
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
