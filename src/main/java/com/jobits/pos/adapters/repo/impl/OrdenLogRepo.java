/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.adapters.repo.impl;

import com.jobits.pos.controller.productos.ProductoVentaMapper;
import com.jobits.pos.controller.productos.ProductoVentaMapperRepoImpl;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.utils.CsvReader;
import com.jobits.pos.ui.utils.CsvWriter;
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

    private static final String LOGS_FILE_PATH = R.logFilePath + "Ordenes/";

    public static void saveToLogFile(String value) {
        try {
            File mediaFolder = new File(LOGS_FILE_PATH);
            if (!mediaFolder.exists()) {
                mediaFolder.mkdirs();
            }
            String[] splitValues = value.split("_");
            File mediaFile = new File(LOGS_FILE_PATH + splitValues[1] + ".txt");
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
        List<String[]> listaToReturn = new ArrayList<>();
        File f = new File(LOGS_FILE_PATH + keyWord + ".txt");
        if (f.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(f));
                String line = br.readLine();
                while (line != null) {
                    String[] data = line.split("_");
                    listaToReturn.add(data);
                    line = br.readLine();
                }
            } catch (IOException ex) {
                Logger.getLogger(CsvReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaToReturn;
    }

}
