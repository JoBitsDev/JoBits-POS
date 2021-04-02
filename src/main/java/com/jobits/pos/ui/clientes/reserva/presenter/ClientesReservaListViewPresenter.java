/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.clientes.reserva.presenter;

import com.jobits.pos.cordinator.DisplayType;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.NotificationService;
import com.jobits.pos.notification.TipoNotificacion;
import com.jobits.pos.reserva.core.domain.Cliente;
import com.jobits.pos.reserva.core.usecase.ClienteUseCase;
import com.jobits.pos.ui.clientes.reserva.ClientesReservaDetailView;
import com.jobits.pos.ui.clientes.reserva.ClientesReservaListView;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractListViewPresenter;

/**
 *
 * JoBits
 *
 * @author Home
 *
 */
public class ClientesReservaListViewPresenter extends AbstractListViewPresenter<ClientesReservaListViewModel> {

    private final ClienteUseCase service = PosDesktopUiModule.getInstance().getImplementation(ClienteUseCase.class);

    public ClientesReservaListViewPresenter() {
        super(new ClientesReservaListViewModel(), ClientesReservaListView.VIEW_NAME);
        setListToBean();
    }

    @Override
    protected void registerOperations() {
        super.registerOperations(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void onAgregarClick() {
        NavigationService.getInstance().navigateTo(ClientesReservaDetailView.VIEW_NAME,
                new ClientesReservaDetailViewPresenter(null), DisplayType.POPUP);
        setListToBean();
    }

    @Override
    protected void onEditarClick() {
        NavigationService.getInstance().navigateTo(ClientesReservaDetailView.VIEW_NAME,
                new ClientesReservaDetailViewPresenter(getBean().getElemento_seleccionado()), DisplayType.POPUP);
        setListToBean();
    }

    @Override
    protected void onEliminarClick() {
        Cliente selected = getBean().getElemento_seleccionado();
        if (selected != null) {
            if ((boolean) Application.getInstance().getNotificationService().
                    showDialog("Esta seguro que desea eliminar: " + selected,
                            TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
                service.destroy(selected);
                setListToBean();
            }
        } else {
            NotificationService.getInstance().notify("Seleccione un cliente", TipoNotificacion.ERROR);
        }
    }

    @Override
    protected void setListToBean() {
        getBean().getLista_elementos().clear();
        getBean().getLista_elementos().addAll(service.findAll());
    }

}
