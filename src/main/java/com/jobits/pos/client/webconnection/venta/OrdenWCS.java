/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.venta;

import com.jobits.pos.client.webconnection.BaseConnection;
import com.jobits.pos.controller.resumen.VentaResumenService;
import com.jobits.pos.controller.venta.OrdenService;
import com.jobits.pos.controller.venta.VentaDetailService;
import com.jobits.pos.controller.venta.resumen.VentaResumenUseCase;
import com.jobits.pos.core.domain.VentaResourcesWrapper;
import com.jobits.pos.core.domain.VentaResumenWrapper;
import com.jobits.pos.core.domain.models.Area;
import com.jobits.pos.core.domain.models.Cocina;
import com.jobits.pos.core.domain.models.IpvRegistro;
import com.jobits.pos.core.domain.models.Mesa;
import com.jobits.pos.core.domain.models.Orden;
import com.jobits.pos.core.domain.models.Personal;
import com.jobits.pos.core.domain.models.ProductoVenta;
import com.jobits.pos.core.domain.models.ProductovOrden;
import com.jobits.pos.core.domain.models.Venta;
import com.jobits.pos.core.domain.models.temporal.ProductoVentaWrapper;
import com.jobits.pos.utils.utils;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class OrdenWCS extends BaseConnection implements OrdenService {

    OrdenWCI service;

    public OrdenWCS() {
        super();
        service = retrofit.create(OrdenWCI.class);
    }

    @Override
    public Orden addNota(String codOrden, int producto_orden_seleccionado, String nuevaNota) {
        return service.addNota(codOrden, producto_orden_seleccionado, nuevaNota);
    }

    @Override
    public ProductovOrden addProduct(String codOrden, String codProducto, Float cantidad, Optional<Integer> productoOrdenAgregar) {
        return service.addProduct(codOrden, codProducto, cantidad, productoOrdenAgregar);
    }

    @Override
    public Orden addProductoCompuesto(String codOrden, String producto_agregar, Float cantidad, List<ProductoVentaWrapper> lista_agregos) {
        return service.addProductoCompuesto(codOrden, producto_agregar, cantidad, lista_agregos);
    }

    @Override
    public Orden addProductInHot(String codOrden, String nombre, String precio, String cantidad) {
        return service.addProductInHot(codOrden, nombre, precio, cantidad);
    }

    @Override
    public Orden removeProduct(String codOrden, int idProductoOrden, float cantidad) {
        return service.removeProduct(codOrden, idProductoOrden, cantidad);
    }

    @Override
    public Orden cerrarOrden(String codOrden, boolean imprimirTicket) {
        return service.cerrarOrden(codOrden, imprimirTicket);
    }

    @Override
    public Orden setDeLaCasa(String codOrden, boolean es_autorizo) {
        return service.setDeLaCasa(codOrden, es_autorizo);
    }

    @Override
    public Orden setPorciento(String codOrden, float porciento_servicio) {
        return service.setPorciento(codOrden, porciento_servicio);
    }

    @Override
    public Orden moverA(String codOrden, String codMesa) {
        return service.moverA(codOrden, codMesa);
    }

    @Override
    public void imprimirPreTicket(String codOrden) {
        service.imprimirPreTicket(codOrden);
    }

    @Override
    public void impimirListaOrdenes(int codVenta) {
        service.impimirListaOrdenes(codVenta);
    }

    @Override
    public Orden enviarACocina(String codOrden) {
        return service.enviarACocina(codOrden);
    }

    @Override
    public Orden setCliente(String codOrden, Integer clienteId) {
        return service.setCliente(codOrden, clienteId);
    }

    @Override
    public Orden create(Orden t) throws RuntimeException {
        return service.create(t);
    }

    @Override
    public Orden edit(Orden t) throws RuntimeException {
        return service.edit(t);
    }

    @Override
    public Orden destroy(Orden t) throws RuntimeException {
        return service.destroy(t);
    }

    @Override
    public Orden destroyById(Object o) throws RuntimeException {
        return service.destroyById(o);
    }

    @Override
    public Orden findBy(Object o) throws RuntimeException {
        return service.findBy(o);
    }

    @Override
    public List<Orden> findAll() throws RuntimeException {
        return service.findAll();
    }

}
