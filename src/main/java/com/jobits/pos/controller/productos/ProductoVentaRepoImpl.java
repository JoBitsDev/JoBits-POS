/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.productos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.Writer;
import java.util.Arrays;

/**
 *
 * @author Home
 */
public class ProductoVentaRepoImpl implements ProductoVentaRepo {

    BufferedReader br;
    BufferedWriter bw;
    String FILE_NAME = "idProductoVenta.csv";
    private static final char DEFAULT_SEPARATOR = ',';
    List<String> nombreColumnas = Arrays.asList("idProductoVenta", "idBusqueda");

    @Override

    public List<ProductoVentaMapper> cargarProductoVenta() {
        List<ProductoVentaMapper> listaPV = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(new File(FILE_NAME)));
            int columnLenght = br.readLine().split(",").length;
            String line = br.readLine();
            while (line != null) {
                String[] values = line.split(",");
                String idProductoVenta = values[0];
                String idBusqueda = values[1];
                ProductoVentaMapper pvMapper = new ProductoVentaMapper(idProductoVenta, idBusqueda);
                listaPV.add(pvMapper);
                line = br.readLine();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProductoVentaRepoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProductoVentaRepoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaPV;
    }

    @Override
    public boolean guardarProductoVenta(List<ProductoVentaMapper> listaPVMapper) {
        try {
            bw = new BufferedWriter(new FileWriter(new File(FILE_NAME)));
            List<String> listaIdProductoVenta;
            String idProductoVenta, idBusqueda;

            writeLine(bw, nombreColumnas, DEFAULT_SEPARATOR, ' ');

            for (ProductoVentaMapper productoVentaMapper : listaPVMapper) {
                
                idProductoVenta = productoVentaMapper.getIdProductoVenta();
                idBusqueda = productoVentaMapper.getIdBusqueda();
                listaIdProductoVenta = Arrays.asList(idProductoVenta, idBusqueda);
                
                writeLine(bw, listaIdProductoVenta, DEFAULT_SEPARATOR, ' ');
            }
            bw.flush();
            bw.close();

        } catch (IOException ex) {
            Logger.getLogger(ProductoVentaRepoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public void writeLine(Writer w, List<String> values, char separators, char customQuote) throws IOException {

        boolean first = true;

        //default customQuote is empty
        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuilder sb = new StringBuilder();
        for (String value : values) {
            if (!first) {
                sb.append(separators);
            }
            if (customQuote == ' ') {
                sb.append(followCVSformat(value));
            } else {
                sb.append(customQuote).append(followCVSformat(value)).append(customQuote);
            }

            first = false;
        }
        sb.append("\n");
        w.append(sb.toString());

    }

    private String followCVSformat(String value) {

        String result = value;
        if (result.contains("\"")) {
            result = result.replace("\"", "\"\"");
        }
        return result;

    }

}
