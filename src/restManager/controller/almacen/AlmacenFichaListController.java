/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.almacen;

import GUI.Views.Almacen.AlmacenFichasListView;
import java.awt.Dialog;
import java.awt.Window;
import javax.swing.JDialog;
import restManager.controller.AbstractController;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.Ficha;
import restManager.persistencia.models.FichaDAO;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class AlmacenFichaListController extends AbstractController<Ficha> {

    AlmacenFichasListView view;

    public AlmacenFichaListController() {
        super(new FichaDAO());
    }

    public AlmacenFichaListController(JDialog parent) {
        this();
        constructView(parent);
    }

    @Override
    public void createInstance() {
        throw new DevelopingOperationException();
    }

    @Override
    public void deleteInstance(Ficha selected) {
        throw new DevelopingOperationException();
    }

    @Override
    public void editInstance(Ficha selected) {
        throw new DevelopingOperationException();
    }

    @Override
    public void constructView(Window parent) {
        view = new AlmacenFichasListView(this, (Dialog) parent, true);
        view.updateView(getItems());
        view.setVisible(true);
    }

}
