/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.venta;

import com.jobits.pos.domain.models.Area;
import com.jobits.pos.domain.models.Orden;
import com.jobits.pos.domain.models.Personal;
import com.jobits.pos.domain.models.Venta;
import com.jobits.pos.ui.venta.orden.presenter.VentaOrdenListViewPresenter;
import com.jobits.pos.ui.venta.presenter.ResumenVentaAreaTablaModel;
import com.jobits.pos.ui.venta.presenter.ResumenVentaPtoElabTablaModel;
import com.jobits.pos.ui.venta.presenter.ResumenVentaUsuarioTablaModel;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ERIK QUESADA
 */
public interface VentaDetailService {

    public Venta cambiarTurno(Venta venta);

    public boolean canReabrirVenta(int codVenta);

    public Orden createNewOrden(int codVenta);

    public void fetchNewDataFromServer(int codVenta);

    public Venta getInstance(int codVenta);

    public List<Orden> getOrdenesActivas(int codVenta);

    public List<ResumenVentaAreaTablaModel> getResumenPorAreaVenta(int codVenta);

    public List<ResumenVentaPtoElabTablaModel> getResumenPorPtoVenta(int codVenta);

    public List<ResumenVentaUsuarioTablaModel> getResumenPorUsuarioVenta(int codVenta);

    public String getTotalAutorizos(int codVenta);

    public String getTotalGastadoInsumos(int codVenta);

    public String getTotalGastos(int codVenta);

    public String getTotalPagoTrabajadores(int codVenta);

    public float getTotalPropina(int codVenta);

    public String getTotalVendido(int codVenta);

    public String getTotalVendidoNeto(int codVenta);

    public void initIPV(Venta venta_seleccionada);

    public void printAreaResumen(Area a, int codVenta);

    public void printCocinaResumen(String codCocina, int codVenta);

    public void printGastosCasa(int codVenta);

    public void printPagoPorVentaPersonal(Personal user, int codVenta);

    public void printPersonalResumenRow(Personal personal, int codVenta);

    public void printZ(int codVenta);

    public void reabrirVentas(int codVenta);

    public boolean terminarVentas(int codVenta);

    public boolean terminarYExportar(File file, int codVenta);
    
    public boolean canOpenNuevoTurno(int ventasExistentes);

}
