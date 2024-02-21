/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.gasto;

import com.jobits.pos.client.webconnection.CRUDBaseConnection;
import com.jobits.pos.controller.gasto.GastoOperacionService;
import com.jobits.pos.core.domain.models.GastoVenta;
import com.jobits.pos.core.domain.models.temporal.DefaultGasto;
import com.jobits.pos.recursos.R;

import java.util.List;

/**
 * JoBits
 *
 * @author Jorge
 */
public class GastoWCS extends CRUDBaseConnection<GastoVenta> implements GastoOperacionService {

    GastoWCI service = retrofit.create(GastoWCI.class);


    public GastoWCS() {
        super();
    }

    @Override
    public GastoVenta createGasto(R.TipoGasto cat, String nombre, float monto, String descripcion, int venta) {
        return handleCall(service.createGasto(cat, nombre, monto, descripcion == null ? "Sin descripcion" : descripcion, venta));
    }


    @Override
    public GastoVenta removeGasto(String codGasto, int idVenta) {
        return handleCall(service.removeGasto(codGasto, idVenta));
    }

    @Override
    public float getValorTotalGastos(int idDiaVenta) {
        return handleCall(service.getValorTotalGastos(idDiaVenta));
    }

    @Override
    public List<String> getNombresByTipo(String toString) {
        return handleCall(service.getNombresByTipo(toString));
    }

    @Override
    public List<DefaultGasto> getDefaultGastosList() {
        return handleCall(service.getDefaultGastosList());
    }

    @Override
    public DefaultGasto agregarDefaultGasto(String alias, R.TipoGasto cat, String nombre, float monto, String descripcion) {
        return handleCall(service.agregarDefaultGasto(alias, cat, nombre, monto, descripcion));

    }

    @Override
    public DefaultGasto eliminarDefaultGasto(DefaultGasto gasto) {
        return handleCall(service.eliminarDefaultGasto(gasto));
    }
}
