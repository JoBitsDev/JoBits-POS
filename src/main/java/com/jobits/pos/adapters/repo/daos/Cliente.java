/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jobits.pos.adapters.repo.daos;

import com.jobits.pos.domain.models.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * FirstDream
 * @author Jorge
 * 
 */
@Entity
@Table(name = "cliente")
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByCodCliente", query = "SELECT c FROM Cliente c WHERE c.codCliente = :codCliente"),
    @NamedQuery(name = "Cliente.findByNombreCliente", query = "SELECT c FROM Cliente c WHERE c.nombreCliente = :nombreCliente"),
    @NamedQuery(name = "Cliente.findByApellidosCliente", query = "SELECT c FROM Cliente c WHERE c.apellidosCliente = :apellidosCliente"),
    @NamedQuery(name = "Cliente.findByDireccionCliente", query = "SELECT c FROM Cliente c WHERE c.direccionCliente = :direccionCliente"),
    @NamedQuery(name = "Cliente.findByTelefonoCliente", query = "SELECT c FROM Cliente c WHERE c.telefonoCliente = :telefonoCliente"),
    @NamedQuery(name = "Cliente.findByTipoCliente", query = "SELECT c FROM Cliente c WHERE c.tipoCliente = :tipoCliente"),
    @NamedQuery(name = "Cliente.findByFechanacCliente", query = "SELECT c FROM Cliente c WHERE c.fechanacCliente = :fechanacCliente"),
    @NamedQuery(name = "Cliente.findByObservacionesCliente", query = "SELECT c FROM Cliente c WHERE c.observacionesCliente = :observacionesCliente")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cod_cliente")
    private String codCliente;
    @Basic(optional = false)
    @Column(name = "nombre_cliente")
    private String nombreCliente;
    @Basic(optional = false)
    @Column(name = "apellidos_cliente")
    private String apellidosCliente;
    @Column(name = "direccion_cliente")
    private String direccionCliente;
    @Column(name = "telefono_cliente")
    private Integer telefonoCliente;
    @Column(name = "tipo_cliente")
    private String tipoCliente;
    @Column(name = "fechanac_cliente")
    @Temporal(TemporalType.DATE)
    private Date fechanacCliente;
    @Column(name = "observaciones_cliente")
    private String observacionesCliente;
    @OneToMany(mappedBy = "clientecodCliente")
    private List<Orden> ordenList;

    public Cliente() {
    }

    public Cliente(String codCliente) {
        this.codCliente = codCliente;
    }

    public Cliente(String codCliente, String nombreCliente, String apellidosCliente) {
        this.codCliente = codCliente;
        this.nombreCliente = nombreCliente;
        this.apellidosCliente = apellidosCliente;
    }

    public String getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(String codCliente) {
        this.codCliente = codCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidosCliente() {
        return apellidosCliente;
    }

    public void setApellidosCliente(String apellidosCliente) {
        this.apellidosCliente = apellidosCliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public Integer getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(Integer telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public Date getFechanacCliente() {
        return fechanacCliente;
    }

    public void setFechanacCliente(Date fechanacCliente) {
        this.fechanacCliente = fechanacCliente;
    }

    public String getObservacionesCliente() {
        return observacionesCliente;
    }

    public void setObservacionesCliente(String observacionesCliente) {
        this.observacionesCliente = observacionesCliente;
    }

    public List<Orden> getOrdenList() {
        return ordenList;
    }

    public void setOrdenList(List<Orden> ordenList) {
        this.ordenList = ordenList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codCliente != null ? codCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.codCliente == null && other.codCliente != null) || (this.codCliente != null && !this.codCliente.equals(other.codCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restManager.persistencia.Cliente[ codCliente=" + codCliente + " ]";
    }

}
