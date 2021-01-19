/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.reserva.ubicaciones.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.ui.viewmodel.AbstractViewModel;
import java.time.LocalTime;

/**
 *
 * @author Home
 */
public class UbicacionDetailViewModel extends AbstractViewModel {

    private String nombre_ubicacion;

    public static final String PROP_NOMBRE_UBICACION = "nombre_ubicacion";

    private LocalTime hora_inicio = LocalTime.MIDNIGHT;

    public static final String PROP_HORA_INICIO = "hora_inicio";

    private LocalTime hora_cierre = LocalTime.MAX;

    public static final String PROP_HORA_CIERRE = "hora_cierre";

    private ArrayListModel<LocalTime> lista_horas_inicio = new ArrayListModel<>();

    public static final String PROP_LISTA_HORAS_INICIO = "lista_horas_inicio";

    private ArrayListModel<LocalTime> lista_horas_cierre = new ArrayListModel<>();

    public static final String PROP_LISTA_HORAS_CIERRE = "lista_horas_cierre";

    private Integer color_seleccionado;

    public static final String PROP_COLOR_SELECCIONADO = "color_seleccionado";

    private ArrayListModel<Integer> lista_colores = new ArrayListModel<>();

    public static final String PROP_LISTA_COLORES = "lista_colores";

    private boolean ubicacion_habilitada = true;

    public static final String PROP_UBICACION_HABILITADA = "ubicacion_habilitada";

    /**
     * Get the value of ubicacion_habilitada
     *
     * @return the value of ubicacion_habilitada
     */
    public boolean isUbicacion_habilitada() {
        return ubicacion_habilitada;
    }

    /**
     * Set the value of ubicacion_habilitada
     *
     * @param ubicacion_habilitada new value of ubicacion_habilitada
     */
    public void setUbicacion_habilitada(boolean ubicacion_habilitada) {
        boolean oldUbicacion_habilitada = this.ubicacion_habilitada;
        this.ubicacion_habilitada = ubicacion_habilitada;
        firePropertyChange(PROP_UBICACION_HABILITADA, oldUbicacion_habilitada, ubicacion_habilitada);
    }

    /**
     * Get the value of lista_colores
     *
     * @return the value of lista_colores
     */
    public ArrayListModel<Integer> getLista_colores() {
        return lista_colores;
    }

    /**
     * Set the value of lista_colores
     *
     * @param lista_colores new value of lista_colores
     */
    public void setLista_colores(ArrayListModel<Integer> lista_colores) {
        ArrayListModel<Integer> oldLista_colores = this.lista_colores;
        this.lista_colores = lista_colores;
        firePropertyChange(PROP_LISTA_COLORES, oldLista_colores, lista_colores);
    }

    /**
     * Get the value of color_seleccionado
     *
     * @return the value of color_seleccionado
     */
    public Integer getColor_seleccionado() {
        return color_seleccionado;
    }

    /**
     * Set the value of color_seleccionado
     *
     * @param color_seleccionado new value of color_seleccionado
     */
    public void setColor_seleccionado(Integer color_seleccionado) {
        Integer oldColor_seleccionado = this.color_seleccionado;
        this.color_seleccionado = color_seleccionado;
        firePropertyChange(PROP_COLOR_SELECCIONADO, oldColor_seleccionado, color_seleccionado);
    }

    /**
     * Get the value of lista_horas_cierre
     *
     * @return the value of lista_horas_cierre
     */
    public ArrayListModel<LocalTime> getLista_horas_cierre() {
        return lista_horas_cierre;
    }

    /**
     * Set the value of lista_horas_cierre
     *
     * @param lista_horas_cierre new value of lista_horas_cierre
     */
    public void setLista_horas_cierre(ArrayListModel<LocalTime> lista_horas_cierre) {
        ArrayListModel<LocalTime> oldLista_horas_cierre = this.lista_horas_cierre;
        this.lista_horas_cierre = lista_horas_cierre;
        firePropertyChange(PROP_LISTA_HORAS_CIERRE, oldLista_horas_cierre, lista_horas_cierre);
    }

    /**
     * Get the value of lista_horas_inicio
     *
     * @return the value of lista_horas_inicio
     */
    public ArrayListModel<LocalTime> getLista_horas_inicio() {
        return lista_horas_inicio;
    }

    /**
     * Set the value of lista_horas_inicio
     *
     * @param lista_horas_inicio new value of lista_horas_inicio
     */
    public void setLista_horas_inicio(ArrayListModel<LocalTime> lista_horas_inicio) {
        ArrayListModel<LocalTime> oldLista_horas_inicio = this.lista_horas_inicio;
        this.lista_horas_inicio = lista_horas_inicio;
        firePropertyChange(PROP_LISTA_HORAS_INICIO, oldLista_horas_inicio, lista_horas_inicio);
    }

    /**
     * Get the value of hora_cierre
     *
     * @return the value of hora_cierre
     */
    public LocalTime getHora_cierre() {
        return hora_cierre;
    }

    /**
     * Set the value of hora_cierre
     *
     * @param hora_cierre new value of hora_cierre
     */
    public void setHora_cierre(LocalTime hora_cierre) {
        LocalTime oldHora_cierre = this.hora_cierre;
        this.hora_cierre = hora_cierre;
        firePropertyChange(PROP_HORA_CIERRE, oldHora_cierre, hora_cierre);
    }

    /**
     * Get the value of hora_inicio
     *
     * @return the value of hora_inicio
     */
    public LocalTime getHora_inicio() {
        return hora_inicio;
    }

    /**
     * Set the value of hora_inicio
     *
     * @param hora_inicio new value of hora_inicio
     */
    public void setHora_inicio(LocalTime hora_inicio) {
        LocalTime oldHora_inicio = this.hora_inicio;
        this.hora_inicio = hora_inicio;
        firePropertyChange(PROP_HORA_INICIO, oldHora_inicio, hora_inicio);
    }

    /**
     * Get the value of nombre_ubicacion
     *
     * @return the value of nombre_ubicacion
     */
    public String getNombre_ubicacion() {
        return nombre_ubicacion;
    }

    /**
     * Set the value of nombre_ubicacion
     *
     * @param nombre_ubicacion new value of nombre_ubicacion
     */
    public void setNombre_ubicacion(String nombre_ubicacion) {
        String oldNombre_ubicacion = this.nombre_ubicacion;
        this.nombre_ubicacion = nombre_ubicacion;
        firePropertyChange(PROP_NOMBRE_UBICACION, oldNombre_ubicacion, nombre_ubicacion);
    }

}
