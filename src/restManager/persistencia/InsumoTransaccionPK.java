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
public class InsumoTransaccionPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "insumocod_insumo")
    private String insumocodInsumo;
    @Basic(optional = false)
    @Column(name = "transaccionfecha_transaccion")
    @Temporal(TemporalType.DATE)
    private Date transaccionfechaTransaccion;
    @Basic(optional = false)
    @Column(name = "hora_transaccion")
    @Temporal(TemporalType.TIME)
    private Date horaTransaccion;

    public InsumoTransaccionPK() {
    }

    public InsumoTransaccionPK(String insumocodInsumo, Date transaccionfechaTransaccion, Date horaTransaccion) {
        this.insumocodInsumo = insumocodInsumo;
        this.transaccionfechaTransaccion = transaccionfechaTransaccion;
        this.horaTransaccion = horaTransaccion;
    }

    public String getInsumocodInsumo() {
        return insumocodInsumo;
    }

    public void setInsumocodInsumo(String insumocodInsumo) {
        this.insumocodInsumo = insumocodInsumo;
    }

    public Date getTransaccionfechaTransaccion() {
        return transaccionfechaTransaccion;
    }

    public void setTransaccionfechaTransaccion(Date transaccionfechaTransaccion) {
        this.transaccionfechaTransaccion = transaccionfechaTransaccion;
    }

    public Date getHoraTransaccion() {
        return horaTransaccion;
    }

    public void setHoraTransaccion(Date horaTransaccion) {
        this.horaTransaccion = horaTransaccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (insumocodInsumo != null ? insumocodInsumo.hashCode() : 0);
        hash += (transaccionfechaTransaccion != null ? transaccionfechaTransaccion.hashCode() : 0);
        hash += (horaTransaccion != null ? horaTransaccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InsumoTransaccionPK)) {
            return false;
        }
        InsumoTransaccionPK other = (InsumoTransaccionPK) object;
        if ((this.insumocodInsumo == null && other.insumocodInsumo != null) || (this.insumocodInsumo != null && !this.insumocodInsumo.equals(other.insumocodInsumo))) {
            return false;
        }
        if ((this.transaccionfechaTransaccion == null && other.transaccionfechaTransaccion != null) || (this.transaccionfechaTransaccion != null && !this.transaccionfechaTransaccion.equals(other.transaccionfechaTransaccion))) {
            return false;
        }
        if ((this.horaTransaccion == null && other.horaTransaccion != null) || (this.horaTransaccion != null && !this.horaTransaccion.equals(other.horaTransaccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restManager.persistencia.InsumoTransaccionPK[ insumocodInsumo=" + insumocodInsumo + ", transaccionfechaTransaccion=" + transaccionfechaTransaccion + ", horaTransaccion=" + horaTransaccion + " ]";
    }

}
