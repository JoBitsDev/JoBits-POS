/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.configuracion;

/**
 *
 * @author Jorge
 */
import com.jobits.pos.core.domain.models.Configuracion;
import com.jobits.pos.core.usecase.algoritmo.ParametrosConfiguracion;
import com.jobits.pos.recursos.R;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ConfiguracionWCI {

    @GET("pos/configuration/list")
    public Call<Map<String, Configuracion>> cargarConfiguracion();

    @PUT("pos/configuration/update/{settingId}/{newValue}")
    public Call<Configuracion> updateConfiguracion(@Path("settingId") String settingId,
            @Path("newValue") Object configuration);

    @GET("pos/configuration/y")
    public Call<ParametrosConfiguracion> cargarConfiguracionY();

    @GET("pos/configuration/find/{settingId}")
    public Call<Configuracion> getConfiguracion(@Path("settingId") String settingId);

}
