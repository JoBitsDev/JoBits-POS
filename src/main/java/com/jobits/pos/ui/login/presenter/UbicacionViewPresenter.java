/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.login.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.login.UbicacionConexionController;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.domain.UbicacionConexionModel;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private final UbicacionConexionController controller;

    public UbicacionViewPresenter(UbicacionConexionController controller) {
        super(new UbicacionViewModel());
        this.controller = controller;
        loadFormData(this.controller.getUbicaciones().getUbicacionActiva());
    }

    private void onEditarUbicacionCLick() {
        UbicacionConexionModel uc = new UbicacionConexionModel(
                getBean().getNombre_ubicacion(),
                getBean().getUrl(),
                getBean().getUsuario(),
                getBean().getPassword(), getBean().getDriver(), getBean().getTipo_servidor_seleccionado());
        try {
            controller.editUbicacion(uc, controller.getUbicaciones().getSelectedUbicacion());
            NavigationService.getInstance().navigateUp();//TODO: codigo de navegacion
        } catch (IOException | IllegalArgumentException ex) {
            Logger.getLogger(UbicacionViewPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        getBean().setNombre_ubicacion(ubicacionActiva.getNombre());
        getBean().setPassword(ubicacionActiva.getContrasena());
        getBean().setTipo_servidor_seleccionado(ubicacionActiva.getTipoUbicacion());
        getBean().setUrl(ubicacionActiva.getUrl());
    }

}
