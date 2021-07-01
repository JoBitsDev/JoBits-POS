/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.almacen.ipv.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.core.domain.models.Cocina;
import com.jobits.pos.core.domain.models.Insumo;
import com.jobits.pos.core.domain.models.IpvRegistro;
import com.jobits.pos.core.domain.models.IpvVentaRegistro;
import com.jobits.pos.core.domain.models.Venta;
import com.jobits.pos.ui.viewmodel.AbstractViewModel;
import java.util.Date;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class IpvGestionViewModel extends AbstractViewModel {

    private ArrayListModel<IpvRegistro> lista_ipv_registro = new ArrayListModel<>();

    public static final String PROP_LISTA_IPV_REGISTRO = "lista_ipv_registro";

    private IpvRegistro ipv_registro_seleciconado;

    public static final String PROP_IPV_REGISTRO_SELECICONADO = "ipv_registro_seleciconado";

    private ArrayListModel<IpvVentaRegistro> lista_ipv_venta_registro = new ArrayListModel<>();

    public static final String PROP_LISTA_IPV_VENTA_REGISTRO = "lista_ipv_venta_registro";

    private IpvVentaRegistro ipv_venta_registro_seleccionado;

    public static final String PROP_IPV_VENTA_REGISTRO_SELECCIONADO = "ipv_venta_registro_seleccionado";

    private boolean check_ocultar_productos_ipv = false;

    public static final String PROP_CHECK_OCULTAR_PRODUCTOS = "check_ocultar_productos_ipv";

    private Cocina punto_elaboracion_seleccionado;

    public static final String PROP_PUNTO_ELABORACION_SELECCIONADO = "punto_elaboracion_seleccionado";

    private Date fecha_ipv_seleccionada;

    public static final String PROP_FECHA_SELECCIONADA = "fecha_ipv_seleccionada";

    private ArrayListModel<Cocina> lista_punto_elaboracion = new ArrayListModel<>();

    public static final String PROP_LISTA_PUNTO_ELABORACION = "lista_punto_elaboracion";

    private Date fecha_ipv_ventas_seleccionada;

    public static final String PROP_FECHA_IPV_VENTAS_SELECCIONADA = "fecha_ipv_ventas_seleccionada";

    private boolean check_ocultar_productos_ipv_venta = false;

    public static final String PROP_CHECK_OCULTAR_PRODUCTOS_IPV_VENTA = "check_ocultar_productos_ipv_venta";

    private Venta venta_ipv_ventas_seleccionada;

    public static final String PROP_VENTA_IPV_VENTAS_SELECCIONADA = "venta_ipv_ventas_seleccionada";

    private Venta venta_ipv_seleccionada;

    public static final String PROP_VENTA_IPV_SELECCIONADA = "venta_ipv_seleccionada";

    private Insumo insumo_seleccionado;

    public static final String PROP_INSUMO_SELECCIONADO = "insumo_seleccionado";

    private ArrayListModel<Insumo> lista_insumos = new ArrayListModel<>();

    public static final String PROP_LISTA_INSUMOS = "lista_insumos";

    /**
     * Get the value of lista_insumos
     *
     * @return the value of lista_insumos
     */
    public ArrayListModel<Insumo> getLista_insumos() {
        return lista_insumos;
    }

    /**
     * Set the value of lista_insumos
     *
     * @param lista_insumos new value of lista_insumos
     */
    public void setLista_insumos(ArrayListModel<Insumo> lista_insumos) {
        ArrayListModel<Insumo> oldLista_insumos = this.lista_insumos;
        this.lista_insumos = lista_insumos;
        firePropertyChange(PROP_LISTA_INSUMOS, oldLista_insumos, lista_insumos);
    }

    /**
     * Get the value of insumo_seleccionado
     *
     * @return the value of insumo_seleccionado
     */
    public Insumo getInsumo_seleccionado() {
        return insumo_seleccionado;
    }

    /**
     * Set the value of insumo_seleccionado
     *
     * @param insumo_seleccionado new value of insumo_seleccionado
     */
    public void setInsumo_seleccionado(Insumo insumo_seleccionado) {
        Insumo oldInsumo_seleccionado = this.insumo_seleccionado;
        this.insumo_seleccionado = insumo_seleccionado;
        firePropertyChange(PROP_INSUMO_SELECCIONADO, oldInsumo_seleccionado, insumo_seleccionado);
    }

    /**
     * Get the value of venta_ipv_seleccionada
     *
     * @return the value of venta_ipv_seleccionada
     */
    public Venta getVenta_ipv_seleccionada() {
        return venta_ipv_seleccionada;
    }

    /**
     * Set the value of venta_ipv_seleccionada
     *
     * @param venta_ipv_seleccionada new value of venta_ipv_seleccionada
     */
    public void setVenta_ipv_seleccionada(Venta venta_ipv_seleccionada) {
        Venta oldVenta_ipv_seleccionada = this.venta_ipv_seleccionada;
        this.venta_ipv_seleccionada = venta_ipv_seleccionada;
        firePropertyChange(PROP_VENTA_IPV_SELECCIONADA, oldVenta_ipv_seleccionada, venta_ipv_seleccionada);
    }

    /**
     * Get the value of venta_ipv_ventas_seleccionada
     *
     * @return the value of venta_ipv_ventas_seleccionada
     */
    public Venta getVenta_ipv_ventas_seleccionada() {
        return venta_ipv_ventas_seleccionada;
    }

    /**
     * Set the value of venta_ipv_ventas_seleccionada
     *
     * @param venta_ipv_ventas_seleccionada new value of
     * venta_ipv_ventas_seleccionada
     */
    public void setVenta_ipv_ventas_seleccionada(Venta venta_ipv_ventas_seleccionada) {
        Venta oldVenta_ipv_ventas_seleccionada = this.venta_ipv_ventas_seleccionada;
        this.venta_ipv_ventas_seleccionada = venta_ipv_ventas_seleccionada;
        firePropertyChange(PROP_VENTA_IPV_VENTAS_SELECCIONADA, oldVenta_ipv_ventas_seleccionada, venta_ipv_ventas_seleccionada);
    }

    /**
     * Get the value of check_ocultar_productos_ipv_venta
     *
     * @return the value of check_ocultar_productos_ipv_venta
     */
    public boolean isCheck_ocultar_productos_ipv_venta() {
        return check_ocultar_productos_ipv_venta;
    }

    /**
     * Set the value of check_ocultar_productos_ipv_venta
     *
     * @param check_ocultar_productos_ipv_venta new value of
     * check_ocultar_productos_ipv_venta
     */
    public void setCheck_ocultar_productos_ipv_venta(boolean check_ocultar_productos_ipv_venta) {
        boolean oldCheck_ocultar_productos_ipv_venta = this.check_ocultar_productos_ipv_venta;
        this.check_ocultar_productos_ipv_venta = check_ocultar_productos_ipv_venta;
        firePropertyChange(PROP_CHECK_OCULTAR_PRODUCTOS_IPV_VENTA, oldCheck_ocultar_productos_ipv_venta, check_ocultar_productos_ipv_venta, true);
    }

    /**
     * Get the value of fecha_ipv_ventas_seleccionada
     *
     * @return the value of fecha_ipv_ventas_seleccionada
     */
    public Date getFecha_ipv_ventas_seleccionada() {
        return fecha_ipv_ventas_seleccionada;
    }

    /**
     * Set the value of fecha_ipv_ventas_seleccionada
     *
     * @param fecha_ipv_ventas_seleccionada new value of
     * fecha_ipv_ventas_seleccionada
     */
    public void setFecha_ipv_ventas_seleccionada(Date fecha_ipv_ventas_seleccionada) {
        Date oldFecha_ipv_ventas_seleccionada = this.fecha_ipv_ventas_seleccionada;
        this.fecha_ipv_ventas_seleccionada = fecha_ipv_ventas_seleccionada;
        firePropertyChange(PROP_FECHA_IPV_VENTAS_SELECCIONADA, oldFecha_ipv_ventas_seleccionada, fecha_ipv_ventas_seleccionada, false);
    }

    /**
     * Get the value of lista_punto_elaboracion
     *
     * @return the value of lista_punto_elaboracion
     */
    public ArrayListModel<Cocina> getLista_punto_elaboracion() {
        return lista_punto_elaboracion;
    }

    /**
     * Set the value of lista_punto_elaboracion
     *
     * @param lista_punto_elaboracion new value of lista_punto_elaboracion
     */
    public void setLista_punto_elaboracion(ArrayListModel<Cocina> lista_punto_elaboracion) {
        ArrayListModel<Cocina> oldLista_punto_elaboracion = this.lista_punto_elaboracion;
        this.lista_punto_elaboracion = lista_punto_elaboracion;
        firePropertyChange(PROP_LISTA_PUNTO_ELABORACION, oldLista_punto_elaboracion, lista_punto_elaboracion, false);
    }

    /**
     * Get the value of fecha_ipv_seleccionada
     *
     * @return the value of fecha_ipv_seleccionada
     */
    public Date getFecha_ipv_seleccionada() {
        return fecha_ipv_seleccionada;
    }

    /**
     * Set the value of fecha_ipv_seleccionada
     *
     * @param fecha_ipv_seleccionada new value of fecha_ipv_seleccionada
     */
    public void setFecha_ipv_seleccionada(Date fecha_ipv_seleccionada) {
        Date oldFecha_seleccionada = this.fecha_ipv_seleccionada;
        this.fecha_ipv_seleccionada = fecha_ipv_seleccionada;
        firePropertyChange(PROP_FECHA_SELECCIONADA, oldFecha_seleccionada, fecha_ipv_seleccionada, false);
    }

    /**
     * Get the value of punto_elaboracion_seleccionado
     *
     * @return the value of punto_elaboracion_seleccionado
     */
    public Cocina getPunto_elaboracion_seleccionado() {
        return punto_elaboracion_seleccionado;
    }

    /**
     * Set the value of punto_elaboracion_seleccionado
     *
     * @param punto_elaboracion_seleccionado new value of
     * punto_elaboracion_seleccionado
     */
    public void setPunto_elaboracion_seleccionado(Cocina punto_elaboracion_seleccionado) {
        Cocina oldPunto_elaboracion_seleccionado = this.punto_elaboracion_seleccionado;
        this.punto_elaboracion_seleccionado = punto_elaboracion_seleccionado;
        firePropertyChange(PROP_PUNTO_ELABORACION_SELECCIONADO, oldPunto_elaboracion_seleccionado, punto_elaboracion_seleccionado, false);
    }

    /**
     * Get the value of check_ocultar_productos_ipv
     *
     * @return the value of check_ocultar_productos_ipv
     */
    public boolean isCheck_ocultar_productos_ipv() {
        return check_ocultar_productos_ipv;
    }

    /**
     * Set the value of check_ocultar_productos_ipv
     *
     * @param check_ocultar_productos_ipv new value of
     * check_ocultar_productos_ipv
     */
    public void setCheck_ocultar_productos_ipv(boolean check_ocultar_productos_ipv) {
        boolean oldCheck_ocultar_productos = this.check_ocultar_productos_ipv;
        this.check_ocultar_productos_ipv = check_ocultar_productos_ipv;
        firePropertyChange(PROP_CHECK_OCULTAR_PRODUCTOS, oldCheck_ocultar_productos, check_ocultar_productos_ipv, true);
    }

    /**
     * Get the value of ipv_venta_registro_seleccionado
     *
     * @return the value of ipv_venta_registro_seleccionado
     */
    public IpvVentaRegistro getIpv_venta_registro_seleccionado() {
        return ipv_venta_registro_seleccionado;
    }

    /**
     * Set the value of ipv_venta_registro_seleccionado
     *
     * @param ipv_venta_registro_seleccionado new value of
     * ipv_venta_registro_seleccionado
     */
    public void setIpv_venta_registro_seleccionado(IpvVentaRegistro ipv_venta_registro_seleccionado) {
        IpvVentaRegistro oldIpv_venta_registro_seleccionado = this.ipv_venta_registro_seleccionado;
        this.ipv_venta_registro_seleccionado = ipv_venta_registro_seleccionado;
        firePropertyChange(PROP_IPV_VENTA_REGISTRO_SELECCIONADO, oldIpv_venta_registro_seleccionado, ipv_venta_registro_seleccionado, false);
    }

    /**
     * Get the value of lista_ipv_venta_registro
     *
     * @return the value of lista_ipv_venta_registro
     */
    public ArrayListModel<IpvVentaRegistro> getLista_ipv_venta_registro() {
        return lista_ipv_venta_registro;
    }

    /**
     * Set the value of lista_ipv_venta_registro
     *
     * @param lista_ipv_venta_registro new value of lista_ipv_venta_registro
     */
    public void setLista_ipv_venta_registro(ArrayListModel<IpvVentaRegistro> lista_ipv_venta_registro) {
        ArrayListModel<IpvVentaRegistro> oldLista_ipv_venta_registro = this.lista_ipv_venta_registro;
        this.lista_ipv_venta_registro.clear();
        this.lista_ipv_venta_registro.addAll(lista_ipv_venta_registro);
        firePropertyChange(PROP_LISTA_IPV_VENTA_REGISTRO, oldLista_ipv_venta_registro, lista_ipv_venta_registro, false);
    }

    /**
     * Get the value of ipv_registro_seleciconado
     *
     * @return the value of ipv_registro_seleciconado
     */
    public IpvRegistro getIpv_registro_seleciconado() {
        return ipv_registro_seleciconado;
    }

    /**
     * Set the value of ipv_registro_seleciconado
     *
     * @param ipv_registro_seleciconado new value of ipv_registro_seleciconado
     */
    public void setIpv_registro_seleciconado(IpvRegistro ipv_registro_seleciconado) {
        IpvRegistro oldIpv_registro_seleciconado = this.ipv_registro_seleciconado;
        this.ipv_registro_seleciconado = ipv_registro_seleciconado;
        firePropertyChange(PROP_IPV_REGISTRO_SELECICONADO, oldIpv_registro_seleciconado, ipv_registro_seleciconado, false);
    }

    /**
     * Get the value of lista_ipv_registro
     *
     * @return the value of lista_ipv_registro
     */
    public ArrayListModel<IpvRegistro> getLista_ipv_registro() {
        return lista_ipv_registro;
    }

    /**
     * Set the value of lista_ipv_registro
     *
     * @param lista_ipv_registro new value of lista_ipv_registro
     */
    public void setLista_ipv_registro(ArrayListModel<IpvRegistro> lista_ipv_registro) {
        ArrayListModel<IpvRegistro> oldLista_ipv_registro = this.lista_ipv_registro;
        this.lista_ipv_registro.clear();
        this.lista_ipv_registro.addAll(lista_ipv_registro);
        firePropertyChange(PROP_LISTA_IPV_REGISTRO, oldLista_ipv_registro, lista_ipv_registro, false);
    }

}
