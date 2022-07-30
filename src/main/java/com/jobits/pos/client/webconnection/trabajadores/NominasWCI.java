/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.trabajadores;

import com.jobits.pos.core.domain.AsistenciaPersonalEstadisticas;
import com.jobits.pos.core.domain.models.Personal;
import java.time.LocalDate;
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
public interface NominasWCI {

    @GET("pos/nominas/personal-activo/{desde}/{hasta}")
    public Call<List<AsistenciaPersonalEstadisticas>> getPersonalActivo(
            @Path("desde") LocalDate desde,
            @Path("hasta") LocalDate hasta);

    @PUT("pos/nominas/imprimir-estadisticas")
    public Call<Void> imprimirEstadisticas(@Body List<AsistenciaPersonalEstadisticas> lista);

    @PUT("pos/nominas/pagar/{hasta}/{imprimir}")
    public Call<List<AsistenciaPersonalEstadisticas>> pagar(@Body List<AsistenciaPersonalEstadisticas> lista,
            @Path("hasta") LocalDate hasta,
            @Path("imprimir") boolean imprimir);

}
