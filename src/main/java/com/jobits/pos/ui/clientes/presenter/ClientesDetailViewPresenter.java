/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.clientes.presenter;

import com.jobits.pos.cliente.core.domain.ClienteDomain;
import com.jobits.pos.cliente.core.usecase.ClienteUseCase;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.main.Application;
import com.root101.clean.core.app.services.utils.TipoNotificacion;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import java.util.Date;
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
    private final ClienteUseCase clienteservice = PosDesktopUiModule.getInstance().getImplementation(ClienteUseCase.class);
    private final boolean creatingMode;

    private ClienteDomain cliente;

    public ClientesDetailViewPresenter(ClienteDomain cliente) {
        super(new ClientesDetailViewModel());
        this.creatingMode = cliente == null;
        this.cliente = cliente;
        if (creatingMode) {
            this.cliente = new ClienteDomain();
            this.cliente.setNombre("");
            this.cliente.setApellidos("");
            this.cliente.addMeta("alias", "");
            this.cliente.setTelefono("");
            this.cliente.addMeta("fecha_nacimiento",new Date());
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
                cliente.setNombre(getBean().getNombre());
                cliente.setApellidos(getBean().getApellidos());
                cliente.addMeta("alias",getBean().getAlias());
                cliente.setTelefono(getBean().getTelefono());
                cliente.addMeta("fecha_nacimiento",getBean().getCumpleanos());

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
        getBean().setNombre(cliente.getNombre());
        getBean().setApellidos(cliente.getApellidos());
        getBean().setAlias(cliente.getMeta("alias").getValue());
        getBean().setTelefono(cliente.getTelefono());
        getBean().setCumpleanos(cliente.getMeta("fecha_nacimiento").getValue());
        return super.refreshState(); //To change body of generated methods, choose Tools | Templates.
    }
}
