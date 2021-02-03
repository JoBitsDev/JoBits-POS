/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.reserva.presenter;

import com.jobits.pos.controller.mesa.MesaService;
import com.jobits.pos.controller.venta.OrdenService;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.core.domain.models.Orden;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.venta.orden.presenter.OrdenDetailViewModel;
import com.jobits.pos.ui.venta.orden.presenter.OrdenDetailViewPresenter;
import com.jobits.pos.ui.venta.orden.presenter.ProductoVentaSelectorPresenter;
import java.beans.PropertyChangeEvent;
import java.util.Optional;

/**
 *
 * @author Home
 */
public class ReservaOrdenDetailViewPresenter extends AbstractViewPresenter<ReservaOrdenDetailViewModel> {

    public static final String ACTION_CANCELAR = "Cancelar";

    private final OrdenDetailViewPresenter ordenPresenter;
    private final ProductoVentaSelectorPresenter productoListPresenter;
    OrdenService service;
    String codOrden;

    public ReservaOrdenDetailViewPresenter(String codOrden, OrdenService service) {
        this.service = service;
        this.codOrden = codOrden;

        ordenPresenter = new OrdenDetailViewPresenter(this.codOrden, this.service);
        productoListPresenter = new ProductoVentaSelectorPresenter(this.service);
        productoListPresenter.setCodOrdenEnlazada(this.codOrden);
        setMesa();
        addListeners();

    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_CANCELAR) {
            @Override
            public Optional doAction() {
                NavigationService.getInstance().navigateUp();
                return Optional.empty();
            }
        });
    }

    public OrdenDetailViewPresenter getOrdenPresenter() {
        return ordenPresenter;
    }

    public ProductoVentaSelectorPresenter getProductoListPresenter() {
        return productoListPresenter;
    }

    private void addListeners() {
        productoListPresenter.addPropertyChangeListener(ProductoVentaSelectorPresenter.PROP_PRODUCTO_SELECCIONADO,
                (PropertyChangeEvent evt) -> {
                    ordenPresenter.refreshState();
                }
        );
        ordenPresenter.addBeanPropertyChangeListener(OrdenDetailViewModel.PROP_MODO_AGREGO_ACTIVADO,
                (PropertyChangeEvent evt) -> {
                    if ((boolean) evt.getNewValue()) {
                        this.service.setModoAgrego(ordenPresenter.getBean().getProducto_orden_seleccionado());
                        productoListPresenter.showSeccionesAgregadas();
                        productoListPresenter.onMostrarSeccionClick();
                    } else {
                        this.service.setModoAgrego(null);
                        productoListPresenter.refreshState();
                    }
                }
        );
        ordenPresenter.addPropertyChangeListener(OrdenDetailViewPresenter.PROP_CHANGES,
                (PropertyChangeEvent evt) -> {
                    refreshState();
                }
        );
    }

    private void setMesa() {
        Orden o = service.getInstance(this.codOrden);
        if (o != null) {
            if (o.getMesacodMesa() != null) {
                productoListPresenter.setMesaSeleccionada(o.getMesacodMesa());
            }
        }

    }

}
