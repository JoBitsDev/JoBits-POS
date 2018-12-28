/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restManager.persistencia;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
@Table(name = "almacen_registro")
@NamedQueries({
    @NamedQuery(name = "AlmacenRegistro.findAll", query = "SELECT a FROM AlmacenRegistro a"),
    @NamedQuery(name = "AlmacenRegistro.findByFecha", query = "SELECT a FROM AlmacenRegistro a WHERE a.almacenRegistroPK.fecha = :fecha"),
    @NamedQuery(name = "AlmacenRegistro.findByHora", query = "SELECT a FROM AlmacenRegistro a WHERE a.almacenRegistroPK.hora = :hora"),
    @NamedQuery(name = "AlmacenRegistro.findByOperacion", query = "SELECT a FROM AlmacenRegistro a WHERE a.operacion = :operacion"),
    @NamedQuery(name = "AlmacenRegistro.findByCantidad", query = "SELECT a FROM AlmacenRegistro a WHERE a.cantidad = :cantidad"),
    @NamedQuery(name = "AlmacenRegistro.findByPrecio", query = "SELECT a FROM AlmacenRegistro a WHERE a.precio = :precio")})
public class AlmacenRegistro implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AlmacenRegistroPK almacenRegistroPK;
    @Basic(optional = false)
    private String operacion;
    @Basic(optional = false)
    private float cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Float precio;
    @JoinColumns({
        @JoinColumn(name = "insumo_almaceninsumocod_insumo", referencedColumnName = "insumocod_insumo"),
        @JoinColumn(name = "insumo_almacenalmacencod_almacen", referencedColumnName = "almacencod_almacen")})
    @ManyToOne(optional = false)
    private InsumoAlmacen insumoAlmacen;

    public AlmacenRegistro() {
    }

    public AlmacenRegistro(AlmacenRegistroPK almacenRegistroPK) {
        this.almacenRegistroPK = almacenRegistroPK;
    }

    public AlmacenRegistro(AlmacenRegistroPK almacenRegistroPK, String operacion, float cantidad) {
        this.almacenRegistroPK = almacenRegistroPK;
        this.operacion = operacion;
        this.cantidad = cantidad;
    }

    public AlmacenRegistro(Date fecha, Date hora) {
        this.almacenRegistroPK = new AlmacenRegistroPK(fecha, hora);
    }

    public AlmacenRegistroPK getAlmacenRegistroPK() {
        return almacenRegistroPK;
    }

    public void setAlmacenRegistroPK(AlmacenRegistroPK almacenRegistroPK) {
        this.almacenRegistroPK = almacenRegistroPK;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public InsumoAlmacen getInsumoAlmacen() {
        return insumoAlmacen;
    }

    public void setInsumoAlmacen(InsumoAlmacen insumoAlmacen) {
        this.insumoAlmacen = insumoAlmacen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (almacenRegistroPK != null ? almacenRegistroPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AlmacenRegistro)) {
            return false;
        }
        AlmacenRegistro other = (AlmacenRegistro) object;
        if ((this.almacenRegistroPK == null && other.almacenRegistroPK != null) || (this.almacenRegistroPK != null && !this.almacenRegistroPK.equals(other.almacenRegistroPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restManager.persistencia.AlmacenRegistro[ almacenRegistroPK=" + almacenRegistroPK + " ]";
    }

}
