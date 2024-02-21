/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.usecase.mesa;

import com.jobits.pos.reserva.core.domain.Reserva;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 *
 * @author Jorge
 */
public interface ReservaUseCaseWCI {

    @POST("reserva/register-reserva")
    Call<Reserva> newReserva(@Header("Tennant") String tennantToken,
            @Header("Authorization") String base64Credentials,
            @Body Reserva newReserva);

}
