/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.persistencia.volatil;

import com.jobits.pos.persistencia.Cocina;
import java.util.List;

/**
 *
 * @author ERIK QUESADA
 */
public class Impresora {
    
    private String nombreImpresoraSistema;
    private List<Cocina> cocinasEnlazadas;
    boolean porDefecto;
           

    public Impresora(String nombreImpresoraSistema,List<Cocina> cocinasEnlazadas,boolean porDefecto) {
        this.nombreImpresoraSistema = nombreImpresoraSistema;
        this.cocinasEnlazadas = cocinasEnlazadas;  
        this.porDefecto = porDefecto;
        
    }

    public String getNombreImpresoraSistema() {
        return nombreImpresoraSistema;
    }

    public void setNombreImpresoraSistema(String nombreImpresoraSistema) {
        this.nombreImpresoraSistema = nombreImpresoraSistema;
    }

    public List<Cocina> getCocinasEnlazadas() {
        return cocinasEnlazadas;
    }

    public void setCocinasEnlazadas(List<Cocina> cocinasEnlazadas) {
        this.cocinasEnlazadas = cocinasEnlazadas;
    }

    public boolean isPorDefecto() {
        return porDefecto;
    }

    public void setPorDefecto(boolean porDefecto) {
        this.porDefecto = porDefecto;
    }
  
}
