/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.login.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import org.jobits.db.core.domain.TipoConexion;
import com.jobits.pos.ui.viewmodel.AbstractViewModel;
import java.util.List;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class UbicacionViewModel extends AbstractViewModel {

    private String nombre_ubicacion;

    public static final String PROP_NOMBRE_UBICACION = "nombre_ubicacion";

    private String url;

    public static final String PROP_URL = "url";

    private String usuario;

    public static final String PROP_USUARIO = "usuario";

    private String password;

    public static final String PROP_PASSWORD = "password";

    private String driver;

    public static final String PROP_DRIVER = "driver";

    private TipoConexion tipo_servidor_seleccionado;

    public static final String PROP_TIPO_SERVIDOR_SELECCIONADO = "tipo_servidor_seleccionado";

    private ArrayListModel<TipoConexion> lista_tipo_servidor;

    public static final String PROP_LISTA_TIPO_SERVIDOR = "lista_tipo_servidor";

    /**
     * Get the value of lista_tipo_servidor
     *
     * @return the value of lista_tipo_servidor
     */
    public List<TipoConexion> getLista_tipo_servidor() {
        return lista_tipo_servidor;
    }

    /**
     * Set the value of lista_tipo_servidor
     *
     * @param lista_tipo_servidor new value of lista_tipo_servidor
     */
    public void setLista_tipo_servidor(ArrayListModel<TipoConexion> lista_tipo_servidor) {
        ArrayListModel<TipoConexion> oldLista_tipo_servidor = this.lista_tipo_servidor;
        this.lista_tipo_servidor = lista_tipo_servidor;
        firePropertyChange(PROP_LISTA_TIPO_SERVIDOR, oldLista_tipo_servidor, lista_tipo_servidor, false);
    }

    /**
     * Get the value of tipo_servidor_seleccionado
     *
     * @return the value of tipo_servidor_seleccionado
     */
    public TipoConexion getTipo_servidor_seleccionado() {
        return tipo_servidor_seleccionado;
    }

    /**
     * Set the value of tipo_servidor_seleccionado
     *
     * @param tipo_servidor_seleccionado new value of tipo_servidor_seleccionado
     */
    public void setTipo_servidor_seleccionado(TipoConexion tipo_servidor_seleccionado) {
        TipoConexion oldLista_tipo_servidor = this.tipo_servidor_seleccionado;
        this.tipo_servidor_seleccionado = tipo_servidor_seleccionado;
        firePropertyChange(PROP_TIPO_SERVIDOR_SELECCIONADO, oldLista_tipo_servidor, tipo_servidor_seleccionado, false);
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
     * Get the value of driver
     *
     * @return the value of driver
     */
    public String getDriver() {
        return driver;
    }

    /**
     * Set the value of driver
     *
     * @param driver new value of driver
     */
    public void setDriver(String driver) {
        String oldLista_drivers = this.driver;
        this.driver = driver;
        firePropertyChange(PROP_DRIVER, oldLista_drivers, driver, false);
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
    public String getUrl() {
        return url;
    }

    /**
     * Set the value of url
     *
     * @param url new value of url
     */
    public void setUrl(String url) {
        String oldUrl = this.url;
        this.url = url;
        firePropertyChange(PROP_URL, oldUrl, url, false);
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
