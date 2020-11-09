/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.clientes.presenter;

import com.jobits.pos.ui.productos.presenter.*;
import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.domain.models.Cocina;
import com.jobits.pos.domain.models.Insumo;
import com.jobits.pos.domain.models.Orden;
import com.jobits.pos.domain.models.ProductoInsumo;
import com.jobits.pos.domain.models.Seccion;
import com.jobits.pos.ui.viewmodel.AbstractViewModel;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * JoBits
 *
 * @author Home
 *
 */
public class ClientesDetailViewModel extends AbstractViewModel {

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

    private String direccion;

    public static final String PROP_DIRECCION = "direccion";

    private String municipio;

    public static final String PROP_MUNICIPIO = "municipio";

    private String ciudad;

    public static final String PROP_CIUDAD = "ciudad";

    private String pais;

    public static final String PROP_PAIS = "pais";

    private ArrayListModel<Orden> lista_ordenes = new ArrayListModel<>();

    public static final String PROP_LISTA_ORDENES = "lista_ordenes";

    /**
     * Get the value of lista_ordenes
     *
     * @return the value of lista_ordenes
     */
    public ArrayListModel<Orden> getLista_ordenes() {
        return lista_ordenes;
    }

    /**
     * Set the value of lista_ordenes
     *
     * @param lista_ordenes new value of lista_ordenes
     */
    public void setLista_ordenes(ArrayListModel<Orden> lista_ordenes) {
        ArrayListModel<Orden> oldLista_ordenes = this.lista_ordenes;
        this.lista_ordenes = lista_ordenes;
        firePropertyChange(PROP_LISTA_ORDENES, oldLista_ordenes, lista_ordenes);
    }

    /**
     * Get the value of pais
     *
     * @return the value of pais
     */
    public String getPais() {
        return pais;
    }

    /**
     * Set the value of pais
     *
     * @param pais new value of pais
     */
    public void setPais(String pais) {
        String oldPais = this.pais;
        this.pais = pais;
        firePropertyChange(PROP_PAIS, oldPais, pais);
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
     * Get the value of municipio
     *
     * @return the value of municipio
     */
    public String getMunicipio() {
        return municipio;
    }

    /**
     * Set the value of municipio
     *
     * @param municipio new value of municipio
     */
    public void setMunicipio(String municipio) {
        String oldMunicipio = this.municipio;
        this.municipio = municipio;
        firePropertyChange(PROP_MUNICIPIO, oldMunicipio, municipio);
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
