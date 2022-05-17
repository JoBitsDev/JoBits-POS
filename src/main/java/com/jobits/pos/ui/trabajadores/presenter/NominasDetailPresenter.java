/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.trabajadores.presenter;

import com.jobits.pos.controller.trabajadores.impl.NominasController;
import com.jobits.pos.controller.trabajadores.NominasService;
import com.jobits.pos.core.domain.AsistenciaPersonalEstadisticas;
import com.jobits.pos.main.Application;
import com.root101.clean.core.app.services.utils.TipoNotificacion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.root101.clean.core.domain.services.ResourceHandler;
import java.beans.PropertyChangeEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import javax.swing.JOptionPane;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class NominasDetailPresenter extends AbstractViewPresenter<NominasDetailViewModel> {

    private final String[] months = {"Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"};

    public static final String ACTION_IMPRIMIR = "Imprimir";
    public static final String ACTION_PAGAR = "Pagar";
    public static final String ACTION_BUSCAR = "Buscar";
    public static String ACTION_SELECCIONAR_TODOS = "Seleccionar todos";
    public static final String ACTION_DESPLEGAR_OPCIONES = "Desplegar opciones";

    private final NominasService service = PosDesktopUiModule.getInstance().getImplementation(NominasService.class);

    public NominasDetailPresenter() {
        super(new NominasDetailViewModel());
        addListeners();
        setPeriodo();
    }

    private void onBuscarClick() {
        getBean().getLista_personal().clear();
        if(getBean().getFecha_desde().after(getBean().getFecha_hasta())){
            throw new IllegalArgumentException("Rango de fechas incorrecto");
        }
        getBean().getLista_personal().addAll(service.getPersonalActivo(getBean().getFecha_desde(), getBean().getFecha_hasta()));

    }

    private void onPagarClick() {
        boolean flag = JOptionPane.showConfirmDialog(null,
                "Desea imprimir el comprobante de pago", "Comprobante de Pago",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
        service.pagar(getBean().getLista_personal(),getBean().getHasta(), flag);
        getBean().getLista_personal().fireContentsChanged(0, getBean().getLista_personal().getSize()-1);
        Application.getInstance().getNotificationService().notify(ResourceHandler.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
    }

    private void onImprimirClick() {
        service.imprimirEstadisticas(getBean().getLista_personal());
    }

    private void onSeleccionarTodosClick() {
        if (!getBean().getLista_personal().isEmpty()) {
            for (AsistenciaPersonalEstadisticas x : getBean().getLista_personal()) {
                x.setUse(getBean().isSeleccionar_todo_seleccionado());
            }
            getBean().getLista_personal().fireContentsChanged(0, getBean().getLista_personal().getSize() - 1);
        } else {
            getBean().setSeleccionar_todo_seleccionado(false);
        }
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_PAGAR) {
            @Override
            public Optional doAction() {
                onPagarClick();
                return Optional.empty();
            }
        });

        registerOperation(new AbstractViewAction(ACTION_BUSCAR) {
            @Override
            public Optional doAction() {
                onBuscarClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_IMPRIMIR) {
            @Override
            public Optional doAction() {
                onImprimirClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_SELECCIONAR_TODOS) {
            @Override
            public Optional doAction() {
                onSeleccionarTodosClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_DESPLEGAR_OPCIONES) {
            @Override
            public Optional doAction() {
                getBean().setPanel_opciones_visible(!getBean().isPanel_opciones_visible());
                return Optional.empty();
            }
        });
    }

    private void addListeners() {
        getBean().addPropertyChangeListener(NominasDetailViewModel.PROP_FECHA_DESDE, (PropertyChangeEvent evt) -> {
            getBean().setFecha_hasta((Date) evt.getNewValue());
            setPeriodo();
        });

        getBean().addPropertyChangeListener(NominasDetailViewModel.PROP_FECHA_HASTA, (PropertyChangeEvent evt) -> {
            LocalDate inicio = getBean().getDesde();
            LocalDate fin = ((Date) evt.getNewValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            int diff = (int) ChronoUnit.DAYS.between(inicio, fin);
            if (diff < 0) {
                JOptionPane.showMessageDialog(Application.getInstance().getMainWindow(), "La fecha de inicio no puede ser mayor a la fecha final", "Error", JOptionPane.ERROR_MESSAGE);
                getBean().setFecha_hasta(getBean().getFecha_desde());
            } else {
                setPeriodo();
            }

        });
    }

    private void setPeriodo() {
        int dia = getBean().getFecha_desde().getDate(),
                mes = getBean().getFecha_desde().getMonth(),
                anno = getBean().getFecha_desde().getYear() + 1900;

        String inicio = dia + "-" + months[mes] + "-" + anno;

        dia = getBean().getFecha_hasta().getDate();
        mes = getBean().getFecha_hasta().getMonth();
        anno = getBean().getFecha_hasta().getYear() + 1900;

        String fin = dia + "-" + months[mes] + "-" + anno;

        getBean().setRango_fechas_text("De: " + inicio + " / " + fin);
    }

}
