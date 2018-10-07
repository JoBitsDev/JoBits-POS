/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restManager.persistencia;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author Jorge
 * 
 */
@Entity
@Table(name = "transaccion")
@NamedQueries({
    @NamedQuery(name = "Transaccion.findAll", query = "SELECT t FROM Transaccion t")
    , @NamedQuery(name = "Transaccion.findByFechaTransaccion", query = "SELECT t FROM Transaccion t WHERE t.fechaTransaccion = :fechaTransaccion")
    , @NamedQuery(name = "Transaccion.findByValorTotalTransacciones", query = "SELECT t FROM Transaccion t WHERE t.valorTotalTransacciones = :valorTotalTransacciones")
    , @NamedQuery(name = "Transaccion.findByValorMerma", query = "SELECT t FROM Transaccion t WHERE t.valorMerma = :valorMerma")})
public class Transaccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "fecha_transaccion")
    @Temporal(TemporalType.DATE)
    private Date fechaTransaccion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_total_transacciones")
    private Float valorTotalTransacciones;
    @Column(name = "valor_merma")
    private Float valorMerma;
    @JoinColumn(name = "almacencod_almacen", referencedColumnName = "cod_almacen")
    @ManyToOne
    private Almacen almacencodAlmacen;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transaccion")
    private List<InsumoTransaccion> insumoTransaccionList;

    public Transaccion() {
    }

    public Transaccion(Date fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public Date getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(Date fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public Float getValorTotalTransacciones() {
        return valorTotalTransacciones;
    }

    public void setValorTotalTransacciones(Float valorTotalTransacciones) {
        this.valorTotalTransacciones = valorTotalTransacciones;
    }

    public Float getValorMerma() {
        return valorMerma;
    }

    public void setValorMerma(Float valorMerma) {
        this.valorMerma = valorMerma;
    }

    public Almacen getAlmacencodAlmacen() {
        return almacencodAlmacen;
    }

    public void setAlmacencodAlmacen(Almacen almacencodAlmacen) {
        this.almacencodAlmacen = almacencodAlmacen;
    }

    public List<InsumoTransaccion> getInsumoTransaccionList() {
        return insumoTransaccionList;
    }

    public void setInsumoTransaccionList(List<InsumoTransaccion> insumoTransaccionList) {
        this.insumoTransaccionList = insumoTransaccionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fechaTransaccion != null ? fechaTransaccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transaccion)) {
            return false;
        }
        Transaccion other = (Transaccion) object;
        if ((this.fechaTransaccion == null && other.fechaTransaccion != null) || (this.fechaTransaccion != null && !this.fechaTransaccion.equals(other.fechaTransaccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restManager.persistencia.Transaccion[ fechaTransaccion=" + fechaTransaccion + " ]";
    }

}
