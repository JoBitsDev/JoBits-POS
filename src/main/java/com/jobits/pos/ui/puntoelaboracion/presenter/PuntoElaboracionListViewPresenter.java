/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.puntoelaboracion.presenter;

import com.jobits.pos.core.repo.impl.ProductoVentaDAO;
import com.jobits.pos.controller.puntoelaboracion.PuntoElaboracionListController;
import com.jobits.pos.controller.puntoelaboracion.PuntoElaboracionListService;
import com.jobits.pos.core.domain.models.Cocina;
import com.jobits.pos.core.domain.models.ProductoVenta;
import com.jobits.pos.main.Application;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.presenters.AbstractListViewPresenter;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.puntoelaboracion.PuntoElaboracionListView;
import java.util.Optional;
import javax.swing.JOptionPane;

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
        String nombre = JOptionPane.showInputDialog(null, "Introduzca el nombre del nuevo punto de elaboracion");
        service.createInstance(nombre);
        setListToBean();
    }

    @Override
    protected void onEditarClick() {
        String nombre = JOptionPane.showInputDialog(null,
                "Introduzca el nuevo nombre al punto de elaboracion",
                getBean().getElemento_seleccionado().getNombreCocina());
        service.update(getBean().getElemento_seleccionado(), nombre);
        setListToBean();
    }

    @Override
    protected void onEliminarClick() {
        Cocina c = getBean().getElemento_seleccionado();
        boolean flag = false;
        if (c == null) {
            throw new IllegalArgumentException("Seleccione una cocina");
        }
        if (!c.getProductoVentaList().isEmpty()) {
            flag = JOptionPane.showConfirmDialog(null, "El punto de elaboracion " + c
                    + " contiene " + c.getProductoVentaList().size()
                    + " productos de venta enlazados \n" + "presione si para borrar los productos de venta, no para cancelar", "Eliminar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
        }
        service.destroy(getBean().getElemento_seleccionado(), flag);
        setListToBean();
    }

    @Override
    protected void setListToBean() {
        getBean().getLista_elementos().clear();
        getBean().getLista_elementos().addAll(service.getItems());
    }

}
