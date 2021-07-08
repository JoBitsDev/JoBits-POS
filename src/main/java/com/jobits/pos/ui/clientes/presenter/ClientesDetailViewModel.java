/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.clientes.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.cliente.core.domain.DireccionEnvioDomain;
import com.jobits.pos.cliente.repo.entity.DireccionEnvio;
import com.jobits.pos.core.domain.models.Orden;
import com.jobits.pos.ui.viewmodel.AbstractViewModel;
import java.util.Date;

/**
 *
 * JoBits
 *
 * @author Home
 *
 */
public class ClientesDetailViewModel extends AbstractViewModel {

    //
    //CLIENTE
    //
    private String nombre;

    public static final String PROP_NOMBRE = "nombre";

    private String apellidos;

    public static final String PROP_APELLIDOS = "apellidos";

    private String alias;

    public static final String PROP_ALIAS = "alias";

    private String telefono;

    public static final String PROP_TELEFONO = "telefono";

    private Date cumpleanos;

    public static final String PROP_CUMPLEANOS = "cumpleanos";

    private DireccionEnvioDomain direccion_envio_seleccionada;

    public static final String PROP_DIRECCION_ENVIO_SELECCIONADA = "direccion_envio_seleccionada";

    private ArrayListModel<DireccionEnvioDomain> lista_direcciones_envio = new ArrayListModel<>();

    public static final String PROP_LISTA_DIRECCIONES_ENVIO = "lista_direcciones_envio";

    //
    //DIRECCION ENVIO
    //
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

    /**
     * Get the value of lista_direcciones_envio
     *
     * @return the value of lista_direcciones_envio
     */
    public ArrayListModel<DireccionEnvioDomain> getLista_direcciones_envio() {
        return lista_direcciones_envio;
    }

    /**
     * Set the value of lista_direcciones_envio
     *
     * @param lista_direcciones_envio new value of lista_direcciones_envio
     */
    public void setLista_direcciones_envio(ArrayListModel<DireccionEnvioDomain> lista_direcciones_envio) {
        ArrayListModel<DireccionEnvioDomain> oldLista_direcciones_envio = this.lista_direcciones_envio;
        this.lista_direcciones_envio = lista_direcciones_envio;
        firePropertyChange(PROP_LISTA_DIRECCIONES_ENVIO, oldLista_direcciones_envio, lista_direcciones_envio);
    }

    /**
     * Get the value of direccion_envio_seleccionada
     *
     * @return the value of direccion_envio_seleccionada
     */
    public DireccionEnvioDomain getDireccion_envio_seleccionada() {
        return direccion_envio_seleccionada;
    }

    /**
     * Set the value of direccion_envio_seleccionada
     *
     * @param direccion_envio_seleccionada new value of
     * direccion_envio_seleccionada
     */
    public void setDireccion_envio_seleccionada(DireccionEnvioDomain direccion_envio_seleccionada) {
        DireccionEnvioDomain oldDireccion_envio_seleccionada = this.direccion_envio_seleccionada;
        this.direccion_envio_seleccionada = direccion_envio_seleccionada;
        firePropertyChange(PROP_DIRECCION_ENVIO_SELECCIONADA, oldDireccion_envio_seleccionada, direccion_envio_seleccionada);
    }

    /**
     * Get the value of cumpleanos
     *
     * @return the value of cumpleanos
     */
    public Date getCumpleanos() {
        return cumpleanos;
    }

    /**
     * Set the value of cumpleanos
     *
     * @param cumpleanos new value of cumpleanos
     */
    public void setCumpleanos(Date cumpleanos) {
        Date oldCumpleanos = this.cumpleanos;
        this.cumpleanos = cumpleanos;
        firePropertyChange(PROP_CUMPLEANOS, oldCumpleanos, cumpleanos);
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
     * Get the value of alias
     *
     * @return the value of alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Set the value of alias
     *
     * @param alias new value of alias
     */
    public void setAlias(String alias) {
        String oldAlias = this.alias;
        this.alias = alias;
        firePropertyChange(PROP_ALIAS, oldAlias, alias);
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
