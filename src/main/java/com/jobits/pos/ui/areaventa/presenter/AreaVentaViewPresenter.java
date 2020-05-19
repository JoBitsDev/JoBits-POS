/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.areaventa.presenter;

import com.jobits.pos.controller.areaventa.AreaVentaController;
import com.jobits.pos.ui.menu.presenter.*;
import com.jobits.pos.controller.seccion.MenuController;
import com.jobits.pos.domain.models.Area;
import com.jobits.pos.domain.models.Carta;
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

    private AreaVentaController controller;

    public static final String ACTION_AGREGAR_AREA = "Nueva area";
    public static final String ACTION_EDITAR_AREA = "Editar";
    public static final String ACTION_ELIMINAR_AREA = "Eliminar area";
    public static final String ACTION_AGREGAR_MESA = "Nueva mesa";
    public static final String ACTION_ELIMINAR_MESA = "Eliminar mesa";

    public AreaVentaViewPresenter(AreaVentaController controller) {
        super(new AreaVentaViewModel());
        this.controller = controller;
        getBean().getLista_area().addAll(controller.getItems());
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

    private void onNuevaAreaCLick() {//TODO: no actualiza correctamente nada en las vistas
        controller.createInstance();
        Area menuSeleccionado = getBean().getArea_seleccionada();
        getBean().getLista_area().clear();
        getBean().getLista_area().addAll(controller.getItems());//TODO: cambiar el metodo create instance para agregar solamente el que se acaba de crear
        getBean().setArea_seleccionada(menuSeleccionado);
    }

    private void onEliminarAreaClick() {
        if (getBean().getArea_seleccionada() == null) {
            NotificationService.getInstance().notify("Debe seleccionar una area", TipoNotificacion.ERROR);
            return;
        }
        controller.destroy(getBean().getArea_seleccionada());
        getBean().setArea_seleccionada(null);
        getBean().getLista_area().clear();
        getBean().getLista_area().addAll(controller.getItems());//TODO: cambiar el metodo create instance para agregar solamente el que se acaba de crear

    }

    private void onEditarAreaClick() {
        if (getBean().getArea_seleccionada() == null) {
            NotificationService.getInstance().notify("Debe seleccionar una area", TipoNotificacion.ERROR);
            return;
        }
        controller.getDetailControllerForEdit(getBean().getArea_seleccionada());
        getBean().getLista_area().fireContentsChanged(0, getBean().getLista_area().getSize());
        
    }

    private void onNuevaMesaClick() {
        if (getBean().getArea_seleccionada() == null) {
            NotificationService.getInstance().notify("Debe seleccionar un area", TipoNotificacion.ERROR);
            return;
        }
        controller.createMesa(getBean().getArea_seleccionada());
        getBean().getLista_mesas().clear();
        getBean().setArea_seleccionada(getBean().getArea_seleccionada());

    }

    private void onEliminarMesaClick() {
        if (getBean().getMesa_seleccionada() == null) {
            NotificationService.getInstance().notify("Debe seleccionar una seccion", TipoNotificacion.ERROR);
            return;
        }
        controller.removeMesa(getBean().getMesa_seleccionada());
        getBean().setArea_seleccionada(getBean().getArea_seleccionada());
    }

}
