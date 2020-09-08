/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.trabajadores.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.domain.models.Area;
import com.jobits.pos.domain.models.Cocina;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.viewmodel.AbstractViewModel;

/**
 *
 * @author Home
 */
public class PuestoTrabajoDetailViewModel extends AbstractViewModel {

    private String nombre_puesto_trabajo;

    public static final String PROP_NOMBRE_PUESTO_TRABAJO = "nombre_puesto_trabajo";

    private ArrayListModel<Area> area_trabajo_list = new ArrayListModel<>();

    public static final String PROP_AREA_TRABAJO_LIST = "area_trabajo_list";

    private Area area_trabajo_seleccionada;

    public static final String PROP_AREA_TRABAJO_SELECCIONADA = "area_trabajo_seleccionada";

    private int puestos_disponibles = 0;

    public static final String PROP_PUESTOS_DISPONIBLES = "puestos_disponibles";

    private ArrayListModel<R.NivelAcceso> nivel_acceso_list = new ArrayListModel<>();

    public static final String PROP_NIVEL_ACCESO_LIST = "nivel_acceso_list";

    private R.NivelAcceso nivel_acceso_seleccionado;

    public static final String PROP_NIVEL_ACCESO_SELECCIONADO = "nivel_acceso_seleccionado";

    private float salario_fijo = 0;

    public static final String PROP_SALARIO_FIJO = "salario_fijo";

    private float salario_venta = 0;

    public static final String PROP_SALARIO_VENTA = "salario_venta";

    private ArrayListModel<Cocina> area_pago_list = new ArrayListModel<>();

    public static final String PROP_AREA_PAGO_LIST = "area_pago_list";

    private Cocina area_pago_seleccionada;

    public static final String PROP_AREA_PAGO_SELECCIONADA = "area_pago_seleccionada";

    private float pago_a_partir = 0;

    public static final String PROP_PAGO_A_PARTIR = "pago_a_partir";

    private float pago_porciento_a_partir = 0;

    public static final String PROP_PAGO_PORCIENTO_A_PARTIR = "pago_porciento_a_partir";

    private boolean pago_por_ventas;

    public static final String PROP_PAGO_POR_VENTAS = "pago_por_ventas";

    private boolean propina;

    public static final String PROP_PROPINA = "propina";

    private String crear_editar_button_text;

    public static final String PROP_CREAR_EDITAR_BUTTON_TEXT = "crear_editar_button_text";

    private boolean nombre_enabled = true;

    public static final String PROP_NOMBRE_ENABLED = "nombre_enabled";

    /**
     * Get the value of nombre_enabled
     *
     * @return the value of nombre_enabled
     */
    public boolean isNombre_enabled() {
        return nombre_enabled;
    }

    /**
     * Set the value of nombre_enabled
     *
     * @param nombre_enabled new value of nombre_enabled
     */
    public void setNombre_enabled(boolean nombre_enabled) {
        boolean oldNombre_enabled = this.nombre_enabled;
        this.nombre_enabled = nombre_enabled;
        firePropertyChange(PROP_NOMBRE_ENABLED, oldNombre_enabled, nombre_enabled);
    }

    /**
     * Get the value of crear_editar_button_text
     *
     * @return the value of crear_editar_button_text
     */
    public String getCrear_editar_button_text() {
        return crear_editar_button_text;
    }

    /**
     * Set the value of crear_editar_button_text
     *
     * @param crear_editar_button_text new value of crear_editar_button_text
     */
    public void setCrear_editar_button_text(String crear_editar_button_text) {
        String oldCrear_editar_button_text = this.crear_editar_button_text;
        this.crear_editar_button_text = crear_editar_button_text;
        firePropertyChange(PROP_CREAR_EDITAR_BUTTON_TEXT, oldCrear_editar_button_text, crear_editar_button_text);
    }

    /**
     * Get the value of propina
     *
     * @return the value of propina
     */
    public boolean isPropina() {
        return propina;
    }

    /**
     * Set the value of propina
     *
     * @param propina new value of propina
     */
    public void setPropina(boolean propina) {
        boolean oldPropina = this.propina;
        this.propina = propina;
        firePropertyChange(PROP_PROPINA, oldPropina, propina);
    }

    /**
     * Get the value of pago_por_ventas
     *
     * @return the value of pago_por_ventas
     */
    public boolean isPago_por_ventas() {
        return pago_por_ventas;
    }

