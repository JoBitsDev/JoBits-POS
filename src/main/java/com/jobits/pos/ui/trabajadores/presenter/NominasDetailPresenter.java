/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.trabajadores.presenter;

import com.jobits.pos.controller.trabajadores.NominasController;
import com.jobits.pos.domain.AsistenciaPersonalEstadisticas;
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
public class NominasDetailPresenter extends AbstractViewPresenter<NominasDetailViewModel> {

    public static final String ACTION_IMPRIMIR = "Imprimir";
    public static final String ACTION_PAGAR = "Pagar";
    public static final String ACTION_BUSCAR = "Buscar";
    public static String ACTION_SELECCIONAR_TODOS = "Seleccionar todos";

    NominasController controller;

    public NominasDetailPresenter(NominasController controller) {
        super(new NominasDetailViewModel());
        this.controller = controller;
    }

    private void onBuscarClick() {
        if (getBean().getDesde() == null || getBean().getHasta() == null) {
            fireToast("Campos de fechas vacios");return;
        }
        if (getBean().getDesde().isAfter(getBean().getHasta())) {
            fireToast("Fechas incorrectas");return;
            
        }
        getBean().getLista_personal().clear();
        getBean().getLista_personal().addAll(controller.getPersonalActivo(getBean().getFecha_desde(), getBean().getFecha_hasta()));
        

    }

    private void onPagarClick() {
        for (AsistenciaPersonalEstadisticas i : getBean().getLista_personal()) {
            if (i.isUse()) {
                controller.pagar(i);
            }
        }
        getBean().getLista_personal().fireContentsChanged(0, getBean().getLista_personal().getSize());
    }

    private void onImprimirClick() {
        controller.imprimirEstadisticas(getBean().getLista_personal());
    }

    private void onSeleccionarTodosClick() {
        for (AsistenciaPersonalEstadisticas x : getBean().getLista_personal()) {
            x.setUse(getBean().isSeleccionar_todo_seleccionado());
        }
        getBean().getLista_personal().fireContentsChanged(0, getBean().getLista_personal().getSize());
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_PAGAR) {
            @Override
            public Optional doAction() {
                onPagarClick();
                return Optional.empty();
            }
        });

        registerOperation(new AbstractViewAction(ACTION_BUSCAR) {
            @Override
            public Optional doAction() {
                onBuscarClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_IMPRIMIR) {
            @Override
            public Optional doAction() {
                onImprimirClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_SELECCIONAR_TODOS) {
            @Override
            public Optional doAction() {
                onSeleccionarTodosClick();
                return Optional.empty();
            }
        });
    }

}
