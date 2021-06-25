/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.orden.presenter;

import com.jobits.pos.controller.venta.DomicilioService;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.core.domain.models.DireccionEnvio;
import com.jobits.pos.core.domain.models.Orden;
import com.jobits.pos.main.Application;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.utils.utils;
import com.root101.clean.core.app.services.utils.TipoNotificacion;
import java.util.Optional;

/**
 *
 * @author Home
 */
public class DomicilioViewPresenter extends AbstractViewPresenter<DomicilioViewModel> {

    DomicilioService service = PosDesktopUiModule.getInstance().getImplementation(DomicilioService.class);
    public static final String ACTION_CANCELAR = "Cancelar";
    public static final String ACTION_ACEPTAR = "Aceptar";

    private DireccionEnvio direccion_envio;
    private final Orden codOrden;
    private final boolean creatingMode;

    public DomicilioViewPresenter(DireccionEnvio direccion_envio, Orden codOrden) {
        super(new DomicilioViewModel());
        this.codOrden = codOrden;
        this.creatingMode = direccion_envio == null;
        if (creatingMode) {
            this.direccion_envio = new DireccionEnvio(codOrden.getCodOrden());
            this.direccion_envio.setOrden(codOrden);
        } else {
            this.direccion_envio = direccion_envio;
        }
        fillForm();
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
                return Optional.empty();
            }

        });
    }

    private void onAceptarClick() {
        String nombre = getBean().getNombre();
        String telefono = getBean().getTelefono();
        String direccion = getBean().getDireccion();
        String ciudad = getBean().getCiudad();
        String provincia = getBean().getProvincia();
        String precio_envio = getBean().getPrecio_envio();
        String apellidos = getBean().getApellidos();
        String empresa = getBean().getEmpresa();
        String direccion_adicional = getBean().getDireccion_adicional();

        if (nombre == null || nombre.isEmpty() || nombre.isBlank()
                || telefono == null || telefono.isEmpty() || telefono.isBlank()
                || direccion == null || direccion.isEmpty() || direccion.isBlank()
                || ciudad == null || ciudad.isEmpty() || ciudad.isBlank()
                || provincia == null || provincia.isEmpty() || provincia.isBlank()
                || precio_envio == null || precio_envio.isEmpty() || precio_envio.isBlank()) {
            throw new IllegalArgumentException("Faltan campos requeridos por llenar");
        }
        try {
            Float.valueOf(precio_envio);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Precio de envío no válido");
        }
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Esta seguro que desea continuar?",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            direccion_envio.setNombre(nombre);
            direccion_envio.setApellidos(apellidos);
            direccion_envio.setTelefono(telefono);
            direccion_envio.setEmpresa(empresa);
            direccion_envio.setDireccion(direccion);
            direccion_envio.setDireccionAdicional(direccion_adicional);
            direccion_envio.setCiudad(ciudad);
            direccion_envio.setProvincia(provincia);
            direccion_envio.setPrecioEnvio(Float.valueOf(precio_envio));

            if (creatingMode) {
                service.create(direccion_envio);
            } else {
                service.edit(direccion_envio);
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

    private void fillForm() {
        getBean().setNombre(direccion_envio.getNombre());
        getBean().setApellidos(direccion_envio.getApellidos());
        getBean().setTelefono(direccion_envio.getTelefono());
        getBean().setEmpresa(direccion_envio.getEmpresa());
        getBean().setDireccion(direccion_envio.getDireccion());
        getBean().setDireccion_adicional(direccion_envio.getDireccionAdicional());
        getBean().setCiudad(direccion_envio.getCiudad());
        getBean().setProvincia(direccion_envio.getProvincia());
        getBean().setPrecio_envio(String.valueOf(direccion_envio.getPrecioEnvio()));
    }

}
