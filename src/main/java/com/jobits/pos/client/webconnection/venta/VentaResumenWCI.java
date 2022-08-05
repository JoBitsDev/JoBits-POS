/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.venta;

import com.jobits.pos.core.domain.models.AsistenciaPersonal;
import com.jobits.pos.core.domain.models.GastoVenta;
import com.jobits.pos.core.domain.models.ProductovOrden;
import com.jobits.pos.core.domain.models.Venta;
import com.jobits.pos.core.domain.models.escandallos.InsumoRegistro;
import com.jobits.pos.core.domain.models.temporal.DayReviewWrapper;
import com.jobits.pos.core.domain.models.temporal.ResumenVentaEstadisticas;
import com.jobits.pos.core.domain.models.temporal.ResumenVentaWrapper;
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
public interface VentaResumenWCI {

    public static final String BASE = "pos/venta/resumen/{desde}/{hasta}";
    public static final String AUTORIZO_RESUMEN = "/autorizo";
    public static final String COSTO_RESUMEN = "/costo";
    public static final String GASTO_RESUMEN = "/gasto";
    public static final String SALARIO_RESUMEN = "/salario";
    public static final String VENTAS_RESUMEN = "/ventas";

    @GET(BASE + AUTORIZO_RESUMEN)
    public Call<ResumenVentaWrapper<DayReviewWrapper<ProductovOrden>, ProductovOrden>> getAutorizoResumen(
            @Path("desde") LocalDate desde,
            @Path("hasta") LocalDate hasta);

    @GET(BASE + COSTO_RESUMEN)
    public Call<ResumenVentaWrapper<DayReviewWrapper<InsumoRegistro>, InsumoRegistro>> getCostoResumen(
            @Path("desde") LocalDate desde,
            @Path("hasta") LocalDate hasta);

    @GET(BASE + GASTO_RESUMEN)
    public Call<ResumenVentaWrapper<DayReviewWrapper<GastoVenta>, GastoVenta>> getGastoResumen(
            @Path("desde") LocalDate desde,
            @Path("hasta") LocalDate hasta);

    @GET(BASE + SALARIO_RESUMEN)
    public Call<ResumenVentaWrapper<DayReviewWrapper<AsistenciaPersonal>, AsistenciaPersonal>> getSalarioResumen(
            @Path("desde") LocalDate desde,
            @Path("hasta") LocalDate hasta);

    @GET(BASE + VENTAS_RESUMEN)
    public Call<ResumenVentaWrapper<DayReviewWrapper<ProductovOrden>, ProductovOrden>> getVentaResumen(
            @Path("desde") LocalDate desde,
            @Path("hasta") LocalDate hasta);

}
