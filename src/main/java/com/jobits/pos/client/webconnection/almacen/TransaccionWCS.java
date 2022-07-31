/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.almacen;

import com.jobits.pos.client.webconnection.BaseConnection;
import com.jobits.pos.inventario.core.almacen.domain.Almacen;
import com.jobits.pos.inventario.core.almacen.domain.InsumoAlmacen;
import com.jobits.pos.inventario.core.almacen.domain.Operacion;
import com.jobits.pos.inventario.core.almacen.domain.Transaccion;
import com.jobits.pos.inventario.core.almacen.domain.TransaccionTransformacion;
import com.jobits.pos.inventario.core.almacen.usecase.TransaccionListService;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.util.List;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class TransaccionWCS extends BaseConnection implements TransaccionListService {

    TransaccionWCI service = retrofit.create(TransaccionWCI.class);

    @Override
    public void imprimirTransaccionesSeleccionadas(List<Transaccion> selectedsObjects) {
        handleCall(service.imprimirTransaccionesSeleccionadas(selectedsObjects));
    }

    @Override
    public List<Transaccion> findAllByAlmacen(String codAlmacen) {
        return handleCall(service.findAllByAlmacen(codAlmacen));

    }

    @Override
    public List<Transaccion> findMermasByAlmacen(String codAlmacen) {
        return handleCall(service.findMermasByAlmacen(codAlmacen));
    }

    @Override
    public Transaccion create(Transaccion t) throws RuntimeException {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Transaccion edit(Transaccion t) throws RuntimeException {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Transaccion destroy(Transaccion t) throws RuntimeException {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Transaccion destroyById(Object o) throws RuntimeException {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Transaccion findBy(Object o) throws RuntimeException {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Transaccion> findAll() throws RuntimeException {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener pl) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener pl) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

}
