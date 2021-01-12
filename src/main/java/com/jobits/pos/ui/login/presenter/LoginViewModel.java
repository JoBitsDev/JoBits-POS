/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.login.presenter;

import com.jobits.pos.core.domain.UbicacionConexionModel;
import com.jobits.pos.core.domain.models.Ubicacion;
import com.jobits.pos.ui.viewmodel.AbstractViewModel;
import java.awt.Color;
import java.util.List;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class LoginViewModel extends AbstractViewModel {

    private String nombreUsuario = "";

    public static final String PROP_NOMBREUSUARIO = "nombreUsuario";

    private String contrasena = "";

    public static final String PROP_CONTRASENA = "contrasena";

    private String estadoConexion;

    public static final String PROP_ESTADOCONEXION = "estadoConexion";

    private Color colorLabelConexion;

    public static final String PROP_COLORLABELCONEXION = "colorLabelConexion";

    public LoginViewModel() {
    }

    /**
     * Get the value of colorLabelConexion
     *
     * @return the value of colorLabelConexion
     */
    public Color getColorLabelConexion() {
        return colorLabelConexion;
    }

    /**
     * Set the value of colorLabelConexion
     *
     * @param colorLabelConexion new value of colorLabelConexion
     */
    public void setColorLabelConexion(Color colorLabelConexion) {
        Color oldColorLabelConexion = this.colorLabelConexion;
        this.colorLabelConexion = colorLabelConexion;
        firePropertyChange(PROP_COLORLABELCONEXION, oldColorLabelConexion, colorLabelConexion, false);
    }

    private boolean botonAutenticarHabilitado = false;

    public static final String PROP_BOTON_AUTENTICAR_HABILITADO = "botonAutenticarHabilitado";

    /**
     * Get the value of botonAutenticarHabilitado
     *
     * @return the value of botonAutenticarHabilitado
     */
    public boolean isBotonAutenticarHabilitado() {
        return botonAutenticarHabilitado;
    }

    /**
     * Set the value of botonAutenticarHabilitado
     *
     * @param botonAutenticarHabilitado new value of botonAutenticarHabilitado
     */
    public void setBotonAutenticarHabilitado(boolean botonAutenticarHabilitado) {
        boolean oldBotonAutenticarHabilitado = this.botonAutenticarHabilitado;
        this.botonAutenticarHabilitado = botonAutenticarHabilitado;
        firePropertyChange(PROP_BOTON_AUTENTICAR_HABILITADO, oldBotonAutenticarHabilitado, botonAutenticarHabilitado, true);
    }

    /**
     * Get the value of estadoConexion
     *
     * @return the value of estadoConexion
     */
    public String getEstadoConexion() {
        return estadoConexion;
    }

    /**
     * Set the value of estadoConexion
     *
     * @param estadoConexion new value of estadoConexion
     */
    public void setEstadoConexion(String estadoConexion) {
        String oldEstadoConexion = this.estadoConexion;
        this.estadoConexion = estadoConexion;
        firePropertyChange(PROP_ESTADOCONEXION, oldEstadoConexion, estadoConexion, false);
    }

    private List<UbicacionConexionModel> listaUbicaciones;

    public static final String PROP_LISTAUBICACIONES = "listaUbicaciones";

    private UbicacionConexionModel ubicacionSeleccionada;

    public static final String PROP_UBICACIONSELECCIONADA = "ubicacionSeleccionada";

    /**
     * Get the value of ubicacionSeleccionada
     *
     * @return the value of ubicacionSeleccionada
     */
    public UbicacionConexionModel getUbicacionSeleccionada() {
        return ubicacionSeleccionada;
    }

    /**
     * Set the value of ubicacionSeleccionada
     *
     * @param ubicacionSeleccionada new value of ubicacionSeleccionada
     */
    public void setUbicacionSeleccionada(UbicacionConexionModel ubicacionSeleccionada) {
        UbicacionConexionModel oldUbicacionSeleccionada = this.ubicacionSeleccionada;
        this.ubicacionSeleccionada = ubicacionSeleccionada;
        firePropertyChange(PROP_UBICACIONSELECCIONADA, oldUbicacionSeleccionada, ubicacionSeleccionada);
    }

    /**
     * Get the value of listaUbicaciones
     *
     * @return the value of listaUbicaciones
     */
    public List<UbicacionConexionModel> getListaUbicaciones() {
        return listaUbicaciones;
    }

    /**
     * Set the value of listaUbicaciones
     *
     * @param listaUbicaciones new value of listaUbicaciones
     */
    public void setListaUbicaciones(List<UbicacionConexionModel> listaUbicaciones) {
        List<UbicacionConexionModel> oldListaUbicaciones = this.listaUbicaciones;
        this.listaUbicaciones = listaUbicaciones;
        firePropertyChange(PROP_LISTAUBICACIONES, oldListaUbicaciones, listaUbicaciones);
    }

    /**
     * Get the value of nombreUsuario
     *
     * @return the value of nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Set the value of nombreUsuario
     *
     * @param nombreUsuario new value of nombreUsuario
     */
    public void setNombreUsuario(String nombreUsuario) {
        String oldNombreUsuario = this.nombreUsuario;
        this.nombreUsuario = nombreUsuario;
        firePropertyChange(PROP_NOMBREUSUARIO, oldNombreUsuario, nombreUsuario, false);
    }

    /**
     * Get the value of contrase�a
     *
     * @return the value of contrase�a
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Set the value of contrase�a
     *
     * @param contrase�a new value of contrase�a
     */
    public void setContrasena(String contrasena) {
        String oldContrasena = this.contrasena;
        this.contrasena = contrasena;
        firePropertyChange(PROP_CONTRASENA, oldContrasena, contrasena, false);
    }

}
