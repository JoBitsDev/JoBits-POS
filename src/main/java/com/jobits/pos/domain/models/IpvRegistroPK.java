/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jobits.pos.domain.models;

import com.fasterxml.jackson.annotation.JsonRootName;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * FirstDream
 * @author Jorge
 * 
 */
@JsonRootName(value = "ipvRegistroPK")
@Embeddable
public class IpvRegistroPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "ipvinsumocod_insumo")
    private String ipvinsumocodInsumo;
    @Basic(optional = false)
    @Column(name = "ipvcocinacod_cocina")
    private String ipvcocinacodCocina;
    @Basic(optional = false)
    @Column(name = "ventaid")
    private int ventaId;

    public IpvRegistroPK() {
    }

    public IpvRegistroPK(String ipvinsumocodInsumo, String ipvcocinacodCocina, int ventaId) {
        this.ipvinsumocodInsumo = ipvinsumocodInsumo;
        this.ipvcocinacodCocina = ipvcocinacodCocina;
        this.ventaId = ventaId;
    }

    public int getVentaId() {
        return ventaId;
    }

    public void setVentaId(int ventaId) {
        this.ventaId = ventaId;
    }

    public String getIpvinsumocodInsumo() {
        return ipvinsumocodInsumo;
    }

    public void setIpvinsumocodInsumo(String ipvinsumocodInsumo) {
        this.ipvinsumocodInsumo = ipvinsumocodInsumo;
    }

    public String getIpvcocinacodCocina() {
        return ipvcocinacodCocina;
    }

    public void setIpvcocinacodCocina(String ipvcocinacodCocina) {
        this.ipvcocinacodCocina = ipvcocinacodCocina;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.ipvinsumocodInsumo);
        hash = 37 * hash + Objects.hashCode(this.ipvcocinacodCocina);
        hash = 37 * hash + this.ventaId;
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
        final IpvRegistroPK other = (IpvRegistroPK) obj;
        if (this.ventaId != other.ventaId) {
            return false;
        }
        if (!Objects.equals(this.ipvinsumocodInsumo, other.ipvinsumocodInsumo)) {
            return false;
        }
        if (!Objects.equals(this.ipvcocinacodCocina, other.ipvcocinacodCocina)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "IpvRegistroPK{" + "ipvinsumocodInsumo=" + ipvinsumocodInsumo + ", ipvcocinacodCocina=" + ipvcocinacodCocina + ", ventaId=" + ventaId + '}';
    }


}
