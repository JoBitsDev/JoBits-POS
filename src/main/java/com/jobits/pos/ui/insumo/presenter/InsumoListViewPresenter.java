/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.insumo.presenter;

import com.jobits.pos.controller.insumo.InsumoListService;
import com.jobits.pos.cordinator.DisplayType;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.core.domain.models.Insumo;
import com.jobits.pos.main.Application;
import com.root101.clean.core.app.services.utils.TipoNotificacion;
import com.jobits.pos.ui.insumo.InsumoDetailView;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractListViewPresenter;
import com.root101.clean.core.app.services.NotificationService;

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
        Insumo selected = getBean().getElemento_seleccionado();
        if (selected != null) {
            if ((boolean) Application.getInstance().getNotificationService().
                    showDialog("Esta seguro que desea eliminar: " + selected,
                            TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
                controller.destroy(selected);
                getBean().setElemento_seleccionado(null);
                setListToBean();
            }
        } else {
           Application.getInstance().getNotificationService().notify("Seleccione un insumo", TipoNotificacion.ERROR);
        }
    }

    @Override
    protected void setListToBean() {
        getBean().getLista_elementos().clear();
        getBean().getLista_elementos().addAll(controller.findAll());
    }

}
