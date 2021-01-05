/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.insumo.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.controller.insumo.InsumoDetailController;
import com.jobits.pos.controller.insumo.InsumoDetailService;
import com.jobits.pos.domain.models.Insumo;
import com.jobits.pos.domain.models.InsumoElaborado;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.TipoNotificacion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.utils.utils;
import java.util.Arrays;
import java.util.Optional;
import javax.swing.JOptionPane;

/**
 *
 * @author Home
 */
public class InsumoDetailViewPresenter extends AbstractViewPresenter<InsumoDetailViewModel> {

    public static String ACTION_AGREGAR = "";
    public static final String ACTION_CANCELAR = "Cancelar";
    public static String ACTION_ELIMINAR_INSUMO = "Eliminar Insumo";
    public static String ACTION_AGREGAR_INSUMO = "Agregr Insumo";
    public static String ACTION_ELIMINAR_PRODUCTO = "Eliminar Producto";
    public static String ACTION_AGREGAR_PRODUCTO = "Agregar Producto";

    private InsumoDetailService service;

    public InsumoDetailViewPresenter(InsumoDetailController service) {
        super(new InsumoDetailViewModel());
        this.service = service;
        fillForm();
        if (service.getState().equals(service.getState().CREATING)) {
            getBean().setCrear_editar_button_text("Crear");
        } else {
            getBean().setCrear_editar_button_text("Editar");
        }
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_AGREGAR) {
            @Override
            public Optional doAction() {
                onAgregarClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_AGREGAR_INSUMO) {
            @Override
            public Optional doAction() {
                onAgregarInsumoFichaClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_AGREGAR_PRODUCTO) {
            @Override
            public Optional doAction() {
                onAgregarProductoFichaClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_CANCELAR) {
            @Override
            public Optional doAction() {
                onCancelarClic();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_ELIMINAR_INSUMO) {
            @Override
            public Optional doAction() {
                onEliminarInsumoFichaClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_ELIMINAR_PRODUCTO) {
            @Override
            public Optional doAction() {
                onEliminarProductoFichaClick();
                return Optional.empty();
            }
        });
    }

    private void fillForm() {
        //COMBOBOX DE UM
        getBean().getList_unidades_medida().addAll(new ArrayListModel(Arrays.asList(R.UM.values())));
        //TABLA DE INSUMOS
        getBean().getLista_insumos_contenidos().clear();
        getBean().getLista_insumos_contenidos().addAll(new ArrayListModel(service.getInstance().getInsumoDerivadoList()));
        getBean().getLista_insumos_disponibles().clear();
        getBean().getLista_insumos_disponibles().addAll(new ArrayListModel(service.getItems()));
        //TABLA DE PRODUCTOS
        getBean().getLista_productos_contenidos().clear();
        getBean().getLista_productos_contenidos().addAll(new ArrayListModel(service.getInstance().getProductoInsumoList()));
        getBean().getLista_productos_disponibles().clear();
        getBean().getLista_productos_disponibles().addAll(new ArrayListModel(service.getProductoList()));
        //PANEL INPUTS
        getBean().setNombre_insumo(service.getInstance().getNombre());
        getBean().setUnidad_medida_selected(R.UM.valueOf(service.getInstance().getUm()));
        getBean().setCosto_unitario(service.getInstance().getCostoPorUnidad());
        getBean().setEstimacion_de_stock(service.getInstance().getStockEstimation());

        if (service.getInstance().getCantidadCreada() == null) {
            getBean().setCantidad_creada(Float.valueOf("0"));
        } else {
            getBean().setCantidad_creada(service.getInstance().getCantidadCreada());
        }
        updateCostoValue();

//        if (getBean().getLista_insumos_contenidos().isEmpty() && getBean().getLista_productos_contenidos().isEmpty()) {
//            getBean().setTabbed_pane_enabled(false);
//        } else {
//            getBean().setTabbed_pane_enabled(true);
//        }
    }

    private void updateCostoValue() {
        if (!getBean().getLista_insumos_contenidos().isEmpty()) {
            float total = 0;
            total = getBean().getLista_insumos_contenidos().stream().map((x) -> x.getCosto()).reduce(total, (accumulator, _item) -> accumulator + _item);
            getBean().setValor_del_costo_text(R.formatoMoneda.format(total));
        } else {
            getBean().setValor_del_costo_text("0.00");
        }
    }

    private void onAgregarClick() {
        service.getInstance().setNombre(getBean().getNombre_insumo());
        service.getInstance().setCostoPorUnidad(getBean().getCosto_unitario());
        service.getInstance().setStockEstimation(getBean().getEstimacion_de_stock());
        service.getInstance().setUm(getBean().getUnidad_medida_selected().getValor());
        service.getInstance().setCantidadCreada(getBean().getCantidad_creada());
//        service.getInstance().setInsumoDerivadoList(getBean().getLista_insumos_contenidos());
//        service.getInstance().setProductoInsumoList(getBean().getLista_productos_contenidos());
        if (!service.getInstance().getInsumoDerivadoList().isEmpty()) {
            service.getInstance().setElaborado(true);
        } else {
            service.getInstance().setElaborado(false);
        }
        service.createUpdateInstance();
        Application.getInstance().getNavigator().navigateUp();
    }

    private void onCancelarClic() {
        Application.getInstance().getNavigator().navigateUp();
    }

    private void onAgregarInsumoFichaClick() {
        if (getBean().getInsumo_disponible_selecionado() == null) {
            JOptionPane.showMessageDialog(Application.getInstance().getMainWindow(), "Selecione un insumo primero");
        } else {
            Optional<String> opt = Application.getInstance().getNotificationService().showDialog("Introduzca la cantidad de " + getBean().getInsumo_disponible_selecionado(), TipoNotificacion.DIALOG_INPUT);
            if (opt.isPresent()) {
                try {
                    float cantidad = Float.parseFloat(opt.get());
                    Insumo inSel = getBean().getInsumo_disponible_selecionado();
                    service.agregarInsumoElaboradoaInsumo(inSel, cantidad);
//                    float nuevoPrecio = utils.setDosLugaresDecimalesFloat(
//                            Float.parseFloat(getBean().getValor_del_costo_text()) + (inSel.getCostoPorUnidad() * cantidad));
//                    getBean().setValor_del_costo_text(String.valueOf(nuevoPrecio));
                    getBean().setInsumo_disponible_selecionado(null);
                    getBean().getLista_insumos_contenidos().clear();
                    getBean().getLista_insumos_contenidos().addAll(service.getInstance().getInsumoDerivadoList());
                    //getBean().setInsumo_disponible_selecionado(null);
                } catch (NumberFormatException ex) {
                    Application.getInstance().getNotificationService().showDialog("Valores Incorrectos", TipoNotificacion.ERROR);
                }
            }
        }
        updateCostoValue();
    }

    private void onEliminarInsumoFichaClick() {
        if (getBean().getInsumo_contenido_seleccionado() == null) {
            JOptionPane.showMessageDialog(Application.getInstance().getMainWindow(), "Selecione el insumo a eliminar primero");
        } else {
            InsumoElaborado inSel = getBean().getInsumo_contenido_seleccionado();

            service.eliminarInsumoElaboradoDeInsumo(getBean().getInsumo_contenido_seleccionado());
//            float nuevoPrecio = utils.setDosLugaresDecimalesFloat(
//                    Float.parseFloat(getBean().getValor_del_costo_text()) - inSel.getCosto());
//            getBean().setValor_del_costo_text(String.valueOf(nuevoPrecio));
            getBean().setInsumo_contenido_seleccionado(null);
            getBean().getLista_insumos_contenidos().clear();
            getBean().getLista_insumos_contenidos().addAll(service.getInstance().getInsumoDerivadoList());
        }
        updateCostoValue();
    }

    private void onAgregarProductoFichaClick() {
        if (getBean().getProducto_disponible_seleccionado() == null) {
            JOptionPane.showMessageDialog(Application.getInstance().getMainWindow(), "Selecione un producto primero");
        } else {
            Optional<String> opt = Application.getInstance().getNotificationService().showDialog("Introduzca la cantidad de " + getBean().getProducto_disponible_seleccionado(), TipoNotificacion.DIALOG_INPUT);
            if (opt.isPresent()) {
                try {
                    float cantidad = Float.parseFloat(opt.get());
                    service.agregarProductoVentaAInsumo(getBean().getProducto_disponible_seleccionado(), cantidad);
                    getBean().setProducto_disponible_seleccionado(null);
                    getBean().getLista_productos_contenidos().clear();
                    getBean().getLista_productos_contenidos().addAll(service.getInstance().getProductoInsumoList());
                    getBean().setProducto_disponible_seleccionado(null);
                } catch (NumberFormatException ex) {
                    Application.getInstance().getNotificationService().showDialog("Valores Incorrectos", TipoNotificacion.ERROR);
                }
            }
        }
    }

    private void onEliminarProductoFichaClick() {
        if (getBean().getProducto_contenido_seleccionado() == null) {
            JOptionPane.showMessageDialog(Application.getInstance().getMainWindow(), "Selecione el producto a eliminar primero");
        } else {
            service.eliminarProductoVentaDeInsumo(getBean().getProducto_contenido_seleccionado());
            getBean().setProducto_contenido_seleccionado(null);
            getBean().getLista_productos_contenidos().clear();
            getBean().getLista_productos_contenidos().addAll(service.getInstance().getProductoInsumoList());
        }
    }

}
