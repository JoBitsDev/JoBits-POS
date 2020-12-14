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

    public void addNota(String codOrden, ProductovOrden producto_orden_seleccionado);

    public boolean addProduct(String codOrden, ProductoVenta producto_venta_seleccionado, float cantidad);

    public void cerrarOrden(String codOrden);

    /**
     *
     * @param codMesa ej. M-114
     * @param fechaPedido en el formato dd/mm/yy ej. 20/09/18
     * @return
     */
    public Orden createNewInstance(String codMesa, String fechaPedido);

    public void enviarACocina(String codOrden);

    public void setDeLaCasa(String codOrden, boolean es_autorizo);

    public Orden getInstance(String codOrden);

    public List<Seccion> getListaSecciones();

    public List<ProductoVenta> getPDVList(String codOrden);

    public float getValorTotal(String codOrden);

    public void imprimirPreTicket(String codOrden);

    public void removeProduct(String codOrden, ProductovOrden producto_orden_seleccionado, float cantidad);

    public void setPorciento(String codOrden, float porciento_servicio);
}
