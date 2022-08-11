/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.carta;

import com.jobits.pos.core.domain.models.Carta;
import com.jobits.pos.core.domain.models.Seccion;
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

    @POST("pos/clientes/create")
    public Call<Carta> create(@Body Carta t);

    @PUT("pos/clientes/edit")
    public Call<Carta> edit(@Body Carta t);

    @DELETE("pos/clientes/destroy/{id}")
    public Call<Carta> destroyById(@Path("id") Object o);

    @GET("pos/clientes/find/{id}")
    public Call<Carta> findBy(@Path("id") Object o);

    @GET("pos/clientes/list")
    public Call<List<Carta>> findAll();

    @POST("pos/clientes/{cod}/add-seccion")
    public Call<Carta> addSeccion(@Path("cod") String codCarta, @Body Seccion codSeccion);

}
