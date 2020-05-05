/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restManager.persistencia;

import com.fasterxml.jackson.annotation.JsonRootName;
import java.io.Serializable;
import java.util.Date;
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
@JsonRootName(value = "ipvVentaRegistroPK")
@Embeddable
public class IpvVentaRegistroPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "ventafecha")
    @Temporal(TemporalType.DATE)
    private Date ventafecha;
    @Basic(optional = false)
    @Column(name = "producto_ventap_cod")
    private String productoVentapCod;

    public IpvVentaRegistroPK() {
    }

    public IpvVentaRegistroPK(Date ventafecha, String productoVentapCod) {
        this.ventafecha = ventafecha;
        this.productoVentapCod = productoVentapCod;
    }

    public Date getVentafecha() {
        return ventafecha;
    }

    public void setVentafecha(Date ventafecha) {
        this.ventafecha = ventafecha;
    }

    public String getProductoVentapCod() {
        return productoVentapCod;
    }

    public void setProductoVentapCod(String productoVentapCod) {
        this.productoVentapCod = productoVentapCod;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ventafecha != null ? ventafecha.hashCode() : 0);
        hash += (productoVentapCod != null ? productoVentapCod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IpvVentaRegistroPK)) {
            return false;
        }
        IpvVentaRegistroPK other = (IpvVentaRegistroPK) object;
        if ((this.ventafecha == null && other.ventafecha != null) || (this.ventafecha != null && !this.ventafecha.equals(other.ventafecha))) {
            return false;
        }
        if ((this.productoVentapCod == null && other.productoVentapCod != null) || (this.productoVentapCod != null && !this.productoVentapCod.equals(other.productoVentapCod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restManager.persistencia.models.IpvVentaRegistroPK[ ventafecha=" + ventafecha + ", productoVentapCod=" + productoVentapCod + " ]";
    }

}
