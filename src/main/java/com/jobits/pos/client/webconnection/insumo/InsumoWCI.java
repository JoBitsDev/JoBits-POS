/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.insumo;

import com.jobits.pos.core.domain.models.Insumo;
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
public interface InsumoWCI {

    @POST("pos/insumos/bulk-import")
    public Call<List<Insumo>> bulkImport(@Body List<Insumo> importList);

    @POST("pos/insumos/create")
    public Call<Insumo> create( @Body Insumo t);

    @PUT("pos/insumos/edit")
    public Call<Insumo> edit( @Body Insumo t);

    @DELETE("pos/insumos/destroy/{id}")
    public Call<Insumo> destroyById( @Path("id") Object o);

    @GET("pos/insumos/find/{id}")
    public Call<Insumo> findBy( @Path("id") Object o);

    @GET("pos/insumos/list")
    public Call<List<Insumo>> findAll();

}
