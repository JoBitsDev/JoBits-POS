/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restManager.persistencia;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "transaccion_salida")
@NamedQueries({
    @NamedQuery(name = "TransaccionSalida.findAll", query = "SELECT t FROM TransaccionSalida t"),
    @NamedQuery(name = "TransaccionSalida.findByTransaccioninsumocodInsumo", query = "SELECT t FROM TransaccionSalida t WHERE t.transaccionSalidaPK.transaccioninsumocodInsumo = :transaccioninsumocodInsumo"),
    @NamedQuery(name = "TransaccionSalida.findByTransaccionfecha", query = "SELECT t FROM TransaccionSalida t WHERE t.transaccionSalidaPK.transaccionfecha = :transaccionfecha"),
    @NamedQuery(name = "TransaccionSalida.findByTransaccionhora", query = "SELECT t FROM TransaccionSalida t WHERE t.transaccionSalidaPK.transaccionhora = :transaccionhora"),
    @NamedQuery(name = "TransaccionSalida.findByIpvRegistroipvcocinacodCocina", query = "SELECT t FROM TransaccionSalida t WHERE t.transaccionSalidaPK.ipvRegistroipvcocinacodCocina = :ipvRegistroipvcocinacodCocina")})
public class TransaccionSalida implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TransaccionSalidaPK transaccionSalidaPK;
    @JoinColumn(name = "almacencod_almacendesde", referencedColumnName = "cod_almacen")
    @ManyToOne(optional = false)
    private Almacen almacencodAlmacendesde;
    @JoinColumns({
        @JoinColumn(name = "transaccioninsumocod_insumo", referencedColumnName = "ipvinsumocod_insumo", insertable = false, updatable = false),
        @JoinColumn(name = "transaccionfecha", referencedColumnName = "fecha", insertable = false, updatable = false),
        @JoinColumn(name = "ipv_registroipvcocinacod_cocina", referencedColumnName = "ipvcocinacod_cocina", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private IpvRegistro ipvRegistro;
    @JoinColumns({
        @JoinColumn(name = "transaccioninsumocod_insumo", referencedColumnName = "insumocod_insumo", insertable = false, updatable = false),
        @JoinColumn(name = "transaccionfecha", referencedColumnName = "fecha", insertable = false, updatable = false),
        @JoinColumn(name = "transaccionhora", referencedColumnName = "hora", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Transaccion transaccion;

    public TransaccionSalida() {
    }

    public TransaccionSalida(TransaccionSalidaPK transaccionSalidaPK) {
        this.transaccionSalidaPK = transaccionSalidaPK;
    }

    public TransaccionSalida(String transaccioninsumocodInsumo, Date transaccionfecha, Date transaccionhora, String ipvRegistroipvcocinacodCocina) {
        this.transaccionSalidaPK = new TransaccionSalidaPK(transaccioninsumocodInsumo, transaccionfecha, transaccionhora, ipvRegistroipvcocinacodCocina);
    }

    public TransaccionSalidaPK getTransaccionSalidaPK() {
        return transaccionSalidaPK;
    }

    public void setTransaccionSalidaPK(TransaccionSalidaPK transaccionSalidaPK) {
        this.transaccionSalidaPK = transaccionSalidaPK;
    }

    public Almacen getAlmacencodAlmacendesde() {
        return almacencodAlmacendesde;
    }

    public void setAlmacencodAlmacendesde(Almacen almacencodAlmacendesde) {
        this.almacencodAlmacendesde = almacencodAlmacendesde;
    }

    public IpvRegistro getIpvRegistro() {
        return ipvRegistro;
    }

    public void setIpvRegistro(IpvRegistro ipvRegistro) {
        this.ipvRegistro = ipvRegistro;
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
        hash += (transaccionSalidaPK != null ? transaccionSalidaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransaccionSalida)) {
            return false;
        }
        TransaccionSalida other = (TransaccionSalida) object;
        if ((this.transaccionSalidaPK == null && other.transaccionSalidaPK != null) || (this.transaccionSalidaPK != null && !this.transaccionSalidaPK.equals(other.transaccionSalidaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restManager.persistencia.TransaccionSalida[ transaccionSalidaPK=" + transaccionSalidaPK + " ]";
    }

}
