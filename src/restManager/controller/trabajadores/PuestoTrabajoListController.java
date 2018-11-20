/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.trabajadores;

import GUI.Views.AbstractView;
import GUI.Views.trabajadores.PuestoTrabajoListView;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Window;
import restManager.controller.AbstractController;
import restManager.controller.AbstractDetailController;
import restManager.controller.AbstractListController;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.PuestoTrabajo;
import restManager.persistencia.models.AbstractModel;
import restManager.persistencia.models.PuestoTrabajoDAO;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class PuestoTrabajoListController extends AbstractListController<PuestoTrabajo> {

    //PuestoTrabajoListView getView();

    public PuestoTrabajoListController() {
        super(new PuestoTrabajoDAO());
    }

    public PuestoTrabajoListController(Frame frame) {
        this();
        constructView(frame);
    }

    @Override
    public void constructView(Window parent) {
        setView(new PuestoTrabajoListView(this, (Frame) parent, true));
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
