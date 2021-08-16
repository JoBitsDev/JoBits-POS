/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.clientes.presenter;

import com.jobits.pos.cliente.core.domain.ClienteDomain;
import com.jobits.pos.cliente.core.usecase.ClienteUseCase;
import com.jobits.pos.ui.clientes.DireccionEnvioListView;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractListViewPresenter;

/**
 *
 * @author Home
 */
public class DireccionEnvioListViewPresenter extends AbstractListViewPresenter<DireccionEnvioListViewModel> {

    private final ClienteUseCase service = PosDesktopUiModule.getInstance().getImplementation(ClienteUseCase.class);

    private final ClienteDomain cliente;

    public DireccionEnvioListViewPresenter(ClienteDomain cliente) {
        super(new DireccionEnvioListViewModel(), DireccionEnvioListView.VIEW_NAME);
        this.cliente = cliente;
        setListToBean();
    }

    @Override
    protected void onAgregarClick() {
    }

    @Override
    protected void onEditarClick() {
    }

    @Override
    protected void onEliminarClick() {
    }

    @Override
    protected void setListToBean() {
        getBean().setNombre_cliente(cliente.toString());
        if (!cliente.getDireccionEnvioList().isEmpty()) {
            getBean().setLista_elementos(cliente.getDireccionEnvioList());
            getBean().setElemento_seleccionado(null);
        }
    }

//    @Override
//    protected void registerOperations() {
//        super.registerOperations(); //To change body of generated methods, choose Tools | Templates.
//    }
}
