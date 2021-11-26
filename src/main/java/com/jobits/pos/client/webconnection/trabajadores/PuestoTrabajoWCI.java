/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.trabajadores;

import com.jobits.pos.core.domain.models.PuestoTrabajo;
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
public interface PuestoTrabajoWCI {

    @POST("pos/puesto-trabajo/create")
    public Call<PuestoTrabajo> create( @Body PuestoTrabajo t);

    @PUT("pos/puesto-trabajo/edit")
    public Call<PuestoTrabajo> edit( @Body PuestoTrabajo t);

    @DELETE("pos/puesto-trabajo/destroy/{id}")
    public Call<PuestoTrabajo> destroyById( @Path("id") Object o);

    @GET("pos/puesto-trabajo/find/{id}")
    public Call<PuestoTrabajo> findBy( @Path("id") Object o);

    @GET("pos/puesto-trabajo/list")
    public Call<List<PuestoTrabajo>> findAll();

}
