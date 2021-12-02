/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.almacen;

import com.jobits.pos.client.webconnection.BaseConnection;
import com.jobits.pos.controller.almacen.AlmacenManageService;
import com.jobits.pos.core.domain.TransaccionSimple;
import com.jobits.pos.core.domain.models.Almacen;
import com.jobits.pos.core.domain.models.InsumoAlmacen;
import com.jobits.pos.core.domain.models.Transaccion;
import com.jobits.pos.core.domain.models.TransaccionEntrada;
import com.jobits.pos.core.domain.models.TransaccionMerma;
import com.jobits.pos.core.domain.models.TransaccionSalida;
import com.jobits.pos.core.domain.models.TransaccionTransformacion;
import com.jobits.pos.core.domain.models.TransaccionTraspaso;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class AlmacenWCS extends BaseConnection implements AlmacenManageService {

    AlmacenWCI service = retrofit.create(AlmacenWCI.class);

    public AlmacenWCS() {
        super();
    }

    @Override
    public void resetAlmacen(String codAlmacen) {
        handleCall(service.resetAlmacen(codAlmacen));
    }

    @Override
    public void crearOperacionEntrada(ArrayList<TransaccionSimple> transacciones, String recibo, Date fechaFactura, String codAlmacen) {
        handleCall(service.crearOperacionEntrada(transacciones, recibo, fechaFactura, codAlmacen));
    }

    @Override
    public void crearOperacionRebaja(ArrayList<TransaccionSimple> transacciones, String recibo, Date fechaFactura, String codAlmacen) {
        handleCall(service.crearOperacionRebaja(transacciones, recibo, fechaFactura, codAlmacen));
    }

    @Override
    public void crearOperacionSalida(ArrayList<TransaccionSimple> transacciones, String recibo, Date fechaFactura, Integer codVenta, String codAlmacen) {
        handleCall(service.crearOperacionSalida(transacciones, recibo, fechaFactura, codVenta, codAlmacen));
    }

    @Override
    public void crearOperacionTraspaso(ArrayList<TransaccionSimple> transacciones, String recibo, Date fechaFactura, String codAlmacen) {
        handleCall(service.crearOperacionTraspaso(transacciones, recibo, fechaFactura, codAlmacen));
    }

    @Override
    public void darEntradaAInsumo(TransaccionEntrada x, String codAlmacen) {
        handleCall(service.darEntradaAInsumo(x, codAlmacen));
    }

    @Override
    public void darMermaInsumo(TransaccionMerma x, String codAlmacen) {
        handleCall(service.darMermaInsumo(x, codAlmacen));
    }

    @Override
    public void darSalidaAInsumo(TransaccionSalida x, int idVenta, String codAlmacen) {
        handleCall(service.darSalidaAInsumo(x, idVenta, codAlmacen));
    }

    @Override
    public void darTransformacionAInsumo(Transaccion t, String codAlmacenDestino, String codAlmacenOrigen) {
        handleCall(service.darTransformacionAInsumo(t, codAlmacenDestino, codAlmacenOrigen));
    }

    @Override
    public void darTraspasoInsumo(TransaccionTraspaso x, String codAlmacen) {
        handleCall(service.darTraspasoInsumo(x, codAlmacen));
    }

    @Override
    public void agregarInsumoAlmacen(String codInsumo, String codAlmacen) {
        handleCall(service.agregarInsumoAlmacen(codInsumo, codAlmacen));
    }

    @Override
    public void crearTransformacion(InsumoAlmacen selected, float cantidad, List<TransaccionTransformacion> items, String codAlmacen) {
        handleCall(service.crearTransformacion(selected, cantidad, items, codAlmacen));
    }

    @Override
    public void setCentroElaboracion(boolean selected, String codAlmacen) {
        handleCall(service.setCentroElaboracion(selected, codAlmacen));
    }

    @Override
    public void imprimirReporteParaCompras(String codAlmacen, int selection) {
        handleCall(service.imprimirReporteParaCompras(codAlmacen, selection));
    }

    @Override
    public void imprimirResumenAlmacen(String codAlmacen) {
        handleCall(service.imprimirResumenAlmacen(codAlmacen));
    }

    @Override
    public void removeInsumoFromStorage(InsumoAlmacen insumoAlmacen, String codAlmacen) {
        handleCall(service.removeInsumoFromStorage(insumoAlmacen, codAlmacen));
    }

    @Override
    public void updateValorTotalAlmacen(String codAlmacen) {
        handleCall(service.updateValorTotalAlmacen(codAlmacen));
    }

    @Override
    public void darEntradaIPV(String codAlmacen, String codInsumo, float cantidad) {
        handleCall(service.darEntradaIPV(codAlmacen, codInsumo, cantidad));
    }

    @Override
    public boolean bulkImport(List<InsumoAlmacen> importList) {
        return handleCall(service.bulkImport(importList));
    }

    @Override
    public List<InsumoAlmacen> getInsumoAlmacenList(String codAlmacen) {
        return handleCall(service.getInsumoAlmacenList(codAlmacen));
    }

    @Override
    public InsumoAlmacen findInsumo(String codInsumo, String codAlmacen) {
        return handleCall(service.findInsumo(codInsumo, codAlmacen));
    }

    @Override
    public Almacen create(Almacen t) throws RuntimeException {
        return handleCall(service.create(t));
    }

    @Override
    public Almacen edit(Almacen t) throws RuntimeException {
        return handleCall(service.edit(t));
    }

    @Override
    public Almacen destroy(Almacen t) throws RuntimeException {
        return handleCall(service.destroy(t));
    }

    @Override
    public Almacen destroyById(Object o) throws RuntimeException {
        return handleCall(service.destroyById(o));
    }

    @Override
    public Almacen findBy(Object o) throws RuntimeException {
        return handleCall(service.findBy(o));
    }

    @Override
    public List<Almacen> findAll() throws RuntimeException {
        return handleCall(service.findAll());
    }

}
