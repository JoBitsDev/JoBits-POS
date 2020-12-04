/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.domain.models.Cocina;
import com.jobits.pos.domain.models.ProductoInsumo;
import com.jobits.pos.domain.models.ProductovOrden;
import com.jobits.pos.ui.viewmodel.AbstractViewModel;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Home
 */
public class VentaResumenViewModel extends AbstractViewModel {

    //Fechas
    private LocalDate fecha_desde = LocalDate.now();

    public static final String PROP_FECHA_DESDE = "fecha_desde";

    private LocalDate fecha_hasta = LocalDate.now();

    public static final String PROP_FECHA_HASTA = "fecha_hasta";

    //Totales
    private String total_recaudado;

    public static final String PROP_TOTAL_RECAUDADO = "total_recaudado";

    private String ganancia;

    public static final String PROP_GANANCIA = "ganancia";

    private String dinero_invertido;

    public static final String PROP_DINERO_INVERTIDO = "dinero_invertido";

    private String gastos_de_la_casa;

    public static final String PROP_GASTOS_DE_LA_CASA = "gastos_de_la_casa";

    //CheckBoxes
    private boolean mostrar_consumo_de_la_casa;

    public static final String PROP_MOSTRAR_CONSUMO_DE_LA_CASA = "mostrar_consumo_de_la_casa";

    private boolean redondear_valores;

    public static final String PROP_REDONDEAR_VALORES = "redondear_valores";

    //Listas
    private ArrayListModel<ProductovOrden> lista_ventas = new ArrayListModel<>();

    public static final String PROP_LISTA_VENTAS = "lista_ventas";

    private ProductovOrden seleccionada_venta;

    public static final String PROP_SELECCIONADA_VENTA = "seleccionada_venta";

    private ArrayListModel<ProductoInsumo> lista_costos = new ArrayListModel();

    public static final String PROP_LISTA_COSTOS = "lista_costos";

    private ProductoInsumo seleccionado_gasto;

    public static final String PROP_SELECCIONADO_GASTO = "seleccionado_gasto";

    private ArrayListModel<Cocina> lista_cocina = new ArrayListModel();

    public static final String PROP_LISTA_COCINA = "lista_cocina";

    private Cocina seleccionada_cocina;

    public static final String PROP_SELECCIONADA_COCINA = "seleccionada_cocina";

    //Otros
    private boolean panel_opciones_visible;

    public static final String PROP_PANEL_OPCIONES_VISIBLE = "panel_opciones_visible";

    private String rango_fechas_text;

    public static final String PROP_RANGO_FECHAS_TEXT = "rango_fechas_text";

    /**
     * Get the value of rango_fechas_text
     *
     * @return the value of rango_fechas_text
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
     * Get the value of seleccionada_cocina
     *
     * @return the value of seleccionada_cocina
     */
    public Cocina getSeleccionada_cocina() {
        return seleccionada_cocina;
    }

    /**
     * Set the value of seleccionada_cocina
     *
     * @param seleccionada_cocina new value of seleccionada_cocina
     */
    public void setSeleccionada_cocina(Cocina seleccionada_cocina) {
        Cocina oldSeleccionada_cocina = this.seleccionada_cocina;
        this.seleccionada_cocina = seleccionada_cocina;
        firePropertyChange(PROP_SELECCIONADA_COCINA, oldSeleccionada_cocina, seleccionada_cocina);
    }

    /**
     * Get the value of lista_cocina
     *
     * @return the value of lista_cocina
     */
    public ArrayListModel<Cocina> getLista_cocina() {
        return lista_cocina;
    }

    /**
     * Set the value of lista_cocina
     *
     * @param lista_cocina new value of lista_cocina
     */
    public void setLista_cocina(ArrayListModel<Cocina> lista_cocina) {
        ArrayListModel<Cocina> oldLista_cocina = this.lista_cocina;
        this.lista_cocina = lista_cocina;
        firePropertyChange(PROP_LISTA_COCINA, oldLista_cocina, lista_cocina);
    }

