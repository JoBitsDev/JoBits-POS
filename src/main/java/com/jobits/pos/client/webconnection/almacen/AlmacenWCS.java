/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.almacen;

import com.jobits.pos.client.webconnection.BaseConnection;
import com.jobits.pos.core.domain.TransaccionSimple;
import com.jobits.pos.inventario.core.almacen.domain.Almacen;
import com.jobits.pos.inventario.core.almacen.domain.InsumoAlmacen;
import com.jobits.pos.inventario.core.almacen.domain.Operacion;
import com.jobits.pos.inventario.core.almacen.domain.Transaccion;
import com.jobits.pos.inventario.core.almacen.domain.TransaccionEntrada;
import com.jobits.pos.inventario.core.almacen.domain.TransaccionMerma;
import com.jobits.pos.inventario.core.almacen.domain.TransaccionSalida;
import com.jobits.pos.inventario.core.almacen.domain.TransaccionTransformacion;
import com.jobits.pos.inventario.core.almacen.domain.TransaccionTraspaso;
import com.jobits.pos.inventario.core.almacen.usecase.AlmacenManageService;
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
    public void agregarInsumoAlmacen(String codInsumo, String codAlmacen) {
        handleCall(service.agregarInsumoAlmacen(codInsumo, codAlmacen));
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
    public boolean bulkImport(List<InsumoAlmacen> importList) {
        return handleCall(service.bulkImport(importList));
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

    @Override
    public Operacion crearOperacion(Operacion.Tipo tipoOp, List<TransaccionSimple> transacciones, String recibo, Date fechaFactura, String codAlmacen, Integer codVenta) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Operacion> getOperacionesPendientes(String codAlmacen) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ejecutarOperacion(String codAlmacen, Operacion operacionToUpdate) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ejecutarOperacion(String codAlmacen, int idOperacion) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeInsumoFromStorage(InsumoAlmacen insumoAlmacen) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

}
