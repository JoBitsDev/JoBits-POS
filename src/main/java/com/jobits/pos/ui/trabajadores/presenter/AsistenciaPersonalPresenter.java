/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.trabajadores.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.trabajadores.AsistenciaPersonalController;
import com.jobits.pos.domain.models.AsistenciaPersonal;
import com.jobits.pos.domain.models.Venta;
import com.jobits.pos.main.Application;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import java.util.Optional;
import javax.swing.JOptionPane;

/**
 *
 * @author Home
 */
public class AsistenciaPersonalPresenter extends AbstractViewPresenter<AsistenciaPersonalViewModel> {

    public static final String ACTION_IMPRIMIR_ASISTENCIA = "Imprimir Asistencia",
            ACTION_A_MAYORES = "A Mayores",
            ACTION_AGREGAR_PERSONAL = "Agregar Personal",
            ACTION_ELIMINAR_PERSONAL = "Eliminar Personal";

    private AsistenciaPersonalController personalService;
    private Venta venta;

    public AsistenciaPersonalPresenter(Venta venta) {
        super(new AsistenciaPersonalViewModel());
        this.venta = venta;
        personalService = new AsistenciaPersonalController(this.venta);
        updateBeanData();
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_IMPRIMIR_ASISTENCIA) {
            @Override
            public Optional doAction() {
                personalService.imprimirAsistencia();
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

    private void updateBeanData() {
        getBean().getLista_personal_disponible().clear();
        getBean().getLista_personal_disponible().addAll(new ArrayListModel<>(personalService.getTrabajadoresList()));
        getBean().getLista_personal_contenido().clear();
        getBean().getLista_personal_contenido().addAll(new ArrayListModel<>(personalService.getPersonalTrabajando(venta)));
    }

    private void onEliminarClick() {
        personalService.destroy(getBean().getPersonal_contenido_selecionado(), true);
        getBean().getLista_personal_contenido().clear();
        getBean().getLista_personal_contenido().addAll(new ArrayListModel<>(personalService.getPersonalTrabajando(venta)));
    }

    private void onAgregarClick() {
        personalService.createNewInstance(getBean().getPersonal_disponible_seleccionado(), venta);
        getBean().getLista_personal_contenido().clear();
        getBean().getLista_personal_contenido().addAll(new ArrayListModel<>(personalService.getPersonalTrabajando(venta)));
    }

    private void onAMayoresClick() {
        if (getBean().getPersonal_contenido_selecionado() == null) {
            JOptionPane.showMessageDialog(Application.getInstance().getMainWindow(), "Seleccione un personal primero");
        } else {
            float aMayores = Float.parseFloat(JOptionPane.showInputDialog("Introduzca el valor de A mayores"));
            AsistenciaPersonal personal = getBean().getPersonal_contenido_selecionado();
            personalService.updateAMayores(personal, aMayores);
            getBean().getLista_personal_contenido().clear();
            getBean().getLista_personal_contenido().addAll(new ArrayListModel<>(personalService.getPersonalTrabajando(venta)));
        }
    }

}
