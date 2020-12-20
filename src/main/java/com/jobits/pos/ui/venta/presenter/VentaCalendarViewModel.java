/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.presenter;

import com.jobits.pos.domain.models.Venta;
import com.jobits.pos.ui.viewmodel.AbstractListViewModel;
import java.io.File;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.util.Date;
import java.util.List;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class VentaCalendarViewModel extends AbstractListViewModel<List<Venta>> {

    private int year_seleccionado = LocalDate.now().get(ChronoField.YEAR);

    public static final String PROP_YEAR_SELECCIONADO = "year_seleccionado";

    private int mes_seleccionado = LocalDate.now().get(ChronoField.MONTH_OF_YEAR) - 1;

    public static final String PROP_MES_SELECCIONADO = "mes_seleccionado";

    private String total_ventas_intervalo;

    public static final String PROP_TOTAL_VENTAS_INTERVALO = "total_ventas_intervalo";

    private String promedio_ventas_intervalo;

    public static final String PROP_PROMEDIO_VENTAS_INTERVALO = "promedio_ventas_intervalo";

    private String hora_pico_intervalo;

    public static final String PROP_HORA_PICO_INTERVALOS = "hora_pico_intervalo";

    private String gasto_trabajadores_intervalo;

    public static final String PROP_GASTO_TRABAJADORES_INTERVALO = "gasto_trabajadores_intervalo";

    private String gasto_insumo_intervalo;

    public static final String PROP_GASTO_OTROS_INTERVALO = "gasto_otros_intervalo";

    private String gasto_otros_intervalo;

    public static final String PROP_GASTO_INSUMO_INTERVALO = "gasto_insumo_intervalo";

    private java.util.Date resumen_desde;

    public static final String PROP_RESUMEN_DESDE = "resumen_desde";

    private java.util.Date resumen_hasta;

    public static final String PROP_RESUMEN_HASTA = "resumen_hasta";

    private File archivo_a_importar;

    public static final String PROP_ARCHIVO_A_IMPORTAR = "archivo_a_importar";

    private boolean y_visible;

    public static final String PROP_Y_VISIBLE = "y_visible";

    private int month_offset;

    public static final String PROP_MONTH_OFFSET = "month_offset";

    private List<Venta> dia_seleccionado;

    public static final String PROP_DIA_SELECCIONADO = "dia_seleccionado";

    /**
     * Get the value of dia_seleccionado
     *
     * @return the value of dia_seleccionado
     */
    public List<Venta> getDia_seleccionado() {
        return dia_seleccionado;
    }

    /**
     * Set the value of dia_seleccionado
     *
     * @param dia_seleccionado new value of dia_seleccionado
     */
    public void setDia_seleccionado(List<Venta> dia_seleccionado) {
        if (dia_seleccionado != null) {
            List<Venta> oldDia_seleccionado = this.dia_seleccionado;
            this.dia_seleccionado = dia_seleccionado;
            firePropertyChange(PROP_DIA_SELECCIONADO, oldDia_seleccionado, dia_seleccionado);
        }
    }

    /**
     * Get the value of month_offset
     *
     * @return the value of month_offset
     */
    public int getMonth_offset() {
        return month_offset;
    }

    /**
     * Set the value of month_offset
     *
     * @param month_offset new value of month_offset
     */
    public void setMonth_offset(int month_offset) {
        int oldMonth_offset = this.month_offset;
        this.month_offset = month_offset;
        firePropertyChange(PROP_MONTH_OFFSET, oldMonth_offset, month_offset);
    }

    /**
     * Get the value of y_visible
     *
     * @return the value of y_visible
     */
    public boolean isY_visible() {
        return y_visible;
    }

    /**
     * Set the value of y_visible
     *
     * @param y_visible new value of y_visible
     */
    public void setY_visible(boolean y_visible) {
        boolean oldY_visible = this.y_visible;
        this.y_visible = y_visible;
        firePropertyChange(PROP_Y_VISIBLE, oldY_visible, y_visible);
    }

    /**
     * Get the value of archivo_a_importar
     *
     * @return the value of archivo_a_importar
     */
    public File getArchivo_a_importar() {
        return archivo_a_importar;
    }

    /**
     * Set the value of archivo_a_importar
     *
     * @param archivo_a_importar new value of archivo_a_importar
     */
    public void setArchivo_a_importar(File archivo_a_importar) {
        File oldArchivo_a_importar = this.archivo_a_importar;
        this.archivo_a_importar = archivo_a_importar;
        firePropertyChange(PROP_ARCHIVO_A_IMPORTAR, oldArchivo_a_importar, archivo_a_importar);
    }

    /**
     * Get the value of resumen_hasta
     *
     * @return the value of resumen_hasta
     */
    public Date getResumen_hasta() {
        return resumen_hasta;
    }

    /**
     * Set the value of resumen_hasta
     *
     * @param resumen_hasta new value of resumen_hasta
     */
    public void setResumen_hasta(Date resumen_hasta) {
        Date oldResumen_hasta = this.resumen_hasta;
        this.resumen_hasta = resumen_hasta;
        firePropertyChange(PROP_RESUMEN_HASTA, oldResumen_hasta, resumen_hasta);
    }

    /**
     * Get the value of resumen_desde
     *
     * @return the value of resumen_desde
     */
    public Date getResumen_desde() {
        return resumen_desde;
    }

    /**
     * Set the value of resumen_desde
     *
     * @param resumen_desde new value of resumen_desde
     */
    public void setResumen_desde(Date resumen_desde) {
        Date oldResumen_desde = this.resumen_desde;
        this.resumen_desde = resumen_desde;
        firePropertyChange(PROP_RESUMEN_DESDE, oldResumen_desde, resumen_desde);
    }

    /**
     * Get the value of hora_pico_intervalo
     *
     * @return the value of hora_pico_intervalo
     */
    public String getHora_pico_intervalo() {
        return hora_pico_intervalo;
    }

    /**
     * Set the value of hora_pico_intervalo
     *
     * @param hora_pico_intervalo new value of hora_pico_intervalo
     */
    public void setHora_pico_intervalo(String hora_pico_intervalo) {
        String oldHora_pico_intervalos = this.hora_pico_intervalo;
        this.hora_pico_intervalo = hora_pico_intervalo;
        firePropertyChange(PROP_HORA_PICO_INTERVALOS, oldHora_pico_intervalos, hora_pico_intervalo);
    }

    /**
     * Get the value of promedio_ventas_intervalo
     *
     * @return the value of promedio_ventas_intervalo
     */
    public String getPromedio_ventas_intervalo() {
        return promedio_ventas_intervalo;
    }

    /**
     * Set the value of promedio_ventas_intervalo
     *
     * @param promedio_ventas_intervalo new value of promedio_ventas_intervalo
     */
    public void setPromedio_ventas_intervalo(String promedio_ventas_intervalo) {
        String oldPromedio_ventas_intervalo = this.promedio_ventas_intervalo;
        this.promedio_ventas_intervalo = promedio_ventas_intervalo;
        firePropertyChange(PROP_PROMEDIO_VENTAS_INTERVALO, oldPromedio_ventas_intervalo, promedio_ventas_intervalo);
    }

    /**
     * Get the value of total_ventas_intervalo
     *
     * @return the value of total_ventas_intervalo
     */
    public String getTotal_ventas_intervalo() {
        return total_ventas_intervalo;
    }

    /**
     * Set the value of total_ventas_intervalo
     *
     * @param total_ventas_intervalo new value of total_ventas_intervalo
     */
    public void setTotal_ventas_intervalo(String total_ventas_intervalo) {
        String oldTotal_ventas_intervalo = this.total_ventas_intervalo;
        this.total_ventas_intervalo = total_ventas_intervalo;
        firePropertyChange(PROP_TOTAL_VENTAS_INTERVALO, oldTotal_ventas_intervalo, total_ventas_intervalo);
    }

    /**
     * Get the value of mes_seleccionado
     *
     * @return the value of mes_seleccionado
     */
    public int getMes_seleccionado() {
        return mes_seleccionado;
    }

    /**
     * Set the value of mes_seleccionado
     *
     * @param mes_seleccionado new value of mes_seleccionado
     */
    public void setMes_seleccionado(int mes_seleccionado) {
        int oldMes_seleccionado = this.mes_seleccionado;
        this.mes_seleccionado = mes_seleccionado;
        firePropertyChange(PROP_MES_SELECCIONADO, oldMes_seleccionado, mes_seleccionado);
    }

    /**
     * Get the value of year_seleccionado
     *
     * @return the value of year_seleccionado
     */
    public int getYear_seleccionado() {
        return year_seleccionado;
    }

    /**
     * Set the value of year_seleccionado
     *
     * @param year_seleccionado new value of year_seleccionado
     */
    public void setYear_seleccionado(int year_seleccionado) {
        int oldYear_seleccionado = this.year_seleccionado;
        this.year_seleccionado = year_seleccionado;
        firePropertyChange(PROP_YEAR_SELECCIONADO, oldYear_seleccionado, year_seleccionado);
    }

    /**
     * Get the value of gasto_insumo_intervalo
     *
     * @return the value of gasto_insumo_intervalo
     */
    public String getGasto_insumo_intervalo() {
        return gasto_insumo_intervalo;
    }

    /**
     * Set the value of gasto_insumo_intervalo
     *
     * @param gasto_insumo_intervalo new value of gasto_insumo_intervalo
     */
    public void setGasto_insumo_intervalo(String gasto_insumo_intervalo) {
        String oldGasto_insumo_intervalo = this.gasto_insumo_intervalo;
        this.gasto_insumo_intervalo = gasto_insumo_intervalo;
        firePropertyChange(PROP_GASTO_INSUMO_INTERVALO, oldGasto_insumo_intervalo, gasto_insumo_intervalo);
    }

    /**
     * Get the value of gasto_trabajadores_intervalo
     *
     * @return the value of gasto_trabajadores_intervalo
     */
    public String getGasto_trabajadores_intervalo() {
        return gasto_trabajadores_intervalo;
    }

    /**
     * Set the value of gasto_trabajadores_intervalo
     *
     * @param gasto_trabajadores_intervalo new value of
     * gasto_trabajadores_intervalo
     */
    public void setGasto_trabajadores_intervalo(String gasto_trabajadores_intervalo) {
        String oldGasto_trabajadores_intervalo = this.gasto_trabajadores_intervalo;
        this.gasto_trabajadores_intervalo = gasto_trabajadores_intervalo;
        firePropertyChange(PROP_GASTO_TRABAJADORES_INTERVALO, oldGasto_trabajadores_intervalo, gasto_trabajadores_intervalo);
    }

    /**
     * Get the value of gasto_otros_intervalo
     *
     * @return the value of gasto_otros_intervalo
     */
    public String getGasto_otros_intervalo() {
        return gasto_otros_intervalo;
    }

    /**
     * Set the value of gasto_otros_intervalo
     *
     * @param gasto_otros_intervalo new value of gasto_otros_intervalo
     */
    public void setGasto_otros_intervalo(String gasto_otros_intervalo) {
        String oldGasto_otros_intervalo = this.gasto_otros_intervalo;
        this.gasto_otros_intervalo = gasto_otros_intervalo;
        firePropertyChange(PROP_GASTO_OTROS_INTERVALO, oldGasto_otros_intervalo, gasto_otros_intervalo);
    }

}
