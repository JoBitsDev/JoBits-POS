/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.cartas.presenter;

import com.jobits.pos.controller.seccion.MenuController;
import com.jobits.pos.domain.models.Carta;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.NotificationService;
import com.jobits.pos.notification.TipoNotificacion;
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
public class CartasSeccionViewPresenter extends AbstractViewPresenter<CartasSeccionViewModel> {

    private MenuController controller;

    public static final String ACTION_AGREGAR_MENU = "Nueva Carta";
    public static final String ACTION_EDITAR_MENU = "Editar Carta";
    public static final String ACTION_ELIMINAR_MENU = "Eliminar Carta";
    public static final String ACTION_AGREGAR_SECCION = "Nueva Seccion";
    public static final String ACTION_EDITAR_SECCION = "Editar sección";
    public static final String ACTION_ELIMINAR_SECCION = "Eliminar Seccion";

    public CartasSeccionViewPresenter(MenuController controller) {
        super(new CartasSeccionViewModel());
        this.controller = controller;
        getBean().getLista_menu().addAll(controller.getCartaListController().getItems());
    }

    private void onNuevoMenuCLick() {//TODO: no actualiza correctamente nada en las vistas
        controller.getCartaListController().createInstance();
        Carta menuSeleccionado = getBean().getMenu_seleccionado();
        getBean().getLista_menu().clear();
        getBean().getLista_menu().addAll(controller.getCartaListController().getItems());//TODO: cambiar el metodo create instance para agregar solamente el que se acaba de crear
        getBean().setMenu_seleccionado(menuSeleccionado);
    }

    private void onEliminarMenuClick() {
        if (getBean().getMenu_seleccionado() == null) {
            Application.getInstance().getNotificationService().notify("Debe seleccionar una Carta", TipoNotificacion.ERROR);
            return;
        }
        controller.getCartaListController().destroy(getBean().getMenu_seleccionado());
        getBean().setMenu_seleccionado(null);
        getBean().setLista_menu(controller.getCartaListController().getItems());//TODO: cambiar el metodo create instance para agregar solamente el que se acaba de crear

    }

    private void onNuevaSeccionClick() {
        if (getBean().getMenu_seleccionado() == null) {
            Application.getInstance().getNotificationService().notify("Debe seleccionar una Carta", TipoNotificacion.ERROR);
            return;
        }
        controller.getCartaListController().createSeccion(getBean().getMenu_seleccionado());
        getBean().getLista_secciones().clear();
        getBean().setMenu_seleccionado(getBean().getMenu_seleccionado());

    }

    private void onEliminarSeccionClick() {
        if (getBean().getSeccion_seleccionada() == null) {
            Application.getInstance().getNotificationService().notify("Debe seleccionar una seccion", TipoNotificacion.ERROR);
            return;
        }
        controller.getCartaListController().removeSeccionFromCarta(getBean().getSeccion_seleccionada());
        getBean().setMenu_seleccionado(getBean().getMenu_seleccionado());
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_AGREGAR_MENU) {
            @Override
            public Optional doAction() {
                onNuevoMenuCLick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_ELIMINAR_MENU) {
            @Override
            public Optional doAction() {
                onEliminarMenuClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_AGREGAR_SECCION) {
            @Override
            public Optional doAction() {
                onNuevaSeccionClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_ELIMINAR_SECCION) {
            @Override
            public Optional doAction() {
                onEliminarSeccionClick();
                return Optional.empty();
            }
        });
    }

}
