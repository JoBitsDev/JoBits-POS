/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class HeaderInterceptor implements Interceptor {

    protected static String TENNANT_TOKEN = null;
    protected final String HTTP_HEADER_LOCATION = "Location";
    protected final String HTTP_HEADER_TENNANT = "Tennant";
    protected final String HTTP_HEADER_AUTHORITATION = "Authorization";
    protected final String HTTP_HEADER_BEARER = "Bearer ";
    protected final String HTTP_HEADER_BASIC = "Basic ";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (request.headers().get(HTTP_HEADER_TENNANT) == null && BaseConnection.TENNANT_TOKEN != null) {
            request = request.newBuilder()
                    .addHeader("Tennant", BaseConnection.TENNANT_TOKEN)
                    .build();
        }
        if (request.header(HTTP_HEADER_BASIC) == null && BaseConnection.TOKEN != null) {
            request = request.newBuilder()
                    .addHeader("Authorization", HTTP_HEADER_BEARER + BaseConnection.TOKEN) //new header added
                    .build();
        }

        Response response = chain.proceed(request);
        return response;
    }
}
