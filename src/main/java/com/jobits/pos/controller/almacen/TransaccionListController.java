/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.almacen;

import java.awt.Container;
import java.util.List;
import com.jobits.pos.controller.AbstractDetailController;
import com.jobits.pos.controller.OldAbstractListController;
import com.jobits.pos.exceptions.DevelopingOperationException;
import com.jobits.pos.domain.models.Almacen;
import com.jobits.pos.domain.models.Transaccion;
import com.jobits.pos.adapters.repo.impl.TransaccionDAO;
import com.jobits.pos.adapters.repo.impl.TransaccionEntradaDAO;
import com.jobits.pos.servicios.impresion.Impresion;
import com.jobits.pos.servicios.impresion.formatter.ComprobanteTransaccionFormatter;
import java.util.ArrayList;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class TransaccionListController extends OldAbstractListController<Transaccion> implements TransaccionListService {

    Almacen almacen;

    public TransaccionListController(/*OldAbstractView parent, */Almacen a) {
        super(TransaccionDAO.getInstance());
        this.almacen = a;
        TransaccionDAO.getInstance().addPropertyChangeListener(this);
        TransaccionEntradaDAO.getInstance().addPropertyChangeListener(this);
        // constructView(parent);
    }

    @Override
    public AbstractDetailController<Transaccion> getDetailControllerForNew() {
        return new TransaccionDetailController(getView(), almacen);
    }

    @Override
    public AbstractDetailController<Transaccion> getDetailControllerForEdit(Transaccion selected) {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void constructView(Container parent) {
//        setView(new OldTransaccionListView(this, (OldAbstractView) parent, true));
//        getView().getjButtonAdd().setVisible(false);
//        getView().getjButtonEdit().setVisible(false);
//
//        getView().updateView();
//        getView().setVisible(true);
    }

    @Override
    public void destroy(Transaccion selected) {
        super.destroy(selected); //To change body of generated methods, choose Tools | Templates.
        //getModel().getEntityManager().refresh(selected.getAlmacencodAlmacen());
        //       getView().updateView();
        //     selected.getAlmacen().getTransaccionList().remove(selected);
//        getModel().startTransaction();
//        getModel().getEntityManager().merge(selected);
//        getModel().commitTransaction();
    }

    @Override
    public void imprimirTransaccionesSeleccionadas(List<Transaccion> selectedsObjects) {
        if (!selectedsObjects.isEmpty()) {
            Impresion i = new Impresion();
            i.print(new ComprobanteTransaccionFormatter(selectedsObjects), null);
        }
    }

    @Override
    public List<Transaccion> getItems() {
        List<Transaccion> ret = new ArrayList<>();
        for (Transaccion x : getModel().findAll()) {
            if (x.getOperacionnoOperacion() != null) {
                if (x.getOperacionnoOperacion().getAlmacen() != null) {
                    if (x.getOperacionnoOperacion().getAlmacen().equals(almacen)) {
                        getModel().getEntityManager().refresh(x);
                        ret.add(x);
                    }
                }
            }
        }
        return ret;
    }

}
