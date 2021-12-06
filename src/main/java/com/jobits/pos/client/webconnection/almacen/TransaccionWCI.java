/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.almacen;

import com.jobits.pos.core.domain.models.Almacen;
import com.jobits.pos.core.domain.models.Cocina;
import com.jobits.pos.core.domain.models.Insumo;
import com.jobits.pos.core.domain.models.InsumoAlmacen;
import com.jobits.pos.core.domain.models.Operacion;
import com.jobits.pos.core.domain.models.Transaccion;
import com.jobits.pos.core.domain.models.TransaccionTransformacion;
import java.util.Date;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 *
 * @author Jorge
 */
public interface TransaccionWCI {

    @GET("pos/almacen/{id}/find-all-by-almacen")
    Call<List<Transaccion>> findAllByAlmacen(@Path("id") String codAlmacen);

    @GET("pos/almacen/{id}/find-mermas-by-almacen")
    Call<List<Transaccion>> findMermasByAlmacen(@Path("id") String almacen);

    @POST("pos/almacen/imprimir-transacciones-seleccionadas")
    Call<List<Transaccion>> imprimirTransaccionesSeleccionadas(@Body List<Transaccion> transaccionesSeleccionadas);

    @POST("pos/{id}/almacen/add-transaccion-entrada/{codInusmo}/{cantidad}/{importe}")
    Call<Transaccion> addTransaccionEntrada(@Body Operacion o, @Path("codInsumo") String codInsumo, @Body Date fecha, @Body Date hora, @Path("id") String codAlmacen, @Path("cantidad") Object cantidad, @Path("importe") Object importe);

    @POST("pos/{id}/almacen/add-transaccion-entrada/{codInusmo}/{cantidad}")
    Call<Transaccion> addTransaccionRebaja(@Body Operacion o, @Path("codInsumo") String codInsumo, @Body Date fecha, @Body Date hora, @Path("id") String codAlmacen, @Path("id") Object cantidad, @Body String causaRebaja, @Body boolean isMerma);

    @POST("pos/{id}/almacen/add-transaccion-entrada/{codInusmo}/{cantidad}/{codCocina}/{idVenta}")
    Call<Transaccion> addTransaccionSalida(@Body Operacion o, @Path("codInsumo") String codInsumo, @Body Date fecha, @Body Date hora, @Path("id") String codAlmacen, @Path("codCocina") String cocina, @Path("cantidad") Object cantidad, @Path("idVenta") Object idVenta);

    @POST("pos/{codAlmacenDestino}/almacen/add-transaccion-entrada/{cantidad}/{merma}")
    Call<Transaccion> addTransaccionTransformacion(@Body InsumoAlmacen selected, @Body Date fecha, @Body Date hora, @Body List<TransaccionTransformacion> items, @Path("cantidad") Object cantidad, @Path("merma") Object merma, @Path("codAlmacenDestino") String codAlmacenDestino);

    @POST("pos/{id}/almacen/add-transaccion-entrada/{codAlmacenDestino}/{codInusmo}/{cantidad}")
    Call<Transaccion> addTransaccionTraspaso(@Body Operacion o, @Path("codInsumo") String codInsumo, @Body Date fecha, @Body Date hora, @Path("id") String codAlmacen, @Path("codAlmacenDestino") String codAlmacenDestinoTraspaso, @Path("cantidad") Object cantidad);

}
