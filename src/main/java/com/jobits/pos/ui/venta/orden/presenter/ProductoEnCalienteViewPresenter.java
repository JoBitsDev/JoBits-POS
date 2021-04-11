/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.orden.presenter;

import com.jobits.pos.controller.venta.OrdenService;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.core.domain.models.ProductoVenta;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import java.util.Optional;

/**
 *
 * @author Home
 */
public class ProductoEnCalienteViewPresenter extends AbstractViewPresenter<ProductoEnCalienteViewModel> {

    private final String codOrden;
    private final OrdenService service = PosDesktopUiModule.getInstance().getImplementation(OrdenService.class);

    public static String ACTION_ADD_PRODUCT_IN_HOT = "Agregar Producto en Caliente";
    public static String CANCELAR = "Cancelar";

    public ProductoEnCalienteViewPresenter(String codOrden) {
        super(new ProductoEnCalienteViewModel());
        this.codOrden = codOrden;
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_ADD_PRODUCT_IN_HOT) {
            @Override
            public Optional doAction() {
                onAceptarClic();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(CANCELAR) {
            @Override
            public Optional doAction() {
                NavigationService.getInstance().navigateUp();
                return Optional.empty();
            }
        });
    }

    private void onAceptarClic() {
        String nombre = getBean().getNombre_producto();
        String precio = getBean().getPrecio_producto();
        String cantidad = getBean().getCantidad_producto();
        service.addProductInHot(codOrden, nombre, precio, cantidad);
        NavigationService.getInstance().navigateUp();
    }
}
