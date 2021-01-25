/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.configuracion.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.io.DataHeader;
import com.jobits.pos.ui.configuracion.DataHeaderWrapper;
import com.jobits.pos.ui.configuracion.OpcionIO;
import com.jobits.pos.ui.configuracion.TipoDato;
import com.jobits.pos.ui.viewmodel.AbstractListViewModel;
import java.io.File;

/**
 *
 * @author Home
 */
public class ImportarExportarViewModel extends AbstractListViewModel {

    private OpcionIO opcion_seleccionada = OpcionIO.DEFAULT;

    public static final String PROP_OPCION_SELECCIONADA = "opcion_seleccionada";

    private ArrayListModel<OpcionIO> lista_opciones = new ArrayListModel<>();

    public static final String PROP_LISTA_OPCIONES = "lista_opciones";

    private TipoDato tipo_dato_seleccionado = TipoDato.DEFAULT;

    public static final String PROP_TIPO_DATO_SELECCIONADO = "tipo_dato_seleccionado";

    private ArrayListModel<TipoDato> lista_tipo_dato = new ArrayListModel<>();

    public static final String PROP_LISTA_TIPO_DATO = "lista_tipo_dato";

    private String data_type_text;

    public static final String PROP_DATA_TYPE_TEXT = "data_type_text";

    private String opcion_text;

    public static final String PROP_OPCION_TEXT = "opcion_text";

    private boolean enable_tipo_dato;

    public static final String PROP_ENABLE_TIPO_DATO = "enable_tipo_dato";

    private boolean enable_button_to_sel_archivo = false;

    public static final String PROP_ENABLE_BUTTON_TO_SEL_ARCHIVO = "enable_button_to_sel_archivo";

    private File archivo_seleccionado;

    public static final String PROP_ARCHIVO_SELECCIONADO = "archivo_seleccionado";

    private String nombre_archivo_seleccionado = "Ningun Archivo Seleccionado";

    public static final String PROP_NOMBRE_ARCHIVO_SELECCIONADO = "nombre_archivo_seleccionado";

    private boolean enable_button_to_sel_columns = false;

    public static final String PROP_ENABLE_BUTTON_TO_SEL_COLUMNS = "enable_button_to_sel_columns";

    private DataHeader data_header_seleccionado;

    public static final String PROP_DATA_HEADER_SELECCIONADO = "data_header_seleccionado";

    private ArrayListModel<DataHeader> lista_data_header = new ArrayListModel<>();

    public static final String PROP_LISTA_DATA_HEADER = "lista_data_header";

    private String raw_header_seleccionado;

    public static final String PROP_RAW_HEADER_SELECCIONADO = "raw_header_seleccionado";

    private ArrayListModel<String> lista_raw_header = new ArrayListModel<>();

    public static final String PROP_LISTA_RAW_HEADER = "lista_raw_header";

    private ArrayListModel<DataHeaderWrapper> lista_data_header_wrapper = new ArrayListModel<>();

    public static final String PROP_LISTA_DATA_HEADER_WRAPPER = "lista_data_header_wrapper";

    private DataHeaderWrapper data_header_wrapper_seleccionado;

    public static final String PROP_DATA_HEADER_WRAPPER_SELECCIONADO = "data_header_wrapper_seleccionado";

    private boolean enable_button_to_ready = false;

    public static final String PROP_ENABLE_BUTTON_TO_READY = "enable_button_to_ready";

    private String cantidad_datos;

    public static final String PROP_CANTIDAD_DATOS = "cantidad_datos";

    private boolean enable_button_do_action = true;

    public static final String PROP_ENABLE_BUTTON_DO_ACTION = "enable_button_do_action";

    /**
     * Get the value of enable_button_do_action
     *
     * @return the value of enable_button_do_action
     */
    public boolean isEnable_button_do_action() {
        return enable_button_do_action;
    }

    /**
     * Set the value of enable_button_do_action
     *
     * @param enable_button_do_action new value of enable_button_do_action
     */
    public void setEnable_button_do_action(boolean enable_button_do_action) {
        boolean oldEnable_button_do_action = this.enable_button_do_action;
        this.enable_button_do_action = enable_button_do_action;
        firePropertyChange(PROP_ENABLE_BUTTON_DO_ACTION, oldEnable_button_do_action, enable_button_do_action);
    }

    /**
     * Get the value of cantidad_datos
     *
     * @return the value of cantidad_datos
     */
    public String getCantidad_datos() {
        return cantidad_datos;
    }

