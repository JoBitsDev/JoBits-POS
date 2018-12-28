/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restManager.persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * FirstDream
 * @author Jorge
 * 
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Ipv.findAll", query = "SELECT i FROM Ipv i"),
    @NamedQuery(name = "Ipv.findByInsumocodInsumo", query = "SELECT i FROM Ipv i WHERE i.ipvPK.insumocodInsumo = :insumocodInsumo"),
    @NamedQuery(name = "Ipv.findByCocinacodCocina", query = "SELECT i FROM Ipv i WHERE i.ipvPK.cocinacodCocina = :cocinacodCocina"),
    @NamedQuery(name = "Ipv.findByCantidad", query = "SELECT i FROM Ipv i WHERE i.cantidad = :cantidad")})
public class Ipv implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected IpvPK ipvPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Float cantidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ipv")
    private List<IpvRegistro> ipvRegistroList;
    @JoinColumn(name = "cocinacod_cocina", referencedColumnName = "cod_cocina", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cocina cocina;
    @JoinColumn(name = "insumocod_insumo", referencedColumnName = "cod_insumo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Insumo insumo;

    public Ipv() {
    }

    public Ipv(IpvPK ipvPK) {
        this.ipvPK = ipvPK;
    }

    public Ipv(String insumocodInsumo, String cocinacodCocina) {
        this.ipvPK = new IpvPK(insumocodInsumo, cocinacodCocina);
    }

    public IpvPK getIpvPK() {
        return ipvPK;
    }

    public void setIpvPK(IpvPK ipvPK) {
        this.ipvPK = ipvPK;
    }

    public Float getCantidad() {
        return cantidad;
    }

    public void setCantidad(Float cantidad) {
        this.cantidad = cantidad;
    }

    public List<IpvRegistro> getIpvRegistroList() {
        return ipvRegistroList;
    }

    public void setIpvRegistroList(List<IpvRegistro> ipvRegistroList) {
        this.ipvRegistroList = ipvRegistroList;
    }

    public Cocina getCocina() {
        return cocina;
    }

    public void setCocina(Cocina cocina) {
        this.cocina = cocina;
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
        hash += (ipvPK != null ? ipvPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ipv)) {
            return false;
        }
        Ipv other = (Ipv) object;
        if ((this.ipvPK == null && other.ipvPK != null) || (this.ipvPK != null && !this.ipvPK.equals(other.ipvPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restManager.persistencia.Ipv[ ipvPK=" + ipvPK + " ]";
    }

}
