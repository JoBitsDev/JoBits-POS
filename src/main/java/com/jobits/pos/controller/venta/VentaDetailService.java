/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.venta;

import com.jobits.pos.domain.models.Area;
import com.jobits.pos.domain.models.Cocina;
import com.jobits.pos.domain.models.IpvRegistro;
import com.jobits.pos.domain.models.Orden;
import com.jobits.pos.domain.models.Personal;
import com.jobits.pos.domain.models.ProductoVenta;
import com.jobits.pos.domain.models.Venta;
import com.jobits.pos.domain.venta.ResumenVentaAreaTablaModel;
import com.jobits.pos.domain.venta.ResumenVentaPtoElabTablaModel;
import com.jobits.pos.domain.venta.ResumenVentaUsuarioTablaModel;
import java.awt.Container;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Home
 */
public interface VentaDetailService {

    public List<Orden> findReservas(int codVenta);

    Orden abrirReserva(Orden orden, int codVenta);

    @Deprecated
    void cambiarTurno(int codVenta);

    Venta cambiarTurno(Venta fecha);

    boolean canOpenNuevoTurno(Date fecha);

    boolean canReabrirVenta(int codVenta);

    void cerrarOrdenRapido(String codOrden, int codVenta);

    void constructView(Container parent);

    Venta createNewInstance();

    Orden createNewOrden(int codVenta);

    void fetchNewDataFromServer(int codVenta);

    List<Area> getAreaList();

    float getAutorizosTotalDelProducto(ProductoVenta productoVenta, int codVenta);

    List<Cocina> getCocinaList();

    Float getGastoTotalDeInsumo(IpvRegistro i, int codVenta);

    Venta getInstance(int codVenta);

    Venta getInstance();

    List<Orden> getOrdenesActivas(int codVenta);

    Float getPagoTrabajador(Personal personal, int codVenta);

    List<Personal> getPersonalList();

    Float getPropinaTrabajador(Personal personal, int codVenta);

    List<ResumenVentaAreaTablaModel> getResumenPorAreaVenta(int codVenta);

    List<ResumenVentaPtoElabTablaModel> getResumenPorPtoVenta(int codVenta);

    List<ResumenVentaUsuarioTablaModel> getResumenPorUsuarioVenta(int codVenta);

    String getTotalAutorizos(int codVenta);

    String getTotalGastadoInsumos(int codVenta);

    String getTotalGastos(int codVenta);

    String getTotalPagoTrabajadores(int codVenta);

    float getTotalPropina(int codVenta);

    String getTotalVendido(int codVenta);

    String getTotalVendidoNeto(int codVenta);

    float getVentaTotalDelProducto(ProductoVenta productoVenta, int codVenta);

    void importarVenta(File file);

    List<Venta> inicializarVentas(Date fecha, boolean nuevaVenta);

    void initIPV(Venta v);

    void mostrarVenta(int turnoVenta);

    void printAreaResumen(Area a, int codVenta);

    void printGastosCasa(int codVenta);

    void printCocinaResumen(String codCocina, int codVenta, boolean printValores);

    void printPagoPorVentaPersonal(Personal user, int codVenta, boolean printValores);

    void printPersonalResumenRow(Personal p, int codVenta, boolean printValores);

    void printZ(int codVenta);

    void reabrirVentas(int codVenta);

    void setPropina(float value, int codVenta);

    void terminarVentas(int codVenta);

    void terminarYExportar(File file, int codVenta);

}
