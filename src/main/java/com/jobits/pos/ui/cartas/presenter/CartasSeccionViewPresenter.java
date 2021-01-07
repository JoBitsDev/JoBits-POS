/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.cartas.presenter;

import com.jobits.pos.controller.seccion.MenuController;
import com.jobits.pos.controller.seccion.MenuService;
import com.jobits.pos.controller.seccion.SeccionDetailServiceImpl;
import com.jobits.pos.cordinator.DisplayType;
import com.jobits.pos.domain.models.Carta;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.TipoNotificacion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.cartas.SeccionDetailView;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import java.util.Optional;
import javax.swing.JOptionPane;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class CartasSeccionViewPresenter extends AbstractViewPresenter<CartasSeccionViewModel> {

    private MenuService controller;

    public static final String ACTION_AGREGAR_MENU = "Nueva Carta";
    public static final String ACTION_EDITAR_MENU = "Editar Carta";
    public static final String ACTION_ELIMINAR_MENU = "Eliminar Carta";
    public static final String ACTION_AGREGAR_SECCION = "Nueva Seccion";
    public static final String ACTION_EDITAR_SECCION = "Editar sección";
    public static final String ACTION_ELIMINAR_SECCION = "Eliminar Seccion";

    public CartasSeccionViewPresenter(MenuController controller) {
        super(new CartasSeccionViewModel());
        this.controller = controller;
        getBean().getLista_menu().addAll(controller.getCartaListService().getItems());
    }

    private void onNuevoMenuCLick() {//TODO: no actualiza correctamente nada en las vistas
        String nombre = JOptionPane.showInputDialog(null, "Introduzca el nombre de la Carta a crear",
                "Nueva Carta", JOptionPane.QUESTION_MESSAGE);
        controller.getCartaListService().createInstance(nombre);
        Carta menuSeleccionado = getBean().getMenu_seleccionado();
        getBean().getLista_menu().clear();
        getBean().getLista_menu().addAll(controller.getCartaListService().getItems());//TODO: cambiar el metodo create instance para agregar solamente el que se acaba de crear
        getBean().setMenu_seleccionado(menuSeleccionado);
        Application.getInstance().getNotificationService().notify(R.RESOURCE_BUNDLE.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
    }

    private void onEliminarMenuClick() {
        controller.getCartaListService().destroy(getBean().getMenu_seleccionado());
        getBean().setMenu_seleccionado(null);
        getBean().setLista_menu(controller.getCartaListService().getItems());//TODO: cambiar el metodo create instance para agregar solamente el que se acaba de crear
        Application.getInstance().getNotificationService().notify(R.RESOURCE_BUNDLE.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
    }

    private void onNuevaSeccionClick() {
        if (getBean().getMenu_seleccionado() == null) {
            throw new IllegalArgumentException("Seleccione una carta");
        }
        Application.getInstance().getNavigator().navigateTo(
                SeccionDetailView.VIEW_NAME,
                new SeccionDetailViewPresenter(new SeccionDetailServiceImpl(), null, getBean().getMenu_seleccionado()),
                DisplayType.POPUP);
        getBean().getLista_secciones().clear();
        getBean().setMenu_seleccionado(getBean().getMenu_seleccionado());
    }

    private void onEditarSeccionClick() {
        if (getBean().getMenu_seleccionado() == null) {
            throw new IllegalArgumentException("Seleccione una carta");
        }
        if (getBean().getSeccion_seleccionada() == null) {
            throw new IllegalArgumentException("Seleccione una seccion");
        }
        Application.getInstance().getNavigator().navigateTo(
                SeccionDetailView.VIEW_NAME,
                new SeccionDetailViewPresenter(
                        new SeccionDetailServiceImpl(), getBean().getSeccion_seleccionada(), getBean().getMenu_seleccionado()),
                DisplayType.POPUP);
        getBean().getLista_secciones().clear();
        getBean().setMenu_seleccionado(getBean().getMenu_seleccionado());
    }

    private void onEliminarSeccionClick() {
        controller.getCartaListService().removeSeccionFromCarta(getBean().getSeccion_seleccionada());
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
        registerOperation(new AbstractViewAction(ACTION_EDITAR_SECCION) {
            @Override
            public Optional doAction() {
                onEditarSeccionClick();
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
