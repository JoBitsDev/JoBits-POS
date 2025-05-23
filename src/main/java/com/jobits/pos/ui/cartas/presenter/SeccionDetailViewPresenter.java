/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.cartas.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.seccion.CartaListService;
import com.jobits.pos.controller.seccion.SeccionListService;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.core.domain.models.Carta;
import com.jobits.pos.core.domain.models.Seccion;
import com.jobits.pos.core.domain.models.SeccionAgregada;
import com.jobits.pos.main.Application;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.root101.clean.core.app.services.utils.TipoNotificacion;
import com.root101.clean.core.domain.services.ResourceHandler;
import com.root101.swing.material.standards.MaterialIcons;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.jobits.pos.ui.cartas.presenter.SeccionDetailViewModel.PROP_SECCION_AGREGADA_SELECCIONADA;

/**
 * JoBits
 *
 * @author Jorge
 */
public class SeccionDetailViewPresenter extends AbstractViewPresenter<SeccionDetailViewModel> {

    public static final String ACTION_CANCELAR = "Cancelar";
    public static final String ACTION_ACEPTAR = "Aceptar";
    public static final String ACTION_AGREGAR = "Agregar";
    public static final String ACTION_ELIMINAR = "Eliminar";

    private final SeccionListService seccionService = PosDesktopUiModule.getInstance().getImplementation(SeccionListService.class);
    private final CartaListService cartaService = PosDesktopUiModule.getInstance().getImplementation(CartaListService.class);
    private final boolean creatingMode;
    Seccion seccion;
    Carta carta;

    public SeccionDetailViewPresenter(Seccion seccion, Carta carta) {
        super(new SeccionDetailViewModel());
        this.carta = carta;
        creatingMode = seccion == null;
        if (creatingMode) {
            this.seccion = new Seccion();
        } else {
            this.seccion = seccion;
        }
        initListeners();
        refreshState();
    }

    @Override
    protected Optional refreshState() {
        if (!creatingMode) {
            getBean().setNombre_habilitado(false);
            getBean().setCrear_editar_button_text("Editar");
        } else {
            getBean().setCrear_editar_button_text("Crear");
        }
        getBean().setNombre_seccion(seccion.getNombreSeccion());
        getBean().setLista_secciones_agregadas(new ArrayListModel<>(seccion.getAgregadoEn()));
        List<Seccion> aux = new ArrayList();
        List<Carta> listaCartas = cartaService.findAll();
        for (Carta x : listaCartas) {
            aux.addAll(x.getSeccionList());
        }
        Collections.sort(aux);
        getBean().setLista_secciones(new ArrayListModel<>(aux));
        return super.refreshState(); //To change body of generated methods, choose Tools | Templates.
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

    private void onCancelarClick() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Esta seguro que desea cancelar?",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            NavigationService.getInstance().navigateUp();
        }
    }

    private void onAceptarClick() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Esta seguro que desea continuar?",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            seccion.setNombreSeccion(getBean().getNombre_seccion());
            seccion.setAgregadoEn(getBean().getLista_secciones_agregadas());
            if (creatingMode) {
                seccion.setCartacodCarta(carta.getCodCarta());
                cartaService.addSeccion(carta.getCodCarta(), seccion);
            } else {
                seccionService.edit(seccion);
            }
            NavigationService.getInstance().navigateUp();//TODO: faltan los insumos
            Application.getInstance().getNotificationService().notify(ResourceHandler.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
        }
    }

    private void onEliminarClick() {
        SeccionAgregada selected = getBean().getSeccion_agregada_seleccionada();
        if (selected == null) {
            throw new IllegalArgumentException("Seleccione una seccion");
        }
        getBean().getLista_secciones_agregadas().remove(getBean().getSeccion_agregada_seleccionada());
        if (!getBean().getLista_secciones_agregadas().isEmpty()) {
            getBean().setSeccion_seleccionada(null);
        }
    }

    private void onAgregarClick() {
        Seccion selected = getBean().getSeccion_seleccionada();
        for (SeccionAgregada seccionAgregada : getBean().getLista_secciones_agregadas()) {
            if (seccionAgregada.getSeccion().equals(selected)) {
                throw new IllegalStateException("Ya la seccion se encuentra agregada");
            }
        }
        SeccionAgregada agregada = new SeccionAgregada(selected.getNombreSeccion(), seccion.getNombreSeccion());
        agregada.setAgregadaEn(seccion);
        agregada.setSeccion(selected);

        Object[] options = {"Si", "No"};
        int confirm = JOptionPane.showOptionDialog(
                null,
                "Desea que esta seccion sea requerida?",
                "Requerido",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.YES_NO_OPTION,
                MaterialIcons.CHECK_BOX,
                options,
                options[0]
        );
        agregada.setRequerida(confirm == JOptionPane.YES_OPTION);
        getBean().getLista_secciones_agregadas().add(agregada);
    }

    private void initListeners() {
        addBeanPropertyChangeListener(PROP_SECCION_AGREGADA_SELECCIONADA, (PropertyChangeEvent evt) -> {
            SeccionAgregada value = (SeccionAgregada) evt.getNewValue();
            if (value != null) {
                getBean().setSeccion_requerida(value.getRequerida());
            }
        });
    }

}
