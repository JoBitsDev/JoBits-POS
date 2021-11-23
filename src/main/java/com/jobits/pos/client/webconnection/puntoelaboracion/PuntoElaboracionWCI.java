/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.puntoelaboracion;

import com.jobits.pos.core.domain.models.Cocina;
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
public interface PuntoElaboracionWCI {

    @DELETE("pos/punto-elaboracion//destroy-cascade/{idCocina}")
    public Call<Cocina> destroyInCascade(@Path("idCocina") String codCocina);

    @PUT("pos/punto-elaboracion/{id}/edit-name/{name}")
    public Call<Cocina> edit(@Path("id") String codCocina, @Path("name") String name);

    @POST("pos/punto-elaboracion/create")
    public Call<Cocina> create(@Body Cocina t);

    @PUT("pos/punto-elaboracion/edit")
    public Call<Cocina> edit(@Body Cocina t);

    @DELETE("pos/punto-elaboracion/destroy/{id}")
    public Call<Cocina> destroyById(@Path("id") Object o);

    @GET("pos/punto-elaboracion/find/{id}")
    public Call<Cocina> findBy(@Path("id") Object o);

    @GET("pos/punto-elaboracion/list")
    public Call<List<Cocina>> findAll();

}
