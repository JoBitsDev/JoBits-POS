/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.productos;

import com.jobits.pos.ui.utils.CsvWriter;
import com.jobits.pos.ui.utils.CsvReader;
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
import java.util.Arrays;

/**
 *
 * @author Home
 */
public class ProductoVentaMapperRepoImpl implements ProductoVentaMapperRepo {

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

            List<String> fileReaded = CsvReader.readFile(br);

            for (String line : fileReaded) {
                String[] values = line.split(",");
                String idProductoVenta = values[0];
                String idBusqueda = values[1];
                ProductoVentaMapper pvMapper = new ProductoVentaMapper(idProductoVenta, idBusqueda);
                listaPV.add(pvMapper);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProductoVentaMapperRepoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaPV;
    }

    @Override
    public boolean guardarProductoVenta(List<ProductoVentaMapper> listaPVMapper) {
        try {
            bw = new BufferedWriter(new FileWriter(new File(FILE_NAME)));
            List<String> listaIdProductoVenta;
            String idProductoVenta, idBusqueda;

            CsvWriter.writeLine(bw, nombreColumnas);

            for (ProductoVentaMapper productoVentaMapper : listaPVMapper) {

                idProductoVenta = productoVentaMapper.getIdProductoVenta();
                idBusqueda = productoVentaMapper.getIdBusqueda();
                listaIdProductoVenta = Arrays.asList(idProductoVenta, idBusqueda);

                CsvWriter.writeLine(bw, listaIdProductoVenta);

            }
            bw.flush();
            bw.close();

        } catch (IOException ex) {
            Logger.getLogger(ProductoVentaMapperRepoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
