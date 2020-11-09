/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.almacen;

import com.jobits.pos.ui.almacen.ActivosList;
import java.awt.Container;
import java.awt.Window;
import java.util.ArrayList;
import java.util.List;
import com.jobits.pos.controller.AbstractDetailController;
import com.jobits.pos.controller.AbstractDialogController;
import com.jobits.pos.exceptions.DevelopingOperationException;
import com.jobits.pos.domain.models.ActivoFijo;
import com.jobits.pos.domain.models.Ubicacion;
import com.jobits.pos.adapters.repo.impl.AbstractRepository;
import com.jobits.pos.adapters.repo.impl.ActivoFijoDAO;
import com.jobits.pos.adapters.repo.impl.UbicacionDAO;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class ActivoFijoController extends AbstractDetailController<ActivoFijo> {

    public ActivoFijoController() {
        super(ActivoFijoDAO.getInstance());
        instance = createNewInstance();
        setDismissOnAction(false);
    }

    public ActivoFijoController(Window parent) {
        this();
        setParent(parent);
        constructView(parent);
    }

    @Override
    public void constructView(Container parent) {
        setView(new ActivosList(this));
        getView().updateView();
        getView().setVisible(true);
    }

    @Override
    public ActivoFijo createNewInstance() {
        return new ActivoFijo();
    }

    public List<Ubicacion> getUbicacionesList() {
        return UbicacionDAO.getInstance().findAll();
    }

    public Ubicacion createNewUbicacion() {
        Ubicacion u = new Ubicacion();
        u.setActivoFijoList(new ArrayList<>());
        String nombre = showInputDialog(getView(), "Introduzca el nombre de la ubiicación");
        if (nombre != null) {
            u.setNombre(nombre);
        } else {
            u.setNombre("");
        }
        getModel().startTransaction();
        UbicacionDAO.getInstance().create(u);
        getModel().commitTransaction();
        return u;
    }

}
