/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.core.domain.models.Venta;
import com.jobits.pos.ui.viewmodel.AbstractListViewModel;
import java.util.Date;

/**
 *
 * @author Home
 */
public class VentaStatisticsViewModel extends AbstractListViewModel<Venta> {

    private java.util.Date resumen_desde = new Date();

    public static final String PROP_RESUMEN_DESDE = "resumen_desde";

    private java.util.Date resumen_hasta = new Date();

    public static final String PROP_RESUMEN_HASTA = "resumen_hasta";

    private boolean panel_opciones_visible = false;

    public static final String PROP_PANEL_OPCIONES_VISIBLE = "panel_opciones_visible";

    private ArrayListModel<Venta> lista_elementos_anterior;

    public static final String PROP_LISTA_ELEMENTOS_ANTERIOR = "lista_elementos_anterior";

    private String total_ventas_intervalo_actual;

    public static final String PROP_TOTAL_VENTAS_INTERVALO_ACTUAL = "total_ventas_intervalo_actual";

    private String promedio_ventas_intervalo_actual;

    public static final String PROP_PROMEDIO_VENTAS_INTERVALO_ACTUAL = "promedio_ventas_intervalo_actual";

    private String hora_pico_intervalo_actual;

    public static final String PROP_HORA_PICO_INTERVALO_ACTUAL = "hora_pico_intervalo_actual";

    private String gasto_trabajadores_intervalo_actual;

    public static final String PROP_GASTO_TRABAJADORES_INTERVALO_ACTUAL = "gasto_trabajadores_intervalo_actual";

    private String gasto_insumo_intervalo_actual;

    public static final String PROP_GASTO_OTROS_INTERVALO_ACTUAL = "gasto_otros_intervalo_actual";

    private String gasto_otros_intervalo_actual;

    public static final String PROP_GASTO_INSUMO_INTERVALO_ACTUAL = "gasto_insumo_intervalo_actual";

    private String total_ventas_intervalo_anterior = null;

    public static final String PROP_TOTAL_VENTAS_INTERVALO_ANTERIOR = "total_ventas_intervalo_anterior";

    private String promedio_ventas_intervalo_anterior = null;

    public static final String PROP_PROMEDIO_VENTAS_INTERVALO_ANTERIOR = "promedio_ventas_intervalo_anterior";

    private String hora_pico_intervalo_anterior = null;

    public static final String PROP_HORA_PICO_INTERVALO_ANTERIOR = "hora_pico_intervalo_anterior";

    private String gasto_trabajadores_intervalo_anterior = null;

    public static final String PROP_GASTO_TRABAJADORES_INTERVALO_ANTERIOR = "gasto_trabajadores_intervalo_anterior";

    private String gasto_insumo_intervalo_anterior = null;

    public static final String PROP_GASTO_OTROS_INTERVALO_ANTERIOR = "gasto_otros_intervalo_anterior";

    private String gasto_otros_intervalo_anterior = null;

    public static final String PROP_GASTO_INSUMO_INTERVALO_ANTERIOR = "gasto_insumo_intervalo_anterior";

    private String rango_fechas_text;

    public static final String PROP_RANGO_FECHAS_TEXT = "rango_fechas_text";

    private String rango_fechas_anterior_text;

    public static final String PROP_RANGO_FECHAS_ANTERIOR_TEXT = "rango_fechas_anterior_text";

    private static ArrayListModel<Date> lista_dias_actual;

    public static final String PROP_LISTA_DIAS_ACTUAL = "lista_dias_actual";

    private static ArrayListModel<Double> lista_total_actual;

    public static final String PROP_LISTA_TOTAL_ACTUAL = "lista_dias_actual";

    private static ArrayListModel<Date> lista_dias_anterior;

    public static final String PROP_LISTA_DIAS_ANTERIOR = "lista_dias_actual";

    private static ArrayListModel<Double> lista_total_anterior;

    public static final String PROP_LISTA_TOTAL_ANTERIOR = "lista_dias_actual";

    private boolean periodo_selected = false;

    public static final String PROP_PERIODO_SELECTED = "periodo_selected";

    private boolean anno_selected = false;

    public static final String PROP_ANNO_SELECTED = "anno_selected";

    private static ArrayListModel<Float> lista_gastos_actual;

    public static final String PROP_LISTA_GASTOS_ACTUAL = "lista_gastos_actual";

