/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.configuracion;

import com.jobits.pos.client.webconnection.BaseConnection;
import com.jobits.pos.controller.configuracion.ConfiguracionService;
import com.jobits.pos.core.domain.models.configuracion.Configuracion;
import com.jobits.pos.core.usecase.algoritmo.ParametrosConfiguracion;
import com.jobits.pos.recursos.R;

import java.util.Map;

/**
 * JoBits
 *
 * @author Jorge
 */
public class ConfiguracionWCS extends BaseConnection implements ConfiguracionService {

    ConfiguracionWCI service = retrofit.create(ConfiguracionWCI.class);

    public ConfiguracionWCS() {
    }

    @Override
    public Map<String, Configuracion> cargarConfiguracion() {
        return handleCall(service.cargarConfiguracion());
    }

    @Override
    public ParametrosConfiguracion cargarConfiguracionY() {
        return handleCall(service.cargarConfiguracionY());
    }

    @Override

    public Configuracion updateConfiguracion(R.SettingID v, Object configuration) {
        return handleCall(service.updateConfiguracion(v.getValue(), configuration));
    }

    @Override
    public Configuracion getConfiguracion(R.SettingID v) {
        return handleCall(service.getConfiguracion(v.getValue()));
    }

    @Override
    public Map<String, Configuracion> guardarConfiguracion(Map<String, Configuracion> map) {
        return handleCall(service.guardarConfiguracion(map));
    }

}
