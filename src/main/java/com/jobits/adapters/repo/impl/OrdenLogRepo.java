/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.adapters.repo.impl;

import com.jobits.pos.controller.productos.ProductoVentaMapperRepoImpl;
import com.jobits.pos.main.Application;
import com.jobits.pos.recursos.R;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Home
 */
public class OrdenLogRepo {

    private static final String LOGS_FILE_PATH = R.LOGS_FILE_PATH + "Ordenes/";

    public static void saveToLogFile(String value) {
        try {
            File mediaFolder = new File(LOGS_FILE_PATH);
            if (!mediaFolder.exists()) {
                mediaFolder.mkdirs();
            }
            String[] fullorden = value.split("_");
            String[] orden = fullorden[1].split(" ");
            String splitValues = orden[0];
            File mediaFile = new File(LOGS_FILE_PATH + splitValues + ".txt");
            if (!mediaFile.exists()) {
                mediaFile.createNewFile();
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter(mediaFile, true));
            StringBuilder sb = new StringBuilder();
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
            String logTime = sdf.format(Calendar.getInstance().getTime());

            sb.append(logTime).append("_").append(value);
            sb.append("\n");
            bw.append(sb.toString());

            bw.flush();
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(ProductoVentaMapperRepoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<String[]> loadLogFile(String keyWord) {
        Application.getInstance().authorizeUser(R.NivelAcceso.ECONOMICO);
        List<String[]> listaToReturn = new ArrayList<>();
        File f = new File(LOGS_FILE_PATH + keyWord + ".txt");
        if (f.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(f));
                String line = br.readLine();
                while (line != null) {
                    String[] data = line.split("_");
                    data[3] = data[3] + "_" + data[4];
                    data[4] = data[5];
                    data = Arrays.copyOf(data, data.length - 1);
                    listaToReturn.add(data);
                    line = br.readLine();
                }
            } catch (IOException ex) {
                Logger.getLogger(OrdenLogRepo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaToReturn;
    }

}
