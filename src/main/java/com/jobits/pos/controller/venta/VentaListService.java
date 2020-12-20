/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.venta;

import com.jobits.pos.domain.models.Venta;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ERIK QUESADA
 */
public interface VentaListService {

    public void destroy(Venta dia_seleccionado);

    public VentaDetailController createDetailResumenView(Date resumen_desde, Date resumen_hasta);

    public void destroy(Venta find, boolean b);

    public List<List<Venta>> findVentas(int month, int year);

    public List<Venta> findVentasInRange(Calendar start, Calendar end);

    public List<Double> getTotalVentas(List<Venta> ventas);

    public List<Date> getFechaVentas(List<Venta> ventas);

    public List<Float> getTotalGastos(List<Venta> ventas);

    public List<Integer> getTotalOrden(List<Venta> ventas);

    public boolean isYVisible();

}
