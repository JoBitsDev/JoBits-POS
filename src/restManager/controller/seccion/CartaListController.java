/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.seccion;

import GUI.Views.AbstractView;
import GUI.Views.seccion.CartaSeccionView;
import java.awt.Container;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import restManager.controller.AbstractDetailController;
import restManager.controller.AbstractListController;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.Carta;
import restManager.persistencia.Seccion;
import javax.swing.JOptionPane;
import restManager.exceptions.ValidatingException;
import restManager.persistencia.ProductoVenta;
import restManager.persistencia.models.MenuDAO;
import restManager.persistencia.models.ProductoVentaDAO;
import restManager.persistencia.models.SeccionDAO;
import restManager.resources.R;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class CartaListController extends AbstractListController<Carta> {

    public CartaListController() {
        super(MenuDAO.getInstance());
    }

    public CartaListController(AbstractView parent) {
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
    public void propertyChange(PropertyChangeEvent evt) {
        getView().updateView();
    }

    @Override
    public void constructView(Container parent) {
        setView(new CartaSeccionView(this, (AbstractView) parent));
        getView().updateView();
        getView().setVisible(true);
    }

    public void createSeccion(Carta selectedValue) {
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
