/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.almacen;

import com.jobits.pos.core.domain.TransaccionSimple;
import com.jobits.pos.inventario.core.almacen.domain.Almacen;
import com.jobits.pos.inventario.core.almacen.domain.InsumoAlmacen;
import com.jobits.pos.inventario.core.almacen.domain.Operacion;
import com.jobits.pos.inventario.core.almacen.domain.Transaccion;
import com.jobits.pos.inventario.core.almacen.domain.TransaccionEntrada;
import com.jobits.pos.inventario.core.almacen.domain.TransaccionMerma;
import com.jobits.pos.inventario.core.almacen.domain.TransaccionSalida;
import com.jobits.pos.inventario.core.almacen.domain.TransaccionTransformacion;
import com.jobits.pos.inventario.core.almacen.domain.TransaccionTraspaso;
import java.time.LocalDate;
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

    public static final String BASE = "pos/almacen";

    public static final String RESET_ALMACEN = "/{id}/reset-almacen";

    public static final String CREAR_OPERACION = "/{id}/crear-operacion/{tipo}/{recibo}/{fecha}/{idVenta}";

    public static final String OPERACIONES_PENDIENTES = "/{id}/operaciones-pendientes";

    public static final String EJECUTAR_OPERACION = "/{id}/ejecutar-operacion/{idOp}";

    public static final String ACTUALIZAR_OPERACION = "/{id}/actualizar-operacion";

    public static final String REGISTRAR_INSUMO = "/{id}/registrar-insumo/{codInsumo}";

    public static final String IMPRIMIR_REPORTE_COMPRAS = "/{id}/imprimir/reporte-compras";

    public static final String IMPRIMIR_RESUMEN = "/{id}/imprimir/resumen";

    public static final String BULK_IMPORT = "/bulk-import";

    public static final String ELIMINAR_INSUMO = "/{id}/eliminar-insumo/{idInsumo}";

    public static final String BUSCAR_INSUMO = "/{id}/insumo/{codInsumo}";

    @POST("pos/almacen/{id}/crear-operacion/{tipo}/{recibo}/{fecha}/{idVenta}")
    Call<Operacion> crearOperacion(
            @Path("tipo") Operacion.Tipo tipo,
            @Body ArrayList<TransaccionSimple> transacciones,
            @Path("recibo") Object recibo,
            @Path("fecha") LocalDate fechaFactura,
            @Path("id") Object codAlmacen,
            @Path("idVenta") Integer idVenta);

    @GET("pos/almacen/{id}/operaciones-pendientes")
    Call<List<Operacion>> getOperacionesPendientes(@Path("id") String codAlmacen);

    @PUT(BASE + ACTUALIZAR_OPERACION)
    public Call<Void> ejecutarOperacion(@Path("id") String codAlmacen, @Body Operacion operacionToUpdate);

    @PUT(BASE + EJECUTAR_OPERACION)
    public Call<Void> ejecutarOperacion(@Path("id") String codAlmacen, @Path("idOp") int idOperacion);

    @PUT(BASE + REGISTRAR_INSUMO)
    public Call<Void> agregarInsumoAlmacen(@Path("codInsumo") String codInsumo, @Path("id") String codAlmacen);

    @GET(BASE + IMPRIMIR_REPORTE_COMPRAS)
    public Call<Void> imprimirReporteParaCompras(@Path("id") String codAlmacen);//TODO: parametro raro aqui

    @GET(BASE + IMPRIMIR_RESUMEN)
    public Call<Void> imprimirResumenAlmacen(@Path("id") String codAlmacen);

    @DELETE(BASE + ELIMINAR_INSUMO)
    public Call<Void> removeInsumoFromStorage(@Path("id") String codAlmacen,
            @Path("idInsumo") String codInsumo);

    @POST(BASE + BULK_IMPORT)
    public Call<Boolean> bulkImport(@Body List<InsumoAlmacen> importList);

    @GET(BASE + BUSCAR_INSUMO)
    public Call<InsumoAlmacen> findInsumo(@Path("codInsumo") String codIns, @Path("id") String codAlmacen);

    @PUT(BASE + RESET_ALMACEN)
    public Call<Void> resetAlmacen(@Path("id") String codAlmacen);

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
