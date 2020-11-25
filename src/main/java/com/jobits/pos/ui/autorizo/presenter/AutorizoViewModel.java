/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.autorizo.presenter;

import com.jobits.pos.ui.viewmodel.AbstractViewModel;

/**
 *
 * @author Home
 */
public class AutorizoViewModel extends AbstractViewModel {

    private String usuario;

    public static final String PROP_USUARIO = "usuario";

    private String contrasenna;

    public static final String PROP_CONTRASENNA = "contrasenna";

    private String header;

    public static final String PROP_HEADER = "header";

    /**
     * Get the value of header
     *
     * @return the value of header
     */
    public String getHeader() {
        return header;
    }

    /**
     * Set the value of header
     *
     * @param header new value of header
     */
    public void setHeader(String header) {
        String oldHeader = this.header;
        this.header = header;
        firePropertyChange(PROP_HEADER, oldHeader, header);
    }

    /**
     * Get the value of contrasenna
     *
     * @return the value of contrasenna
     */
    public String getContrasenna() {
        return contrasenna;
    }

    /**
     * Set the value of contrasenna
     *
     * @param contrasenna new value of contrasenna
     */
    public void setContrasenna(String contrasenna) {
        String oldContrasenna = this.contrasenna;
        this.contrasenna = contrasenna;
        firePropertyChange(PROP_CONTRASENNA, oldContrasenna, contrasenna);
    }

    /**
     * Get the value of usuario
     *
     * @return the value of usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Set the value of usuario
     *
     * @param usuario new value of usuario
     */
    public void setUsuario(String usuario) {
        String oldUsuario = this.usuario;
        this.usuario = usuario;
        firePropertyChange(PROP_USUARIO, oldUsuario, usuario);
    }

}
