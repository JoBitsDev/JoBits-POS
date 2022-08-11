/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.cliente;

import com.jobits.pos.cliente.core.domain.ClienteDomain;
import com.jobits.pos.core.domain.models.Carta;
import com.jobits.pos.core.domain.models.Seccion;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * @author Jorge
 */
public interface ClienteWCI {

    @POST("pos/clientes/create")
    public Call<ClienteDomain> create(@Body ClienteDomain t);

    @PUT("pos/clientes/edit")
    public Call<ClienteDomain> edit(@Body ClienteDomain t);

    @DELETE("pos/clientes/destroy/{id}")
    public Call<ClienteDomain> destroyById(@Path("id") Object o);

    @GET("pos/clientes/find/{id}")
    public Call<ClienteDomain> findBy(@Path("id") Object o);

    @GET("pos/clientes/list")
    public Call<List<ClienteDomain>> findAll();

    @GET("pos/clientes/find-by-phone/{phone}")
    public Call<ClienteDomain> findByPhone(String phone);
}