    /**
     * Set the value of cantidad_datos
     *
     * @param cantidad_datos new value of cantidad_datos
     */
    public void setCantidad_datos(String cantidad_datos) {
        String oldCantidad_datos = this.cantidad_datos;
        this.cantidad_datos = cantidad_datos;
        firePropertyChange(PROP_CANTIDAD_DATOS, oldCantidad_datos, cantidad_datos);
    }

    /**
     * Get the value of enable_button_to_ready
     *
     * @return the value of enable_button_to_ready
     */
    public boolean isEnable_button_to_ready() {
        return enable_button_to_ready;
    }

    /**
     * Set the value of enable_button_to_ready
     *
     * @param enable_button_to_ready new value of enable_button_to_ready
     */
    public void setEnable_button_to_ready(boolean enable_button_to_ready) {
        boolean oldEnable_button_to_ready = this.enable_button_to_ready;
        this.enable_button_to_ready = enable_button_to_ready;
        firePropertyChange(PROP_ENABLE_BUTTON_TO_READY, oldEnable_button_to_ready, enable_button_to_ready);
    }

    /**
     * Get the value of data_header_wrapper_seleccionado
     *
     * @return the value of data_header_wrapper_seleccionado
     */
    public DataHeaderWrapper getData_header_wrapper_seleccionado() {
        return data_header_wrapper_seleccionado;
    }

    /**
     * Set the value of data_header_wrapper_seleccionado
     *
     * @param data_header_wrapper_seleccionado new value of
     * data_header_wrapper_seleccionado
     */
    public void setData_header_wrapper_seleccionado(DataHeaderWrapper data_header_wrapper_seleccionado) {
        DataHeaderWrapper oldData_header_wrapper_seleccionado = this.data_header_wrapper_seleccionado;
        this.data_header_wrapper_seleccionado = data_header_wrapper_seleccionado;
        firePropertyChange(PROP_DATA_HEADER_WRAPPER_SELECCIONADO, oldData_header_wrapper_seleccionado, data_header_wrapper_seleccionado);
    }

    /**
     * Get the value of lista_data_header_wrapper
     *
     * @return the value of lista_data_header_wrapper
     */
    public ArrayListModel<DataHeaderWrapper> getLista_data_header_wrapper() {
        return lista_data_header_wrapper;
    }

    /**
     * Set the value of lista_data_header_wrapper
     *
     * @param lista_data_header_wrapper new value of lista_data_header_wrapper
     */
    public void setLista_data_header_wrapper(ArrayListModel<DataHeaderWrapper> lista_data_header_wrapper) {
        ArrayListModel<DataHeaderWrapper> oldLista_data_header_wrapper = this.lista_data_header_wrapper;
        this.lista_data_header_wrapper = lista_data_header_wrapper;
        firePropertyChange(PROP_LISTA_DATA_HEADER_WRAPPER, oldLista_data_header_wrapper, lista_data_header_wrapper);
    }

    /**
     * Get the value of lista_raw_header
     *
     * @return the value of lista_raw_header
     */
    public ArrayListModel<String> getLista_raw_header() {
        return lista_raw_header;
    }

    /**
     * Set the value of lista_raw_header
     *
     * @param lista_raw_header new value of lista_raw_header
     */
    public void setLista_raw_header(ArrayListModel<String> lista_raw_header) {
        ArrayListModel<String> oldLista_raw_header = this.lista_raw_header;
        this.lista_raw_header = lista_raw_header;
        firePropertyChange(PROP_LISTA_RAW_HEADER, oldLista_raw_header, lista_raw_header);
    }

    /**
     * Get the value of raw_header_seleccionado
     *
     * @return the value of raw_header_seleccionado
     */
    public String getRaw_header_seleccionado() {
        return raw_header_seleccionado;
    }

    /**
     * Set the value of raw_header_seleccionado
     *
     * @param raw_header_seleccionado new value of raw_header_seleccionado
     */
    public void setRaw_header_seleccionado(String raw_header_seleccionado) {
        String oldRaw_header_seleccionado = this.raw_header_seleccionado;
        this.raw_header_seleccionado = raw_header_seleccionado;
        firePropertyChange(PROP_RAW_HEADER_SELECCIONADO, oldRaw_header_seleccionado, raw_header_seleccionado);
    }

    /**
     * Get the value of lista_data_header
     *
     * @return the value of lista_data_header
     */
    public ArrayListModel<DataHeader> getLista_data_header() {
        return lista_data_header;
    }

