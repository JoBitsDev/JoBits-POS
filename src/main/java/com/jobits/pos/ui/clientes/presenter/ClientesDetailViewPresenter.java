/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.clientes.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.clientes.ClientesDetailService;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.core.domain.models.Cliente;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.TipoNotificacion;
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
public class ClientesDetailViewPresenter extends AbstractViewPresenter<ClientesDetailViewModel> {

    public static final String ACTION_CANCELAR = "Cancelar";
    public static String ACTION_AGREGAR = "";
    private final ClientesDetailService clienteservice = PosDesktopUiModule.getInstance().getImplementation(ClientesDetailService.class);
    private final boolean creatingMode;

    private Cliente cliente;

    public ClientesDetailViewPresenter(Cliente cliente) {
        super(new ClientesDetailViewModel());
        this.creatingMode = cliente == null;
        this.cliente = cliente;
        if (creatingMode) {
            this.cliente = clienteservice.createNewInstance();
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
                cliente.setNombreCliente(getBean().getNombre());
                cliente.setApellidosCliente(getBean().getApellidos());
                cliente.setAliasCliente(getBean().getAlias());
                cliente.setTelefonoCliente(getBean().getTelefono());
                cliente.setFechanacCliente(getBean().getCumpleanos());
                cliente.setDireccionCliente(getBean().getDireccion());
                cliente.setMunicipioCliente(getBean().getMunicipio());
                cliente.setPrivinciaCliente(getBean().getCiudad());
                cliente.setOrdenList(getBean().getLista_ordenes());

                if (creatingMode) {
                    clienteservice.crearCliente(cliente);
                } else {
                    clienteservice.editarCliente(cliente);
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
        getBean().setNombre(cliente.getNombreCliente());
        getBean().setApellidos(cliente.getApellidosCliente());
        getBean().setAlias(cliente.getAliasCliente());
        getBean().setTelefono(cliente.getTelefonoCliente());
        getBean().setCumpleanos(cliente.getFechanacCliente());
        getBean().setDireccion(cliente.getDireccionCliente());
        getBean().setMunicipio(cliente.getMunicipioCliente());
        getBean().setCiudad(cliente.getPrivinciaCliente());
        getBean().setLista_ordenes(new ArrayListModel<>(cliente.getOrdenList()));
        return super.refreshState(); //To change body of generated methods, choose Tools | Templates.
    }
}
