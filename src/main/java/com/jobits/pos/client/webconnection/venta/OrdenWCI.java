/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.venta;

import com.jobits.pos.controller.venta.OrdenService;
import com.jobits.pos.core.domain.models.Orden;
import com.jobits.pos.core.domain.models.Personal;
import com.jobits.pos.core.domain.models.ProductoVenta;
import com.jobits.pos.core.domain.models.ProductovOrden;
import com.jobits.pos.core.domain.models.temporal.ProductoVentaWrapper;

import java.util.List;
import java.util.Optional;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author Jorge
 */
public interface OrdenWCI {

    @POST(OrdenService.BASE + OrdenService.ADD_NOTA_PATH)
    public Call<Orden> addNota(@Path("id") String codOrden,
                               @Path("codProducto") int producto_orden_seleccionado,
                               @Path("nota") String nuevaNota);

    @POST(OrdenService.BASE + OrdenService.ADD_PRODUCT_PATH)
    public Call<ProductovOrden> addProduct(@Path("id") String codOrden,
                                           @Path("idProducto") String codProducto,
                                           @Path("cantidad") float cantidad,
                                           @Query("agregadoA") Optional<Integer> productoOrdenAgregar);

    @POST(OrdenService.BASE + OrdenService.ADD_PRODUCTO_COMPUESTO_PATH)
    public Call<Orden> addProductoCompuesto(@Path("id") String codOrden,
                                            @Path("idProducto") String producto_agregar,
                                            @Path("cantidad") Float cantidad,
                                            @Body List<ProductoVentaWrapper> lista_agregos);

    @POST(OrdenService.BASE + OrdenService.ADD_PRODUCTO_IN_HOT_PATH)
    public Call<Orden> addProductInHot(@Path("id") String codOrden,
                                       @Path("nombre") String nombre,
                                       @Path("precio") String precio,
                                       @Path("cantidad") String cantidad);

    @DELETE(OrdenService.BASE + OrdenService.REMOVE_PRODUCT_PATH)
    public Call<Orden> removeProduct(@Path("id") String codOrden,
                                     @Path("idProducto") int idProductoOrden, @Path("cantidad") float cantidad);

    @PUT(OrdenService.BASE + OrdenService.CERRAR_ORDEN_PATH)
    public Call<Orden> cerrarOrden(@Path("id") String codOrden, @Path("imprimirTicket") boolean imprimirTicket,
                                   @Path("pagadoCash") float pagadoCash, @Path("pagadoTarjeta") float pagadoTarjeta);

    @PUT(OrdenService.BASE + OrdenService.SET_DE_LA_CASA_PATH)
    public Call<Orden> setDeLaCasa(@Path("id") String codOrden, @Path("boolValue") boolean es_autorizo);

    @PUT(OrdenService.BASE + OrdenService.SET_PORCIENTO_PATH)
    public Call<Orden> setPorciento(@Path("id") String codOrden, @Path("porciento") float porciento_servicio);

    @PUT(OrdenService.BASE + OrdenService.MOVER_A_PATH)
    public Call<Orden> moverA(@Path("id") String codOrden, @Path("codMesa") String codMesa);

    @POST(OrdenService.BASE + OrdenService.IMPRIMIR_PRE_TICKET_PATH)
    public Call<Void> imprimirPreTicket(@Path("id") String codOrden);

    @PUT(OrdenService.BASE + OrdenService.ENVIAR_COCINA_PATH)
    public Call<Orden> enviarACocina(@Path("id") String codOrden);

    @POST(OrdenService.BASE + OrdenService.SET_CLIENTE)
    public Call<Orden> setCliente(@Path("id") String codOrden, @Path("idCliente") Integer clienteId);

    public Call<Orden> create(Orden t) throws RuntimeException;

    public Call<Orden> edit(Orden t) throws RuntimeException;

    public Call<Orden> destroy(Orden t) throws RuntimeException;

    public Call<Orden> destroyById(Object o) throws RuntimeException;

    @GET("pos/orden/find/{id}")
    public Call<Orden> findBy(@Path("id") Object o) throws RuntimeException;

    public Call<List<Orden>> findAll() throws RuntimeException;


    @POST(OrdenService.BASE + OrdenService.MARK_READY_TO_PICK_UP)
    Call<Orden> markReadyToPickup(String codOrden, @Path("idProductoOrden") int codProductoOrden, @Path("cantidad") float ammount);


    @POST(OrdenService.BASE + OrdenService.SET_PAGADO_POR_TARJETA_PATH)
    Call<Orden> setPagadoPorTarjeta(@Path("id") String string, @Path("pagadoTarjeta") boolean bln);
}
