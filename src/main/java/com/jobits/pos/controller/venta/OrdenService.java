/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.venta;

import com.jobits.pos.domain.models.Orden;
import com.jobits.pos.domain.models.ProductoVenta;
import com.jobits.pos.domain.models.ProductovOrden;
import com.jobits.pos.domain.models.Seccion;
import java.util.List;

/**
 *
 * @author Jorge
 */
public interface OrdenService {


    public void addProduct(String noOrden,ProductoVenta producto_venta_seleccionado);

    public void removeProduct(ProductovOrden producto_orden_seleccionado, float cantidad);

    public void setPorciento(float porciento_servicio);

    public void setDeLaCasa(boolean es_autorizo);

    public void enviarACocina();

    public void cerrarOrden();

    public void addNota(ProductovOrden producto_orden_seleccionado);

    public void imprimirPreTicket();

    public List<Seccion> getListaSecciones();

    public float getValorTotal();

    public List<ProductoVenta> getPDVList();

    public Orden getInstance();
    
    
    
    
}
