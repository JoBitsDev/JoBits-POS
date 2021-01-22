/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.cartas.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.seccion.MenuController;
import com.jobits.pos.controller.seccion.SeccionDetailService;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.core.domain.models.Carta;
import com.jobits.pos.core.domain.models.Seccion;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.TipoNotificacion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class SeccionDetailViewPresenter extends AbstractViewPresenter<SeccionDetailViewModel> {

    private SeccionDetailService service;
    Seccion seccion;
    Carta carta;

    public static final String ACTION_CANCELAR = "Cancelar";
    public static final String ACTION_ACEPTAR = "Aceptar";
    public static final String ACTION_AGREGAR = "Agregar";
    public static final String ACTION_ELIMINAR = "Eliminar";

    public SeccionDetailViewPresenter(SeccionDetailService controller, Seccion seccion, Carta carta) {
        super(new SeccionDetailViewModel());
        this.service = controller;
        this.carta = carta;
        if (seccion == null) {
            this.seccion = new Seccion();
            service.setCreatingMode(true);
            getBean().setCrear_editar_button_text("Crear");
        } else {
            this.seccion = seccion;
            getBean().setCrear_editar_button_text("Editar");
        }
        refreshState();
    }

    @Override
    protected Optional refreshState() {
        getBean().setNombre_habilitado(false);
        getBean().setNombre_seccion(service.getSeccion(seccion.getNombreSeccion()).getNombreSeccion());
        getBean().setLista_secciones_agregadas(
                new ArrayListModel<>(service.getSeccion(seccion.getNombreSeccion()).getAgregadoEn()));
        List<Seccion> aux = new ArrayList();
        List<Carta> listaCartas = new MenuController().getCartaListService().getItems();
        for (Carta x : listaCartas) {
            aux.addAll(x.getSeccionList());
        }
        Collections.sort(aux);
        getBean().setLista_secciones(new ArrayListModel<>(aux));
        return super.refreshState(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_ACEPTAR) {
            @Override
            public Optional doAction() {
                onAceptarClick();
                return Optional.empty();
            }

        });
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
        registerOperation(new AbstractViewAction(ACTION_ELIMINAR) {
            @Override
            public Optional doAction() {
                onEliminarClick();
                return Optional.empty();
            }

        });
    }

    private void onCancelarClick() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Desea descartar los cambios?",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            NavigationService.getInstance().navigateUp();
        }
    }

    private void onAceptarClick() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Desea guardar los cambios",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            seccion.setNombreSeccion(getBean().getNombre_seccion());
            seccion.setAgregadoEn(getBean().getLista_secciones_agregadas());
            if (service.isCreatingMode()) {
                service.crearSeccion(carta, seccion);
            } else {
                service.editarSeccion(seccion);
            }
            NavigationService.getInstance().navigateUp();//TODO: faltan los insumos
            Application.getInstance().getNotificationService().notify(R.RESOURCE_BUNDLE.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
        }
    }

    private void onEliminarClick() {
        if (getBean().getSeccion_agregada_seleccionada() == null) {
            throw new IllegalArgumentException("Seleccione una seccion");
        }
        getBean().getLista_secciones_agregadas().remove(getBean().getSeccion_seleccionada());
        if (!getBean().getLista_secciones_agregadas().isEmpty()) {
            getBean().setSeccion_seleccionada(null);
        }
    }

    private void onAgregarClick() {
        if (getBean().getLista_secciones_agregadas().contains(getBean().getSeccion_seleccionada())) {
            throw new IllegalStateException("Ya la seccion se encuentra agregada");
        }
        getBean().getLista_secciones_agregadas().add(getBean().getSeccion_seleccionada());
    }

}
