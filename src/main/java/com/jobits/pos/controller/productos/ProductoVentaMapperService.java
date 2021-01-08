/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.productos;

import com.jobits.pos.domain.models.ProductoVentaMapper;
import java.util.List;

/**
 *
 * @author Home
 */
public interface ProductoVentaMapperService {
    
    public String buscarProductoVenta (String idPVBuscar);
    
    public boolean guardarIdProductoVenta (List <ProductoVentaMapper> listaProductoVenta);
    
}
