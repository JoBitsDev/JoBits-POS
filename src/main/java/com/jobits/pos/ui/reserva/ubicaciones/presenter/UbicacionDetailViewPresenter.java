/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.reserva.ubicaciones.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.TipoNotificacion;
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
import java.util.Arrays;
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

    private static final Color[] Colors = new Color[]{
        new Color(Integer.parseInt(ResourceHandler.getString("com.jobits.pos.reserva.default_color"))),
        DefaultValues.PRIMARY_COLOR_DARK,
        DefaultValues.PRIMARY_COLOR,
        DefaultValues.PRIMARY_COLOR_LIGHT,
        DefaultValues.SECONDARY_DARK,
        DefaultValues.SECONDARY_COLOR,
        DefaultValues.SECONDARY_COLOR_LIGHT,
        new Color(51, 204, 255),
        new Color(55, 102, 205),
        new Color(204, 255, 51),
        new Color(251, 198, 12, 200),
        new Color(12, 251, 160, 200),
        new Color(166, 251, 12, 200),
        new Color(66, 151, 12, 200)
    };

    List<LocalTime> times = new ArrayList<>();

    Ubicacion ubicacion = new Ubicacion();

    private boolean creatingMode = true;

//    public UbicacionDetailViewPresenter() {
//        super(new UbicacionDetailViewModel());
//        addListeners();
//        initLists();
//        refreshState();
//    }
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
        getBean().setLista_colores(new ArrayListModel<>(Arrays.asList(Colors)));
        getBean().setColor_seleccionado(
                getBean().getLista_colores().get(
                        getBean().getLista_colores().indexOf(
                                new Color(Integer.parseInt(
                                        ubicacion.getColorubicacion())))));

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
        NavigationService.getInstance().navigateUp();
    }

    private void onAgregarClick() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Desea confirmar los cambios?",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            ubicacion.setNombreubicacion(getBean().getNombre_ubicacion());
            ubicacion.setDisponibledesde(getBean().getHora_inicio());
            ubicacion.setDisponiblehasta(getBean().getHora_cierre());
            ubicacion.setColorubicacion(String.valueOf(getBean().getColor_seleccionado().getRGB()));
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

}
