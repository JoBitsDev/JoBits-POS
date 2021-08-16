/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.clientes.presenter;

import com.jobits.pos.cliente.core.domain.DireccionEnvioDomain;
import com.jobits.pos.ui.viewmodel.AbstractListViewModel;

/**
 *
 * @author Home
 */
public class DireccionEnvioListViewModel extends AbstractListViewModel<DireccionEnvioDomain> {

    private String nombre_cliente;

    public static final String PROP_NOMBRE_CLIENTE = "nombre_cliente";

    /**
     * Get the value of nombre_cliente
     *
     * @return the value of nombre_cliente
     */
    public String getNombre_cliente() {
        return nombre_cliente;
    }

    /**
     * Set the value of nombre_cliente
     *
     * @param nombre_cliente new value of nombre_cliente
     */
    public void setNombre_cliente(String nombre_cliente) {
        String oldNombre_cliente = this.nombre_cliente;
        this.nombre_cliente = nombre_cliente;
        firePropertyChange(PROP_NOMBRE_CLIENTE, oldNombre_cliente, nombre_cliente);
    }

}
