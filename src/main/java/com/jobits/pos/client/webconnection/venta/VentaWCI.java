/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.venta;

import com.jobits.pos.core.domain.models.Venta;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 *
 * @author Jorge
 */
public interface VentaWCI {

    @POST("venta-list/inicializar")
    public Call<Venta> inicializarVentas(
            @Header("Tennant") String bearerTennantToken,
            @Header("Authorization") String basicAndToken,
            @Body Map<String, Object> params);

}
