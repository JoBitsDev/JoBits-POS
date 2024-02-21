/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.login.presenter;

import com.jobits.pos.client.webconnection.login.model.UbicacionModel;
import com.jobits.pos.client.webconnection.login.model.UbicacionModelWrapper;
import com.jobits.pos.client.webconnection.login.model.UbicacionService;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import java.util.Optional;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class UbicacionViewPresenter extends AbstractViewPresenter<UbicacionViewModel> {

    public static final String ACTION_ACEPTAR_EDICION = "Editar ubicación";
    public static String ACTION_CANCELAR_EDICION = "Cancelar";
    UbicacionService service;

    public UbicacionViewPresenter(UbicacionService service) {
        super(new UbicacionViewModel());
        this.service = service;
        var ubicaciones = service.loadLocations();
        loadFormData(ubicaciones.getUbicaciones().get(ubicaciones.getUbicacionSeleccionada()));
    }

    private void onEditarUbicacionCLick() {
        var ubicacion = UbicacionModel.builder()
                .nombre(getBean().getNombre_ubicacion())
                .ip(getBean().getIp())
                .puerto(getBean().getPuerto())
                .usuario(getBean().getUsuario())
                .password(getBean().getPassword())
                .usuarioId(Integer.parseInt(getBean().getIdUsuario()))
                .baseDatosId(Integer.parseInt(getBean().getIdBaseDatos())).build();

        var ubicaciones = service.loadLocations();
        var ubicacionesList = ubicaciones.getUbicaciones();
        ubicacionesList.set(ubicaciones.getUbicacionSeleccionada(), ubicacion);
        service.saveLocations(new UbicacionModelWrapper(ubicacionesList, ubicaciones.getUbicacionSeleccionada()));
        NavigationService.getInstance().navigateUp();//TODO: codigo de navegacion
    }

    private void onCancelar() {
        NavigationService.getInstance().navigateUp();
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_ACEPTAR_EDICION) {
            @Override
            public Optional doAction() {
                onEditarUbicacionCLick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_CANCELAR_EDICION) {
            @Override
            public Optional doAction() {
                onCancelar();
                return Optional.empty();
            }

        });

    }

    private void loadFormData(UbicacionModel ubicacionActiva) {
        getBean().setUsuario(ubicacionActiva.getUsuario());
        getBean().setIdBaseDatos("" + ubicacionActiva.getBaseDatosId());
        getBean().setIdUsuario("" + ubicacionActiva.getUsuarioId());
        getBean().setNombre_ubicacion(ubicacionActiva.getNombre());
        getBean().setPassword(ubicacionActiva.getPassword());
        getBean().setIp(ubicacionActiva.getIp());
        getBean().setPuerto(ubicacionActiva.getPuerto());
    }

}
