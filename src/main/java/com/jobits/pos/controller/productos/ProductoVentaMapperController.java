/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.productos;

import com.jobits.pos.adapters.repo.impl.ProductoVentaMapperRepoImpl;
import com.jobits.pos.domain.models.ProductoVentaMapper;
import java.util.List;

/**
 *
 * @author Home
 */
public class ProductoVentaMapperController implements ProductoVentaMapperService {

    ProductoVentaMapperRepoImpl pvRepository;

    public ProductoVentaMapperController(ProductoVentaMapperRepoImpl pvRepo) {
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