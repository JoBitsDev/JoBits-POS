/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.orden.presenter;

import com.jobits.pos.ui.viewmodel.AbstractViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author ERIK QUESADA
 */
public class CalcularCambioViewModel extends AbstractViewModel {

    private String codigo_orden;

    public static final String PROP_CODIGO_ORDEN = "codigo_orden";

    private String total_a_pagar;

    public static final String PROP_TOTAL_A_PAGAR = "total_a_pagar";

    private float entrada_moneda_nacional;

    public static final String PROP_ENTRADA_MONEDA_NACIONAL = "entrada_moneda_nacional";

    private String cambio;

    public static final String PROP_CAMBIO = "cambio";

    private String moneda;

    public static final String PROP_MONEDA = "moneda";

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        String oldMoneda = this.moneda;
        this.moneda = moneda;
        firePropertyChange(PROP_MONEDA, oldMoneda, moneda);
    }

    /**
     * Get the value of cambio
     *
     * @return the value of cambio
     */
    public String getCambio() {
        return cambio;
    }

    /**
     * Set the value of cambio
     *
     * @param cambio new value of cambio
     */
    public void setCambio(String cambio) {
        String oldCambio = this.cambio;
        this.cambio = cambio;
        firePropertyChange(PROP_CAMBIO, oldCambio, cambio);
    }

    /**
     * Get the value of entrada_moneda_nacional
     *
     * @return the value of entrada_moneda_nacional
     */
    public float getEntrada_moneda_nacional() {
        return entrada_moneda_nacional;
    }

    /**
     * Set the value of entrada_moneda_nacional
     *
     * @param entrada_moneda_nacional new value of entrada_moneda_nacional
     */
    public void setEntrada_moneda_nacional(float entrada_moneda_nacional) {
        float oldEntrada_moneda_nacional = this.entrada_moneda_nacional;
        this.entrada_moneda_nacional = entrada_moneda_nacional;
        firePropertyChange(PROP_ENTRADA_MONEDA_NACIONAL, oldEntrada_moneda_nacional, entrada_moneda_nacional);
    }

    /**
     * Get the value of total_a_pagar
     *
     * @return the value of total_a_pagar
     */
    public String getTotal_a_pagar() {
        return total_a_pagar;
    }

    /**
     * Set the value of total_a_pagar
     *
     * @param total_a_pagar new value of total_a_pagar
     */
    public void setTotal_a_pagar(String total_a_pagar) {
        String oldTotal_a_pagar = this.total_a_pagar;
        this.total_a_pagar = total_a_pagar;
        firePropertyChange(PROP_TOTAL_A_PAGAR, oldTotal_a_pagar, total_a_pagar);
    }

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

}
