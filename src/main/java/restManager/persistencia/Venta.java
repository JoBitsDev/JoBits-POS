/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.persistencia;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import restManager.resources.R;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "fecha",scope = Venta.class )
@Entity
@Table(name = "venta")
@NamedQueries({
    @NamedQuery(name = "Venta.findAll", query = "SELECT v FROM Venta v"),
    @NamedQuery(name = "Venta.findByFecha", query = "SELECT v FROM Venta v WHERE v.fecha = :fecha"),
    @NamedQuery(name = "Venta.findByVentaTotal", query = "SELECT v FROM Venta v WHERE v.ventaTotal = :ventaTotal"),
    @NamedQuery(name = "Venta.findByVentagastosEninsumos", query = "SELECT v FROM Venta v WHERE v.ventagastosEninsumos = :ventagastosEninsumos"),
    @NamedQuery(name = "Venta.findByHoraPico", query = "SELECT v FROM Venta v WHERE v.horaPico = :horaPico"),
    @NamedQuery(name = "Venta.findByVentagastosPagotrabajadores", query = "SELECT v FROM Venta v WHERE v.ventagastosPagotrabajadores = :ventagastosPagotrabajadores"),
    @NamedQuery(name = "Venta.findByVentagastosGastos", query = "SELECT v FROM Venta v WHERE v.ventagastosGastos = :ventagastosGastos"),
    @NamedQuery(name = "Venta.findByVentagastosEninsumos", query = "SELECT v FROM Venta v WHERE v.ventagastosEninsumos = :ventagastosEninsumos")})
public class Venta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Column(name = "ventapropina")
    private Float ventapropina;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "venta")
    private List<AsistenciaPersonal> asistenciaPersonalList;

    @Column(name = "cambio_turno1")
    private String cambioTurno1;
    @Column(name = "cambio_turno2")
    private String cambioTurno2;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "venta_total")
    private Double ventaTotal;
    @Column(name = "ventagastos_eninsumos")
    private Double ventagastosEninsumos;
    @Column(name = "hora_pico")
    private Integer horaPico;
    @Column(name = "ventagastos_pagotrabajadores")
    private Float ventagastosPagotrabajadores;
    @Column(name = "ventagastos_gastos")
    private Float ventagastosGastos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "venta")
    private List<GastoVenta> gastoVentaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ventafecha")
    private List<Orden> ordenList;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fechaVenta")
    private List<IpvVentaRegistro> ipvVentaRegistroList;

    public Venta() {
    }

    public Venta(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getVentaTotal() {
        return ventaTotal;
    }

    public void setVentaTotal(Double ventaTotal) {
        this.ventaTotal = ventaTotal;
    }

    public Double getVentagastosEninsumos() {
        return ventagastosEninsumos;
    }

    public void setVentagastosEninsumos(Double ventagastosEninsumos) {
        this.ventagastosEninsumos = ventagastosEninsumos;
    }

    public Integer getHoraPico() {
        return horaPico;
    }

    public List<Orden> getOrdenList() {
        return ordenList;
    }

    public void setOrdenList(List<Orden> ordenList) {
        this.ordenList = ordenList;
    }

    public void setHoraPico(Integer horaPico) {
        this.horaPico = horaPico;
    }

    public Float getVentagastosPagotrabajadores() {
        return ventagastosPagotrabajadores;
    }

    public void setVentagastosPagotrabajadores(Float ventagastosPagotrabajadores) {
        this.ventagastosPagotrabajadores = ventagastosPagotrabajadores;
    }

    public Float getVentagastosGastos() {
        return ventagastosGastos;
    }

    public void setVentagastosGastos(Float ventagastosGastos) {
        this.ventagastosGastos = ventagastosGastos;
    }

    public List<GastoVenta> getGastoVentaList() {
        return gastoVentaList;
    }

    public void setGastoVentaList(List<GastoVenta> gastoVentaList) {
        this.gastoVentaList = gastoVentaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fecha != null ? fecha.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Venta)) {
            return false;
        }
        Venta other = (Venta) object;
        if ((this.fecha == null && other.fecha != null) || (this.fecha != null && !this.fecha.equals(other.fecha))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Venta del dia " + R.DATE_FORMAT.format(fecha);
    }

    public String getCambioTurno1() {
        return cambioTurno1;
    }

    public void setCambioTurno1(String cambioTurno1) {
        this.cambioTurno1 = cambioTurno1;
    }

    public String getCambioTurno2() {
        return cambioTurno2;
    }

    public void setCambioTurno2(String cambioTurno2) {
        this.cambioTurno2 = cambioTurno2;
    }

    public List<AsistenciaPersonal> getAsistenciaPersonalList() {
        return asistenciaPersonalList;
    }

    public void setAsistenciaPersonalList(List<AsistenciaPersonal> asistenciaPersonalList) {
        this.asistenciaPersonalList = asistenciaPersonalList;
    }

    public Float getVentapropina() {
        return ventapropina;
    }

    public void setVentapropina(Float ventapropina) {
        this.ventapropina = ventapropina;
    }

    public List<IpvVentaRegistro> getIpvVentaRegistroList() {
        return ipvVentaRegistroList;
    }

    public void setIpvVentaRegistroList(List<IpvVentaRegistro> ipvVentaRegistroList) {
        this.ipvVentaRegistroList = ipvVentaRegistroList;
    }

}
