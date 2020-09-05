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
import com.jobits.pos.ui.venta.presenter.ResumenVentaAreaTablaModel;
import com.jobits.pos.ui.venta.presenter.ResumenVentaPtoElabTablaModel;
import com.jobits.pos.ui.venta.presenter.ResumenVentaUsuarioTablaModel;
import java.io.File;
import java.util.List;

/**
 *
 * @author ERIK QUESADA
 */
public interface VentaDetailService {

    public void createNewOrden();

    public List<Orden> getOrdenesActivas();

    public void printZ();

    public void printGastosCasa();
    
    public boolean terminarVentas();
    
    public boolean terminarYExportar(File file);
    
    public void reabrirVentas();
    
    public void printCocinaResumen(String codCocina);
    
    public void printAreaResumen(Area a);

    public void printPersonalResumenRow(Personal personal);
    
    public void printPagoPorVentaPersonal(Personal user);

    public List<ResumenVentaAreaTablaModel> getResumenPorAreaVenta();

    public List<ResumenVentaPtoElabTablaModel> getResumenPorPtoVenta();

    public List<ResumenVentaUsuarioTablaModel> getResumenPorUsuarioVenta();
    
    public float getTotalPropina();
    
    public boolean canReabrirVenta();
    
    public String getTotalAutorizos();
    
    public String getTotalGastadoInsumos();
    
    public String getTotalGastos();
    
    public String getTotalPagoTrabajadores();
    
    public String getTotalVendidoNeto();
    
    public String getTotalVendido();
    
    public boolean canCambiarTurno();
    
    public Venta getInstance();
    
    
    
    
    
}
