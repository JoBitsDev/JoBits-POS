/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.cartas.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.seccion.SeccionDetailService;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.domain.models.Seccion;
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
 * @author Jorge
 *
 */
public class SeccionEditViewPresenter extends AbstractViewPresenter<SeccionEditViewModel> {

    private SeccionDetailService service;
    Seccion seccion;

    public static final String ACTION_CANCELAR = "Cancelar";
    public static final String ACTION_ACEPTAR = "Aceptar";
    public static final String ACTION_AGREGAR = "Agregar";
    public static final String ACTION_ELIMINAR = "Eliminar";

    public SeccionEditViewPresenter(SeccionDetailService controller) {
        super(new SeccionEditViewModel());
        this.service = controller;
        if (service.isCreatingMode()) {
            seccion = service.crearNuevaInstancia();
        } else {
            seccion = service.getSeccion();
        }
    }

    @Override
    protected Optional refreshState() {
        getBean().setNombre_seccion(service.getSeccion().getNombreSeccion());
        getBean().setLista_secciones_agregadas(new ArrayListModel<>(service.getSeccion().getAgregos()));
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
            seccion.setAgregos(getBean().getLista_secciones_agregadas());
            if (service.isCreatingMode()) {
                service.crearSeccion(seccion);
            } else {
                service.editarSeccion(seccion);
            }
            NavigationService.getInstance().navigateUp();//TODO: faltan los insumos
        }
    }

    private void onEliminarClick() {

        if (getBean().getSeccion_seleccionada() != null) {
            getBean().getLista_secciones_agregadas().remove(getBean().getSeccion_seleccionada());
            if (!getBean().getLista_secciones_agregadas().isEmpty()) {
                getBean().setSeccion_seleccionada(null);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una Seccion primero");
        }
    }

    private void onAgregarClick() {
        if (!getBean().getLista_secciones_agregadas().contains(getBean().getSeccion_seleccionada())) {
            getBean().getLista_secciones_agregadas().add(getBean().getSeccion_seleccionada());
        } else {
            JOptionPane.showMessageDialog(null, "Ya la seccion se encuentra agregada");
        }
    }

}
