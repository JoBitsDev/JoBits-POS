/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restManager.persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
    @NamedQuery(name = "Cocina.findAll", query = "SELECT c FROM Cocina c"),
    @NamedQuery(name = "Cocina.findByCodCocina", query = "SELECT c FROM Cocina c WHERE c.codCocina = :codCocina"),
    @NamedQuery(name = "Cocina.findByNombreCocina", query = "SELECT c FROM Cocina c WHERE c.nombreCocina = :nombreCocina")})
public class Cocina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cod_cocina")
    private String codCocina;
    @Basic(optional = false)
    @Column(name = "nombre_cocina")
    private String nombreCocina;
    @OneToMany(mappedBy = "cocinacodCocina")
    private List<ProductoVenta> productoVentaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cocina")
    private List<Ipv> ipvList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cocinacodCocina")
    private List<Impresora> impresoraList;

    public Cocina() {
    }

    public Cocina(String codCocina) {
        this.codCocina = codCocina;
    }

    public Cocina(String codCocina, String nombreCocina) {
        this.codCocina = codCocina;
        this.nombreCocina = nombreCocina;
    }

    public String getCodCocina() {
        return codCocina;
    }

    public void setCodCocina(String codCocina) {
        this.codCocina = codCocina;
    }

    public String getNombreCocina() {
        return nombreCocina;
    }

    public void setNombreCocina(String nombreCocina) {
        this.nombreCocina = nombreCocina;
    }

    public List<ProductoVenta> getProductoVentaList() {
        return productoVentaList;
    }

    public void setProductoVentaList(List<ProductoVenta> productoVentaList) {
        this.productoVentaList = productoVentaList;
    }

    public List<Ipv> getIpvList() {
        return ipvList;
    }

    public void setIpvList(List<Ipv> ipvList) {
        this.ipvList = ipvList;
    }

    public List<Impresora> getImpresoraList() {
        return impresoraList;
    }

    public void setImpresoraList(List<Impresora> impresoraList) {
        this.impresoraList = impresoraList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codCocina != null ? codCocina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cocina)) {
            return false;
        }
        Cocina other = (Cocina) object;
        if ((this.codCocina == null && other.codCocina != null) || (this.codCocina != null && !this.codCocina.equals(other.codCocina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreCocina+" ("+codCocina+")";
    }

}
