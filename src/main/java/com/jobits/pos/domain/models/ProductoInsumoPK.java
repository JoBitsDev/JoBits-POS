/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jobits.pos.domain.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * FirstDream
 * @author Jorge
 * 
 */
@JsonRootName(value = "productoInsumoPK")
@Embeddable
public class ProductoInsumoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "producto_ventap_cod")
    private String productoVentapCod;
    @Basic(optional = false)
    @Column(name = "insumocod_insumo")
    private String insumocodInsumo;

    public ProductoInsumoPK() {
    }

    public ProductoInsumoPK(String productoVentapCod, String insumocodInsumo) {
        this.productoVentapCod = productoVentapCod;
        this.insumocodInsumo = insumocodInsumo;
    }

    public String getProductoVentapCod() {
        return productoVentapCod;
    }

    public void setProductoVentapCod(String productoVentapCod) {
        this.productoVentapCod = productoVentapCod;
    }

    public String getInsumocodInsumo() {
        return insumocodInsumo;
    }

    public void setInsumocodInsumo(String insumocodInsumo) {
        this.insumocodInsumo = insumocodInsumo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productoVentapCod != null ? productoVentapCod.hashCode() : 0);
        hash += (insumocodInsumo != null ? insumocodInsumo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductoInsumoPK)) {
            return false;
        }
        ProductoInsumoPK other = (ProductoInsumoPK) object;
        if ((this.productoVentapCod == null && other.productoVentapCod != null) || (this.productoVentapCod != null && !this.productoVentapCod.equals(other.productoVentapCod))) {
            return false;
        }
        if ((this.insumocodInsumo == null && other.insumocodInsumo != null) || (this.insumocodInsumo != null && !this.insumocodInsumo.equals(other.insumocodInsumo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restManager.persistencia.ProductoInsumoPK[ productoVentapCod=" + productoVentapCod + ", insumocodInsumo=" + insumocodInsumo + " ]";
    }

}
