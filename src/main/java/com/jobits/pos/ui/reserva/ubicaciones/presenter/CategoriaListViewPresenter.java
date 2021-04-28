/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.reserva.ubicaciones.presenter;

import com.jobits.pos.cordinator.DisplayType;
import com.jobits.pos.main.Application;
import com.root101.clean.core.app.services.utils.TipoNotificacion;
import com.jobits.pos.reserva.core.domain.Categoria;
import com.jobits.pos.reserva.core.module.ReservaCoreModule;
import com.jobits.pos.reserva.core.usecase.CategoriaUseCase;
import com.jobits.pos.ui.presenters.AbstractListViewPresenter;
import com.jobits.pos.ui.reserva.ubicaciones.CategoriaDetailView;
import com.jobits.pos.ui.reserva.ubicaciones.CategoriasListView;

/**
 *
 * @author Home
 */
public class CategoriaListViewPresenter extends AbstractListViewPresenter<CategoriaListViewModel> {

    CategoriaUseCase categoriaUseCase = ReservaCoreModule.getInstance().getImplementation(CategoriaUseCase.class);

    public CategoriaListViewPresenter() {
        super(new CategoriaListViewModel(), CategoriasListView.VIEW_NAME);
        setListToBean();
    }

    @Override
    protected void onAgregarClick() {
        Application.getInstance().getNavigator().navigateTo(
                CategoriaDetailView.VIEW_NAME, new CategoriaDetailViewPresenter(
                        new Categoria(), true), DisplayType.POPUP);
        setListToBean();
    }

    @Override
    protected void onEditarClick() {
        if (getBean().getElemento_seleccionado() != null) {
            Application.getInstance().getNavigator().navigateTo(
                    CategoriaDetailView.VIEW_NAME, new CategoriaDetailViewPresenter(
                            getBean().getElemento_seleccionado(), false), DisplayType.POPUP);
            setListToBean();
        } else {
            Application.getInstance().getNotificationService().
                    notify("Seleccione una Categoria", TipoNotificacion.ERROR);
        }

    }

    @Override
    protected void onEliminarClick() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Esta seguro que desea eliminar la categoria seleccionada?",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            categoriaUseCase.destroy(getBean().getElemento_seleccionado());
            setListToBean();
        }
    }

    @Override
    protected void setListToBean() {
        getBean().setLista_elementos(categoriaUseCase.findAll());
        getBean().setElemento_seleccionado(null);
    }

}
