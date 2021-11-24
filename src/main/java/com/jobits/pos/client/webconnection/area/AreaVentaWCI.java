/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.area;

import com.jobits.pos.core.domain.models.Area;
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
public interface AreaVentaWCI {

    @POST("pos/area-venta/create")
    public Call<Area> create( @Body Area t);

    @PUT("pos/area-venta/edit")
    public Call<Area> edit( @Body Area t);

    @DELETE("pos/area-venta/destroy/{id}")
    public Call<Area> destroyById( @Path("id") Object o);

    @GET("pos/area-venta/find/{id}")
    public Call<Area> findBy( @Path("id") Object o);

    @GET("pos/area-venta/list")
    public Call<List<Area>> findAll();

}
