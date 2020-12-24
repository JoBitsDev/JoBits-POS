/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.reservas;

import com.jobits.pos.domain.models.Cliente;
import com.jobits.pos.domain.models.Mesa;
import com.jobits.pos.domain.models.Orden;
import com.jobits.pos.domain.models.ProductoVenta;
import com.jobits.pos.domain.models.ProductovOrden;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Home
 */
public interface ReservaService {

    public void crearEditarReserva(Date fecha, Mesa mesa, Cliente cliente, List<ProductovOrden> lista);

    public ProductovOrden generarProductovOrden(ProductoVenta producto, float cantidad);

    public void agregarProductoAOrden(boolean esAgrego, ProductovOrden productoAgregar, ProductovOrden productoSeleccinado);

    public void eliminarProDuctoDeOrden(ProductovOrden producto_seleccionado);

    /**
     *
     * @param fecha Fecha para la cual se esta realizando la reservacion
     * @return Devuelve la lista de mesas disponibles de acuerdo a la fecha de
     * reservacion. si la fecha es el mismo dia o al dia siguiente que se
     * realiza la reservacion solo se retornaran las mesas disponibles en el
     * momento de la reservacion
     */
    public List<Mesa> mesasDisponiblesParaReservar(Date fecha);

    /**
     *
     * @return Devuelve la instancia de la orden que se encuentra en el service
     */
    public Orden getReserva();

    public List<Cliente> getListaClientes();

}
