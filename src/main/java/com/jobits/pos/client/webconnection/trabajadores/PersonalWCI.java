/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.trabajadores;

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
public interface PersonalWCI {

    @POST("pos/personal/create")
    public Call<Personal> create(@Body Personal t);

    @PUT("pos/personal/edit")
    public Call<Personal> edit(@Body Personal t);

    @DELETE("pos/personal/destroy/{id}")
    public Call<Personal> destroyById(@Path("id") Object o);

    @PUT("pos/personal/{usuario}/pagar/{dateISO}")
    public Call<Personal> pagar(@Path("usuario") String usuario,@Path("dateISO") LocalDate hasta);

    @GET("pos/personal/find/{id}")
    public Call<Personal> findBy(@Path("id") Object o);

    @GET("pos/personal/list")
    public Call<List<Personal>> findAll();

}
