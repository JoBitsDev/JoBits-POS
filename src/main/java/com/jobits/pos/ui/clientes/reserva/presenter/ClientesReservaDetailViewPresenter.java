/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.clientes.reserva.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.TipoNotificacion;
import com.jobits.pos.reserva.core.domain.Cliente;
import com.jobits.pos.reserva.core.usecase.ClienteUseCase;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import java.util.Optional;
import javax.swing.JOptionPane;

/**
 *
 * JoBits
 *
 * @author Home
 *
 */
public class ClientesReservaDetailViewPresenter extends AbstractViewPresenter<ClientesReservaDetailViewModel> {

    public static final String ACTION_CANCELAR = "Cancelar";
    public static String ACTION_AGREGAR = "";
    private final ClienteUseCase clienteservice = PosDesktopUiModule.getInstance().getImplementation(ClienteUseCase.class);

    private final boolean creatingMode;

    private Cliente cliente;

    public ClientesReservaDetailViewPresenter(Cliente cliente) {
        super(new ClientesReservaDetailViewModel());
        this.creatingMode = cliente == null;
        if (creatingMode) {
            this.cliente = new Cliente();
        } else {
            this.cliente = cliente;
        }
        refreshState();
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_CANCELAR) {
            @Override
            public Optional doAction() {
                onCancelarClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_AGREGAR) {
            @Override
            public Optional doAction() {
                onAgregarClick();
                return Optional.empty();
            }
        });
    }

    private void onAgregarClick() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Esta seguro que desea continuar?",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            if (getBean().getNombre() != null
                    && getBean().getApellidos() != null
                    && getBean().getTelefono() != null) {
                cliente.setNombrecliente(getBean().getNombre());
                cliente.setApellidocliente(getBean().getApellidos());
                cliente.setTelefonocliente(getBean().getTelefono());
                cliente.setDireccioncliente(getBean().getDireccion());
                cliente.setMunicipiocliente(getBean().getMunicipio());
                cliente.setProvinciacliente(getBean().getCiudad());
                cliente.setReservaCollection(getBean().getLista_reservas());

                if (creatingMode) {
                    clienteservice.create(cliente);
                } else {
                    clienteservice.edit(cliente);
                }
                NavigationService.getInstance().navigateUp();
            } else {
                JOptionPane.showMessageDialog(null, "Faltan campos obligarios por llenar");
            }
        }
    }

    private void onCancelarClick() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Esta seguro que desea cancelar?",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            NavigationService.getInstance().navigateUp();
        }
    }

    @Override
    protected Optional refreshState() {
        getBean().setNombre(cliente.getNombrecliente());
        getBean().setApellidos(cliente.getApellidocliente());
        getBean().setTelefono(cliente.getTelefonocliente());
        getBean().setDireccion(cliente.getDireccioncliente());
        getBean().setMunicipio(cliente.getMunicipiocliente());
        getBean().setCiudad(cliente.getProvinciacliente());
        getBean().setLista_reservas(new ArrayListModel<>(cliente.getReservaCollection()));
        return super.refreshState(); //To change body of generated methods, choose Tools | Templates.
    }
}
