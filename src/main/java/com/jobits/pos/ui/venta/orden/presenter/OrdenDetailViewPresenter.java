/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.orden.presenter;

import com.jobits.pos.controller.venta.OrdenService;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.utils.utils;
import java.util.Optional;

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
    public static String ACTION_SET_AUTORIZO = "Autorizo";
    public static String ACTION_SET_PORCIENTO = "Porciento";
    private String codOrden;
    private OrdenService controller;

    public OrdenDetailViewPresenter(OrdenService controller) {
        super(new OrdenDetailViewModel());
        this.controller = controller;
    }

    public OrdenDetailViewPresenter(String cod_orden, OrdenService controller) {
        this(controller);
        this.codOrden = cod_orden;
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
        getController().addProduct(getBean().getId_orden(), getBean().getProducto_orden_seleccionado().getProductoVenta());
        getBean().setLista_producto_orden((getController().getInstance(getCodOrden()).getProductovOrdenList()));
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
        getController().removeProduct(getCodOrden(), getBean().getProducto_orden_seleccionado(),
                getBean().getProducto_orden_seleccionado().getCantidad());
        getBean().setLista_producto_orden((getController().getInstance(getCodOrden()).getProductovOrdenList()));
    }

    private void onSetAutorizoClick() {
        getController().setDeLaCasa(getCodOrden(), getBean().isEs_autorizo());
    }

    private void onSetPorcientoClick() {
        getController().setPorciento(getCodOrden(), getBean().getPorciento_servicio());
        getBean().setTotal_orden(utils.setDosLugaresDecimales(getController().getValorTotal(getCodOrden())));
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
        //getBean().setLista_secciones(getController().getListaSecciones());
        getBean().setMesa_pedido(instance.getMesacodMesa().getCodMesa());
        getBean().setPorciento_servicio(instance.getPorciento());
        getBean().setTotal_orden(utils.setDosLugaresDecimales(getController().getValorTotal(getCodOrden())));
        getBean().setUsuario(instance.getPersonalusuario().getUsuario());
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
        registerOperation(new AbstractViewAction(ACTION_SET_PORCIENTO) {
            @Override
            public Optional doAction() {
                onSetPorcientoClick();
                refreshState();
                return Optional.empty();
            }
        });
    }

}
