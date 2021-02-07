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

    //Totales
    private String total_venta = "XX.XX";

    public static final String PROP_TOTAL_VENTA = "total_venta";

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
