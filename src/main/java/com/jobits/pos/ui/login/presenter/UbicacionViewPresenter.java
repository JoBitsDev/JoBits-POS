/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.login.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import java.util.Arrays;
import java.util.Optional;
import org.jobits.db.core.domain.UbicacionConexionModel;
import org.jobits.db.core.usecase.UbicacionConexionService;

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
    private final UbicacionConexionService service;

    public UbicacionViewPresenter(UbicacionConexionService controller) {
        super(new UbicacionViewModel());
        this.service = controller;
        loadFormData(this.service.getUbicaciones().getUbicacionActiva());
    }

    private void onEditarUbicacionCLick() {
        UbicacionConexionModel uc = new UbicacionConexionModel(
                getBean().getNombre_ubicacion(),
                getBean().getUrl(),
                getBean().getUsuario(),
                getBean().getPassword(), getBean().getDriver(), getBean().getTipo_servidor_seleccionado());
        service.editUbicacion(uc, service.getUbicaciones().getSelectedUbicacion());
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

    private void loadFormData(UbicacionConexionModel ubicacionActiva) {
        getBean().setUsuario(ubicacionActiva.getUsuario());
        getBean().setDriver(ubicacionActiva.getDriver());
        getBean().setLista_tipo_servidor(new ArrayListModel<>(Arrays.asList(UbicacionConexionModel.TipoUbicacion.values())));
        getBean().setNombre_ubicacion(ubicacionActiva.getNombreUbicacion());
        getBean().setPassword(ubicacionActiva.getContrasena());
        getBean().setTipo_servidor_seleccionado(ubicacionActiva.getTipoUbicacion());
        getBean().setUrl(ubicacionActiva.getUrl());
    }

}
