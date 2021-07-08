/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.clientes.presenter;

import com.jobits.pos.cliente.core.domain.ClienteDomain;
import com.jobits.pos.cliente.core.usecase.ClienteUseCase;
import com.jobits.pos.cordinator.DisplayType;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.main.Application;
import com.root101.clean.core.app.services.utils.TipoNotificacion;
import com.jobits.pos.ui.clientes.ClientesDetailView;
import com.jobits.pos.ui.clientes.ClientesListView;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractListViewPresenter;

/**
 *
 * JoBits
 *
 * @author Home
 *
 */
public class ClientesListViewPresenter extends AbstractListViewPresenter<ClientesListViewModel> {

    private final ClienteUseCase service = PosDesktopUiModule.getInstance().getImplementation(ClienteUseCase.class);

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
                new ClientesDetailViewPresenter(null), DisplayType.POPUP);
        setListToBean();
    }

    @Override
    protected void onEditarClick() {
        ClienteDomain selected = getBean().getElemento_seleccionado();
        if (selected != null) {
            NavigationService.getInstance().navigateTo(ClientesDetailView.VIEW_NAME,
                    new ClientesDetailViewPresenter(getBean().getElemento_seleccionado()), DisplayType.POPUP);
            setListToBean();
        } else {
            Application.getInstance().getNotificationService().showDialog("Seleccione un cliente", TipoNotificacion.ERROR);
        }
    }

    @Override
    protected void onEliminarClick() {
        ClienteDomain selected = getBean().getElemento_seleccionado();
        if (selected != null) {
            if ((boolean) Application.getInstance().getNotificationService().
                    showDialog("Esta seguro que desea eliminar: " + selected,
                            TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
                service.destroy(selected);
                setListToBean();
            }
        } else {
            Application.getInstance().getNotificationService().showDialog("Seleccione un cliente", TipoNotificacion.ERROR);
        }
    }

    @Override
    protected void setListToBean() {
        getBean().getLista_elementos().clear();
        getBean().getLista_elementos().addAll(service.findAll());
    }

}
