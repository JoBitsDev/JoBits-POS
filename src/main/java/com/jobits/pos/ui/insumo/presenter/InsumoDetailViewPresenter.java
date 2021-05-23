/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.insumo.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.controller.insumo.InsumoDetailService;
import com.jobits.pos.controller.productos.ProductoVentaListService;
import com.jobits.pos.core.domain.models.Insumo;
import com.jobits.pos.main.Application;
import com.root101.clean.core.app.services.utils.TipoNotificacion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.utils.NumberPad;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

/**
 *
 * @author Home
 */
public class InsumoDetailViewPresenter extends AbstractViewPresenter<InsumoDetailViewModel> {

    public static String ACTION_AGREGAR = "Aceptar";
    public static final String ACTION_CANCELAR = "Cancelar";
    public static String ACTION_ELIMINAR_INSUMO = "Eliminar Insumo";
    public static String ACTION_AGREGAR_INSUMO = "Agregr Insumo";
    public static String ACTION_ELIMINAR_PRODUCTO = "Eliminar Producto";
    public static String ACTION_AGREGAR_PRODUCTO = "Agregar Producto";

    private final InsumoDetailService service = PosDesktopUiModule.getInstance().getImplementation(InsumoDetailService.class);
    private final ProductoVentaListService productoService = PosDesktopUiModule.getInstance().getImplementation(ProductoVentaListService.class);
    private final boolean creatingMode;
    private Insumo insumo;

    public InsumoDetailViewPresenter(Insumo insumo) {
        super(new InsumoDetailViewModel());
        this.creatingMode = insumo == null;
        if (creatingMode) {
            this.insumo = new Insumo();
            this.insumo.setNombre("");
            this.insumo.setElaborado(false);
            this.insumo.setCostoPorUnidad(Float.valueOf("0"));
            this.insumo.setStockEstimation(Float.valueOf("0"));
            this.insumo.setUm(R.UM.U.toString());
            this.insumo.setInsumoDerivadoList(new ArrayList<>());
            this.insumo.setProductoInsumoList(new ArrayList<>());
        } else {
            this.insumo = insumo;
        }
        fillForm();
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
        getBean().getLista_insumos_contenidos().addAll(new ArrayListModel(insumo.getInsumoDerivadoList()));
        getBean().getLista_insumos_disponibles().clear();
        getBean().getLista_insumos_disponibles().addAll(new ArrayListModel(service.findAll()));
        //TABLA DE PRODUCTOS
        getBean().getLista_productos_contenidos().clear();
        getBean().getLista_productos_contenidos().addAll(new ArrayListModel(insumo.getProductoInsumoList()));
        getBean().getLista_productos_disponibles().clear();
        getBean().getLista_productos_disponibles().addAll(new ArrayListModel(productoService.getItems()));
        //PANEL INPUTS
        getBean().setNombre_insumo(insumo.getNombre());
        getBean().setUnidad_medida_selected(R.UM.valueOf(insumo.getUm()));
        getBean().setCosto_unitario(insumo.getCostoPorUnidad());
        getBean().setEstimacion_de_stock(insumo.getStockEstimation());
        if (insumo.getCantidadCreada() == null) {
            getBean().setCantidad_creada(Float.valueOf("0"));
        } else {
            getBean().setCantidad_creada(insumo.getCantidadCreada());
        }
        updateCostoValue();
        if (creatingMode) {
            getBean().setCrear_editar_button_text("Crear");
        } else {
            getBean().setCrear_editar_button_text("Editar");
        }
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
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Esta seguro que desea continuar?",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            insumo.setNombre(getBean().getNombre_insumo());
            insumo.setCostoPorUnidad(getBean().getCosto_unitario());
            insumo.setStockEstimation(getBean().getEstimacion_de_stock());
            insumo.setUm(getBean().getUnidad_medida_selected().getValor());
            insumo.setCantidadCreada(getBean().getCantidad_creada());
            insumo.setElaborado(!insumo.getInsumoDerivadoList().isEmpty());
            if (creatingMode) {
                service.create(insumo);
            } else {
                service.edit(insumo);
                service.updateProductoOnInsumo(insumo);
            }
            Application.getInstance().getNotificationService().notify(R.RESOURCE_BUNDLE.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
            Application.getInstance().getNavigator().navigateUp();
        }
    }

    private void onCancelarClic() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Esta seguro que desea cancelar?",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            Application.getInstance().getNavigator().navigateUp();
        }
    }

    private void onAgregarInsumoFichaClick() {
        Float cantidad = new NumberPad(null).showView();
        if (cantidad != null) {
            Insumo selected = getBean().getInsumo_disponible_selecionado();
            service.agregarInsumoElaboradoaInsumo(insumo, selected, cantidad);
            getBean().setInsumo_disponible_selecionado(null);
            getBean().getLista_insumos_contenidos().clear();
            getBean().getLista_insumos_contenidos().addAll(insumo.getInsumoDerivadoList());
            updateCostoValue();
        }
    }

    private void onEliminarInsumoFichaClick() {
        service.eliminarInsumoElaboradoDeInsumo(insumo, getBean().getInsumo_contenido_seleccionado());
        getBean().setInsumo_contenido_seleccionado(null);
        getBean().getLista_insumos_contenidos().clear();
        getBean().getLista_insumos_contenidos().addAll(insumo.getInsumoDerivadoList());
        updateCostoValue();
    }

    private void onAgregarProductoFichaClick() {
        Float cantidad = new NumberPad(null).showView();
        if (cantidad != null) {
            service.agregarProductoVentaAInsumo(insumo, getBean().getProducto_disponible_seleccionado(), cantidad);
            getBean().setProducto_disponible_seleccionado(null);
            getBean().getLista_productos_contenidos().clear();
            getBean().getLista_productos_contenidos().addAll(insumo.getProductoInsumoList());
            getBean().setProducto_disponible_seleccionado(null);
        }
    }

    private void onEliminarProductoFichaClick() {
        service.eliminarProductoVentaDeInsumo(insumo, getBean().getProducto_contenido_seleccionado());
        getBean().setProducto_contenido_seleccionado(null);
        getBean().getLista_productos_contenidos().clear();
        getBean().getLista_productos_contenidos().addAll(insumo.getProductoInsumoList());
    }

}
