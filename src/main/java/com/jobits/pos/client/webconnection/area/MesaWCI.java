/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.area;

import com.jobits.pos.core.domain.models.Mesa;
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
public interface MesaWCI {

    @PUT("pos/mesa/edit")
    public Call<Mesa> edit(@Body Mesa t);

    @GET("pos/mesa/list-free")
    public Call<List<Mesa>> getFreeTables();

    @DELETE("pos/mesa/destroy/{id}")
    public Call<Mesa> destroyById(@Path("id") Object o);

    @GET("pos/mesa/find/{id}")
    public Call<Mesa> findBy(@Path("id") Object o);

    @GET("pos/mesa/list")
    public Call<List<Mesa>> findAll();

}
