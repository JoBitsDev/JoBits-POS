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
public class TransaccionEntradaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "transaccioninsumocod_insumo")
    private String transaccioninsumocodInsumo;
    @Basic(optional = false)
    @Column(name = "transaccionfecha")
    @Temporal(TemporalType.DATE)
    private Date transaccionfecha;
    @Basic(optional = false)
    @Column(name = "transaccionhora")
    @Temporal(TemporalType.TIME)
    private Date transaccionhora;
    @Basic(optional = false)
    @Column(name = "transaccionalmacencod_almacen")
    private String transaccionalmacencodAlmacen;

    public TransaccionEntradaPK() {
    }

    public TransaccionEntradaPK(String transaccioninsumocodInsumo, Date transaccionfecha, Date transaccionhora, String transaccionalmacencodAlmacen) {
        this.transaccioninsumocodInsumo = transaccioninsumocodInsumo;
        this.transaccionfecha = transaccionfecha;
        this.transaccionhora = transaccionhora;
        this.transaccionalmacencodAlmacen = transaccionalmacencodAlmacen;
    }

    public String getTransaccioninsumocodInsumo() {
        return transaccioninsumocodInsumo;
    }

    public void setTransaccioninsumocodInsumo(String transaccioninsumocodInsumo) {
        this.transaccioninsumocodInsumo = transaccioninsumocodInsumo;
    }

    public Date getTransaccionfecha() {
        return transaccionfecha;
    }

    public void setTransaccionfecha(Date transaccionfecha) {
        this.transaccionfecha = transaccionfecha;
    }

    public Date getTransaccionhora() {
        return transaccionhora;
    }

    public void setTransaccionhora(Date transaccionhora) {
        this.transaccionhora = transaccionhora;
    }

    public String getTransaccionalmacencodAlmacen() {
        return transaccionalmacencodAlmacen;
    }

    public void setTransaccionalmacencodAlmacen(String transaccionalmacencodAlmacen) {
        this.transaccionalmacencodAlmacen = transaccionalmacencodAlmacen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transaccioninsumocodInsumo != null ? transaccioninsumocodInsumo.hashCode() : 0);
        hash += (transaccionfecha != null ? transaccionfecha.hashCode() : 0);
        hash += (transaccionhora != null ? transaccionhora.hashCode() : 0);
        hash += (transaccionalmacencodAlmacen != null ? transaccionalmacencodAlmacen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransaccionEntradaPK)) {
            return false;
        }
        TransaccionEntradaPK other = (TransaccionEntradaPK) object;
        if ((this.transaccioninsumocodInsumo == null && other.transaccioninsumocodInsumo != null) || (this.transaccioninsumocodInsumo != null && !this.transaccioninsumocodInsumo.equals(other.transaccioninsumocodInsumo))) {
            return false;
        }
        if ((this.transaccionfecha == null && other.transaccionfecha != null) || (this.transaccionfecha != null && !this.transaccionfecha.equals(other.transaccionfecha))) {
            return false;
        }
        if ((this.transaccionhora == null && other.transaccionhora != null) || (this.transaccionhora != null && !this.transaccionhora.equals(other.transaccionhora))) {
            return false;
        }
        if ((this.transaccionalmacencodAlmacen == null && other.transaccionalmacencodAlmacen != null) || (this.transaccionalmacencodAlmacen != null && !this.transaccionalmacencodAlmacen.equals(other.transaccionalmacencodAlmacen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restManager.persistencia.TransaccionEntradaPK[ transaccioninsumocodInsumo=" + transaccioninsumocodInsumo + ", transaccionfecha=" + transaccionfecha + ", transaccionhora=" + transaccionhora + ", transaccionalmacencodAlmacen=" + transaccionalmacencodAlmacen + " ]";
    }

}