    /**
     * Set the value of pago_por_ventas
     *
     * @param pago_por_ventas new value of pago_por_ventas
     */
    public void setPago_por_ventas(boolean pago_por_ventas) {
        boolean oldPago_por_ventas = this.pago_por_ventas;
        this.pago_por_ventas = pago_por_ventas;
        firePropertyChange(PROP_PAGO_POR_VENTAS, oldPago_por_ventas, pago_por_ventas);
    }

    /**
     * Get the value of pago_porciento_a_partir
     *
     * @return the value of pago_porciento_a_partir
     */
    public float getPago_porciento_a_partir() {
        return pago_porciento_a_partir;
    }

    /**
     * Set the value of pago_porciento_a_partir
     *
     * @param pago_porciento_a_partir new value of pago_porciento_a_partir
     */
    public void setPago_porciento_a_partir(float pago_porciento_a_partir) {
        float oldPago_porciento_a_partir = this.pago_porciento_a_partir;
        this.pago_porciento_a_partir = pago_porciento_a_partir;
        firePropertyChange(PROP_PAGO_PORCIENTO_A_PARTIR, oldPago_porciento_a_partir, pago_porciento_a_partir);
    }

    /**
     * Get the value of pago_a_partir
     *
     * @return the value of pago_a_partir
     */
    public float getPago_a_partir() {
        return pago_a_partir;
    }

    /**
     * Set the value of pago_a_partir
     *
     * @param pago_a_partir new value of pago_a_partir
     */
    public void setPago_a_partir(float pago_a_partir) {
        float oldPago_a_partir = this.pago_a_partir;
        this.pago_a_partir = pago_a_partir;
        firePropertyChange(PROP_PAGO_A_PARTIR, oldPago_a_partir, pago_a_partir);
    }

    /**
     * Get the value of area_pago_seleccionada
     *
     * @return the value of area_pago_seleccionada
     */
    public Cocina getArea_pago_seleccionada() {
        return area_pago_seleccionada;
    }

    /**
     * Set the value of area_pago_seleccionada
     *
     * @param area_pago_seleccionada new value of area_pago_seleccionada
     */
    public void setArea_pago_seleccionada(Cocina area_pago_seleccionada) {
        Cocina oldArea_pago_seleccionada = this.area_pago_seleccionada;
        this.area_pago_seleccionada = area_pago_seleccionada;
        firePropertyChange(PROP_AREA_PAGO_SELECCIONADA, oldArea_pago_seleccionada, area_pago_seleccionada);
    }

    /**
     * Get the value of area_pago_list
     *
     * @return the value of area_pago_list
     */
    public ArrayListModel<Cocina> getArea_pago_list() {
        return area_pago_list;
    }

    /**
     * Set the value of area_pago_list
     *
     * @param area_pago_list new value of area_pago_list
     */
    public void setArea_pago_list(ArrayListModel<Cocina> area_pago_list) {
        ArrayListModel<Cocina> oldArea_pago_list = this.area_pago_list;
        this.area_pago_list = area_pago_list;
        firePropertyChange(PROP_AREA_PAGO_LIST, oldArea_pago_list, area_pago_list);
    }

    /**
     * Get the value of salario_venta
     *
     * @return the value of salario_venta
     */
    public float getSalario_venta() {
        return salario_venta;
    }

    /**
     * Set the value of salario_venta
     *
     * @param salario_venta new value of salario_venta
     */
    public void setSalario_venta(float salario_venta) {
        float oldSalario_venta = this.salario_venta;
        this.salario_venta = salario_venta;
        firePropertyChange(PROP_SALARIO_VENTA, oldSalario_venta, salario_venta);
    }

    /**
     * Get the value of salario_fijo
     *
     * @return the value of salario_fijo
     */
    public float getSalario_fijo() {
        return salario_fijo;
    }

    /**
     * Set the value of salario_fijo
     *
     * @param salario_fijo new value of salario_fijo
     */
    public void setSalario_fijo(float salario_fijo) {
        float oldSalario_fijo = this.salario_fijo;
        this.salario_fijo = salario_fijo;
        firePropertyChange(PROP_SALARIO_FIJO, oldSalario_fijo, salario_fijo);
    }

    /**
     * Get the value of nivel_acceso_seleccionado
     *
     * @return the value of nivel_acceso_seleccionado
     */
    public R.NivelAcceso getNivel_acceso_seleccionado() {
        return nivel_acceso_seleccionado;
    }

