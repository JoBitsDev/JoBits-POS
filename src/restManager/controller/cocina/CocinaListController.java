/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restManager.controller.cocina;

import GUI.Views.cocina.CocinaListView;
import java.awt.Frame;
import java.awt.Window;
import javax.swing.JFrame;
import restManager.controller.AbstractDetailController;
import restManager.controller.AbstractListController;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.Cocina;
import restManager.persistencia.models.AbstractModel;
import restManager.persistencia.models.CocinaDAO;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class CocinaListController extends AbstractListController<Cocina>{

    public CocinaListController() {
        super(new CocinaDAO());
    }

    public CocinaListController(Frame parent) {
        super(new CocinaDAO());
        constructView(parent);
    }
    
    
 
    @Override
    public AbstractDetailController<Cocina> getDetailControllerForNew() {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AbstractDetailController<Cocina> getDetailControllerForEdit(Cocina selected) {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void constructView(Window parent) {
        setView(new CocinaListView(this,(JFrame) parent, true));
        getView().updateView();
        getView().setVisible(true);
    }

}
