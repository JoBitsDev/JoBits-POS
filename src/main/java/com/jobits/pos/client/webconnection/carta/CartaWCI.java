/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.carta;

import com.jobits.pos.core.domain.models.Carta;
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
public interface CartaWCI {

    @POST("pos/carta/create")
    public Call<Carta> create( @Body Carta t);

    @PUT("pos/carta/edit")
    public Call<Carta> edit( @Body Carta t);

    @DELETE("pos/carta/destroy/{id}")
    public Call<Carta> destroyById( @Path("id") Object o);

    @GET("pos/carta/find/{id}")
    public Call<Carta> findBy( @Path("id") Object o);

    @GET("pos/carta/list")
    public Call<List<Carta>> findAll();

}
