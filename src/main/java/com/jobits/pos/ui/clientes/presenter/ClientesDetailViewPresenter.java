/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.clientes.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.clientes.ClientesDetailService;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.domain.models.Cliente;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.TipoNotificacion;
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
    private final ClientesDetailService service;
    private Cliente cliente;

    public ClientesDetailViewPresenter(ClientesDetailService service) {
        super(new ClientesDetailViewModel());
        this.service = service;
        if (service.isCreatingMode()) {
            cliente = service.createNewInstance();
        } else {
            cliente = service.getInstance();
        }
        fillForm();
        addListeners();
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

    private void addListeners() {
    }

    private void onAgregarClick() {
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
            service.crearCliente(cliente);
            NavigationService.getInstance().navigateUp();
        } else {
            JOptionPane.showMessageDialog(null, "Faltan campos obligarios por llenar");
        }
    }

    private void onCancelarClick() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Desea descartar los cambios?",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            NavigationService.getInstance().navigateUp();
        }
    }

    private void fillForm() {
        getBean().setNombre(cliente.getNombreCliente());
        getBean().setApellidos(cliente.getApellidosCliente());
        getBean().setAlias(cliente.getAliasCliente());
        getBean().setTelefono(cliente.getTelefonoCliente());
        getBean().setCumpleanos(cliente.getFechanacCliente());
        getBean().setDireccion(cliente.getDireccionCliente());
        getBean().setMunicipio(cliente.getMunicipioCliente());
        getBean().setCiudad(cliente.getPrivinciaCliente());
        getBean().setLista_ordenes(new ArrayListModel<>(cliente.getOrdenList()));
    }
}
