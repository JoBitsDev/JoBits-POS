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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
@Entity
@Table(name = "operacion")
@NamedQueries({
    @NamedQuery(name = "Operacion.findAll", query = "SELECT o FROM Operacion o"),
    @NamedQuery(name = "Operacion.findByNoOperacion", query = "SELECT o FROM Operacion o WHERE o.noOperacion = :noOperacion"),
    @NamedQuery(name = "Operacion.findByFecha", query = "SELECT o FROM Operacion o WHERE o.fecha = :fecha"),
    @NamedQuery(name = "Operacion.findByRecibidoPor", query = "SELECT o FROM Operacion o WHERE o.recibidoPor = :recibidoPor"),
    @NamedQuery(name = "Operacion.findByHora", query = "SELECT o FROM Operacion o WHERE o.hora = :hora"),
    @NamedQuery(name = "Operacion.findByNoRecibo", query = "SELECT o FROM Operacion o WHERE o.noRecibo = :noRecibo")})
public class Operacion implements Serializable {

    @OneToMany(mappedBy = "operacionnoOperacion")
    private List<Transaccion> transaccionList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "no_operacion")
    private Integer noOperacion;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "recibido_por")
    private String recibidoPor;
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Column(name = "no_recibo")
    private String noRecibo;
    @JoinColumn(name = "almacen", referencedColumnName = "cod_almacen")
    @ManyToOne(optional = false)
    private Almacen almacen;
    @JoinColumn(name = "proveedor", referencedColumnName = "no_proveedor")
    @ManyToOne(optional = false)
    private Proveedor proveedor;

    public Operacion() {
    }

    public Operacion(Integer noOperacion) {
        this.noOperacion = noOperacion;
    }

    public Integer getNoOperacion() {
        return noOperacion;
    }

    public void setNoOperacion(Integer noOperacion) {
        this.noOperacion = noOperacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getRecibidoPor() {
        return recibidoPor;
    }

    public void setRecibidoPor(String recibidoPor) {
        this.recibidoPor = recibidoPor;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getNoRecibo() {
        return noRecibo;
    }

    public void setNoRecibo(String noRecibo) {
        this.noRecibo = noRecibo;
    }

    public Almacen getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (noOperacion != null ? noOperacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Operacion)) {
            return false;
        }
        Operacion other = (Operacion) object;
        if ((this.noOperacion == null && other.noOperacion != null) || (this.noOperacion != null && !this.noOperacion.equals(other.noOperacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restManager.persistencia.Operacion[ noOperacion=" + noOperacion + " ]";
    }

    public List<Transaccion> getTransaccionList() {
        return transaccionList;
    }

    public void setTransaccionList(List<Transaccion> transaccionList) {
        this.transaccionList = transaccionList;
    }

}
