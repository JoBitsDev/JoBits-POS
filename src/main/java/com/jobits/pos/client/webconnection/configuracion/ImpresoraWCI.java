/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.configuracion;

import com.jobits.pos.servicios.impresion.Impresora;
import java.util.List;
import javax.print.Doc;
import javax.print.PrintException;
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
public interface ImpresoraWCI {

    @POST("pos/printing/add")
    public Call<Impresora> agregarImpresora(@Body Impresora impresora);

    @PUT("pos/printing/update")
    public Call<Impresora> updateImpresora(@Body Impresora impresora);

    @DELETE("pos/printing/remove/{id}")
    public Call<Impresora> deleteImpresora(@Path("id") int impresora);

    @GET("pos/printing/{nameVirtualPrinter}")
    public Call<Impresora> findBy(@Path("nameVirtualPrinter")String nombreVirtualImpresora);

    @GET("pos/printing/list")
    public Call<List<Impresora>> findAll();

    @GET("pos/printing/get-system-printers")
    public Call<List<String>> getNombreImpresorasSistema();

    @GET("pos/printing/get-group-names")
    public Call<List<String>> getNombreGrupos();

}
