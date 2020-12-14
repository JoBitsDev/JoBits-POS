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
public class SeccionDetailViewModel extends AbstractViewModel {

    private String nombre_seccion;

    public static final String PROP_NOMBRE_SECCION = "nombre_seccion";

    private ArrayListModel<Seccion> lista_secciones_agregadas = new ArrayListModel();

    public static final String PROP_LISTA_SECCIONES_AGREGADAS = "lista_secciones_agregadas";

    private Seccion seccion_seleccionada;

    public static final String PROP_SECCION_SELECCIONADA = "seccion_seleccionada";

    private ArrayListModel<Seccion> lista_secciones = new ArrayListModel();

    public static final String PROP_LISTA_SECCIONES = "lista_secciones";

    private Seccion seccion_agregada_seleccionada;

    public static final String PROP_SECCION_AGREGADA_SELECCIONADA = "seccion_agregada_seleccionada";

    private boolean nombre_habilitado = true;

    public static final String PROP_NOMBRE_HABILITADO = "nombre_habilitado";
    
        private String crear_editar_button_text;

    public static final String PROP_CREAR_EDITAR_BUTTON_TEXT = "crear_editar_button_text";

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
     * Get the value of nombre_habilitado
     *
     * @return the value of nombre_habilitado
     */
    public boolean isNombre_habilitado() {
        return nombre_habilitado;
    }

    /**
     * Set the value of nombre_habilitado
     *
     * @param nombre_habilitado new value of nombre_habilitado
     */
    public void setNombre_habilitado(boolean nombre_habilitado) {
        boolean oldNombre_habilitado = this.nombre_habilitado;
        this.nombre_habilitado = nombre_habilitado;
        firePropertyChange(PROP_NOMBRE_HABILITADO, oldNombre_habilitado, nombre_habilitado);
    }

    /**
     * Get the value of seccion_agregada_seleccionada
     *
     * @return the value of seccion_agregada_seleccionada
     */
    public Seccion getSeccion_agregada_seleccionada() {
        return seccion_agregada_seleccionada;
    }

    /**
     * Set the value of seccion_agregada_seleccionada
     *
     * @param seccion_agregada_seleccionada new value of
     * seccion_agregada_seleccionada
     */
    public void setSeccion_agregada_seleccionada(Seccion seccion_agregada_seleccionada) {
        Seccion oldSeccion_agregada_seleccionada = this.seccion_agregada_seleccionada;
        this.seccion_agregada_seleccionada = seccion_agregada_seleccionada;
        firePropertyChange(PROP_SECCION_AGREGADA_SELECCIONADA, oldSeccion_agregada_seleccionada, seccion_agregada_seleccionada);
    }

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
