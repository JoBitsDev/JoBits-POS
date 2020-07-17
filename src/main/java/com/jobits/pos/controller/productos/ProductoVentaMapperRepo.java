/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.productos;

import java.util.List;

/**
 *
 * @author Home
 */
public interface ProductoVentaMapperRepo {

    public List<ProductoVentaMapper> cargarProductoVenta();

    public boolean guardarProductoVenta(List<ProductoVentaMapper> listaPVMapper);

}
