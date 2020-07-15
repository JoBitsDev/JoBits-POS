/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.almacen;

import com.jobits.pos.ui.AbstractView;
import com.jobits.pos.ui.almacen.TransaccionListView;
import com.jidesoft.swing.JideButton;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.util.List;
import javax.swing.JButton;
import com.jobits.pos.controller.AbstractDetailController;
import com.jobits.pos.controller.AbstractListController;
import com.jobits.pos.exceptions.DevelopingOperationException;
import com.jobits.pos.persistencia.Almacen;
import com.jobits.pos.persistencia.Transaccion;
import com.jobits.pos.persistencia.modelos.AbstractModel;
import com.jobits.pos.persistencia.modelos.TransaccionDAO;
import com.jobits.pos.persistencia.modelos.TransaccionEntradaDAO;
import com.jobits.pos.servicios.impresion.Impresion;
import com.jobits.pos.servicios.impresion.formatter.ComprobanteTransaccionFormatter;

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
    public void destroy(Transaccion selected) {
        super.destroy(selected); //To change body of generated methods, choose Tools | Templates.
        //getModel().getEntityManager().refresh(selected.getAlmacencodAlmacen());
        getView().updateView();
   //     selected.getAlmacen().getTransaccionList().remove(selected);
//        getModel().startTransaction();
//        getModel().getEntityManager().merge(selected);
//        getModel().commitTransaction();
    }
    
    
    
    public void imprimirTransaccionesSeleccionadas(List<Transaccion> selectedsObjects) {
        if (!selectedsObjects.isEmpty()) {
        Impresion i = new Impresion();
        i.print(new ComprobanteTransaccionFormatter(selectedsObjects), null);
        }
    }
    
    

}
