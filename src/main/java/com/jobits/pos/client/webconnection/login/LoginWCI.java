/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.login;

/**
 *
 * @author Jorge
 */
import com.jobits.pos.core.domain.models.Personal;
import java.util.Map;
import com.jobits.pos.client.webconnection.domain.Token;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface LoginWCI {

    @GET("pos/auth/basic")
    Call<Map<String, Object>> getToken(@Header("Tennant") String tennantToken, @Header("Authorization") String base64Credentials);

    @GET("tennant/cuenta/{idCuenta}/token-for/{idBaseDatos}")
    Call<Token> getTennantToken(@Header("Authorization") String base64CredentialsForTennant,
            @Path("idCuenta") int idCuenta, @Path("idBaseDatos") int idBaseDatos);
    
    @GET("pos/auth/get-user-info")
    Call<Personal> getPersonal(@Header("Tennant") String tennantToken, @Header("Authorization") String base64Credentials);

    
}
