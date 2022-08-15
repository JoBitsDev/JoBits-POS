/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.gasto;

import com.jobits.pos.core.domain.models.GastoVenta;
import com.jobits.pos.core.domain.models.temporal.DefaultGasto;
import com.jobits.pos.recursos.R;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * @author Jorge
 */
public interface GastoWCI {

    public static final String BASE = "pos/gastos";
    public static final String CREATE_GASTO_PATH = "/create-gasto/{idVenta}/{tipo}/{nombre}/{monto}";
    public static final String REMOVE_GASTO_PATH = "/remove-gasto/{idVenta}/{codGasto}";
    public static final String GET_VALOR_TOTAL_GASTOS_PATH = "/{idVenta}/get-valor-total-gastos";
    public static final String GET_NOMBRE_BY_TIPO_PATH = "/nombres-por-tipo/{tipo}";
    public static final String GET_DEFAULT_GASTOS_LIST_PATH = "/default-gastos-list";
    public static final String AGREGAR_DEFAULT_GASTO_PATH = "/agregar-gasto/{categoria}/{alias}/{nombre}/{monto}/{desc}";
    public static final String ELIMINAR_DEFAULT_GASTO_PATH = "/eliminar-gasto";


    @POST(BASE + CREATE_GASTO_PATH)
    public Call<GastoVenta> createGasto(@Path("tipo") R.TipoGasto tipoGasto,
                                        @Path("nombre") String nombre, @Path("monto") float monto,
                                        @Query("desc") String desc, @Path("idVenta") int idVenta);

    @DELETE(BASE + REMOVE_GASTO_PATH)
    public Call<GastoVenta> removeGasto(@Path("codGasto") String codGasto, @Path("idVenta") int idVenta);

    @GET(BASE + GET_VALOR_TOTAL_GASTOS_PATH)
    public Call<Float> getValorTotalGastos(@Path("idVenta") int idVenta);


    @GET(BASE + GET_NOMBRE_BY_TIPO_PATH)
    public Call<List<String>> getNombresByTipo(@Path("tipo") String tipo);


    @GET(BASE + GET_DEFAULT_GASTOS_LIST_PATH)
    public Call<List<DefaultGasto>> getDefaultGastosList();

    @POST(BASE + AGREGAR_DEFAULT_GASTO_PATH)
    public Call<DefaultGasto> agregarDefaultGasto(@Path("alias") String alias,
                                                  @Path("categoria") R.TipoGasto cat,
                                                  @Path("nombre") String nombre,
                                                  @Path("monto") float monto,
                                                  @Path("desc") String descripcion);


    @PUT(BASE + ELIMINAR_DEFAULT_GASTO_PATH)
    public Call<DefaultGasto> eliminarDefaultGasto(@Body DefaultGasto defaultGasto);

}
