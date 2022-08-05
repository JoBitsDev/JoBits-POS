/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.venta;

import com.jobits.pos.client.webconnection.CRUDBaseConnection;
import com.jobits.pos.controller.venta.VentaCalendarResumeUseCase;
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
public class VentaCalendarWCS extends CRUDBaseConnection<Venta> implements VentaCalendarResumeUseCase {

    VentaCalendarWCI service;

    public VentaCalendarWCS() {
        super();
        service = retrofit.create(VentaCalendarWCI.class);
    }

    @Override
    public Venta resolveVentaAbierta() {
        return handleCall(service.resolveVentaAbierta());
    }

    @Override
    public List<DayReviewWrapper<Venta>> findVentasByMonth(int month, int year) {
        return handleCall(service.findVentasByMonth(month, year));
    }

    @Override
    public List<DayReviewWrapper<ResumenVentaEstadisticas>> findVentasByMonthView(int month, int year) {
        return handleCall(service.findVentasByMonthView(month, year));
    }

    @Override
    public List<Venta> findVentasInRange(LocalDate start, LocalDate end) {
        return handleCall(service.findVentasInRange(start, end));
    }

    @Override
    public List<ResumenVentaEstadisticas> getResumenDeVentasEstadisticas(List<Integer> idVentas) {
        return handleCall(service.getResumenDeVentasEstadisticas(idVentas));
    }

    @Override
    public boolean isYVisible() {
        return handleCall(service.isYVisible());
    }

    @Override
    public Venta deleteVenta(int idVenta) {
        return handleCall(service.destroyById(idVenta));
    }

    @Override
    public Venta destroyById(Object o) throws RuntimeException {
        return deleteVenta((int)o);
        
    }

}
