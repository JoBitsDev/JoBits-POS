/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.servicios.impresion;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobits.pos.persistencia.Cocina;
import com.jobits.pos.persistencia.modelos.CocinaDAO;
import com.jobits.pos.persistencia.volatil.Impresora;
import com.jobits.pos.persistencia.volatil.UbicacionConexionModel;
import com.jobits.pos.persistencia.volatil.UbicacionWrapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintServiceLookup;

/**
 *
 * @author ERIK QUESADA
 */
public class ImpresoraUseCase {
    
    private List<Impresora> impresoras;
    private ObjectMapper om = new ObjectMapper();
    private String FILE_NAME = "impresoras.json";

    public ImpresoraUseCase() {
        try {
            impresoras = getImpresorasAlmacenadas();
        } catch (IOException ex) {
            try {
                File f = new File(FILE_NAME);
                f.createNewFile();
                
                impresoras = new ArrayList<>();
                CocinaDAO.getInstance().findAll();
                impresoras.add(new Impresora("Test1", CocinaDAO.getInstance().findAll(), true) );
                impresoras.add(new Impresora("Test2", new ArrayList(), true) );
                guardarImpresorasAlmacenadas();
            } catch (IOException ex1) {
    //            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Error de IO. {0}", ex1.getMessage());
            }
        }
    }
    
    private List<Impresora> getImpresorasAlmacenadas() throws IOException {
        return om.readValue(new File(FILE_NAME), om.getTypeFactory().constructCollectionLikeType(List.class,Impresora.class));
    }

    private void guardarImpresorasAlmacenadas() throws IOException {
        om.writeValue(new File(FILE_NAME), impresoras);
    }
    
    public List<Impresora> impresoraMathCocina (String mathWithCocina){
        List<Impresora> listaImpresoras = new ArrayList<>(); 
        
            for(int i =0;i<impresoras.size();i++){
                for (int j=0;j<impresoras.get(i).getCocinasEnlazadas().size();j++){
                    if(impresoras.get(i).getCocinasEnlazadas().get(j).getNombreCocina().equals(mathWithCocina));
                        listaImpresoras.add(impresoras.get(i));
                }
            }
      
        return listaImpresoras;   
    }
    
    public List<Impresora> impresorasDefault (){
        List<Impresora> listaImpresoras = new ArrayList<>();       
        
            for(int i =0;i<impresoras.size();i++){
                if (impresoras.get(i).isPorDefecto())
                    listaImpresoras.add(impresoras.get(i));              
            }
            
        return listaImpresoras; 
    }
    
    public void AddNewPrinter(String PrinterName,List<Cocina> Kitchen,boolean defecto){
        try {
            impresoras = getImpresorasAlmacenadas();
            impresoras.add(new Impresora(PrinterName,Kitchen,defecto) );
            guardarImpresorasAlmacenadas();

        } catch (IOException ex) {
            Logger.getLogger(ImpresoraUseCase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
   
}
