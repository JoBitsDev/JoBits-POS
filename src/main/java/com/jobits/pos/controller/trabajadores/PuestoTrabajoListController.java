/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.trabajadores;

import com.jobits.pos.ui.AbstractView;
import com.jobits.pos.ui.trabajadores.PuestoTrabajoListView;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import com.jobits.pos.controller.AbstractDialogController;
import com.jobits.pos.controller.AbstractDetailController;
import com.jobits.pos.controller.AbstractListController;
import com.jobits.pos.exceptions.DevelopingOperationException;
import com.jobits.pos.domain.models.PuestoTrabajo;
import com.jobits.pos.adapters.repo.AbstractRepository;
import com.jobits.pos.adapters.repo.PuestoTrabajoDAO;
import com.jobits.pos.recursos.R;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class PuestoTrabajoListController extends AbstractListController<PuestoTrabajo> {

    //PuestoTrabajoListView getView();
    public PuestoTrabajoListController() {
        super(PuestoTrabajoDAO.getInstance());
    }

    public PuestoTrabajoListController(Window frame) {
        this();
        constructView(frame);
    }

    /**
     *
     * @param parent the value of parent
     */
    @Override
    public void constructView(java.awt.Container parent) {
        setView(new PuestoTrabajoListView(this, (Dialog) parent, true));
        getView().updateView();
        getView().setVisible(true);

    }

    @Override
    public AbstractDetailController<PuestoTrabajo> getDetailControllerForNew() {
        return new PuestoTrabajoCreateEditController(getView());
    }

    @Override
    public AbstractDetailController<PuestoTrabajo> getDetailControllerForEdit(PuestoTrabajo selected) {
        return new PuestoTrabajoCreateEditController(selected, getView());
    }

}
