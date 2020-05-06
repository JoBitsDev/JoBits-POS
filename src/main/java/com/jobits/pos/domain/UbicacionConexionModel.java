/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.domain;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class UbicacionConexionModel {

    public static UbicacionConexionModel[] getDefaultUbicaciones() {
        UbicacionConexionModel[] ret = new UbicacionConexionModel[4];
        for (int i = 0; i < ret.length; i++) {
            UbicacionConexionModel ubicacionVacia = new UbicacionConexionModel("<Nueva Ubicacion>", "0.0.0.0", "Nueva", "Nueva", "Nueva", TipoUbicacion.DESACTIVADA);
            ret[i] = ubicacionVacia;
        }
        return ret;
    }

    private String nombre, url, usuario, contrasena, driver;
    private TipoUbicacion tipoUbicacion;

    public UbicacionConexionModel(String nombre, String url, String usuario,
            String contrasena, String driver, TipoUbicacion tipoUbicacion) {
        this.tipoUbicacion = tipoUbicacion;
        this.nombre = nombre;
        this.url = url;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.driver = driver;
    }

    public UbicacionConexionModel() {
    }

    //
    // Getters y Setters
    //
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public TipoUbicacion getTipoUbicacion() {
        return tipoUbicacion;
    }

    public void setTipoUbicacion(TipoUbicacion tipoUbicacion) {
        this.tipoUbicacion = tipoUbicacion;
    }

    @Override
    public String toString() {
        return getNombre() + "<" + getTipoUbicacion() + ">";
    }

    public enum TipoUbicacion {
        DESACTIVADA, MASTER, RESPALDO, SINCRONIZACION;
    }

}
