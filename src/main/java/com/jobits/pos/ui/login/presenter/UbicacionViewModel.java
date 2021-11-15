/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.login.presenter;

import com.jobits.pos.ui.viewmodel.AbstractViewModel;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class UbicacionViewModel extends AbstractViewModel {

    public static final String PROP_NOMBRE_UBICACION = "nombre_ubicacion";
    private String nombre_ubicacion;

    public static final String PROP_IP = "ip";
    private String ip;

    public static final String PROP_USUARIO = "usuario";
    private String usuario;

    public static final String PROP_PUERTO = "puerto";
    private String puerto;

    public static final String PROP_PASSWORD = "password";
    private String password;

    public static final String PROP_ID_USUARIO = "idUsuario";
    private String idUsuario;

    public static final String PROP_ID_DB = "idBaseDatos";
    private String idBaseDatos;

    public UbicacionViewModel() {
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        String oldpuerto = this.puerto;
        this.puerto = puerto;
        firePropertyChange(PROP_PUERTO, oldpuerto, password, false);
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        String oldIdUsuario = this.idUsuario;
        this.idUsuario = idUsuario;
        firePropertyChange(PROP_ID_USUARIO, oldIdUsuario, idUsuario, false);
    }

    public String getIdBaseDatos() {
        return idBaseDatos;
    }

    public void setIdBaseDatos(String idBaseDatos) {
        String oldidBaseDatos = this.idBaseDatos;
        this.idBaseDatos = idBaseDatos;
        firePropertyChange(PROP_ID_DB, oldidBaseDatos, idUsuario, false);
    }

    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(String password) {
        String oldPassword = this.password;
        this.password = password;
        firePropertyChange(PROP_PASSWORD, oldPassword, password, false);
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
        firePropertyChange(PROP_USUARIO, oldUsuario, usuario, false);
    }

    /**
     * Get the value of url
     *
     * @return the value of url
     */
    public String getIp() {
        return ip;
    }

    /**
     * Set the value of url
     *
     * @param ip new value of url
     */
    public void setIp(String ip) {
        String oldUrl = this.ip;
        this.ip = ip;
        firePropertyChange(PROP_IP, oldUrl, ip, false);
    }

    /**
     * Get the value of nombre_ubicacion
     *
     * @return the value of nombre_ubicacion
     */
    public String getNombre_ubicacion() {
        return nombre_ubicacion;
    }

    /**
     * Set the value of nombre_ubicacion
     *
     * @param nombre_ubicacion new value of nombre_ubicacion
     */
    public void setNombre_ubicacion(String nombre_ubicacion) {
        String oldNombre_ubicacion = this.nombre_ubicacion;
        this.nombre_ubicacion = nombre_ubicacion;
        firePropertyChange(PROP_NOMBRE_UBICACION, oldNombre_ubicacion, nombre_ubicacion, false);
    }

}