    private static ArrayListModel<Float> list_gastos_anterior;

    public static final String PROP_LIST_GASTOS_ANTERIOR = "list_gastos_anterior";

    private static ArrayListModel<Integer> list_ordenes_actual;

    public static final String PROP_LIST_ORDENES_ACTUAL = "list_ordenes_actual";

    private static ArrayListModel<Integer> lista_ordenes_anterior;

    public static final String PROP_LISTA_ORDENES_ANTERIOR = "lista_ordenes_anterior";

    /**
     * Get the value of lista_ordenes_anterior
     *
     * @return the value of lista_ordenes_anterior
     */
    public static ArrayListModel<Integer> getLista_ordenes_anterior() {
        return lista_ordenes_anterior;
    }

    /**
     * Set the value of lista_ordenes_anterior
     *
     * @param lista_ordenes_anterior new value of lista_ordenes_anterior
     */
    public void setLista_ordenes_anterior(ArrayListModel<Integer> lista_ordenes_anterior) {
        ArrayListModel<Integer> oldLista_ordenes_anterior = VentaStatisticsViewModel.lista_ordenes_anterior;
        VentaStatisticsViewModel.lista_ordenes_anterior = lista_ordenes_anterior;
        firePropertyChange(PROP_LISTA_ORDENES_ANTERIOR, oldLista_ordenes_anterior, lista_ordenes_anterior);
    }

    /**
     * Get the value of list_ordenes_actual
     *
     * @return the value of list_ordenes_actual
     */
    public static ArrayListModel<Integer> getList_ordenes_actual() {
        return list_ordenes_actual;
    }

    /**
     * Set the value of list_ordenes_actual
     *
     * @param list_ordenes_actual new value of list_ordenes_actual
     */
    public void setList_ordenes_actual(ArrayListModel<Integer> list_ordenes_actual) {
        ArrayListModel<Integer> oldList_ordenes_actual = VentaStatisticsViewModel.list_ordenes_actual;
        VentaStatisticsViewModel.list_ordenes_actual = list_ordenes_actual;
        firePropertyChange(PROP_LIST_ORDENES_ACTUAL, oldList_ordenes_actual, list_ordenes_actual);
    }

    /**
     * Get the value of list_gastos_anterior
     *
     * @return the value of list_gastos_anterior
     */
    public static ArrayListModel<Float> getList_gastos_anterior() {
        return list_gastos_anterior;
    }

    /**
     * Set the value of list_gastos_anterior
     *
     * @param list_gastos_anterior new value of list_gastos_anterior
     */
    public void setList_gastos_anterior(ArrayListModel<Float> list_gastos_anterior) {
        ArrayListModel<Float> oldList_gastos_anterior = VentaStatisticsViewModel.list_gastos_anterior;
        VentaStatisticsViewModel.list_gastos_anterior = list_gastos_anterior;
        firePropertyChange(PROP_LIST_GASTOS_ANTERIOR, oldList_gastos_anterior, list_gastos_anterior);
    }

    /**
     * Get the value of lista_gastos_actual
     *
     * @return the value of lista_gastos_actual
     */
    public static ArrayListModel<Float> getLista_gastos_actual() {
        return lista_gastos_actual;
    }

    /**
     * Set the value of lista_gastos_actual
     *
     * @param lista_gastos_actual new value of lista_gastos_actual
     */
    public void setLista_gastos_actual(ArrayListModel<Float> lista_gastos_actual) {
        ArrayListModel<Float> oldLista_gastos_actual = VentaStatisticsViewModel.lista_gastos_actual;
        VentaStatisticsViewModel.lista_gastos_actual = lista_gastos_actual;
        firePropertyChange(PROP_LISTA_GASTOS_ACTUAL, oldLista_gastos_actual, lista_gastos_actual);
    }

    /**
     * Get the value of periodo_selected
     *
     * @return the value of periodo_selected
     */
    public boolean isPeriodo_selected() {
        return periodo_selected;
    }

    /**
     * Set the value of periodo_selected
     *
     * @param periodo_selected new value of periodo_selected
     */
    public void setPeriodo_selected(boolean periodo_selected) {
        boolean oldPeriodo_selected = this.periodo_selected;
        this.periodo_selected = periodo_selected;
        firePropertyChange(PROP_PERIODO_SELECTED, oldPeriodo_selected, periodo_selected);
    }

