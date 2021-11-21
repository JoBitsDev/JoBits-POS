/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.product;

import com.jobits.pos.core.domain.models.ProductoVenta;
import java.util.List;
import org.hibernate.validator.constraints.URL;
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
public interface ProductoVentaWCI {

    @POST("pos/poducts/bulk-import")
    public Call<List<ProductoVenta>> bulkImport(@Body List<ProductoVenta> importList);

    @POST("pos/products/create")
    public Call<ProductoVenta> create( @Body ProductoVenta t);

    @PUT("pos/products/edit")
    public Call<ProductoVenta> edit( @Body ProductoVenta t);

    @DELETE("pos/products/destroy/{id}")
    public Call<ProductoVenta> destroyById( @Path("id") Object o);

    @GET("pos/products/find/{id}")
    public Call<ProductoVenta> findBy( @Path("id") Object o);

    @GET("pos/products/list")
    public Call<List<ProductoVenta>> findAll();

}
