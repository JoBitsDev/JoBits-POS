/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.almacen.ipv.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.almacen.AlmacenListService;
import com.root101.swing.material.standards.MaterialIcons;
import com.jobits.pos.controller.almacen.IPVService;
import com.jobits.pos.controller.almacen.PedidoIpvVentasService;
import com.jobits.pos.controller.insumo.InsumoListService;
import com.jobits.pos.controller.puntoelaboracion.PuntoElaboracionListService;
import com.jobits.pos.controller.venta.VentaDetailService;
import com.jobits.pos.cordinator.DisplayType;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.core.domain.models.Almacen;
import com.jobits.pos.core.domain.models.Cocina;
import com.jobits.pos.core.domain.models.Insumo;
import com.jobits.pos.core.domain.models.IpvRegistro;
import com.jobits.pos.core.domain.models.IpvVentaRegistro;
import com.jobits.pos.core.domain.models.Venta;
import com.jobits.pos.main.Application;
import com.jobits.pos.recursos.R;
import com.jobits.pos.servicios.impresion.Impresion;
import com.jobits.pos.servicios.impresion.formatter.IPVRegistroFomatter;
import com.jobits.pos.servicios.impresion.formatter.IPVVentaRegistroFomatter;
import com.jobits.pos.ui.almacen.ipv.IPVPedidoVentasView;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.utils.LongProcessActionServiceImpl;
import com.jobits.pos.ui.utils.NumberPad;
import com.jobits.pos.utils.utils;
import com.root101.clean.core.domain.services.ResourceHandler;
import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Optional;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
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
            ACTION_IMPRIMIR_IPV_REGISTRO = "Imprimir registros",
            ACTION_IMPRIMIR_IPV_VENTA_REGISTRO = "Imprimir Ipv venta",
            ACTION_NUEVO_PEDIDO_IPV_VENTA = "Nuevo Pedido",
            ACTION_ENVIAR_IPV_TO_IPV = "Enviar IPV to IPV",
            ACTION_ENVIAR_IPV_TO_ALMACEN = "Enviar IPV to Almacen",
            ACTION_AJUSTAR_IPV = "Ajustar consumo",
            ACTION_AJUSTAR_COSTO_IPV = "Ajustar costo",
            ACTION_REGISTRAR_IPV_REGISTRO = "Registrar IPV Registro",
            ACTION_ELIMINAR_IPV_REGISTRO = "Eliminar IPV Registro";

    private IPVService service;
    private PuntoElaboracionListService cocinaService = PosDesktopUiModule.getInstance().getImplementation(PuntoElaboracionListService.class);
    private AlmacenListService almacenService = PosDesktopUiModule.getInstance().getImplementation(AlmacenListService.class);
    private VentaDetailService ventaService = PosDesktopUiModule.getInstance().getImplementation(VentaDetailService.class);
    private InsumoListService insumoService = PosDesktopUiModule.getInstance().getImplementation(InsumoListService.class);

    public IpvGestionViewPresenter(IPVService controller) {
        super(new IpvGestionViewModel());
        this.service = controller;
        getBean().setLista_punto_elaboracion(new ArrayListModel<>(cocinaService.findAll()));
        getBean().setPunto_elaboracion_seleccionado(getBean().getLista_punto_elaboracion().get(0));
        getBean().setLista_insumos(new ArrayListModel<>(insumoService.findAll()));
        getBean().setInsumo_seleccionado(null);
        addBeanPropertyChangeListener((PropertyChangeEvent evt) -> {
            switch (evt.getPropertyName()) {
                case IpvGestionViewModel.PROP_PUNTO_ELABORACION_SELECCIONADO:
                    onCocinaStateChange();
                    break;
                case IpvGestionViewModel.PROP_FECHA_SELECCIONADA:
                    onFechaCambiadaIpv();
                    break;
                case IpvGestionViewModel.PROP_FECHA_IPV_VENTAS_SELECCIONADA:
                    onFechaCambiadaIpvVenta();
                    break;
            }
            recalcularTotal();
        });
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_CAMBIAR_FECHA_IPV) {
            @Override
            public Optional doAction() {
                onFechaCambiadaIpv();
                recalcularTotal();
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
                recalcularTotal();
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
                        recalcularTotal();
                    }
                }.performAction(null);
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_DAR_ENTRADA_IPV_REGISTROS) {
            @Override
            public Optional doAction() {
                onDarEntradaIpv();
                recalcularTotal();
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
        registerOperation(new AbstractViewAction(ACTION_IMPRIMIR_IPV_REGISTRO) {
            @Override
            public Optional doAction() {
                onImprimirIpvRegistro();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_IMPRIMIR_IPV_VENTA_REGISTRO) {
            @Override
            public Optional doAction() {
                onImprimirIPVVentaRegistroClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_NUEVO_PEDIDO_IPV_VENTA) {
            @Override
            public Optional doAction() {
                onNuevoPedido();
                recalcularTotal();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_AJUSTAR_IPV) {
            @Override
            public Optional doAction() {
                onAjustarIpv();
                recalcularTotal();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_ENVIAR_IPV_TO_IPV) {
            @Override
            public Optional doAction() {
                onTransferirAIPV();
                recalcularTotal();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_ENVIAR_IPV_TO_ALMACEN) {
            @Override
            public Optional doAction() {
                onTransferirAAlmacen();
                recalcularTotal();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_REGISTRAR_IPV_REGISTRO) {
            @Override
            public Optional doAction() {
                onRegistrarIpvRegistro();
                recalcularTotal();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_AJUSTAR_COSTO_IPV) {
            @Override
            public Optional doAction() {
                onAjustarCostoClick();
                recalcularTotal();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_ELIMINAR_IPV_REGISTRO) {
            @Override
            public Optional doAction() {
                onEliminarIpvRegistroClick();
                return Optional.empty();
            }
        });
    }

    private void recalcularTotal() {
        List<IpvRegistro> list = getBean().getLista_ipv_registro();
        float value = 0f;
        for (IpvRegistro ipv : list) {
            value += (ipv.getConsumoUnico() * ipv.getPrecioCosto());
        }
        getBean().setTotal_ipv_registro(utils.setDosLugaresDecimales(value));
    }

    private void onEliminarIpvRegistroClick() {
        IpvRegistro instance = getBean().getIpv_registro_seleciconado();
        if (instance == null) {
            throw new IllegalArgumentException("Seleccione un ipv primero");
        }
        if (instance.getIpv() != null) {
            service.destroy(instance.getIpv());
            getBean().setLista_ipv_registro(new ArrayListModel<>(
                    service.getIpvRegistroList(
                            getBean().getPunto_elaboracion_seleccionado(),
                            getBean().getVenta_ipv_seleccionada().getId(),
                            ventaService.getConsumoPorInsumo(
                                    getBean().getVenta_ipv_seleccionada().getId(),
                                    getBean().getPunto_elaboracion_seleccionado()))));
        }
    }

    private void onAjustarCostoClick() {
        IpvRegistro instance = getBean().getIpv_registro_seleciconado();
        Float cantidad = new NumberPad().showView();
        if (cantidad != null && instance != null) {
            if (JOptionPane.showConfirmDialog(null,
                    "Desea ajustar el costo de " + instance.getIpv().getInsumo() + " a " + cantidad + " " + R.COIN_SUFFIX,
                    ResourceHandler.getString("label_confirmacion"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)
                    == JOptionPane.YES_OPTION) {
                service.ajustarCosto(instance, cantidad);
            }
        }
    }

    private void onRegistrarIpvRegistro() {
        Insumo insumo = getBean().getInsumo_seleccionado();
        Cocina cocina = getBean().getPunto_elaboracion_seleccionado();
        Venta venta = getBean().getVenta_ipv_seleccionada();
        if (insumo == null) {
            throw new IllegalArgumentException("Seleccione un insumo primero");
        }
        if (cocina == null) {
            throw new IllegalArgumentException("Seleccione una cocina primero");
        }
        if (venta == null) {
            throw new IllegalArgumentException("Seleccione una venta primero");
        }
        service.registrarIPV(insumo, cocina, venta.getId());
        getBean().setLista_ipv_registro(new ArrayListModel<>(
                service.getIpvRegistroList(
                        getBean().getPunto_elaboracion_seleccionado(),
                        getBean().getVenta_ipv_seleccionada().getId(),
                        ventaService.getConsumoPorInsumo(
                                getBean().getVenta_ipv_seleccionada().getId(),
                                getBean().getPunto_elaboracion_seleccionado()))));
    }

    private void onFechaCambiadaIpv() {
        if (getBean().getFecha_ipv_seleccionada() != null) {
            Application.getInstance().getBackgroundWorker().processInBackground(() -> {
                if (getBean().getPunto_elaboracion_seleccionado() != null) {
                    getBean().setVenta_ipv_seleccionada(selectFecha(ventaService.getVentasDeFecha(getBean().getFecha_ipv_seleccionada())));
                    if (getBean().getVenta_ipv_seleccionada() != null) {
                        getBean().setLista_ipv_registro(new ArrayListModel<>(
                                service.getIpvRegistroList(
                                        getBean().getPunto_elaboracion_seleccionado(),
                                        getBean().getVenta_ipv_seleccionada().getId(),
                                        ventaService.getConsumoPorInsumo(
                                                getBean().getVenta_ipv_seleccionada().getId(),
                                                getBean().getPunto_elaboracion_seleccionado()))));
                        getBean().setCheck_ocultar_productos_ipv(false);
                        getBean().setFecha_ipv_ventas_seleccionada(null);
                        getBean().setLista_ipv_venta_registro(new ArrayListModel<>());
                    }
                }
            });
        }
    }

    private void onFechaCambiadaIpvVenta() {
        if (getBean().getFecha_ipv_ventas_seleccionada() != null) {
            Application.getInstance().getBackgroundWorker().processInBackground(() -> {
                if (getBean().getPunto_elaboracion_seleccionado() != null) {
                    getBean().setVenta_ipv_ventas_seleccionada(selectFecha(ventaService.getVentasDeFecha(getBean().getFecha_ipv_ventas_seleccionada())));
                    if (getBean().getVenta_ipv_ventas_seleccionada() != null) {
                        getBean().setLista_ipv_venta_registro(new ArrayListModel<>(
                                service.getIpvRegistroVentaList(getBean().
                                        getPunto_elaboracion_seleccionado(),
                                        getBean().getVenta_ipv_ventas_seleccionada().getId())));
                        getBean().setCheck_ocultar_productos_ipv_venta(false);
                        getBean().setFecha_ipv_seleccionada(null);
                        getBean().setLista_ipv_registro(new ArrayListModel<>());
                    }
                }
            });
        }
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
        if (getBean().getIpv_registro_seleciconado() != null) {
            Insumo instance = getBean().getIpv_registro_seleciconado().getIpv().getInsumo();
            Float cantidad = new NumberPad().showView();
            Venta fecha = getBean().getVenta_ipv_seleccionada();
            Cocina cocina = getBean().getPunto_elaboracion_seleccionado();
            if (cantidad != null && instance != null && fecha != null && cocina != null) {
                if (JOptionPane.showConfirmDialog(null, "Desea dar entrada a " + cantidad + " de " + instance,
                        ResourceHandler
                                .getString("label_confirmacion"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)
                        == JOptionPane.YES_OPTION) {
                    service.darEntradaExistencia(instance, cocina, fecha.getId(), cantidad);
                }
            }
        }

    }

    private void onDarEntradaIpvVentas() {
        IpvVentaRegistro instance = getBean().getIpv_venta_registro_seleccionado();
        Float cantidad = new NumberPad().showView();
        if (cantidad != null && instance != null) {
            if (JOptionPane.showConfirmDialog(null, "Desea dar entrada a " + cantidad + " de " + instance.getProductoVenta(),
                    ResourceHandler.getString("label_confirmacion"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)
                    == JOptionPane.YES_OPTION) {
                service.darEntradaIPV(instance, cantidad);
            }
        }

    }

    private void onNuevoPedido() {
        PedidoIpvVentasService pedidoService = PosDesktopUiModule.getInstance().getImplementation(PedidoIpvVentasService.class);
        AlmacenListService almacenService = PosDesktopUiModule.getInstance().getImplementation(AlmacenListService.class);
        NavigationService.getInstance().navigateTo(IPVPedidoVentasView.VIEW_NAME,
                new IPVPedidoVentasViewPresenter(pedidoService, almacenService,
                        getBean().getPunto_elaboracion_seleccionado(),
                        getBean().getVenta_ipv_ventas_seleccionada(),
                        getBean().getLista_ipv_venta_registro()), DisplayType.POPUP);
        onCocinaStateChange();
    }

    private void onAjustarIpv() {
        IpvRegistro instance = getBean().getIpv_registro_seleciconado();
        Float cantidad = new NumberPad().showView();
        if (cantidad != null && instance != null) {
            if (JOptionPane.showConfirmDialog(null, "Desea ajustar el consumo de " + instance.getIpv().getInsumo() + " a " + cantidad,
                    ResourceHandler.getString("label_confirmacion"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)
                    == JOptionPane.YES_OPTION) {
                service.ajustarConsumo(instance, cantidad);
            }
        }
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
        List<Cocina> cocinas = cocinaService.findAll();
        Cocina cocina;
        if (!cocinas.isEmpty()) {
            JComboBox<Cocina> jComboBox1 = new JComboBox<>();
            jComboBox1.setModel(new DefaultComboBoxModel<>(cocinas.toArray(new Cocina[0])));
            jComboBox1.setSelectedItem(cocinas.get(0));
            Object[] options = {"Seleccionar", "Cancelar"};
            //                     yes        no       cancel
            int confirm = JOptionPane.showOptionDialog(
                    null,
                    jComboBox1,
                    "Puntos de Elaboracion",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.YES_NO_OPTION,
                    new ImageIcon(getClass().getResource("/restManager/resources/icons pack/enviar_indigo.png")),
                    options,
                    options[0]);
            switch (confirm) {
                case JOptionPane.YES_OPTION:
                    cocina = (Cocina) jComboBox1.getSelectedItem();
                    if (cocina != null) {
                        Float cantidad = new NumberPad().showView();
                        if (cantidad != null) {
                            service.transferirIPVRegistro(getBean().getIpv_registro_seleciconado(), cocina, cantidad);
                        }
                    }
                case JOptionPane.NO_OPTION:
                default:
                    break;
            }
        } else {
            throw new IllegalArgumentException("No hay Puntos de Elaboracion registrados en el sistema");
        }
    }

    private void onTransferirAAlmacen() {
        List<Almacen> almacenes = almacenService.findAll();
        Almacen almacen;
        if (!almacenes.isEmpty()) {
            JComboBox<Almacen> jComboBox1 = new JComboBox<>();
            jComboBox1.setModel(new DefaultComboBoxModel<>(almacenes.toArray(new Almacen[0])));
            jComboBox1.setSelectedItem(almacenes.get(0));
            Object[] options = {"Seleccionar", "Cancelar"};
            //                     yes        no       cancel
            int confirm = JOptionPane.showOptionDialog(
                    null,
                    jComboBox1,
                    "Almacenes",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.YES_NO_OPTION,
                    new ImageIcon(getClass().getResource("/restManager/resources/icons pack/enviar_indigo.png")),
                    options,
                    options[0]);
            switch (confirm) {
                case JOptionPane.YES_OPTION:
                    almacen = (Almacen) jComboBox1.getSelectedItem();
                    if (almacen != null) {
                        Float cantidad = new NumberPad().showView();
                        if (cantidad != null) {
                            service.transferirIPVRegistroToAlmacen(getBean().getIpv_registro_seleciconado(), almacen, cantidad);
                        }
                    }
                case JOptionPane.NO_OPTION:
                default:
                    break;
            }
        } else {
            throw new IllegalArgumentException("No hay Puntos de Elaboracion registrados en el sistema");
        }
    }

    private void onImprimirIPVVentaRegistroClick() {
        String[] options = {"Impresora Regular", "Impresora Ticket", "Cancelar"};
        int selection = JOptionPane.showOptionDialog(null,
                ResourceHandler.getString("dialog_seleccionar_manera_imprimir"),
                ResourceHandler.getString("label_impresion"), JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
        switch (selection) {
            case 0:
                firePropertyChange("ImprimirTablaIPVVentaRegistro", null, null);
                break;//impresion normal
            case 1:
                Impresion i = new Impresion();
                i.print(new IPVVentaRegistroFomatter(getBean().getLista_ipv_venta_registro()), null);
                break;//impresion ticket
            default:
                break;//cancelado
        }
    }

    private void onImprimirIpvRegistro() {
        String[] options = {"Impresora Regular", "Impresora Ticket", "Cancelar"};
        int selection = JOptionPane.showOptionDialog(null,
                ResourceHandler.getString("dialog_seleccionar_manera_imprimir"),
                ResourceHandler.getString("label_impresion"), JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
        switch (selection) {
            case 0:
                firePropertyChange("ImprimirTablaIPVRegistro", null, null);
                break;//impresion normal
            case 1:
                Impresion i = new Impresion();
                i.print(new IPVRegistroFomatter(getBean().getLista_ipv_registro()), null);
                break;//impresion ticket
            default:
                break;//cancelado
        }
    }
}
