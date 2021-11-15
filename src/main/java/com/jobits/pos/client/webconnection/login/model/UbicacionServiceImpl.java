/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.login.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jobits.pos.client.webconnection.BaseConnection;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UbicacionServiceImpl implements UbicacionService {

    private static final ObjectMapper om = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private static final String FILE_NAME = "ubicaciones.json";

    public UbicacionServiceImpl() {
    }

    @Override
    public UbicacionModelWrapper loadLocations() {
        try {
            var wrapper = om.readValue(new File(FILE_NAME), UbicacionModelWrapper.class);
            BaseConnection.setRetrofit("http://" + wrapper.getActiveUbicacion().getIp() + ":" + wrapper.getActiveUbicacion().getPuerto() + "/jobits/");
            return wrapper;
        } catch (IOException ex) {
            Logger.getLogger(UbicacionModelWrapper.class.getName()).log(Level.SEVERE, null, ex);
            var list = new ArrayList<UbicacionModel>();
            list.add(UbicacionModel.getDefaultUbicacion());
            list.add(UbicacionModel.getDefaultUbicacion());
            list.add(UbicacionModel.getDefaultUbicacion());
            list.add(UbicacionModel.getDefaultUbicacion());
            var ret = new UbicacionModelWrapper(list, 0);
            saveLocations(ret);
            return ret;
        }

    }

    @Override
    public UbicacionModelWrapper saveLocations(UbicacionModelWrapper wrapper) {
        try {
            om.writeValue(new File(FILE_NAME), wrapper);
            BaseConnection.setRetrofit("http://" + wrapper.getActiveUbicacion().getIp() + ":" + wrapper.getActiveUbicacion().getPuerto() + "/jobits/");
        } catch (IOException ex) {
            Logger.getLogger(UbicacionModelWrapper.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalStateException("No es posible acceder a las ubicaciones");
        }
        return wrapper;
    }

    @Override
    public UbicacionModelWrapper setSelectedLocation(UbicacionModel location) {
        var wrapper = loadLocations().getUbicaciones();
        for (int i = 0; i < wrapper.size(); i++) {
            if (wrapper.get(i).equals(location)) {
                wrapper.set(i, location);
                var newWrapper = new UbicacionModelWrapper(wrapper, i);
                return saveLocations(newWrapper);
            }
        }
        throw new IllegalStateException("Bad Location");
    }

}
