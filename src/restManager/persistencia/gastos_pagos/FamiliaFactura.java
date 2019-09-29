/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restManager.persistencia.gastos_pagos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "familia_factura")
@NamedQueries({
    @NamedQuery(name = "FamiliaFactura.findAll", query = "SELECT f FROM FamiliaFactura f"),
    @NamedQuery(name = "FamiliaFactura.findByIdFamilia", query = "SELECT f FROM FamiliaFactura f WHERE f.idFamilia = :idFamilia"),
    @NamedQuery(name = "FamiliaFactura.findByNombre", query = "SELECT f FROM FamiliaFactura f WHERE f.nombre = :nombre")})
public class FamiliaFactura implements Serializable {

    @Column(name = "tipo_familia")
    private String tipoFamilia;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_familia")
    private Integer idFamilia;
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "idFamiliaFactura")
    private List<Factura> facturaList;
    @OneToMany(mappedBy = "idFamiliaPadre")
    private List<FamiliaFactura> familiaFacturaList;
    @JoinColumn(name = "id_familia_padre", referencedColumnName = "id_familia")
    @ManyToOne
    private FamiliaFactura idFamiliaPadre;

    public FamiliaFactura() {
    }

    public FamiliaFactura(Integer idFamilia) {
        this.idFamilia = idFamilia;
    }

    public Integer getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(Integer idFamilia) {
        this.idFamilia = idFamilia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Factura> getFacturaList() {
        return facturaList;
    }

    public void setFacturaList(List<Factura> facturaList) {
        this.facturaList = facturaList;
    }

    public List<FamiliaFactura> getFamiliaFacturaList() {
        return familiaFacturaList;
    }

    public void setFamiliaFacturaList(List<FamiliaFactura> familiaFacturaList) {
        this.familiaFacturaList = familiaFacturaList;
    }

    public FamiliaFactura getIdFamiliaPadre() {
        return idFamiliaPadre;
    }

    public void setIdFamiliaPadre(FamiliaFactura idFamiliaPadre) {
        this.idFamiliaPadre = idFamiliaPadre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFamilia != null ? idFamilia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FamiliaFactura)) {
            return false;
        }
        FamiliaFactura other = (FamiliaFactura) object;
        if ((this.idFamilia == null && other.idFamilia != null) || (this.idFamilia != null && !this.idFamilia.equals(other.idFamilia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restManager.persistencia.FamiliaFactura[ idFamilia=" + idFamilia + " ]";
    }

    public String getTipoFamilia() {
        return tipoFamilia;
    }

    public void setTipoFamilia(String tipoFamilia) {
        this.tipoFamilia = tipoFamilia;
    }

}
