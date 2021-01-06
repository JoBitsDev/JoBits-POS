/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.trabajadores.presenter;

import com.jobits.pos.controller.trabajadores.PersonalDetailController;
import com.jobits.pos.controller.trabajadores.PersonalListController;
import com.jobits.pos.controller.trabajadores.PersonalListService;
import com.jobits.pos.cordinator.DisplayType;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.TipoNotificacion;
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

    PersonalListService controller;

    public PersonalListViewPresenter(PersonalListController controller) {
        super(new PersonalListViewModel(), PersonalListView.VIEW_NAME);
        this.controller = controller;
        setListToBean();
    }

    @Override
    protected void onAgregarClick() {
        Application.getInstance().getNavigator().navigateTo("Crear Personal", null, DisplayType.POPUP);
        //    PersonalDetailController newController = new PersonalDetailController(Application.getInstance().getMainWindow());
        setListToBean();
    }

    @Override
    protected void onEditarClick() {
        if (getBean().getElemento_seleccionado() == null) {
            Application.getInstance().getNotificationService().notify("Debe seleccionar una trabajador", TipoNotificacion.ERROR);
            return;
        }
        Application.getInstance().getNavigator().navigateTo(
                "Crear Personal",
                new PersonalDetailViewPresenter(
                        new PersonalDetailController(
                                getBean().getElemento_seleccionado())),
                DisplayType.POPUP);

        //PersonalDetailController newController = new PersonalDetailController(getBean().getElemento_seleccionado(), Application.getInstance().getMainWindow());
        setListToBean();

    }

    @Override
    protected void onEliminarClick() {
        controller.destroy(getBean().getElemento_seleccionado());
        setListToBean();
    }

    @Override
    protected void setListToBean() {
        getBean().getLista_elementos().clear();
        getBean().getLista_elementos().addAll(controller.getItems());
    }

}
