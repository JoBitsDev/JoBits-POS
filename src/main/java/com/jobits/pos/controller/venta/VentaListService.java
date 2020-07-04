/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.venta;

import com.jobits.pos.domain.models.Venta;
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
    
    public List<Venta> findVentas(int month, int year);
    
    public boolean isYVisible();
    
}
