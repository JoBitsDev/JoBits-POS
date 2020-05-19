/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.areaventa;

import java.awt.Container;
import java.util.ArrayList;
import com.jobits.pos.controller.AbstractDetailController;
import com.jobits.pos.controller.OldAbstractListController;
import com.jobits.pos.exceptions.DuplicatedException;
import com.jobits.pos.exceptions.ValidatingException;
import com.jobits.pos.domain.models.Area;
import com.jobits.pos.domain.models.Mesa;
import com.jobits.pos.adapters.repo.AreaDAO;
import com.jobits.pos.adapters.repo.MesaDAO;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class AreaVentaController extends OldAbstractListController<Area> {

    public AreaVentaController() {
        super(AreaDAO.getInstance());
    }


    @Override
    public void constructView(Container parent) {
    }

    @Override
    public AbstractDetailController<Area> getDetailControllerForNew() {
        return new AreaDetailController(getView());
    }

    @Override
    public AbstractDetailController<Area> getDetailControllerForEdit(Area selected) {
        return new AreaDetailController(selected, getView());
    }

    public void createMesa(Area a) {
        String id = showInputDialog(getView(), "Introduzca el numero de la mesa a agregar ");
        try {
            Integer i = Integer.parseInt(id);
            id = "M-" + i;
            Mesa m = MesaDAO.getInstance().find(id);
            if (m != null) {
                throw new DuplicatedException(getView());
            }
            m = new Mesa(id);
            m.setAreacodArea(a);
            m.setCapacidadMax(4);
            m.setEstado("vacia");
            m.setEstallena(false);
            m.setOrdenList(new ArrayList<>());
            a.getMesaList().add(m);
            super.getModel().startTransaction();
            MesaDAO.getInstance().create(m);
            super.getModel().commitTransaction();

            showSuccessDialog(getView());

        } catch (NumberFormatException e) {
            throw new ValidatingException(getView());
        }

    }

    public void removeMesa(Mesa selectedValue) {
        if (showConfirmDialog(getView())) {
            MesaDAO.getInstance().startTransaction();
            MesaDAO.getInstance().remove(selectedValue);
            MesaDAO.getInstance().commitTransaction();
            selectedValue.getAreacodArea().getMesaList().remove(selectedValue);
            showSuccessDialog(getView());
        }
    }

}
