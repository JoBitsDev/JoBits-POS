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
public enum OpcionIO {

    IMPORTAR("Importar"),
    EXPORTAR("Exportar");

    private final String opcionAEjecutar;

    private OpcionIO(String opcionAEjecutar) {
        this.opcionAEjecutar = opcionAEjecutar;
    }

    public String getStringValue() {
        return opcionAEjecutar;
    }

    @Override
    public String toString() {
        return opcionAEjecutar;
    }

}
