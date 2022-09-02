/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection;

/**
 * JoBits
 *
 * @author Jorge
 */

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jobits.pos.client.webconnection.exception.ServerErrorException;
import com.root101.clean.core.app.usecase.AbstractUseCase;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import org.jboss.logging.Logger;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.lang.annotation.Annotation;

public class BaseConnection implements AbstractUseCase {

    /**
     * Tiempo maximo esperado para la lectura.
     */
    public static final int MAX_READ_TIME = 5 * 1000;
    /**
     * Tiempo maximo esperado para la respuesta del servidor.
     */
    public static final int MAX_RESPONSE_TIME = 4 * 1000;
    protected static final ObjectMapper oMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private static final OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new HeaderInterceptor())
            .build();
    protected static Retrofit retrofit;
    protected static Converter<ResponseBody, ApiError> converter;
    /**
     * Token para las llamandas seguras al servidor.
     */
    protected static String TOKEN = null;
    /**
     * Token para autenticarse en el servidor y que este lo redirija hacia la
     * base de datos correspondiente
     */
    protected static String TENNANT_TOKEN = null;
    protected final String HTTP_HEADER_LOCATION = "Location";
    protected final String HTTP_HEADER_TENNANT_ID = "TennantId ";
    protected final String HTTP_HEADER_AUTHORITATION = "Authorization";
    protected final String HTTP_HEADER_BEARER = "Bearer ";
    protected final String HTTP_HEADER_BASIC = "Basic ";

    public BaseConnection() {
    }

    public static void setRetrofit(String baseUrl) {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create(oMapper))
                .build();
        converter = retrofit.responseBodyConverter(ApiError.class, new Annotation[0]);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener pl) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener pl) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    protected <T> T handleCall(Call<T> call) throws ServerErrorException {
        Response<T> resp = null;
        try {
            resp = call.execute();
            if (resp.isSuccessful()) {
                return resp.body();
            } else {
                var error = converter.convert(resp.errorBody());
                throw new ServerErrorException(error);
            }
        } catch (IOException ex) {
            Logger.getLogger(this.getClass()).log(Logger.Level.ERROR, ex.fillInStackTrace());
            var err = new ApiError(500, ex.getMessage());
            throw new ServerErrorException(err);
        }
    }

    protected String getBearerToken() {
        return HTTP_HEADER_BEARER + TOKEN;
    }
}
