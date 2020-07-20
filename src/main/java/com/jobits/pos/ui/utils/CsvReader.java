/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Home
 */
public class CsvReader {

    public static List<String> readFile(BufferedReader br) {
        List<String> listaToReturn = new ArrayList<>();
        try {
            int columnLenght = br.readLine().split(",").length;
            String line = br.readLine();
            while (line != null) {
                listaToReturn.add(line);
                line = br.readLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(CsvReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaToReturn;
    }
}
