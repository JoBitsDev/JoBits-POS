/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.domain;

import com.jobits.pos.domain.models.Almacen;
import com.jobits.pos.domain.models.Cocina;
import com.jobits.pos.domain.models.Insumo;
import com.jobits.pos.domain.models.InsumoAlmacen;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class TransaccionSimple {

    private final InsumoAlmacen insumo;
    private final float cantidad;
    private float monto;
    private Almacen aDestino;
    private Cocina cDestino;
    private String causa;

    /**
     * Contructor Indefinido
     *
     * @param insumo
     * @param cantidad
     */
    public TransaccionSimple(InsumoAlmacen insumo, float cantidad) {
        this.insumo = insumo;
        this.cantidad = cantidad;
    }

    /**
     * Contructor para entradas
     *
     * @param insumo
     * @param cantidad
     * @param monto
     */
    public TransaccionSimple(InsumoAlmacen insumo, float cantidad, float monto) {
        this.insumo = insumo;
        this.cantidad = cantidad;
        this.monto = monto;
    }

    /**
     * Constructor para traspasos
     *
     * @param insumo
     * @param cantidad
     * @param aDestino
     */
    public TransaccionSimple(InsumoAlmacen insumo, float cantidad, Almacen aDestino) {
        this.insumo = insumo;
        this.cantidad = cantidad;
        this.aDestino = aDestino;
    }

    /**
     * Constructor para salidas
     *
     * @param insumo
     * @param cantidad
     * @param cDestino
     */
    public TransaccionSimple(InsumoAlmacen insumo, float cantidad, Cocina cDestino) {
        this.insumo = insumo;
        this.cantidad = cantidad;
        this.cDestino = cDestino;
    }

    /**
     * Contructor para rebajas
     *
     * @param insumo
     * @param cantidad
     * @param causa
     */
    public TransaccionSimple(InsumoAlmacen insumo, float cantidad, String causa) {
        this.insumo = insumo;
        this.cantidad = cantidad;
        this.causa = causa;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public void setaDestino(Almacen aDestino) {
        this.aDestino = aDestino;
    }

    public void setcDestino(Cocina cDestino) {
        this.cDestino = cDestino;
    }

    public void setCausa(String causa) {
        this.causa = causa;
    }

    public InsumoAlmacen getInsumo() {
        return insumo;
    }

    public float getCantidad() {
        return cantidad;
    }

    public float getMonto() {
        return monto;
    }

    public Almacen getaDestino() {
        return aDestino;
    }

    public Cocina getcDestino() {
        return cDestino;
    }

    public String getCausa() {
        return causa;
    }
    
    

}

