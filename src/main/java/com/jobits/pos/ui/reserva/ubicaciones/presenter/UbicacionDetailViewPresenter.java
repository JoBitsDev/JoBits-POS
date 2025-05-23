/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.reserva.ubicaciones.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.main.Application;
import com.root101.clean.core.app.services.utils.TipoNotificacion;
import com.jobits.pos.reserva.core.domain.Ubicacion;
import com.jobits.pos.reserva.core.domain.UbicacionEstado;
import com.jobits.pos.reserva.core.usecase.UbicacionUseCase;
import com.jobits.pos.ui.DefaultValues;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import static com.jobits.pos.ui.reserva.ubicaciones.presenter.UbicacionDetailViewModel.*;
import com.root101.clean.core.domain.services.ResourceHandler;
import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Home
 */
public class UbicacionDetailViewPresenter extends AbstractViewPresenter<UbicacionDetailViewModel> {

    UbicacionUseCase ubicacionUseCase = PosDesktopUiModule.getInstance().getImplementation(UbicacionUseCase.class);

    public static final String ACTION_CANCELAR = "Cancelar";
    public static final String ACTION_ACEPTAR = "Aceptar";

    List<LocalTime> times = new ArrayList<>();

    Ubicacion ubicacion = new Ubicacion();

    private boolean creatingMode = true;

    public UbicacionDetailViewPresenter(Ubicacion ubicacion, boolean creatingMode) {
        super(new UbicacionDetailViewModel());
        this.ubicacion = ubicacion;
        this.creatingMode = creatingMode;
        addListeners();
        initLists();
        refreshState();
    }

    @Override
    protected Optional refreshState() {
        getBean().setNombre_ubicacion(ubicacion.getNombreubicacion());
        getBean().setLista_horas_inicio(new ArrayListModel<>(times));
        getBean().setHora_inicio(ubicacion.getDisponibledesde());
        getBean().setLista_horas_cierre(new ArrayListModel<>(times));
        getBean().setHora_cierre(ubicacion.getDisponiblehasta());
        getBean().setLista_colores(new ArrayListModel<>(colorsToRGB()));
        getBean().setColor_seleccionado(Integer.parseInt(ubicacion.getColorubicacion()));
        if (ubicacion.getEstadoubicacion().equals(UbicacionEstado.HABILITADA.getEstado())) {
            getBean().setUbicacion_habilitada(true);
        } else {
            getBean().setUbicacion_habilitada(false);
        }
        return super.refreshState(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_CANCELAR) {
            @Override
            public Optional doAction() {
                onCancelarClick();
                return Optional.empty();
            }

        });
        registerOperation(new AbstractViewAction(ACTION_ACEPTAR) {
            @Override
            public Optional doAction() {
                onAgregarClick();
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

    private void onAgregarClick() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Esta seguro que desea continuar?",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            ubicacion.setNombreubicacion(getBean().getNombre_ubicacion());
            ubicacion.setDisponibledesde(getBean().getHora_inicio());
            ubicacion.setDisponiblehasta(getBean().getHora_cierre());
            ubicacion.setColorubicacion(String.valueOf(getBean().getColor_seleccionado()));
            if (getBean().isUbicacion_habilitada()) {
                ubicacion.setEstadoubicacion(UbicacionEstado.HABILITADA.getEstado());
            } else {
                ubicacion.setEstadoubicacion(UbicacionEstado.INABILITADA.getEstado());
            }
            if (creatingMode) {
                ubicacionUseCase.create(ubicacion);
            } else {
                ubicacionUseCase.edit(ubicacion);
            }
            NavigationService.getInstance().navigateUp();
        }

    }

    private void initLists() {
        LocalTime baseTime = LocalTime.MIN;
        for (int i = 0; i < 48; i++) {
            times.add(baseTime);
            baseTime = baseTime.plusMinutes(30);
        }

    }

    private void addListeners() {
        addBeanPropertyChangeListener(PROP_HORA_INICIO, (PropertyChangeEvent evt) -> {
            if (evt.getNewValue() != null) {
                LocalTime newVal = (LocalTime) evt.getNewValue();
                if (newVal.isAfter(getBean().getHora_cierre())) {
                    getBean().setHora_cierre(newVal);
                }
            }
        });
        addBeanPropertyChangeListener(PROP_HORA_CIERRE, (PropertyChangeEvent evt) -> {
            if (evt.getNewValue() != null) {
                LocalTime newVal = (LocalTime) evt.getNewValue();
                if (newVal.isBefore(getBean().getHora_inicio())) {
                    getBean().setHora_cierre(newVal);
                }
            }
        });

    }

    private List<Integer> colorsToRGB() {
        List<Integer> ret = new ArrayList<>();
        for (Color x : DefaultValues.DEFAULT_COLOR_PALETE) {
            ret.add(x.getRGB());
        }
        return ret;
    }

}
