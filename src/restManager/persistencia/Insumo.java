/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restManager.persistencia;

import java.io.Serializable;
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

/**
 * FirstDream
 * @author Jorge
 * 
 */
@Entity
@Table(name = "insumo")
@NamedQueries({
    @NamedQuery(name = "Insumo.findAll", query = "SELECT i FROM Insumo i")
    , @NamedQuery(name = "Insumo.findByCodInsumo", query = "SELECT i FROM Insumo i WHERE i.codInsumo = :codInsumo")
    , @NamedQuery(name = "Insumo.findByNombre", query = "SELECT i FROM Insumo i WHERE i.nombre = :nombre")
    , @NamedQuery(name = "Insumo.findByUm", query = "SELECT i FROM Insumo i WHERE i.um = :um")
    , @NamedQuery(name = "Insumo.findByElaborado", query = "SELECT i FROM Insumo i WHERE i.elaborado = :elaborado")
    , @NamedQuery(name = "Insumo.findByCostoPorUnidad", query = "SELECT i FROM Insumo i WHERE i.costoPorUnidad = :costoPorUnidad")
    , @NamedQuery(name = "Insumo.findByCantidadExistente", query = "SELECT i FROM Insumo i WHERE i.cantidadExistente = :cantidadExistente")})
public class Insumo implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "insumo")
    private List<InsumoTransaccion> insumoTransaccionList;

    @Column(name = "cantidad_creada")
    private Float cantidadCreada;
    
    
    @Column(name = "cantidad_existente")
    private Float cantidadExistente;
    @Column(name = "stock_estimation")
    private Float stockEstimation;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cod_insumo")
    private String codInsumo;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "um")
    private String um;
    @Column(name = "elaborado")
    private Boolean elaborado;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "costo_por_unidad")
    private Float costoPorUnidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "insumo")
    private List<ProductoInsumo> productoInsumoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "insumo")
    private List<Ipv> ipvList;
    @JoinColumn(name = "almacencod_almacen", referencedColumnName = "cod_almacen")
    @ManyToOne
    private Almacen almacencodAlmacen;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "insumo")
    private List<InsumoElaborado> insumoElaboradoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "insumo1")
    private List<InsumoElaborado> insumoElaboradoList1;

    public Insumo() {
    }

    public Insumo(String codInsumo) {
        this.codInsumo = codInsumo;
    }

    public Insumo(String codInsumo, String nombre) {
        this.codInsumo = codInsumo;
        this.nombre = nombre;
    }

    public String getCodInsumo() {
        return codInsumo;
    }

    public void setCodInsumo(String codInsumo) {
        this.codInsumo = codInsumo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUm() {
        return um;
    }

    public void setUm(String um) {
        this.um = um;
    }

    public Boolean getElaborado() {
        return elaborado;
    }

    public void setElaborado(Boolean elaborado) {
        this.elaborado = elaborado;
    }

    public Float getCostoPorUnidad() {
        return costoPorUnidad;
    }

    public void setCostoPorUnidad(Float costoPorUnidad) {
        this.costoPorUnidad = costoPorUnidad;
    }

    public Float getCantidadExistente() {
        return cantidadExistente;
    }

    public void setCantidadExistente(Float cantidadExistente) {
        this.cantidadExistente = cantidadExistente;
    }

    public List<ProductoInsumo> getProductoInsumoList() {
        return productoInsumoList;
    }

    public void setProductoInsumoList(List<ProductoInsumo> productoInsumoList) {
        this.productoInsumoList = productoInsumoList;
    }

    public List<Ipv> getIpvList() {
        return ipvList;
    }

    public void setIpvList(List<Ipv> ipvList) {
        this.ipvList = ipvList;
    }

    public Almacen getAlmacencodAlmacen() {
        return almacencodAlmacen;
    }

    public void setAlmacencodAlmacen(Almacen almacencodAlmacen) {
        this.almacencodAlmacen = almacencodAlmacen;
    }

    public List<InsumoElaborado> getInsumoElaboradoList() {
        return insumoElaboradoList;
    }

    public void setInsumoElaboradoList(List<InsumoElaborado> insumoElaboradoList) {
        this.insumoElaboradoList = insumoElaboradoList;
    }

    public List<InsumoElaborado> getInsumoElaboradoList1() {
        return insumoElaboradoList1;
    }

    public void setInsumoElaboradoList1(List<InsumoElaborado> insumoElaboradoList1) {
        this.insumoElaboradoList1 = insumoElaboradoList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codInsumo != null ? codInsumo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Insumo)) {
            return false;
        }
        Insumo other = (Insumo) object;
        if ((this.codInsumo == null && other.codInsumo != null) || (this.codInsumo != null && !this.codInsumo.equals(other.codInsumo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restManager.persistencia.Insumo[ codInsumo=" + codInsumo + " ]";
    }

    public Float getStockEstimation() {
        return stockEstimation;
    }

    public void setStockEstimation(Float stockEstimation) {
        this.stockEstimation = stockEstimation;
    }

    public Float getCantidadCreada() {
        return cantidadCreada;
    }

    public void setCantidadCreada(Float cantidadCreada) {
        this.cantidadCreada = cantidadCreada;
    }

    public List<InsumoTransaccion> getInsumoTransaccionList() {
        return insumoTransaccionList;
    }

    public void setInsumoTransaccionList(List<InsumoTransaccion> insumoTransaccionList) {
        this.insumoTransaccionList = insumoTransaccionList;
    }
    
    

}
