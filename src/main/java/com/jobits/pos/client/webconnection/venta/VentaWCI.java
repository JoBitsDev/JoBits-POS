/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.venta;

import com.jobits.pos.core.domain.VentaResourcesWrapper;
import com.jobits.pos.core.domain.VentaResumenWrapper;
import com.jobits.pos.core.domain.models.Area;
import com.jobits.pos.core.domain.models.Cocina;
import com.jobits.pos.core.domain.models.IpvRegistro;
import com.jobits.pos.core.domain.models.Mesa;
import com.jobits.pos.core.domain.models.Orden;
import com.jobits.pos.core.domain.models.Personal;
import com.jobits.pos.core.domain.models.ProductoVenta;
import com.jobits.pos.core.domain.models.ProductovOrden;
import com.jobits.pos.core.domain.models.Venta;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 *
 * @author Jorge
 */
public interface VentaWCI {

    @POST("venta/inicializar")
    public Call<Venta> inicializarVentas(
            @Header("Tennant") String bearerTennantToken,
            @Header("Authorization") String basicAndToken,
            @Body Map<String, Object> params);

    @GET("venta/get-ventas-de-fecha")
    public Call<List<Venta>> getVentasDeFecha(@Body Date date);

    @GET("venta/get-ventas-de-fecha-by-id/{id}")
    public Call<List<Venta>> getVentasDeFecha(@Path("id") Object idVenta);

    @POST("venta/cambiar-turno/{id}")
    public Call<Venta> cambiarTurno(@Path("id") Object idTurnoTerminar, @Body Personal user);

    @GET("venta/can-open-nuevo-turno")
    public Call<Boolean> canOpenNuevoTurno(@Body Date fecha);

    @GET("venta/can-open-nuevo-turno-by-id/{id}")
    public Call<Boolean> canOpenNuevoTurno(@Path("id") Object idVenta);

    @GET("venta/can-reabrir-venta-by-id/{id}")
    public Call<Boolean> canReabrirVenta(@Path("id") Object idVenta);

    @POST("venta/create-new-orden/{id}")
    public Call<Orden> createNewOrden(@Path("id") Object idVenta, @Body Mesa mesa);

    @GET("venta/get-autorizos-total-producto/{id}")
    public Call<Float> getAutorizosTotalDelProducto(@Path("id") Object idVenta, @Body ProductoVenta productoVenta);

    @GET("venta/get-autorizos-total-producto/{id}")
    public Call<Float> getGastoTotalDeInsumo(@Path("id") Object idVenta, @Body IpvRegistro ipvRegistro);

    @GET("venta/get-ordenes-activas/{id}")
    public Call<List<Orden>> getOrdenesActivas(@Path("id") Object idVenta);

    @GET("venta/get-pago-trabajador/{id}/{dividirEntre}")
    public Call<Float> getPagoTrabajador(@Path("id") Object idVenta, @Path("dividirEntre") Object dividirEntre, @Body Personal personal);

    @GET("venta/get-propina-trabajador/{id}")
    public Call<Float> getPropinaTrabajador(@Path("id") Object idVenta, @Body Personal personal);

    public Call<String> getTotalGastadoInsumos(@Path("id") Object idVenta);

    @GET("venta/get-venta-resumen/{id}")
    public Call<VentaResumenWrapper> getVentaResumen(@Path("id") Object idVenta);

    @GET("venta/get-venta-total-producto/{id}")
    public Call<Float> getVentaTotalDelProducto(@Path("id") Object idVenta, @Body ProductoVenta productoVenta);

    @PUT("venta/get-importar-venta/{id}")
    public Call<File> importarVenta(@Body File file);

    @POST("venta/print-gastos-casa/{id}")
    public Call<Float> printGastosCasa(@Path("id") Object idVenta);

    @POST("venta/print-pago-por-venta-personal/{id}")
    public Call<Float> printPagoPorVentaPersonal(@Path("id") Object idVenta, @Body Personal personal, @Body boolean printValores);

    @POST("venta/print-comision-porcentual-resumen/{id}")
    public Call<Float> printComisionPorcentualResumen(@Path("id") Object idVenta, @Body Mesa mesa);

    @POST("venta/print-z/{id}")
    public Call<Float> printZ(@Path("id") Object idVenta);

    @POST("venta/reabrir-ventas/{id}")
    public Call<Float> reabrirVentas(@Path("id") Object idVenta);

    @POST("venta/terminar-ventas/{id}")
    public Call<Float> terminarVentas(@Path("id") Object idVenta);

    @POST("venta/terminar-y-exportar/{id}")
    public Call<Float> terminarYExportar(@Path("id") Object idVenta, @Body File file);

    @POST("venta/print-mesa-resumen/{id}")
    public Call<Float> printMesaResumen(@Path("id") Object idVenta, @Body Mesa mesa);

    @POST("venta/print-area-resumen/{id}")
    public Call<Float> printAreaResumen(@Path("id") Object idVenta, @Body Area area);

    @POST("venta/print-personal-resumen-row/{id}")
    public Call<Float> printPersonalResumenRow(@Path("id") Object idVenta, @Body Personal personal, @Body boolean printValores);

    @POST("venta/print-cocina-resumen/{id}/{idCocina}")
    public Call<Float> printCocinaResumen(@Path("id") Object idVenta, @Path("idCocina") Object idCocina, @Body boolean printValores);

    @GET("venta/get-venta-resources/{id}")
    public Call<VentaResourcesWrapper> getVentaResources(@Path("id") Object idVenta);

    @GET("venta/get-resumen-por-mesa/{id}")
    public Call<List<ProductovOrden>> getResumenPorMesa(@Path("id") Object idVenta, @Body Mesa mesa);

    @GET("venta/get-resumen-por-personal/{id}")
    public Call<List<ProductovOrden>> getResumenPorPersonal(@Path("id") Object idVenta, @Body Personal personal);

    @GET("venta/get-resumen-por-cocina/{id}")
    public Call<List<ProductovOrden>> getResumenPorCocina(@Path("id") Object idVenta, @Body Cocina cocina);

    @GET("venta/get-resumen-por-area/{id}")
    public Call<List<ProductovOrden>> getResumenPorArea(@Path("id") Object idVenta, @Body Area area);

    @GET("venta/get-total-resumen-por-mesa/{id}")
    public Call<String> getTotalResumenMesa(@Path("id") Object idVenta, @Body Mesa mesa);

    @GET("venta/get-total-resumen-por-personal/{id}")
    public Call<String> getTotalResumenDependiente(@Path("id") Object idVenta, @Body Personal personal);

    @GET("venta/get-total-resumen-por-cocina/{id}")
    public Call<String> getTotalResumenCocina(@Path("id") Object idVenta, @Body Cocina cocina);

    @GET("venta/get-total-resumen-por-area/{id}")
    public Call<String> getTotalResumenArea(@Path("id") Object idVenta, @Body Area area);

}
