/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.utils;

import com.jobits.pos.ui.utils.ConfigLoaderService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Home
 */
public class ConfigLoaderController implements ConfigLoaderService {

    private String DEFAULT_CFG_PATH = "app.config";
    private Properties prop = new Properties();

    public ConfigLoaderController() {
        File f = new File(DEFAULT_CFG_PATH);
        if (!f.exists()) {
            try {
                FileOutputStream os = new FileOutputStream(DEFAULT_CFG_PATH);
                prop.setProperty("app.debug", "true");
                prop.store(os, "Propiedades generales del sistema");
            } catch (IOException ex) {
            }
        }
    }

    @Override
    public String getConfigValue(String key) {
        File f = new File(DEFAULT_CFG_PATH);
        if (!f.exists()) {
            throw new IllegalArgumentException("El archivo de configuracion no existe");
        }
        try {
            InputStream is = new FileInputStream(DEFAULT_CFG_PATH);
            prop.load(is);
        } catch (IOException e) {
        }
        String value = prop.getProperty(key);
        if (value == null) {
            throw new IllegalArgumentException("Propiedad " + key + " no encontrada");
        }
        return value;
    }

}
