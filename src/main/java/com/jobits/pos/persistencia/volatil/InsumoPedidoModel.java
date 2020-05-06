/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.persistencia.volatil;

import com.jobits.pos.persistencia.Insumo;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class InsumoPedidoModel {

    private final Insumo insumo;
    private final float cantidad;

    public InsumoPedidoModel(Insumo insumo, float cantidad) {
        this.insumo = insumo;
        this.cantidad = cantidad;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public float getCantidad() {
        return cantidad;
    }

}
