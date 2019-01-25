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
public class TransaccionSalidaPK implements Serializable {

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
    @Column(name = "ipv_registroipvcocinacod_cocina")
    private String ipvRegistroipvcocinacodCocina;

    public TransaccionSalidaPK() {
    }

    public TransaccionSalidaPK(String transaccioninsumocodInsumo, Date transaccionfecha, Date transaccionhora, String ipvRegistroipvcocinacodCocina) {
        this.transaccioninsumocodInsumo = transaccioninsumocodInsumo;
        this.transaccionfecha = transaccionfecha;
        this.transaccionhora = transaccionhora;
        this.ipvRegistroipvcocinacodCocina = ipvRegistroipvcocinacodCocina;
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

    public String getIpvRegistroipvcocinacodCocina() {
        return ipvRegistroipvcocinacodCocina;
    }

    public void setIpvRegistroipvcocinacodCocina(String ipvRegistroipvcocinacodCocina) {
        this.ipvRegistroipvcocinacodCocina = ipvRegistroipvcocinacodCocina;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transaccioninsumocodInsumo != null ? transaccioninsumocodInsumo.hashCode() : 0);
        hash += (transaccionfecha != null ? transaccionfecha.hashCode() : 0);
        hash += (transaccionhora != null ? transaccionhora.hashCode() : 0);
        hash += (ipvRegistroipvcocinacodCocina != null ? ipvRegistroipvcocinacodCocina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransaccionSalidaPK)) {
            return false;
        }
        TransaccionSalidaPK other = (TransaccionSalidaPK) object;
        if ((this.transaccioninsumocodInsumo == null && other.transaccioninsumocodInsumo != null) || (this.transaccioninsumocodInsumo != null && !this.transaccioninsumocodInsumo.equals(other.transaccioninsumocodInsumo))) {
            return false;
        }
        if ((this.transaccionfecha == null && other.transaccionfecha != null) || (this.transaccionfecha != null && !this.transaccionfecha.equals(other.transaccionfecha))) {
            return false;
        }
        if ((this.transaccionhora == null && other.transaccionhora != null) || (this.transaccionhora != null && !this.transaccionhora.equals(other.transaccionhora))) {
            return false;
        }
        if ((this.ipvRegistroipvcocinacodCocina == null && other.ipvRegistroipvcocinacodCocina != null) || (this.ipvRegistroipvcocinacodCocina != null && !this.ipvRegistroipvcocinacodCocina.equals(other.ipvRegistroipvcocinacodCocina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restManager.persistencia.TransaccionSalidaPK[ transaccioninsumocodInsumo=" + transaccioninsumocodInsumo + ", transaccionfecha=" + transaccionfecha + ", transaccionhora=" + transaccionhora + ", ipvRegistroipvcocinacodCocina=" + ipvRegistroipvcocinacodCocina + " ]";
    }

}
