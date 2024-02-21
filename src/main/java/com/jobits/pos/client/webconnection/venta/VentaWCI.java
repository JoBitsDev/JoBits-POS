/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.venta;

import com.jobits.pos.core.domain.VentaResourcesWrapper;
import com.jobits.pos.core.domain.VentaResumenWrapper;
import com.jobits.pos.core.domain.models.Orden;
import com.jobits.pos.core.domain.models.Venta;
import com.jobits.pos.core.domain.models.temporal.ResumenPagoTrabajador;
import retrofit2.Call;
import retrofit2.http.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * @author Jorge
 */
public interface VentaWCI {

    @POST("pos/venta/inicializar/{dateISO}")
    public Call<Integer> inicializarVentas(@Path("dateISO") LocalDate date);

    @GET("pos/venta/listar-ventas-id/fecha/{dateISO}")
    public Call<List<Venta>> getVentasDeFecha(@Path("dateISO") LocalDate date);

    @GET("pos/venta/listar-ventas-id/fecha/{dateISO}")
    public Call<List<Integer>> getVentas(@Path("dateISO") LocalDate date);

    @GET("pos/venta/listar-ventas-id/{id}")
    public Call<List<Integer>> getVentas(@Path("id") Integer date);

    @GET("pos/venta/{id}/list-all")
    public Call<List<Venta>> getVentasDeFecha(@Path("id") int idVenta);

    @POST("pos/venta/{id}/cambiar-turno")
    public Call<Venta> cambiarTurno(@Path("id") int idTurnoTerminar);

    @GET("pos/venta/can-open-nuevo-turno/{dateISO}")
    public Call<Integer> canOpenNuevoTurno(@Path("dateISO") LocalDate fecha);

    @GET("pos/venta/{id}/can-open-nuevo-turno")
    public Call<Integer> canOpenNuevoTurno(@Path("id") int idVenta);

    @GET("pos/venta/{id}/can-reabrir")
    public Call<Integer> canReabrirVenta(@Path("id") int idVenta);

    @POST("pos/venta/{id}/create-new-orden/{idMesa}")
    public Call<Orden> createNewOrden(@Path("id") int idVenta, @Path("idMesa") Object idMesa);

    @GET("pos/venta/{id}/get-autorizos-total-producto")
    public Call<Map<String, Float>> getAutorizosTotalDelProducto(@Path("id") int idVenta);

    @GET("pos/venta/{id}/get-gasto-total-insumos")
    public Call<Map<String, Float>> getGastoTotalDeInsumo(@Path("id") int idVenta);

    @GET("pos/venta/{id}/ordenes-activas")
    public Call<List<Orden>> getOrdenesActivas(@Path("id") int idVenta);

    @GET("pos/venta/{id}/get-pago-trabajador/{dividirEntre}/{codPersonal}")
    public Call<ResumenPagoTrabajador> getPagoTrabajador(@Path("id") int idVenta, @Path("dividirEntre") int dividirEntre, @Path("codPersonal") String codPersonal);

    @GET("pos/venta/{id}/get-propina-trabajador/{codPersonal}")
    public Call<Float> getPropinaTrabajador(@Path("id") int idVenta, @Path("codPersonal") String codPersonal);

    @GET("pos/venta/{id}/get-resumen")
    public Call<VentaResumenWrapper> getVentaResumen(@Path("id") int idVenta);

    @GET("pos/venta/{id}/get-venta-total-producto")
    public Call<Map<String, Float>> getVentaTotalDelProducto(@Path("id") int idVenta);

    @PUT("pos/venta/importar")
    public Call<Venta> importarVenta(@Body String jsonFile);

    @POST("pos/venta/{id}/print/autorizos")
    public Call<Void> printGastosCasa(@Path("id") int idVenta);

    @POST("pos/venta/{id}/print/pago-por-venta/{usuario}/{printValores}")
    public Call<Void> printPagoPorVentaPersonal(@Path("id") int idVenta, @Path("usuario") String personal, @Path("printValores") boolean printValores);

    @POST("pos/venta/{id}/print/comision-porcentual/{codMesa}")
    public Call<Void> printComisionPorcentualResumen(@Path("id") int idVenta, @Path("codMesa") String mesa);

    @POST("pos/venta/{id}/print/z")
    public Call<Void> printZ(@Path("id") int idVenta);

    @POST("pos/venta/{id}/reabrir-ventas")
    public Call<Venta> reabrirVentas(@Path("id") int idVenta);

    @POST("pos/venta/{id}/terminar-ventas")
    public Call<Venta> terminarVentas(@Path("id") int idVenta);

    @POST("pos/venta/{id}/terminar-y-exportar")
    public Call<String> terminarYExportar(@Path("id") int idVenta);

    @GET("pos/venta/{id}/print/mesa-resumen/{codMesa}")
    public Call<Void> printMesaResumen(@Path("id") int idVenta, @Path("codMesa") String codMesa);

    @GET("pos/venta/{id}/print/area-resumen/{codArea}")
    public Call<Void> printAreaResumen(@Path("id") int idVenta, @Path("codArea") String codArea);

    @GET("pos/venta/{id}/print/personal-resumen/{usuario}/{printValues}")
    public Call<Void> printPersonalResumenRow(@Path("id") int idVenta,
                                              @Path("usuario") String codPersonal,
                                              @Path("printValues") boolean printValores);

    @GET("pos/venta/{id}/print/cocina-resumen/{idCocina}/{printValues}")
    public Call<Void> printCocinaResumen(@Path("id") int idVenta,
                                         @Path("idCocina") String idCocina,
                                         @Path("printValues") boolean printValores);

    @GET("pos/venta/{id}/get-resources")
    public Call<VentaResourcesWrapper> getVentaResources(@Path("id") int idVenta);

    @GET("pos/venta/find-integer/{id}")
    public Call<Venta> findBy(@Path("id") int idVenta);


    @PUT("pos/venta/{id}/set-propina/{cantidad}")
    Call<Float> setPropina(@Path("id") Integer id, @Path("cantidad") Float ventapropina);

}
