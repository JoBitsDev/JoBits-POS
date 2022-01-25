/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.venta;

import com.jobits.pos.core.domain.models.Orden;
import com.jobits.pos.core.domain.models.Personal;
import com.jobits.pos.core.domain.models.ProductoVenta;
import com.jobits.pos.core.domain.models.ProductovOrden;
import com.jobits.pos.core.domain.models.temporal.ProductoVentaWrapper;
import java.util.List;
import java.util.Optional;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 *
 * @author Jorge
 */
public interface OrdenWCI {

    @POST("pos/orden/{codOrden}/add-nota/{codProduct}/{nuevaNota}")
    public Orden addNota(@Path("codOrden") String codOrden,
            @Path("codProduct") int producto_orden_seleccionado,
            @Path("nuevaNota") String nuevaNota);

    @POST("pos/orden/{codOrden}/add-product/{codProduct}/{cantidad}/extras")
    public ProductovOrden addProduct(@Path("codOrden") String codOrden,
            @Path("codProduct") String codProducto,
            @Path("cantidad") float cantidad,
            @Query("agregadoA") Optional<Integer> productoOrdenAgregar);

    @POST("pos/orden/{codOrden}/add-product-advanced/{codProduct}/{cantidad}")
    public Orden addProductoCompuesto(@Path("codOrden") String codOrden,
            @Path("codProduct") String producto_agregar,
            @Path("cantidad") Float cantidad,
            @Body List<ProductoVentaWrapper> lista_agregos);

    @POST("pos/orden/{codOrden}/add-product-hot/{nombre}/{precio}/{cantidad}")
    public Orden addProductInHot(@Path("codOrden") String codOrden,
            @Path("nombre") String nombre, @Path("precio") String precio, @Path("cantidad") String cantidad);

    @DELETE("pos/orden/{codOrden}/remmove-product/{id}/{cantidad}")
    public Orden removeProduct(@Path("codOrden") String codOrden,
            @Path("id") int idProductoOrden, @Path("cantidad") float cantidad);

    @PUT("pos/orden/{codOrden}/cerrar-orden")
    public Orden cerrarOrden(@Path("codOrden") String codOrden, @Body boolean imprimirTicket);

    @PUT("pos/orden/{codOrden}/set-autorizo/{boolValue}")
    public Orden setDeLaCasa(@Path("codOrden") String codOrden, @Path("boolValue") boolean es_autorizo);

    @PUT("pos/orden/{codOrden}/set-porciento")
    public Orden setPorciento(@Path("codOrden") String codOrden, @Body float porciento_servicio);

    @PUT("pos/orden/{id}/mover-a/{codMesa}")
    public Orden moverA(@Path("id") String codOrden, @Path("codMesa") String codMesa);

    @POST("pos/orden/{id}/print-summary")
    public void imprimirPreTicket(@Path("id") String codOrden);

    @PUT("pos/orden/{id}/enviar-a-cocina")
    public Orden enviarACocina(@Path("id") String codOrden);

    @POST("pos/orden/{id}/set-client/{idClient}")
    public Orden setCliente(@Path("id") String codOrden, @Path("idCliente") Integer clienteId);

    public Orden create(Orden t) throws RuntimeException;

    public Orden edit(Orden t) throws RuntimeException;

    public Orden destroy(Orden t) throws RuntimeException;

    public Orden destroyById(Object o) throws RuntimeException;

    @GET("pos/orden/find/{id}")
    public Orden findBy(@Path("id") Object o) throws RuntimeException;

    public List<Orden> findAll() throws RuntimeException;
}
