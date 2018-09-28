/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restManager.persistencia;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * FirstDream
 * @author Jorge
 * 
 */
@Embeddable
public class InsumoFichaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "insumocod_insumo")
    private String insumocodInsumo;
    @Basic(optional = false)
    @Column(name = "fichaid_ficha")
    private int fichaidFicha;

    public InsumoFichaPK() {
    }

    public InsumoFichaPK(String insumocodInsumo, int fichaidFicha) {
        this.insumocodInsumo = insumocodInsumo;
        this.fichaidFicha = fichaidFicha;
    }

    public String getInsumocodInsumo() {
        return insumocodInsumo;
    }

    public void setInsumocodInsumo(String insumocodInsumo) {
        this.insumocodInsumo = insumocodInsumo;
    }

    public int getFichaidFicha() {
        return fichaidFicha;
    }

    public void setFichaidFicha(int fichaidFicha) {
        this.fichaidFicha = fichaidFicha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (insumocodInsumo != null ? insumocodInsumo.hashCode() : 0);
        hash += (int) fichaidFicha;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InsumoFichaPK)) {
            return false;
        }
        InsumoFichaPK other = (InsumoFichaPK) object;
        if ((this.insumocodInsumo == null && other.insumocodInsumo != null) || (this.insumocodInsumo != null && !this.insumocodInsumo.equals(other.insumocodInsumo))) {
            return false;
        }
        if (this.fichaidFicha != other.fichaidFicha) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restManager.persistencia.InsumoFichaPK[ insumocodInsumo=" + insumocodInsumo + ", fichaidFicha=" + fichaidFicha + " ]";
    }

}
