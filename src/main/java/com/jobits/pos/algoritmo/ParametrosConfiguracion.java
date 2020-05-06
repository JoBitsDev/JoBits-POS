/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jobits.pos.algoritmo;

import java.util.List;
import com.jobits.pos.persistencia.Seccion;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class ParametrosConfiguracion {

    List<Seccion> bebidas,excluidos;
    
    byte c,m;

    public ParametrosConfiguracion() {
    }
    
    public ParametrosConfiguracion(List<Seccion> bebidas, List<Seccion> excluidos, byte c, byte m) {
        this.bebidas = bebidas;
        this.excluidos = excluidos;
        this.c = c;
        this.m = m;
    }

    public List<Seccion> getBebidas() {
        return bebidas;
    }

    public List<Seccion> getExcluidos() {
        return excluidos;
    }

    public byte getC() {
        return c;
    }

    public byte getM() {
        return m;
    }

    public void setBebidas(List<Seccion> bebidas) {
        this.bebidas = bebidas;
    }

    public void setExcluidos(List<Seccion> excluidos) {
        this.excluidos = excluidos;
    }

    public void setC(byte c) {
        this.c = c;
    }

    public void setM(byte m) {
        this.m = m;
    }
    
    
    
    
}
