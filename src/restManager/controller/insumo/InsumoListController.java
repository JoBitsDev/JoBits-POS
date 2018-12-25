/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.insumo;

import GUI.Views.Insumo.InsumoListView;
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
        this();
        constructView(frame);
    }
    
    /**
     *
     * @param parent the value of parent
     */
    @Override
    public void constructView(java.awt.Container parent) {
        setView(new InsumoListView(this, (Frame) parent, true));
        getView().updateView();
        getView().setVisible(true);
    }

    public void crossReferenceInsumo(Insumo objectAtSelectedRow) {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
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
