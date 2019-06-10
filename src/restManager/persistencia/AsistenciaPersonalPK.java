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
public class AsistenciaPersonalPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "ventafecha")
    @Temporal(TemporalType.DATE)
    private Date ventafecha;
    @Basic(optional = false)
    @Column(name = "personalusuario")
    private String personalusuario;

    public AsistenciaPersonalPK() {
    }

    public AsistenciaPersonalPK(Date ventafecha, String personalusuario) {
        this.ventafecha = ventafecha;
        this.personalusuario = personalusuario;
    }

    public Date getVentafecha() {
        return ventafecha;
    }

    public void setVentafecha(Date ventafecha) {
        this.ventafecha = ventafecha;
    }

    public String getPersonalusuario() {
        return personalusuario;
    }

    public void setPersonalusuario(String personalusuario) {
        this.personalusuario = personalusuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ventafecha != null ? ventafecha.hashCode() : 0);
        hash += (personalusuario != null ? personalusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsistenciaPersonalPK)) {
            return false;
        }
        AsistenciaPersonalPK other = (AsistenciaPersonalPK) object;
        if ((this.ventafecha == null && other.ventafecha != null) || (this.ventafecha != null && !this.ventafecha.equals(other.ventafecha))) {
            return false;
        }
        if ((this.personalusuario == null && other.personalusuario != null) || (this.personalusuario != null && !this.personalusuario.equals(other.personalusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restManager.persistencia.AsistenciaPersonalPK[ ventafecha=" + ventafecha + ", personalusuario=" + personalusuario + " ]";
    }

}
