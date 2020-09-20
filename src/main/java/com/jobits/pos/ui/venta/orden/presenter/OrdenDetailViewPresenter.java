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

    public static String ACTION_ADD_PRODUCTO = "Agregar";
    public static String ACTION_REMOVE_PRODUCTO = "Eliminar";
    public static String ACTION_SET_PORCIENTO = "Porciento";
    public static String ACTION_SET_AUTORIZO = "Autorizo";
    public static String ACTION_ENVIAR_ELABORAR = "Enviar a elaborar";
    public static String ACTION_CERRAR_ORDEN = "Cerrar Orden";
    public static String ACTION_ADD_NOTA = "Agregar Nota";
    public static String ACTION_IMPRIMIR_CIERRE_PARCIAL = "Cierre Parcial";

    private OrdenService controller;
    private String codOrden;

    public OrdenDetailViewPresenter(String cod_orden, OrdenService controller) {
        super(new OrdenDetailViewModel());
        this.controller = controller;
        this.codOrden = cod_orden;
        updateBean();
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_ADD_NOTA) {
            @Override
            public Optional doAction() {
                onAddNotaLCick();
                updateBean();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_ADD_PRODUCTO) {
            @Override
            public Optional doAction() {
                onAddProductoClick();
                updateBean();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_CERRAR_ORDEN) {
            @Override
            public Optional doAction() {
                onCerrarOrdenClick();
                updateBean();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_ENVIAR_ELABORAR) {
            @Override
            public Optional doAction() {
                onEnviarAElaborarCLick();
                updateBean();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_IMPRIMIR_CIERRE_PARCIAL) {
            @Override
            public Optional doAction() {
                onImprimirCierreParcial();
                updateBean();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_REMOVE_PRODUCTO) {
            @Override
            public Optional doAction() {
                onRemoveProductoCLick();
                updateBean();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_SET_AUTORIZO) {
            @Override
            public Optional doAction() {
                onSetAutorizoClick();
                updateBean();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_SET_PORCIENTO) {
            @Override
            public Optional doAction() {
                onSetPorcientoClick();
                updateBean();
                return Optional.empty();
            }
        });
    }

    private void onAddProductoClick() {
        controller.addProduct(getBean().getId_orden(), getBean().getProducto_venta_seleccionado());
        getBean().setLista_producto_orden((controller.getInstance(codOrden).getProductovOrdenList()));
    }

    private void onRemoveProductoCLick() {
        controller.removeProduct(codOrden, getBean().getProducto_orden_seleccionado(),
                getBean().getProducto_orden_seleccionado().getCantidad());
        getBean().setLista_producto_orden((controller.getInstance(codOrden).getProductovOrdenList()));
    }

    private void onSetPorcientoClick() {
        controller.setPorciento(codOrden, getBean().getPorciento_servicio());
        getBean().setTotal_orden(utils.setDosLugaresDecimales(controller.getValorTotal(codOrden)));
    }

    private void onSetAutorizoClick() {
        controller.setDeLaCasa(codOrden, getBean().isEs_autorizo());
    }

    private void onEnviarAElaborarCLick() {
        controller.enviarACocina(codOrden);
        getBean().setLista_producto_orden(controller.getInstance(codOrden).getProductovOrdenList());
    }

    private void onCerrarOrdenClick() {
        controller.cerrarOrden(codOrden);
    }

    private void onAddNotaLCick() {
        controller.addNota(codOrden, getBean().getProducto_orden_seleccionado());
    }

    private void onImprimirCierreParcial() {
        controller.imprimirPreTicket(codOrden);
    }
    
    private void updateBean() {
        com.jobits.pos.domain.models.Orden instance = controller.getInstance(codOrden);
        getBean().setEs_autorizo(instance.getDeLaCasa());
        getBean().setFecha_orden(R.DATE_FORMAT.format(instance.getVentafecha().getFecha()));
        getBean().setHora_pedido(R.TIME_FORMAT.format(instance.getHoraComenzada()));
        getBean().setOrden_terminada(instance.getHoraTerminada() != null);
        getBean().setId_orden(instance.getCodOrden());
        //getBean().setLista_general_productos_venta(controller.getPDVList());
        getBean().setLista_producto_orden(instance.getProductovOrdenList());
        //getBean().setLista_secciones(controller.getListaSecciones());
        getBean().setMesa_pedido(instance.getMesacodMesa().getCodMesa());
        getBean().setPorciento_servicio(instance.getPorciento());
        getBean().setTotal_orden(utils.setDosLugaresDecimales(controller.getValorTotal(codOrden)));
        getBean().setUsuario(instance.getPersonalusuario().getUsuario());
    }

}
