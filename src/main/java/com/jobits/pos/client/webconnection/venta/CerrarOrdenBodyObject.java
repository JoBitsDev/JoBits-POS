/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jobits.pos.client.webconnection.venta;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class CerrarOrdenBodyObject {

    private final boolean imprimirTicket;
    private final float pagadoCash;
    private final float pagadoTarjeta;

    public static CerrarOrdenBodyObject from(boolean imprimirTicket, float pagadoCash, float pagadoTarjeta) {
        return new CerrarOrdenBodyObject(imprimirTicket, pagadoCash, pagadoTarjeta);
    }

    private CerrarOrdenBodyObject(boolean imprimirTicket, float pagadoCash, float pagadoTarjeta) {
        this.imprimirTicket = imprimirTicket;
        this.pagadoCash = pagadoCash;
        this.pagadoTarjeta = pagadoTarjeta;
    }

    public boolean isImprimirTicket() {
        return imprimirTicket;
    }

    public float getPagadoCash() {
        return pagadoCash;
    }

    public float getPagadoTarjeta() {
        return pagadoTarjeta;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (this.imprimirTicket ? 1 : 0);
        hash = 79 * hash + Float.floatToIntBits(this.pagadoCash);
        hash = 79 * hash + Float.floatToIntBits(this.pagadoTarjeta);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CerrarOrdenBodyObject other = (CerrarOrdenBodyObject) obj;
        if (this.imprimirTicket != other.imprimirTicket) {
            return false;
        }
        if (Float.floatToIntBits(this.pagadoCash) != Float.floatToIntBits(other.pagadoCash)) {
            return false;
        }
        return Float.floatToIntBits(this.pagadoTarjeta) == Float.floatToIntBits(other.pagadoTarjeta);
    }

}
