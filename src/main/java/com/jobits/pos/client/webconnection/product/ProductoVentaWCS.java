/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.product;

import com.jobits.pos.client.webconnection.BaseConnection;
import com.jobits.pos.controller.productos.ProductoVentaService;
import com.jobits.pos.core.domain.models.ProductoVenta;
import java.util.List;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class ProductoVentaWCS extends BaseConnection implements ProductoVentaService {

    ProductoVentaWCI service = retrofit.create(ProductoVentaWCI.class);

    public ProductoVentaWCS() {
        super();
    }

    @Override
    public List<ProductoVenta> bulkImport(List<ProductoVenta> importList) {
        return handleCall(service.bulkImport(importList));
    }

    @Override
    public ProductoVenta create(ProductoVenta t) throws RuntimeException {
        return handleCall(service.create(t));
    }

    @Override
    public ProductoVenta edit(ProductoVenta t) throws RuntimeException {
        return handleCall(service.edit(t));
    }

    @Override
    public ProductoVenta destroy(ProductoVenta t) throws RuntimeException {
        return destroyById(t.getCodigoProducto());
    }

    @Override
    public ProductoVenta destroyById(Object o) throws RuntimeException {
        return handleCall(service.destroyById(o));
    }

    @Override
    public ProductoVenta findBy(Object o) throws RuntimeException {
        return handleCall(service.findBy(o));
    }

    @Override
    public List<ProductoVenta> findAll() throws RuntimeException {
        return handleCall(service.findAll());
        
    }

}
