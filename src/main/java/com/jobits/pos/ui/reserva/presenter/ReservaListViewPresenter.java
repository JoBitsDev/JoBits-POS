/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.reserva.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.reservas.ReservaController;
import com.jobits.pos.controller.reservas.ReservaListController;
import com.jobits.pos.controller.reservas.ReservaListService;
import com.jobits.pos.cordinator.DisplayType;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.ui.presenters.AbstractListViewPresenter;
import com.jobits.pos.ui.reserva.ReservasDetailView;
import com.jobits.pos.ui.reserva.ReservasListView;
import java.util.Optional;

/**
 *
 * @author Home
 */
public class ReservaListViewPresenter extends AbstractListViewPresenter<ReservaListViewModel> {

    private ReservaListService service;

    public ReservaListViewPresenter(ReservaListService service) {
        super(new ReservaListViewModel(), ReservasListView.VIEW_NAME);
        this.service = service;
        setListToBean();
    }

    @Override
    protected void onAgregarClick() {
//        NavigationService.getInstance().navigateTo(ReservasDetailView.VIEW_NAME,
//                new ReservaDetailViewPresenter(new ReservaController()), DisplayType.POPUP);
//        setListToBean();
    }

    @Override
    protected void onEditarClick() {
//        if (getBean().getElemento_seleccionado() != null) {
//            NavigationService.getInstance().navigateTo(ReservasDetailView.VIEW_NAME,
//                    new ReservaDetailViewPresenter(new ReservaController(getBean().getElemento_seleccionado())), DisplayType.POPUP);
//            setListToBean();
//        }
    }

    @Override
    protected void onEliminarClick() {
        service.deleteReserva(getBean().getElemento_seleccionado());
        setListToBean();
    }

    @Override
    protected void setListToBean() {
        getBean().setLista_elementos(new ArrayListModel<>(service.getListaReservas()));
        getBean().setElemento_seleccionado(null);
    }
}
