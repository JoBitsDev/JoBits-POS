/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.almacen;

import com.jobits.pos.client.webconnection.BaseConnection;
import com.jobits.pos.controller.almacen.TransaccionService;
import com.jobits.pos.core.domain.models.InsumoAlmacen;
import com.jobits.pos.core.domain.models.Operacion;
import com.jobits.pos.core.domain.models.Transaccion;
import com.jobits.pos.core.domain.models.TransaccionTransformacion;
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
public class TransaccionWCS extends BaseConnection implements TransaccionService {

    TransaccionWCI service = retrofit.create(TransaccionWCI.class);

    @Override
    public List<Transaccion> findAllByAlmacen(String codAlmacen) {
        return handleCall(service.findAllByAlmacen(codAlmacen));
    }

    @Override
    public List<Transaccion> findMermasByAlmacen(String codAlmacen) {
        return handleCall(service.findMermasByAlmacen(codAlmacen));
    }

    @Override
    public void imprimirTransaccionesSeleccionadas(List<Transaccion> selectedsObjects) {
        handleCall(service.imprimirTransaccionesSeleccionadas(selectedsObjects));
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

    @Override
    public void addTransaccionEntrada(Operacion o, String codInsumo, Date fecha, Date hora, String codAlmacen, float cantidad, float importe) {
        handleCall(service.addTransaccionEntrada(o, codInsumo, fecha, hora, codAlmacen, cantidad, importe));
    }

    @Override
    public void addTransaccionRebaja(Operacion o, String codInsumo, Date fecha, Date hora, String codAlmacen, float cantidad, String causaRebaja, boolean isMerma) {
        handleCall(service.addTransaccionRebaja(o, codInsumo, fecha, hora, codAlmacen, cantidad, causaRebaja, isMerma));
    }

    @Override
    public void addTransaccionSalida(Operacion o, String codInsumo, Date fecha, Date hora, String codAlmacen, String codCocina, float cantidad, int idVenta) {
        handleCall(service.addTransaccionSalida(o, codInsumo, fecha, hora, codAlmacen, codCocina, cantidad, idVenta));
    }

    @Override
    public void addTransaccionTransformacion(InsumoAlmacen selected, Date fecha, Date hora, List<TransaccionTransformacion> items, float cantidad, float merma, String codAlmacenDestino) {
        handleCall(service.addTransaccionTransformacion(selected, fecha, hora, items, cantidad, merma, codAlmacenDestino));
    }

    @Override
    public void addTransaccionTraspaso(Operacion o, String codInsumoe, Date fecha, Date hora, String codAlmacen, String codAlmacenDestinoTraspaso, float cantidad) {
        handleCall(service.addTransaccionTraspaso(o, codInsumoe, fecha, hora, codAlmacen, codAlmacenDestinoTraspaso, cantidad));
    }

}
