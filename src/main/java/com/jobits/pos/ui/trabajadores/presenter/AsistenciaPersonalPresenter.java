/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.trabajadores.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.trabajadores.AsistenciaPersonalService;
import com.jobits.pos.controller.trabajadores.PersonalUseCase;
import com.jobits.pos.core.domain.models.AsistenciaPersonal;
import com.jobits.pos.core.domain.models.Personal;
import com.jobits.pos.main.Application;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.utils.NumberPad;
import com.root101.clean.core.app.services.utils.TipoNotificacion;

import java.util.Optional;

/**
 * @author Home
 */
public class AsistenciaPersonalPresenter extends AbstractViewPresenter<AsistenciaPersonalViewModel> {

    public static final String ACTION_IMPRIMIR_ASISTENCIA = "Imprimir Asistencia",
            ACTION_A_MAYORES = "A Mayores",
            ACTION_AGREGAR_PERSONAL = "Agregar Personal",
            ACTION_ELIMINAR_PERSONAL = "Eliminar Personal",
            PROP_VALUE_CHANGED = "Valores Modificados";

    private final AsistenciaPersonalService asistenciaPersonalService = PosDesktopUiModule.getInstance().getImplementation(AsistenciaPersonalService.class);
    private final PersonalUseCase personalService = PosDesktopUiModule.getInstance().getImplementation(PersonalUseCase.class);
    private int idVenta;

    public AsistenciaPersonalPresenter(int idVenta) {
        super(new AsistenciaPersonalViewModel());
        this.idVenta = idVenta;
        refreshState();
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
        refreshState();
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_IMPRIMIR_ASISTENCIA) {
            @Override
            public Optional doAction() {
                asistenciaPersonalService.imprimirAsistencia(idVenta);
                return Optional.empty();
            }

        });
        registerOperation(new AbstractViewAction(ACTION_A_MAYORES) {
            @Override
            public Optional doAction() {
                onAMayoresClick();
                return Optional.empty();
            }

        });
        registerOperation(new AbstractViewAction(ACTION_AGREGAR_PERSONAL) {
            @Override
            public Optional doAction() {
                onAgregarClick();
                return Optional.empty();
            }

        });
        registerOperation(new AbstractViewAction(ACTION_ELIMINAR_PERSONAL) {
            @Override
            public Optional doAction() {
                onEliminarClick();
                return Optional.empty();
            }
        });
    }

    @Override
    protected Optional refreshState() {
        getBean().getLista_personal_disponible().clear();
        getBean().getLista_personal_disponible().addAll(new ArrayListModel<>(personalService.findAll()));
        getBean().getLista_personal_contenido().clear();
        getBean().getLista_personal_contenido().addAll(new ArrayListModel<>(asistenciaPersonalService.getPersonalTrabajando(idVenta)));
        return super.refreshState(); //To change body of generated methods, choose Tools | Templates.
    }

    private void onEliminarClick() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Esta seguro que desea eliminar al personal seleccionado?",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            AsistenciaPersonal ap = getBean().getPersonal_contenido_selecionado();
            if (ap != null) {
                asistenciaPersonalService.destroy(ap.getAsistenciaPersonalPK().getVentaid(),
                        ap.getAsistenciaPersonalPK().getPersonalusuario());
                refreshState();

            } else {
                throw new IllegalArgumentException("Seleccione un trabajador primero");
            }
        }
    }

    private void onAgregarClick() {
        Personal personal = getBean().getPersonal_disponible_seleccionado();
        if (personal != null) {
            AsistenciaPersonal ap = new AsistenciaPersonal(idVenta, personal.getUsuario());

            ap.setPersonal(personal);
            // ap.setVenta(idVenta);
            asistenciaPersonalService.create(ap.getAsistenciaPersonalPK().getVentaid(), ap.getAsistenciaPersonalPK().getPersonalusuario());
            refreshState();
            firePropertyChange(PROP_VALUE_CHANGED, false, true);
        }
    }

    private void onAMayoresClick() {
        Float cantidad = new NumberPad().showView();
        if (cantidad != null) {
            AsistenciaPersonal personal = getBean().getPersonal_contenido_selecionado();
            asistenciaPersonalService.updateAMayores(
                    personal.getAsistenciaPersonalPK().getVentaid(),
                    personal.getAsistenciaPersonalPK().getPersonalusuario(), cantidad);
            getBean().getLista_personal_contenido().clear();
            getBean().getLista_personal_contenido().addAll(new ArrayListModel<>(
                    asistenciaPersonalService.getPersonalTrabajando(idVenta)));
        }

    }

}
