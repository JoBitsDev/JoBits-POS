/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.trabajadores;

import GUI.Views.AbstractView;
import GUI.Views.trabajadores.PuestoTrabajoCreateEditView;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Window;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import restManager.controller.AbstractDialogController;
import restManager.controller.AbstractDetailController;
import restManager.persistencia.Cocina;
import restManager.persistencia.PuestoTrabajo;
import restManager.persistencia.models.AbstractModel;
import restManager.persistencia.models.CocinaDAO;
import restManager.persistencia.models.PuestoTrabajoDAO;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class PuestoTrabajoCreateEditController extends AbstractDetailController<PuestoTrabajo> {

    public PuestoTrabajoCreateEditController() {
        super(PuestoTrabajoDAO.getInstance());
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

    public List<Cocina> getAreasPago() {
        return  CocinaDAO.getInstance().findAll();

    }

}
