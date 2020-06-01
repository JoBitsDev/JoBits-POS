/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.presenter;

import com.jobits.pos.controller.venta.OrdenController;
import com.jobits.pos.main.Application;
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

    public static String ACTION_ADD_PRODUCTO;
    public static String ACTION_REMOVE_PRODUCTO;
    public static String ACTION_SET_PORCIENTO;
    public static String ACTION_SET_AUTORIZO;
    public static String ACTION_ENVIAR_ELABORAR;
    public static String ACTION_CERRAR_ORDEN;
    public static String ACTION_ADD_NOTA;
    public static String ACTION_IMPRIMIR_CIERRE_PARCIAL;

    private OrdenController controller;

    public OrdenDetailViewPresenter(OrdenController controller) {
        super(new OrdenDetailViewModel());
        this.controller = controller;
        updateBean();
    }

    public void setOrden(com.jobits.pos.domain.models.Orden o) {
        controller.setInstance(o);
        updateBean();

    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_ADD_NOTA) {
            @Override
            public Optional doAction() {
                onAddNotaLCick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_ADD_PRODUCTO) {
            @Override
            public Optional doAction() {
                onAddProductoClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_CERRAR_ORDEN) {
            @Override
            public Optional doAction() {
                onCerrarOrdenClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_ENVIAR_ELABORAR) {
            @Override
            public Optional doAction() {
                onEnviarAElaborarCLick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_IMPRIMIR_CIERRE_PARCIAL) {
            @Override
            public Optional doAction() {
                onImprimirCierreParcial();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_REMOVE_PRODUCTO) {
            @Override
            public Optional doAction() {
                onRemoveProductoCLick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_SET_AUTORIZO) {
            @Override
            public Optional doAction() {
                onSetAutorizoClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_SET_PORCIENTO) {
            @Override
            public Optional doAction() {
                onSetPorcientoClick();
                return Optional.empty();
            }
        });
    }

    private void onAddProductoClick() {
        controller.addProduct(getBean().getProducto_venta_seleccionado());
        getBean().setLista_producto_orden((controller.getInstance().getProductovOrdenList()));
    }

    private void onRemoveProductoCLick() {
        controller.removeProduct(getBean().getProducto_orden_seleccionado(),
                getBean().getProducto_orden_seleccionado().getCantidad());
        getBean().setLista_producto_orden((controller.getInstance().getProductovOrdenList()));
    }

    private void onSetPorcientoClick() {
        controller.setPorciento(getBean().getPorciento_servicio());
        getBean().setTotal_orden(utils.setDosLugaresDecimales(controller.getValorTotal()));
    }

    private void onSetAutorizoClick() {
        controller.setDeLaCasa(getBean().isEs_autorizo());
    }

    private void onEnviarAElaborarCLick() {
        controller.enviarACocina();
        getBean().setLista_producto_orden(controller.getInstance().getProductovOrdenList());
    }

    private void onCerrarOrdenClick() {
        controller.cerrarOrden();
        Application.getInstance().getNavigator().navigateUp();
    }

    private void onAddNotaLCick() {
        controller.addNota(getBean().getProducto_orden_seleccionado());
    }

    private void onImprimirCierreParcial() {
        controller.imprimirPreTicket();
    }

    public void old() {

//    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//        switch (columnIndex) {
//            case 1:
//                if (getController().autorize()) {
//                    float cantidadOld = items.get(rowIndex).getCantidad();
//                    float diferencia = cantidadOld - (float) aValue;
//                    if (diferencia > 0) {
//                        getController().removeProduct(items.get(rowIndex), diferencia);
//                    } else {
//                        getController().addProduct(items.get(rowIndex), diferencia * -1);
//                    }
//                    fireTableRowsUpdated(rowIndex, rowIndex);
//                    state = ButtonState.ENVIAR_COCINA;
//                    jideButtonCerrarMesaEnviarCocina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/enviar_cocina.png")));
//                    updateValorTotal();
//
//                }
//                break;
//            default:
//                super.setValueAt(aValue, rowIndex, columnIndex);
//                break;
//        }
//    }
//
//    public Class<?> getColumnClass(int columnIndex) {
//        if (columnIndex == 1) {
//            return Float.class;
//        } else {
//            return super.getColumnClass(columnIndex); //To change body of generated methods, choose Tools | Templates.
//        }
//    }
    }

    private void updateBean() {
        com.jobits.pos.domain.models.Orden instance = controller.getInstance();
        getBean().setEs_autorizo(instance.getDeLaCasa());
        getBean().setFecha_orden(R.DATE_FORMAT.format(instance.getVentafecha().getFecha()));
        getBean().setHora_pedido(R.TIME_FORMAT.format(instance.getHoraComenzada()));
        getBean().setId_orden(instance.getCodOrden());
        getBean().setLista_general_productos_venta(controller.getPDVList());
        getBean().setLista_producto_orden(instance.getProductovOrdenList());
        getBean().setLista_secciones(controller.getListaSecciones());
        getBean().setMesa_pedido(instance.getMesacodMesa().getCodMesa());
        getBean().setPorciento_servicio(instance.getPorciento());
        getBean().setTotal_orden(utils.setDosLugaresDecimales(controller.getValorTotal()));
        getBean().setUsuario(instance.getPersonalusuario().getUsuario());
    }

}