    /**
     * Get the value of seleccionado_gasto
     *
     * @return the value of seleccionado_gasto
     */
    public ProductoInsumo getSeleccionado_gasto() {
        return seleccionado_gasto;
    }

    /**
     * Set the value of seleccionado_gasto
     *
     * @param seleccionado_gasto new value of seleccionado_gasto
     */
    public void setSeleccionado_gasto(ProductoInsumo seleccionado_gasto) {
        ProductoInsumo oldSeleccionado_gasto = this.seleccionado_gasto;
        this.seleccionado_gasto = seleccionado_gasto;
        firePropertyChange(PROP_SELECCIONADO_GASTO, oldSeleccionado_gasto, seleccionado_gasto);
    }

    /**
     * Get the value of lista_costos
     *
     * @return the value of lista_costos
     */
    public ArrayListModel<ProductoInsumo> getLista_costos() {
        return lista_costos;
    }

    /**
     * Set the value of lista_costos
     *
     * @param lista_costos new value of lista_costos
     */
    public void setLista_costos(ArrayListModel<ProductoInsumo> lista_costos) {
        ArrayListModel<ProductoInsumo> oldLista_costos = this.lista_costos;
        this.lista_costos = lista_costos;
        firePropertyChange(PROP_LISTA_COSTOS, oldLista_costos, lista_costos);
    }

    /**
     * Get the value of seleccionada_venta
     *
     * @return the value of seleccionada_venta
     */
    public ProductovOrden getSeleccionada_venta() {
        return seleccionada_venta;
    }

    /**
     * Set the value of seleccionada_venta
     *
     * @param seleccionada_venta new value of seleccionada_venta
     */
    public void setSeleccionada_venta(ProductovOrden seleccionada_venta) {
        ProductovOrden oldSeleccionada_venta = this.seleccionada_venta;
        this.seleccionada_venta = seleccionada_venta;
        firePropertyChange(PROP_SELECCIONADA_VENTA, oldSeleccionada_venta, seleccionada_venta);
    }

    /**
     * Get the value of lista_ventas
     *
     * @return the value of lista_ventas
     */
    public ArrayListModel<ProductovOrden> getLista_ventas() {
        return lista_ventas;
    }

    /**
     * Set the value of lista_ventas
     *
     * @param lista_ventas new value of lista_ventas
     */
    public void setLista_ventas(ArrayListModel<ProductovOrden> lista_ventas) {
        ArrayListModel<ProductovOrden> oldLista_ventas = this.lista_ventas;
        this.lista_ventas = lista_ventas;
        firePropertyChange(PROP_LISTA_VENTAS, oldLista_ventas, lista_ventas);
    }

    /**
     * Get the value of redondear_valores
     *
     * @return the value of redondear_valores
     */
    public boolean isRedondear_valores() {
        return redondear_valores;
    }

    /**
     * Set the value of redondear_valores
     *
     * @param redondear_valores new value of redondear_valores
     */
    public void setRedondear_valores(boolean redondear_valores) {
        boolean oldRedondear_valores = this.redondear_valores;
        this.redondear_valores = redondear_valores;
        firePropertyChange(PROP_REDONDEAR_VALORES, oldRedondear_valores, redondear_valores);
    }

    /**
     * Get the value of mostrar_consumo_de_la_casa
     *
     * @return the value of mostrar_consumo_de_la_casa
     */
    public boolean isMostrar_consumo_de_la_casa() {
        return mostrar_consumo_de_la_casa;
    }

    /**
     * Set the value of mostrar_consumo_de_la_casa
     *
     * @param mostrar_consumo_de_la_casa new value of mostrar_consumo_de_la_casa
     */
    public void setMostrar_consumo_de_la_casa(boolean mostrar_consumo_de_la_casa) {
        boolean oldMostrar_consumo_de_la_casa = this.mostrar_consumo_de_la_casa;
        this.mostrar_consumo_de_la_casa = mostrar_consumo_de_la_casa;
        firePropertyChange(PROP_MOSTRAR_CONSUMO_DE_LA_CASA, oldMostrar_consumo_de_la_casa, mostrar_consumo_de_la_casa);
    }

