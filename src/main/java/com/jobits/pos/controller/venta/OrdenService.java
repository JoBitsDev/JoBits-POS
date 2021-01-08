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

    public boolean nuevaNota(ProductovOrden p);

    public void addNota(String codOrden, ProductovOrden producto_orden_seleccionado, String nuevaNota);

    public boolean addProduct(String codOrden, ProductoVenta producto_seleccionado, float cantidad);

    public Orden cerrarOrden(String codOrden, boolean imprimirTicket);

    /**
     *
     * @param codMesa ej. M-114
     * @param fechaPedido en el formato dd/mm/yy ej. 20/09/18
     * @param idVenta el id de la venta a la que pertenece o -1 si es una
     * reserva
     * @return
     */
    public Orden createNewInstance(String codMesa, String fechaPedido, Integer idVenta);

    public void enviarACocina(String codOrden);

    public void setDeLaCasa(String codOrden, boolean es_autorizo);

    public Orden getInstance(String codOrden);

    public List<Seccion> getListaSecciones();

    public List<ProductoVenta> getPDVList(String codOrden);

    public float getValorTotal(String codOrden);

    public void imprimirPreTicket(String codOrden);

    public void removeProduct(String codOrden, ProductovOrden producto_orden_seleccionado, float cantidad);

    public void setPorciento(String codOrden, float porciento_servicio);

    public void setModoAgrego(ProductovOrden producto_orden_seleccionado);

    public void canViewOrdenLog(String codOrden);
}
