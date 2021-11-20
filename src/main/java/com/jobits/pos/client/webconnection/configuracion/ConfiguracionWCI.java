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
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LicenceWCI {

    @GET("licence/status")
    public Call<Licence> getLicence(
            @Header("Tennant") String bearerTennantToken,
            @Header("Authorization") String basicAndToken,
            @Query("tipo") String tipoLicencia);

    @GET("licence/uid")
    public Call<Map<String, String>> getUID(@Header("Tennant") String bearerTennantToken,
            @Header("Authorization") String basicAndToken);

    @PUT("licence/renew")
    public Call<Licence> renew(@Header("Tennant") String bearerTennantToken,
            @Header("Authorization") String basicAndToken, @Body String key);

}
