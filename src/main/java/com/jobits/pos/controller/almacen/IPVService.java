/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.almacen;

import com.jobits.pos.domain.models.Cocina;
import com.jobits.pos.domain.models.IpvRegistro;
import com.jobits.pos.domain.models.IpvVentaRegistro;
import com.jobits.pos.domain.models.Venta;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ERIK QUESADA
 */
public interface IPVService {

    public void darEntradaExistencia(IpvRegistro ipv_registro_seleciconado);

    public void darEntradaIPV(IpvVentaRegistro ipv_venta_registro_seleccionado);

    public void ajustarConsumo(IpvRegistro ipv_registro_seleciconado);

    public List<Venta> getVentasInRange(Date fecha);

    public List<IpvRegistro> getIpvRegistroList(Cocina cocina, int codVenta);

    public List<IpvVentaRegistro> getIpvRegistroVentaList(Cocina cocina, int codVenta);

}
