/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.areaventa.presenter;

import com.jobits.pos.ui.viewmodel.AbstractViewModel;

/**
 *
 * @author Home
 */
public class MesaDetailViewModel extends AbstractViewModel {

    private String nombre;

    public static final String PROP_NOMBRE = "nombre";

    private String codigo;

    public static final String PROP_CODIGO = "codigo";

    private int capacidad;

    public static final String PROP_CAPACIDAD = "capacidad";

    private boolean show_cod_mesa;

    public static final String PROP_SHOW_COD_MESA = "show_cod_mesa";

    /**
     * Get the value of show_cod_mesa
     *
     * @return the value of show_cod_mesa
     */
    public boolean getShow_cod_mesa() {
        return show_cod_mesa;
    }

    /**
     * Set the value of show_cod_mesa
     *
     * @param show_cod_mesa new value of show_cod_mesa
     */
    public void setShow_cod_mesa(boolean show_cod_mesa) {
        boolean oldShow_cod_mesa = this.show_cod_mesa;
        this.show_cod_mesa = show_cod_mesa;
        firePropertyChange(PROP_SHOW_COD_MESA, oldShow_cod_mesa, show_cod_mesa);
    }

    /**
     * Get the value of capacidad
     *
     * @return the value of capacidad
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * Set the value of capacidad
     *
     * @param capacidad new value of capacidad
     */
    public void setCapacidad(int capacidad) {
        int oldCapacidad = this.capacidad;
        this.capacidad = capacidad;
        firePropertyChange(PROP_CAPACIDAD, oldCapacidad, capacidad);
    }

    /**
     * Get the value of codigo
     *
     * @return the value of codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Set the value of codigo
     *
     * @param codigo new value of codigo
     */
    public void setCodigo(String codigo) {
        String oldCodigo = this.codigo;
        this.codigo = codigo;
        firePropertyChange(PROP_CODIGO, oldCodigo, codigo);
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
