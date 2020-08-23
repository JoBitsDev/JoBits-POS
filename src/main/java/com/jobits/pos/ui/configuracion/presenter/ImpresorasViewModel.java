/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.configuracion.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.servicios.impresion.Impresora;
import com.jobits.pos.ui.viewmodel.AbstractListViewModel;

/**
 *
 * @author Home
 */
public class ImpresorasViewModel extends AbstractListViewModel<Impresora> {

    private Impresora impresora_seleccionada;

    public static final String PROP_IMPRESORA_SELECCIONADA = "impresora_seleccionada";

    private ArrayListModel<Impresora> lista_impresoras = new ArrayListModel<>();

    public static final String PROP_LISTA_IMPRESORAS = "lista_impresoras";

    private String nombre_impresora_actual;

    public static final String PROP_NOMBRE_IMPRESORA_ACTUAL = "nombre_impresora_actual";

    private ArrayListModel<String> lista_impresoras_sistema = new ArrayListModel<>();

    public static final String PROP_LISTA_IMPRESORAS_SISTEMA = "lista_impresoras_sistema";

    private String impresora_sistema_seleccionada;

    public static final String PROP_IMPRESORA_SISTEMA_SELECCIONADA = "impresora_sistema_seleccionada";

    private ArrayListModel<String> lista_area = new ArrayListModel<>();

    public static final String PROP_LISTA_GRUPO = "lista_area";

    private String area_seleccionado;

    public static final String PROP_GRUPO_SELECCIONADO = "area_seleccionado";

    /**
     * Get the value of impresora_seleccionada
     *
     * @return the value of impresora_seleccionada
     */
    public Impresora getImpresora_seleccionada() {
        return impresora_seleccionada;
    }

    /**
     * Set the value of impresora_seleccionada
     *
     * @param impresora_seleccionada new value of impresora_seleccionada
     */
    public void setImpresora_seleccionada(Impresora impresora_seleccionada) {
        Impresora oldImpresora_seleccionada = this.impresora_seleccionada;
        this.impresora_seleccionada = impresora_seleccionada;
        firePropertyChange(PROP_IMPRESORA_SELECCIONADA, oldImpresora_seleccionada, impresora_seleccionada);
    }

    /**
     * Get the value of lista_impresoras
     *
     * @return the value of lista_impresoras
     */
    public ArrayListModel<Impresora> getLista_impresoras() {
        return lista_impresoras;
    }

    /**
     * Set the value of lista_impresoras
     *
     * @param lista_impresoras new value of lista_impresoras
     */
    public void setLista_impresoras(ArrayListModel<Impresora> lista_impresoras) {
        ArrayListModel<Impresora> oldLista_impresoras = this.lista_impresoras;
        this.lista_impresoras = lista_impresoras;
        firePropertyChange(PROP_LISTA_IMPRESORAS, oldLista_impresoras, lista_impresoras);
    }

    /**
     * Get the value of area_seleccionado
     *
     * @return the value of area_seleccionado
     */
    public String getArea_seleccionado() {
        return area_seleccionado;
    }

    /**
     * Set the value of area_seleccionado
     *
     * @param area_seleccionado new value of area_seleccionado
     */
    public void setArea_seleccionado(String area_seleccionado) {
        String oldArea_seleccionado = this.area_seleccionado;
        this.area_seleccionado = area_seleccionado;
        firePropertyChange(PROP_GRUPO_SELECCIONADO, oldArea_seleccionado, area_seleccionado);
    }

    /**
     * Get the value of lista_area
     *
     * @return the value of lista_area
     */
    public ArrayListModel<String> getLista_area() {
        return lista_area;
    }

    /**
     * Set the value of lista_area
     *
     * @param lista_area new value of lista_area
     */
    public void setLista_area(ArrayListModel<String> lista_area) {
        ArrayListModel<String> oldLista_area = this.lista_area;
        this.lista_area = lista_area;
        firePropertyChange(PROP_LISTA_GRUPO, oldLista_area, lista_area);
    }

    /**
     * Get the value of impresora_sistema_seleccionada
     *
     * @return the value of impresora_sistema_seleccionada
     */
    public String getImpresora_sistema_seleccionada() {
        return impresora_sistema_seleccionada;
    }

    /**
     * Set the value of impresora_sistema_seleccionada
     *
     * @param impresora_sistema_seleccionada new value of
     * impresora_sistema_seleccionada
     */
    public void setImpresora_sistema_seleccionada(String impresora_sistema_seleccionada) {
        String oldImpresora_sistema_seleccionada = this.impresora_sistema_seleccionada;
        this.impresora_sistema_seleccionada = impresora_sistema_seleccionada;
        firePropertyChange(PROP_IMPRESORA_SISTEMA_SELECCIONADA, oldImpresora_sistema_seleccionada, impresora_sistema_seleccionada);
    }

    /**
     * Get the value of lista_impresoras_sistema
     *
     * @return the value of lista_impresoras_sistema
     */
    public ArrayListModel<String> getLista_impresoras_sistema() {
        return lista_impresoras_sistema;
    }

    /**
     * Set the value of lista_impresoras_sistema
     *
     * @param lista_impresoras_sistema new value of lista_impresoras_sistema
     */
    public void setLista_impresoras_sistema(ArrayListModel<String> lista_impresoras_sistema) {
        ArrayListModel<String> oldLista_impresoras_sistema = this.lista_impresoras_sistema;
        this.lista_impresoras_sistema = lista_impresoras_sistema;
        firePropertyChange(PROP_LISTA_IMPRESORAS_SISTEMA, oldLista_impresoras_sistema, lista_impresoras_sistema);
    }

    /**
     * Get the value of nombre_impresora_actual
     *
     * @return the value of nombre_impresora_actual
     */
    public String getNombre_impresora_actual() {
        return nombre_impresora_actual;
    }

    /**
     * Set the value of nombre_impresora_actual
     *
     * @param nombre_impresora_actual new value of nombre_impresora_actual
     */
    public void setNombre_impresora_actual(String nombre_impresora_actual) {
        String oldNombre_impresora_actual = this.nombre_impresora_actual;
        this.nombre_impresora_actual = nombre_impresora_actual;
        firePropertyChange(PROP_NOMBRE_IMPRESORA_ACTUAL, oldNombre_impresora_actual, nombre_impresora_actual);
    }

}
