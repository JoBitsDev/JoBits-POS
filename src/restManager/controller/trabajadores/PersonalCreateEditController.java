/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.trabajadores;

import GUI.Views.trabajadores.PersonalCreateEditView;
import java.awt.Dialog;
import java.awt.Window;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import restManager.controller.AbstractDetailController;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.DatosPersonales;
import restManager.persistencia.Personal;
import restManager.persistencia.PuestoTrabajo;
import restManager.persistencia.models.AbstractModel;
import restManager.persistencia.models.PersonalDAO;
import restManager.persistencia.models.PuestoTrabajoDAO;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class PersonalCreateEditController extends AbstractDetailController<Personal> {

    public PersonalCreateEditController() {
        super(PersonalDAO.getInstance());
        instance = createNewInstance();
    }

    public PersonalCreateEditController(Personal instance) {
        super(instance, PersonalDAO.getInstance());
    }

    public PersonalCreateEditController(Window parent) {
        super(parent, PersonalDAO.getInstance());
    }

    public PersonalCreateEditController(Personal instance, Window parent) {
        super(instance, parent, PersonalDAO.getInstance());
    }

    /**
     *
     * @param parent the value of parent
     */
    @Override
    public void constructView(java.awt.Container parent) {
        setView(new PersonalCreateEditView(instance, this, (Dialog) parent, true));
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
