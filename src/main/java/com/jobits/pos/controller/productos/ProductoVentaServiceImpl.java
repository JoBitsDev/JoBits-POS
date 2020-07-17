/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.productos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Home
 */
public class ProductoVentaServiceImpl implements ProductoVentaService {

    ProductoVentaRepo pvRepository;

    public ProductoVentaServiceImpl(ProductoVentaRepo pvRepo) {
        this.pvRepository = pvRepo;
    }

    @Override
    public String buscarProductoVenta(String idPVBuscar) {
        List<ProductoVentaMapper> listaPV = pvRepository.cargarProductoVenta();

        for (ProductoVentaMapper productoVentaMapper : listaPV) {
            if (productoVentaMapper.getIdBusqueda().equals(idPVBuscar)) {
                return productoVentaMapper.getIdProductoVenta();
            }
        }
        return null;
    }

    @Override
    public boolean guardarIdProductoVenta(List<ProductoVentaMapper> listaProductoVenta) {
        return pvRepository.guardarProductoVenta(listaProductoVenta);
    }
}