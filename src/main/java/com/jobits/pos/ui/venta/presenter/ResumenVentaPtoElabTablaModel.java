/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.presenter;

import com.jobits.pos.domain.models.Cocina;
import lombok.Value;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
@Value
public class ResumenVentaPtoElabTablaModel {

    private Cocina ptoElaboracion;
    private String codigoPto, nombrePto, montoRecaudado;

    public ResumenVentaPtoElabTablaModel(Cocina ptoElaboracion, String montoRecaudado) {
        this.ptoElaboracion = ptoElaboracion;
        this.montoRecaudado = montoRecaudado;
        this.nombrePto = ptoElaboracion.getNombreCocina();
        this.codigoPto = ptoElaboracion.getCodCocina();
    }

}
