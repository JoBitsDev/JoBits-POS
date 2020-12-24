/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.almacen.ipv.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.almacen.PedidoIpvVentasService;
import com.jobits.pos.domain.InsumoPedidoModel;
import com.jobits.pos.domain.ProdcutoVentaPedidoModel;
import com.jobits.pos.domain.VentaDAO1;
import com.jobits.pos.domain.models.IpvVentaRegistro;
import com.jobits.pos.domain.models.ProductoInsumo;
import com.jobits.pos.main.Application;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.utils.NumberPad;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.swing.JOptionPane;

/**
 *
 * @author Home
 */
public class IPVPedidoVentasViewPresenter extends AbstractViewPresenter<IPVPedidoVentasViewModel> {

    public static String ACTION_CANCELAR = "Cancelar";
    public static String ACTION_ACEPTAR = "Aceptar";

    public static String ACTION_ELIMINAR_IPV = "Eliminar IPV";
    public static String ACTION_AGREGAR_IPV = "Agregr IPV";

    private PedidoIpvVentasService service;

    public IPVPedidoVentasViewPresenter(PedidoIpvVentasService controller) {
        super(new IPVPedidoVentasViewModel());
        this.service = controller;
        fillBeanData();
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_AGREGAR_IPV) {
            @Override
            public Optional doAction() {
                onAgregarIPVClick();
                actualizarTablaPedido();
                return Optional.empty();
            }
        }
        );
        registerOperation(new AbstractViewAction(ACTION_ELIMINAR_IPV) {
            @Override
            public Optional doAction() {
                onEliminarIPVClick();
                actualizarTablaPedido();
                return Optional.empty();
            }

        }
        );
        registerOperation(new AbstractViewAction(ACTION_CANCELAR) {
            @Override
            public Optional doAction() {
                Application.getInstance().getNavigator().navigateUp();
                return Optional.empty();
            }
        }
        );
        registerOperation(new AbstractViewAction(ACTION_ACEPTAR) {
            @Override
            public Optional doAction() {
                onAceptarClick();
                return Optional.empty();
            }

        }
        );
    }

    private void onAceptarClick() {
        if (service.realizarPedidoDeIpv(
                getBean().getLista_insumo_pedido_model(),
                getBean().getLista_producto_venta_model(),
                service.getElaboracion(),
                getBean().getSeleccionado_almacen())) {
            Application.getInstance().getNavigator().navigateUp();
        }
    }

    private void onAgregarIPVClick() {
        IpvVentaRegistro selected = getBean().getSeleccionado_ipv_ventas();
        if (selected != null) {
            float cantidad = new NumberPad(null).showView();
            getBean().getLista_producto_venta_model().add(
                    new ProdcutoVentaPedidoModel(selected, cantidad));
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un Producto de IPV primero");
        }
    }

    private void onEliminarIPVClick() {
        ProdcutoVentaPedidoModel selected = getBean().getSeleccionado_producto_venta();
        if (selected != null) {
            getBean().getLista_producto_venta_model().remove(selected);
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un Producto de IPV primero");
        }
    }

    private void fillBeanData() {
        getBean().setLista_ipv_venta_registro(new ArrayListModel<>(service.getIpvProductList()));
        getBean().setLista_almacen(new ArrayListModel<>(service.getAlmacenList()));
        getBean().setSeleccionado_almacen(getBean().getLista_almacen().get(0));
    }

    private void actualizarTablaPedido() {
        List<ProdcutoVentaPedidoModel> porPedir = getBean().getLista_producto_venta_model();
        ArrayList<ProductoInsumo> productoInsumo = new ArrayList<>();
        for (ProdcutoVentaPedidoModel x : porPedir) {
            VentaDAO1.joinListsProductoInsumos(productoInsumo, new ArrayList<>(x.getIpvProducto().getProductoVenta().getProductoInsumoList()), x.getCantidad());
        }
        List<InsumoPedidoModel> pedido = new ArrayList<>();
        for (ProductoInsumo x : productoInsumo) {
            pedido.add(new InsumoPedidoModel(x.getInsumo(), x.getCantidad()));
        }
        getBean().getLista_insumo_pedido_model().clear();
        getBean().getLista_insumo_pedido_model().addAll(pedido);
    }

}
