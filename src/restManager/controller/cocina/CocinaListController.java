/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restManager.controller.cocina;

import GUI.Views.cocina.CocinaListView;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import java.util.ArrayList;
import javax.swing.JFrame;
import restManager.controller.AbstractDetailController;
import restManager.controller.AbstractListController;
import restManager.exceptions.DevelopingOperationException;
import restManager.exceptions.ValidatingException;
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

    public CocinaListController(Window parent) {
        super(new CocinaDAO());
        constructView(parent);
    }

    @Override
    public void createInstance() {
        String newCocina = showInputDialog(getView(), "Introduzca el nombre de la nueva Cocina");
        Cocina c = new Cocina(getModel().generateStringCode("C-"));
        c.setImpresoraList(new ArrayList<>());
        c.setIpvList(new ArrayList<>());
        c.setNombreCocina(newCocina);
        c.setProductoVentaList(new ArrayList<>());
        getItems().stream().filter((x) ->
                (x.getNombreCocina().toLowerCase().equals(newCocina.toLowerCase()))).forEachOrdered((_item) -> {
            throw new ValidatingException();
        });
        create(c);
        
    }

    @Override
    public void update(Cocina selected) {
        String editCocina = showInputDialog(getView(), "Introduzca el nuevo nombre a la Cocina", selected.getNombreCocina());     
        getItems().stream().filter((x) ->
                (x.getNombreCocina().toLowerCase().equals(editCocina.toLowerCase()))).forEachOrdered((_item) -> {
            throw new ValidatingException();
        });
        selected.setNombreCocina(editCocina);
        setSelected(selected);
        super.update();
        
    }
    
    
    
    
 
    @Override
    public AbstractDetailController<Cocina> getDetailControllerForNew() {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AbstractDetailController<Cocina> getDetailControllerForEdit(Cocina selected) {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param parent the value of parent
     */
    @Override
    public void constructView(java.awt.Container parent) {
        setView(new CocinaListView(this,(Dialog) parent, true));
        getView().updateView();
        getView().setVisible(true);
    }

}
