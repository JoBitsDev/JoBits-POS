/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.filter.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.ui.filter.presenter.FilterType;
import com.jobits.pos.ui.viewmodel.AbstractViewModel;

/**
 *
 * @author Home
 * @param <T>
 */
public class AbstractFilterTypeModel<T> extends AbstractViewModel {

    private ArrayListModel<T> lista_elementos = new ArrayListModel<>();

    public static final String PROP_LISTA_ELEMENTOS = "lista_elementos";

    private T elemento_seleccionado;

    public static final String PROP_ELEMENTO_SELECCIONADO = "elemento_seleccionado";

    private String tipo_filtro_info = "Filtrar por: ";

    public static final String PROP_TIPO_FILTRO_INFO = "tipo_filtro_info";

    private FilterType tipo_filtro;

    public static final String PROP_TIPO_FILTRO = "tipo_filtro";

    private boolean tipo_operacion = false;

    public static final String PROP_TIPO_OPERACION = "tipo_operacion";

    /**
     * Get the value of tipo_operacion
     *
     * @return the value of tipo_operacion
     */
    public boolean isTipo_operacion() {
        return tipo_operacion;
    }

    /**
     * Set the value of tipo_operacion
     *
     * @param tipo_operacion new value of tipo_operacion
     */
    public void setTipo_operacion(boolean tipo_operacion) {
        boolean oldTipo_operacion = this.tipo_operacion;
        this.tipo_operacion = tipo_operacion;
        firePropertyChange(PROP_TIPO_OPERACION, oldTipo_operacion, tipo_operacion);
    }

    /**
     * Get the value of tipo_filtro
     *
     * @return the value of tipo_filtro
     */
    public FilterType getTipo_filtro() {
        return tipo_filtro;
    }

    /**
     * Set the value of tipo_filtro
     *
     * @param tipo_filtro new value of tipo_filtro
     */
    public void setTipo_filtro(FilterType tipo_filtro) {
        FilterType oldTipo_filtro = this.tipo_filtro;
        this.tipo_filtro = tipo_filtro;
        this.tipo_filtro_info = "Filtrar por: " + tipo_filtro.getFilterType();
        firePropertyChange(PROP_TIPO_FILTRO, oldTipo_filtro, tipo_filtro);
    }

    /**
     * Get the value of tipo_filtro_info
     *
     * @return the value of tipo_filtro_info
     */
    public String getTipo_filtro_info() {
        return tipo_filtro_info;
    }

    /**
     * Set the value of tipo_filtro_info
     *
     * @param tipo_filtro_info new value of tipo_filtro_info
     */
    public void setTipo_filtro_info(String tipo_filtro_info) {
        String oldTipo_filtro_info = this.tipo_filtro_info;
        this.tipo_filtro_info = tipo_filtro_info;
        firePropertyChange(PROP_TIPO_FILTRO_INFO, oldTipo_filtro_info, tipo_filtro_info);
    }

    /**
     * Get the value of elemento_seleccionado
     *
     * @return the value of elemento_seleccionado
     */
    public T getElemento_seleccionado() {
        return elemento_seleccionado;
    }

    /**
     * Set the value of elemento_seleccionado
     *
     * @param elemento_seleccionado new value of elemento_seleccionado
     */
    public void setElemento_seleccionado(T elemento_seleccionado) {
        T oldElemento_seleccionado = this.elemento_seleccionado;
        this.elemento_seleccionado = elemento_seleccionado;
        firePropertyChange(PROP_ELEMENTO_SELECCIONADO, oldElemento_seleccionado, elemento_seleccionado);
    }

    /**
     * Get the value of lista_elementos
     *
     * @return the value of lista_elementos
     */
    public ArrayListModel<T> getLista_elementos() {
        return lista_elementos;
    }

    /**
     * Set the value of lista_elementos
     *
     * @param lista_elementos new value of lista_elementos
     */
    public void setLista_elementos(ArrayListModel<T> lista_elementos) {
        ArrayListModel<T> oldLista_elementos = this.lista_elementos;
        this.lista_elementos = lista_elementos;
        firePropertyChange(PROP_LISTA_ELEMENTOS, oldLista_elementos, lista_elementos);
    }

}
