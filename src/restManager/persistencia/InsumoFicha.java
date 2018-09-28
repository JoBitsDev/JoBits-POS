/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restManager.persistencia;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * FirstDream
 * @author Jorge
 * 
 */
@Entity
@Table(name = "insumo_ficha")
@NamedQueries({
    @NamedQuery(name = "InsumoFicha.findAll", query = "SELECT i FROM InsumoFicha i")
    , @NamedQuery(name = "InsumoFicha.findByInsumocodInsumo", query = "SELECT i FROM InsumoFicha i WHERE i.insumoFichaPK.insumocodInsumo = :insumocodInsumo")
    , @NamedQuery(name = "InsumoFicha.findByFichaidFicha", query = "SELECT i FROM InsumoFicha i WHERE i.insumoFichaPK.fichaidFicha = :fichaidFicha")
    , @NamedQuery(name = "InsumoFicha.findByCantidadMovida", query = "SELECT i FROM InsumoFicha i WHERE i.cantidadMovida = :cantidadMovida")
    , @NamedQuery(name = "InsumoFicha.findByValorTotalMonetario", query = "SELECT i FROM InsumoFicha i WHERE i.valorTotalMonetario = :valorTotalMonetario")})
public class InsumoFicha implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InsumoFichaPK insumoFichaPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cantidad_movida")
    private Float cantidadMovida;
    @Column(name = "valor_total_monetario")
    private Float valorTotalMonetario;
    @JoinColumn(name = "fichaid_ficha", referencedColumnName = "id_ficha", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ficha ficha;
    @JoinColumn(name = "insumocod_insumo", referencedColumnName = "cod_insumo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Insumo insumo;

    public InsumoFicha() {
    }

    public InsumoFicha(InsumoFichaPK insumoFichaPK) {
        this.insumoFichaPK = insumoFichaPK;
    }

    public InsumoFicha(String insumocodInsumo, int fichaidFicha) {
        this.insumoFichaPK = new InsumoFichaPK(insumocodInsumo, fichaidFicha);
    }

    public InsumoFichaPK getInsumoFichaPK() {
        return insumoFichaPK;
    }

    public void setInsumoFichaPK(InsumoFichaPK insumoFichaPK) {
        this.insumoFichaPK = insumoFichaPK;
    }

    public Float getCantidadMovida() {
        return cantidadMovida;
    }

    public void setCantidadMovida(Float cantidadMovida) {
        this.cantidadMovida = cantidadMovida;
    }

    public Float getValorTotalMonetario() {
        return valorTotalMonetario;
    }

    public void setValorTotalMonetario(Float valorTotalMonetario) {
        this.valorTotalMonetario = valorTotalMonetario;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (insumoFichaPK != null ? insumoFichaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InsumoFicha)) {
            return false;
        }
        InsumoFicha other = (InsumoFicha) object;
        if ((this.insumoFichaPK == null && other.insumoFichaPK != null) || (this.insumoFichaPK != null && !this.insumoFichaPK.equals(other.insumoFichaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restManager.persistencia.InsumoFicha[ insumoFichaPK=" + insumoFichaPK + " ]";
    }

}
