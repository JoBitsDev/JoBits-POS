/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.almacen.ipv.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.core.domain.InsumoPedidoModel;
import com.jobits.pos.core.domain.ProdcutoVentaPedidoModel;
import com.jobits.pos.core.domain.models.Almacen;
import com.jobits.pos.core.domain.models.IpvVentaRegistro;
import com.jobits.pos.ui.viewmodel.AbstractViewModel;

/**
 *
 * @author Home
 */
public class IPVPedidoVentasViewModel extends AbstractViewModel {

    //
    //LISTAS
    //
    private ArrayListModel<IpvVentaRegistro> lista_ipv_venta_registro = new ArrayListModel<>();

    public static final String PROP_LISTA_IPV_VENTA_REGISTRO = "lista_ipv_venta_registro";

    private ArrayListModel<ProdcutoVentaPedidoModel> lista_producto_venta_model = new ArrayListModel<>();

    public static final String PROP_LISTA_PRODUCTO_VENTA_MODEL = "lista_producto_venta_model";

    private ArrayListModel<Almacen> lista_almacen = new ArrayListModel<>();

    public static final String PROP_LISTA_ALMACEN = "lista_almacen";

    private ArrayListModel<InsumoPedidoModel> lista_insumo_pedido_model = new ArrayListModel<>();

    public static final String PROP_LISTA_INSUMO_PEDIDO_MODEL = "lista_insumo_pedido_model";

    //
    //SELECIONADO
    //
    private IpvVentaRegistro seleccionado_ipv_ventas;

    public static final String PROP_SELECCIONADO_IPV_VENTAS = "seleccionado_ipv_ventas";

    private ProdcutoVentaPedidoModel seleccionado_producto_venta;

    public static final String PROP_SELECCIONADO_PRODUCTO_VENTA = "seleccionado_producto_venta";

    private Almacen seleccionado_almacen;

    public static final String PROP_SELECCIONADO_ALMACEN = "seleccionado_almacen";

    private InsumoPedidoModel seleccionado_insumo_pedido;

    public static final String PROP_SELECCIONADO_INSUMO_PEDIDO = "seleccionado_insumo_pedido";

    /**
     * Get the value of seleccionado_insumo_pedido
     *
     * @return the value of seleccionado_insumo_pedido
     */
    public InsumoPedidoModel getSeleccionado_insumo_pedido() {
        return seleccionado_insumo_pedido;
    }

    /**
     * Set the value of seleccionado_insumo_pedido
     *
     * @param seleccionado_insumo_pedido new value of seleccionado_insumo_pedido
     */
    public void setSeleccionado_insumo_pedido(InsumoPedidoModel seleccionado_insumo_pedido) {
        InsumoPedidoModel oldSeleccionado_insumo_pedido = this.seleccionado_insumo_pedido;
        this.seleccionado_insumo_pedido = seleccionado_insumo_pedido;
        firePropertyChange(PROP_SELECCIONADO_INSUMO_PEDIDO, oldSeleccionado_insumo_pedido, seleccionado_insumo_pedido);
    }

    /**
     * Get the value of seleccionado_almacen
     *
     * @return the value of seleccionado_almacen
     */
    public Almacen getSeleccionado_almacen() {
        return seleccionado_almacen;
    }

    /**
     * Set the value of seleccionado_almacen
     *
     * @param seleccionado_almacen new value of seleccionado_almacen
     */
    public void setSeleccionado_almacen(Almacen seleccionado_almacen) {
        Almacen oldSeleccionado_almacen = this.seleccionado_almacen;
        this.seleccionado_almacen = seleccionado_almacen;
        firePropertyChange(PROP_SELECCIONADO_ALMACEN, oldSeleccionado_almacen, seleccionado_almacen);
    }

    /**
     * Get the value of seleccionado_producto_venta
     *
     * @return the value of seleccionado_producto_venta
     */
    public ProdcutoVentaPedidoModel getSeleccionado_producto_venta() {
        return seleccionado_producto_venta;
    }

