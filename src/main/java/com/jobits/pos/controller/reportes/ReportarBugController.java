/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.reportes;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Home
 */
public class ReportarBugController implements ReportarBugService {

    public ReportarBugController() {
    }

    @Override
    public void guardarReporte(String usuario, String tituloReporte, String descripcion, String fecha) {
        if (tituloReporte == null || descripcion == null) {
            throw new IllegalArgumentException("Campos obligatorios vacios");
        }
        File d = new File("Reportes");
        d.mkdir();

        String reporte = "(" + usuario + ")  " + fecha + "\n" + "\n"
                + ResourceBundle.getBundle("Strings").getString("label_titulo") + ": " + "\n" + tituloReporte + "\n" + "\n"
                + ResourceBundle.getBundle("Strings").getString("label_descripcion") + ": " + "\n" + descripcion;
        fecha = fecha.replace(":", "-");
        String FILE_NAME = "Reportes/" + fecha + " " + tituloReporte + ".txt";

        File f = new File(FILE_NAME);

        if (!f.exists()) {
            try {
                f.createNewFile();
                try (FileWriter fw = new FileWriter(f, false)) {
                    fw.write(reporte);
                }
            } catch (IOException ex) {
                throw new IllegalAccessError("Error al guardar el reporte");
            }

        }
    }
}
