/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restManager.persistencia;

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
@Embeddable
public class TransaccionPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "insumocod_insumo")
    private String insumocodInsumo;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;

    public TransaccionPK() {
    }

    public TransaccionPK(String insumocodInsumo, Date fecha, Date hora) {
        this.insumocodInsumo = insumocodInsumo;
        this.fecha = fecha;
        this.hora = hora;
    }

    public String getInsumocodInsumo() {
        return insumocodInsumo;
    }

    public void setInsumocodInsumo(String insumocodInsumo) {
        this.insumocodInsumo = insumocodInsumo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (insumocodInsumo != null ? insumocodInsumo.hashCode() : 0);
        hash += (fecha != null ? fecha.hashCode() : 0);
        hash += (hora != null ? hora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransaccionPK)) {
            return false;
        }
        TransaccionPK other = (TransaccionPK) object;
        if ((this.insumocodInsumo == null && other.insumocodInsumo != null) || (this.insumocodInsumo != null && !this.insumocodInsumo.equals(other.insumocodInsumo))) {
            return false;
        }
        if ((this.fecha == null && other.fecha != null) || (this.fecha != null && !this.fecha.equals(other.fecha))) {
            return false;
        }
        if ((this.hora == null && other.hora != null) || (this.hora != null && !this.hora.equals(other.hora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restManager.persistencia.TransaccionPK[ insumocodInsumo=" + insumocodInsumo + ", fecha=" + fecha + ", hora=" + hora + " ]";
    }

}
