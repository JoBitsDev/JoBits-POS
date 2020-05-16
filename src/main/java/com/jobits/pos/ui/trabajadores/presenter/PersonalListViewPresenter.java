/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.trabajadores.presenter;

import com.jobits.pos.controller.trabajadores.PersonalCreateEditController;
import com.jobits.pos.controller.trabajadores.PersonalListController;
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

    PersonalListController controller;

    public PersonalListViewPresenter(PersonalListController controller) {
        super(new PersonalListViewModel(), PersonalListView.VIEW_NAME);
        this.controller = controller;
        setListToBean();
    }

    @Override
    protected void onAgregarClick() {
        PersonalCreateEditController newController = new PersonalCreateEditController();
        setListToBean();
    }

    @Override
    protected void onEditarClick() {
        PersonalCreateEditController newController = new PersonalCreateEditController(getBean().getElemento_seleccionado());
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
