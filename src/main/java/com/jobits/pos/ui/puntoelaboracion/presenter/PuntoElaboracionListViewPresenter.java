/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.puntoelaboracion.presenter;

import com.jobits.pos.controller.puntoelaboracion.PuntoElaboracionListController;
import com.jobits.pos.controller.puntoelaboracion.PuntoElaboracionListService;
import com.jobits.pos.domain.models.Cocina;
import com.jobits.pos.ui.presenters.AbstractListViewPresenter;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.puntoelaboracion.PuntoElaboracionListView;
import java.util.Optional;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class PuntoElaboracionListViewPresenter extends AbstractListViewPresenter<PuntoElaboracionListViewModel> {

    public static String ACTION_CHANGE_RECIBIR_NOTIFICACION = "Recibir notificaciones";
    public static String ACTION_CHANGE_LIMITAR_VENTA = "Limitar ventas";

    private PuntoElaboracionListService service;

    public PuntoElaboracionListViewPresenter(PuntoElaboracionListService service) {
        super(new PuntoElaboracionListViewModel(), PuntoElaboracionListView.VIEW_NAME);
        this.service = service;
        setListToBean();
    }

    @Override
    protected void registerOperations() {
        super.registerOperations(); //To change body of generated methods, choose Tools | Templates.
        registerOperation(new AbstractViewAction(ACTION_CHANGE_RECIBIR_NOTIFICACION) {
            @Override
            public Optional doAction() {
                Cocina cocinaSeleccionada = getBean().getElemento_seleccionado();
                cocinaSeleccionada.setRecibirNotificacion(
                        !getBean().getElemento_seleccionado().getRecibirNotificacion());//TODO: logica en presenter
                service.setSelected(cocinaSeleccionada);
                service.update();//TODO: activar comportamiento
                getBean().getLista_elementos().fireContentsChanged(getBean().getLista_elementos().indexOf(cocinaSeleccionada));
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_CHANGE_LIMITAR_VENTA) {
            @Override
            public Optional doAction() {
                Cocina ptoSeleccionado = getBean().getElemento_seleccionado();
                ptoSeleccionado.setLimitarVentaInsumoAgotado(
                        !getBean().getElemento_seleccionado().getLimitarVentaInsumoAgotado());//TODO: logica en presenter
                service.setSelected(ptoSeleccionado);
                service.update();//TODO: activar comportamiento
                getBean().getLista_elementos().fireContentsChanged(getBean().getLista_elementos().indexOf(ptoSeleccionado));
                return Optional.empty();
            }
        });
    }

    @Override
    protected void onAgregarClick() {
        service.createInstance();
        setListToBean();
    }

    @Override
    protected void onEditarClick() {
        service.update(getBean().getElemento_seleccionado());
        setListToBean();
    }

    @Override
    protected void onEliminarClick() {
        service.destroy(getBean().getElemento_seleccionado());
        setListToBean();
    }

    @Override
    protected void setListToBean() {
        getBean().getLista_elementos().clear();
        getBean().getLista_elementos().addAll(service.getItems());
    }

}
