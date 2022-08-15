/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.mesas.presenter;

import com.jobits.pos.controller.areaventa.MesaService;
import com.jobits.pos.cordinator.DisplayType;
import com.jobits.pos.core.domain.models.Mesa;
import com.jobits.pos.main.Application;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.venta.mesas.MesaListView;

import java.beans.PropertyChangeEvent;
import java.util.Optional;

/**
 * JoBits
 *
 * @author Jorge
 */
public class MesaListViewPresenter extends AbstractViewPresenter<MesaListViewModel> {

    public static final String ACTION_ABRIR_MESA = "Abrir Mesa";
    public static final String ACTION_CANCELAR = "Cancelar";
    public static final String ACTION_CAMBIAR_AREA = "Cambiar area";

    private MesaService controller = PosDesktopUiModule.getInstance().getImplementation(MesaService.class);

    public MesaListViewPresenter() {
        super(new MesaListViewModel());
        addBeanPropertyChangeListener(MesaListViewModel.PROP_AREA_SELECCIONADA, (PropertyChangeEvent evt) -> {
            if (evt.getNewValue() != null) {
                onCambiarAreaClick();
            }
        });
        getBean().setLista_areas(controller.getListaAreasDisponibles());
        if (!getBean().getLista_areas().isEmpty()) {
            getBean().setArea_seleccionada(getBean().getLista_areas().get(0));
            //onCambiarAreaClick();
        }

    }

    public Mesa selectMesa() {
        Application.getInstance().getNavigator().navigateTo(MesaListView.VIEW_NAME, this, DisplayType.POPUP);
        return getBean().getElemento_seleccionado();
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
        registerOperation(new AbstractViewAction(ACTION_CAMBIAR_AREA) {
            @Override
            public Optional doAction() {
                onCambiarAreaClick();
                return Optional.empty();
            }

        });
        registerOperation(new AbstractViewAction(ACTION_CANCELAR) {
            @Override
            public Optional doAction() {
                onCancelar();
                return Optional.empty();
            }
        });
    }

    private void onAbrirMesaClick() {
        if (getBean().getElemento_seleccionado().isVacia()) {
            //ordenController = new OrdenController(getBean().getElemento_seleccionado());
        } else {
            //ordenController = new OrdenController(getBean().getElemento_seleccionado().getEstado().split(" ")[1]);
        }
        Application.getInstance().getNavigator().navigateUp();

    }

    private void onCancelar() {
        Application.getInstance().getNavigator().navigateUp();
    }

    private void onCambiarAreaClick() {
        getBean().setLista_elementos(controller.getListaMesas(getBean().getArea_seleccionada().getCodArea()));
    }

}