    /**
     * Set the value of nivel_acceso_seleccionado
     *
     * @param nivel_acceso_seleccionado new value of nivel_acceso_seleccionado
     */
    public void setNivel_acceso_seleccionado(R.NivelAcceso nivel_acceso_seleccionado) {
        R.NivelAcceso oldNivel_acceso_seleccionado = this.nivel_acceso_seleccionado;
        this.nivel_acceso_seleccionado = nivel_acceso_seleccionado;
        firePropertyChange(PROP_NIVEL_ACCESO_SELECCIONADO, oldNivel_acceso_seleccionado, nivel_acceso_seleccionado);
    }

    /**
     * Get the value of nivel_acceso_list
     *
     * @return the value of nivel_acceso_list
     */
    public ArrayListModel<R.NivelAcceso> getNivel_acceso_list() {
        return nivel_acceso_list;
    }

    /**
     * Set the value of nivel_acceso_list
     *
     * @param nivel_acceso_list new value of nivel_acceso_list
     */
    public void setNivel_acceso_list(ArrayListModel<R.NivelAcceso> nivel_acceso_list) {
        ArrayListModel<R.NivelAcceso> oldNivel_acceso_list = this.nivel_acceso_list;
        this.nivel_acceso_list = nivel_acceso_list;
        firePropertyChange(PROP_NIVEL_ACCESO_LIST, oldNivel_acceso_list, nivel_acceso_list);
    }

    /**
     * Get the value of puestos_disponibles
     *
     * @return the value of puestos_disponibles
     */
    public int getPuestos_disponibles() {
        return puestos_disponibles;
    }

    /**
     * Set the value of puestos_disponibles
     *
     * @param puestos_disponibles new value of puestos_disponibles
     */
    public void setPuestos_disponibles(int puestos_disponibles) {
        int oldPuestos_disponibles = this.puestos_disponibles;
        this.puestos_disponibles = puestos_disponibles;
        firePropertyChange(PROP_PUESTOS_DISPONIBLES, oldPuestos_disponibles, puestos_disponibles);
    }

    /**
     * Get the value of area_trabajo_seleccionada
     *
     * @return the value of area_trabajo_seleccionada
     */
    public Area getArea_trabajo_seleccionada() {
        return area_trabajo_seleccionada;
    }

    /**
     * Set the value of area_trabajo_seleccionada
     *
     * @param area_trabajo_seleccionada new value of area_trabajo_seleccionada
     */
    public void setArea_trabajo_seleccionada(Area area_trabajo_seleccionada) {
        Area oldArea_trabajo_seleccionada = this.area_trabajo_seleccionada;
        this.area_trabajo_seleccionada = area_trabajo_seleccionada;
        firePropertyChange(PROP_AREA_TRABAJO_SELECCIONADA, oldArea_trabajo_seleccionada, area_trabajo_seleccionada);
    }

    /**
     * Get the value of area_trabajo_list
     *
     * @return the value of area_trabajo_list
     */
    public ArrayListModel<Area> getArea_trabajo_list() {
        return area_trabajo_list;
    }

    /**
     * Set the value of area_trabajo_list
     *
     * @param area_trabajo_list new value of area_trabajo_list
     */
    public void setArea_trabajo_list(ArrayListModel<Area> area_trabajo_list) {
        ArrayListModel<Area> oldArea_trabajo_list = this.area_trabajo_list;
        this.area_trabajo_list = area_trabajo_list;
        firePropertyChange(PROP_AREA_TRABAJO_LIST, oldArea_trabajo_list, area_trabajo_list);
    }

    /**
     * Get the value of nombre_puesto_trabajo
     *
     * @return the value of nombre_puesto_trabajo
     */
    public String getNombre_puesto_trabajo() {
        return nombre_puesto_trabajo;
    }

    /**
     * Set the value of nombre_puesto_trabajo
     *
     * @param nombre_puesto_trabajo new value of nombre_puesto_trabajo
     */
    public void setNombre_puesto_trabajo(String nombre_puesto_trabajo) {
        String oldNombre_puesto_trabajo = this.nombre_puesto_trabajo;
        this.nombre_puesto_trabajo = nombre_puesto_trabajo;
        firePropertyChange(PROP_NOMBRE_PUESTO_TRABAJO, oldNombre_puesto_trabajo, nombre_puesto_trabajo);
    }

}
