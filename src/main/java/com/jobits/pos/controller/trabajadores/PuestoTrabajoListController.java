/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.trabajadores;

import com.jobits.pos.controller.AbstractDetailController;
import com.jobits.pos.controller.OldAbstractListController;
import com.jobits.pos.domain.models.PuestoTrabajo;
import com.jobits.pos.adapters.repo.impl.PuestoTrabajoDAO;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class PuestoTrabajoListController extends OldAbstractListController<PuestoTrabajo> {

    //PuestoTrabajoListView getView();
    public PuestoTrabajoListController() {
        super(PuestoTrabajoDAO.getInstance());
    }


    /**
     *
     * @param parent the value of parent
     */
    @Override
    public void constructView(java.awt.Container parent) {

    }

    @Override
    public AbstractDetailController<PuestoTrabajo> getDetailControllerForNew() {
        return new PuestoTrabajoDetailController(getView());
    }

    @Override
    public AbstractDetailController<PuestoTrabajo> getDetailControllerForEdit(PuestoTrabajo selected) {
        return new PuestoTrabajoDetailController(selected, getView());
    }

}
