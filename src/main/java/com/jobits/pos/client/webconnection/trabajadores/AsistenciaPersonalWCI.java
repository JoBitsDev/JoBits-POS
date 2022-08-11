/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.trabajadores;

import com.jobits.pos.controller.trabajadores.AsistenciaPersonalService;
import com.jobits.pos.core.domain.AsistenciaPersonalEstadisticas;
import com.jobits.pos.core.domain.models.AsistenciaPersonal;
import com.jobits.pos.core.module.PosCoreModule;
import retrofit2.Call;
import retrofit2.http.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Jorge
 */
public interface AsistenciaPersonalWCI {


    public static final String BASE = "pos/asistencia-personal";

    public static final String IMPRIMIR_ASISTENCIA = "/venta/{id}/imprimir-asistencia";

    public static final String CALCULAR_PAGO_TRABAJADOR = "/venta/{id}/calcular-pago/{usuario}";

    public static final String UPDATE_A_MAYORES = "/venta/{id}/a-mayores/{usuario}/{cantidad}";

    public static final String GET_PERSONAL_TRABAJANDO = "/venta/{id}/get-personal-trabajando";

    public static final String UPDATE_SALARIES = "/venta/{id}/update-salaries";

    @POST(BASE + CALCULAR_PAGO_TRABAJADOR)
    public Call<AsistenciaPersonal> calcularPagoTrabajador(@Path("id") int idVenta, @Path("usuario") String usuario);

    @GET(BASE + IMPRIMIR_ASISTENCIA)
    public Call<Void> imprimirAsistencia(@Path("id") int idVenta);

    @PUT(BASE + UPDATE_A_MAYORES)
    public Call<AsistenciaPersonal> updateAMayores(@Path("id") int idVenta,
                                                   @Path("usuario") String usuario, @Path("cantidad") float cantidad);

    @GET(BASE + GET_PERSONAL_TRABAJANDO)
    public Call<List<AsistenciaPersonal>> getPersonalTrabajando(@Path("id") int idVenta);

    @GET(BASE + UPDATE_SALARIES)
    public Call<List<AsistenciaPersonal>> updateSalaries(@Path("id") int idVenta);

}
