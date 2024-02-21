/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.core.domain.VentaDAO1;
import com.jobits.pos.core.domain.models.Venta;
import com.jobits.pos.main.Application;
import com.jobits.pos.ui.presenters.AbstractListViewPresenter;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.utils.utils;
import java.beans.PropertyChangeEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.swing.JOptionPane;
import com.jobits.pos.controller.venta.VentaCalendarResumeUseCase;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Home
 */
public class VentaStatisticsViewPresenter extends AbstractListViewPresenter<VentaStatisticsViewModel> {

    private final VentaCalendarResumeUseCase service;

    public static final String ACTION_DESPLEGAR_OPCIONES = "Desplegar opciones",
            ACTION_RESTABLECER = "Restablecer",
            ACTION_ACTUALIZAR = "Actualizar",
            ACTION_SET_PERIODO = "Periodo anterior",
            ACTION_SET_ANNO = "Anno anterior";

    private final String[] months = {"Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"};

    int periodDays = 0;

    private LocalDate fecha_inicio_actual,
            fecha_final_actual,
            fecha_inicio_anterior,
            fecha_final_anterior;

    public VentaStatisticsViewPresenter(VentaCalendarResumeUseCase controller) {
        super(new VentaStatisticsViewModel(), "Ventas");
        this.fecha_final_anterior = LocalDate.now();
        this.fecha_inicio_anterior = LocalDate.now();
        this.fecha_final_actual = LocalDate.now();
        this.fecha_inicio_actual = LocalDate.now();
        this.service = controller;
        addListeners();
    }

