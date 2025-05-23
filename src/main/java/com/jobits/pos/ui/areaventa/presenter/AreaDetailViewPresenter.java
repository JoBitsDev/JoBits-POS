/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.areaventa.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.areaventa.AreaVentaService;
import com.jobits.pos.controller.seccion.CartaListService;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.core.domain.models.Area;
import com.jobits.pos.core.domain.models.Carta;
import com.jobits.pos.main.Application;
import com.root101.clean.core.app.services.utils.TipoNotificacion;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import java.util.ArrayList;
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

    private AreaVentaService service = PosDesktopUiModule.getInstance().getImplementation(AreaVentaService.class);
    private CartaListService cartaService = PosDesktopUiModule.getInstance().getImplementation(CartaListService.class);

    private final boolean creatingMode;
    Area area;

    public AreaDetailViewPresenter(Area area, boolean creatingMode) {
        super(new AreaDetailViewModel());
        this.creatingMode = creatingMode;
        this.area = area;
        if (creatingMode) {
            this.area = new Area();
            this.area.setCapacidad(0);
            this.area.setCartaList(new ArrayList<>());
            this.area.setMesaList(new ArrayList<>());
            this.area.setNombre("");
            this.area.setPorcientoPorServicio(0);
        }
        fillForm();
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

    private void onAceptarClick() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Esta seguro que desea continuar?",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {

            area.setCodArea(getBean().getId_area());
            area.setNombre(getBean().getNombre_area());
            area.setCapacidad(getBean().getCant_mesas_area());
            area.setPorcientoPorServicio(getBean().getPorciento_servicio());
            area.setCartaList(getBean().getLista_menu_area());
            if (creatingMode) {
                service.create(area);
            } else {
                service.edit(area);
            }

            NavigationService.getInstance().navigateUp();//TODO: faltan los insumos
        }
    }

    private void onCancelarClick() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Esta seguro que desea cancelar?",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            NavigationService.getInstance().navigateUp();
        }
    }

    private void onEliminarClick() {
        Carta selected = getBean().getMenu_seleccionado_area();
        getBean().getLista_menu_area().remove(selected);
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
        getBean().getLista_all_menus().addAll(new ArrayListModel<>(cartaService.findAll()));
    }
}
