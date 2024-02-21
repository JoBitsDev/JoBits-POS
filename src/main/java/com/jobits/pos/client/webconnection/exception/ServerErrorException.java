/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.exception;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
import com.jobits.pos.client.webconnection.ApiError;
import java.util.concurrent.ExecutionException;

/**
 * Clase: Services Esta el la excepcion que se lanza cuando ocurre un error en
 * el servidor. En realidad la que se lanza es {@link ExecutionException } pero
 * se castea a esta para tener un mejor control sobre la excepcion en caso que
 * se necesite. Ademas que ExecutionException no permite instanciacion directa.
 *
 * @extends ExecutionException.
 */
public class ServerErrorException extends RuntimeException {

    private final ApiError apiError;

    public ServerErrorException(ApiError error) {
        super(error.toString());
        this.apiError = error;
    }

    public ServerErrorException() {
        super("Unknown");
        apiError = new ApiError(500, "Unknown");
    }

    public ApiError getApiError() {
        return apiError;
    }

}
