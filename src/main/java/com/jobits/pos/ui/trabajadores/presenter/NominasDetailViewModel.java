/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.trabajadores.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.domain.AsistenciaPersonalEstadisticas;
import com.jobits.pos.ui.viewmodel.AbstractViewModel;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class NominasDetailViewModel extends AbstractViewModel {

    private LocalDate fecha_desde = LocalDate.now();

    public static final String PROP_FECHA_DESDE = "fecha_desde";

    private LocalDate fecha_hasta = LocalDate.now();

    public static final String PROP_FECHA_HASTA = "fecha_hasta";

    private ArrayListModel<AsistenciaPersonalEstadisticas> lista_personal = new ArrayListModel<>();

    public static final String PROP_LISTA_PERSONAL = "lista_personal";

    private AsistenciaPersonalEstadisticas personal_seleccionado;

    public static final String PROP_PERSONAL_SELECCIONADO = "personal_seleccionado";

    private boolean seleccionar_todo_seleccionado = false;

    public static final String PROP_SELECCIONAR_TODO_SELECCIONADO = "seleccionar_todo_seleccionado";

    /**
     * Get the value of seleccionar_todo_seleccionado
     *
     * @return the value of seleccionar_todo_seleccionado
     */
    public boolean isSeleccionar_todo_seleccionado() {
        return seleccionar_todo_seleccionado;
    }

    /**
     * Set the value of seleccionar_todo_seleccionado
     *
     * @param seleccionar_todo_seleccionado new value of
     * seleccionar_todo_seleccionado
     */
    public void setSeleccionar_todo_seleccionado(boolean seleccionar_todo_seleccionado) {
        boolean oldSeleccionar_todo_seleccionado = this.seleccionar_todo_seleccionado;
        this.seleccionar_todo_seleccionado = seleccionar_todo_seleccionado;
        firePropertyChange(PROP_SELECCIONAR_TODO_SELECCIONADO, oldSeleccionar_todo_seleccionado, seleccionar_todo_seleccionado, true);
    }

    /**
     * Get the value of personal_seleccionado
     *
     * @return the value of personal_seleccionado
     */
    public AsistenciaPersonalEstadisticas getPersonal_seleccionado() {
        return personal_seleccionado;
    }

    /**
     * Set the value of personal_seleccionado
     *
     * @param personal_seleccionado new value of personal_seleccionado
     */
    public void setPersonal_seleccionado(AsistenciaPersonalEstadisticas personal_seleccionado) {
        AsistenciaPersonalEstadisticas oldPersonal_seleccionado = this.personal_seleccionado;
        this.personal_seleccionado = personal_seleccionado;
        firePropertyChange(PROP_PERSONAL_SELECCIONADO, oldPersonal_seleccionado, personal_seleccionado, false);
    }

    /**
     * Get the value of lista_personal
     *
     * @return the value of lista_personal
     */
    public ArrayListModel<AsistenciaPersonalEstadisticas> getLista_personal() {
        return lista_personal;
    }

    /**
     * Set the value of lista_personal
     *
     * @param lista_personal new value of lista_personal
     */
    public void setLista_personal(ArrayListModel<AsistenciaPersonalEstadisticas> lista_personal) {
        ArrayListModel<AsistenciaPersonalEstadisticas> oldLista_personal = this.lista_personal;
        this.lista_personal = lista_personal;
        firePropertyChange(PROP_LISTA_PERSONAL, oldLista_personal, lista_personal, false);
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
