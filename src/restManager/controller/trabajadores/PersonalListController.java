/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.trabajadores;

import GUI.Views.trabajadores.PersonalListView;
import java.awt.Dialog;
import java.awt.Window;
import java.util.Date;
import java.util.List;
import restManager.controller.AbstractDetailController;
import restManager.controller.AbstractListController;
import restManager.persistencia.AsistenciaPersonal;
import restManager.persistencia.Orden;
import restManager.persistencia.Personal;
import restManager.persistencia.models.PersonalDAO;
import restManager.printservice.Impresion;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class PersonalListController extends AbstractListController<Personal> {

    public PersonalListController() {
        super(PersonalDAO.getInstance());
    }

    public PersonalListController(Window frame) {
        this();
        constructView(frame);
    }

    /**
     *
     * @param parent the value of parent
     */
    @Override
    public void constructView(java.awt.Container parent) {
        setView(new PersonalListView(this, (Dialog) parent, true));
        getView().updateView();
        getView().setVisible(true);

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
        return p;
    }

    @Override
    public AbstractDetailController<Personal> getDetailControllerForNew() {
        return new PersonalCreateEditController(getView());
    }

    @Override
    public AbstractDetailController<Personal> getDetailControllerForEdit(Personal selected) {
        return new PersonalCreateEditController(selected, getView());
    }

    @Override
    public void destroy(Personal selected) {
        if (!selected.getOrdenList().isEmpty()) {
            if (showConfirmDialog(getView(), "ATENCION: esto elimina al usuario de todas las ordenes que ha atendido."
                    + "\n Esta seguro que desea continuar?")) {
                getModel().startTransaction();
                for (Orden o : selected.getOrdenList()) {
                    o.setPersonalusuario(null);
                }
                super.destroy(selected);
                getModel().commitTransaction();
                return;
            } else {
                return;
            }
        }
        super.destroy(selected); //To change body of generated methods, choose Tools | Templates.
    }

    public void pagar(Personal personal) {
            if (showConfirmDialog(getView(), "Desea imprimir un comprobante de pago")) {
            Impresion i = Impresion.getDefaultInstance();
            i.printComprobantePago(personal);
        }
        if (showConfirmDialog(getView(), "Confirme el pago a " + personal.getDatosPersonales().getNombre() + personal.getDatosPersonales().getApellidos())) {
            PersonalCreateEditController controller = new PersonalCreateEditController(personal);
            controller.setView(getView());
            controller.pagarTrabajador();
        }
    }

}
