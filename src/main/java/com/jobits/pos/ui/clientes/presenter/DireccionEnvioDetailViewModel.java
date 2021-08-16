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
public class DireccionEnvioDetailViewModel extends AbstractViewModel {

    private String de_alias;

    public static final String PROP_DE_ALIAS = "de_alias";

    private String de_nombre;

    public static final String PROP_DE_NOMBRE = "de_nombre";

    private String de_apellidos;

    public static final String PROP_DE_APELLIDOS = "de_apellidos";

    private String de_telefono;

    public static final String PROP_DE_TELEFONO = "de_telefono";

    private String de_empresa;

    public static final String PROP_DE_EMPRESA = "de_empresa";

    private String de_direccion;

    public static final String PROP_DE_DIRECCION = "de_direccion";

    private String de_direccion_adicional;

    public static final String PROP_DE_DIRECCION_ADICIONAL = "de_direccion_adicional";

    private String de_ciudad;

    public static final String PROP_DE_CIUDAD = "de_ciudad";

    private String de_provincia;

    public static final String PROP_DE_PROVINCIA = "de_provincia";

    /**
     * Get the value of de_direccion_adicional
     *
     * @return the value of de_direccion_adicional
     */
    public String getDe_direccion_adicional() {
        return de_direccion_adicional;
    }

    /**
     * Set the value of de_direccion_adicional
     *
     * @param de_direccion_adicional new value of de_direccion_adicional
     */
    public void setDe_direccion_adicional(String de_direccion_adicional) {
        String oldDe_direccion_adicional = this.de_direccion_adicional;
        this.de_direccion_adicional = de_direccion_adicional;
        firePropertyChange(PROP_DE_DIRECCION_ADICIONAL, oldDe_direccion_adicional, de_direccion_adicional);
    }

    /**
     * Get the value of de_provincia
     *
     * @return the value of de_provincia
     */
    public String getDe_provincia() {
        return de_provincia;
    }

    /**
     * Set the value of de_provincia
     *
     * @param de_provincia new value of de_provincia
     */
    public void setDe_provincia(String de_provincia) {
        String oldDe_provincia = this.de_provincia;
        this.de_provincia = de_provincia;
        firePropertyChange(PROP_DE_PROVINCIA, oldDe_provincia, de_provincia);
    }

    /**
     * Get the value of de_ciudad
     *
     * @return the value of de_ciudad
     */
    public String getDe_ciudad() {
        return de_ciudad;
    }

    /**
     * Set the value of de_ciudad
     *
     * @param de_ciudad new value of de_ciudad
     */
    public void setDe_ciudad(String de_ciudad) {
        String oldDe_ciudad = this.de_ciudad;
        this.de_ciudad = de_ciudad;
        firePropertyChange(PROP_DE_CIUDAD, oldDe_ciudad, de_ciudad);
    }

    /**
     * Get the value of de_direccion
     *
     * @return the value of de_direccion
     */
    public String getDe_direccion() {
        return de_direccion;
    }

    /**
     * Set the value of de_direccion
     *
     * @param de_direccion new value of de_direccion
     */
    public void setDe_direccion(String de_direccion) {
        String oldDe_direccion = this.de_direccion;
        this.de_direccion = de_direccion;
        firePropertyChange(PROP_DE_DIRECCION, oldDe_direccion, de_direccion);
    }

    /**
     * Get the value of de_empresa
     *
     * @return the value of de_empresa
     */
    public String getDe_empresa() {
        return de_empresa;
    }

    /**
     * Set the value of de_empresa
     *
     * @param de_empresa new value of de_empresa
     */
    public void setDe_empresa(String de_empresa) {
        String oldDe_empresa = this.de_empresa;
        this.de_empresa = de_empresa;
        firePropertyChange(PROP_DE_EMPRESA, oldDe_empresa, de_empresa);
    }

    /**
     * Get the value of de_telefono
     *
     * @return the value of de_telefono
     */
    public String getDe_telefono() {
        return de_telefono;
    }

    /**
     * Set the value of de_telefono
     *
     * @param de_telefono new value of de_telefono
     */
    public void setDe_telefono(String de_telefono) {
        String oldDe_telefono = this.de_telefono;
        this.de_telefono = de_telefono;
        firePropertyChange(PROP_DE_TELEFONO, oldDe_telefono, de_telefono);
    }

    /**
     * Get the value of de_apellidos
     *
     * @return the value of de_apellidos
     */
    public String getDe_apellidos() {
        return de_apellidos;
    }

    /**
     * Set the value of de_apellidos
     *
     * @param de_apellidos new value of de_apellidos
     */
    public void setDe_apellidos(String de_apellidos) {
        String oldDe_apellidos = this.de_apellidos;
        this.de_apellidos = de_apellidos;
        firePropertyChange(PROP_DE_APELLIDOS, oldDe_apellidos, de_apellidos);
    }

    /**
     * Get the value of de_nombre
     *
     * @return the value of de_nombre
     */
    public String getDe_nombre() {
        return de_nombre;
    }

    /**
     * Set the value of de_nombre
     *
     * @param de_nombre new value of de_nombre
     */
    public void setDe_nombre(String de_nombre) {
        String oldDe_nombre = this.de_nombre;
        this.de_nombre = de_nombre;
        firePropertyChange(PROP_DE_NOMBRE, oldDe_nombre, de_nombre);
    }

    /**
     * Get the value of de_alias
     *
     * @return the value of de_alias
     */
    public String getDe_alias() {
        return de_alias;
    }

    /**
     * Set the value of de_alias
     *
     * @param de_alias new value of de_alias
     */
    public void setDe_alias(String de_alias) {
        String oldDe_alias = this.de_alias;
        this.de_alias = de_alias;
        firePropertyChange(PROP_DE_ALIAS, oldDe_alias, de_alias);
    }

}
