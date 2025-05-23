/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.venta;

import com.jobits.pos.client.webconnection.BaseConnection;
import com.jobits.pos.controller.venta.OrdenService;
import com.jobits.pos.core.domain.models.Orden;
import com.jobits.pos.core.domain.models.temporal.ProductoVentaWrapper;

import java.util.List;

/**
 * JoBits
 *
 * @author Jorge
 */
public class OrdenWCS extends BaseConnection implements OrdenService {

    OrdenWCI service;

    public OrdenWCS() {
        super();
        service = retrofit.create(OrdenWCI.class);
    }

    @Override
    public Orden addNota(String codOrden, int producto_orden_seleccionado, String nuevaNota) {
        return handleCall(service.addNota(codOrden, producto_orden_seleccionado, nuevaNota));
    }

    @Override
    public Orden addProduct(String codOrden, String codProducto, Float cantidad, int productoOrdenAgregar) {
        return handleCall(service.addProduct(codOrden, codProducto, cantidad, productoOrdenAgregar));
    }

    @Override
    public Orden addProductvOrden(int codProductvOrden, Float cantidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Orden addProductoCompuesto(String codOrden, String producto_agregar, Float cantidad, List<ProductoVentaWrapper> lista_agregos) {
        return handleCall(service.addProductoCompuesto(codOrden, producto_agregar, cantidad, lista_agregos));
    }

    @Override
    public Orden addProductInHot(String codOrden, String nombre, String precio, String cantidad) {
        return handleCall(service.addProductInHot(codOrden, nombre, precio, cantidad));
    }

    @Override
    public Orden removeProduct(String codOrden, int idProductoOrden, float cantidad) {
        return handleCall(service.removeProduct(codOrden, idProductoOrden, cantidad));
    }

    @Override
    public Orden cerrarOrden(String codOrden, boolean imprimirTicket, float pagadoCash, float pagadoTarjeta) {
        return handleCall(service.cerrarOrden(codOrden, imprimirTicket, pagadoCash, pagadoTarjeta));
    }

    @Override
    public Orden setDeLaCasa(String codOrden, boolean es_autorizo) {
        return handleCall(service.setDeLaCasa(codOrden, es_autorizo));
    }

    @Override
    public Orden setPorciento(String codOrden, float porciento_servicio) {
        return handleCall(service.setPorciento(codOrden, porciento_servicio));
    }

    @Override
    public Orden moverA(String codOrden, String codMesa) {
        return handleCall(service.moverA(codOrden, codMesa));
    }

    @Override
    public void imprimirPreTicket(String codOrden) {
        handleCall(service.imprimirPreTicket(codOrden));
    }

    @Override
    public Orden enviarACocina(String codOrden) {
        return handleCall(service.enviarACocina(codOrden, "127.0.0.1"));
    }

    @Override
    public Orden setCliente(String codOrden, Integer clienteId) {
        return handleCall(service.setCliente(codOrden, clienteId));
    }

    @Override
    public Orden create(Orden t) throws RuntimeException {
        return handleCall(service.create(t));
    }

    @Override
    public Orden edit(Orden t) throws RuntimeException {
        return handleCall(service.edit(t));
    }

    @Override
    public Orden destroy(Orden t) throws RuntimeException {
        return handleCall(service.destroy(t));
    }

    @Override
    public Orden destroyById(Object o) throws RuntimeException {
        return handleCall(service.destroyById(o));
    }

    @Override
    public Orden findBy(Object o) throws RuntimeException {
        return handleCall(service.findBy(o));
    }

    @Override
    public List<Orden> findAll() throws RuntimeException {
        return handleCall(service.findAll());
    }

    @Override
    public Orden markReadyToPickup(String codOrden, int codProductoOrden, float ammount) {
        return handleCall(service.markReadyToPickup(codOrden, codProductoOrden, ammount));
    }

    @Override
    public Orden enviarACocina(String codOrden, String uuid) {
        return handleCall(service.enviarACocina(codOrden, uuid));
    }


    @Override
    public Orden setPagadoPorTarjeta(String string, boolean bln) {
        return handleCall(service.setPagadoPorTarjeta(string, bln));
    }


    @Override
    public Orden cerrarOrden(String string, boolean bln) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }
}
