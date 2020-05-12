/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.seccion;

import com.jobits.pos.ui.OldAbstractView;
import com.jobits.pos.ui.seccion.SeccionListView;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import com.jobits.pos.controller.AbstractDetailController;
import com.jobits.pos.controller.OldAbstractListController;
import com.jobits.pos.exceptions.DevelopingOperationException;
import com.jobits.pos.domain.models.Area;
import com.jobits.pos.domain.models.Carta;
import com.jobits.pos.domain.models.ProductoVenta;
import com.jobits.pos.domain.models.Seccion;
import com.jobits.pos.adapters.repo.AbstractRepository;
import com.jobits.pos.adapters.repo.ProductoVentaDAO;
import com.jobits.pos.adapters.repo.SeccionDAO;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class SeccionListController extends OldAbstractListController<Seccion> {

    public Carta owner;

    public SeccionListController(Carta owner) {
        super(SeccionDAO.getInstance());
        this.owner = owner;
    }

    public SeccionListController() {
        super(SeccionDAO.getInstance());
    }

    public SeccionListController(Window parent) {
        super(SeccionDAO.getInstance());
        constructView(parent);
    }

    @Override
    public void createInstance() {
        String nombre = JOptionPane.showInputDialog(getView(), "Introduzca el nombre de la sección a crear",
                "Nueva Sección", JOptionPane.QUESTION_MESSAGE);
        Seccion newSeccion = new Seccion();
        newSeccion.setDescripcion("");
        newSeccion.setNombreSeccion(nombre);
        newSeccion.setProductoVentaList(new ArrayList<>());
        if (owner != null) {
            newSeccion.setCartacodCarta(owner);
        }

        if (nombre != null && !nombre.isEmpty()) {
            if (validate(newSeccion)) {
                getModel().startTransaction();
                create(newSeccion);
                getModel().commitTransaction();
            } else {
                showErrorDialog(getView(), "La sección a crear ya existe");
            }
        }
    }

    public void createInstanceOffline(Carta a, OldAbstractView view) {
        setView(view);
        String nombre = JOptionPane.showInputDialog(getView(), "Introduzca el nombre de la sección a crear",
                "Nueva Sección", JOptionPane.QUESTION_MESSAGE);
        getModel().removePropertyChangeListener(this);
        Seccion newSeccion = new Seccion();
        newSeccion.setDescripcion("");
        newSeccion.setNombreSeccion(nombre);
        newSeccion.setProductoVentaList(new ArrayList<>());
        newSeccion.setCartacodCarta(a);
        if (owner != null) {
            newSeccion.setCartacodCarta(owner);
        }

        if (nombre != null && !nombre.isEmpty()) {
            if (validate(newSeccion)) {
                getModel().startTransaction();
                create(newSeccion);
                getModel().commitTransaction();
                a.getSeccionList().add(newSeccion);
            } else {
                showErrorDialog(getView(), "La sección a crear ya existe");
            }
        }
    }

    @Override
    public void update(Seccion selected) {
        String nombre = JOptionPane.showInputDialog(getView(), "Introduzca el nuevo nombre de la sección",
                "Editar Sección", JOptionPane.QUESTION_MESSAGE);
        selected.setNombreSeccion(nombre);
        if (nombre != null && !nombre.isEmpty()) {
            if (validate(selected)) {
                update(selected);
            } else {
                showErrorDialog(getView(), "La sección a crear ya existe");
            }
        }
    }

    @Override
    public void destroy(Seccion selected) {
        if (!selected.getProductoVentaList().isEmpty()) {
            if (showConfirmDialog(getView(), "La seccion " + selected
                    + " contiene " + selected.getProductoVentaList().size()
                    + " productos de venta enlazados \n" + "presione si para borrar el enlace de los productos de venta, no para cancelar")) {
                for (ProductoVenta x : selected.getProductoVentaList()) {
                    x.setSeccionnombreSeccion(null);
                    x.setVisible(false);
                    ProductoVentaDAO.getInstance().edit(x);
                }
            } else {
                return;
            }
        }
        super.destroy(selected); //To change body of generated methods, choose Tools | Templates.
        selected.getCartacodCarta().getSeccionList().remove(selected);
    }

    @Override
    public AbstractDetailController<Seccion> getDetailControllerForNew() {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AbstractDetailController<Seccion> getDetailControllerForEdit(Seccion selected) {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param parent the value of parent
     */
    @Override
    public void constructView(java.awt.Container parent) {
        setView(new SeccionListView(this, (Dialog) parent, true));
        getView().updateView();
        getView().setVisible(true);
    }

    private boolean validate(Seccion newSeccion) {
        return getItems().stream().noneMatch((x)
                -> (x.getNombreSeccion().toLowerCase().equals(newSeccion.getNombreSeccion().toLowerCase())));
    }

}
