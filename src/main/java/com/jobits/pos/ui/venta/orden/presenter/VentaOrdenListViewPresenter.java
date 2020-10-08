/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.orden.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.venta.OrdenController;
import com.jobits.pos.controller.venta.OrdenService;
import com.jobits.pos.controller.venta.VentaDetailService;
import com.jobits.pos.domain.models.Orden;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Optional;

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
    private OrdenService ordenService;
    private OrdenDetailViewPresenter newPresenter;

    private VentaOrdenListViewPresenter(VentaDetailService ventaService) {
        super(new VentaOrdenListViewModel());
        this.ventaService = ventaService;

    }

    public OrdenDetailViewPresenter getNewPresenter() {
        return newPresenter;
    }

    public VentaOrdenListViewPresenter(VentaDetailService ventaService, OrdenController ordenService) {
        this(ventaService);
        this.ordenService = ordenService;
        newPresenter = new OrdenDetailViewPresenter(ordenService);
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
        ventaService.createNewOrden();
        refreshState();
    }
    private void onAbrirOrdenAction(){
        newPresenter.setCodOrden(getBean().getElemento_seleccionado().getCodOrden());;
    }

    @Override
    protected Optional refreshState() {
        getBean().setLista_elementos(ventaService.getOrdenesActivas());
        return Optional.empty();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        refreshState();
    }

}
