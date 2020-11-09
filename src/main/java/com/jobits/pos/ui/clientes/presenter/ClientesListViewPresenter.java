/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.clientes.presenter;

import com.jobits.pos.ui.productos.presenter.*;
import com.jobits.pos.controller.productos.ProductoVentaDetailController;
import com.jobits.pos.controller.productos.ProductoVentaListService;
import com.jobits.pos.cordinator.DisplayType;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.domain.models.ProductoVenta;
import com.jobits.pos.main.Application;
import com.jobits.pos.ui.clientes.ClientesDetailView;
import com.jobits.pos.ui.clientes.ClientesListView;
import com.jobits.pos.ui.presenters.AbstractListViewPresenter;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.productos.ProductoVentaDetailView;
import com.jobits.pos.ui.productos.ProductoVentaListView;
import java.util.Optional;

/**
 *
 * JoBits
 *
 * @author Home
 *
 */
public class ClientesListViewPresenter extends AbstractListViewPresenter<ClientesListViewModel> {

    public ClientesListViewPresenter() {
        super(new ClientesListViewModel(), ClientesListView.VIEW_NAME);
        setListToBean();
    }

    @Override
    protected void registerOperations() {
        super.registerOperations(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void onAgregarClick() {
        NavigationService.getInstance().navigateTo(ClientesDetailView.VIEW_NAME,
                new ClientesDetailViewPresenter(), DisplayType.POPUP);
        setListToBean();
    }

    @Override
    protected void onEditarClick() {
        NavigationService.getInstance().navigateTo(ClientesDetailView.VIEW_NAME,
                new ClientesDetailViewPresenter(), DisplayType.POPUP);
        setListToBean();
    }

    @Override
    protected void onEliminarClick() {
//        controller.destroy(getBean().getElemento_seleccionado());
        setListToBean();
    }

    @Override
    protected void setListToBean() {
//        getBean().getLista_elementos().clear();
//        getBean().getLista_elementos().addAll(controller.getItems());
    }

}
