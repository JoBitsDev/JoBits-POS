/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.productos.presenter;

import com.jobits.pos.controller.productos.ProductoVentaDetailController;
import com.jobits.pos.controller.puntoelaboracion.PuntoElaboracionListController;
import com.jobits.pos.controller.seccion.SeccionListController;
import com.jobits.pos.cordinator.MainNavigator;
import com.jobits.pos.domain.models.ProductoVenta;
import com.jobits.pos.exceptions.DevelopingOperationException;
import com.jobits.pos.notification.NotificationService;
import com.jobits.pos.notification.TipoNotificacion;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
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

    private ProductoVentaDetailController controller;

    /**
     * Si es nulo es que el producto que se va a crear es nuevo
     *
     * @param controller
     * @param productoSeleccionado
     */
    public ProductoVentaDetailPresenter(ProductoVentaDetailController controller, ProductoVenta productoSeleccionado) {
        super(new ProductoVentaDetailViewModel());
        this.controller = controller;
        getBean().setLista_categorias(controller.getSeccionList());
        getBean().setLista_elaborado(controller.getCocinaList());
        if (productoSeleccionado != null) {
            throw new DevelopingOperationException();
        } else {

        }

    }

    private void onAddIngredienteClick() {
        controller.agregarIngrediente();
    }

    private void onAceptarClick() {
        if ((boolean) NotificationService.getInstance().
                showDialog("Desea guardar los cambios",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            ProductoVenta p = controller.createNewInstance();
            p.setCocinacodCocina(getBean().getElaborado_seleccionado());
            p.setNombre(getBean().getNombre_producto());
            p.setPagoPorVenta(Float.parseFloat(getBean().getComision_por_venta()));
            p.setPrecioVenta(Float.parseFloat(getBean().getPrecio_venta()));
            if (getBean().isCheckbox_inventariar_producto()) {
                p.setGasto(Float.valueOf(getBean().getPrecio_costo()));
            }
            p.setSeccionnombreSeccion(getBean().getCategoria_seleccionada());
            p.setVisible(true);
            controller.create(p);
        }

    }

    private void onCancelarClick() {
        if ((boolean) NotificationService.getInstance().
                showDialog("Desea descartar los cambios?",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            MainNavigator.getInstance().navigateUp();
        }

    }

    private void onAddCategoriaClick() {
        new SeccionListController().getDetailControllerForNew();
        getBean().setLista_categorias(controller.getSeccionList());

    }

    private void onAddElaboracionCLick() {
        new PuntoElaboracionListController().createInstance();
        getBean().setLista_elaborado(controller.getCocinaList());

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
    }

}
