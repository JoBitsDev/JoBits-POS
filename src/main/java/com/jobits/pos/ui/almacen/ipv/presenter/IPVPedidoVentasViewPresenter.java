/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.almacen.ipv.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.core.domain.InsumoPedidoModel;
import com.jobits.pos.core.domain.ProdcutoVentaPedidoModel;
import com.jobits.pos.core.domain.VentaDAO1;
import com.jobits.pos.core.domain.models.Cocina;
import com.jobits.pos.core.domain.models.ProductoInsumo;
import com.jobits.pos.inventario.core.almacen.domain.IpvVentaRegistro;
import com.jobits.pos.inventario.core.almacen.domain.wrapper.PedidoIpvWrapper;
import com.jobits.pos.inventario.core.almacen.usecase.AlmacenManageService;
import com.jobits.pos.inventario.core.almacen.usecase.PedidoIpvVentasService;
import com.jobits.pos.main.Application;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.utils.NumberPad;
import com.root101.clean.core.app.services.utils.TipoNotificacion;
import com.root101.clean.core.domain.services.ResourceHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Home
 */
public class IPVPedidoVentasViewPresenter extends AbstractViewPresenter<IPVPedidoVentasViewModel> {

    public static String ACTION_CANCELAR = "Cancelar";
    public static String ACTION_ACEPTAR = "Aceptar";

    public static String ACTION_ELIMINAR_IPV = "Eliminar IPV";
    public static String ACTION_AGREGAR_IPV = "Agregr IPV";

    private PedidoIpvVentasService service;
    private AlmacenManageService almacenService = PosDesktopUiModule.getInstance().getImplementation(AlmacenManageService.class);
    private Cocina cocina;
    private Integer venta;
    private List<IpvVentaRegistro> ipvProductList;

    public IPVPedidoVentasViewPresenter(PedidoIpvVentasService service, Cocina cocina, Integer ventaId, List<IpvVentaRegistro> ipvProductList) {
        super(new IPVPedidoVentasViewModel());
        this.service = service;
        this.cocina = cocina;
        this.venta = ventaId;
        this.ipvProductList = ipvProductList;
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
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Esta seguro que desea ejecutar el pedido",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            service.realizarPedidoDeIpv(
                    new PedidoIpvWrapper(getBean().getLista_insumo_pedido_model(),
                            getBean().getLista_producto_venta_model()),
                    cocina.getCodCocina(),
                    getBean().getSeleccionado_almacen().getCodAlmacen(),
                    venta);
            Application.getInstance().getNavigator().navigateUp();
            Application.getInstance().getNotificationService().notify(ResourceHandler.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
        }
    }

    private void onAgregarIPVClick() {
        IpvVentaRegistro selected = getBean().getSeleccionado_ipv_ventas();
        if (selected != null) {
            if ((boolean) Application.getInstance().getNotificationService().
                    showDialog("Esta seguro que desea agregar: " + selected.getProductoVenta(),
                            TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
                Float cantidad = new NumberPad().showView();
                if (cantidad != null) {
                    getBean().getLista_producto_venta_model().add(
                            new ProdcutoVentaPedidoModel(selected, cantidad));
                }
            }
        } else {
            throw new IllegalArgumentException("Seleccione un Producto de IPV primero");
        }
    }

    private void onEliminarIPVClick() {
        ProdcutoVentaPedidoModel selected = getBean().getSeleccionado_producto_venta();
        if (selected != null) {
            if ((boolean) Application.getInstance().getNotificationService().
                    showDialog("Esta seguro que desea eliminar: " + selected.getIpvProducto().getProductoVenta(),
                            TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
                getBean().getLista_producto_venta_model().remove(selected);
            }
        } else {
            throw new IllegalArgumentException("Seleccione un Producto de IPV primero");
        }
    }

    private void fillBeanData() {
        getBean().setLista_ipv_venta_registro(new ArrayListModel<>(ipvProductList));
        getBean().setLista_almacen(new ArrayListModel<>(almacenService.findAll()));
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
