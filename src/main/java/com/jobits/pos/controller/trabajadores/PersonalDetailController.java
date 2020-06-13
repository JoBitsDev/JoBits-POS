/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.trabajadores;

import com.jobits.pos.ui.trabajadores.PersonalDetailView;
import java.awt.Dialog;
import java.awt.Window;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.jobits.pos.controller.AbstractDetailController;
import com.jobits.pos.exceptions.DevelopingOperationException;
import com.jobits.pos.domain.models.DatosPersonales;
import com.jobits.pos.domain.models.Personal;
import com.jobits.pos.domain.models.PuestoTrabajo;
import com.jobits.pos.adapters.repo.impl.AbstractRepository;
import com.jobits.pos.adapters.repo.autenticacion.PersonalDAO;
import com.jobits.pos.adapters.repo.impl.PuestoTrabajoDAO;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class PersonalDetailController extends AbstractDetailController<Personal> {

    public PersonalDetailController() {
        super(PersonalDAO.getInstance());
        instance = createNewInstance();
    }

    public PersonalDetailController(Personal instance) {
        super(instance, PersonalDAO.getInstance());
    }

    public PersonalDetailController(Window parent) {
        super(parent, PersonalDAO.getInstance());
    }

    public PersonalDetailController(Personal instance, Window parent) {
        super(instance, parent, PersonalDAO.getInstance());
    }

    /**
     *
     * @param parent the value of parent
     */
    @Override
    public void constructView(java.awt.Container parent) {
        if (parent instanceof JDialog) {
            setView(new PersonalDetailView(instance, this, (Dialog) parent, true));
        } else {
            setView(new PersonalDetailView(instance, this, (JFrame) parent, true));

        }
        getView().setVisible(true);
    }

    @Override
    public Personal createNewInstance() {
        Personal ret = new Personal();
        ret.setOrdenList(new ArrayList<>());
        ret.setDatosPersonales(new DatosPersonales());
        return ret;
    }

    public List<PuestoTrabajo> getPuestoTrabajoList() {
        return PuestoTrabajoDAO.getInstance().findAll();
    }

    public void pagarTrabajador() {
        instance.setUltimodiaPago(new Date());
        instance.setPagoPendiente((float) 0);
        update(selected, true);
        showSuccessDialog(null, "Trabajador Pagado exitosamente");
    }

    public void acumularSalarioTrabajador(float salarioAcumular) {
        instance.setPagoPendiente(instance.getPagoPendiente() + salarioAcumular);
        update(instance, true);
    }

}
