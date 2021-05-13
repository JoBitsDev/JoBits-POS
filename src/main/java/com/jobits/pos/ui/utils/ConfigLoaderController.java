/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.utils;

import com.jobits.pos.ui.utils.ConfigLoaderService;
import com.jobits.ui.swing.View;
import com.jobits.ui.themes.ThemeType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Home
 */
public class ConfigLoaderController implements ConfigLoaderService {

    private String DEFAULT_CFG_PATH = "app.config";
    private Properties prop = new Properties();
    private Map<String, String> defaultProperties = new HashMap<>(Map.of(
            "app.debug", "true",
            "app.theme", ThemeType.MATERIAL.getThemeName()
    ));

    public ConfigLoaderController() {
        File f = new File(DEFAULT_CFG_PATH);
        if (f.exists()) {
            checkAndSetProperties();
        } else {
            setProperties();
        }
        try {
            FileOutputStream os = new FileOutputStream(DEFAULT_CFG_PATH);
            prop.store(os, "Propiedades generales del sistema");
        } catch (IOException ex) {
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

    @Override
    public void setConfigValue(String property, String key) {
        File f = new File(DEFAULT_CFG_PATH);
        if (!f.exists()) {
            throw new IllegalArgumentException("El archivo de configuracion no existe");
        }
        try {
            InputStream is = new FileInputStream(DEFAULT_CFG_PATH);
            FileOutputStream os = new FileOutputStream(DEFAULT_CFG_PATH);
            prop.load(is);
            prop.replace(property, key);
            prop.store(os, "Propiedades generales del sistema");
        } catch (IOException e) {
        }
    }

    private void checkAndSetProperties() {
        try {
            InputStream is = new FileInputStream(DEFAULT_CFG_PATH);
            prop.load(is);
            for (String string : defaultProperties.keySet()) {
                if (prop.getProperty(string) == null) {
                    prop.setProperty(string, defaultProperties.get(string));
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ConfigLoaderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setProperties() {
        for (String string : defaultProperties.keySet()) {
            prop.setProperty(string, defaultProperties.get(string));
        }
    }

}
