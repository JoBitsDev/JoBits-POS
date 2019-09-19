/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.areaventa;

import GUI.Views.AbstractView;
import GUI.Views.areaventa.AreaVentaListView;
import java.awt.Container;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import restManager.controller.AbstractController;
import restManager.controller.AbstractDetailController;
import restManager.controller.AbstractDialogController;
import restManager.controller.AbstractListController;
import restManager.exceptions.DevelopingOperationException;
import restManager.exceptions.DuplicatedException;
import restManager.exceptions.ValidatingException;
import restManager.persistencia.Area;
import restManager.persistencia.Mesa;
import restManager.persistencia.models.AbstractModel;
import restManager.persistencia.models.AreaDAO;
import restManager.persistencia.models.MesaDAO;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class AreaVentaController extends AbstractListController<Area> {

    public AreaVentaController() {
        super(AreaDAO.getInstance());
    }

    public AreaVentaController(AbstractView parent) {
        this();
        MesaDAO.getInstance().addPropertyChangeListener(this);
        constructView(parent);
    }

    @Override
    public void constructView(Container parent) {
        setView(new AreaVentaListView(this, (AbstractView) parent));
        getView().updateView();
        getView().setVisible(true);
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
