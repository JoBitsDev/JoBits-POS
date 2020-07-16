/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.servicios.impresion;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobits.pos.exceptions.ExceptionHandler;
import com.jobits.pos.persistencia.volatil.Impresora;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImpresoraRepoImpl implements ImpresoraRepo {

    private String FILE_NAME = "impresoras.json";
    private ObjectMapper om = new ObjectMapper();

    public ImpresoraRepoImpl() {
        try {
            File f = new File(FILE_NAME);
            if (!f.exists()) {
                f.createNewFile();
                guardarImpresoras(new ArrayList<>());
            }
        } catch (IOException ex) {
            Logger.getLogger(ImpresoraRepoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public boolean eliminarImpresora(Impresora impresoraToDelete) {
        List<Impresora> lista = cargarImpresoras();
        boolean b = lista.remove(impresoraToDelete);
        guardarImpresoras(lista);
        return b;
    }

    @Override
    public List<Impresora> cargarImpresoras() {
        return importarFromJson();
    }

    @Override
    public boolean guardarImpresoras(List<Impresora> listaAGuardar) {
        exportarToJson(new File(FILE_NAME), listaAGuardar);
        return true;
    }

    private void exportarToJson(File fileToExport, List list) {
        try {
            om.writeValue(fileToExport, list);
        } catch (IOException ex) {
            ExceptionHandler.showExceptionToUser(ex);
        }
    }

    private List<Impresora> importarFromJson() {

        try {
            List<Impresora> listaImpresoras = om.readValue(new File(FILE_NAME),
                    om.getTypeFactory().constructCollectionLikeType(List.class, Impresora.class));
            if (listaImpresoras != null) {
                return listaImpresoras;
            }
        } catch (IOException ex) {
            ExceptionHandler.showExceptionToUser(ex);
        }
        return new ArrayList<>();
    }

}
