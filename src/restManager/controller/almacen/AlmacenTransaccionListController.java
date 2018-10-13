/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.almacen;

import GUI.Views.Almacen.AlmacenTransaccionsListView;
import java.awt.Dialog;
import java.awt.Window;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import javax.swing.JDialog;
import restManager.controller.AbstractController;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.Almacen;
import restManager.persistencia.Transaccion;
import restManager.persistencia.models.TransaccionDAO;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class AlmacenTransaccionListController extends AbstractController<Transaccion> {
    
    AlmacenTransaccionsListView view;
    private Almacen a;
    
    public AlmacenTransaccionListController() {
        super(new TransaccionDAO());
    }
    
    public AlmacenTransaccionListController(JDialog parent) {
        this();
        constructView(parent);
    }
    
    @Override
    public void createInstance() {
        Transaccion f = new Transaccion();
        f.setFechaTransaccion(new Date());
        f.setValorTotalTransacciones(Float.parseFloat("0"));
        f.setValorMerma(Float.parseFloat("0"));
        f.setAlmacencodAlmacen(a);
        f.setInsumoTransaccionList(new ArrayList<>());
        
        AlmacenTransaccionManageController newTransaccion = new AlmacenTransaccionManageController(view, f);
        newTransaccion = null;
        items = null;
        view.updateView(getItems());
    }
    
    @Override
    public void deleteInstance(Transaccion selected) {
        super.deleteInstance(selected); //To change body of generated methods, choose Tools | Templates.
        view.updateView(getItems());
    }
    
    @Override
    public void editInstance(Transaccion selected) {
        AlmacenTransaccionManageController newTransaccion = new AlmacenTransaccionManageController(view, selected);
        newTransaccion = null;
        items = null;
        view.updateView(getItems());
    }
    
    @Override
    public void constructView(Window parent) {
        view = new AlmacenTransaccionsListView(this, (Dialog) parent, true);
        view.updateView(getItems());
        view.setVisible(true);
    }
    
}
