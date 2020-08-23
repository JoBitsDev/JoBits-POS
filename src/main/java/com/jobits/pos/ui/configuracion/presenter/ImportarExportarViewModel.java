/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.configuracion.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.ui.viewmodel.AbstractListViewModel;
import java.util.Arrays;

/**
 *
 * @author Home
 */
public class ImportarExportarViewModel extends AbstractListViewModel {

    private ArrayListModel importar_exportar_opciones = new ArrayListModel();

    public static final String PROP_IMPORTAR_EXPORTAR_OPCIONES = "importar_exportar_opciones";

    private String importar_exportar_opcion_seleccionada;

    public static final String PROP_IMPORTAR_EXPORTAR_OPCION_SELECCIONADA = "importar_exportar_opcion_seleccionada";

    private ArrayListModel lista_tipo_datos = new ArrayListModel();

    public static final String PROP_LISTA_TIPO_DATOS = "lista_tipo_datos";

    private String tipo_dato_seleccionado;

    public static final String PROP_TIPO_DATO_SELECCIONADO = "tipo_dato_seleccionado";

    private boolean tipo_dato_enabled = false;

    public static final String PROP_TIPO_DATO_ENABLED = "tipo_dato_enabled";

    private String button_text = "---";

    public static final String PROP_BUTTON_TEXT = "button_text";

    private boolean button_enabled = false;

    public static final String PROP_BUTTON_ENABLED = "button_enabled";

    /**
     * Get the value of button_enabled
     *
     * @return the value of button_enabled
     */
    public boolean isButton_enabled() {
        return button_enabled;
    }

    /**
     * Set the value of button_enabled
     *
     * @param button_enabled new value of button_enabled
     */
    public void setButton_enabled(boolean button_enabled) {
        boolean oldButton_enabled = this.button_enabled;
        this.button_enabled = button_enabled;
        firePropertyChange(PROP_BUTTON_ENABLED, oldButton_enabled, button_enabled);
    }

    /**
     * Get the value of tipo_dato_enabled
     *
     * @return the value of tipo_dato_enabled
     */
    public boolean isTipo_dato_enabled() {
        return tipo_dato_enabled;
    }

    /**
     * Set the value of tipo_dato_enabled
     *
     * @param tipo_dato_enabled new value of tipo_dato_enabled
     */
    public void setTipo_dato_enabled(boolean tipo_dato_enabled) {
        boolean oldTipo_dato_enabled = this.tipo_dato_enabled;
        this.tipo_dato_enabled = tipo_dato_enabled;
        firePropertyChange(PROP_TIPO_DATO_ENABLED, oldTipo_dato_enabled, tipo_dato_enabled);
    }

    /**
     * Get the value of button_text
     *
     * @return the value of button_text
     */
    public String getButton_text() {
        return button_text;
    }

    /**
     * Set the value of button_text
     *
     * @param button_text new value of button_text
     */
    public void setButton_text(String button_text) {
        String oldButton_text = this.button_text;
        this.button_text = button_text;
        firePropertyChange(PROP_BUTTON_TEXT, oldButton_text, button_text);
    }

    /**
     * Get the value of tipo_dato_seleccionado
     *
     * @return the value of tipo_dato_seleccionado
     */
    public String getTipo_dato_seleccionado() {
        return tipo_dato_seleccionado;
    }

    /**
     * Set the value of tipo_dato_seleccionado
     *
     * @param tipo_dato_seleccionado new value of tipo_dato_seleccionado
     */
    public void setTipo_dato_seleccionado(String tipo_dato_seleccionado) {
        String oldTipo_dato_seleccionado = this.tipo_dato_seleccionado;
        this.tipo_dato_seleccionado = tipo_dato_seleccionado;
        firePropertyChange(PROP_TIPO_DATO_SELECCIONADO, oldTipo_dato_seleccionado, tipo_dato_seleccionado);
    }

    /**
     * Get the value of lista_tipo_datos
     *
     * @return the value of lista_tipo_datos
     */
    public ArrayListModel getLista_tipo_datos() {
        return lista_tipo_datos;
    }

    /**
     * Set the value of lista_tipo_datos
     *
     * @param lista_tipo_datos new value of lista_tipo_datos
     */
    public void setLista_tipo_datos(ArrayListModel lista_tipo_datos) {
        ArrayListModel oldLista_tipo_datos = this.lista_tipo_datos;
        this.lista_tipo_datos = lista_tipo_datos;
        firePropertyChange(PROP_LISTA_TIPO_DATOS, oldLista_tipo_datos, lista_tipo_datos);
    }

    /**
     * Get the value of importar_exportar_opcion_seleccionada
     *
     * @return the value of importar_exportar_opcion_seleccionada
     */
    public String getImportar_exportar_opcion_seleccionada() {
        return importar_exportar_opcion_seleccionada;
    }

    /**
     * Set the value of importar_exportar_opcion_seleccionada
     *
     * @param importar_exportar_opcion_seleccionada new value of
     * importar_exportar_opcion_seleccionada
     */
    public void setImportar_exportar_opcion_seleccionada(String importar_exportar_opcion_seleccionada) {
        String oldImportar_exportar_opcion_seleccionada = this.importar_exportar_opcion_seleccionada;
        this.importar_exportar_opcion_seleccionada = importar_exportar_opcion_seleccionada;
        firePropertyChange(PROP_IMPORTAR_EXPORTAR_OPCION_SELECCIONADA, oldImportar_exportar_opcion_seleccionada, importar_exportar_opcion_seleccionada);
    }

    /**
     * Get the value of importar_exportar_opciones
     *
     * @return the value of importar_exportar_opciones
     */
    public ArrayListModel getImportar_exportar_opciones() {
        return importar_exportar_opciones;
    }

    /**
     * Set the value of importar_exportar_opciones
     *
     * @param importar_exportar_opciones new value of importar_exportar_opciones
     */
    public void setImportar_exportar_opciones(ArrayListModel importar_exportar_opciones) {
        ArrayListModel oldImportar_exportar_opciones = this.importar_exportar_opciones;
        this.importar_exportar_opciones = importar_exportar_opciones;
        firePropertyChange(PROP_IMPORTAR_EXPORTAR_OPCIONES, oldImportar_exportar_opciones, importar_exportar_opciones);
    }

}
