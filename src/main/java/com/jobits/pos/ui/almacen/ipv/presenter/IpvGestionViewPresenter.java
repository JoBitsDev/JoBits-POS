/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.almacen.ipv.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jhw.swing.material.standars.MaterialIcons;
import com.jobits.pos.controller.almacen.IPVController;
import com.jobits.pos.controller.almacen.IPVService;
import com.jobits.pos.controller.almacen.PedidoIpvVentasController;
import com.jobits.pos.cordinator.DisplayType;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.domain.models.Cocina;
import com.jobits.pos.domain.models.IpvRegistro;
import com.jobits.pos.domain.models.IpvVentaRegistro;
import com.jobits.pos.domain.models.Venta;
import com.jobits.pos.main.Application;
import com.jobits.pos.ui.almacen.ipv.IPVPedidoVentasView;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.utils.LongProcessActionServiceImpl;
import java.util.List;
import java.util.Optional;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class IpvGestionViewPresenter extends AbstractViewPresenter<IpvGestionViewModel> {

    public static String ACTION_CAMBIAR_FECHA_IPV = "Fecha",
            ACTION_CAMBIAR_FECHA_IPV_VENTA = "Fecha ipv",
            ACTION_CAMBIAR_COCINA = "Punto de elaboracion",
            ACTION_OCULTAR_PRODUCTOS_IPV = "Ocultar insumos no utilizados",
            ACTION_OCULTAR_PRODUCTOS_IPV_VENTA = "Ocultar productos no utilizados",
            ACTION_DAR_ENTRADA_IPV_REGISTROS = "Entrada Registro",
            ACTION_DAR_ENTRADA_IPV_VENTA = "Entrada Ipv",
            ACTION_IMPRIMIR_IPV = "Imprimir registros",
            ACTION_IMPRIMIR_IPV_VENTA = "Imprimir Ipv venta",
            ACTION_NUEVO_PEDIDO_IPV_VENTA = "Nuevo Pedido",
            ACTION_ENVIAR_IPV_TO_IPV = "Enviar IPV to IPV",
            ACTION_AJUSTAR_IPV = "Ajustar consumo";

    private IPVService service;

    public IpvGestionViewPresenter(IPVController controller) {
        super(new IpvGestionViewModel());
        this.service = controller;
        getBean().setLista_punto_elaboracion(new ArrayListModel<>(controller.getCocinaList()));
        getBean().setPunto_elaboracion_seleccionado(getBean().getLista_punto_elaboracion().get(0));
        getBean().addPropertyChangeListener(IpvGestionViewModel.PROP_PUNTO_ELABORACION_SELECCIONADO,
                (evt) -> {
                    onCocinaStateChange();
                });
        getBean().addPropertyChangeListener(IpvGestionViewModel.PROP_FECHA_SELECCIONADA,
                (evt) -> {
                    onFechaCambiadaIpv();
                });
        getBean().addPropertyChangeListener(IpvGestionViewModel.PROP_FECHA_IPV_VENTAS_SELECCIONADA,
                (evt) -> {
                    onFechaCambiadaIpvVenta();
                });
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_CAMBIAR_FECHA_IPV) {
            @Override
            public Optional doAction() {
                onFechaCambiadaIpv();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_CAMBIAR_FECHA_IPV_VENTA) {
            @Override
            public Optional doAction() {
                onFechaCambiadaIpvVenta();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_OCULTAR_PRODUCTOS_IPV) {
            @Override
            public Optional doAction() {
                onOcultarProductosIpv();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_OCULTAR_PRODUCTOS_IPV_VENTA) {
            @Override
            public Optional doAction() {
                onOcultarProductosIpvVentas();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_CAMBIAR_COCINA) {
            @Override
            public Optional doAction() {
                new LongProcessActionServiceImpl("Cargando ipvs") {
                    @Override
                    protected void longProcessMethod() {
                        onCocinaStateChange();
                    }
                }.performAction(null);
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_DAR_ENTRADA_IPV_REGISTROS) {
            @Override
            public Optional doAction() {
                onDarEntradaIpv();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_DAR_ENTRADA_IPV_VENTA) {
            @Override
            public Optional doAction() {
                onDarEntradaIpvVentas();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_IMPRIMIR_IPV) {
            @Override
            public Optional doAction() {
                onImprimirIpv();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_IMPRIMIR_IPV_VENTA) {
            @Override
            public Optional doAction() {
                onImprimirIpvVentas();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_NUEVO_PEDIDO_IPV_VENTA) {
            @Override
            public Optional doAction() {
                onNuevoPedido();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_AJUSTAR_IPV) {
            @Override
            public Optional doAction() {
                onAjustarIpv();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_ENVIAR_IPV_TO_IPV) {
            @Override
            public Optional doAction() {
                onTransferirAIPV();
                return Optional.empty();
            }
        });

    }

    private void onFechaCambiadaIpv() {
        Application.getInstance().getBackgroundWorker().processInBackground(() -> {
            if (getBean().getPunto_elaboracion_seleccionado() != null) {
                Venta v = selectFecha(service.getVentasInRange(getBean().getFecha_ipv_seleccionada()));
                if (v != null) {
                    getBean().setLista_ipv_registro(new ArrayListModel<>(
                            service.getIpvRegistroList(
                                    getBean().getPunto_elaboracion_seleccionado(), v.getId())));
                    getBean().setCheck_ocultar_productos_ipv(false);
                }
            }
        });
    }

    private void onFechaCambiadaIpvVenta() {
        Application.getInstance().getBackgroundWorker().processInBackground(() -> {
            if (getBean().getPunto_elaboracion_seleccionado() != null) {
                Venta v = selectFecha(service.getVentasInRange(getBean().getFecha_ipv_ventas_seleccionada()));
                if (v != null) {
                    getBean().setLista_ipv_venta_registro(new ArrayListModel<>(
                            service.getIpvRegistroVentaList(getBean().
                                    getPunto_elaboracion_seleccionado(), v.getId())));
                    getBean().setCheck_ocultar_productos_ipv_venta(false);
                }
            }
        });
    }

    private void onCocinaStateChange() {
        Application.getInstance().getBackgroundWorker().processInBackground(() -> {
            if (getBean().getFecha_ipv_seleccionada() != null) {
                onFechaCambiadaIpv();
            }
            if (getBean().getFecha_ipv_ventas_seleccionada() != null) {
                onFechaCambiadaIpvVenta();
            }

        });

    }

    private void onOcultarProductosIpv() {
        if (getBean().isCheck_ocultar_productos_ipv()) {
            for (int i = 0; i < getBean().getLista_ipv_registro().getSize();) {
                IpvRegistro x = getBean().getLista_ipv_registro().get(i);
                if (x.getConsumo() == 0 && x.getDisponible() == 0) {
                    getBean().getLista_ipv_registro().remove(i);
                } else {
                    i++;
                }
            }
        } else {
            onFechaCambiadaIpv();
        }

    }

    private void onOcultarProductosIpvVentas() {
        if (getBean().isCheck_ocultar_productos_ipv_venta()) {
            for (int i = 0; i < getBean().getLista_ipv_venta_registro().getSize();) {
                IpvVentaRegistro x = getBean().getLista_ipv_venta_registro().get(i);
                if (x.getVenta() == 0 && x.getDisponible() == 0) {
                    getBean().getLista_ipv_venta_registro().remove(i);
                } else {
                    i++;
                }
            }
        } else {
            onFechaCambiadaIpvVenta();
        }
    }

    private void onDarEntradaIpv() {
        service.darEntradaExistencia(getBean().getIpv_registro_seleciconado());

    }

    private void onDarEntradaIpvVentas() {
        service.darEntradaIPV(getBean().getIpv_venta_registro_seleccionado());

    }

    private void onImprimirIpv() {//TODO:impresion donde y quien
    }

    private void onImprimirIpvVentas() {//TODO:impresion donde y quien

    }

    private void onNuevoPedido() {//TODO: mojon aqui

        Cocina cocina = getBean().getPunto_elaboracion_seleccionado();
        NavigationService.getInstance().navigateTo(IPVPedidoVentasView.VIEW_NAME,
                new IPVPedidoVentasViewPresenter(
                        new PedidoIpvVentasController(
                                getBean().getLista_ipv_venta_registro(), cocina)),
                DisplayType.POPUP);

//        new PedidoIpvVentasView(
//                Application.getInstance().getMainWindow(),
//                getBean().getLista_ipv_venta_registro(), getBean().getPunto_elaboracion_seleccionado());
        onCocinaStateChange();

    }

    private void onAjustarIpv() {
        service.ajustarConsumo(getBean().getIpv_registro_seleciconado());
    }

    private Venta selectFecha(List<Venta> ventas) {
        if (ventas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay ventas registradas", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (ventas.size() == 1) {
            return ventas.get(0);
        } else {
            JComboBox<Venta> jComboBox1 = new JComboBox<>();
            jComboBox1.setModel(new DefaultComboBoxModel<>(ventas.toArray(new Venta[0])));
            jComboBox1.setSelectedItem(ventas.get(0));
            Object[] options = {"Seleccionar", "Cancelar"};
            //                     yes        no       cancel
            int confirm = JOptionPane.showOptionDialog(
                    null,
                    jComboBox1,
                    "Ventas",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.YES_NO_OPTION,
                    MaterialIcons.MONETIZATION_ON,
                    options,
                    options[0]);
            switch (confirm) {
                case JOptionPane.YES_OPTION:
                    return (Venta) jComboBox1.getSelectedItem();
                case JOptionPane.NO_OPTION:
                    break;
                default:
                    break;
            }
        }
        return null;
    }

    private void onTransferirAIPV() {
        service.transferirIPVRegistro(getBean().getIpv_registro_seleciconado());
    }
}