    /**
     * Get the value of gastos_de_la_casa
     *
     * @return the value of gastos_de_la_casa
     */
    public String getGastos_de_la_casa() {
        return gastos_de_la_casa;
    }

    /**
     * Set the value of gastos_de_la_casa
     *
     * @param gastos_de_la_casa new value of gastos_de_la_casa
     */
    public void setGastos_de_la_casa(String gastos_de_la_casa) {
        String oldGastos_de_la_casa = this.gastos_de_la_casa;
        this.gastos_de_la_casa = gastos_de_la_casa;
        firePropertyChange(PROP_GASTOS_DE_LA_CASA, oldGastos_de_la_casa, gastos_de_la_casa);
    }

    /**
     * Get the value of dinero_invertido
     *
     * @return the value of dinero_invertido
     */
    public String getDinero_invertido() {
        return dinero_invertido;
    }

    /**
     * Set the value of dinero_invertido
     *
     * @param dinero_invertido new value of dinero_invertido
     */
    public void setDinero_invertido(String dinero_invertido) {
        String oldDinero_invertido = this.dinero_invertido;
        this.dinero_invertido = dinero_invertido;
        firePropertyChange(PROP_DINERO_INVERTIDO, oldDinero_invertido, dinero_invertido);
    }

    /**
     * Get the value of ganancia
     *
     * @return the value of ganancia
     */
    public String getGanancia() {
        return ganancia;
    }

    /**
     * Set the value of ganancia
     *
     * @param ganancia new value of ganancia
     */
    public void setGanancia(String ganancia) {
        String oldGanancia = this.ganancia;
        this.ganancia = ganancia;
        firePropertyChange(PROP_GANANCIA, oldGanancia, ganancia);
    }

    /**
     * Get the value of total_recaudado
     *
     * @return the value of total_recaudado
     */
    public String getTotal_recaudado() {
        return total_recaudado;
    }

    /**
     * Set the value of total_recaudado
     *
     * @param total_recaudado new value of total_recaudado
     */
    public void setTotal_recaudado(String total_recaudado) {
        String oldTotal_recaudado = this.total_recaudado;
        this.total_recaudado = total_recaudado;
        firePropertyChange(PROP_TOTAL_RECAUDADO, oldTotal_recaudado, total_recaudado);
    }

    /**
     * Get the value of fecha_hasta
     *
     * @return the value of fecha_hasta
     */
    public Date getFecha_hasta() {
        java.sql.Date sqlDate = java.sql.Date.valueOf(fecha_hasta);
        return new Date(sqlDate.getTime());
    }

    /**
     * Set the value of fecha_hasta
     *
     * @param fecha_hasta new value of fecha_hasta
     */
    public void setFecha_hasta(Date fecha_hasta) {
        java.sql.Date sqlDate = new java.sql.Date(fecha_hasta.getTime());
        LocalDate oldFecha_hasta = this.fecha_hasta;
        this.fecha_hasta = sqlDate.toLocalDate();;
        firePropertyChange(PROP_FECHA_HASTA, oldFecha_hasta, fecha_hasta, false);
    }

    /**
     * Get the value of fecha_desde
     *
     * @return the value of fecha_desde
     */
    public Date getFecha_desde() {
        java.sql.Date sqlDate = java.sql.Date.valueOf(fecha_desde);
        return new Date(sqlDate.getTime());
    }

    /**
     * Set the value of fecha_desde
     *
     * @param fecha_desde new value of fecha_desde
     */
    public void setFecha_desde(Date fecha_desde) {
        java.sql.Date sqlDate = new java.sql.Date(fecha_desde.getTime());
        LocalDate oldFecha_desde = this.fecha_desde;
        this.fecha_desde = sqlDate.toLocalDate();
        firePropertyChange(PROP_FECHA_DESDE, oldFecha_desde, fecha_desde, false);
    }

    public LocalDate getDesde() {
        return this.fecha_desde;
    }

    public LocalDate getHasta() {
        return this.fecha_hasta;
    }

}
