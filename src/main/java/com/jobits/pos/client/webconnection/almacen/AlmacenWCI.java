/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.almacen;

import com.jobits.pos.core.domain.TransaccionSimple;
import com.jobits.pos.core.domain.models.Almacen;
import com.jobits.pos.core.domain.models.InsumoAlmacen;
import com.jobits.pos.core.domain.models.PuestoTrabajo;
import com.jobits.pos.core.domain.models.Transaccion;
import com.jobits.pos.core.domain.models.TransaccionEntrada;
import com.jobits.pos.core.domain.models.TransaccionMerma;
import com.jobits.pos.core.domain.models.TransaccionSalida;
import com.jobits.pos.core.domain.models.TransaccionTransformacion;
import com.jobits.pos.core.domain.models.TransaccionTraspaso;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 *
 * @author Jorge
 */
public interface AlmacenWCI {

    @PUT("pos/almacen/{id}/reset-almacen")
    Call<Almacen> resetAlmacen(@Path("id") Object codAlmacen);

    @PUT("pos/almacen/{id}/crear-operacion-entrada/{recibo}")
    Call<Almacen> crearOperacionEntrada(@Body ArrayList<TransaccionSimple> transacciones, @Path("recibo") Object recibo, @Body Date fechaFactura, @Path("id") Object codAlmacen);

    @PUT("pos/almacen/{id}/crear-operacion-rebaja/{recibo}")
    Call<Almacen> crearOperacionRebaja(@Body ArrayList<TransaccionSimple> transacciones, @Path("recibo") Object recibo, @Body Date fechaFactura, @Path("id") Object codAlmacen);

    @PUT("pos/almacen/{id}/crear-operacion-salida/{idVenta}/{recibo}")
    Call<Almacen> crearOperacionSalida(@Body ArrayList<TransaccionSimple> transacciones, @Path("recibo") Object recibo, @Body Date fechaFactura, @Path("idVenta") Object codVenta, @Path("id") Object codAlmacen);

    @PUT("pos/almacen/{id}/crear-operacion-traspaso/{idVenta}/{recibo}")
    Call<Almacen> crearOperacionTraspaso(@Body ArrayList<TransaccionSimple> transacciones, @Path("recibo") Object recibo, @Body Date fechaFactura, @Path("id") Object codAlmacen);

    @PUT("pos/almacen/{id}/dar-entrada-insumo")
    Call<Almacen> darEntradaAInsumo(@Body TransaccionEntrada x, @Path("id") Object codAlmacen);

    @PUT("pos/almacen/{id}/dar-merma-insumo")
    Call<Almacen> darMermaInsumo(@Body TransaccionMerma x, @Path("id") Object codAlmacen);

    @PUT("pos/almacen/{id}/dar-salida-insumo/{idVenta}")
    Call<Almacen> darSalidaAInsumo(@Body TransaccionSalida x, @Path("idVenta") Object idVenta, @Path("id") Object codAlmacen);

    @PUT("pos/almacen/dar-transformacion-insumo/{idOrigen}/{idDestino}")
    Call<Almacen> darTransformacionAInsumo(@Body Transaccion t, @Path("idDestino") Object codAlmacenDestino, @Path("idOrigen") Object codAlmacenOrigen);

    @PUT("pos/almacen/{id}/dar-traspaso-insumo")
    Call<Almacen> darTraspasoInsumo(@Body TransaccionTraspaso x, @Path("id") Object codAlmacen);

    @PUT("pos/almacen/{id}/agregar-insumo-almacen/{codInsumo}")
    Call<Almacen> agregarInsumoAlmacen(@Path("codInsumo") Object codInsumo, @Path("id") Object codAlmacen);

    @PUT("pos/almacen/{id}/crear-transformacion/{cantidad}")
    Call<Almacen> crearTransformacion(@Body InsumoAlmacen selected, @Path("cantidad") Object cantidad, @Body List<TransaccionTransformacion> items, @Path("id") Object codAlmacen);

    @PUT("pos/almacen/{id}/set-centro-elaboracion")
    Call<Almacen> setCentroElaboracion(@Body boolean selected, @Path("id") Object codAlmacen);

    @PUT("pos/almacen/{id}/imprimir-reporte-compras/{seleccion}")
    Call<Almacen> imprimirReporteParaCompras(@Path("id") Object codAlmacen, @Path("id") Object selection);

    @PUT("pos/almacen/{id}/imprimir-resumen-almacen")
    Call<Almacen> imprimirResumenAlmacen(@Path("id") Object codAlmacen);

    @PUT("pos/almacen/{id}/remove-insumo-from-storage")
    Call<Almacen> removeInsumoFromStorage(@Body InsumoAlmacen insumoAlmacen, @Path("id") Object codAlmacen);

    @PUT("pos/almacen/{id}/update-valor-total-almacen")
    Call<Almacen> updateValorTotalAlmacen(@Path("id") Object codAlmacen);

    @PUT("pos/almacen/{id}/dar-entrada-ipv/{codInsumo}/{cantidad}")
    Call<Almacen> darEntradaIPV(@Path("id") Object codAlmacen, @Path("codInsumo") Object codInsumo, @Path("cantidad") Object cantidad);

    @PUT("pos/almacen/bulk-import")
    Call<Boolean> bulkImport(@Body List<InsumoAlmacen> importList);

    @GET("pos/almacen/{id}/get-insumo-almacen-list")
    Call<List<InsumoAlmacen>> getInsumoAlmacenList(@Path("id") Object codAlmacen);

    @GET("pos/almacen/{id}/find-insumo/{codInsumo}")
    Call<InsumoAlmacen> findInsumo(@Path("codInsumo") Object codInsumo, @Path("id") Object codAlmacen);

    @POST("pos/almacen/create")
    Call<Almacen> create(@Body Almacen t);

    @PUT("pos/almacen/edit")
    Call<Almacen> edit(@Body Almacen t);

    @PUT("pos/almacen/destroy")
    Call<Almacen> destroy(@Body Almacen t);

    @DELETE("pos/almacen/destroy/{id}")
    Call<Almacen> destroyById(@Path("id") Object o);

    @GET("pos/almacen/find/{id}")
    Call<Almacen> findBy(@Path("id") Object o);

    @GET("pos/almacen/list")
    Call<List<Almacen>> findAll();

}
