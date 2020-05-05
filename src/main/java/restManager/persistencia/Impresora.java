/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restManager.persistencia;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "impresora")
@NamedQueries({
    @NamedQuery(name = "Impresora.findAll", query = "SELECT i FROM Impresora i"),
    @NamedQuery(name = "Impresora.findByCodImpresora", query = "SELECT i FROM Impresora i WHERE i.codImpresora = :codImpresora"),
    @NamedQuery(name = "Impresora.findByIpImpresora", query = "SELECT i FROM Impresora i WHERE i.ipImpresora = :ipImpresora"),
    @NamedQuery(name = "Impresora.findByEstaactiva", query = "SELECT i FROM Impresora i WHERE i.estaactiva = :estaactiva"),
    @NamedQuery(name = "Impresora.findByNombreImpresora", query = "SELECT i FROM Impresora i WHERE i.nombreImpresora = :nombreImpresora")})
public class Impresora implements Serializable {

    @JoinColumn(name = "areacod_area", referencedColumnName = "cod_area")
    @ManyToOne
    private Area areacodArea;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cod_impresora")
    private String codImpresora;
    @Basic(optional = false)
    @Column(name = "ip_impresora")
    private String ipImpresora;
    @Column(name = "estaactiva")
    private Boolean estaactiva;
    @Column(name = "nombre_impresora")
    private String nombreImpresora;
    @JoinColumn(name = "cocinacod_cocina", referencedColumnName = "cod_cocina")
    @ManyToOne(optional = false)
    private Cocina cocinacodCocina;

    public Impresora() {
    }

    public Impresora(String codImpresora) {
        this.codImpresora = codImpresora;
    }

    public Impresora(String codImpresora, String ipImpresora) {
        this.codImpresora = codImpresora;
        this.ipImpresora = ipImpresora;
    }

    public String getCodImpresora() {
        return codImpresora;
    }

    public void setCodImpresora(String codImpresora) {
        this.codImpresora = codImpresora;
    }

    public String getIpImpresora() {
        return ipImpresora;
    }

    public void setIpImpresora(String ipImpresora) {
        this.ipImpresora = ipImpresora;
    }

    public Boolean getEstaactiva() {
        return estaactiva;
    }

    public void setEstaactiva(Boolean estaactiva) {
        this.estaactiva = estaactiva;
    }

    public String getNombreImpresora() {
        return nombreImpresora;
    }

    public void setNombreImpresora(String nombreImpresora) {
        this.nombreImpresora = nombreImpresora;
    }

    public Cocina getCocinacodCocina() {
        return cocinacodCocina;
    }

    public void setCocinacodCocina(Cocina cocinacodCocina) {
        this.cocinacodCocina = cocinacodCocina;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codImpresora != null ? codImpresora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Impresora)) {
            return false;
        }
        Impresora other = (Impresora) object;
        if ((this.codImpresora == null && other.codImpresora != null) || (this.codImpresora != null && !this.codImpresora.equals(other.codImpresora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restManager.persistencia.Impresora[ codImpresora=" + codImpresora + " ]";
    }

    public Area getAreacodArea() {
        return areacodArea;
    }

    public void setAreacodArea(Area areacodArea) {
        this.areacodArea = areacodArea;
    }

}
