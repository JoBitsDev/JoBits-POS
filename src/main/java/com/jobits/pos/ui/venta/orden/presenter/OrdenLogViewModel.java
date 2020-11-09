/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.orden.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.ui.viewmodel.AbstractViewModel;

/**
 *
 * @author Home
 */
public class OrdenLogViewModel extends AbstractViewModel {

    private String[] log_selecionado;

    public static final String PROP_LOG_SELECIONADO = "log_selecionado";

    private ArrayListModel<String[]> log_list = new ArrayListModel();

    public static final String PROP_LOG_LIST = "log_list";

    private String codigo_orden = "<Orden>";

    public static final String PROP_CODIGO_ORDEN = "codigo_orden";

    /**
     * Get the value of codigo_orden
     *
     * @return the value of codigo_orden
     */
    public String getCodigo_orden() {
        return codigo_orden;
    }

    /**
     * Set the value of codigo_orden
     *
     * @param codigo_orden new value of codigo_orden
     */
    public void setCodigo_orden(String codigo_orden) {
        String oldCodigo_orden = this.codigo_orden;
        this.codigo_orden = codigo_orden;
        firePropertyChange(PROP_CODIGO_ORDEN, oldCodigo_orden, codigo_orden);
    }

    /**
     * Get the value of log_list
     *
     * @return the value of log_list
     */
    public ArrayListModel<String[]> getLog_list() {
        return log_list;
    }

    /**
     * Set the value of log_list
     *
     * @param log_list new value of log_list
     */
    public void setLog_list(ArrayListModel<String[]> log_list) {
        ArrayListModel<String[]> oldLog_list = this.log_list;
        this.log_list = log_list;
        firePropertyChange(PROP_LOG_LIST, oldLog_list, log_list);
    }

    /**
     * Get the value of log_selecionado
     *
     * @return the value of log_selecionado
     */
    public String[] getLog_selecionado() {
        return log_selecionado;
    }

    /**
     * Set the value of log_selecionado
     *
     * @param log_selecionado new value of log_selecionado
     */
    public void setLog_selecionado(String[] log_selecionado) {
        String[] oldLog_selecionado = this.log_selecionado;
        this.log_selecionado = log_selecionado;
        firePropertyChange(PROP_LOG_SELECIONADO, oldLog_selecionado, log_selecionado);
    }

}
