/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.clientes.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.cliente.core.domain.ClienteDomain;
import com.jobits.pos.cliente.core.domain.DireccionEnvioDomain;
import com.jobits.pos.cliente.core.usecase.ClienteUseCase;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.main.Application;
import com.root101.clean.core.app.services.utils.TipoNotificacion;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public static final String ACTION_AGREGAR = "Agregar";
    public static final String ACTION_AGREGAR_DIRECCION_ENVIO = "Agregar Direccion Envio";
    public static final String ACTION_ELIMINAR_DIRECCION_ENVIO = "Eliminar Direccion Envio";
    public static final String ACTION_SWITCH_TO_AGREGAR_DIRECCION_ENVIO = "Cambiar a Agregar Direccion Envio";

    private final ClienteUseCase clienteservice = PosDesktopUiModule.getInstance().getImplementation(ClienteUseCase.class);
    private final boolean creatingMode;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private ClienteDomain cliente;

    public ClientesDetailViewPresenter(ClienteDomain cliente) {
        super(new ClientesDetailViewModel());
        this.creatingMode = cliente == null;
        this.cliente = cliente;
        if (creatingMode) {
            this.cliente = new ClienteDomain();
            this.cliente.setNombre("");
            this.cliente.setApellidos("");
            this.cliente.setTelefono("");
            this.cliente.addMeta("alias", "");
            this.cliente.addMeta("fecha_nacimiento", sdf.format(new Date()));
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
        registerOperation(new AbstractViewAction(ACTION_AGREGAR_DIRECCION_ENVIO) {
            @Override
            public Optional doAction() {
                onAgregarDireccionEnvioClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_ELIMINAR_DIRECCION_ENVIO) {
            @Override
            public Optional doAction() {
                onEliminarDireccionEnvioClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_SWITCH_TO_AGREGAR_DIRECCION_ENVIO) {
            @Override
            public Optional doAction() {
                onSwitchToAgregarDireccionEnvioClick();
                return Optional.empty();
            }
        });
    }

    private void onEliminarDireccionEnvioClick() {
        DireccionEnvioDomain dirEnv = getBean().getDireccion_envio_seleccionada();
        if (dirEnv == null) {
            throw new IllegalArgumentException("Seleccione una direccion de envio primero");
        }
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Esta seguro que desea eliminar la direccion de envio seleccionada?",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {

            List<DireccionEnvioDomain> list = cliente.getDireccionEnvioList();

            for (DireccionEnvioDomain d : list) {
                if (d.getAlias().equals(dirEnv.getAlias())
                        && d.getTelefono().equals(dirEnv.getTelefono())
                        && d.getDireccion().equals(dirEnv.getDireccion())) {
                    list.remove(d);
                    cliente.setDireccionEnvioList(list);
                    refreshState();
                    break;
                }
            }
        }

    }

    private void onAgregarDireccionEnvioClick() {
        String nombre = getBean().getDe_nombre();
        String alias = getBean().getDe_alias();
        String apellidos = getBean().getDe_apellidos();
        String telefono = getBean().getDe_telefono();
        String empresa = getBean().getDe_empresa();
        String direccion = getBean().getDe_direccion();
        String direccion_adicional = getBean().getDe_direccion_adicional();
        String ciudad = getBean().getDe_ciudad();
        String provincia = getBean().getDe_provincia();

        if (checkEmpty(nombre, alias, telefono, direccion, ciudad, provincia)) {
            throw new IllegalArgumentException("Faltan campos obligatorios por llenar");
        }

        DireccionEnvioDomain de = new DireccionEnvioDomain(cliente.getId(), alias, nombre, telefono, direccion, ciudad, provincia);
        de.setApellidos(apellidos);
        de.setEmpresa(empresa);
        de.setDireccionAdicional(direccion_adicional);

        cliente.getDireccionEnvioList().add(de);
        refreshState();
        firePropertyChange(ACTION_AGREGAR_DIRECCION_ENVIO, null, null);
    }

    private void onSwitchToAgregarDireccionEnvioClick() {
        firePropertyChange(ACTION_SWITCH_TO_AGREGAR_DIRECCION_ENVIO, null, null);
    }

    private void onAgregarClick() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Esta seguro que desea continuar?",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            String nombre = getBean().getNombre();
            String apellidos = getBean().getApellidos();
            String alias = getBean().getAlias();
            String telefono = getBean().getTelefono();
            Date date = getBean().getCumpleanos();
            if (checkEmpty(nombre, telefono)) {
                throw new IllegalArgumentException("Faltan campos obligatorios");
            }
            cliente.setNombre(nombre);
            cliente.setApellidos(apellidos);
            cliente.setTelefono(telefono);
//            cliente.addMeta("alias", alias);
//            cliente.addMeta("fecha_nacimiento", sdf.format(date));
            cliente.setDireccionEnvioList(getBean().getLista_direcciones_envio());
            if (creatingMode) {
                clienteservice.create(cliente);
            } else {
                clienteservice.edit(cliente);
            }
            NavigationService.getInstance().navigateUp();
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
        try {
            getBean().setCumpleanos(sdf.parse(cliente.getMeta("fecha_nacimiento").getValue()));
        } catch (ParseException ex) {
        }
        getBean().setTelefono(cliente.getTelefono());

        getBean().setLista_direcciones_envio(new ArrayListModel<>(cliente.getDireccionEnvioList()));
        getBean().setDireccion_envio_seleccionada(null);

        getBean().setDe_alias(null);
        getBean().setDe_apellidos(null);
        getBean().setDe_ciudad(null);
        getBean().setDe_direccion(null);
        getBean().setDe_direccion_adicional(null);
        getBean().setDe_empresa(null);
        getBean().setDe_nombre(null);
        getBean().setDe_provincia(null);
        getBean().setDe_telefono(null);

        try {
            getBean().setCumpleanos(sdf.parse(cliente.getMeta("fecha_nacimiento").getValue()));
        } catch (ParseException ex) {
            Logger.getLogger(ClientesDetailViewPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return super.refreshState(); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean checkEmpty(String... values) {
        for (String value : values) {
            if (value == null || value.isEmpty() || value.isBlank()) {
                return true;
            }
        }
        return false;
    }
}
