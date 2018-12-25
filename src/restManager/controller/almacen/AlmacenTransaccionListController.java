/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.almacen;

import GUI.Views.Almacen.AlmacenTransaccionsListView;
import java.awt.Dialog;
import java.awt.Window;
import javax.swing.JDialog;
import restManager.controller.AbstractDetailController;
import restManager.controller.AbstractListController;
import restManager.persistencia.Almacen;
import restManager.persistencia.Transaccion;
import restManager.persistencia.models.TransaccionDAO;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class AlmacenTransaccionListController extends AbstractListController<Transaccion> {
    
    private Almacen a;
    
    public AlmacenTransaccionListController() {
        super(new TransaccionDAO());
    }
    
    public AlmacenTransaccionListController(JDialog parent,Almacen a) {
        this();
        this.a = a;
        constructView(parent);
    }

    /**
     *
     * @param parent the value of parent
     */
    @Override
    public void constructView(java.awt.Container parent) {
        setView(new AlmacenTransaccionsListView(this, (Dialog) parent, true));
        getView().updateView();
        getView().setVisible(true);
    }

    @Override
    public AbstractDetailController<Transaccion> getDetailControllerForNew() {
   
        return  new AlmacenTransaccionManageController(getView());
    }

    @Override
    public AbstractDetailController<Transaccion> getDetailControllerForEdit(Transaccion selected) {
        return new AlmacenTransaccionManageController(selected,getView());
    }
    
}
