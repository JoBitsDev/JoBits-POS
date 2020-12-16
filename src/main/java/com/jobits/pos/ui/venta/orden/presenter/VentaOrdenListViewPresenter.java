/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.orden.presenter;

import com.jobits.pos.controller.venta.OrdenController;
import com.jobits.pos.controller.venta.OrdenService;
import com.jobits.pos.controller.venta.VentaDetailService;
import com.jobits.pos.domain.models.Orden;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Optional;
import javax.swing.JOptionPane;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class VentaOrdenListViewPresenter extends AbstractViewPresenter<VentaOrdenListViewModel>
        implements PropertyChangeListener {

    public static final String ACTION_CREAR_ORDEN = "Nueva Orden";
    public static final String ACTION_ABRIR_ORDEN = "Abrir Orden";

    private VentaDetailService ventaService;
    private OrdenDetailViewPresenter ordenPresenter;
    private ProductoVentaSelectorPresenter menuPresenter;
    private OrdenService ordenService;

    private VentaOrdenListViewPresenter(VentaDetailService ventaService) {
        super(new VentaOrdenListViewModel());
        this.ventaService = ventaService;

    }

    public OrdenDetailViewPresenter getOrdenPresenter() {
        return ordenPresenter;
    }

    public ProductoVentaSelectorPresenter getMenuPresenter() {
        return menuPresenter;
    }

    public VentaOrdenListViewPresenter(VentaDetailService ventaService,
            OrdenService ordenService) {
        this(ventaService);
        this.ordenService = ordenService;
        ordenPresenter = new OrdenDetailViewPresenter(ordenService);
        menuPresenter = new ProductoVentaSelectorPresenter(ordenService);
        menuPresenter.addPropertyChangeListener(ProductoVentaSelectorPresenter.PROP_PRODUCTO_SELECCIONADO, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                ordenPresenter.refreshState();
            }
        });
        ordenPresenter.addBeanPropertyChangeListener(OrdenDetailViewModel.PROP_MODO_AGREGO_ACTIVADO, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ((boolean) evt.getNewValue()) {
                    ordenService.setModoAgrego(ordenPresenter.getBean().getProducto_orden_seleccionado());
                    menuPresenter.showSeccionesAgregadas();
                    menuPresenter.onMostrarSeccionClick();
                } else {
                    ordenService.setModoAgrego(null);
                    menuPresenter.refreshState();
                }
            }
        });
        refreshState();
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_ABRIR_ORDEN) {
            @Override
            public Optional doAction() {
                onAbrirOrdenAction();
                return Optional.empty();
            }

        });
        registerOperation(new AbstractViewAction(ACTION_CREAR_ORDEN) {
            @Override
            public Optional doAction() {
                onCrearOrdenAction();
                return Optional.empty();
            }

        });
    }

    private void onCrearOrdenAction() {
        Orden newOrden = ventaService.createNewOrden();
        getBean().setElemento_seleccionado(newOrden);
        onAbrirOrdenAction();
        refreshState();
    }

    private void onAbrirOrdenAction() {
        ordenPresenter.setCodOrden(getBean().getElemento_seleccionado().getCodOrden());

        menuPresenter.setMesaSeleccionada(getBean().getElemento_seleccionado().getMesacodMesa());
        menuPresenter.setCodOrdenEnlazada(getBean().getElemento_seleccionado().getCodOrden());
    }

    @Override
    protected Optional refreshState() {
        Orden o = getBean().getElemento_seleccionado();
        getBean().setLista_elementos(ventaService.getOrdenesActivas());
        getBean().setElemento_seleccionado(o);
        return Optional.empty();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        refreshState();
    }

}
