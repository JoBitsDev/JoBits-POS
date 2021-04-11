/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.orden.presenter;

import com.jobits.pos.ui.viewmodel.AbstractViewModel;

/**
 *
 * @author Home
 */
public class ProductoEnCalienteViewModel extends AbstractViewModel {

    private String nombre_producto;

    public static final String PROP_NOMBRE_PRODUCTO = "nombre_producto";

    private String precio_producto;

    public static final String PROP_PRECIO_PRODUCTO = "precio_producto";

    private String cantidad_producto;

    public static final String PROP_CANTIDAD_PRODUCTO = "cantidad_producto";

    /**
     * Get the value of cantidad_producto
     *
     * @return the value of cantidad_producto
     */
    public String getCantidad_producto() {
        return cantidad_producto;
    }

    /**
     * Set the value of cantidad_producto
     *
     * @param cantidad_producto new value of cantidad_producto
     */
    public void setCantidad_producto(String cantidad_producto) {
        String oldCantidad_producto = this.cantidad_producto;
        this.cantidad_producto = cantidad_producto;
        firePropertyChange(PROP_CANTIDAD_PRODUCTO, oldCantidad_producto, cantidad_producto);
    }

    /**
     * Get the value of precio_producto
     *
     * @return the value of precio_producto
     */
    public String getPrecio_producto() {
        return precio_producto;
    }

    /**
     * Set the value of precio_producto
     *
     * @param precio_producto new value of precio_producto
     */
    public void setPrecio_producto(String precio_producto) {
        String oldPrecio_producto = this.precio_producto;
        this.precio_producto = precio_producto;
        firePropertyChange(PROP_PRECIO_PRODUCTO, oldPrecio_producto, precio_producto);
    }

    /**
     * Get the value of nombre_producto
     *
     * @return the value of nombre_producto
     */
    public String getNombre_producto() {
        return nombre_producto;
    }

    /**
     * Set the value of nombre_producto
     *
     * @param nombre_producto new value of nombre_producto
     */
    public void setNombre_producto(String nombre_producto) {
        String oldNombre_producto = this.nombre_producto;
        this.nombre_producto = nombre_producto;
        firePropertyChange(PROP_NOMBRE_PRODUCTO, oldNombre_producto, nombre_producto);
    }

}
