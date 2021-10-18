/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.cartas.presenter;

import com.jobits.pos.controller.seccion.CartaListService;
import com.jobits.pos.controller.seccion.SeccionListService;
import com.jobits.pos.cordinator.DisplayType;
import com.jobits.pos.core.domain.models.Carta;
import com.jobits.pos.core.domain.models.Seccion;
import com.jobits.pos.main.Application;
import com.root101.clean.core.app.services.utils.TipoNotificacion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.cartas.SeccionDetailView;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.root101.clean.core.domain.services.ResourceHandler;
import java.util.ArrayList;
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

    private final CartaListService cartaService = PosDesktopUiModule.getInstance().getImplementation(CartaListService.class);
    private final SeccionListService seccionService = PosDesktopUiModule.getInstance().getImplementation(SeccionListService.class);

    public static final String ACTION_AGREGAR_MENU = "Nueva Carta";
    public static final String ACTION_EDITAR_MENU = "Editar Carta";
    public static final String ACTION_ELIMINAR_MENU = "Eliminar Carta";
    public static final String ACTION_AGREGAR_SECCION = "Nueva Seccion";
    public static final String ACTION_EDITAR_SECCION = "Editar sección";
    public static final String ACTION_ELIMINAR_SECCION = "Eliminar Seccion";

    public CartasSeccionViewPresenter() {
        super(new CartasSeccionViewModel());
        getBean().getLista_menu().addAll(cartaService.findAll());
    }

    private void onNuevoMenuCLick() {//TODO: no actualiza correctamente nada en las vistas
        String nombre = JOptionPane.showInputDialog(null, "Introduzca el nombre de la Carta a crear",
                "Nueva Carta", JOptionPane.QUESTION_MESSAGE);
        if (nombre != null) {
            Carta carta = new Carta();
            carta.setAreaList(new ArrayList<>());
            carta.setMonedaPrincipal(R.COIN_SUFFIX);
            carta.setNombreCarta(nombre);
            carta.setSeccionList(new ArrayList<>());
            cartaService.create(carta);
            Carta menuSeleccionado = getBean().getMenu_seleccionado();
            getBean().setLista_menu(cartaService.findAll());
            getBean().setMenu_seleccionado(menuSeleccionado);
            Application.getInstance().getNotificationService().notify(ResourceHandler.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
        }
    }

    private void onEliminarMenuClick() {
        Carta selected = getBean().getMenu_seleccionado();
        if (selected != null) {
            if ((boolean) Application.getInstance().getNotificationService().
                    showDialog("Esta seguro que desea eliminar: " + selected,
                            TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
                cartaService.destroy(getBean().getMenu_seleccionado());
                getBean().setMenu_seleccionado(null);
                getBean().setLista_menu(cartaService.findAll());//TODO: cambiar el metodo create instance para agregar solamente el que se acaba de crear
                Application.getInstance().getNotificationService().notify(ResourceHandler.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
            }
        }
    }

    private void onNuevaSeccionClick() {
        if (getBean().getMenu_seleccionado() == null) {
            throw new IllegalArgumentException("Seleccione una carta");
        }
        Application.getInstance().getNavigator().navigateTo(
                SeccionDetailView.VIEW_NAME,
                new SeccionDetailViewPresenter(null, getBean().getMenu_seleccionado()),
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
                new SeccionDetailViewPresenter(getBean().getSeccion_seleccionada(), getBean().getMenu_seleccionado()),
                DisplayType.POPUP);
        getBean().getLista_secciones().clear();
        getBean().setMenu_seleccionado(getBean().getMenu_seleccionado());
    }

    private void onEliminarSeccionClick() {
        Seccion selected = getBean().getSeccion_seleccionada();
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Esta seguro que desea eliminar: " + selected,
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            seccionService.destroy(selected);
            getBean().setMenu_seleccionado(getBean().getMenu_seleccionado());
        }
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
