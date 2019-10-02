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
public class TransaccionTraspasoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "transaccioninsumocod_insumo")
    private String transaccioninsumocodInsumo;
    @Basic(optional = false)
    @Column(name = "transaccionalmacencod_almacen")
    private String transaccionalmacencodAlmacen;
    @Basic(optional = false)
    @Column(name = "transaccionfecha")
    @Temporal(TemporalType.DATE)
    private Date transaccionfecha;
    @Basic(optional = false)
    @Column(name = "transaccionhora")
    @Temporal(TemporalType.TIME)
    private Date transaccionhora;

    public TransaccionTraspasoPK() {
    }

    public TransaccionTraspasoPK(String transaccioninsumocodInsumo, String transaccionalmacencodAlmacen, Date transaccionfecha, Date transaccionhora) {
        this.transaccioninsumocodInsumo = transaccioninsumocodInsumo;
        this.transaccionalmacencodAlmacen = transaccionalmacencodAlmacen;
        this.transaccionfecha = transaccionfecha;
        this.transaccionhora = transaccionhora;
    }

    public String getTransaccioninsumocodInsumo() {
        return transaccioninsumocodInsumo;
    }

    public void setTransaccioninsumocodInsumo(String transaccioninsumocodInsumo) {
        this.transaccioninsumocodInsumo = transaccioninsumocodInsumo;
    }

    public String getTransaccionalmacencodAlmacen() {
        return transaccionalmacencodAlmacen;
    }

    public void setTransaccionalmacencodAlmacen(String transaccionalmacencodAlmacen) {
        this.transaccionalmacencodAlmacen = transaccionalmacencodAlmacen;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transaccioninsumocodInsumo != null ? transaccioninsumocodInsumo.hashCode() : 0);
        hash += (transaccionalmacencodAlmacen != null ? transaccionalmacencodAlmacen.hashCode() : 0);
        hash += (transaccionfecha != null ? transaccionfecha.hashCode() : 0);
        hash += (transaccionhora != null ? transaccionhora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransaccionTraspasoPK)) {
            return false;
        }
        TransaccionTraspasoPK other = (TransaccionTraspasoPK) object;
        if ((this.transaccioninsumocodInsumo == null && other.transaccioninsumocodInsumo != null) || (this.transaccioninsumocodInsumo != null && !this.transaccioninsumocodInsumo.equals(other.transaccioninsumocodInsumo))) {
            return false;
        }
        if ((this.transaccionalmacencodAlmacen == null && other.transaccionalmacencodAlmacen != null) || (this.transaccionalmacencodAlmacen != null && !this.transaccionalmacencodAlmacen.equals(other.transaccionalmacencodAlmacen))) {
            return false;
        }
        if ((this.transaccionfecha == null && other.transaccionfecha != null) || (this.transaccionfecha != null && !this.transaccionfecha.equals(other.transaccionfecha))) {
            return false;
        }
        if ((this.transaccionhora == null && other.transaccionhora != null) || (this.transaccionhora != null && !this.transaccionhora.equals(other.transaccionhora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restManager.persistencia.TransaccionTraspasoPK[ transaccioninsumocodInsumo=" + transaccioninsumocodInsumo + ", transaccionalmacencodAlmacen=" + transaccionalmacencodAlmacen + ", transaccionfecha=" + transaccionfecha + ", transaccionhora=" + transaccionhora + " ]";
    }

}
