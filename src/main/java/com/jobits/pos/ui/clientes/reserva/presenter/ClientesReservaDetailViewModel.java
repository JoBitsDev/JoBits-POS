/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.clientes.reserva.presenter;

import com.jobits.pos.ui.clientes.presenter.*;
import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.reserva.core.domain.Reserva;
import com.jobits.pos.ui.viewmodel.AbstractViewModel;
import java.util.Date;

/**
 *
 * JoBits
 *
 * @author Home
 *
 */
public class ClientesReservaDetailViewModel extends AbstractViewModel {

    private String nombre;

    public static final String PROP_NOMBRE = "nombre";

    private String apellidos;

    public static final String PROP_APELLIDOS = "apellidos";

    private String telefono;

    public static final String PROP_TELEFONO = "telefono";

    private String direccion;

    public static final String PROP_DIRECCION = "direccion";

    private String municipio;

    public static final String PROP_MUNICIPIO = "municipio";

    private String ciudad;

    public static final String PROP_CIUDAD = "ciudad";

    private String pais;

    public static final String PROP_PAIS = "pais";

    private ArrayListModel<Reserva> lista_reservas = new ArrayListModel<>();

    public static final String PROP_LISTA_RESERVAS = "lista_reservas";

    private Reserva reserva_seleccionada;

    public static final String PROP_RESERVA_SELECCIONADA = "reserva_seleccionada";

    /**
     * Get the value of reserva_seleccionada
     *
     * @return the value of reserva_seleccionada
     */
    public Reserva getReserva_seleccionada() {
        return reserva_seleccionada;
    }

    /**
     * Set the value of reserva_seleccionada
     *
     * @param reserva_seleccionada new value of reserva_seleccionada
     */
    public void setReserva_seleccionada(Reserva reserva_seleccionada) {
        Reserva oldReserva_seleccionada = this.reserva_seleccionada;
        this.reserva_seleccionada = reserva_seleccionada;
        firePropertyChange(PROP_RESERVA_SELECCIONADA, oldReserva_seleccionada, reserva_seleccionada);
    }

    /**
     * Get the value of lista_reservas
     *
     * @return the value of lista_reservas
     */
    public ArrayListModel<Reserva> getLista_reservas() {
        return lista_reservas;
    }

    /**
     * Set the value of lista_reservas
     *
     * @param lista_reservas new value of lista_reservas
     */
    public void setLista_reservas(ArrayListModel<Reserva> lista_reservas) {
        ArrayListModel<Reserva> oldLista_reservas = this.lista_reservas;
        this.lista_reservas = lista_reservas;
        firePropertyChange(PROP_LISTA_RESERVAS, oldLista_reservas, lista_reservas);
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
