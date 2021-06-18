/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.orden.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.core.domain.models.ProductoVenta;
import com.jobits.pos.core.domain.models.temporal.ProductoVentaWrapper;
import com.jobits.pos.ui.viewmodel.AbstractViewModel;

/**
 *
 * @author Home
 */
public class AgregarProductoViewModel extends AbstractViewModel {

    private ArrayListModel<ProductoVenta> lista_productos_disponibles = new ArrayListModel<>();

    public static final String PROP_LISTA_PRODUCTOS_DISPONIBLES = "lista_productos_disponibles";

    private ProductoVenta producto_disponible_seleccionado;

    public static final String PROP_PRODUCTO_DISPONIBLE_SELECCIONADO = "producto_disponible_seleccionado";

    private ArrayListModel<ProductoVentaWrapper> lista_productos_contenidos = new ArrayListModel<>();

    public static final String PROP_LISTA_PRODUCTOS_CONTENIDOS = "lista_productos_contenidos";

    private ProductoVentaWrapper producto_contenido_seleccionado;

    public static final String PROP_PRODUCTO_CONTENIDO_SELECCIONADO = "producto_contenido_seleccionado";

    private String nombre_producto = "<Nombre Producto>";

    public static final String PROP_NOMBRE_PRODUCTO = "nombre_producto";

    private String nombre_seccion_mostrada = "<Nombre Seccion>";

    public static final String PROP_NOMBRE_SECCION_MOSTRADA = "nombre_seccion_mostrada";

    private boolean navegacion_habilitada = false;

    public static final String PROP_NAVEGACION_HABILITADA = "navegacion_habilitada";

    /**
     * Get the value of navegacion_habilitada
     *
     * @return the value of navegacion_habilitada
     */
    public boolean isNavegacion_habilitada() {
        return navegacion_habilitada;
    }

    /**
     * Set the value of navegacion_habilitada
     *
     * @param navegacion_habilitada new value of navegacion_habilitada
     */
    public void setNavegacion_habilitada(boolean navegacion_habilitada) {
        boolean oldNavegacion_habilitada = this.navegacion_habilitada;
        this.navegacion_habilitada = navegacion_habilitada;
        firePropertyChange(PROP_NAVEGACION_HABILITADA, oldNavegacion_habilitada, navegacion_habilitada);
    }

    /**
     * Get the value of nombre_seccion_mostrada
     *
     * @return the value of nombre_seccion_mostrada
     */
    public String getNombre_seccion_mostrada() {
        return nombre_seccion_mostrada;
    }

    /**
     * Set the value of nombre_seccion_mostrada
     *
     * @param nombre_seccion_mostrada new value of nombre_seccion_mostrada
     */
    public void setNombre_seccion_mostrada(String nombre_seccion_mostrada) {
        String oldNombre_seccion_mostrada = this.nombre_seccion_mostrada;
        this.nombre_seccion_mostrada = nombre_seccion_mostrada;
        firePropertyChange(PROP_NOMBRE_SECCION_MOSTRADA, oldNombre_seccion_mostrada, nombre_seccion_mostrada);
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

    /**
     * Get the value of producto_contenido_seleccionado
     *
     * @return the value of producto_contenido_seleccionado
     */
    public ProductoVentaWrapper getProducto_contenido_seleccionado() {
        return producto_contenido_seleccionado;
    }

    /**
     * Set the value of producto_contenido_seleccionado
     *
     * @param producto_contenido_seleccionado new value of
     * producto_contenido_seleccionado
     */
    public void setProducto_contenido_seleccionado(ProductoVentaWrapper producto_contenido_seleccionado) {
        ProductoVentaWrapper oldProducto_contenido_seleccionado = this.producto_contenido_seleccionado;
        this.producto_contenido_seleccionado = producto_contenido_seleccionado;
        firePropertyChange(PROP_PRODUCTO_CONTENIDO_SELECCIONADO, oldProducto_contenido_seleccionado, producto_contenido_seleccionado);
    }

    /**
     * Get the value of lista_productos_contenidos
     *
     * @return the value of lista_productos_contenidos
     */
    public ArrayListModel<ProductoVentaWrapper> getLista_productos_contenidos() {
        return lista_productos_contenidos;
    }

    /**
     * Set the value of lista_productos_contenidos
     *
     * @param lista_productos_contenidos new value of lista_productos_contenidos
     */
    public void setLista_productos_contenidos(ArrayListModel<ProductoVentaWrapper> lista_productos_contenidos) {
        ArrayListModel<ProductoVentaWrapper> oldLista_productos_contenidos = this.lista_productos_contenidos;
//        this.lista_productos_contenidos = lista_productos_contenidos;
        this.lista_productos_contenidos.clear();
        this.lista_productos_contenidos.addAll(lista_productos_contenidos);
        firePropertyChange(PROP_LISTA_PRODUCTOS_CONTENIDOS, oldLista_productos_contenidos, lista_productos_contenidos);
    }

    /**
     * Get the value of producto_disponible_seleccionado
     *
     * @return the value of producto_disponible_seleccionado
     */
    public ProductoVenta getProducto_disponible_seleccionado() {
        return producto_disponible_seleccionado;
    }

    /**
     * Set the value of producto_disponible_seleccionado
     *
     * @param producto_disponible_seleccionado new value of
     * producto_disponible_seleccionado
     */
    public void setProducto_disponible_seleccionado(ProductoVenta producto_disponible_seleccionado) {
        ProductoVenta oldProducto_disponible_seleccionado = this.producto_disponible_seleccionado;
        this.producto_disponible_seleccionado = producto_disponible_seleccionado;
        firePropertyChange(PROP_PRODUCTO_DISPONIBLE_SELECCIONADO, oldProducto_disponible_seleccionado, producto_disponible_seleccionado);
    }

    /**
     * Get the value of lista_productos_disponibles
     *
     * @return the value of lista_productos_disponibles
     */
    public ArrayListModel<ProductoVenta> getLista_productos_disponibles() {
        return lista_productos_disponibles;
    }

    /**
     * Set the value of lista_productos_disponibles
     *
     * @param lista_productos_disponibles new value of
     * lista_productos_disponibles
     */
    public void setLista_productos_disponibles(ArrayListModel<ProductoVenta> lista_productos_disponibles) {
        ArrayListModel<ProductoVenta> oldLista_productos_disponibles = this.lista_productos_disponibles;
        this.lista_productos_disponibles.clear();
        this.lista_productos_disponibles.addAll(lista_productos_disponibles);
        firePropertyChange(PROP_LISTA_PRODUCTOS_DISPONIBLES, oldLista_productos_disponibles, lista_productos_disponibles);
    }

}
