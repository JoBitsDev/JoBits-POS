/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.orden.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.cliente.core.domain.ClienteDomain;
import com.jobits.pos.cliente.core.usecase.ClienteUseCase;
import com.jobits.pos.controller.venta.OrdenService;
import com.jobits.pos.cordinator.DisplayType;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.core.domain.models.Orden;
import com.jobits.pos.core.domain.models.ProductovOrden;
import com.jobits.pos.core.repo.impl.ConfiguracionDAO;
import com.jobits.pos.main.Application;
import com.root101.clean.core.app.services.utils.TipoNotificacion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.utils.NumberPad;
import com.jobits.pos.ui.venta.orden.CalcularCambioView;
import com.jobits.pos.ui.venta.orden.DomicilioView;
import com.jobits.pos.ui.venta.orden.OrdenLogView;
import com.jobits.pos.ui.venta.orden.ProductoEnCalienteView;
import com.jobits.pos.utils.utils;
import java.beans.PropertyChangeEvent;
import java.util.Optional;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class OrdenDetailViewPresenter extends AbstractViewPresenter<OrdenDetailViewModel> {

    public static String ACTION_ADD_NOTA = "Agregar Nota";

    public static String ACTION_ADD_PRODUCTO = "Agregar";
    public static String ACTION_ADD_PRODUCTO_IN_HOT = "Producto Caliente";
    public static String ACTION_CERRAR_ORDEN = "Cerrar Orden";
    public static String ACTION_ENVIAR_ELABORAR = "Enviar a elaborar";
    public static String ACTION_IMPRIMIR_CIERRE_PARCIAL = "Cierre Parcial";
    public static String ACTION_REMOVE_PRODUCTO = "Eliminar";
    public static String ACTION_ELIMINAR_PARCIAL_PRODUCTO = "Eliminar Parcial";
    public static String ACTION_SHOW_LOGS = "Ver Registros";
    public static String ACTION_SET_AUTORIZO = "Autorizo";
    public static String ACTION_SET_AGREGO = "Agrego";
    public static String ACTION_SET_SUPORT_PANEL_VISIBLE = "Suport Panel Visible";
    public static String ACTION_SET_PORCIENTO = "Porciento";
    public static String ACTION_SET_DOMICILIO = "Domicilio";
    private String codOrden;

    private OrdenService ordenService = PosDesktopUiModule.getInstance().getImplementation(OrdenService.class);

    public static final String PROP_CHANGES = "Changes";

    private ClienteUseCase clienteservice = PosDesktopUiModule.getInstance().getImplementation(ClienteUseCase.class);

    public OrdenDetailViewPresenter(OrdenService controller) {
        super(new OrdenDetailViewModel());
        addListeners();
    }

    public OrdenDetailViewPresenter(String cod_orden, OrdenService controller) {
        this(controller);
        this.codOrden = cod_orden;
        refreshState();
    }

    public String getCodOrden() {
        if (this.codOrden == null) {
            throw new IllegalStateException("El codigo de la orden no puede ser nulo.");
        }
        return this.codOrden;
    }

    public void setCodOrden(String codOrden) {
        this.codOrden = codOrden;
        refreshState();
    }

    private void onAddNotaLCick() {
        ProductovOrden p = getBean().getProducto_orden_seleccionado();
        String nota;

        if (p == null) {
            throw new IllegalArgumentException("Seleccione un producto");
        }
        if (p.getNota() == null) {
            nota = JOptionPane.showInputDialog(null, "Introduzca la nota a adjuntar");
        } else {
            nota = JOptionPane.showInputDialog(null, "Introduzca la nota a adjuntar", p.getNota().getDescripcion());
        }
        ordenService.addNota(getCodOrden(), p, nota);
    }

    private void fireAddProducto() {

    }

    private void onAddProductoFromListClick() {
        if (getBean().getProducto_orden_seleccionado() == null) {
            throw new IllegalArgumentException("Seleccione un Producto Primero");
        }
        Float cantidad = new NumberPad().showView();
        if (cantidad != null) {
            if (getBean().isModo_agrego_activado()) {
                ordenService.addProduct(getCodOrden(), getBean().getProducto_orden_seleccionado().getProductoVenta(), cantidad, getBean().getProducto_orden_seleccionado());
            } else {
                ordenService.addProduct(getCodOrden(), getBean().getProducto_orden_seleccionado().getProductoVenta(), cantidad, null);
            }
            getBean().setLista_producto_orden((ordenService.findBy(getCodOrden()).getProductovOrdenList()));
        }

    }

    private void onCerrarOrdenClick() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Desea cerrar la orden", TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            boolean cerrarTicket = (boolean) Application.getInstance().getNotificationService().
                    showDialog("Desea imprimir un ticket de la orden", TipoNotificacion.DIALOG_CONFIRM).orElse(false);
            Orden o = ordenService.cerrarOrden(getCodOrden(), cerrarTicket);
            if (o != null) {
                NavigationService.getInstance().navigateTo(CalcularCambioView.VIEW_NAME,
                        new CalcularCambioViewPresenter(o), DisplayType.POPUP);
            }
        }
    }

    private void onEnviarAElaborarCLick() {
        ordenService.enviarACocina(getCodOrden());
        getBean().setLista_producto_orden(ordenService.findBy(getCodOrden()).getProductovOrdenList());
        Application.getInstance().getNotificationService().notify(R.RESOURCE_BUNDLE.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
    }

    private void onImprimirCierreParcial() {
        ordenService.imprimirPreTicket(getCodOrden());
        Application.getInstance().getNotificationService().notify(R.RESOURCE_BUNDLE.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
    }

    private void onRemoveProductoCLick() {
        ProductovOrden selected = getBean().getProducto_orden_seleccionado();
        if (selected == null) {
            throw new IllegalArgumentException("Seleccione un Producto Primero");
        }
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Esta seguro que desea eliminar: " + selected.getNombreProductoVendido(),
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            ordenService.removeProduct(getCodOrden(), selected,
                    getBean().getProducto_orden_seleccionado().getCantidad());
            getBean().setLista_producto_orden((ordenService.findBy(getCodOrden()).getProductovOrdenList()));
            Application.getInstance().getNotificationService().notify(R.RESOURCE_BUNDLE.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
        }
    }

    private void onRemoveParcialProductoCLick() {
        ProductovOrden selected = getBean().getProducto_orden_seleccionado();
        if (selected == null) {
            throw new IllegalArgumentException("Seleccione un Producto Primero");
        }
        Float value = new NumberPad().showView();
        if (value != null) {
            if ((boolean) Application.getInstance().getNotificationService().
                    showDialog("Esta seguro que desea eliminar (" + value + ") de: " + selected.getNombreProductoVendido(),
                            TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
                ordenService.removeProduct(getCodOrden(), selected, value);
                getBean().setLista_producto_orden((ordenService.findBy(getCodOrden()).getProductovOrdenList()));
                Application.getInstance().getNotificationService().notify(R.RESOURCE_BUNDLE.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
            }
        }
    }

    private void onSetAutorizoClick() {
        getBean().setEs_autorizo(!getBean().isEs_autorizo());
        ordenService.setDeLaCasa(getCodOrden(), getBean().isEs_autorizo());
        firePropertyChange(PROP_CHANGES, null, null);
    }

    private void onSetDomicilioClick() {
        if (codOrden != null) {
            Orden orden = ordenService.findBy(codOrden);
            if (orden != null) {
                NavigationService.getInstance().navigateTo(DomicilioView.VIEW_NAME,
                        new DomicilioViewPresenter(orden.getDireccionEnvio(), orden), DisplayType.POPUP);
                refreshState();
            }
        }
    }

    private void onSetAgregoClick() {
        getBean().setModo_agrego_activado(!getBean().isModo_agrego_activado());
    }

//    private void onSetPorcientoClick() {
//        ordenService.setPorciento(getCodOrden(), getBean().getPorciento_servicio());
//        getBean().setTotal_orden(utils.setDosLugaresDecimales(ordenService.getValorTotal(getCodOrden())));
//    }
    private void onVerDetallesClick() {
        ordenService.canViewOrdenLog(Application.getInstance().getLoggedUser(), codOrden);
        Application.getInstance().getNavigator().navigateTo(
                OrdenLogView.VIEW_NAME, new OrdenLogViewPresenter(codOrden), DisplayType.POPUP);
    }

    private void onSuperPanelVisibleClick() {
        getBean().setSuport_panel_visible(!getBean().isSuport_panel_visible());
    }

    private void onAddProductoInHotClick() {
        Application.getInstance().getNavigator().navigateTo(
                ProductoEnCalienteView.VIEW_NAME, new ProductoEnCalienteViewPresenter(codOrden), DisplayType.POPUP);
    }

    @Override
    protected Optional refreshState() {
        if (codOrden != null) {
            Orden instance = ordenService.findBy(getCodOrden());
            getBean().setEs_autorizo(instance.getDeLaCasa());
            getBean().setFecha_orden(R.DATE_FORMAT.format(instance.getVentafecha()));
            getBean().setHora_pedido(R.TIME_FORMAT.format(instance.getHoraComenzada()));
            getBean().setOrden_terminada(instance.getHoraTerminada() != null);
            getBean().setId_orden(instance.getCodOrden());
            getBean().setLista_general_productos_venta(ordenService.getPDVList(getCodOrden()));
            getBean().setModo_agrego_activado(false);
            getBean().setLista_producto_orden(instance.getProductovOrdenList());
            if (instance.getMesacodMesa() != null) {
                getBean().setMesa_pedido(instance.getMesacodMesa().getCodMesa());
            }
            getBean().setPorciento_servicio(instance.getPorciento());
            getBean().setTotal_orden(utils.setDosLugaresDecimales(ordenService.getValorTotalOrden(getCodOrden())));
            getBean().setUsuario(instance.getPersonalusuario().getUsuario());
            if (getBean().getPorciento_servicio() == 0) {
                getBean().setIcono_porciento(new ImageIcon(getClass().getResource(
                        "/restManager/resources/icons pack/porciento_gris.png")));
            } else {
                getBean().setIcono_porciento(new ImageIcon(getClass().getResource(
                        "/restManager/resources/icons pack/porciento_indigo.png")));
            }
            if (instance.isDomicilio()) {
                getBean().setDomicilio_icono(new ImageIcon(getClass().getResource(
                        "/restManager/resources/icons pack/domicilio_indigo.png")));
            } else {
                getBean().setDomicilio_icono(new ImageIcon(getClass().getResource(
                        "/restManager/resources/icons pack/domicilio_gris.png")));
            }
            getBean().setLista_clientes(new ArrayListModel<>(clienteservice.findAll()));
            if (instance.getClienteIdCliente() != null) {
                getBean().setCliente_seleccionado(clienteservice.findBy(instance.getClienteIdCliente()));
            } else {
                getBean().setCliente_seleccionado(null);
            }
            if (getBean().getLista_producto_orden().isEmpty()) {
                getBean().setEnvio_cocina(false);
            } else {
                boolean flag = false;
                for (ProductovOrden x : getBean().getLista_producto_orden()) {
                    if (x.getCantidad() != x.getEnviadosacocina()) {
                        flag = true;
                        break;
                    }
                }
                getBean().setEnvio_cocina(flag);
            }
            ProductovOrden p = getBean().getProducto_orden_seleccionado();
            if (p != null && p.getAgregadoA() == null) {
                getBean().setBotton_agrego_enabled(true);
            } else {
                getBean().setBotton_agrego_enabled(false);
            }
        }
        getBean().setCierre_parcial_enabled(
                ConfiguracionDAO.getInstance().find(
                        R.SettingID.IMPRESION_CIERRE_PARCIAL_ENABLED).getValor() == 1);
        return Optional.empty();
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_ADD_NOTA) {
            @Override
            public Optional doAction() {
                onAddNotaLCick();
                refreshState();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_ADD_PRODUCTO) {
            @Override
            public Optional doAction() {
                onAddProductoFromListClick();
                setCodOrden(codOrden);
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_ADD_PRODUCTO_IN_HOT) {
            @Override
            public Optional doAction() {
                onAddProductoInHotClick();
                setCodOrden(codOrden);
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_CERRAR_ORDEN) {
            @Override
            public Optional doAction() {
                onCerrarOrdenClick();
                refreshState();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_ENVIAR_ELABORAR) {
            @Override
            public Optional doAction() {
                onEnviarAElaborarCLick();
                refreshState();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_IMPRIMIR_CIERRE_PARCIAL) {
            @Override
            public Optional doAction() {
                onImprimirCierreParcial();
                refreshState();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_REMOVE_PRODUCTO) {
            @Override
            public Optional doAction() {
                onRemoveProductoCLick();
                refreshState();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_ELIMINAR_PARCIAL_PRODUCTO) {
            @Override
            public Optional doAction() {
                onRemoveParcialProductoCLick();
                refreshState();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_SET_AUTORIZO) {
            @Override
            public Optional doAction() {
                onSetAutorizoClick();
                refreshState();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_SET_AGREGO) {
            @Override
            public Optional doAction() {
                onSetAgregoClick();
                return Optional.empty();
            }

            @Override
            public boolean isEnabled() {
                return getBean().isBotton_agrego_enabled();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_SET_PORCIENTO) {
            @Override
            public Optional doAction() {
//                onSetPorcientoClick();
//                refreshState();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_SHOW_LOGS) {
            @Override
            public Optional doAction() {
                onVerDetallesClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_SET_DOMICILIO) {
            @Override
            public Optional doAction() {
                onSetDomicilioClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_SET_SUPORT_PANEL_VISIBLE) {
            @Override
            public Optional doAction() {
                onSuperPanelVisibleClick();
                return Optional.empty();
            }
        }
        );
    }

    private void addListeners() {
        getBean().addPropertyChangeListener(OrdenDetailViewModel.PROP_PORCIENTO_SERVICIO, (PropertyChangeEvent evt) -> {
            float value = (float) evt.getNewValue();
            if (value == 0) {
                getBean().setIcono_porciento(new ImageIcon(getClass().getResource(
                        "/restManager/resources/icons pack/porciento_gris.png")));
            } else {
                getBean().setIcono_porciento(new ImageIcon(getClass().getResource(
                        "/restManager/resources/icons pack/porciento_indigo.png")));
            }
            ordenService.setPorciento(getCodOrden(), value);
            getBean().setTotal_orden(utils.setDosLugaresDecimales(ordenService.getValorTotalOrden(getCodOrden())));
            refreshState();
        }
        );
        getBean().addPropertyChangeListener(OrdenDetailViewModel.PROP_CLIENTE_SELECCIONADO, (PropertyChangeEvent evt) -> {
            ClienteDomain newValue = (ClienteDomain) evt.getNewValue();
            if (newValue != null) {
               // clienteservice.addOrdenToClientOrdenList(newValue, ordenService.findBy(codOrden));
            }
        }
        );
        getBean().addPropertyChangeListener(OrdenDetailViewModel.PROP_PRODUCTO_ORDEN_SELECCIONADO, (PropertyChangeEvent evt) -> {
            ProductovOrden p = (ProductovOrden) evt.getNewValue();
            boolean flag = false;
            if (p != null && p.getAgregadoA() == null) {
                if (p.getProductoVenta() != null) {
                    if (!p.getProductoVenta().getSeccionnombreSeccion().getAgregadoEn().isEmpty()) {
                        flag = true;
                    }
                }
            }
            if (flag) {
                getBean().setBotton_agrego_enabled(true);
            } else {
                getBean().setBotton_agrego_enabled(false);
                getBean().setModo_agrego_activado(false);
            }
        }
        );
    }

}
