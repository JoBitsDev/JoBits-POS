/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.puntoelaboracion;

import GUI.Views.puntoelaboracion.CocinaListView;
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
import restManager.persistencia.ProductoVenta;
import restManager.persistencia.models.AbstractModel;
import restManager.persistencia.models.CocinaDAO;
import restManager.persistencia.models.ProductoVentaDAO;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class PuntoElaboracionListController extends AbstractListController<Cocina> {

    public PuntoElaboracionListController() {
        super(CocinaDAO.getInstance());
    }

    public PuntoElaboracionListController(Window parent) {
        super(CocinaDAO.getInstance());
        constructView(parent);
    }

    @Override
    public void createInstance() {
        String newCocina = showInputDialog(getView(), "Introduzca el nombre del nuevo punto de elaboracion");
        Cocina c = new Cocina(getModel().generateStringCode("C-"));
        c.setImpresoraList(new ArrayList<>());
        c.setIpvList(new ArrayList<>());
        c.setNombreCocina(newCocina);
        c.setProductoVentaList(new ArrayList<>());
        getItems().stream().filter((x)
                -> (x.getNombreCocina().toLowerCase().equals(newCocina.toLowerCase()))).forEachOrdered((_item) -> {
            throw new ValidatingException();
        });
        create(c);

    }

    @Override
    public void update(Cocina selected) {
        String editCocina = showInputDialog(getView(), "Introduzca el nuevo nombre al punto de elaboracion", selected.getNombreCocina());
        getItems().stream().filter((x)
                -> (x.getNombreCocina().toLowerCase().equals(editCocina.toLowerCase()))).forEachOrdered((_item) -> {
            throw new ValidatingException(getView());
        });
        selected.setNombreCocina(editCocina);
        setSelected(selected);
        super.update();

    }

    @Override
    public void destroy(Cocina selected) {
        if (!selected.getProductoVentaList().isEmpty()) {
            if (showConfirmDialog(getView(),"El punto de elaboracion " + selected
                        + " contiene " + selected.getProductoVentaList().size() 
                    + " productos de venta enlazados \n" + "presione si para borrar los productos de venta, no para cancelar")) {
                for (ProductoVenta p : selected.getProductoVentaList()) {
                    p.setCocinacodCocina(null);
                    p.setVisible(false);
                    ProductoVentaDAO.getInstance().edit(p);
                }
            }else{
                return;
            }
        }
        super.destroy(selected); //To change body of generated methods, choose Tools | Templates.
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
        setView(new CocinaListView(this, (Dialog) parent, true));
        getView().updateView();
        getView().setVisible(true);
    }

}
