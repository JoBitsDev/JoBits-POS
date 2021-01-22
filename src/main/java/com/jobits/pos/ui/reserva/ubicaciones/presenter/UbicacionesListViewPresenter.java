/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.reserva.ubicaciones.presenter;

import com.jobits.pos.cordinator.DisplayType;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.TipoNotificacion;
import com.jobits.pos.reserva.core.domain.Ubicacion;
import com.jobits.pos.reserva.core.domain.UbicacionEstado;
import com.jobits.pos.reserva.core.module.ReservaCoreModule;
import com.jobits.pos.reserva.core.usecase.UbicacionUseCase;
import com.jobits.pos.ui.presenters.AbstractListViewPresenter;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.reserva.ubicaciones.UbicacionDetailView;
import com.jobits.pos.ui.reserva.ubicaciones.UbicacionesListView;
import java.util.Optional;

/**
 *
 * @author Home
 */
public class UbicacionesListViewPresenter extends AbstractListViewPresenter<UbicacionesListViewModel> {

    UbicacionUseCase ubicacionUseCase = ReservaCoreModule.getInstance().getImplementation(UbicacionUseCase.class);

    public static final String ACTION_ACT_DESAC_UB = "Activar Desacticar Ubicacion";

    public UbicacionesListViewPresenter() {
        super(new UbicacionesListViewModel(), UbicacionesListView.VIEW_NAME);
        setListToBean();
    }

    @Override
    protected void registerOperations() {
        super.registerOperations();
        registerOperation(new AbstractViewAction(ACTION_ACT_DESAC_UB) {
            @Override
            public Optional doAction() {
                Ubicacion ubicacion = getBean().getElemento_seleccionado();//TODO: logica en presenter
                if (ubicacion.getEstadoubicacion().equals(UbicacionEstado.HABILITADA.getEstado())) {
                    ubicacionUseCase.desactivarUbicacion(ubicacion.getIdubicacion().intValue());
                } else {
                    ubicacionUseCase.activarUbicacion(ubicacion.getIdubicacion().intValue());
                }
                setListToBean();
                return Optional.empty();
            }

        });

    }

    @Override
    protected void onAgregarClick() {
        Application.getInstance().getNavigator().navigateTo(
                UbicacionDetailView.VIEW_NAME, new UbicacionDetailViewPresenter(
                        new Ubicacion(), true), DisplayType.POPUP);
        setListToBean();
    }

    @Override
    protected void onEditarClick() {
        if (getBean().getElemento_seleccionado() != null) {
            Application.getInstance().getNavigator().navigateTo(
                    UbicacionDetailView.VIEW_NAME, new UbicacionDetailViewPresenter(
                            getBean().getElemento_seleccionado(), false), DisplayType.POPUP);
            setListToBean();
        } else {
            Application.getInstance().getNotificationService().
                    notify("Seleccione una Ubiacion", TipoNotificacion.ERROR);
        }
    }

    @Override
    protected void onEliminarClick() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Desea eliminar la ubicacion seleccionada?",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            ubicacionUseCase.destroy(getBean().getElemento_seleccionado());
            setListToBean();
        }
    }

    @Override
    protected void setListToBean() {
        getBean().setLista_elementos(ubicacionUseCase.findAll());
        getBean().setElemento_seleccionado(null);
    }
}
