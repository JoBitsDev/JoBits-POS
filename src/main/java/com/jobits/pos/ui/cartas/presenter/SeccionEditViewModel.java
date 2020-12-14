/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.cartas.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.domain.models.Seccion;
import com.jobits.pos.ui.viewmodel.AbstractViewModel;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class SeccionEditViewModel extends AbstractViewModel {

    private String nombre_seccion;

    public static final String PROP_NOMBRE_SECCION = "nombre_seccion";

    private ArrayListModel<Seccion> lista_secciones_agregadas = new ArrayListModel();

    public static final String PROP_LISTA_SECCIONES_AGREGADAS = "lista_secciones_agregadas";

    private Seccion seccion_seleccionada;

    public static final String PROP_SECCION_SELECCIONADA = "seccion_seleccionada";

    private ArrayListModel<Seccion> lista_secciones = new ArrayListModel();

    public static final String PROP_LISTA_SECCIONES = "lista_secciones";

    /**
     * Get the value of lista_secciones
     *
     * @return the value of lista_secciones
     */
    public ArrayListModel<Seccion> getLista_secciones() {
        return lista_secciones;
    }

    /**
     * Set the value of lista_secciones
     *
     * @param lista_secciones new value of lista_secciones
     */
    public void setLista_secciones(ArrayListModel<Seccion> lista_secciones) {
        ArrayListModel<Seccion> oldLista_secciones = this.lista_secciones;
        this.lista_secciones = lista_secciones;
        firePropertyChange(PROP_LISTA_SECCIONES, oldLista_secciones, lista_secciones);
    }

    /**
     * Get the value of seccion_seleccionada
     *
     * @return the value of seccion_seleccionada
     */
    public Seccion getSeccion_seleccionada() {
        return seccion_seleccionada;
    }

    /**
     * Set the value of seccion_seleccionada
     *
     * @param seccion_seleccionada new value of seccion_seleccionada
     */
    public void setSeccion_seleccionada(Seccion seccion_seleccionada) {
        Seccion oldSeccion_seleccionada = this.seccion_seleccionada;
        this.seccion_seleccionada = seccion_seleccionada;
        firePropertyChange(PROP_SECCION_SELECCIONADA, oldSeccion_seleccionada, seccion_seleccionada);
    }

    /**
     * Get the value of lista_secciones_agregadas
     *
     * @return the value of lista_secciones_agregadas
     */
    public ArrayListModel<Seccion> getLista_secciones_agregadas() {
        return lista_secciones_agregadas;
    }

    /**
     * Set the value of lista_secciones_agregadas
     *
     * @param lista_secciones_agregadas new value of lista_secciones_agregadas
     */
    public void setLista_secciones_agregadas(ArrayListModel<Seccion> lista_secciones_agregadas) {
        ArrayListModel<Seccion> oldLista_secciones_agregadas = this.lista_secciones_agregadas;
        this.lista_secciones_agregadas = lista_secciones_agregadas;
        firePropertyChange(PROP_LISTA_SECCIONES_AGREGADAS, oldLista_secciones_agregadas, lista_secciones_agregadas);
    }

    /**
     * Get the value of nombre_seccion
     *
     * @return the value of nombre_seccion
     */
    public String getNombre_seccion() {
        return nombre_seccion;
    }

    /**
     * Set the value of nombre_seccion
     *
     * @param nombre_seccion new value of nombre_seccion
     */
    public void setNombre_seccion(String nombre_seccion) {
        String oldNombre_seccion = this.nombre_seccion;
        this.nombre_seccion = nombre_seccion;
        firePropertyChange(PROP_NOMBRE_SECCION, oldNombre_seccion, nombre_seccion);
    }

}
