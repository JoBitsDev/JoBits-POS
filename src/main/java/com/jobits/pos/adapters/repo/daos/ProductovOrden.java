/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jobits.pos.adapters.repo.daos;

import com.jobits.pos.domain.models.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "productovOrdenPK",scope = ProductovOrden.class )
@Entity
@Table(name = "productov_orden")
@NamedQueries({
    @NamedQuery(name = "ProductovOrden.findAll", query = "SELECT p FROM ProductovOrden p"),
    @NamedQuery(name = "ProductovOrden.findByProductoVentapCod", query = "SELECT p FROM ProductovOrden p WHERE p.productovOrdenPK.productoVentapCod = :productoVentapCod"),
    @NamedQuery(name = "ProductovOrden.findByOrdencodOrden", query = "SELECT p FROM ProductovOrden p WHERE p.productovOrdenPK.ordencodOrden = :ordencodOrden"),
    @NamedQuery(name = "ProductovOrden.findByCantidad", query = "SELECT p FROM ProductovOrden p WHERE p.cantidad = :cantidad"),
    @NamedQuery(name = "ProductovOrden.findByEnviadosacocina", query = "SELECT p FROM ProductovOrden p WHERE p.enviadosacocina = :enviadosacocina"),
    @NamedQuery(name = "ProductovOrden.findByNumeroComensal", query = "SELECT p FROM ProductovOrden p WHERE p.numeroComensal = :numeroComensal"),
    @NamedQuery(name = "ProductovOrden.findByListoParaRecoger", query = "SELECT p FROM ProductovOrden p WHERE p.listoParaRecoger = :listoParaRecoger")})
public class ProductovOrden implements Serializable {

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "enviadosacocina")
    private Float enviadosacocina;

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProductovOrdenPK productovOrdenPK;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private float cantidad;
    @Column(name = "numero_comensal")
    private Integer numeroComensal;
    @Column(name = "listo_para_recoger")
    private Boolean listoParaRecoger;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productovOrden")
    private List<NotificacionEnvioCocina> notificacionEnvioCocinaList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "productovOrden")
    private Nota nota;
    @JoinColumn(name = "ordencod_orden", referencedColumnName = "cod_orden", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Orden orden;
    @JoinColumn(name = "producto_ventap_cod", referencedColumnName = "p_cod", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ProductoVenta productoVenta;

    public ProductovOrden() {
    }

    public ProductovOrden(ProductovOrdenPK productovOrdenPK) {
        this.productovOrdenPK = productovOrdenPK;
    }

    public ProductovOrden(ProductovOrdenPK productovOrdenPK, int cantidad) {
        this.productovOrdenPK = productovOrdenPK;
        this.cantidad = cantidad;
    }

    public ProductovOrden(String productoVentapCod, String ordencodOrden) {
        this.productovOrdenPK = new ProductovOrdenPK(productoVentapCod, ordencodOrden);
    }

    public ProductovOrdenPK getProductovOrdenPK() {
        return productovOrdenPK;
    }

    public void setProductovOrdenPK(ProductovOrdenPK productovOrdenPK) {
        this.productovOrdenPK = productovOrdenPK;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }


    public Integer getNumeroComensal() {
        return numeroComensal;
    }

    public void setNumeroComensal(Integer numeroComensal) {
        this.numeroComensal = numeroComensal;
    }

    public Boolean getListoParaRecoger() {
        return listoParaRecoger;
    }

    public void setListoParaRecoger(Boolean listoParaRecoger) {
        this.listoParaRecoger = listoParaRecoger;
    }

    public List<NotificacionEnvioCocina> getNotificacionEnvioCocinaList() {
        return notificacionEnvioCocinaList;
    }

    public void setNotificacionEnvioCocinaList(List<NotificacionEnvioCocina> notificacionEnvioCocinaList) {
        this.notificacionEnvioCocinaList = notificacionEnvioCocinaList;
    }

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public ProductoVenta getProductoVenta() {
        return productoVenta;
    }

    public void setProductoVenta(ProductoVenta productoVenta) {
        this.productoVenta = productoVenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productovOrdenPK != null ? productovOrdenPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductovOrden)) {
            return false;
        }
        ProductovOrden other = (ProductovOrden) object;
        if ((this.productovOrdenPK == null && other.productovOrdenPK != null) || (this.productovOrdenPK != null && !this.productovOrdenPK.equals(other.productovOrdenPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  getOrden() +"_"+ getProductoVenta();
    }

    public Float getEnviadosacocina() {
        return enviadosacocina;
    }

    public void setEnviadosacocina(Float enviadosacocina) {
        this.enviadosacocina = enviadosacocina;
    }

}
