/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.reportes.presenter;

import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.viewmodel.AbstractViewModel;
import java.util.Calendar;

/**
 *
 * @author Home
 */
public class ReportarBugViewModel extends AbstractViewModel {

    private String usuario_loggeado;

    public static final String PROP_USUARIO_LOGGEADO = "usuario_loggeado";

    private String fecha;

    public static final String PROP_FECHA = "fecha";

    private String titulo_reporte;

    public static final String PROP_TITULO_REPORTE = "titulo_reporte";

    private String descripcion_reporte;

    public static final String PROP_DESCRIPCION_REPORTE = "descripcion_reporte";

    /**
     * Get the value of descripcion_reporte
     *
     * @return the value of descripcion_reporte
     */
    public String getDescripcion_reporte() {
        return descripcion_reporte;
    }

    /**
     * Set the value of descripcion_reporte
     *
     * @param descripcion_reporte new value of descripcion_reporte
     */
    public void setDescripcion_reporte(String descripcion_reporte) {
        String oldDescripcion_reporte = this.descripcion_reporte;
        this.descripcion_reporte = descripcion_reporte;
        firePropertyChange(PROP_DESCRIPCION_REPORTE, oldDescripcion_reporte, descripcion_reporte);
    }

    /**
     * Get the value of titulo_reporte
     *
     * @return the value of titulo_reporte
     */
    public String getTitulo_reporte() {
        return titulo_reporte;
    }

    /**
     * Set the value of titulo_reporte
     *
     * @param titulo_reporte new value of titulo_reporte
     */
    public void setTitulo_reporte(String titulo_reporte) {
        String oldTitulo_reporte = this.titulo_reporte;
        this.titulo_reporte = titulo_reporte;
        firePropertyChange(PROP_TITULO_REPORTE, oldTitulo_reporte, titulo_reporte);
    }

    /**
     * Get the value of fecha
     *
     * @return the value of fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Set the value of fecha
     *
     * @param fecha new value of fecha
     */
    public void setFecha(String fecha) {
        String oldFecha = this.fecha;
        this.fecha = fecha;
        firePropertyChange(PROP_FECHA, oldFecha, fecha);
    }

    /**
     * Get the value of usuario_loggeado
     *
     * @return the value of usuario_loggeado
     */
    public String getUsuario_loggeado() {
        return usuario_loggeado;
    }

    /**
     * Set the value of usuario_loggeado
     *
     * @param usuario_loggeado new value of usuario_loggeado
     */
    public void setUsuario_loggeado(String usuario_loggeado) {
        String oldUsuario_loggeado = this.usuario_loggeado;
        this.usuario_loggeado = usuario_loggeado;
        firePropertyChange(PROP_USUARIO_LOGGEADO, oldUsuario_loggeado, usuario_loggeado);
    }

}
