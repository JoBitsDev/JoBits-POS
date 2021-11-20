/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.licence;

/**
 *
 * @author Jorge
 */
import com.jobits.pos.controller.licencia.impl.Licence;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;

public interface LicenceWCI {

    @GET("licence/")
    public Call<Licence> getLicence(
            @Header("Tennant") String bearerTennantToken,
            @Header("Authorization") String basicAndToken);

    @GET("licence/uid")
    public Call<Map<String, String>> getUID();

    @PUT("licence/renew")
    public Call<Licence> renew(@Body String key);

}
