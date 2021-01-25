/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.configuracion;

/**
 *
 * @author Home
 */
public enum TipoDato {

    DEFAULT("---"),
    FICHA_DE_COSTO("Ficha de Costo"),
    INSUMO("Insumo");

    private final String tipoDeDato;

    private TipoDato(String tipoDeDato) {
        this.tipoDeDato = tipoDeDato;
    }

    public String getStringValue() {
        return tipoDeDato;
    }

    @Override
    public String toString() {
        return tipoDeDato; //To change body of generated methods, choose Tools | Templates.
    }

}
