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

    @POST("pos/venta/inicializar")
    public Call<Venta> inicializarVentas(
            @Header("Tennant") String bearerTennantToken,
            @Header("Authorization") String basicAndToken,
            @Body Map<String, Object> params);

    @GET("pos/venta/get-ventas-de-fecha")
    public Call<List<Venta>> getVentasDeFecha(@Body Date date);

    @GET("pos/venta/{id}/get-ventas-de-fecha-by-id")
    public Call<List<Venta>> getVentasDeFecha(@Path("id") Object idVenta);

    @POST("pos/venta/{id}/cambiar-turno")
    public Call<Venta> cambiarTurno(@Path("id") Object idTurnoTerminar);

    @GET("pos/venta/can-open-nuevo-turno")
    public Call<Boolean> canOpenNuevoTurno(@Body Date fecha);

    @GET("pos/venta/{id}/can-open-nuevo-turno-by-id")
    public Call<Boolean> canOpenNuevoTurno(@Path("id") Object idVenta);

    @GET("pos/venta/{id}/can-reabrir-venta-by-id")
    public Call<Boolean> canReabrirVenta(@Path("id") Object idVenta);

    @POST("pos/venta/{id}/create-new-orden/{idMesa}")
    public Call<Orden> createNewOrden(@Path("id") Object idVenta, @Path("idMesa") Object idMesa);

    @GET("pos/venta/{id}/get-autorizos-total-producto/{idProdVenta}")
    public Call<Float> getAutorizosTotalDelProducto(@Path("id") Object idVenta, @Path("idProdVenta") Object idProductoVenta);

    @GET("pos/venta/{id}/get-autorizos-total-producto")
    public Call<Float> getGastoTotalDeInsumo(@Path("id") Object idVenta, @Body IpvRegistro ipvRegistro);

    @GET("pos/venta/{id}/get-ordenes-activas")
    public Call<List<Orden>> getOrdenesActivas(@Path("id") Object idVenta);

    @GET("pos/venta/{id}/get-pago-trabajador/{dividirEntre}/{codPersonal}")
    public Call<Float> getPagoTrabajador(@Path("id") Object idVenta, @Path("dividirEntre") Object dividirEntre, @Path("codPersonal") String codPersonal);

    @GET("pos/venta/{id}/get-propina-trabajador/{codPersonal")
    public Call<Float> getPropinaTrabajador(@Path("id") Object idVenta, @Path("codPersonal") String codPersonal);

    @GET("pos/venta/{id}/get-venta-resumen")
    public Call<VentaResumenWrapper> getVentaResumen(@Path("id") Object idVenta);

    @GET("pos/venta/{id}/get-venta-total-producto")
    public Call<Float> getVentaTotalDelProducto(@Path("id") Object idVenta, @Body ProductoVenta productoVenta);

    @PUT("pos/venta/{id}/get-importar-venta")
    public Call<File> importarVenta(@Body File file);

    @POST("pos/venta/{id}/print-gastos-casa")
    public Call<Float> printGastosCasa(@Path("id") Object idVenta);

    @POST("pos/venta/{id}/print-pago-por-venta-personal")
    public Call<Float> printPagoPorVentaPersonal(@Path("id") Object idVenta, @Body String personal, @Body boolean printValores);

    @POST("pos/venta/{id}/print-comision-porcentual-resumen")
    public Call<Float> printComisionPorcentualResumen(@Path("id") Object idVenta, @Body String mesa);

    @POST("pos/venta/{id}/print-z")
    public Call<Float> printZ(@Path("id") Object idVenta);

    @POST("pos/venta/{id}/reabrir-ventas")
    public Call<Float> reabrirVentas(@Path("id") Object idVenta);

    @POST("pos/venta/{id}/terminar-ventas")
    public Call<Float> terminarVentas(@Path("id") Object idVenta);

    @POST("pos/venta/{id}/terminar-y-exportar")
    public Call<Float> terminarYExportar(@Path("id") Object idVenta, @Body File file);

    @POST("pos/venta/{id}/print-mesa-resumen")
    public Call<Float> printMesaResumen(@Path("id") Object idVenta, @Body String codMesa);

    @POST("pos/venta/{id}/print-area-resumen")
    public Call<Float> printAreaResumen(@Path("id") Object idVenta, @Body String codArea);

    @POST("pos/venta/{id}/print-personal-resumen-row")
    public Call<Float> printPersonalResumenRow(@Path("id") Object idVenta, @Body String codPersonal, @Body boolean printValores);

    @POST("pos/venta/{id}/print-cocina-resumen/{idCocina}")
    public Call<Float> printCocinaResumen(@Path("id") Object idVenta, @Path("idCocina") Object idCocina, @Body boolean printValores);

    @GET("pos/venta/{id}/get-venta-resources")
    public Call<VentaResourcesWrapper> getVentaResources(@Path("id") Object idVenta);

    @GET("pos/venta/{id}/get-resumen-por-mesa")
    public Call<List<ProductovOrden>> getResumenPorMesa(@Path("id") Object idVenta, @Body String codMesa);

    @GET("pos/venta/{id}/get-resumen-por-personal")
    public Call<List<ProductovOrden>> getResumenPorPersonal(@Path("id") Object idVenta, @Body String codPersonal);

    @GET("pos/venta/{id}/get-resumen-por-cocina")
    public Call<List<ProductovOrden>> getResumenPorCocina(@Path("id") Object idVenta, @Body String codCocina);

    @GET("pos/venta/{id}/get-resumen-por-area")
    public Call<List<ProductovOrden>> getResumenPorArea(@Path("id") Object idVenta, @Body String codArea);

    @GET("pos/venta/{id}/get-total-resumen-por-mesa")
    public Call<String> getTotalResumenMesa(@Path("id") Object idVenta, @Body String codMesa);

    @GET("pos/venta/{id}/get-total-resumen-por-personal")
    public Call<String> getTotalResumenDependiente(@Path("id") Object idVenta, @Body String codPersonal);

    @GET("pos/venta/{id}/get-total-resumen-por-cocina")
    public Call<String> getTotalResumenCocina(@Path("id") Object idVenta, @Body String codCocina);

    @GET("pos/venta/{id}/get-total-resumen-por-area")
    public Call<String> getTotalResumenArea(@Path("id") Object idVenta, @Body String codArea);

}
