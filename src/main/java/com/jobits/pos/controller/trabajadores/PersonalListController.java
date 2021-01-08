/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.trabajadores;

import com.jobits.pos.ui.trabajadores.PersonalListView;
import java.awt.Dialog;
import java.awt.Window;
import java.util.Date;
import java.util.List;
import com.jobits.pos.controller.AbstractDetailController;
import com.jobits.pos.controller.OldAbstractListController;
import com.jobits.pos.domain.models.AsistenciaPersonal;
import com.jobits.pos.domain.models.Orden;
import com.jobits.pos.domain.models.Personal;
import com.jobits.pos.adapters.repo.autenticacion.PersonalDAO;
import com.jobits.pos.servicios.impresion.Impresion;
import java.util.Collections;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class PersonalListController extends OldAbstractListController<Personal> implements PersonalListService {

    public PersonalListController() {
        super(PersonalDAO.getInstance());
    }

    public PersonalListController(Window frame) {
        this();
    }

    /**
     *
     * @param parent the value of parent
     */
    @Override
    public void constructView(java.awt.Container parent) {

    }

    @Override
    public List<Personal> getItems() {
        List<Personal> p = getModel().findAll();
        for (Personal pe : p) {
            pe.setPagoPendiente((float) 0);
            for (AsistenciaPersonal a : pe.getAsistenciaPersonalList()) {
                if (pe.getUltimodiaPago() == null) {
                    pe.setUltimodiaPago(new Date());
                }
                if (a.getVenta().getFecha().compareTo(pe.getUltimodiaPago()) >= 0 && a.getVenta().getVentaTotal() != null) {
                    pe.setPagoPendiente(pe.getPagoPendiente() != null ? pe.getPagoPendiente() + a.getPago() : a.getPago());
                }
            }
            getModel().startTransaction();
            getModel().edit(pe);
            getModel().commitTransaction();
        }
        Collections.sort(p);
        return p;
    }

    @Override
    public AbstractDetailController<Personal> getDetailControllerForNew() {
        return new PersonalDetailController();
    }

    @Override
    public AbstractDetailController<Personal> getDetailControllerForEdit(Personal selected) {
        return new PersonalDetailController();
    }

    @Override
    public void destroy(Personal selected) {
        if (!selected.getOrdenList().isEmpty()) {
            getModel().startTransaction();
            for (Orden o : selected.getOrdenList()) {
                o.setPersonalusuario(null);
            }
            super.destroy(selected);
            getModel().commitTransaction();
        } else {
            super.destroy(selected); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
