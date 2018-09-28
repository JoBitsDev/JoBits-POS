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
 * @author Jorge
 * 
 */
@Entity
@Table(name = "ficha")
@NamedQueries({
    @NamedQuery(name = "Ficha.findAll", query = "SELECT f FROM Ficha f")
    , @NamedQuery(name = "Ficha.findByIdFicha", query = "SELECT f FROM Ficha f WHERE f.idFicha = :idFicha")
    , @NamedQuery(name = "Ficha.findByFechaCreacion", query = "SELECT f FROM Ficha f WHERE f.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Ficha.findByTipo", query = "SELECT f FROM Ficha f WHERE f.tipo = :tipo")
    , @NamedQuery(name = "Ficha.findByValorMonetario", query = "SELECT f FROM Ficha f WHERE f.valorMonetario = :valorMonetario")})
public class Ficha implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ficha")
    private Integer idFicha;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Column(name = "tipo")
    private String tipo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_monetario")
    private Float valorMonetario;
    @JoinColumn(name = "almacencod_almacen", referencedColumnName = "cod_almacen")
    @ManyToOne
    private Almacen almacencodAlmacen;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ficha")
    private List<InsumoFicha> insumoFichaList;

    public Ficha() {
    }

    public Ficha(Integer idFicha) {
        this.idFicha = idFicha;
    }

    public Integer getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(Integer idFicha) {
        this.idFicha = idFicha;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Float getValorMonetario() {
        return valorMonetario;
    }

    public void setValorMonetario(Float valorMonetario) {
        this.valorMonetario = valorMonetario;
    }

    public Almacen getAlmacencodAlmacen() {
        return almacencodAlmacen;
    }

    public void setAlmacencodAlmacen(Almacen almacencodAlmacen) {
        this.almacencodAlmacen = almacencodAlmacen;
    }

    public List<InsumoFicha> getInsumoFichaList() {
        return insumoFichaList;
    }

    public void setInsumoFichaList(List<InsumoFicha> insumoFichaList) {
        this.insumoFichaList = insumoFichaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFicha != null ? idFicha.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ficha)) {
            return false;
        }
        Ficha other = (Ficha) object;
        if ((this.idFicha == null && other.idFicha != null) || (this.idFicha != null && !this.idFicha.equals(other.idFicha))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restManager.persistencia.Ficha[ idFicha=" + idFicha + " ]";
    }

}
