/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.clientes.presenter;

import com.jobits.pos.cliente.core.domain.ClienteDomain;
import com.jobits.pos.cliente.core.domain.DireccionEnvioDomain;
import com.jobits.pos.cliente.core.usecase.DireccionEnvioUseCase;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.main.Application;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.root101.clean.core.app.services.utils.TipoNotificacion;
import java.util.Optional;

/**
 *
 * @author Home
 */
public class DireccionEnvioDetailViewPresenter extends AbstractViewPresenter<DireccionEnvioDetailViewModel> {

    public static final String ACTION_CANCELAR = "Cancelar";
    public static final String ACTION_ACEPTAR = "Aceptar";

    private final ClienteDomain cliente;
    private DireccionEnvioDomain direccion_envio;

    DireccionEnvioUseCase service = PosDesktopUiModule.getInstance().getImplementation(DireccionEnvioUseCase.class);

    public DireccionEnvioDetailViewPresenter(ClienteDomain cliente) {
        super(new DireccionEnvioDetailViewModel());
        this.cliente = cliente;
        this.direccion_envio = new DireccionEnvioDomain();
        this.direccion_envio.setId(cliente.getId());
        this.direccion_envio.setNombre(cliente.getNombre());
        this.direccion_envio.setApellidos(cliente.getApellidos());
        this.direccion_envio.setTelefono(cliente.getTelefono());
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
        registerOperation(new AbstractViewAction(ACTION_ACEPTAR) {
            @Override
            public Optional doAction() {
                onAceptarClick();
                NavigationService.getInstance().navigateUp();
                return Optional.empty();
            }
        });
    }

    private void onCancelarClick() {
        NavigationService.getInstance().navigateUp();
    }

    public DireccionEnvioDomain onAceptarClick() {
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

        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Esta seguro que desea continuar?",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            direccion_envio.setAlias(alias);
            direccion_envio.setNombre(nombre);
            direccion_envio.setTelefono(telefono);
            direccion_envio.setDireccion(direccion);
            direccion_envio.setCiudad(ciudad);
            direccion_envio.setProvincia(provincia);

            direccion_envio.setApellidos(apellidos);
            direccion_envio.setEmpresa(empresa);
            direccion_envio.setDireccionAdicional(direccion_adicional);
            return direccion_envio;
        }
        return null;
    }

    @Override
    protected Optional refreshState() {
        getBean().setDe_nombre(direccion_envio.getNombre());
        getBean().setDe_apellidos(direccion_envio.getApellidos());
        getBean().setDe_telefono(direccion_envio.getTelefono());
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
