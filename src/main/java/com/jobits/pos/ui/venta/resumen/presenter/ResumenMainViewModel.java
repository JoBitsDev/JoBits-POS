/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.resumen.presenter;

import com.jobits.pos.ui.viewmodel.AbstractViewModel;
import java.util.Date;
import javax.swing.ImageIcon;

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
    private String total_venta = "0.0";

    public static final String PROP_TOTAL_VENTA = "total_venta";

    private String total_autorizos = "0.0";

    public static final String PROP_TOTAL_AUTORIZOS = "total_autorizos";

    private String total_costos = "0.0";

    public static final String PROP_TOTAL_COSTOS = "total_costos";

    private String total_salarios = "0.0";

    public static final String PROP_TOTAL_SALARIOS = "total_salarios";

    private String total_gastos = "0.0";

    public static final String PROP_TOTAL_GASTOS = "total_gastos";

    private String total_utilidades = "0.0";

    public static final String PROP_TOTAL_UTILIDADES = "total_utilidades";

    private ImageIcon profits_icon = new ImageIcon(getClass().getResource("/restManager/resources/icons pack/neutral_negro.png"));//TODO: corregir url

    public static final String PROP_PROFITS_ICON = "profits_icon";

    private String rdi_value = "0.0";

    public static final String PROP_RDI_VALUE = "rdi_value";

    /**
     * Get the value of rdi_value
     *
     * @return the value of rdi_value
     */
    public String getRdi_value() {
        return rdi_value;
    }

    /**
     * Set the value of rdi_value
     *
     * @param rdi_value new value of rdi_value
     */
    public void setRdi_value(String rdi_value) {
        String oldRdi_value = this.rdi_value;
        this.rdi_value = rdi_value;
        firePropertyChange(PROP_RDI_VALUE, oldRdi_value, rdi_value);
    }

    /**
     * Get the value of profits_icon
     *
     * @return the value of profits_icon
     */
    public ImageIcon getProfits_icon() {
        return profits_icon;
    }

    /**
     * Set the value of profits_icon
     *
     * @param profits_icon new value of profits_icon
     */
    public void setProfits_icon(ImageIcon profits_icon) {
        ImageIcon oldProfits_icon = this.profits_icon;
        this.profits_icon = profits_icon;
        firePropertyChange(PROP_PROFITS_ICON, oldProfits_icon, profits_icon);
    }

    /**
     * Get the value of total_utilidades
     *
     * @return the value of total_utilidades
     */
    public String getTotal_utilidades() {
        return total_utilidades;
    }

    /**
     * Set the value of total_utilidades
     *
     * @param total_utilidades new value of total_utilidades
     */
    public void setTotal_utilidades(String total_utilidades) {
        String oldTotal_utilidades = this.total_utilidades;
        this.total_utilidades = total_utilidades;
        firePropertyChange(PROP_TOTAL_UTILIDADES, oldTotal_utilidades, total_utilidades);
    }

    /**
     * Get the value of total_gastos
     *
     * @return the value of total_gastos
     */
    public String getTotal_gastos() {
        return total_gastos;
    }

    /**
     * Set the value of total_gastos
     *
     * @param total_gastos new value of total_gastos
     */
    public void setTotal_gastos(String total_gastos) {
        String oldTotal_gastos = this.total_gastos;
        this.total_gastos = total_gastos;
        firePropertyChange(PROP_TOTAL_GASTOS, oldTotal_gastos, total_gastos);
    }

    /**
     * Get the value of total_salarios
     *
     * @return the value of total_salarios
     */
    public String getTotal_salarios() {
        return total_salarios;
    }

    /**
     * Set the value of total_salarios
     *
     * @param total_salarios new value of total_salarios
     */
    public void setTotal_salarios(String total_salarios) {
        String oldTotal_salarios = this.total_salarios;
        this.total_salarios = total_salarios;
        firePropertyChange(PROP_TOTAL_SALARIOS, oldTotal_salarios, total_salarios);
    }

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
