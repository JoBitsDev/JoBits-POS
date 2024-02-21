/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.venta;

import com.jobits.pos.core.domain.models.Venta;
import com.jobits.pos.core.domain.models.temporal.DayReviewWrapper;
import com.jobits.pos.core.domain.models.temporal.ResumenVentaEstadisticas;
import java.time.LocalDate;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 *
 * @author Jorge
 */
public interface VentaCalendarWCI {

    public static final String BASE = "pos/venta-list";
    public static final String RESOLVE_VENTA_ABIERTA = "/venta-abierta";
    public static final String FIND_VENTAS_BY_MONTH = "/find-by/month/{anno}/{mes}";
    public static final String FIND_VENTAS_IN_RANGE = "/find-by/range/{start}/{end}";
    public static final String IS_Y_VISIBLE = "/is-y-visible";
    public static final String FIND_VENTAS_BY_MONTH_VIEW = "/find-by/month-view/{anno}/{mes}";
    public static final String GET_RESUMEN_VENTAS_ESTAIDISTICAS = "/resumen-por-ids";
    public static final String DESTROY_BY_ID = "/destroy/{id}";

    @GET(BASE + RESOLVE_VENTA_ABIERTA)
    public Call<Venta> resolveVentaAbierta();

    @GET(BASE + FIND_VENTAS_BY_MONTH)
    Call<List<DayReviewWrapper<Venta>>> findVentasByMonth(@Path("mes") int month, @Path("anno") int year);

    @GET(BASE + FIND_VENTAS_BY_MONTH_VIEW)
    Call<List<DayReviewWrapper<ResumenVentaEstadisticas>>> findVentasByMonthView(@Path("mes") int month, @Path("anno") int year);

    @GET(BASE + FIND_VENTAS_IN_RANGE)
    Call<List<Venta>> findVentasInRange(@Path("start") LocalDate start, @Path("end") LocalDate end);

    @GET(BASE + GET_RESUMEN_VENTAS_ESTAIDISTICAS)
    Call<List<ResumenVentaEstadisticas>> getResumenDeVentasEstadisticas(@Body List<Integer> idVentas);

    @GET(BASE + IS_Y_VISIBLE)
    Call<Boolean> isYVisible();

    @DELETE(BASE + DESTROY_BY_ID)
    Call<Venta> destroyById(@Path("id") int ventaId);

}