    /**
     * Set the value of lista_data_header
     *
     * @param lista_data_header new value of lista_data_header
     */
    public void setLista_data_header(ArrayListModel<DataHeader> lista_data_header) {
        ArrayListModel<DataHeader> oldLista_data_header = this.lista_data_header;
        this.lista_data_header = lista_data_header;
        firePropertyChange(PROP_LISTA_DATA_HEADER, oldLista_data_header, lista_data_header);
    }

    /**
     * Get the value of data_header_seleccionado
     *
     * @return the value of data_header_seleccionado
     */
    public DataHeader getData_header_seleccionado() {
        return data_header_seleccionado;
    }

    /**
     * Set the value of data_header_seleccionado
     *
     * @param data_header_seleccionado new value of data_header_seleccionado
     */
    public void setData_header_seleccionado(DataHeader data_header_seleccionado) {
        DataHeader oldData_header_seleccionado = this.data_header_seleccionado;
        this.data_header_seleccionado = data_header_seleccionado;
        firePropertyChange(PROP_DATA_HEADER_SELECCIONADO, oldData_header_seleccionado, data_header_seleccionado);
    }

    /**
     * Get the value of data_type_text
     *
     * @return the value of data_type_text
     */
    public String getData_type_text() {
        return data_type_text;
    }

    /**
     * Set the value of data_type_text
     *
     * @param data_type_text new value of data_type_text
     */
    public void setData_type_text(String data_type_text) {
        String oldData_type_text = this.data_type_text;
        this.data_type_text = data_type_text;
        firePropertyChange(PROP_DATA_TYPE_TEXT, oldData_type_text, data_type_text);
    }

    /**
     * Get the value of enable_button_to_sel_columns
     *
     * @return the value of enable_button_to_sel_columns
     */
    public boolean isEnable_button_to_sel_columns() {
        return enable_button_to_sel_columns;
    }

    /**
     * Set the value of enable_button_to_sel_columns
     *
     * @param enable_button_to_sel_columns new value of
     * enable_button_to_sel_columns
     */
    public void setEnable_button_to_sel_columns(boolean enable_button_to_sel_columns) {
        boolean oldEnable_button_to_sel_columns = this.enable_button_to_sel_columns;
        this.enable_button_to_sel_columns = enable_button_to_sel_columns;
        firePropertyChange(PROP_ENABLE_BUTTON_TO_SEL_COLUMNS, oldEnable_button_to_sel_columns, enable_button_to_sel_columns);
    }

    /**
     * Get the value of nombre_archivo_seleccionado
     *
     * @return the value of nombre_archivo_seleccionado
     */
    public String getNombre_archivo_seleccionado() {
        return nombre_archivo_seleccionado;
    }

    /**
     * Set the value of nombre_archivo_seleccionado
     *
     * @param nombre_archivo_seleccionado new value of
     * nombre_archivo_seleccionado
     */
    public void setNombre_archivo_seleccionado(String nombre_archivo_seleccionado) {
        String oldNombre_archivo_seleccionado = this.nombre_archivo_seleccionado;
        this.nombre_archivo_seleccionado = nombre_archivo_seleccionado;
        firePropertyChange(PROP_NOMBRE_ARCHIVO_SELECCIONADO, oldNombre_archivo_seleccionado, nombre_archivo_seleccionado);
    }

    /**
     * Get the value of archivo_seleccionado
     *
     * @return the value of archivo_seleccionado
     */
    public File getArchivo_seleccionado() {
        return archivo_seleccionado;
    }

    /**
     * Set the value of archivo_seleccionado
     *
     * @param archivo_seleccionado new value of archivo_seleccionado
     */
    public void setArchivo_seleccionado(File archivo_seleccionado) {
        File oldArchivo_seleccionado = this.archivo_seleccionado;
        this.archivo_seleccionado = archivo_seleccionado;
        firePropertyChange(PROP_ARCHIVO_SELECCIONADO, oldArchivo_seleccionado, archivo_seleccionado);
    }

    /**
     * Get the value of enable_button_to_sel_archivo
     *
     * @return the value of enable_button_to_sel_archivo
     */
    public boolean isEnable_button_to_sel_archivo() {
        return enable_button_to_sel_archivo;
    }

