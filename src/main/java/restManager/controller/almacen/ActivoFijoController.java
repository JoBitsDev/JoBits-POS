/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.almacen;

import GUI.Views.Almacen.ActivosList;
import java.awt.Container;
import java.awt.Window;
import java.util.ArrayList;
import java.util.List;
import restManager.controller.AbstractDetailController;
import restManager.controller.AbstractDialogController;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.ActivoFijo;
import restManager.persistencia.Ubicacion;
import restManager.persistencia.models.AbstractModel;
import restManager.persistencia.models.ActivoFijoDAO;
import restManager.persistencia.models.UbicacionDAO;

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
        u.setNombre(showInputDialog(getView(), "Introduzca el nombre de la ubiicación"));
        getModel().startTransaction();
        UbicacionDAO.getInstance().create(u);
        getModel().commitTransaction();
        return u;
    }

}
