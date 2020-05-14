/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.productos.presenter;

import com.jobits.pos.domain.models.Cocina;
import com.jobits.pos.domain.models.Insumo;
import com.jobits.pos.domain.models.ProductoInsumo;
import com.jobits.pos.domain.models.Seccion;
import com.jobits.pos.ui.viewmodel.AbstractViewModel;
import java.util.List;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class ProductoVentaDetailViewModel extends AbstractViewModel {

    //
    //Basico
    //
    private String nombre_producto;

    public static final String PROP_NOMBRE_PRODUCTO = "nombre_producto";

    private String precio_venta;

    public static final String PROP_PRECIO_VENTA = "precio_venta";

    private String codigo_producto;

    public static final String PROP_CODIGO_PRODUCTO = "codigo_producto";

    private List<Seccion> lista_categorias;

    public static final String PROP_LISTA_CATEGORIAS = "lista_categorias";

    private Seccion categoria_seleccionada;

    public static final String PROP_CATEGORIA_SELECCIONADA = "categoria_seleccionada";

    private List<Cocina> lista_elaborado;

    public static final String PROP_LISTA_ELABORADO = "lista_elaborado";

    private Cocina elaborado_seleccionado;

    public static final String PROP_ELABORADO_SELECCIONADO = "elaborado_seleccionado";

    private String precio_costo;

    public static final String PROP_PRECIO_COSTO = "precio_costo";

    //
    // Inventario
    //
    private boolean checkbox_inventariar_producto;

    public static final String PROP_CHECKBOX_INVENTARIAR_PRODUCTO = "checkbox_inventariar_producto";

    private boolean checkbox_producto_elaborado;

    public static final String PROP_CHECKBOX_PRODUCTO_ELABORADO = "checkbox_producto_elaborado";

    private List<Insumo> lista_insumos_disponibles;

    public static final String PROP_LISTA_INSUMOS_DISPONIBLES = "lista_insumos_disponibles";

    private List<ProductoInsumo> lista_insumos_contenidos;

    public static final String PROP_LISTA_INSUMOS_CONTENIDOS = "lista_insumos_contenidos";

    //
    // Otros
    //
    private String comision_por_venta;

    public static final String PROP_COMISION_POR_VENTA = "comision_por_venta";

    /**
     * Get the value of lista_insumos_contenidos
     *
     * @return the value of lista_insumos_contenidos
     */
    public List<ProductoInsumo> getLista_insumos_contenidos() {
        return lista_insumos_contenidos;
    }

    /**
     * Set the value of lista_insumos_contenidos
     *
     * @param lista_insumos_contenidos new value of lista_insumos_contenidos
     */
    public void setLista_insumos_contenidos(List<ProductoInsumo> lista_insumos_contenidos) {
        List<ProductoInsumo> oldLista_insumos_contenidos = this.lista_insumos_contenidos;
        this.lista_insumos_contenidos = lista_insumos_contenidos;
        firePropertyChange(PROP_LISTA_INSUMOS_CONTENIDOS, oldLista_insumos_contenidos, lista_insumos_contenidos, false);
    }

    /**
     * Get the value of lista_insumos_disponibles
     *
     * @return the value of lista_insumos_disponibles
     */
    public List<Insumo> getLista_insumos_disponibles() {
        return lista_insumos_disponibles;
    }

    /**
     * Set the value of lista_insumos_disponibles
     *
     * @param lista_insumos_disponibles new value of lista_insumos_disponibles
     */
    public void setLista_insumos_disponibles(List<Insumo> lista_insumos_disponibles) {
        List<Insumo> oldLista_insumos_disponibles = this.lista_insumos_disponibles;
        this.lista_insumos_disponibles = lista_insumos_disponibles;
        firePropertyChange(PROP_LISTA_INSUMOS_DISPONIBLES, oldLista_insumos_disponibles, lista_insumos_disponibles, false);
    }

    /**
     * Get the value of comision_por_venta
     *
     * @return the value of comision_por_venta
     */
    public String getComision_por_venta() {
        return comision_por_venta;
    }

    /**
     * Set the value of comision_por_venta
     *
     * @param comision_por_venta new value of comision_por_venta
     */
    public void setComision_por_venta(String comision_por_venta) {
        String oldComision_por_venta = this.comision_por_venta;
        this.comision_por_venta = comision_por_venta;
        firePropertyChange(PROP_COMISION_POR_VENTA, oldComision_por_venta, comision_por_venta, false);
    }

    /**
     * Get the value of checkbox_producto_elaborado
     *
     * @return the value of checkbox_producto_elaborado
     */
    public boolean isCheckbox_producto_elaborado() {
        return checkbox_producto_elaborado;
    }

    /**
     * Set the value of checkbox_producto_elaborado
     *
     * @param checkbox_producto_elaborado new value of
     * checkbox_producto_elaborado
     */
    public void setCheckbox_producto_elaborado(boolean checkbox_producto_elaborado) {
        boolean oldCheckbox_producto_elaborado = this.checkbox_producto_elaborado;
        this.checkbox_producto_elaborado = checkbox_producto_elaborado;
        firePropertyChange(PROP_CHECKBOX_PRODUCTO_ELABORADO, oldCheckbox_producto_elaborado, checkbox_producto_elaborado, true);
    }

    /**
     * Get the value of precio_costo
     *
     * @return the value of precio_costo
     */
    public String getPrecio_costo() {
        return precio_costo;
    }

    /**
     * Set the value of precio_costo
     *
     * @param precio_costo new value of precio_costo
     */
    public void setPrecio_costo(String precio_costo) {
        String oldPrecio_costo = this.precio_costo;
        this.precio_costo = precio_costo;
        firePropertyChange(PROP_PRECIO_COSTO, oldPrecio_costo, precio_costo, false);
    }

    /**
     * Get the value of checkbox_inventariar_producto
     *
     * @return the value of checkbox_inventariar_producto
     */
    public boolean isCheckbox_inventariar_producto() {
        return checkbox_inventariar_producto;
    }

    /**
     * Set the value of checkbox_inventariar_producto
     *
     * @param checkbox_inventariar_producto new value of
     * checkbox_inventariar_producto
     */
    public void setCheckbox_inventariar_producto(boolean checkbox_inventariar_producto) {
        boolean oldCheckbox_inventariar_producto = this.checkbox_inventariar_producto;
        this.checkbox_inventariar_producto = checkbox_inventariar_producto;
        firePropertyChange(PROP_CHECKBOX_INVENTARIAR_PRODUCTO, oldCheckbox_inventariar_producto, checkbox_inventariar_producto, true);
    }

    /**
     * Get the value of elaborado_seleccionado
     *
     * @return the value of elaborado_seleccionado
     */
    public Cocina getElaborado_seleccionado() {
        return elaborado_seleccionado;
    }

    /**
     * Set the value of elaborado_seleccionado
     *
     * @param elaborado_seleccionado new value of elaborado_seleccionado
     */
    public void setElaborado_seleccionado(Cocina elaborado_seleccionado) {
        Cocina oldElaborado_seleccionado = this.elaborado_seleccionado;
        this.elaborado_seleccionado = elaborado_seleccionado;
        firePropertyChange(PROP_ELABORADO_SELECCIONADO, oldElaborado_seleccionado, elaborado_seleccionado, false);
    }

    /**
     * Get the value of lista_elaborado
     *
     * @return the value of lista_elaborado
     */
    public List<Cocina> getLista_elaborado() {
        return lista_elaborado;
    }

    /**
     * Set the value of lista_elaborado
     *
     * @param lista_elaborado new value of lista_elaborado
     */
    public void setLista_elaborado(List<Cocina> lista_elaborado) {
        List<Cocina> oldLista_elaborado = this.lista_elaborado;
        this.lista_elaborado = lista_elaborado;
        firePropertyChange(PROP_LISTA_ELABORADO, oldLista_elaborado, lista_elaborado, false);
    }

    /**
     * Get the value of categoria_seleccionada
     *
     * @return the value of categoria_seleccionada
     */
    public Seccion getCategoria_seleccionada() {
        return categoria_seleccionada;
    }

    /**
     * Set the value of categoria_seleccionada
     *
     * @param categoria_seleccionada new value of categoria_seleccionada
     */
    public void setCategoria_seleccionada(Seccion categoria_seleccionada) {
        Seccion oldCategoria_seleccionada = this.categoria_seleccionada;
        this.categoria_seleccionada = categoria_seleccionada;
        firePropertyChange(PROP_CATEGORIA_SELECCIONADA, oldCategoria_seleccionada, categoria_seleccionada, false);
    }

    /**
     * Get the value of lista_categorias
     *
     * @return the value of lista_categorias
     */
    public List<Seccion> getLista_categorias() {
        return lista_categorias;
    }

    /**
     * Set the value of lista_categorias
     *
     * @param lista_categorias new value of lista_categorias
     */
    public void setLista_categorias(List<Seccion> lista_categorias) {
        List<Seccion> oldLista_categorias = this.lista_categorias;
        this.lista_categorias = lista_categorias;
        firePropertyChange(PROP_LISTA_CATEGORIAS, oldLista_categorias, lista_categorias, false);
    }

    /**
     * Get the value of codigo_producto
     *
     * @return the value of codigo_producto
     */
    public String getCodigo_producto() {
        return codigo_producto;
    }

    /**
     * Set the value of codigo_producto
     *
     * @param codigo_producto new value of codigo_producto
     */
    public void setCodigo_producto(String codigo_producto) {
        String oldCodigo_producto = this.codigo_producto;
        this.codigo_producto = codigo_producto;
        firePropertyChange(PROP_CODIGO_PRODUCTO, oldCodigo_producto, codigo_producto, false);
    }

    /**
     * Get the value of precio_venta
     *
     * @return the value of precio_venta
     */
    public String getPrecio_venta() {
        return precio_venta;
    }

    /**
     * Set the value of precio_venta
     *
     * @param precio_venta new value of precio_venta
     */
    public void setPrecio_venta(String precio_venta) {
        String oldPrecio_venta = this.precio_venta;
        this.precio_venta = precio_venta;
        firePropertyChange(PROP_PRECIO_VENTA, oldPrecio_venta, precio_venta, false);
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
        firePropertyChange(PROP_NOMBRE_PRODUCTO, oldNombre_producto, nombre_producto, false);
    }

}
