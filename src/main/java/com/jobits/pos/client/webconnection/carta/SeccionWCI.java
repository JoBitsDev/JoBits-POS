/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.carta;

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
public interface SeccionWCI {

    @POST("pos/seccion/create")
    public Call<Seccion> create(@Body Seccion t);

    @PUT("pos/seccion/edit")
    public Call<Seccion> edit(@Body Seccion t);

    @DELETE("pos/seccion/destroy/{id}")
    public Call<Seccion> destroyById(@Path("id") Object o);

    @GET("pos/seccion/find/{id}")
    public Call<Seccion> findBy(@Path("id") Object o);

    @GET("pos/seccion/list")
    public Call<List<Seccion>> findAll();

    @GET("pos/seccion/list/by-mesa/{idMesa}")
    public Call<List<Seccion>> findSeccionesByMesa(@Path("idMesa") String codMesa);

    @PUT("pos/seccion/{nombreSeccion}/move-to/{codCarta}")
    public Call<Seccion> moveSeccionToCarta(@Path("nombreSeccion") String seccionNombre, @Path("codCarta") String codigoCarta);

}
