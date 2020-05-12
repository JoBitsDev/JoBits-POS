/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.seccion;

import com.jobits.pos.ui.OldAbstractView;
import com.jobits.pos.ui.seccion.CartaSeccionView;
import java.awt.Container;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import com.jobits.pos.controller.AbstractDetailController;
import com.jobits.pos.controller.OldAbstractListController;
import com.jobits.pos.exceptions.DevelopingOperationException;
import com.jobits.pos.domain.models.Carta;
import com.jobits.pos.domain.models.Seccion;
import javax.swing.JOptionPane;
import com.jobits.pos.exceptions.NoSelectedException;
import com.jobits.pos.exceptions.ValidatingException;
import com.jobits.pos.domain.models.ProductoVenta;
import com.jobits.pos.adapters.repo.MenuDAO;
import com.jobits.pos.adapters.repo.ProductoVentaDAO;
import com.jobits.pos.adapters.repo.SeccionDAO;
import com.jobits.pos.recursos.R;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class CartaListController extends OldAbstractListController<Carta> {

    public CartaListController() {
        super(MenuDAO.getInstance());
    }

    public CartaListController(OldAbstractView parent) {
        super(MenuDAO.getInstance());
        SeccionDAO.getInstance().addPropertyChangeListener(this);
        constructView(parent);

    }

    @Override
    public void createInstance() {
        String nombre = JOptionPane.showInputDialog(getView(), "Introduzca el nombre de la Carta a crear",
                "Nueva Carta", JOptionPane.QUESTION_MESSAGE);
        Carta c = new Carta();
        c.setAreaList(new ArrayList<>());
        c.setCodCarta(getModel().generateStringCode("Mnu-"));
        c.setMonedaPrincipal(R.COIN_SUFFIX.trim());
        c.setNombreCarta(nombre);
        c.setSeccionList(new ArrayList<>());
        if (nombre != null && !nombre.isEmpty()) {
            getModel().startTransaction();
            create(c);
            getModel().commitTransaction();
        } else {
            throw new ValidatingException(getView());
        }
    }

    @Override
    public AbstractDetailController<Carta> getDetailControllerForNew() {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AbstractDetailController<Carta> getDetailControllerForEdit(Carta selected) {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void constructView(Container parent) {
        setView(new CartaSeccionView(this, (OldAbstractView) parent));
        getView().updateView();
        getView().setVisible(true);
    }

    public void createSeccion(Carta selectedValue) {
        if (selectedValue == null) {
            throw new NoSelectedException(getView());
        }
        new SeccionListController().createInstanceOffline(selectedValue, getView());
    }

    public void removeSeccionFromCarta(Seccion selectedValue) {
        SeccionListController controller = new SeccionListController();
        controller.getModel().removePropertyChangeListener(controller);
        controller.destroy(selectedValue);
    }

    @Override
    public void destroy(Carta selected) {
        for (Seccion s : selected.getSeccionList()) {
            if (!s.getProductoVentaList().isEmpty()) {
                if (showConfirmDialog(getView(), "La seccion " + s.getNombreSeccion()
                        + " contiene " + s.getProductoVentaList().size() + " productos de venta enlazados \n" + "presione si para borrar los productos de venta, no para cancelar")) {
                    for (ProductoVenta p : s.getProductoVentaList()) {
                        p.setSeccionnombreSeccion(null);
                        p.setVisible(false);
                        ProductoVentaDAO.getInstance().edit(p);
                    }

                } else {
                    return;
                }
            }
        }
        super.destroy(selected); //To change body of generated methods, choose Tools | Templates.
    }

}
