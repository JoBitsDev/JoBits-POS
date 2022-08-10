/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.venta;

import com.jobits.pos.client.webconnection.BaseConnection;
import com.jobits.pos.controller.venta.VentaDetailService;
import com.jobits.pos.controller.venta.resumen.VentaResumenUseCase;
import com.jobits.pos.core.domain.VentaResourcesWrapper;
import com.jobits.pos.core.domain.VentaResumenWrapper;
import com.jobits.pos.core.domain.models.Orden;
import com.jobits.pos.core.domain.models.ProductovOrden;
import com.jobits.pos.core.domain.models.Venta;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class VentaWCS extends BaseConnection implements VentaDetailService, VentaResumenUseCase {

    private VentaWCI service;

    public VentaWCS() {
        super();
        service = retrofit.create(VentaWCI.class);
    }

    @Override
    public List<Venta> getVentasDeFecha(LocalDate date) {
        return handleCall(service.getVentasDeFecha(date));
    }

    @Override
    public List<Venta> getVentasDeFecha(int idVenta) {
        return handleCall(service.getVentasDeFecha(idVenta));
    }

    @Override
    public Venta cambiarTurno(int idTurnoATerminar) {
        return handleCall(service.cambiarTurno(idTurnoATerminar));
    }

    @Override
    public boolean canOpenNuevoTurno(LocalDate fecha) {
        return handleCall(service.canOpenNuevoTurno(fecha));
    }

    @Override
    public boolean canOpenNuevoTurno(int idVenta) {
        return handleCall(service.canOpenNuevoTurno(idVenta));
    }

    @Override
    public boolean canReabrirVenta(int codVenta) {
        return handleCall(service.canReabrirVenta(codVenta));
    }

    @Override
    public Orden createNewOrden(int codVenta, String mesa) {
        return handleCall(service.createNewOrden(codVenta, mesa));
    }

    @Override
    public Map<String, Float> getAutorizosTotalDelProducto(int codVenta) {
        return handleCall(service.getAutorizosTotalDelProducto(codVenta));
    }

    @Override
    public List<Orden> getOrdenesActivas(int codVenta) {
        return handleCall(service.getOrdenesActivas(codVenta));
    }

    @Override
    public Float getPagoTrabajador(String codPersonal, int codVenta, int dividirEntre) {
        return handleCall(service.getPagoTrabajador(codVenta, dividirEntre, codPersonal));
    }

    @Override
    public Float getPropinaTrabajador(String codPersonal, int codVenta) {
        return handleCall(service.getPropinaTrabajador(codVenta, codPersonal));
    }

    @Override
    public Venta importarVenta(String file) {
        return handleCall(service.importarVenta(file));//TODO; esto no pincha
    }

    @Override
    public Integer inicializarVentas(LocalDate fecha) {
        return handleCall(service.inicializarVentas(fecha == null ? LocalDate.now() : fecha));
    }

    @Override
    public void printGastosCasa(int codVenta) {
        handleCall(service.printGastosCasa(codVenta));
    }

    @Override
    public void printPagoPorVentaPersonal(String user, int codVenta, boolean printValores) {
        handleCall(service.printPagoPorVentaPersonal(codVenta, user, printValores));
    }

    @Override
    public void printComisionPorcentualResumen(String mesa, int idVenta) {
        handleCall(service.printComisionPorcentualResumen(idVenta, mesa));
    }

    @Override
    public void printZ(int codVenta) {
        handleCall(service.printZ(codVenta));
    }

    @Override
    public boolean reabrirVentas(int codVenta) {
        return handleCall(service.reabrirVentas(codVenta));
    }

    @Override
    public boolean terminarVentas(int codVenta) {
        return handleCall(service.terminarVentas(codVenta));
    }

    @Override
    public String terminarYExportar(int codVenta) {
        return handleCall(service.terminarYExportar(codVenta));
    }

    @Override
    public void printMesaResumen(String mesa, int idVenta) {
        handleCall(service.printMesaResumen(idVenta, mesa));
    }

    @Override
    public void printAreaResumen(String a, int codVenta) {
        handleCall(service.printAreaResumen(codVenta, a));
    }

    @Override
    public void printPersonalResumenRow(String p, int codVenta, boolean printValores) {
        handleCall(service.printPersonalResumenRow(codVenta, p, printValores));
    }

    @Override
    public void printCocinaResumen(String codCocina, int codVenta, boolean printValores) {
        handleCall(service.printCocinaResumen(codVenta, codCocina, printValores));
    }

    @Override
    public Venta create(Venta t) throws RuntimeException {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.  
    }

    @Override
    public Venta edit(Venta t) throws RuntimeException {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Venta destroy(Venta t) throws RuntimeException {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Venta destroyById(Object o) throws RuntimeException {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Venta findBy(Object o) throws RuntimeException {
        return handleCall(service.findBy((int) o));
    }

    @Override
    public List<Venta> findAll() throws RuntimeException {
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
    public VentaResumenWrapper getResumenVenta(int codVenta) {
        return handleCall(service.getVentaResumen(codVenta));
    }

    @Override
    public VentaResourcesWrapper getVentaResources(int codVenta) {
        return handleCall(service.getVentaResources(codVenta));
    }

    @Override
    public Map<String, Float> getVentaTotalDelProducto(int codVenta) {
        return handleCall(service.getVentaTotalDelProducto(codVenta));
    }

    @Override
    public Map<String, Float> getGastoTotalDeInsumo(int codVenta) {
        return handleCall(service.getGastoTotalDeInsumo(codVenta));
    }

    @Override
    public List<Integer> getVentasIds(LocalDate date) {
        return handleCall(service.getVentas(date));
    }

    @Override
    public List<Integer> getVentasIds(int idVenta) {
        return handleCall(service.getVentas(idVenta));
    }

}