    /**
     * Get the value of anno_selected
     *
     * @return the value of anno_selected
     */
    public boolean isAnno_selected() {
        return anno_selected;
    }

    /**
     * Set the value of anno_selected
     *
     * @param anno_selected new value of anno_selected
     */
    public void setAnno_selected(boolean anno_selected) {
        boolean oldAnno_selected = this.anno_selected;
        this.anno_selected = anno_selected;
        firePropertyChange(PROP_ANNO_SELECTED, oldAnno_selected, anno_selected);
    }

    /**
     * Get the value of panel_opciones_visible
     *
     * @return the value of panel_opciones_visible
     */
    public boolean isPanel_opciones_visible() {
        return panel_opciones_visible;
    }

    /**
     * Set the value of panel_opciones_visible
     *
     * @param panel_opciones_visible new value of panel_opciones_visible
     */
    public void setPanel_opciones_visible(boolean panel_opciones_visible) {
        boolean oldPanel_opciones_visible = this.panel_opciones_visible;
        this.panel_opciones_visible = panel_opciones_visible;
        firePropertyChange(PROP_PANEL_OPCIONES_VISIBLE, oldPanel_opciones_visible, panel_opciones_visible);
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
     * Get the value of lista_elementos_anterior
     *
     * @return the value of lista_elementos_anterior
     */
    public ArrayListModel<Venta> getLista_elementos_anterior() {
        return lista_elementos_anterior;
    }

    /**
     * Set the value of lista_elementos_anterior
     *
     * @param lista_elementos_anterior new value of lista_elementos_anterior
     */
    public void setLista_elementos_anterior(ArrayListModel<Venta> lista_elementos_anterior) {
        ArrayListModel<Venta> oldLista_elementos_anterior = this.lista_elementos_anterior;
        this.lista_elementos_anterior = lista_elementos_anterior;
        firePropertyChange(PROP_LISTA_ELEMENTOS_ANTERIOR, oldLista_elementos_anterior, lista_elementos_anterior);

    }

    /**
     * Get the value of hora_pico_intervalo_actual
     *
     * @return the value of hora_pico_intervalo_actual
     */
    public String getHora_pico_intervalo_actual() {
        return hora_pico_intervalo_actual;
    }

    /**
     * Set the value of hora_pico_intervalo_actual
     *
     * @param hora_pico_intervalo_actual new value of hora_pico_intervalo_actual
     */
    public void setHora_pico_intervalo_actual(String hora_pico_intervalo_actual) {
        String oldHora_pico_intervalo_actuals = this.hora_pico_intervalo_actual;
        this.hora_pico_intervalo_actual = hora_pico_intervalo_actual;
        firePropertyChange(PROP_HORA_PICO_INTERVALO_ACTUAL, oldHora_pico_intervalo_actuals, hora_pico_intervalo_actual);
    }

    /**
     * Get the value of promedio_ventas_intervalo_actual
     *
     * @return the value of promedio_ventas_intervalo_actual
     */
    public String getPromedio_ventas_intervalo_actual() {
        return promedio_ventas_intervalo_actual;
    }

    /**
     * Set the value of promedio_ventas_intervalo_actual
     *
     * @param promedio_ventas_intervalo_actual new value of
     * promedio_ventas_intervalo_actual
     */
    public void setPromedio_ventas_intervalo_actual(String promedio_ventas_intervalo_actual) {
        String oldPromedio_ventas_intervalo_actual = this.promedio_ventas_intervalo_actual;
        this.promedio_ventas_intervalo_actual = promedio_ventas_intervalo_actual;
        firePropertyChange(PROP_PROMEDIO_VENTAS_INTERVALO_ACTUAL, oldPromedio_ventas_intervalo_actual, promedio_ventas_intervalo_actual);
    }

    /**
     * Get the value of total_ventas_intervalo_actual
     *
     * @return the value of total_ventas_intervalo_actual
     */
    public String getTotal_ventas_intervalo_actual() {
        return total_ventas_intervalo_actual;
    }

    /**
     * Set the value of total_ventas_intervalo_actual
     *
     * @param total_ventas_intervalo_actual new value of
     * total_ventas_intervalo_actual
     */
    public void setTotal_ventas_intervalo_actual(String total_ventas_intervalo_actual) {
        String oldTotal_ventas_intervalo_actual = this.total_ventas_intervalo_actual;
        this.total_ventas_intervalo_actual = total_ventas_intervalo_actual;
        firePropertyChange(PROP_TOTAL_VENTAS_INTERVALO_ACTUAL, oldTotal_ventas_intervalo_actual, total_ventas_intervalo_actual);
    }

    /**
     * Get the value of gasto_insumo_intervalo_actual
     *
     * @return the value of gasto_insumo_intervalo_actual
     */
    public String getGasto_insumo_intervalo_actual() {
        return gasto_insumo_intervalo_actual;
    }

    /**
     * Set the value of gasto_insumo_intervalo_actual
     *
     * @param gasto_insumo_intervalo_actual new value of
     * gasto_insumo_intervalo_actual
     */
    public void setGasto_insumo_intervalo_actual(String gasto_insumo_intervalo_actual) {
        String oldGasto_insumo_intervalo_actual = this.gasto_insumo_intervalo_actual;
        this.gasto_insumo_intervalo_actual = gasto_insumo_intervalo_actual;
        firePropertyChange(PROP_GASTO_INSUMO_INTERVALO_ACTUAL, oldGasto_insumo_intervalo_actual, gasto_insumo_intervalo_actual);
    }

    /**
     * Get the value of gasto_trabajadores_intervalo_actual
     *
     * @return the value of gasto_trabajadores_intervalo_actual
     */
    public String getGasto_trabajadores_intervalo_actual() {
        return gasto_trabajadores_intervalo_actual;
    }

    /**
     * Set the value of gasto_trabajadores_intervalo_actual
     *
     * @param gasto_trabajadores_intervalo_actual new value of
     * gasto_trabajadores_intervalo_actual
     */
    public void setGasto_trabajadores_intervalo_actual(String gasto_trabajadores_intervalo_actual) {
        String oldGasto_trabajadores_intervalo_actual = this.gasto_trabajadores_intervalo_actual;
        this.gasto_trabajadores_intervalo_actual = gasto_trabajadores_intervalo_actual;
        firePropertyChange(PROP_GASTO_TRABAJADORES_INTERVALO_ACTUAL, oldGasto_trabajadores_intervalo_actual, gasto_trabajadores_intervalo_actual);
    }

    /**
     * Get the value of gasto_otros_intervalo_actual
     *
     * @return the value of gasto_otros_intervalo_actual
     */
    public String getGasto_otros_intervalo_actual() {
        return gasto_otros_intervalo_actual;
    }

    /**
     * Set the value of gasto_otros_intervalo_actual
     *
     * @param gasto_otros_intervalo_actual new value of
     * gasto_otros_intervalo_actual
     */
    public void setGasto_otros_intervalo_actual(String gasto_otros_intervalo_actual) {
        String oldGasto_otros_intervalo_actual = this.gasto_otros_intervalo_actual;
        this.gasto_otros_intervalo_actual = gasto_otros_intervalo_actual;
        firePropertyChange(PROP_GASTO_OTROS_INTERVALO_ACTUAL, oldGasto_otros_intervalo_actual, gasto_otros_intervalo_actual);
    }

    /**
     * Get the value of hora_pico_intervalo_anterior
     *
     * @return the value of hora_pico_intervalo_anterior
     */
    public String getHora_pico_intervalo_anterior() {
        return hora_pico_intervalo_anterior;
    }

    /**
     * Set the value of hora_pico_intervalo_anterior
     *
     * @param hora_pico_intervalo_anterior new value of
     * hora_pico_intervalo_anterior
     */
    public void setHora_pico_intervalo_anterior(String hora_pico_intervalo_anterior) {
        String oldHora_pico_intervalo_anteriors = this.hora_pico_intervalo_anterior;
        this.hora_pico_intervalo_anterior = hora_pico_intervalo_anterior;
        firePropertyChange(PROP_HORA_PICO_INTERVALO_ANTERIOR, oldHora_pico_intervalo_anteriors, hora_pico_intervalo_anterior);
    }

    /**
     * Get the value of promedio_ventas_intervalo_anterior
     *
     * @return the value of promedio_ventas_intervalo_anterior
     */
    public String getPromedio_ventas_intervalo_anterior() {
        return promedio_ventas_intervalo_anterior;
    }

    /**
     * Set the value of promedio_ventas_intervalo_anterior
     *
     * @param promedio_ventas_intervalo_anterior new value of
     * promedio_ventas_intervalo_anterior
     */
    public void setPromedio_ventas_intervalo_anterior(String promedio_ventas_intervalo_anterior) {
        String oldPromedio_ventas_intervalo_anterior = this.promedio_ventas_intervalo_anterior;
        this.promedio_ventas_intervalo_anterior = promedio_ventas_intervalo_anterior;
        firePropertyChange(PROP_PROMEDIO_VENTAS_INTERVALO_ANTERIOR, oldPromedio_ventas_intervalo_anterior, promedio_ventas_intervalo_anterior);
    }

    /**
     * Get the value of total_ventas_intervalo_anterior
     *
     * @return the value of total_ventas_intervalo_anterior
     */
    public String getTotal_ventas_intervalo_anterior() {
        return total_ventas_intervalo_anterior;
    }

    /**
     * Set the value of total_ventas_intervalo_anterior
     *
     * @param total_ventas_intervalo_anterior new value of
     * total_ventas_intervalo_anterior
     */
    public void setTotal_ventas_intervalo_anterior(String total_ventas_intervalo_anterior) {
        String oldTotal_ventas_intervalo_anterior = this.total_ventas_intervalo_anterior;
        this.total_ventas_intervalo_anterior = total_ventas_intervalo_anterior;
        firePropertyChange(PROP_TOTAL_VENTAS_INTERVALO_ANTERIOR, oldTotal_ventas_intervalo_anterior, total_ventas_intervalo_anterior);
    }

    /**
     * Get the value of gasto_insumo_intervalo_anterior
     *
     * @return the value of gasto_insumo_intervalo_anterior
     */
    public String getGasto_insumo_intervalo_anterior() {
        return gasto_insumo_intervalo_anterior;
    }

    /**
     * Set the value of gasto_insumo_intervalo_anterior
     *
     * @param gasto_insumo_intervalo_anterior new value of
     * gasto_insumo_intervalo_anterior
     */
    public void setGasto_insumo_intervalo_anterior(String gasto_insumo_intervalo_anterior) {
        String oldGasto_insumo_intervalo_anterior = this.gasto_insumo_intervalo_anterior;
        this.gasto_insumo_intervalo_anterior = gasto_insumo_intervalo_anterior;
        firePropertyChange(PROP_GASTO_INSUMO_INTERVALO_ANTERIOR, oldGasto_insumo_intervalo_anterior, gasto_insumo_intervalo_anterior);
    }

    /**
     * Get the value of gasto_trabajadores_intervalo_anterior
     *
     * @return the value of gasto_trabajadores_intervalo_anterior
     */
    public String getGasto_trabajadores_intervalo_anterior() {
        return gasto_trabajadores_intervalo_anterior;
    }

    /**
     * Set the value of gasto_trabajadores_intervalo_anterior
     *
     * @param gasto_trabajadores_intervalo_anterior new value of
     * gasto_trabajadores_intervalo_anterior
     */
    public void setGasto_trabajadores_intervalo_anterior(String gasto_trabajadores_intervalo_anterior) {
        String oldGasto_trabajadores_intervalo_anterior = this.gasto_trabajadores_intervalo_anterior;
        this.gasto_trabajadores_intervalo_anterior = gasto_trabajadores_intervalo_anterior;
        firePropertyChange(PROP_GASTO_TRABAJADORES_INTERVALO_ANTERIOR, oldGasto_trabajadores_intervalo_anterior, gasto_trabajadores_intervalo_anterior);
    }

    /**
     * Get the value of gasto_otros_intervalo_anterior
     *
     * @return the value of gasto_otros_intervalo_anterior
     */
    public String getGasto_otros_intervalo_anterior() {
        return gasto_otros_intervalo_anterior;
    }

    /**
     * Set the value of gasto_otros_intervalo_anterior
     *
     * @param gasto_otros_intervalo_anterior new value of
     * gasto_otros_intervalo_anterior
     */
    public void setGasto_otros_intervalo_anterior(String gasto_otros_intervalo_anterior) {
        String oldGasto_otros_intervalo_anterior = this.gasto_otros_intervalo_anterior;
        this.gasto_otros_intervalo_anterior = gasto_otros_intervalo_anterior;
        firePropertyChange(PROP_GASTO_OTROS_INTERVALO_ANTERIOR, oldGasto_otros_intervalo_anterior, gasto_otros_intervalo_anterior);
    }

    /**
     * Get the value of fecha_inicio_actual
     *
     * @return the value of fecha_inicio_actual
     */
    public String getRango_fechas_text() {
        return rango_fechas_text;
    }

    /**
     * Set the value of rango_fechas_text
     *
     * @param rango_fechas_text new value of rango_fechas_text
     */
    public void setRango_fechas_text(String rango_fechas_text) {
        String oldRango_fechas_text = this.rango_fechas_text;
        this.rango_fechas_text = rango_fechas_text;
        firePropertyChange(PROP_RANGO_FECHAS_TEXT, oldRango_fechas_text, rango_fechas_text);
    }

    /**
     * Get the value of rango_fechas_anterior_text
     *
     * @return the value of rango_fechas_anterior_text
     */
    public String getRango_fechas_anterior_text() {
        return rango_fechas_anterior_text;
    }

    /**
     * Set the value of rango_fechas_anterior_text
     *
     * @param rango_fechas_anterior_text new value of rango_fechas_anterior_text
     */
    public void setRango_fechas_anterior_text(String rango_fechas_anterior_text) {
        String oldRango_fechas_anterior_text = this.rango_fechas_anterior_text;
        this.rango_fechas_anterior_text = rango_fechas_anterior_text;
        firePropertyChange(PROP_RANGO_FECHAS_ANTERIOR_TEXT, oldRango_fechas_anterior_text, rango_fechas_anterior_text);
    }

    /**
     * Get the value of lista_dias_actual
     *
     * @return the value of lista_dias_actual
     */
    public static ArrayListModel<Date> getLista_dias_actual() {
        return lista_dias_actual;
    }

    /**
     * Set the value of lista_dias_actual
     *
     * @param lista_dias_actual new value of lista_dias_actual
     */
    public void setLista_dias_actual(ArrayListModel<Date> lista_dias_actual) {
        ArrayListModel<Date> oldLista_dias_actual = VentaStatisticsViewModel.lista_dias_actual;
        VentaStatisticsViewModel.lista_dias_actual = lista_dias_actual;
        firePropertyChange(PROP_LISTA_DIAS_ACTUAL, oldLista_dias_actual, lista_dias_actual);
    }

    /**
     * Get the value of lista_total_actual
     *
     * @return the value of lista_total_actual
     */
    public static ArrayListModel<Double> getLista_total_actual() {
        return lista_total_actual;
    }

    /**
     * Set the value of lista_total_actual
     *
     * @param lista_total_actual new value of lista_total_actual
     */
    public void setLista_total_actual(ArrayListModel<Double> lista_total_actual) {
        ArrayListModel<Double> oldLista_total_actual = VentaStatisticsViewModel.lista_total_actual;
        VentaStatisticsViewModel.lista_total_actual = lista_total_actual;
        firePropertyChange(PROP_LISTA_TOTAL_ACTUAL, oldLista_total_actual, lista_total_actual);
    }

    /**
     * Get the value of lista_dias_anterior
     *
     * @return the value of lista_dias_anterior
     */
    public static ArrayListModel<Date> getLista_dias_anterior() {
        return lista_dias_anterior;
    }

    /**
     * Set the value of lista_dias_anterior
     *
     * @param lista_dias_anterior new value of lista_dias_anterior
     */
    public void setLista_dias_anterior(ArrayListModel<Date> lista_dias_anterior) {
        ArrayListModel<Date> oldLista_dias_anterior = VentaStatisticsViewModel.lista_dias_anterior;
        VentaStatisticsViewModel.lista_dias_anterior = lista_dias_anterior;
        firePropertyChange(PROP_LISTA_DIAS_ANTERIOR, oldLista_dias_anterior, lista_dias_anterior);
    }

    /**
     * Get the value of lista_total_anterior
     *
     * @return the value of lista_total_anterior
     */
    public static ArrayListModel<Double> getLista_total_anterior() {
        return lista_total_anterior;
    }

    /**
     * Set the value of lista_total_anterior
     *
     * @param lista_total_anterior new value of lista_total_anterior
     */
    public void setLista_total_anterior(ArrayListModel<Double> lista_total_anterior) {
        ArrayListModel<Double> oldLista_total_anterior = VentaStatisticsViewModel.lista_total_anterior;
        VentaStatisticsViewModel.lista_total_anterior = lista_total_anterior;
        firePropertyChange(PROP_LISTA_TOTAL_ANTERIOR, oldLista_total_anterior, lista_total_anterior);
    }

}
