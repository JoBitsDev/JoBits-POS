/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restManager.persistencia;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * FirstDream
 * @author Jorge
 * 
 */
@Entity
@Table(name = "insumo_transaccion")
@NamedQueries({
    @NamedQuery(name = "InsumoTransaccion.findAll", query = "SELECT i FROM InsumoTransaccion i")
    , @NamedQuery(name = "InsumoTransaccion.findByInsumocodInsumo", query = "SELECT i FROM InsumoTransaccion i WHERE i.insumoTransaccionPK.insumocodInsumo = :insumocodInsumo")
    , @NamedQuery(name = "InsumoTransaccion.findByTransaccionfechaTransaccion", query = "SELECT i FROM InsumoTransaccion i WHERE i.insumoTransaccionPK.transaccionfechaTransaccion = :transaccionfechaTransaccion")
    , @NamedQuery(name = "InsumoTransaccion.findByCantidadTransferida", query = "SELECT i FROM InsumoTransaccion i WHERE i.cantidadTransferida = :cantidadTransferida")
    , @NamedQuery(name = "InsumoTransaccion.findByValorTotalMonetario", query = "SELECT i FROM InsumoTransaccion i WHERE i.valorTotalMonetario = :valorTotalMonetario")
    , @NamedQuery(name = "InsumoTransaccion.findByTipoTransaccion", query = "SELECT i FROM InsumoTransaccion i WHERE i.tipoTransaccion = :tipoTransaccion")
    , @NamedQuery(name = "InsumoTransaccion.findByDescripcionTransaccion", query = "SELECT i FROM InsumoTransaccion i WHERE i.descripcionTransaccion = :descripcionTransaccion")
    , @NamedQuery(name = "InsumoTransaccion.findByDestino", query = "SELECT i FROM InsumoTransaccion i WHERE i.destino = :destino")
    , @NamedQuery(name = "InsumoTransaccion.findByHoraTransaccion", query = "SELECT i FROM InsumoTransaccion i WHERE i.horaTransaccion = :horaTransaccion")})
public class InsumoTransaccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InsumoTransaccionPK insumoTransaccionPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cantidad_transferida")
    private Float cantidadTransferida;
    @Column(name = "valor_total_monetario")
    private Float valorTotalMonetario;
    @Column(name = "tipo_transaccion")
    private String tipoTransaccion;
    @Column(name = "descripcion_transaccion")
    private String descripcionTransaccion;
    @Column(name = "destino")
    private String destino;
    @Basic(optional = false)
    @Column(name = "hora_transaccion")
    @Temporal(TemporalType.TIME)
    private Date horaTransaccion;
    @JoinColumn(name = "insumocod_insumo", referencedColumnName = "cod_insumo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Insumo insumo;
    @JoinColumn(name = "transaccionfecha_transaccion", referencedColumnName = "fecha_transaccion", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Transaccion transaccion;

    public InsumoTransaccion() {
    }

    public InsumoTransaccion(InsumoTransaccionPK insumoTransaccionPK) {
        this.insumoTransaccionPK = insumoTransaccionPK;
    }

    public InsumoTransaccion(InsumoTransaccionPK insumoTransaccionPK, Date horaTransaccion) {
        this.insumoTransaccionPK = insumoTransaccionPK;
        this.horaTransaccion = horaTransaccion;
    }

    public InsumoTransaccion(String insumocodInsumo, Date transaccionfechaTransaccion) {
        this.insumoTransaccionPK = new InsumoTransaccionPK(insumocodInsumo, transaccionfechaTransaccion);
    }

    public InsumoTransaccionPK getInsumoTransaccionPK() {
        return insumoTransaccionPK;
    }

    public void setInsumoTransaccionPK(InsumoTransaccionPK insumoTransaccionPK) {
        this.insumoTransaccionPK = insumoTransaccionPK;
    }

    public Float getCantidadTransferida() {
        return cantidadTransferida;
    }

    public void setCantidadTransferida(Float cantidadTransferida) {
        this.cantidadTransferida = cantidadTransferida;
    }

    public Float getValorTotalMonetario() {
        return valorTotalMonetario;
    }

    public void setValorTotalMonetario(Float valorTotalMonetario) {
        this.valorTotalMonetario = valorTotalMonetario;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public String getDescripcionTransaccion() {
        return descripcionTransaccion;
    }

    public void setDescripcionTransaccion(String descripcionTransaccion) {
        this.descripcionTransaccion = descripcionTransaccion;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getHoraTransaccion() {
        return horaTransaccion;
    }

    public void setHoraTransaccion(Date horaTransaccion) {
        this.horaTransaccion = horaTransaccion;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public Transaccion getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Transaccion transaccion) {
        this.transaccion = transaccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (insumoTransaccionPK != null ? insumoTransaccionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InsumoTransaccion)) {
            return false;
        }
        InsumoTransaccion other = (InsumoTransaccion) object;
        if ((this.insumoTransaccionPK == null && other.insumoTransaccionPK != null) || (this.insumoTransaccionPK != null && !this.insumoTransaccionPK.equals(other.insumoTransaccionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restManager.persistencia.InsumoTransaccion[ insumoTransaccionPK=" + insumoTransaccionPK + " ]";
    }

}
