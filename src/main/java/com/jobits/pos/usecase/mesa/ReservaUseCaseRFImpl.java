/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.usecase.mesa;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobits.pos.reserva.core.domain.Reserva;
import com.jobits.pos.reserva.core.usecase.ReservaUseCase;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class ReservaUseCaseRFImpl implements ReservaUseCase {

    protected static final ObjectMapper oMapper = new ObjectMapper()
            // .registerModule(new JavaTimeModule())
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://localhost:8080/jobits")
            .addConverterFactory(JacksonConverterFactory.create(oMapper))
            .build();

    ReservaUseCaseWCI wci = retrofit.create(ReservaUseCaseWCI.class);

    @Override
    public List<Reserva> getReservasDisponibles(LocalDate diaDereservas) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean checkIn(int idReserva, LocalDateTime checkinTime) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean checkOut(int idReserva, LocalDateTime checkoutTime) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean cancelar(int idReserva) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reserva create(Reserva t) throws RuntimeException {
        Reserva ret = handleResponse(wci.newReserva(null, null, t));
        return ret;
    }

    @Override
    public Reserva edit(Reserva t) throws RuntimeException {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reserva destroy(Reserva t) throws RuntimeException {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reserva destroyById(Object o) throws RuntimeException {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reserva findBy(Object o) throws RuntimeException {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reserva> findAll() throws RuntimeException {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener pl) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener pl) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    protected <T> T handleResponse(Call<T> call) throws RuntimeException {
        try {
            Response<T> resp = call.execute();
            if (resp.isSuccessful()) {
                return resp.body();
            } else {
                throw new RuntimeException(resp.errorBody().string());
            }
        } catch (IOException ex) {
            throw new RuntimeException("Bad call on" + call);
        }
    }
}
