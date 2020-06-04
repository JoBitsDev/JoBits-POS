/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.productos.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.productos.ProductoVentaDetailController;
import com.jobits.pos.controller.puntoelaboracion.PuntoElaboracionListController;
import com.jobits.pos.controller.seccion.SeccionListController;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.domain.models.ProductoInsumo;
import com.jobits.pos.domain.models.ProductoVenta;
import com.jobits.pos.exceptions.DevelopingOperationException;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.NotificationService;
import com.jobits.pos.notification.TipoNotificacion;
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
public class ProductoVentaDetailPresenter extends AbstractViewPresenter<ProductoVentaDetailViewModel> {

    public static final String ACTION_AGREGAR_PUNTO_ELABORACION = "Nuevo Pto Elaboración";
    public static final String ACTION_AGREGAR_CATEGORIA = "Nueva Categoría";
    public static final String ACTION_AGREGAR_INSUMO = "Nuevo Insumo";
    public static final String ACTION_AGREGAR = "Aceptar";
    public static final String ACTION_CANCELAR = "Cancelar";
    public static String ACTION_AGREGAR_INSUMO_FICHA = "Registrar";
    public static String ACTION_ELIMINAR_INSUMO_FICHA = "Eliminar";

    private ProductoVentaDetailController controller;
    private boolean creatingMode = true;

    /**
     * Si es nulo es que el producto que se va a crear es nuevo
     *
     * @param controller
     * @param productoSeleccionado
     */
    public ProductoVentaDetailPresenter(ProductoVentaDetailController controller, ProductoVenta productoSeleccionado) {
        super(new ProductoVentaDetailViewModel());
        this.controller = controller;
        getBean().getLista_categorias().addAll(new ArrayListModel<>(controller.getSeccionList()));
        getBean().getLista_elaborado().addAll(new ArrayListModel<>(controller.getCocinaList()));
        getBean().getLista_insumos_disponibles().addAll(new ArrayListModel<>(controller.getInsumoList()));
        if (productoSeleccionado != null) {
            fillForm(productoSeleccionado);
        }
        creatingMode = productoSeleccionado == null;
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_AGREGAR) {
            @Override
            public Optional doAction() {
                onAceptarClick();
                return Optional.empty();
            }
        });

        registerOperation(new AbstractViewAction(ACTION_AGREGAR_CATEGORIA) {
            @Override
            public Optional doAction() {
                onAddCategoriaClick();
                return Optional.empty();
            }
        });

        registerOperation(new AbstractViewAction(ACTION_AGREGAR_PUNTO_ELABORACION) {
            @Override
            public Optional doAction() {
                onAddElaboracionCLick();
                return Optional.empty();
            }
        });

        registerOperation(new AbstractViewAction(ACTION_CANCELAR) {
            @Override
            public Optional doAction() {
                onCancelarClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_AGREGAR_INSUMO) {
            @Override
            public Optional doAction() {
                onAddIngredienteClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_ELIMINAR_INSUMO_FICHA) {
            @Override
            public Optional doAction() {
                onEliminarInsumoFichaClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_AGREGAR_INSUMO_FICHA) {
            @Override
            public Optional doAction() {
                onAgregarInsumoFichaClick();
                return Optional.empty();
            }
        });
    }

    private void fillForm(ProductoVenta productoSeleccionado) {
        getBean().setCategoria_seleccionada(productoSeleccionado.getSeccionnombreSeccion());
        getBean().setNombre_producto(productoSeleccionado.getNombre());
        getBean().setCodigo_producto(productoSeleccionado.getCodigoProducto());
        getBean().setComision_por_venta("" + utils.setDosLugaresDecimalesFloat(productoSeleccionado.getPagoPorVenta()));
        getBean().setElaborado_seleccionado(productoSeleccionado.getCocinacodCocina());
        getBean().getLista_insumos_contenidos().addAll(new ArrayListModel<>(productoSeleccionado.getProductoInsumoList()));
        getBean().setCheckbox_producto_elaborado(!getBean().getLista_insumos_contenidos().isEmpty());
        getBean().setPrecio_venta("" + utils.setDosLugaresDecimalesFloat(productoSeleccionado.getPrecioVenta()));
        getBean().setPrecio_costo("" + utils.setDosLugaresDecimalesFloat(productoSeleccionado.getGasto()));
        getBean().getLista_insumos_disponibles().addAll(new ArrayListModel<>(controller.getInsumoList()));

    }

    private void onAddIngredienteClick() {
        controller.registrarNuevoInsumo();
        getBean().getLista_insumos_disponibles().clear();
        getBean().getLista_insumos_disponibles().addAll(controller.getInsumoList());
    }

    private void onAceptarClick() {
        if ((boolean)Application.getInstance().getNotificationService().
                showDialog("Desea guardar los cambios",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            ProductoVenta p;
            if (creatingMode) {
                p = controller.createNewInstance();
            } else {
                p = controller.getInstance();
            }
            p.setNombre(getBean().getNombre_producto());
            p.setCocinacodCocina(getBean().getElaborado_seleccionado());
            p.setSeccionnombreSeccion(getBean().getCategoria_seleccionada());
            p.setPrecioVenta(Float.parseFloat(getBean().getPrecio_venta()));
            p.setGasto(Float.valueOf(getBean().getPrecio_costo()));
            p.setProductoInsumoList(getBean().getLista_insumos_contenidos());
            if (getBean().getComision_por_venta() != null) {
                p.setPagoPorVenta(Float.parseFloat(getBean().getComision_por_venta()));
            } else {
                p.setPagoPorVenta((float) 0);
            }
            p.setVisible(true);
            controller.create(p);
            NavigationService.getInstance().navigateUp();//TODO: faltan los insumos
        }

    }

    private void onCancelarClick() {
        if ((boolean)Application.getInstance().getNotificationService().
                showDialog("Desea descartar los cambios?",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            controller.discardChanges();
            NavigationService.getInstance().navigateUp();
        }

    }

    private void onAddCategoriaClick() {
        new SeccionListController().getDetailControllerForNew();
        getBean().setLista_categorias(new ArrayListModel<>(controller.getSeccionList()));

    }

    private void onAddElaboracionCLick() {
        new PuntoElaboracionListController().createInstance();
        getBean().setLista_elaborado(new ArrayListModel<>(controller.getCocinaList()));

    }

    private void onAgregarInsumoFichaClick() {
        Optional<String> opt =Application.getInstance().getNotificationService().showDialog("Introduzca la cantidad de " + getBean().getInsumo_disponible_sel(), TipoNotificacion.DIALOG_INPUT);
        if (opt.isPresent()) {
            try {
                float cantidad = Float.parseFloat(opt.get());
                controller.agregarInsumoaProducto(getBean().getInsumo_disponible_sel(), cantidad);
                getBean().setInsumo_disponible_sel(null);
                getBean().getLista_insumos_contenidos().clear();
                getBean().getLista_insumos_contenidos().addAll(controller.getInstance().getProductoInsumoList());
                getBean().setInsumo_disponible_sel(null);
            } catch (NumberFormatException ex) {
               Application.getInstance().getNotificationService().showDialog("Valores Incorrectos", TipoNotificacion.ERROR);
            }
        }

    }

    private void onEliminarInsumoFichaClick() {
        controller.eliminarInsumoProducto(getBean().getInsumo_contenido_seleccionado());
        getBean().setInsumo_contenido_seleccionado(null);
        getBean().getLista_insumos_contenidos().clear();
        getBean().getLista_insumos_contenidos().addAll(controller.getInstance().getProductoInsumoList());

    }

}
