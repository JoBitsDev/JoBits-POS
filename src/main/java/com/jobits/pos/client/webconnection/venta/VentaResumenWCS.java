/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.venta;

import com.jobits.pos.client.webconnection.BaseConnection;
import com.jobits.pos.controller.filter.FilterWrapper;
import com.jobits.pos.controller.resumen.ResumenFacadeInterface;
import com.jobits.pos.core.domain.models.AsistenciaPersonal;
import com.jobits.pos.core.domain.models.GastoVenta;
import com.jobits.pos.core.domain.models.ProductovOrden;
import com.jobits.pos.core.domain.models.escandallos.InsumoRegistro;
import com.jobits.pos.core.domain.models.temporal.DayReviewWrapper;
import com.jobits.pos.core.domain.models.temporal.ResumenFacadeRequest;
import com.jobits.pos.core.domain.models.temporal.ResumenVentaWrapper;

import java.time.LocalDate;
import java.util.List;

/**
 * JoBits
 *
 * @author Jorge
 */
public class VentaResumenWCS extends BaseConnection implements ResumenFacadeInterface {

    VentaResumenWCI service;

    public VentaResumenWCS() {
        super();
        service = retrofit.create(VentaResumenWCI.class);
    }

    @Override
    public ResumenVentaWrapper<DayReviewWrapper<ProductovOrden>, ProductovOrden> getAutorizoResumen(LocalDate desde, LocalDate hasta, List<FilterWrapper> filters) {
        return handleCall(service.getAutorizoResumen(desde, hasta, filters));
    }

    @Override
    public void printResumen(ResumenFacadeRequest request) {
        handleCall(service.printResumen(request));
    }

    @Override
    public ResumenVentaWrapper<DayReviewWrapper<InsumoRegistro>, InsumoRegistro> getCostoResumen(LocalDate desde, LocalDate hasta, List<FilterWrapper> filters) {
        return handleCall(service.getCostoResumen(desde, hasta, filters));
    }

    @Override
    public ResumenVentaWrapper<DayReviewWrapper<GastoVenta>, GastoVenta> getGastoResumen(LocalDate desde, LocalDate hasta, List<FilterWrapper> filters) {
        return handleCall(service.getGastoResumen(desde, hasta, filters));
    }

    @Override
    public ResumenVentaWrapper<DayReviewWrapper<AsistenciaPersonal>, AsistenciaPersonal> getSalarioResumen(LocalDate desde, LocalDate hasta, List<FilterWrapper> filters) {
        return handleCall(service.getSalarioResumen(desde, hasta, filters));
    }

    @Override
    public ResumenVentaWrapper<DayReviewWrapper<ProductovOrden>, ProductovOrden> getVentaResumen(LocalDate desde, LocalDate hasta, List<FilterWrapper> filters) {
        return handleCall(service.getVentaResumen(desde, hasta, filters));
    }

}
