/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.trabajadores.presenter;

import com.jobits.pos.controller.trabajadores.PersonalListService;
import com.jobits.pos.cordinator.DisplayType;
import com.jobits.pos.main.Application;
import com.root101.clean.core.app.services.utils.TipoNotificacion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractListViewPresenter;
import com.jobits.pos.ui.trabajadores.PersonalListView;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class PersonalListViewPresenter extends AbstractListViewPresenter<PersonalListViewModel> {

    private final PersonalListService service = PosDesktopUiModule.getInstance().getImplementation(PersonalListService.class);

    public PersonalListViewPresenter() {
        super(new PersonalListViewModel(), PersonalListView.VIEW_NAME);
        setListToBean();
    }

    @Override
    protected void onAgregarClick() {
        Application.getInstance().getNavigator().navigateTo("Crear Personal", null, DisplayType.POPUP);
        setListToBean();
    }

    @Override
    protected void onEditarClick() {
        if (getBean().getElemento_seleccionado() == null) {
            throw new IllegalArgumentException("Debe seleccionar una trabajador");
        }
        Application.getInstance().getNavigator().navigateTo(
                "Crear Personal",
                new PersonalDetailViewPresenter(
                        getBean().getElemento_seleccionado()),
                DisplayType.POPUP);
        setListToBean();

    }

    @Override
    protected void onEliminarClick() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("ATENCION: esto elimina al usuario de todas las ordenes que ha atendido."
                        + "\n Esta seguro que desea continuar?",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            service.destroy(getBean().getElemento_seleccionado());
            setListToBean();
            Application.getInstance().getNotificationService().notify(R.RESOURCE_BUNDLE.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
        }

    }

    @Override
    protected void setListToBean() {
        getBean().getLista_elementos().clear();
        getBean().getLista_elementos().addAll(service.getItems());
    }

}
