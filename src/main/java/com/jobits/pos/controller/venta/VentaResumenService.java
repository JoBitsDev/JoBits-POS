/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.venta;

import com.jobits.pos.domain.models.Cocina;
import com.jobits.pos.domain.models.ProductoInsumo;
import com.jobits.pos.domain.models.ProductovOrden;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Home
 */
public interface VentaResumenService {

    public void createResumen(Date del, Date al, Cocina cocina);

    public ArrayList<ProductovOrden> getListaVentas(boolean roundedVal);

    public ArrayList<ProductoInsumo> getListaGastos(boolean roundedVal);

    public ArrayList<ProductovOrden> getListaGastosDeLaCasa(boolean roundedVal);

    public ArrayList<Cocina> getListaCocinas(boolean roundedVal);

    public float getTotalRecaudado();

    public float getGanancias();

    public float getDineroInvertido();

    public float getGastosDeLaCasa();

}
