/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.trabajadores;

import java.awt.Window;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import com.jobits.pos.controller.AbstractDetailController;
import com.jobits.pos.domain.models.Area;
import com.jobits.pos.domain.models.Cocina;
import com.jobits.pos.domain.models.PuestoTrabajo;
import com.jobits.pos.adapters.repo.impl.AreaDAO;
import com.jobits.pos.adapters.repo.impl.CocinaDAO;
import com.jobits.pos.adapters.repo.impl.PuestoTrabajoDAO;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class PuestoTrabajoDetailController extends AbstractDetailController<PuestoTrabajo> implements PuestoTrabajoDetailService {

    private boolean creatingMode = true;

    public PuestoTrabajoDetailController() {
        super(PuestoTrabajoDAO.getInstance());
        instance = createNewInstance();
    }

    public PuestoTrabajoDetailController(PuestoTrabajo instance) {
        super(instance, PuestoTrabajoDAO.getInstance());
        creatingMode = false;
    }
//    
//    public PuestoTrabajoDetailController() {
//        super(PuestoTrabajoDAO.getInstance());
//        instance = createNewInstance();
//
//    }
//
//    public PuestoTrabajoDetailController(PuestoTrabajo instance) {
//        super(instance, PuestoTrabajoDAO.getInstance());
//
//    }
//
//    public PuestoTrabajoDetailController(Window parent) {
//        super(parent, PuestoTrabajoDAO.getInstance());
//    }
//
//    public PuestoTrabajoDetailController(PuestoTrabajo instance, Window parent) {
//        super(instance, parent, PuestoTrabajoDAO.getInstance());
//    }

    /**
     *
     * @param parent the value of parent
     */
    @Override
    public void constructView(java.awt.Container parent) {
//        if (parent instanceof JDialog) {
//            setView(new OLDPuestoTrabajoDetailView(this, (JDialog) parent, true, getInstance()));
//
//        } else {
//            setView(new OLDPuestoTrabajoDetailView(this, (JFrame) parent, true, getInstance()));
//        }
//        getView().updateView();
//        getView().setVisible(true);
    }

    @Override
    public PuestoTrabajo createNewInstance() {
        return new PuestoTrabajo();
    }

    @Override
    public List<Area> getAreaList() {
        return AreaDAO.getInstance().findAll();
    }

    @Override
    public List<Cocina> getAreasPago() {
        return CocinaDAO.getInstance().findAll();

    }

    @Override
    public boolean isCreatingMode() {
        return creatingMode;
    }

}
