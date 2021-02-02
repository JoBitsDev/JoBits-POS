/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.mesas.presenter;

import com.jobits.pos.controller.venta.OrdenService;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.usecase.mesa.MesaUseCase;
import java.beans.PropertyChangeEvent;
import java.util.Optional;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class MesaListViewPresenter extends AbstractViewPresenter<MesaListViewModel> {

    public static final String ACTION_ABRIR_MESA = "Abrir Mesa";
    public static final String ACTION_CAMBIAR_AREA = "Cambiar area";

    private MesaUseCase controller;

    public MesaListViewPresenter(MesaUseCase controller) {
        super(new MesaListViewModel());
        this.controller = controller;
        getBean().setLista_areas(controller.getListaAreasDisponibles());
        if (!getBean().getLista_areas().isEmpty()) {
            getBean().setArea_seleccionada(getBean().getLista_areas().get(0));
            onCambiarAreaClick();
        }
        addBeanPropertyChangeListener(MesaListViewModel.PROP_AREA_SELECCIONADA, (PropertyChangeEvent evt) -> {
            if (evt.getNewValue() != null) {
                getBean().setLista_elementos(getBean().getArea_seleccionada().getMesaList());
            }
        });

    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_ABRIR_MESA) {
            @Override
            public Optional doAction() {
                onAbrirMesaClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_ABRIR_MESA) {
            @Override
            public Optional doAction() {
                onCambiarAreaClick();
                return Optional.empty();
            }

        });
    }

    private void onAbrirMesaClick() {
        OrdenService ordenController;

        if (getBean().getElemento_seleccionado().isVacia()) {
            //ordenController = new OrdenController(getBean().getElemento_seleccionado());
        } else {
            //ordenController = new OrdenController(getBean().getElemento_seleccionado().getEstado().split(" ")[1]);
        }
     //   Application.getInstance().getNavigator().navigateTo(OrdenDetailFragmentView.VIEW_NAME, new OrdenDetailViewPresenter(ordenController));

    }

    private void onCambiarAreaClick() {
        getBean().setLista_elementos(controller.getListaMesas(getBean().getArea_seleccionada()));
    }

}
