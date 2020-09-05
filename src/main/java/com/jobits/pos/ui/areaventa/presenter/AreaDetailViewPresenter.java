/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.areaventa.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.adapters.repo.impl.AreaDAO;
import com.jobits.pos.controller.areaventa.AreaDetailController;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.domain.models.Area;
import com.jobits.pos.domain.models.Carta;
import com.jobits.pos.domain.models.ProductoVenta;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.TipoNotificacion;
import com.jobits.pos.ui.presenters.AbstractListViewPresenter;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.venta.presenter.VentaStatisticsViewModel;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Home
 */
public class AreaDetailViewPresenter extends AbstractViewPresenter<AreaDetailViewModel> {

    public static final String ACTION_CANCELAR = "Cancelar";
    public static final String ACTION_ACEPTAR = "Aceptar";
    public static final String ACTION_AGREGAR = "Agregar";
    public static final String ACTION_ELIMINAR = "Eliminar";

    private AreaDetailController service;
    Area area;

    public AreaDetailViewPresenter(AreaDetailController service) {
        super(new AreaDetailViewModel());
        this.service = service;
        if (service.isCreatingMode()) {
            area = service.createNewInstance();
        } else {
            area = service.getInstance();
        }
        fillForm();
        addListeners();
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
    private void addListeners() {
        getBean().addPropertyChangeListener(AreaDetailViewModel.PROP_CANT_MESAS_AREA, (PropertyChangeEvent evt) -> {
            System.out.println(getBean().getCant_mesas_area());
        });
    }
    private void onAceptarClick() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Desea guardar los cambios",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {

            area.setCodArea(getBean().getId_area());
            area.setNombre(getBean().getNombre_area());
            area.setCapacidad(getBean().getCant_mesas_area());
            area.setPorcientoPorServicio(getBean().getPorciento_servicio());
            area.setCartaList(getBean().getLista_menu_area());
            if (service.isCreatingMode()) {
                service.create(area);
            } else {
                service.update(area);
            }

            NavigationService.getInstance().navigateUp();//TODO: faltan los insumos
        }
    }

    private void onCancelarClick() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Desea descartar los cambios?",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            NavigationService.getInstance().navigateUp();
        }
    }

    private void onEliminarClick() {
        getBean().getLista_menu_area().remove(getBean().getMenu_seleccionado_area());
        if (getBean().getLista_menu_area().get(0) != null) {
            getBean().setMenu_seleccionado_area(getBean().getLista_menu_area().get(0));
        }
    }

    private void onAgregarClick() {
        getBean().getLista_menu_area().add(getBean().getMenu_selecionado());
    }

    private void fillForm() {
        getBean().setId_area(area.getCodArea());
        getBean().setNombre_area(area.getNombre());
        getBean().setCant_mesas_area(area.getCapacidad());
        getBean().setPorciento_servicio(area.getPorcientoPorServicio());
        getBean().getLista_menu_area().clear();
        getBean().getLista_menu_area().addAll(new ArrayListModel<>(area.getCartaList()));
        getBean().getLista_all_menus().clear();
        getBean().getLista_all_menus().addAll(new ArrayListModel<>(service.getCartaList()));
    }
}
