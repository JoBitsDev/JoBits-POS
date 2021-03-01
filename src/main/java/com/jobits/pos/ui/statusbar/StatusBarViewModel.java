/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.statusbar;

import com.jobits.pos.ui.viewmodel.AbstractViewModel;
import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class StatusBarViewModel extends AbstractViewModel {

    private String estado_licencia;

    public static final String PROP_ESTADO_LICENCIA = "estado_licencia";

    private String usuario_registrado;

    public static final String PROP_USUARIO_REGISTRADO = "usuario_registrado";

    private String version_software;

    public static final String PROP_VERSION_SOFTWARE = "version_software";

    private boolean boton_licencia_habilitado;

    public static final String PROP_BOTON_LICENCIA_HABILITADO = "boton_licencia_habilitado";

    private Color usuario_registrado_color;

    public static final String PROP_USUARIO_REGISTRADO_COLOR = "usuario_registrado_color";

    private Color estado_licencia_color;

    public static final String PROP_ESTADO_LICENCIA_COLOR = "estado_licencia_color";

    /**
     * Get the value of estado_licencia_color
     *
     * @return the value of estado_licencia_color
     */
    public Color getEstado_licencia_color() {
        return estado_licencia_color;
    }

    /**
     * Set the value of estado_licencia_color
     *
     * @param estado_licencia_color new value of estado_licencia_color
     */
    public void setEstado_licencia_color(Color estado_licencia_color) {
        Color oldEstado_licencia_color = this.estado_licencia_color;
        this.estado_licencia_color = estado_licencia_color;
        firePropertyChange(PROP_ESTADO_LICENCIA_COLOR, oldEstado_licencia_color, estado_licencia_color);
    }

    /**
     * Get the value of usuario_registrado_color
     *
     * @return the value of usuario_registrado_color
     */
    public Color getUsuario_registrado_color() {
        return usuario_registrado_color;
    }

    /**
     * Set the value of usuario_registrado_color
     *
     * @param usuario_registrado_color new value of usuario_registrado_color
     */
    public void setUsuario_registrado_color(Color usuario_registrado_color) {
        Color oldUsuario_registrado_color = this.usuario_registrado_color;
        this.usuario_registrado_color = usuario_registrado_color;
        firePropertyChange(PROP_USUARIO_REGISTRADO_COLOR, oldUsuario_registrado_color, usuario_registrado_color);
    }

    /**
     * Get the value of boton_licencia_habilitado
     *
     * @return the value of boton_licencia_habilitado
     */
    public boolean isBoton_licencia_habilitado() {
        return boton_licencia_habilitado;
    }

    /**
     * Set the value of boton_licencia_habilitado
     *
     * @param boton_licencia_habilitado new value of boton_licencia_habilitado
     */
    public void setBoton_licencia_habilitado(boolean boton_licencia_habilitado) {
        boolean oldBoton_licencia_habilitado = this.boton_licencia_habilitado;
        this.boton_licencia_habilitado = boton_licencia_habilitado;
        firePropertyChange(PROP_BOTON_LICENCIA_HABILITADO, oldBoton_licencia_habilitado, boton_licencia_habilitado, true);
    }

    /**
     * Get the value of version_software
     *
     * @return the value of version_software
     */
    public String getVersion_software() {
        return version_software;
    }

    /**
     * Set the value of version_software
     *
     * @param version_software new value of version_software
     */
    public void setVersion_software(String version_software) {
        String oldVersion_software = this.version_software;
        this.version_software = version_software;
        firePropertyChange(PROP_VERSION_SOFTWARE, oldVersion_software, version_software, false);
    }

    /**
     * Get the value of usuario_registrado
     *
     * @return the value of usuario_registrado
     */
    public String getUsuario_registrado() {
        return usuario_registrado;
    }

    /**
     * Set the value of usuario_registrado
     *
     * @param usuario_registrado new value of usuario_registrado
     */
    public void setUsuario_registrado(String usuario_registrado) {
        String oldUsuario_registrado = this.usuario_registrado;
        this.usuario_registrado = usuario_registrado;
        firePropertyChange(PROP_USUARIO_REGISTRADO, oldUsuario_registrado, usuario_registrado, false);
    }

    /**
     * Get the value of estado_licencia
     *
     * @return the value of estado_licencia
     */
    public String getEstado_licencia() {
        return estado_licencia;
    }

    /**
     * Set the value of estado_licencia
     *
     * @param estado_licencia new value of estado_licencia
     */
    public void setEstado_licencia(String estado_licencia) {
        String oldEstado_licencia = this.estado_licencia;
        this.estado_licencia = estado_licencia;
        firePropertyChange(PROP_ESTADO_LICENCIA, oldEstado_licencia, estado_licencia, false);
    }

}
