/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.backup.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.domain.UbicacionConexionModel;
import com.jobits.pos.ui.viewmodel.AbstractViewModel;
import java.util.List;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class BackUpViewModel extends AbstractViewModel {

    private ArrayListModel<UbicacionConexionModel> lista_ubicaciones = new ArrayListModel<>();

    public static final String PROP_LISTA_UBICACIONES = "lista_ubicaciones";

    private UbicacionConexionModel ubicacion_seleccionada;

    public static final String PROP_UBICACION_SELECCIONADA = "ubicacion_seleccionada";

    private boolean checkbox_productos;

    public static final String PROP_CHECKBOX_PRODUCTOS = "checkbox_productos";

    private boolean checkbox_personal;

    public static final String PROP_CHECKBOX_USUARIOS = "checkbox_personal";

    private boolean checkbox_ventas;

    public static final String PROP_CHECKBOX_VENTAS = "checkbox_ventas";

    private boolean checkbox_todo;

    public static final String PROP_CHECKBOX_TODO = "checkbox_todo";

    private boolean checkbox_limpieza_servidor;

    public static final String PROP_CHECKBOX_LIMPIEZA_SERVIDOR = "checkbox_limpieza_servidor";

    private int progress_indicator;

    public static final String PROP_PROGRESS_INDICATOR = "progress_indicator";

    /**
     * Get the value of progress_indicator
     *
     * @return the value of progress_indicator
     */
    public int getProgress_indicator() {
        return progress_indicator;
    }

    /**
     * Set the value of progress_indicator
     *
     * @param progress_indicator new value of progress_indicator
     */
    public void setProgress_indicator(int progress_indicator) {
        int oldProgress_indicator = this.progress_indicator;
        this.progress_indicator = progress_indicator;
        firePropertyChange(PROP_PROGRESS_INDICATOR, oldProgress_indicator, progress_indicator);
    }

    /**
     * Get the value of checkbox_limpieza_servidor
     *
     * @return the value of checkbox_limpieza_servidor
     */
    public boolean isCheckbox_limpieza_servidor() {
        return checkbox_limpieza_servidor;
    }

    /**
     * Set the value of checkbox_limpieza_servidor
     *
     * @param checkbox_limpieza_servidor new value of checkbox_limpieza_servidor
     */
    public void setCheckbox_limpieza_servidor(boolean checkbox_limpieza_servidor) {
        boolean oldCheckbox_limpieza_servidor = this.checkbox_limpieza_servidor;
        this.checkbox_limpieza_servidor = checkbox_limpieza_servidor;
        firePropertyChange(PROP_CHECKBOX_LIMPIEZA_SERVIDOR, oldCheckbox_limpieza_servidor, checkbox_limpieza_servidor);
    }

    /**
     * Get the value of checkbox_todo
     *
     * @return the value of checkbox_todo
     */
    public boolean isCheckbox_todo() {
        return checkbox_todo;
    }

    /**
     * Set the value of checkbox_todo
     *
     * @param checkbox_todo new value of checkbox_todo
     */
    public void setCheckbox_todo(boolean checkbox_todo) {
        boolean oldCheckbox_todo = this.checkbox_todo;
        this.checkbox_todo = checkbox_todo;
        firePropertyChange(PROP_CHECKBOX_TODO, oldCheckbox_todo, checkbox_todo);
    }

    /**
     * Get the value of checkbox_ventas
     *
     * @return the value of checkbox_ventas
     */
    public boolean isCheckbox_ventas() {
        return checkbox_ventas;
    }

    /**
     * Set the value of checkbox_ventas
     *
     * @param checkbox_ventas new value of checkbox_ventas
     */
    public void setCheckbox_ventas(boolean checkbox_ventas) {
        boolean oldCheckbox_ventas = this.checkbox_ventas;
        this.checkbox_ventas = checkbox_ventas;
        firePropertyChange(PROP_CHECKBOX_VENTAS, oldCheckbox_ventas, checkbox_ventas);
    }

    /**
     * Get the value of checkbox_personal
     *
     * @return the value of checkbox_personal
     */
    public boolean isCheckbox_personal() {
        return checkbox_personal;
    }

    /**
     * Set the value of checkbox_personal
     *
     * @param checkbox_personal new value of checkbox_personal
     */
    public void setCheckbox_personal(boolean checkbox_personal) {
        boolean oldCheckbox_insumo = this.checkbox_personal;
        this.checkbox_personal = checkbox_personal;
        firePropertyChange(PROP_CHECKBOX_USUARIOS, oldCheckbox_insumo, checkbox_personal);
    }

    /**
     * Get the value of checkbox_productos
     *
     * @return the value of checkbox_productos
     */
    public boolean isCheckbox_productos() {
        return checkbox_productos;
    }

    /**
     * Set the value of checkbox_productos
     *
     * @param checkbox_productos new value of checkbox_productos
     */
    public void setCheckbox_productos(boolean checkbox_productos) {
        boolean oldCheckbox_productos = this.checkbox_productos;
        this.checkbox_productos = checkbox_productos;
        firePropertyChange(PROP_CHECKBOX_PRODUCTOS, oldCheckbox_productos, checkbox_productos);
    }

    /**
     * Get the value of ubicacion_seleccionada
     *
     * @return the value of ubicacion_seleccionada
     */
    public UbicacionConexionModel getUbicacion_seleccionada() {
        return ubicacion_seleccionada;
    }

    /**
     * Set the value of ubicacion_seleccionada
     *
     * @param ubicacion_seleccionada new value of ubicacion_seleccionada
     */
    public void setUbicacion_seleccionada(UbicacionConexionModel ubicacion_seleccionada) {
        UbicacionConexionModel oldUbicacion_seleccionada = this.ubicacion_seleccionada;
        this.ubicacion_seleccionada = ubicacion_seleccionada;
        firePropertyChange(PROP_UBICACION_SELECCIONADA, oldUbicacion_seleccionada, ubicacion_seleccionada);
    }

    /**
     * Get the value of lista_ubicaciones
     *
     * @return the value of lista_ubicaciones
     */
    public ArrayListModel<UbicacionConexionModel> getLista_ubicaciones() {
        return lista_ubicaciones;
    }

    /**
     * Set the value of lista_ubicaciones
     *
     * @param lista_ubicaciones new value of lista_ubicaciones
     */
    public void setLista_ubicaciones(List<UbicacionConexionModel> lista_ubicaciones) {
        ArrayListModel<UbicacionConexionModel> oldLista_ubicaciones = this.lista_ubicaciones;
        this.lista_ubicaciones.clear();
        this.lista_ubicaciones.addAll(lista_ubicaciones);
        firePropertyChange(PROP_LISTA_UBICACIONES, oldLista_ubicaciones, lista_ubicaciones);
    }

}
