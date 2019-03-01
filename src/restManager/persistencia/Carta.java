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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * FirstDream
 * @author Jorge
 * 
 */
@Entity
@Table(name = "carta")
@NamedQueries({
    @NamedQuery(name = "Carta.findAll", query = "SELECT c FROM Carta c"),
    @NamedQuery(name = "Carta.findByCodCarta", query = "SELECT c FROM Carta c WHERE c.codCarta = :codCarta"),
    @NamedQuery(name = "Carta.findByNombreCarta", query = "SELECT c FROM Carta c WHERE c.nombreCarta = :nombreCarta"),
    @NamedQuery(name = "Carta.findByMonedaPrincipal", query = "SELECT c FROM Carta c WHERE c.monedaPrincipal = :monedaPrincipal"),
    @NamedQuery(name = "Carta.findByPorcientoPorServicio", query = "SELECT c FROM Carta c WHERE c.porcientoPorServicio = :porcientoPorServicio")})
public class Carta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cod_carta")
    private String codCarta;
    @Basic(optional = false)
    @Column(name = "nombre_carta")
    private String nombreCarta;
    @Column(name = "moneda_principal")
    private String monedaPrincipal;
    @Column(name = "porciento_por_servicio")
    private Integer porcientoPorServicio;
    @JoinTable(name = "carta_area", joinColumns = {
        @JoinColumn(name = "cartacod_carta", referencedColumnName = "cod_carta")}, inverseJoinColumns = {
        @JoinColumn(name = "areacod_area", referencedColumnName = "cod_area")})
    @ManyToMany
    private List<Area> areaList;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "cartacodCarta")
    private List<Seccion> seccionList;

    public Carta() {
    }

    public Carta(String codCarta) {
        this.codCarta = codCarta;
    }

    public Carta(String codCarta, String nombreCarta) {
        this.codCarta = codCarta;
        this.nombreCarta = nombreCarta;
    }

    public String getCodCarta() {
        return codCarta;
    }

    public void setCodCarta(String codCarta) {
        this.codCarta = codCarta;
    }

    public String getNombreCarta() {
        return nombreCarta;
    }

    public void setNombreCarta(String nombreCarta) {
        this.nombreCarta = nombreCarta;
    }

    public String getMonedaPrincipal() {
        return monedaPrincipal;
    }

    public void setMonedaPrincipal(String monedaPrincipal) {
        this.monedaPrincipal = monedaPrincipal;
    }

    public Integer getPorcientoPorServicio() {
        return porcientoPorServicio;
    }

    public void setPorcientoPorServicio(Integer porcientoPorServicio) {
        this.porcientoPorServicio = porcientoPorServicio;
    }

    public List<Area> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<Area> areaList) {
        this.areaList = areaList;
    }

    public List<Seccion> getSeccionList() {
        return seccionList;
    }

    public void setSeccionList(List<Seccion> seccionList) {
        this.seccionList = seccionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codCarta != null ? codCarta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Carta)) {
            return false;
        }
        Carta other = (Carta) object;
        if ((this.codCarta == null && other.codCarta != null) || (this.codCarta != null && !this.codCarta.equals(other.codCarta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "("+getMonedaPrincipal()+")-" + getNombreCarta() + "["+getCodCarta()+"]";
    }

}
