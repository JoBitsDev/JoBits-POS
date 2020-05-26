/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jobits.pos.ui.almacen.ipv;

import com.jobits.pos.domain.models.Insumo;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class InsumoTransformacion {

    private Insumo insumo;
    private float cantidadCreada;
    private float cantidadDelTotal;

    public InsumoTransformacion(Insumo insumo, float cantidadCreada, float cantidadDelTotal) {
        this.insumo = insumo;
        this.cantidadCreada = cantidadCreada;
        this.cantidadDelTotal = cantidadDelTotal;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public float getCantidadCreada() {
        return cantidadCreada;
    }

    public void setCantidadCreada(float cantidadCreada) {
        this.cantidadCreada = cantidadCreada;
    }

    public float getCantidadDelTotal() {
        return cantidadDelTotal;
    }

    public void setCantidadDelTotal(float cantidadDelTotal) {
        this.cantidadDelTotal = cantidadDelTotal;
    }
    
    
    
}
