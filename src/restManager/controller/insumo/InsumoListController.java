/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.insumo;

import GUI.Views.Insumo.InsumoListView;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import restManager.controller.AbstractDetailController;
import restManager.controller.AbstractListController;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.Insumo;
import restManager.persistencia.models.InsumoDAO;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class InsumoListController extends AbstractListController<Insumo> {

    private final String PREFIX_FOR_ID = "In-";

    public InsumoListController() {
        super(InsumoDAO.getInstance());
    }

    public InsumoListController(Window frame) {
        super(InsumoDAO.getInstance());
        constructView(frame);
    }

    /**
     *
     * @param parent the value of parent
     */
    @Override
    public void constructView(java.awt.Container parent) {
        setView(new InsumoListView(this, (Dialog) parent, true));
        getView().updateView();
        getView().setVisible(true);
    }

    @Override
    public void createInstance() {
        detailController = getDetailControllerForNew();
        items = null;
        getView().updateView();//TODO:metodo forzado
    }

    @Override
    public AbstractDetailController<Insumo> getDetailControllerForNew() {
        return new InsumoCreateEditController(getView());
    }

    @Override
    public AbstractDetailController<Insumo> getDetailControllerForEdit(Insumo selected) {
        return new InsumoCreateEditController(getSelected(), getView());
    }

}
