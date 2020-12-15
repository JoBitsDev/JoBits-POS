/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.orden.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jhw.swing.material.standars.MaterialIcons;
import com.jobits.pos.controller.clientes.ClientesDetailServiceImpl;
//import com.jobits.pos.adapters.repo.impl.OrdenTemporalRepo;
import com.jobits.pos.controller.venta.OrdenService;
import com.jobits.pos.cordinator.DisplayType;
import com.jobits.pos.domain.models.Cliente;
import com.jobits.pos.domain.models.ProductovOrden;
import com.jobits.pos.exceptions.ValidatingException;
import com.jobits.pos.main.Application;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.utils.NumberPad;
import com.jobits.pos.ui.utils.utils;
import com.jobits.pos.ui.venta.orden.OrdenLogView;
//import com.jobits.pos.ui.venta.orden.OrdenLogsDetailView;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.util.List;
import java.util.Optional;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

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
    public static String ACTION_CERRAR_ORDEN = "Cerrar Orden";
    public static String ACTION_ENVIAR_ELABORAR = "Enviar a elaborar";
    public static String ACTION_IMPRIMIR_CIERRE_PARCIAL = "Cierre Parcial";
    public static String ACTION_REMOVE_PRODUCTO = "Eliminar";
    public static String ACTION_SHOW_LOGS = "Ver Detalles";
    public static String ACTION_SET_AUTORIZO = "Autorizo";
    public static String ACTION_SET_AGREGO = "Agrego";
    public static String ACTION_SET_PORCIENTO = "Porciento";
    private String codOrden;
    private OrdenService controller;

    public OrdenDetailViewPresenter(OrdenService controller) {
        super(new OrdenDetailViewModel());
        this.controller = controller;
        addListeners();
        
    }

    public OrdenDetailViewPresenter(String cod_orden, OrdenService controller) {
        this(controller);
        this.codOrden = cod_orden;
        addListeners();
        refreshState();
    }

    public String getCodOrden() {
        if (codOrden == null) {
            throw new IllegalStateException("El codigo de la orden no puede ser nulo.");
        }
        return codOrden;
    }

    public void setCodOrden(String codOrden) {
        this.codOrden = codOrden;
        refreshState();
    }

    public OrdenService getController() {
        if (controller == null) {
            throw new IllegalStateException("El controlador no puede ser nulo");

        }
        return controller;
    }

    private void onAddNotaLCick() {
        getController().addNota(getCodOrden(), getBean().getProducto_orden_seleccionado());
    }

    private void onAddProductoClick() {
        if (getBean().getProducto_orden_seleccionado() != null) {
            
            getController().addProduct(getBean().getId_orden(), getBean().getProducto_orden_seleccionado().getProductoVenta(),new NumberPad(null).showView());
            getBean().setLista_producto_orden((getController().getInstance(getCodOrden()).getProductovOrdenList()));
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un producto primero", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    private void onCerrarOrdenClick() {
        getController().cerrarOrden(getCodOrden());
    }

    private void onEnviarAElaborarCLick() {
        getController().enviarACocina(getCodOrden());
        getBean().setLista_producto_orden(getController().getInstance(getCodOrden()).getProductovOrdenList());
    }

    private void onImprimirCierreParcial() {
        getController().imprimirPreTicket(getCodOrden());
    }

    private void onRemoveProductoCLick() {
        if (getBean().getProducto_orden_seleccionado() != null) {
            getController().removeProduct(getCodOrden(), getBean().getProducto_orden_seleccionado(),
                    getBean().getProducto_orden_seleccionado().getCantidad());
            getBean().setLista_producto_orden((getController().getInstance(getCodOrden()).getProductovOrdenList()));
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un producto primero", "Error", JOptionPane.ERROR_MESSAGE);

        }

    }

    private void onSetAutorizoClick() {
        getBean().setEs_autorizo(!getBean().isEs_autorizo());
        getController().setDeLaCasa(getCodOrden(), getBean().isEs_autorizo());
        refreshState();
//        getController().setDeLaCasa(getCodOrden(), getBean().isEs_autorizo());
    }
    private void onSetAgregoClick() {
        getBean().setModo_agrego_activado(!getBean().isModo_agrego_activado());
    }

//    private void onSetPorcientoClick() {
//        getController().setPorciento(getCodOrden(), getBean().getPorciento_servicio());
//        getBean().setTotal_orden(utils.setDosLugaresDecimales(getController().getValorTotal(getCodOrden())));
//    }
    private void onVerDetallesClick() {
        File temporalFile = new File(R.LOGS_FILE_PATH + "/Ordenes" + "/" + codOrden + ".txt");
        if (temporalFile.exists()) {
            Application.getInstance().getNavigator().navigateTo(
                    OrdenLogView.VIEW_NAME, new OrdenLogViewPresenter(codOrden), DisplayType.POPUP);
        } else {
            JOptionPane.showMessageDialog(null, "No hay registros de orden: " + codOrden, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected Optional refreshState() {
        com.jobits.pos.domain.models.Orden instance = getController().getInstance(getCodOrden());
        getBean().setEs_autorizo(instance.getDeLaCasa());
        getBean().setFecha_orden(R.DATE_FORMAT.format(instance.getVentafecha().getFecha()));
        getBean().setHora_pedido(R.TIME_FORMAT.format(instance.getHoraComenzada()));
        getBean().setOrden_terminada(instance.getHoraTerminada() != null);
        getBean().setId_orden(instance.getCodOrden());
        getBean().setLista_general_productos_venta(getController().getPDVList(getCodOrden()));
        getBean().setLista_producto_orden(instance.getProductovOrdenList());
        getBean().setModo_agrego_activado(false);
        //getBean().setLista_secciones(getController().getListaSecciones());
        if (instance.getMesacodMesa() != null) {
            getBean().setMesa_pedido(instance.getMesacodMesa().getCodMesa());
        }
        getBean().setPorciento_servicio(instance.getPorciento());
        getBean().setTotal_orden(utils.setDosLugaresDecimales(getController().getValorTotal(getCodOrden())));
        getBean().setUsuario(instance.getPersonalusuario().getUsuario());
        if (getBean().getPorciento_servicio() == 0) {
            getBean().setIcono_porciento(new ImageIcon(getClass().getResource(
                    "/restManager/resources/icons pack/porciento_gris.png")));
        } else {
            getBean().setIcono_porciento(new ImageIcon(getClass().getResource(
                    "/restManager/resources/icons pack/porciento_indigo.png")));
        }
        getBean().setLista_clientes(new ArrayListModel<>(new ClientesDetailServiceImpl().getListaClientes()));
        if (instance.getClienteIdCliente() != null) {
            getBean().setCliente_seleccionado(instance.getClienteIdCliente());
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
                onAddProductoClick();
                refreshState();
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
            getController().setPorciento(getCodOrden(), value);
            getBean().setTotal_orden(utils.setDosLugaresDecimales(getController().getValorTotal(getCodOrden())));
            refreshState();
        }
        );
        getBean().addPropertyChangeListener(OrdenDetailViewModel.PROP_CLIENTE_SELECCIONADO, (PropertyChangeEvent evt) -> {
            Cliente newValue = (Cliente) evt.getNewValue();
            if (newValue != null) {
                ClientesDetailServiceImpl clienteservice = new ClientesDetailServiceImpl();
                clienteservice.addOrdenToClientOrdenList(newValue, getController().getInstance(codOrden));
            }
//            System.out.println("Its Happ");

        }
        );
//        getBean().addPropertyChangeListener(OrdenDetailViewModel.PROP_MODO_AGREGO_ACTIVADO, (PropertyChangeEvent evt) -> {
//            firePropertyChange(evt);
//        }
//        );
    }

}
