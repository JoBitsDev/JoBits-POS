/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.orden.presenter;

import com.jobits.pos.domain.models.ProductoVenta;
import com.jobits.pos.domain.models.Seccion;
import com.jobits.pos.ui.viewmodel.AbstractListViewModel;
import java.util.List;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class ProductoVentaSelectorViewModel extends AbstractListViewModel<Seccion> {

    private List<ProductoVenta> listaProductos;

    public static final String PROP_LISTAPRODUCTOS = "listaProductos";

    private String productoVentaSeleccionado;

    public static final String PROP_PRODUCTOVENTASELECCIONADO = "productoVentaSeleccionado";

    
    
    /**
     * Get the value of productoVentaSeleccionado
     *
     * @return the value of productoVentaSeleccionado
     */
    public String getProductoVentaSeleccionado() {
        return productoVentaSeleccionado;
    }

    /**
     * Set the value of productoVentaSeleccionado
     *
     * @param productoVentaSeleccionado new value of productoVentaSeleccionado
     */
    public void setProductoVentaSeleccionado(String productoVentaSeleccionado) {
        String oldProductoVentaSeleccionado = this.productoVentaSeleccionado;
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
        this.setListaProductos(getElemento_seleccionado().getProductoVentaList());//TODO: logica de la vista fuera del presenter
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
