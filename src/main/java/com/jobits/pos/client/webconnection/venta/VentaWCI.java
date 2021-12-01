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
import java.time.LocalDate;
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
import retrofit2.http.Query;

/**
 *
 * @author Jorge
 */
public interface VentaWCI {

    @POST("pos/venta/inicializar")
    public Call<Venta> inicializarVentas(@Body Map<String, Object> params);

    @GET("pos/venta/list-of-date/{dateISO}")
    public Call<List<Venta>> getVentasDeFecha(@Path("dateISO") LocalDate date);

    @GET("pos/venta/{id}/list-all-turns")
    public Call<List<Venta>> getVentasDeFecha(@Path("id") int idVenta);

    @POST("pos/venta/{id}/cambiar-turno")
    public Call<Venta> cambiarTurno(@Path("id") int idTurnoTerminar);

    @GET("pos/venta/can-open-nuevo-turno/{dateISO}")
    public Call<Boolean> canOpenNuevoTurno(@Path("dateISO") LocalDate fecha);

    @GET("pos/venta/{id}/can-open-nuevo-turno")
    public Call<Boolean> canOpenNuevoTurno(@Path("id") int idVenta);

    @GET("pos/venta/{id}/can-reabrir")
    public Call<Boolean> canReabrirVenta(@Path("id") int idVenta);

    @POST("pos/venta/{id}/create-new-orden/{idMesa}")
    public Call<Orden> createNewOrden(@Path("id") int idVenta, @Path("idMesa") Object idMesa);

    @GET("pos/venta/{id}/get-autorizos-total-producto/{idProdVenta}")
    public Call<Float> getAutorizosTotalDelProducto(@Path("id") int idVenta, @Path("idProdVenta") String idProductoVenta);

    @GET("pos/venta/{id}/get-gasto-total-insumo/{codCocina}/{codInsumo}")
    public Call<Float> getGastoTotalDeInsumo(@Path("id") int idVenta,
            @Path("codCocina") String codCocina,
            @Path("codInsumo") String codInsumo);

    @GET("pos/venta/{id}/get-ordenes-activas")
    public Call<List<Orden>> getOrdenesActivas(@Path("id") int idVenta);

    @GET("pos/venta/{id}/get-pago-trabajador/{dividirEntre}/{codPersonal}")
    public Call<Float> getPagoTrabajador(@Path("id") int idVenta, @Path("dividirEntre") int dividirEntre, @Path("codPersonal") String codPersonal);

    @GET("pos/venta/{id}/get-propina-trabajador/{codPersonal}")
    public Call<Float> getPropinaTrabajador(@Path("id") int idVenta, @Path("codPersonal") String codPersonal);

    @GET("pos/venta/{id}/get-resumen")
    public Call<VentaResumenWrapper> getVentaResumen(@Path("id") int idVenta);

    @GET("pos/venta/{id}/get-venta-total-producto/{codProducto}")
    public Call<Float> getVentaTotalDelProducto(@Path("id") int idVenta,
            @Path("codProducto") String codProducto);

    @PUT("pos/venta/importar")
    public Call<File> importarVenta(@Body String jsonFile);

    @POST("pos/venta/{id}/print/autorizos")
    public Call<Float> printGastosCasa(@Path("id") int idVenta);

    @POST("pos/venta/{id}/print/pago-por-venta/{usuario}")
    public Call<Float> printPagoPorVentaPersonal(@Path("id") int idVenta, @Path("usuario") String personal, @Query("printValues") boolean printValores);

    @POST("pos/venta/{id}/print/comision-porcentual/{codMesa}")
    public Call<Float> printComisionPorcentualResumen(@Path("id") int idVenta, @Path("codMesa") String mesa);

    @POST("pos/venta/{id}/print/z")
    public Call<Float> printZ(@Path("id") int idVenta);

    @POST("pos/venta/{id}/reabrir-ventas")
    public Call<Float> reabrirVentas(@Path("id") int idVenta);

    @POST("pos/venta/{id}/terminar-ventas")
    public Call<Float> terminarVentas(@Path("id") int idVenta);

    @POST("pos/venta/{id}/terminar-y-exportar")
    public Call<Float> terminarYExportar(@Path("id") int idVenta, @Body String pathToFile);

    @POST("pos/venta/{id}/print/mesa-resumen/{codMesa}")
    public Call<Float> printMesaResumen(@Path("id") int idVenta, @Path("codMesa") String codMesa);

    @POST("pos/venta/{id}/print/area-resumen/{codArea}")
    public Call<Float> printAreaResumen(@Path("id") int idVenta, @Path("codArea") String codArea);

    @POST("pos/venta/{id}/print/personal-resumen/{usuario}")
    public Call<Float> printPersonalResumenRow(@Path("id") int idVenta,
            @Path("usuario") String codPersonal, @Query("printValues") boolean printValores);

    @POST("pos/venta/{id}/print/cocina-resumen/{idCocina}")
    public Call<Float> printCocinaResumen(@Path("id") int idVenta, @Path("idCocina") String idCocina, @Query("printValues") boolean printValores);

    @GET("pos/venta/{id}/get-resources")
    public Call<VentaResourcesWrapper> getVentaResources(@Path("id") int idVenta);

    @GET("pos/venta/{id}/get-resumen-por-mesa/{codMesa}")
    public Call<List<ProductovOrden>> getResumenPorMesa(@Path("id") int idVenta, @Path("codMesa") String codMesa);

    @GET("pos/venta/{id}/get-resumen-por-personal/{usuario}")
    public Call<List<ProductovOrden>> getResumenPorPersonal(@Path("id") int idVenta, @Path("usuario") String codPersonal);

    @GET("pos/venta/{id}/get-resumen-por-cocina/{ptoElaboracion}")
    public Call<List<ProductovOrden>> getResumenPorCocina(@Path("id") int idVenta, @Path("ptoElaboracion") String codCocina);

    @GET("pos/venta/{id}/get-resumen-por-area/{codArea}")
    public Call<List<ProductovOrden>> getResumenPorArea(@Path("id") int idVenta, @Path("codArea") String codArea);

    @GET("pos/venta/{id}/get-total-resumen-por-mesa/{codMesa}")
    public Call<String> getTotalResumenMesa(@Path("id") int idVenta, @Path("codMesa") String codMesa);

    @GET("pos/venta/{id}/get-total-resumen-por-personal/{usuario}")
    public Call<String> getTotalResumenDependiente(@Path("id") int idVenta, @Path("usuario") String codPersonal);

    @GET("pos/venta/{id}/get-total-resumen-por-cocina/{ptoElab}")
    public Call<String> getTotalResumenCocina(@Path("id") int idVenta, @Path("ptoElab") String codCocina);

    @GET("pos/venta/{id}/get-total-resumen-por-area/{codArea}")
    public Call<String> getTotalResumenArea(@Path("id") int idVenta, @Path("codArea") String codArea);

}
