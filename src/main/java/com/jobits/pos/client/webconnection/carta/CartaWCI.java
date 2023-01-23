/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.carta;

import com.jobits.pos.core.domain.models.Carta;
import com.jobits.pos.core.domain.models.Seccion;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * @author Jorge
 */
public interface CartaWCI {

    @POST("pos/carta/create")
    public Call<Carta> create(@Body Carta t);

    @PUT("pos/carta/edit")
    public Call<Carta> edit(@Body Carta t);

    @DELETE("pos/carta/destroy/{id}")
    public Call<Carta> destroyById(@Path("id") Object o);

    @GET("pos/carta/find/{id}")
    public Call<Carta> findBy(@Path("id") Object o);

    @GET("pos/carta/list")
    public Call<List<Carta>> findAll();

    @POST("pos/carta/{cod}/add-seccion")
    public Call<Carta> addSeccion(@Path("cod") String codCarta, @Body Seccion codSeccion);

    @DELETE("pos/carta/{cod}/delete-seccion/{nombre}")
    public Call<Carta> removeSeccion(@Path("cod") String codCarta, @Path("nombre") String codSeccion);

}
