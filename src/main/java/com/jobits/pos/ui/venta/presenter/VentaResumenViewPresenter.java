/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.venta.VentaResumenService;
import com.jobits.pos.domain.models.Cocina;
import com.jobits.pos.main.Application;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import static com.jobits.pos.ui.venta.presenter.VentaResumenViewModel.*;
import java.beans.PropertyChangeEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import javax.swing.JOptionPane;

/**
 *
 * @author Home
 */
public class VentaResumenViewPresenter extends AbstractViewPresenter<VentaResumenViewModel> {

    private final String[] months = {"Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"};

    public static final String ACTION_IMPRIMIR_RESUMEN = "Imprimir Resumen";
//    public static final String ACTION_1 = "Action_name";
//    public static final String ACTION_2 = "Action_name";
//    public static final String ACTION_3 = "Action_name";
//    public static final String ACTION_4 = "Action_name";
//    public static final String ACTION_5 = "Action_name";
    private final VentaResumenService service;

    public VentaResumenViewPresenter(VentaResumenService service) {
        super(new VentaResumenViewModel());
        this.service = service;
        addListeners();
        setPeriodo();
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_IMPRIMIR_RESUMEN) {
            @Override
            public Optional doAction() {
//                Impresion i = new Impresion();
//                i.print(new PvOrdenResumenFormatter(getBean().getLista_ventas(), "Prueba"), null);
                return Optional.empty();
            }
        });
    }

    @Override
    protected Optional refreshState() {
        Date del = getBean().getFecha_desde();
        Date al = getBean().getFecha_hasta();
        Cocina c = getBean().getSeleccionada_cocina();
        service.createResumen(del, al, c);
        boolean redondear = getBean().isRedondear_valores();
        if (getBean().isMostrar_consumo_de_la_casa()) {
            getBean().setLista_ventas(new ArrayListModel<>(service.getListaGastosDeLaCasa(redondear)));
        } else {
            getBean().setLista_ventas(new ArrayListModel<>(service.getListaVentas(redondear)));
        }
        getBean().setLista_costos(new ArrayListModel<>(service.getListaGastos(redondear)));
        getBean().setTotal_recaudado(Float.toString(service.getTotalRecaudado()) + R.COIN_SUFFIX);
        getBean().setGanancia(Float.toString(service.getGanancias()) + R.COIN_SUFFIX);
        getBean().setDinero_invertido(Float.toString(service.getDineroInvertido()) + R.COIN_SUFFIX);
        return super.refreshState(); //To change body of generated methods, choose Tools | Templates.
    }

    private void addListeners() {
        getBean().addPropertyChangeListener(PROP_MOSTRAR_CONSUMO_DE_LA_CASA, (PropertyChangeEvent evt) -> {
            refreshState();
        });
        getBean().addPropertyChangeListener(PROP_REDONDEAR_VALORES, (PropertyChangeEvent evt) -> {
            refreshState();
        });
        getBean().addPropertyChangeListener(PROP_SELECCIONADA_COCINA, (PropertyChangeEvent evt) -> {
            refreshState();
        });
        getBean().addPropertyChangeListener(PROP_FECHA_DESDE, (PropertyChangeEvent evt) -> {
            getBean().setFecha_hasta((Date) evt.getNewValue());
            setPeriodo();
        });
        getBean().addPropertyChangeListener(PROP_FECHA_HASTA, (PropertyChangeEvent evt) -> {
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
        Application.getInstance().getBackgroundWorker().processInBackground("Cargando...", () -> {
            refreshState();
        });
        getBean().setSeleccionada_cocina(null);
        getBean().getLista_cocina().clear();
        getBean().getLista_cocina().addAll(new ArrayListModel<>(
                service.getListaCocinas(getBean().isRedondear_valores())));
    }

}
