/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.clientes.presenter;

import com.jobits.pos.ui.viewmodel.AbstractViewModel;

/**
 *
 * @author Home
 */
public class DomicilioViewModel extends AbstractViewModel {

    private String nombre;

    public static final String PROP_NOMBRE = "nombre";

    private String apellidos;

    public static final String PROP_APELLIDOS = "apellidos";

    private String telefono;

    public static final String PROP_TELEFONO = "telefono";

    private String empresa;

    public static final String PROP_EMPRESA = "empresa";

    private String direccion;

    public static final String PROP_DIRECCION = "direccion";

    private String direccion_adicional;

    public static final String PROP_DIRECCION_ADICIONAL = "direccion_adicional";

    private String ciudad;

    public static final String PROP_CIUDAD = "ciudad";

    private String provincia;

    public static final String PROP_PROVINCIA = "provincia";

    private String precio_envio;

    public static final String PROP_PRECIO_ENVIO = "precio_envio";

    /**
     * Get the value of precio_envio
     *
     * @return the value of precio_envio
     */
    public String getPrecio_envio() {
        return precio_envio;
    }

    /**
     * Set the value of precio_envio
     *
     * @param precio_envio new value of precio_envio
     */
    public void setPrecio_envio(String precio_envio) {
        String oldPrecio_envio = this.precio_envio;
        this.precio_envio = precio_envio;
        firePropertyChange(PROP_PRECIO_ENVIO, oldPrecio_envio, precio_envio);
    }

    /**
     * Get the value of provincia
     *
     * @return the value of provincia
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Set the value of provincia
     *
     * @param provincia new value of provincia
     */
    public void setProvincia(String provincia) {
        String oldProvincia = this.provincia;
        this.provincia = provincia;
        firePropertyChange(PROP_PROVINCIA, oldProvincia, provincia);
    }

    /**
     * Get the value of ciudad
     *
     * @return the value of ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Set the value of ciudad
     *
     * @param ciudad new value of ciudad
     */
    public void setCiudad(String ciudad) {
        String oldCiudad = this.ciudad;
        this.ciudad = ciudad;
        firePropertyChange(PROP_CIUDAD, oldCiudad, ciudad);
    }

    /**
     * Get the value of direccion_adicional
     *
     * @return the value of direccion_adicional
     */
    public String getDireccion_adicional() {
        return direccion_adicional;
    }

    /**
     * Set the value of direccion_adicional
     *
     * @param direccion_adicional new value of direccion_adicional
     */
    public void setDireccion_adicional(String direccion_adicional) {
        String oldDireccion_adicional = this.direccion_adicional;
        this.direccion_adicional = direccion_adicional;
        firePropertyChange(PROP_DIRECCION_ADICIONAL, oldDireccion_adicional, direccion_adicional);
    }

    /**
     * Get the value of direccion
     *
     * @return the value of direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Set the value of direccion
     *
     * @param direccion new value of direccion
     */
    public void setDireccion(String direccion) {
        String oldDireccion = this.direccion;
        this.direccion = direccion;
        firePropertyChange(PROP_DIRECCION, oldDireccion, direccion);
    }

    /**
     * Get the value of empresa
     *
     * @return the value of empresa
     */
    public String getEmpresa() {
        return empresa;
    }

    /**
     * Set the value of empresa
     *
     * @param empresa new value of empresa
     */
    public void setEmpresa(String empresa) {
        String oldEmpresa = this.empresa;
        this.empresa = empresa;
        firePropertyChange(PROP_EMPRESA, oldEmpresa, empresa);
    }

    /**
     * Get the value of telefono
     *
     * @return the value of telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Set the value of telefono
     *
     * @param telefono new value of telefono
     */
    public void setTelefono(String telefono) {
        String oldTelefono = this.telefono;
        this.telefono = telefono;
        firePropertyChange(PROP_TELEFONO, oldTelefono, telefono);
    }

    /**
     * Get the value of apellidos
     *
     * @return the value of apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Set the value of apellidos
     *
     * @param apellidos new value of apellidos
     */
    public void setApellidos(String apellidos) {
        String oldApellidos = this.apellidos;
        this.apellidos = apellidos;
        firePropertyChange(PROP_APELLIDOS, oldApellidos, apellidos);
    }

    /**
     * Get the value of nombre
     *
     * @return the value of nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Set the value of nombre
     *
     * @param nombre new value of nombre
     */
    public void setNombre(String nombre) {
        String oldNombre = this.nombre;
        this.nombre = nombre;
        firePropertyChange(PROP_NOMBRE, oldNombre, nombre);
    }

}
