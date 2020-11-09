/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.clientes.presenter;

import com.jobits.pos.controller.clientes.ClientesDetailServiceImpl;
import com.jobits.pos.controller.clientes.ClientesListService;
import com.jobits.pos.cordinator.DisplayType;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.ui.clientes.ClientesDetailView;
import com.jobits.pos.ui.clientes.ClientesListView;
import com.jobits.pos.ui.presenters.AbstractListViewPresenter;

/**
 *
 * JoBits
 *
 * @author Home
 *
 */
public class ClientesListViewPresenter extends AbstractListViewPresenter<ClientesListViewModel> {

    private final ClientesListService service;

    public ClientesListViewPresenter(ClientesListService service) {
        super(new ClientesListViewModel(), ClientesListView.VIEW_NAME);
        this.service = service;
        setListToBean();
    }

    @Override
    protected void registerOperations() {
        super.registerOperations(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void onAgregarClick() {
        NavigationService.getInstance().navigateTo(ClientesDetailView.VIEW_NAME,
                new ClientesDetailViewPresenter(new ClientesDetailServiceImpl()), DisplayType.POPUP);
        setListToBean();
    }

    @Override
    protected void onEditarClick() {
        NavigationService.getInstance().navigateTo(ClientesDetailView.VIEW_NAME,
                new ClientesDetailViewPresenter(new ClientesDetailServiceImpl(getBean().getElemento_seleccionado())), DisplayType.POPUP);
        setListToBean();
    }

    @Override
    protected void onEliminarClick() {
        service.eliminar(getBean().getElemento_seleccionado());
        setListToBean();
    }

    @Override
    protected void setListToBean() {
        getBean().getLista_elementos().clear();
        getBean().getLista_elementos().addAll(service.getListaClientes());
    }

}
