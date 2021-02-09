/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.resumen.presenter;

import com.jobits.pos.ui.viewmodel.AbstractViewModel;
import java.util.Date;

/**
 *
 * @author Home
 */
public class ResumenMainViewModel extends AbstractViewModel {

    //Rango de Fechas
    private Date fecha_desde = new Date();

    public static final String PROP_FECHA_DESDE = "fecha_desde";

    private Date fecha_hasta = new Date();

    public static final String PROP_FECHA_HASTA = "fecha_hasta";

    //Visibilidad de Componentes
    private boolean controls_visibility = false;

    public static final String PROP_CONTROLS_VISIBILITY = "controls_visibility";

    //Totales
    private String total_venta = "00.00";

    public static final String PROP_TOTAL_VENTA = "total_venta";

    private String total_autorizos = "00.00";

    public static final String PROP_TOTAL_AUTORIZOS = "total_autorizos";

    private String total_costos = "00.00";

    public static final String PROP_TOTAL_COSTOS = "total_costos";

    /**
     * Get the value of total_costos
     *
     * @return the value of total_costos
     */
    public String getTotal_costos() {
        return total_costos;
    }

    /**
     * Set the value of total_costos
     *
     * @param total_costos new value of total_costos
     */
    public void setTotal_costos(String total_costos) {
        String oldTotal_costos = this.total_costos;
        this.total_costos = total_costos;
        firePropertyChange(PROP_TOTAL_COSTOS, oldTotal_costos, total_costos);
    }

    /**
     * Get the value of total_autorizos
     *
     * @return the value of total_autorizos
     */
    public String getTotal_autorizos() {
        return total_autorizos;
    }

    /**
     * Set the value of total_autorizos
     *
     * @param total_autorizos new value of total_autorizos
     */
    public void setTotal_autorizos(String total_autorizos) {
        String oldTotal_autorizos = this.total_autorizos;
        this.total_autorizos = total_autorizos;
        firePropertyChange(PROP_TOTAL_AUTORIZOS, oldTotal_autorizos, total_autorizos);
    }

    /**
     * Get the value of controls_visibility
     *
     * @return the value of controls_visibility
     */
    public boolean isControls_visibility() {
        return controls_visibility;
    }

    /**
     * Set the value of controls_visibility
     *
     * @param controls_visibility new value of controls_visibility
     */
    public void setControls_visibility(boolean controls_visibility) {
        boolean oldControls_visibility = this.controls_visibility;
        this.controls_visibility = controls_visibility;
        firePropertyChange(PROP_CONTROLS_VISIBILITY, oldControls_visibility, controls_visibility);
    }

    /**
     * Get the value of total_venta
     *
     * @return the value of total_venta
     */
    public String getTotal_venta() {
        return total_venta;
    }

    /**
     * Set the value of total_venta
     *
     * @param total_venta new value of total_venta
     */
    public void setTotal_venta(String total_venta) {
        String oldTotal_venta = this.total_venta;
        this.total_venta = total_venta;
        firePropertyChange(PROP_TOTAL_VENTA, oldTotal_venta, total_venta);
    }

    /**
     * Get the value of fecha_hasta
     *
     * @return the value of fecha_hasta
     */
    public Date getFecha_hasta() {
        return fecha_hasta;
    }

    /**
     * Set the value of fecha_hasta
     *
     * @param fecha_hasta new value of fecha_hasta
     */
    public void setFecha_hasta(Date fecha_hasta) {
        Date oldFecha_hasta = this.fecha_hasta;
        this.fecha_hasta = fecha_hasta;
        firePropertyChange(PROP_FECHA_HASTA, oldFecha_hasta, fecha_hasta);
    }

    /**
     * Get the value of fecha_desde
     *
     * @return the value of fecha_desde
     */
    public Date getFecha_desde() {
        return fecha_desde;
    }

    /**
     * Set the value of fecha_desde
     *
     * @param fecha_desde new value of fecha_desde
     */
    public void setFecha_desde(Date fecha_desde) {
        Date oldFecha_desde = this.fecha_desde;
        this.fecha_desde = fecha_desde;
        firePropertyChange(PROP_FECHA_DESDE, oldFecha_desde, fecha_desde);
    }

}
