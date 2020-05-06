/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.trabajadores;

import com.jobits.pos.ui.trabajadores.PuestoTrabajoCreateEditView;
import java.awt.Window;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import com.jobits.pos.controller.AbstractDetailController;
import com.jobits.pos.persistencia.Area;
import com.jobits.pos.persistencia.Cocina;
import com.jobits.pos.persistencia.PuestoTrabajo;
import com.jobits.pos.persistencia.modelos.AreaDAO;
import com.jobits.pos.persistencia.modelos.CocinaDAO;
import com.jobits.pos.persistencia.modelos.PuestoTrabajoDAO;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class PuestoTrabajoCreateEditController extends AbstractDetailController<PuestoTrabajo> {

    public PuestoTrabajoCreateEditController() {
        super(PuestoTrabajoDAO.getInstance());
        instance = createNewInstance();

    }

    public PuestoTrabajoCreateEditController(PuestoTrabajo instance) {
        super(instance, PuestoTrabajoDAO.getInstance());

    }

    public PuestoTrabajoCreateEditController(Window parent) {
        super(parent, PuestoTrabajoDAO.getInstance());
    }

    public PuestoTrabajoCreateEditController(PuestoTrabajo instance, Window parent) {
        super(instance, parent, PuestoTrabajoDAO.getInstance());
    }

    /**
     *
     * @param parent the value of parent
     */
    @Override
    public void constructView(java.awt.Container parent) {
        if (parent instanceof JDialog) {
            setView(new PuestoTrabajoCreateEditView(this, (JDialog) parent, true, instance));

        } else {
            setView(new PuestoTrabajoCreateEditView(this, (JFrame) parent, true, instance));
        }
        getView().updateView();
        getView().setVisible(true);
    }

    @Override
    public PuestoTrabajo createNewInstance() {
        return new PuestoTrabajo();
    }

    public List<Area> getAreaList() {
        return AreaDAO.getInstance().findAll();
    }

    public List<Cocina> getAreasPago() {
        return CocinaDAO.getInstance().findAll();

    }

}