    @Override
    protected void registerOperations() {
        super.registerOperations(); //To change body of generated methods, choose Tools | Templates.
        registerOperation(new AbstractViewAction(ACTION_DESPLEGAR_OPCIONES) {
            @Override
            public Optional doAction() {
                getBean().setPanel_opciones_visible(!getBean().isPanel_opciones_visible());
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_SET_PERIODO) {
            @Override
            public Optional doAction() {
                setPeriodoAnterior();
                updateBeanDataAnterior();
                updateListPeriodoAnterior();
                if (getBean().isAnno_selected()) {
                    getBean().setAnno_selected(!getBean().isAnno_selected());
                }
                if (!getBean().isPeriodo_selected()) {
                    getBean().setGasto_insumo_intervalo_anterior(null);
                    getBean().setGasto_otros_intervalo_anterior(null);
                    getBean().setGasto_trabajadores_intervalo_anterior(null);
                    getBean().setTotal_ventas_intervalo_anterior(null);
                    getBean().setPromedio_ventas_intervalo_anterior(null);
                    getBean().setHora_pico_intervalo_anterior(null);
                    getBean().setRango_fechas_anterior_text("");
                }
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_SET_ANNO) {
            @Override
            public Optional doAction() {
                setAnnoAnterior();
                updateBeanDataAnterior();
                updateListAnnoAnterior();
                if (getBean().isPeriodo_selected()) {
                    getBean().setPeriodo_selected(!getBean().isPeriodo_selected());
                }
                if (!getBean().isAnno_selected()) {
                    getBean().setGasto_insumo_intervalo_anterior(null);
                    getBean().setGasto_otros_intervalo_anterior(null);
                    getBean().setGasto_trabajadores_intervalo_anterior(null);
                    getBean().setTotal_ventas_intervalo_anterior(null);
                    getBean().setPromedio_ventas_intervalo_anterior(null);
                    getBean().setHora_pico_intervalo_anterior(null);
                    getBean().setRango_fechas_anterior_text("");
                }
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_RESTABLECER) {
            @Override
            public Optional doAction() {
                resetAllBeanData();
                return Optional.empty();
            }
        });

    }

    @Override
    protected void onAgregarClick() {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void onEditarClick() {
        //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void onEliminarClick() {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void setListToBean() {

        getBean().setLista_elementos(service.findVentasInRange(fecha_inicio_actual, fecha_final_actual));
        getBean().setLista_elementos_anterior(new ArrayListModel<>(service.findVentasInRange(fecha_inicio_anterior, fecha_final_anterior)));
    }

    private void addListeners() {
        getBean().addPropertyChangeListener(VentaStatisticsViewModel.PROP_RESUMEN_DESDE, (PropertyChangeEvent evt) -> {
            getBean().setResumen_hasta((Date) evt.getNewValue());
            setPeriodo();
            updateBeanDataActual();
            if (getBean().isPeriodo_selected()) {
                setPeriodoAnterior();
                updateBeanDataAnterior();
                updateListPeriodoAnterior();
            } else if (getBean().isAnno_selected()) {
                setAnnoAnterior();
                updateBeanDataAnterior();
                updateListAnnoAnterior();
            }
        });
        getBean().addPropertyChangeListener(VentaStatisticsViewModel.PROP_RESUMEN_HASTA, (PropertyChangeEvent evt) -> {
            LocalDate inicio = getBean().getResumen_desde().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate fin = ((Date) evt.getNewValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            int diff = (int) ChronoUnit.DAYS.between(inicio, fin);
            if (diff < 0) {
                JOptionPane.showMessageDialog(Application.getInstance().getMainWindow(), "La fecha de inicio no puede ser mayor a la fecha final", "Error", JOptionPane.ERROR_MESSAGE);
                getBean().setResumen_hasta(getBean().getResumen_desde());
            } else {
                setPeriodo();
                updateBeanDataActual();
                if (getBean().isPeriodo_selected()) {
                    setPeriodoAnterior();
                    updateBeanDataAnterior();
                    updateListPeriodoAnterior();
                } else if (getBean().isAnno_selected()) {
                    setAnnoAnterior();
                    updateBeanDataAnterior();
                    updateListAnnoAnterior();
                }
            }

        });
    }

    private void setPeriodo() {
        fecha_inicio_actual.adjustInto(utils.toLocalDate(getBean().getResumen_desde()));

        fecha_final_actual.adjustInto(utils.toLocalDate(getBean().getResumen_hasta()));

        getBean().setRango_fechas_text("De: " + fecha_inicio_actual.format(DateTimeFormatter.ISO_DATE) + " / " + fecha_final_actual.format(DateTimeFormatter.ISO_DATE));
    }

    private void updateBeanDataActual() {
        setListToBean();
        double suma = 0;
        double gInsumos = 0;
        double gGastos = 0;
        double gTrabajadores = 0;
        int cantidad = 0;
        for (Venta x : getBean().getLista_elementos()) {
            if (x.getVentaTotal() != null) {
                suma += x.getVentaTotal();
                if (x.getVentagastosEninsumos() != null) {
                    gInsumos += x.getVentagastosEninsumos();
                }
                if (x.getVentagastosGastos() != null) {
                    gGastos += x.getVentagastosGastos();
                }
                if (x.getVentagastosPagotrabajadores() != null) {
                    gTrabajadores += x.getVentagastosPagotrabajadores();
                }
                cantidad++;
            }
        }
        double promedio = suma / cantidad;

        //TODO logica en el presenter
        getBean().setTotal_ventas_intervalo_actual(utils.setDosLugaresDecimales((float) suma));
        getBean().setPromedio_ventas_intervalo_actual(utils.setDosLugaresDecimales((float) promedio));
        getBean().setGasto_insumo_intervalo_actual(utils.setDosLugaresDecimales((float) gInsumos));
        getBean().setGasto_trabajadores_intervalo_actual(utils.setDosLugaresDecimales((float) (gTrabajadores)));
        getBean().setGasto_otros_intervalo_actual(utils.setDosLugaresDecimales((float) gGastos));
        int hora_pico_promedio = VentaDAO1.getModalPickHour(getBean().getLista_elementos());
        getBean().setHora_pico_intervalo_actual(hora_pico_promedio > 12 ? (hora_pico_promedio - 12) + " PM" : hora_pico_promedio + " AM");
        List<Venta> ventas = cambiarVentasNull(getBean().getLista_elementos(), fecha_inicio_actual, fecha_final_actual);

        var dias = toNextYearDateList(ventas);

        getBean().setLista_dias_actual(new ArrayListModel<>(dias));

        //  getBean().setLista_total_actual(new ArrayListModel<>(service.getTotalVentas(ventas)));
        // getBean().setLista_gastos_actual(new ArrayListModel<>(service.getTotalGastos(ventas)));
//        getBean().setList_ordenes_actual(new ArrayListModel<>(service.getTotalOrden(ventas)));
    }

    private void setPeriodoAnterior() {
        LocalDate fechaDel = LocalDate.from(fecha_inicio_actual);
        LocalDate fechaAl = LocalDate.from(fecha_final_actual);

        periodDays = (int) ChronoUnit.DAYS.between(fechaDel, fechaAl);

        fecha_inicio_anterior.adjustInto(utils.toLocalDate(getBean().getResumen_desde()));

        fecha_final_anterior.adjustInto(utils.toLocalDate(getBean().getResumen_hasta()));

        fecha_inicio_anterior.minusDays(periodDays + 1);
        fecha_final_anterior.minusDays(periodDays + 1);

        getBean().setRango_fechas_anterior_text("Contra: " + fecha_inicio_anterior.format(DateTimeFormatter.ISO_DATE) + " / " + fecha_final_anterior.format(DateTimeFormatter.ISO_DATE));

    }

    private void updateListPeriodoAnterior() {

        List<Venta> ventas = cambiarVentasNull(getBean().getLista_elementos_anterior(), fecha_inicio_anterior, fecha_final_anterior);

//        getBean().setLista_total_anterior(new ArrayListModel<>(service.getTotalVentas(ventas)));
//        getBean().setList_gastos_anterior(new ArrayListModel<>(service.getTotalGastos(ventas)));
//        getBean().setLista_ordenes_anterior(new ArrayListModel<>(service.getTotalOrden(ventas)));

        var dias = toNextYearDateList(ventas);

        getBean().setLista_dias_anterior(new ArrayListModel<>(dias));
    }

    private void setAnnoAnterior() {
        fecha_inicio_anterior.adjustInto(utils.toLocalDate(getBean().getResumen_desde()));

        fecha_final_anterior.adjustInto(utils.toLocalDate(getBean().getResumen_hasta()));

        fecha_inicio_anterior.minusYears(1);
        fecha_final_anterior.minusYears(1);

        getBean().setRango_fechas_anterior_text("Contra: " + fecha_inicio_actual.format(DateTimeFormatter.ISO_DATE) + " / " + fecha_final_anterior.format(DateTimeFormatter.ISO_DATE));
    }

    private void updateListAnnoAnterior() {

        List<Venta> ventas = cambiarVentasNull(getBean().getLista_elementos_anterior(), fecha_inicio_anterior, fecha_final_anterior);

//        getBean().setLista_total_anterior(new ArrayListModel<>(service.getTotalVentas(ventas)));
//        getBean().setList_gastos_anterior(new ArrayListModel<>(service.getTotalGastos(ventas)));
//        getBean().setLista_ordenes_anterior(new ArrayListModel<>(service.getTotalOrden(ventas)));

        var dias = toNextYearDateList(ventas);
        getBean().setLista_dias_anterior(new ArrayListModel<>(dias));
    }

    private List<Date> toNextYearDateList(List<Venta> ventas) {
        List< Date> dias = new ArrayList<>();
//        service.getFechaVentas(ventas).stream().map((date) -> {
//            return date.plusYears(1);
//        }).forEachOrdered((date) -> {
//            dias.add(utils.toDate(date));
//        });
        return dias;
    }

    private void updateBeanDataAnterior() {
        setListToBean();
        double suma = 0;
        double gInsumos = 0;
        double gGastos = 0;
        double gTrabajadores = 0;
        int cantidad = 0;
        for (Venta x : getBean().getLista_elementos_anterior()) {
            if (x.getVentaTotal() != null) {
                suma += x.getVentaTotal();
                if (x.getVentagastosEninsumos() != null) {
                    gInsumos += x.getVentagastosEninsumos();
                }
                if (x.getVentagastosGastos() != null) {
                    gGastos += x.getVentagastosGastos();
                }
                if (x.getVentagastosPagotrabajadores() != null) {
                    gTrabajadores += x.getVentagastosPagotrabajadores();
                }
                cantidad++;
            }
        }
        double promedio = suma / cantidad;

        //TODO logica en el presenter
        getBean().setTotal_ventas_intervalo_anterior(utils.setDosLugaresDecimales((float) suma));
        getBean().setPromedio_ventas_intervalo_anterior(utils.setDosLugaresDecimales((float) promedio));
        getBean().setGasto_insumo_intervalo_anterior(utils.setDosLugaresDecimales((float) gInsumos));
        getBean().setGasto_trabajadores_intervalo_anterior(utils.setDosLugaresDecimales((float) (gTrabajadores)));
        getBean().setGasto_otros_intervalo_anterior(utils.setDosLugaresDecimales((float) gGastos));
        int hora_pico_promedio = VentaDAO1.getModalPickHour(getBean().getLista_elementos_anterior());
        getBean().setHora_pico_intervalo_anterior(hora_pico_promedio > 12 ? (hora_pico_promedio - 12) + " PM" : hora_pico_promedio + " AM");

    }

    /**
     * Algoritmo indefinido
     *
     * @param ventasList
     * @param startDate
     * @param endDate
     * @return
     */
    private List<Venta> cambiarVentasNull(ArrayListModel<Venta> ventasList, LocalDate startDate, LocalDate endDate) {
        List<Venta> ret = new ArrayList<>();
        LocalDate fechaPuntero = LocalDate.from(startDate);
        int i = 0;
        LocalDate currentVenta = null;

        while (fechaPuntero.compareTo(endDate) <= 0) {
            if (!ventasList.isEmpty()) {
                if (i < ventasList.size()) {
                    currentVenta = ventasList.get(i).getFecha();
                }
            }

            if (!currentVenta.isEqual(fechaPuntero)) {
                Venta a = new Venta();
                a.setVentaTotal(0.0);
                a.setFecha(fechaPuntero);
                ret.add(a);
            } else {
                boolean flag = false;
                Venta a = ventasList.get(i);
                if ((ventasList.get(i).getVentaTotal() == null)
                        || (ventasList.get(i).getVentaTotal() == 0.0)) {
                    a.setVentaTotal(0.0);
                    flag = true;
                }
                if ((ventasList.get(i).getVentagastosGastos() == null)
                        || (ventasList.get(i).getVentagastosGastos() == 0.0)) {
                    a.setVentagastosGastos((float) 0.0);
                    flag = true;
                }
                if ((ventasList.get(i).getOrdenList() == null)
                        || (ventasList.get(i).getOrdenList().isEmpty())) {
                    a.setOrdenList(new ArrayList<>());
                    flag = true;
                }
                if (flag) {
                    ret.add(a);
                } else {
                    ret.add(ventasList.get(i));
                }
                i++;
            }
            fechaPuntero.plusDays(1);
        }
        return ret;
    }

    private void resetAllBeanData() {
        getBean().setGasto_insumo_intervalo_anterior(null);
        getBean().setGasto_otros_intervalo_anterior(null);
        getBean().setGasto_trabajadores_intervalo_anterior(null);
        getBean().setTotal_ventas_intervalo_anterior(null);
        getBean().setPromedio_ventas_intervalo_anterior(null);
        getBean().setHora_pico_intervalo_anterior(null);
        getBean().setRango_fechas_anterior_text("");

        getBean().setResumen_desde(new Date());
        getBean().setResumen_hasta(new Date());

        getBean().setPeriodo_selected(false);
        getBean().setAnno_selected(false);

        this.fecha_final_anterior = LocalDate.now();
        this.fecha_inicio_anterior = LocalDate.now();
        this.fecha_final_actual = LocalDate.now();
        this.fecha_inicio_actual = LocalDate.now();

    }

}
