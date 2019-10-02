/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restManager.persistencia;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * FirstDream
 * @author Jorge
 * 
 */
@Entity
@Table(name = "transaccion_traspaso")
@NamedQueries({
    @NamedQuery(name = "TransaccionTraspaso.findAll", query = "SELECT t FROM TransaccionTraspaso t"),
    @NamedQuery(name = "TransaccionTraspaso.findByTransaccioninsumocodInsumo", query = "SELECT t FROM TransaccionTraspaso t WHERE t.transaccionTraspasoPK.transaccioninsumocodInsumo = :transaccioninsumocodInsumo"),
    @NamedQuery(name = "TransaccionTraspaso.findByTransaccionalmacencodAlmacen", query = "SELECT t FROM TransaccionTraspaso t WHERE t.transaccionTraspasoPK.transaccionalmacencodAlmacen = :transaccionalmacencodAlmacen"),
    @NamedQuery(name = "TransaccionTraspaso.findByTransaccionfecha", query = "SELECT t FROM TransaccionTraspaso t WHERE t.transaccionTraspasoPK.transaccionfecha = :transaccionfecha"),
    @NamedQuery(name = "TransaccionTraspaso.findByTransaccionhora", query = "SELECT t FROM TransaccionTraspaso t WHERE t.transaccionTraspasoPK.transaccionhora = :transaccionhora")})
public class TransaccionTraspaso implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TransaccionTraspasoPK transaccionTraspasoPK;
    @JoinColumn(name = "almacen_destino", referencedColumnName = "cod_almacen")
    @ManyToOne(optional = false)
    private Almacen almacenDestino;
    @JoinColumns({
        @JoinColumn(name = "transaccioninsumocod_insumo", referencedColumnName = "insumocod_insumo", insertable = false, updatable = false),
        @JoinColumn(name = "transaccionalmacencod_almacen", referencedColumnName = "almacencod_almacen", insertable = false, updatable = false),
        @JoinColumn(name = "transaccionfecha", referencedColumnName = "fecha", insertable = false, updatable = false),
        @JoinColumn(name = "transaccionhora", referencedColumnName = "hora", insertable = false, updatable = false)})
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private Transaccion transaccion;

    public TransaccionTraspaso() {
    }

    public TransaccionTraspaso(TransaccionTraspasoPK transaccionTraspasoPK) {
        this.transaccionTraspasoPK = transaccionTraspasoPK;
    }

    public TransaccionTraspaso(String transaccioninsumocodInsumo, String transaccionalmacencodAlmacen, Date transaccionfecha, Date transaccionhora) {
        this.transaccionTraspasoPK = new TransaccionTraspasoPK(transaccioninsumocodInsumo, transaccionalmacencodAlmacen, transaccionfecha, transaccionhora);
    }

    public TransaccionTraspasoPK getTransaccionTraspasoPK() {
        return transaccionTraspasoPK;
    }

    public void setTransaccionTraspasoPK(TransaccionTraspasoPK transaccionTraspasoPK) {
        this.transaccionTraspasoPK = transaccionTraspasoPK;
    }

    public Almacen getAlmacenDestino() {
        return almacenDestino;
    }

    public void setAlmacenDestino(Almacen almacenDestino) {
        this.almacenDestino = almacenDestino;
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
        hash += (transaccionTraspasoPK != null ? transaccionTraspasoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransaccionTraspaso)) {
            return false;
        }
        TransaccionTraspaso other = (TransaccionTraspaso) object;
        if ((this.transaccionTraspasoPK == null && other.transaccionTraspasoPK != null) || (this.transaccionTraspasoPK != null && !this.transaccionTraspasoPK.equals(other.transaccionTraspasoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restManager.persistencia.TransaccionTraspaso[ transaccionTraspasoPK=" + transaccionTraspasoPK + " ]";
    }

}
