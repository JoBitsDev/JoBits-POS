/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.almacen.presenter;

import com.jobits.pos.controller.almacen.AlmacenManageService;
import com.jobits.pos.cordinator.DisplayType;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.core.domain.models.Almacen;
import com.jobits.pos.main.Application;
import com.jobits.pos.ui.almacen.FacturaView;
import com.jobits.pos.ui.almacen.PendingOperationsListView;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractListViewPresenter;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import java.util.Optional;

/**
 *
 * @author Home
 */
public class OperacionesListPresenter extends AbstractListViewPresenter<OperacionListModel> {

    AlmacenManageService service = PosDesktopUiModule.getInstance().getImplementation(AlmacenManageService.class);
    public static final String ACTION_CERRAR_POPUP = "Cerrar";
    public static final String ACTION_ABRIR_Y_CONFIRMAR = "Ver y confirmar";

    public OperacionesListPresenter(Almacen almacen) {
        super(new OperacionListModel(), PendingOperationsListView.VIEW_NAME);
        getBean().setAlmacen(almacen);
        setListToBean();
        registerExtraOperations();
    }

    protected void registerExtraOperations() {
        registerOperation(new AbstractViewAction(ACTION_CERRAR_POPUP) {
            @Override
            public Optional doAction() {
                Application.getInstance().getNavigator().navigateUp();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_ABRIR_Y_CONFIRMAR) {
            @Override
            public Optional doAction() {
                Application.getInstance().getNavigator().navigateUp();
                return Optional.empty();
            }
        });

    }

    @Override
    protected void setListToBean() {
        getBean().getLista_elementos().clear();
        getBean().getLista_elementos().addAll(service.getOperacionesPendientes(getBean().getAlmacen().getCodAlmacen()));
    }

    private void onAbrirClick() {
        getBean().getElemento_seleccionado();
        NavigationService.getInstance().navigateTo(FacturaView.VIEW_NAME,
                new FacturaViewPresenter(PosDesktopUiModule.getInstance()
                        .getImplementation(AlmacenManageService.class),
                        getBean().getElemento_seleccionado()), DisplayType.POPUP);
    }

    @Override
    protected void onAgregarClick() {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void onEditarClick() {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void onEliminarClick() {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

}
