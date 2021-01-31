/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.insumo.presenter;

import com.jobits.pos.controller.insumo.impl.InsumoDetailController;
import com.jobits.pos.controller.insumo.InsumoListService;
import com.jobits.pos.cordinator.DisplayType;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.ui.insumo.InsumoDetailView;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractListViewPresenter;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class InsumoListViewPresenter extends AbstractListViewPresenter<InsumoListViewModel> {

    private final InsumoListService controller = PosDesktopUiModule.getInstance().getImplementation(InsumoListService.class);

    public InsumoListViewPresenter() {
        super(new InsumoListViewModel(), "Insumos");
        setListToBean();
    }

    @Override
    protected void onAgregarClick() {
        NavigationService.getInstance().navigateTo(InsumoDetailView.VIEW_NAME,
                new InsumoDetailViewPresenter(null), DisplayType.POPUP);
        setListToBean();
    }

    @Override
    protected void onEditarClick() {
        NavigationService.getInstance().navigateTo(InsumoDetailView.VIEW_NAME,
                new InsumoDetailViewPresenter(getBean().getElemento_seleccionado()), DisplayType.POPUP);
        setListToBean();

    }

    @Override
    protected void onEliminarClick() {
        controller.destroy(getBean().getElemento_seleccionado());
        getBean().setElemento_seleccionado(null);
        setListToBean();
    }

    @Override
    protected void setListToBean() {
        getBean().getLista_elementos().clear();
        getBean().getLista_elementos().addAll(controller.getItems());
    }

}
