/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.orden.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.core.domain.models.ProductoVenta;
import com.jobits.pos.core.domain.models.Seccion;
import com.jobits.pos.ui.viewmodel.AbstractListViewModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class ProductoVentaSelectorViewModel extends AbstractListViewModel<Seccion> {

    private List<ProductoVenta> listaProductos = new ArrayListModel<>();

    public static final String PROP_LISTAPRODUCTOS = "listaProductos";

    private ProductoVenta productoVentaSeleccionado;

    public static final String PROP_PRODUCTOVENTASELECCIONADO = "productoVentaSeleccionado";

    //AutoCompletado
    private String pv_filtrado;

    public static final String PROP_PV_FILTRADO = "pv_filtrado";

    private boolean campo_busqueda_enabled;

    public static final String PROP_CAMPO_BUSQUEDA_ENABLED = "campo_busqueda_enabled";

    /**
     * Get the value of campo_busqueda_enabled
     *
     * @return the value of campo_busqueda_enabled
     */
    public boolean isCampo_busqueda_enabled() {
        return campo_busqueda_enabled;
    }

    /**
     * Set the value of campo_busqueda_enabled
     *
     * @param campo_busqueda_enabled new value of campo_busqueda_enabled
     */
    public void setCampo_busqueda_enabled(boolean campo_busqueda_enabled) {
        boolean oldCampo_busqueda_enabled = this.campo_busqueda_enabled;
        this.campo_busqueda_enabled = campo_busqueda_enabled;
        firePropertyChange(PROP_CAMPO_BUSQUEDA_ENABLED, oldCampo_busqueda_enabled, campo_busqueda_enabled);
    }

    /**
     * Get the value of pv_filtrado
     *
     * @return the value of pv_filtrado
     */
    public String getPv_filtrado() {
        return pv_filtrado;
    }

    /**
     * Set the value of pv_filtrado
     *
     * @param pv_filtrado new value of pv_filtrado
     */
    public void setPv_filtrado(String pv_filtrado) {
        String oldPv_filtrado = this.pv_filtrado;
        this.pv_filtrado = pv_filtrado;
        firePropertyChange(PROP_PV_FILTRADO, oldPv_filtrado, pv_filtrado);
    }

    /**
     * Get the value of productoVentaSeleccionado
     *
     * @return the value of productoVentaSeleccionado
     */
    public ProductoVenta getProductoVentaSeleccionado() {
        return productoVentaSeleccionado;
    }

    /**
     * Set the value of productoVentaSeleccionado
     *
     * @param productoVentaSeleccionado new value of productoVentaSeleccionado
     */
    public void setProductoVentaSeleccionado(ProductoVenta productoVentaSeleccionado) {
        ProductoVenta oldProductoVentaSeleccionado = this.productoVentaSeleccionado;
        this.productoVentaSeleccionado = productoVentaSeleccionado;
        firePropertyChange(PROP_PRODUCTOVENTASELECCIONADO, oldProductoVentaSeleccionado, productoVentaSeleccionado);
    }

    /**
     * Get the value of listaProductos
     *
     * @return the value of listaProductos
     */
    public List<ProductoVenta> getListaProductos() {
        return listaProductos;
    }

    @Override
    public void setElemento_seleccionado(Seccion elemento_seleccionado) {
        super.setElemento_seleccionado(elemento_seleccionado); //To change body of generated methods, choose Tools | Templates.
        if (elemento_seleccionado != null) {
            this.setListaProductos(getElemento_seleccionado().getProductoVentaList());//TODO: logica de la vista fuera del presenter
        } else {
            this.setListaProductos(new ArrayList<>());
        }
    }

    /**
     * Set the value of listaProductos
     *
     * @param listaProductos new value of listaProductos
     */
    public void setListaProductos(List<ProductoVenta> listaProductos) {
        List<ProductoVenta> oldListaProductos = this.listaProductos;
        this.listaProductos = listaProductos;
        firePropertyChange(PROP_LISTAPRODUCTOS, oldListaProductos, listaProductos);
    }

}