    /**
     * Set the value of enable_button_to_sel_archivo
     *
     * @param enable_button_to_sel_archivo new value of
     * enable_button_to_sel_archivo
     */
    public void setEnable_button_to_sel_archivo(boolean enable_button_to_sel_archivo) {
        boolean oldEnable_button_to_sel_archivo = this.enable_button_to_sel_archivo;
        this.enable_button_to_sel_archivo = enable_button_to_sel_archivo;
        firePropertyChange(PROP_ENABLE_BUTTON_TO_SEL_ARCHIVO, oldEnable_button_to_sel_archivo, enable_button_to_sel_archivo);
    }

    /**
     * Get the value of enable_tipo_dato
     *
     * @return the value of enable_tipo_dato
     */
    public boolean isEnable_tipo_dato() {
        return enable_tipo_dato;
    }

    /**
     * Set the value of enable_tipo_dato
     *
     * @param enable_tipo_dato new value of enable_tipo_dato
     */
    public void setEnable_tipo_dato(boolean enable_tipo_dato) {
        boolean oldEnable_tipo_dato = this.enable_tipo_dato;
        this.enable_tipo_dato = enable_tipo_dato;
        firePropertyChange(PROP_ENABLE_TIPO_DATO, oldEnable_tipo_dato, enable_tipo_dato);
    }

    /**
     * Get the value of opcion_text
     *
     * @return the value of opcion_text
     */
    public String getOpcion_text() {
        return opcion_text;
    }

    /**
     * Set the value of opcion_text
     *
     * @param opcion_text new value of opcion_text
     */
    public void setOpcion_text(String opcion_text) {
        String oldOpcion_text = this.opcion_text;
        this.opcion_text = opcion_text;
        firePropertyChange(PROP_OPCION_TEXT, oldOpcion_text, opcion_text);
    }

    /**
     * Get the value of lista_tipo_dato
     *
     * @return the value of lista_tipo_dato
     */
    public ArrayListModel<TipoDato> getLista_tipo_dato() {
        return lista_tipo_dato;
    }

    /**
     * Set the value of lista_tipo_dato
     *
     * @param lista_tipo_dato new value of lista_tipo_dato
     */
    public void setLista_tipo_dato(ArrayListModel<TipoDato> lista_tipo_dato) {
        ArrayListModel<TipoDato> oldLista_tipo_dato = this.lista_tipo_dato;
        this.lista_tipo_dato = lista_tipo_dato;
        firePropertyChange(PROP_LISTA_TIPO_DATO, oldLista_tipo_dato, lista_tipo_dato);
    }

    /**
     * Get the value of tipo_dato_seleccionado
     *
     * @return the value of tipo_dato_seleccionado
     */
    public TipoDato getTipo_dato_seleccionado() {
        return tipo_dato_seleccionado;
    }

    /**
     * Set the value of tipo_dato_seleccionado
     *
     * @param tipo_dato_seleccionado new value of tipo_dato_seleccionado
     */
    public void setTipo_dato_seleccionado(TipoDato tipo_dato_seleccionado) {
        TipoDato oldTipo_dato_seleccionado = this.tipo_dato_seleccionado;
        this.tipo_dato_seleccionado = tipo_dato_seleccionado;
        firePropertyChange(PROP_TIPO_DATO_SELECCIONADO, oldTipo_dato_seleccionado, tipo_dato_seleccionado);
    }

    /**
     * Get the value of lista_opciones
     *
     * @return the value of lista_opciones
     */
    public ArrayListModel<OpcionIO> getLista_opciones() {
        return lista_opciones;
    }

    /**
     * Set the value of lista_opciones
     *
     * @param lista_opciones new value of lista_opciones
     */
    public void setLista_opciones(ArrayListModel<OpcionIO> lista_opciones) {
        ArrayListModel<OpcionIO> oldLista_opciones = this.lista_opciones;
        this.lista_opciones = lista_opciones;
        firePropertyChange(PROP_LISTA_OPCIONES, oldLista_opciones, lista_opciones);
    }

    /**
     * Get the value of opcion_seleccionada
     *
     * @return the value of opcion_seleccionada
     */
    public OpcionIO getOpcion_seleccionada() {
        return opcion_seleccionada;
    }

    /**
     * Set the value of opcion_seleccionada
     *
     * @param opcion_seleccionada new value of opcion_seleccionada
     */
    public void setOpcion_seleccionada(OpcionIO opcion_seleccionada) {
        OpcionIO oldOpcion_seleccionada = this.opcion_seleccionada;
        this.opcion_seleccionada = opcion_seleccionada;
        firePropertyChange(PROP_OPCION_SELECCIONADA, oldOpcion_seleccionada, opcion_seleccionada);
    }

}
