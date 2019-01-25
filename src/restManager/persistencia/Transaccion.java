/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restManager.persistencia;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * FirstDream
 * @author Jorge
 * 
 */
@Entity
@Table(name = "transaccion")
@NamedQueries({
    @NamedQuery(name = "Transaccion.findAll", query = "SELECT t FROM Transaccion t"),
    @NamedQuery(name = "Transaccion.findByInsumocodInsumo", query = "SELECT t FROM Transaccion t WHERE t.transaccionPK.insumocodInsumo = :insumocodInsumo"),
    @NamedQuery(name = "Transaccion.findByFecha", query = "SELECT t FROM Transaccion t WHERE t.transaccionPK.fecha = :fecha"),
    @NamedQuery(name = "Transaccion.findByHora", query = "SELECT t FROM Transaccion t WHERE t.transaccionPK.hora = :hora"),
    @NamedQuery(name = "Transaccion.findByCantidad", query = "SELECT t FROM Transaccion t WHERE t.cantidad = :cantidad"),
    @NamedQuery(name = "Transaccion.findByDescripcion", query = "SELECT t FROM Transaccion t WHERE t.descripcion = :descripcion")})
public class Transaccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TransaccionPK transaccionPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cantidad")
    private Float cantidad;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transaccion")
    private List<TransaccionSalida> transaccionSalidaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transaccion")
    private List<TransaccionEntrada> transaccionEntradaList;
    @JoinColumn(name = "insumocod_insumo", referencedColumnName = "cod_insumo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Insumo insumo;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "transaccion")
    private TransaccionMerma transaccionMerma;

    public Transaccion() {
    }

    public Transaccion(TransaccionPK transaccionPK) {
        this.transaccionPK = transaccionPK;
    }

    public Transaccion(String insumocodInsumo, Date fecha, Date hora) {
        this.transaccionPK = new TransaccionPK(insumocodInsumo, fecha, hora);
    }

    public TransaccionPK getTransaccionPK() {
        return transaccionPK;
    }

    public void setTransaccionPK(TransaccionPK transaccionPK) {
        this.transaccionPK = transaccionPK;
    }

    public Float getCantidad() {
        return cantidad;
    }

    public void setCantidad(Float cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<TransaccionSalida> getTransaccionSalidaList() {
        return transaccionSalidaList;
    }

    public void setTransaccionSalidaList(List<TransaccionSalida> transaccionSalidaList) {
        this.transaccionSalidaList = transaccionSalidaList;
    }

    public List<TransaccionEntrada> getTransaccionEntradaList() {
        return transaccionEntradaList;
    }

    public void setTransaccionEntradaList(List<TransaccionEntrada> transaccionEntradaList) {
        this.transaccionEntradaList = transaccionEntradaList;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public TransaccionMerma getTransaccionMerma() {
        return transaccionMerma;
    }

    public void setTransaccionMerma(TransaccionMerma transaccionMerma) {
        this.transaccionMerma = transaccionMerma;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transaccionPK != null ? transaccionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transaccion)) {
            return false;
        }
        Transaccion other = (Transaccion) object;
        if ((this.transaccionPK == null && other.transaccionPK != null) || (this.transaccionPK != null && !this.transaccionPK.equals(other.transaccionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restManager.persistencia.Transaccion[ transaccionPK=" + transaccionPK + " ]";
    }

}
