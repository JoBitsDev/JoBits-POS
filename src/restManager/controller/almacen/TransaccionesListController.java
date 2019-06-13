/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.almacen;

import GUI.Views.AbstractView;
import GUI.Views.Almacen.TransaccionListView;
import com.jidesoft.swing.JideButton;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.util.List;
import javax.swing.JButton;
import restManager.controller.AbstractDetailController;
import restManager.controller.AbstractListController;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.Almacen;
import restManager.persistencia.Transaccion;
import restManager.persistencia.models.AbstractModel;
import restManager.persistencia.models.TransaccionDAO;
import restManager.persistencia.models.TransaccionEntradaDAO;
import restManager.printservice.Impresion;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class TransaccionesListController extends AbstractListController<Transaccion> {

    Almacen a;

    public TransaccionesListController(AbstractView parent, Almacen a) {
        super(TransaccionDAO.getInstance());
        this.a = a;
        TransaccionDAO.getInstance().addPropertyChangeListener(this);
        TransaccionEntradaDAO.getInstance().addPropertyChangeListener(this);
        constructView(parent);
    }

    @Override
    public AbstractDetailController<Transaccion> getDetailControllerForNew() {
        return new TransaccionDetailController(getView(), a);
    }

    @Override
    public AbstractDetailController<Transaccion> getDetailControllerForEdit(Transaccion selected) {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void constructView(Container parent) {
        setView(new TransaccionListView(this, (AbstractView) parent, true));
        getView().getjButtonAdd().setVisible(false);
        getView().getjButtonEdit().setVisible(false);       

        getView().updateView();
        getView().setVisible(true);
    }

    @Override
    public List<Transaccion> getItems() {
        return a.getTransaccionList();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        getView().updateView();
    }

    @Override
    public void destroy(Transaccion selected) {
        super.destroy(selected); //To change body of generated methods, choose Tools | Templates.
        getModel().getEntityManager().refresh(selected.getAlmacen());
        getView().updateView();
   //     selected.getAlmacen().getTransaccionList().remove(selected);
//        getModel().startTransaction();
//        getModel().getEntityManager().merge(selected);
//        getModel().commitTransaction();
    }
    
    
    
    public void imprimirTransaccionesSeleccionadas(List<Transaccion> selectedsObjects) {
        if (!selectedsObjects.isEmpty()) {
        Impresion i = new Impresion();
        i.printComprobanteTransaccion(selectedsObjects);
        }
    }
    
    

}
