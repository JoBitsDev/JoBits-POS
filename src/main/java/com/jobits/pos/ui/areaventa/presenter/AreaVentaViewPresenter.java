/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.areaventa.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.areaventa.AreaDetailController;
import com.jobits.pos.controller.areaventa.AreaVentaController;
import com.jobits.pos.controller.areaventa.AreaVentaService;
import com.jobits.pos.ui.menu.presenter.*;
import com.jobits.pos.controller.seccion.MenuController;
import com.jobits.pos.cordinator.DisplayType;
import com.jobits.pos.domain.models.Area;
import com.jobits.pos.domain.models.Carta;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.NotificationService;
import com.jobits.pos.notification.TipoNotificacion;
import com.jobits.pos.ui.areaventa.AreaCreateEditView;
import com.jobits.pos.ui.areaventa.AreaVentaListView;
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
public class AreaVentaViewPresenter extends AbstractViewPresenter<AreaVentaViewModel> {

    private AreaVentaService service;

    public static final String ACTION_AGREGAR_AREA = "Nueva area";
    public static final String ACTION_EDITAR_AREA = "Editar";
    public static final String ACTION_ELIMINAR_AREA = "Eliminar area";
    public static final String ACTION_AGREGAR_MESA = "Nueva mesa";
    public static final String ACTION_ELIMINAR_MESA = "Eliminar mesa";

    public AreaVentaViewPresenter(AreaVentaService service) {
        super(new AreaVentaViewModel());
        this.service = service;
        getBean().getLista_area().addAll(service.getItems());
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_AGREGAR_AREA) {
            @Override
            public Optional doAction() {
                onNuevaAreaCLick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_ELIMINAR_AREA) {
            @Override
            public Optional doAction() {
                onEliminarAreaClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_AGREGAR_MESA) {
            @Override
            public Optional doAction() {
                onNuevaMesaClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_ELIMINAR_MESA) {
            @Override
            public Optional doAction() {
                onEliminarMesaClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_EDITAR_AREA) {
            @Override
            public Optional doAction() {
                onEditarAreaClick();
                return Optional.empty();
            }
        });
    }

    @Override
    protected Optional refreshState() {
        if (getBean().getArea_seleccionada() == null) {
            getBean().getLista_area().clear();
            getBean().getLista_area().addAll(new ArrayListModel<>(service.getItems()));
            getBean().getLista_mesas().clear();
        } else if (getBean().getArea_seleccionada() != null) {
            getBean().getLista_area().clear();
            getBean().getLista_area().addAll(new ArrayListModel<>(service.getItems()));
            getBean().getLista_mesas().clear();
            getBean().getLista_mesas().addAll(new ArrayListModel<>(getBean().getArea_seleccionada().getMesaList()));
        }
        return Optional.empty();
    }

    private void onNuevaAreaCLick() {//TODO: no actualiza correctamente nada en las vistas
        Application.getInstance().getNavigator().navigateTo("Crear Area", null, DisplayType.POPUP);
        refreshState();
    }

    private void onEliminarAreaClick() {
        if (getBean().getArea_seleccionada() == null) {
            Application.getInstance().getNotificationService().notify("Debe seleccionar una area", TipoNotificacion.ERROR);
            return;
        }
        service.destroy(getBean().getArea_seleccionada());
        getBean().setArea_seleccionada(null);
        getBean().setLista_area(service.getItems());//TODO: cambiar el metodo create instance para agregar solamente el que se acaba de crear

    }

    private void onEditarAreaClick() {
        if (getBean().getArea_seleccionada() == null) {
            Application.getInstance().getNotificationService().notify("Debe seleccionar una area", TipoNotificacion.ERROR);
            return;
        }
        Application.getInstance().getNavigator().navigateTo(
                "Crear Area",
                new AreaDetailViewPresenter(
                        new AreaDetailController(
                                getBean().getArea_seleccionada())),
                DisplayType.POPUP);

//        service.getDetailControllerForEdit(getBean().getArea_seleccionada());
        getBean().getLista_area().fireContentsChanged(0, getBean().getLista_area().getSize());
        refreshState();
    }

    private void onNuevaMesaClick() {
        if (getBean().getArea_seleccionada() == null) {
            Application.getInstance().getNotificationService().notify("Debe seleccionar un area", TipoNotificacion.ERROR);
            return;
        }
        service.createMesa(getBean().getArea_seleccionada());
        getBean().getLista_mesas().clear();
        getBean().setArea_seleccionada(getBean().getArea_seleccionada());

    }

    private void onEliminarMesaClick() {
        if (getBean().getMesa_seleccionada() == null) {
            Application.getInstance().getNotificationService().notify("Debe seleccionar una seccion", TipoNotificacion.ERROR);
            return;
        }
        service.removeMesa(getBean().getMesa_seleccionada());
        getBean().setArea_seleccionada(getBean().getArea_seleccionada());
    }

}
