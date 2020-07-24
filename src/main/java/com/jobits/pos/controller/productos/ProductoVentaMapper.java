/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.productos;

/**
 *
 * @author Home
 */
public class ProductoVentaMapper {
    
    String idProductoVenta;
    String idBusqueda;

    public ProductoVentaMapper(String idProductoVenta, String idBusqueda) {
        this.idProductoVenta = idProductoVenta;
        this.idBusqueda = idBusqueda;
    }

    public String getIdProductoVenta() {
        return idProductoVenta;
    }

    public void setIdProductoVenta(String idProductoVenta) {
        this.idProductoVenta = idProductoVenta;
    }

    public String getIdBusqueda() {
        return idBusqueda;
    }

    public void setIdBusqueda(String idBusqueda) {
        this.idBusqueda = idBusqueda;
    }
    
    
    
    
    
}
