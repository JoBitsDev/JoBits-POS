/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.filter;

import com.jobits.pos.client.webconnection.BaseConnection;
import com.jobits.pos.client.webconnection.venta.*;
import com.jobits.pos.client.webconnection.CRUDBaseConnection;
import com.jobits.pos.client.webconnection.area.AreaVentaWCI;
import com.jobits.pos.client.webconnection.carta.SeccionWCI;
import com.jobits.pos.client.webconnection.insumo.InsumoWCI;
import com.jobits.pos.client.webconnection.product.ProductoVentaWCI;
import com.jobits.pos.client.webconnection.puntoelaboracion.PuntoElaboracionWCI;
import com.jobits.pos.controller.filter.FilterService;
import com.jobits.pos.controller.venta.VentaCalendarResumeUseCase;
import com.jobits.pos.core.domain.models.Area;
import com.jobits.pos.core.domain.models.Cocina;
import com.jobits.pos.core.domain.models.Insumo;
import com.jobits.pos.core.domain.models.ProductoVenta;
import com.jobits.pos.core.domain.models.Seccion;
import com.jobits.pos.core.domain.models.Venta;
import com.jobits.pos.core.domain.models.temporal.DayReviewWrapper;
import com.jobits.pos.core.domain.models.temporal.ResumenVentaEstadisticas;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class FilterWCS extends BaseConnection implements FilterService {

    public FilterWCS() {
        super();
    }

    @Override
    public List<Insumo> getListaInsumos() {
        return handleCall(retrofit.create(InsumoWCI.class).findAll());
    }

    @Override
    public List<Cocina> getListaCocinas() {
        return handleCall(retrofit.create(PuntoElaboracionWCI.class).findAll());
    }

    @Override
    public List<Seccion> getListaSecciones() {
        return handleCall(retrofit.create(SeccionWCI.class).findAll());
    }

    @Override
    public List<Area> getListaAreas() {
        return handleCall(retrofit.create(AreaVentaWCI.class).findAll());
    }

    @Override
    public List<ProductoVenta> getListaProductos() {
        return handleCall(retrofit.create(ProductoVentaWCI.class).findAll());
    }

}
