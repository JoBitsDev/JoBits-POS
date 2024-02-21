/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.area;

import com.jobits.pos.core.domain.models.Area;
import com.jobits.pos.core.domain.models.Mesa;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * @author Jorge
 */
public interface AreaVentaWCI {

    @POST("pos/area-venta/create")
    public Call<Area> create(@Body Area t);

    @PUT("pos/area-venta/edit")
    public Call<Area> edit(@Body Area t);

    @DELETE("pos/area-venta/destroy/{id}")
    public Call<Area> destroyById(@Path("id") Object o);

    @GET("pos/area-venta/find/{id}")
    public Call<Area> findBy(@Path("id") Object o);

    @GET("pos/area-venta/list")
    public Call<List<Area>> findAll();

    @POST("pos/area-venta/{cod}/add-mesa")
    public Call<Area> addMesa(@Path("cod") String codArea, @Body Mesa newmesa);

    @DELETE("pos/area-venta/{cod}/remove-mesa/{cod-mesa}")
    public Call<Area> removeMesa(@Path("cod") String codArea, @Path("cod-mesa") String newmesa);

}