    /**
     * Set the value of seleccionado_producto_venta
     *
     * @param seleccionado_producto_venta new value of
     * seleccionado_producto_venta
     */
    public void setSeleccionado_producto_venta(ProdcutoVentaPedidoModel seleccionado_producto_venta) {
        ProdcutoVentaPedidoModel oldSeleccionado_producto_venta = this.seleccionado_producto_venta;
        this.seleccionado_producto_venta = seleccionado_producto_venta;
        firePropertyChange(PROP_SELECCIONADO_PRODUCTO_VENTA, oldSeleccionado_producto_venta, seleccionado_producto_venta);
    }

    /**
     * Get the value of seleccionado_ipv_ventas
     *
     * @return the value of seleccionado_ipv_ventas
     */
    public IpvVentaRegistro getSeleccionado_ipv_ventas() {
        return seleccionado_ipv_ventas;
    }

    /**
     * Set the value of seleccionado_ipv_ventas
     *
     * @param seleccionado_ipv_ventas new value of seleccionado_ipv_ventas
     */
    public void setSeleccionado_ipv_ventas(IpvVentaRegistro seleccionado_ipv_ventas) {
        IpvVentaRegistro oldSeleccionado_ipv_ventas = this.seleccionado_ipv_ventas;
        this.seleccionado_ipv_ventas = seleccionado_ipv_ventas;
        firePropertyChange(PROP_SELECCIONADO_IPV_VENTAS, oldSeleccionado_ipv_ventas, seleccionado_ipv_ventas);
    }

    /**
     * Get the value of lista_insumo_pedido_model
     *
     * @return the value of lista_insumo_pedido_model
     */
    public ArrayListModel<InsumoPedidoModel> getLista_insumo_pedido_model() {
        return lista_insumo_pedido_model;
    }

    /**
     * Set the value of lista_insumo_pedido_model
     *
     * @param lista_insumo_pedido_model new value of lista_insumo_pedido_model
     */
    public void setLista_insumo_pedido_model(ArrayListModel<InsumoPedidoModel> lista_insumo_pedido_model) {
        ArrayListModel<InsumoPedidoModel> oldLista_insumo_pedido_model = this.lista_insumo_pedido_model;
        this.lista_insumo_pedido_model = lista_insumo_pedido_model;
        firePropertyChange(PROP_LISTA_INSUMO_PEDIDO_MODEL, oldLista_insumo_pedido_model, lista_insumo_pedido_model);
    }

    /**
     * Get the value of lista_almacen
     *
     * @return the value of lista_almacen
     */
    public ArrayListModel<Almacen> getLista_almacen() {
        return lista_almacen;
    }

    /**
     * Set the value of lista_almacen
     *
     * @param lista_almacen new value of lista_almacen
     */
    public void setLista_almacen(ArrayListModel<Almacen> lista_almacen) {
        ArrayListModel<Almacen> oldLista_almacen = this.lista_almacen;
        this.lista_almacen = lista_almacen;
        firePropertyChange(PROP_LISTA_ALMACEN, oldLista_almacen, lista_almacen);
    }

    /**
     * Get the value of lista_producto_venta_model
     *
     * @return the value of lista_producto_venta_model
     */
    public ArrayListModel<ProdcutoVentaPedidoModel> getLista_producto_venta_model() {
        return lista_producto_venta_model;
    }

    /**
     * Set the value of lista_producto_venta_model
     *
     * @param lista_producto_venta_model new value of lista_producto_venta_model
     */
    public void setLista_producto_venta_model(ArrayListModel<ProdcutoVentaPedidoModel> lista_producto_venta_model) {
        ArrayListModel<ProdcutoVentaPedidoModel> oldLista_producto_venta_model = this.lista_producto_venta_model;
        this.lista_producto_venta_model = lista_producto_venta_model;
        firePropertyChange(PROP_LISTA_PRODUCTO_VENTA_MODEL, oldLista_producto_venta_model, lista_producto_venta_model);
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
        this.lista_ipv_venta_registro = lista_ipv_venta_registro;
        firePropertyChange(PROP_LISTA_IPV_VENTA_REGISTRO, oldLista_ipv_venta_registro, lista_ipv_venta_registro);
    }

}
