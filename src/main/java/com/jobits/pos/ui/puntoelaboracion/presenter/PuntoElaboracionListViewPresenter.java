/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.puntoelaboracion.presenter;

import com.jobits.pos.core.domain.models.Cocina;
import com.jobits.pos.main.Application;
import com.root101.clean.core.app.services.utils.TipoNotificacion;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractListViewPresenter;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.puntoelaboracion.PuntoElaboracionListView;

import java.util.Optional;
import javax.swing.JOptionPane;

import com.jobits.pos.controller.puntoelaboracion.PuntoElaboracionService;

/**
 * JoBits
 *
 * @author Jorge
 */
public class PuntoElaboracionListViewPresenter extends AbstractListViewPresenter<PuntoElaboracionListViewModel> {

    public static String ACTION_CHANGE_RECIBIR_NOTIFICACION = "Recibir notificaciones";
    public static String ACTION_CHANGE_LIMITAR_VENTA = "Limitar ventas";

    private final PuntoElaboracionService service = PosDesktopUiModule.getInstance().getImplementation(PuntoElaboracionService.class);

    public PuntoElaboracionListViewPresenter() {
        super(new PuntoElaboracionListViewModel(), PuntoElaboracionListView.VIEW_NAME);
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
                service.edit(cocinaSeleccionada);//TODO: activar comportamiento
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
                service.edit(ptoSeleccionado);//TODO: activar comportamiento
                getBean().getLista_elementos().fireContentsChanged(getBean().getLista_elementos().indexOf(ptoSeleccionado));
                return Optional.empty();
            }
        });
    }

    @Override
    protected void onAgregarClick() {
        String nombre = JOptionPane.showInputDialog(null, "Introduzca el nombre del nuevo punto de elaboracion");
        if (nombre != null) {
            Cocina c = new Cocina();
            c.setNombreCocina(nombre);
            service.create(c);
            setListToBean();
        }
    }

    @Override
    protected void onEditarClick() {
        Cocina cocina = getBean().getElemento_seleccionado();
        if (cocina == null) {
            throw new IllegalArgumentException("Seleccione una cocina");
        }
        String nombre = JOptionPane.showInputDialog(null,
                "Introduzca el nuevo nombre al punto de elaboracion",
                getBean().getElemento_seleccionado().getNombreCocina());
        if (nombre != null) {
//            cocina.setNombreCocina(nombre);
            service.edit(cocina.getCodCocina(), nombre);
            setListToBean();
        }
    }

    @Override
    protected void onEliminarClick() {
        Cocina selected = getBean().getElemento_seleccionado();
        if (selected == null) {
            throw new IllegalArgumentException("Seleccione una cocina");
        }
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Esta seguro que desea eliminar: " + selected,
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            if ((boolean) Application.getInstance().getNotificationService().showDialog(
                    "El punto de elaboracion " + selected
                            + " contiene "
                            + " productos de venta enlazados \n"
                            + "presione si para ocultar los productos de venta asociados, no para cancelar",
                    TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
                service.destroyInCascade(selected.getCodCocina());
            } else {
                service.destroy(selected);
            }
            setListToBean();
        }
    }

    @Override
    protected void setListToBean() {
        getBean().getLista_elementos().clear();
        getBean().getLista_elementos().addAll(service.findAll());
    }

}
