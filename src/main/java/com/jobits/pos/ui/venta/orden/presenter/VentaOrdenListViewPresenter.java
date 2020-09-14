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
public class VentaOrdenListViewPresenter extends AbstractViewPresenter<VentaOrdenListViewModel> implements PropertyChangeListener {

    public static final String ACTION_ABRIR_ORDEN = "Editar Orden";

    private VentaDetailService ventaService;
    private OrdenService ordenService;

    public VentaOrdenListViewPresenter(VentaDetailService ventaService) {
        super(new VentaOrdenListViewModel());
        this.ventaService = ventaService;

    }

    public VentaOrdenListViewPresenter(VentaDetailService ventaService, OrdenController ordenService) {
        this(ventaService);
        this.ordenService = ordenService;
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
    }

    private void onAbrirOrdenAction() {
        if (getBean().getElemento_seleccionado() == null) {
            //Orden newOrden =
            ventaService.createNewOrden();
            refreshState();
            //throw new IllegalArgumentException("No hay una orden seleccionada");
        }
        //Application.getInstance().getNavigator().navigateTo(OrdenDetailFragmentView.VIEW_NAME, ordenPresenter);
    }

    @Override
    protected Optional refreshState() {
        getBean().setLista_elementos(createPresentersFrom(ventaService.getOrdenesActivas()));
        return Optional.empty();
    }

    private List<OrdenDetailViewPresenter> createPresentersFrom(List<Orden> pedidos) {
        ArrayListModel<OrdenDetailViewPresenter> ret = new ArrayListModel<>();
        for (Orden x : pedidos) {
            OrdenDetailViewPresenter newPresenter = new OrdenDetailViewPresenter(ordenService);
            ret.add(newPresenter);
            newPresenter.addBeanPropertyChangeListener(OrdenDetailViewModel.PROP_ORDEN_STATUS_UPDATE, this);
        }
        return ret;
    }

    public ArrayListModel<OrdenDetailViewPresenter> getOrdenPresenter() {
        return getBean().getLista_elementos();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        refreshState();
    }

}
