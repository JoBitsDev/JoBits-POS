/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.productoventa;

import GUI.Views.productoventa.ProductoVentaCreateEditView;
import java.awt.Graphics;
import java.awt.Window;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JDialog;
import restManager.controller.AbstractDetailController;

import restManager.persistencia.Cocina;
import restManager.persistencia.Insumo;
import restManager.persistencia.ProductoVenta;
import restManager.persistencia.Seccion;
import restManager.persistencia.models.CocinaDAO;
import restManager.persistencia.models.InsumoDAO;
import restManager.persistencia.models.ProductoVentaDAO;
import restManager.persistencia.models.SeccionDAO;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class ProductoVentaCreateEditController extends AbstractDetailController<ProductoVenta> {

    public ProductoVentaCreateEditController() {
        super(ProductoVentaDAO.getInstance());
    }

    public ProductoVentaCreateEditController(ProductoVenta instance) {
        super(instance, ProductoVentaDAO.getInstance());
    }

    public ProductoVentaCreateEditController(Window parent) {
        super(parent, ProductoVentaDAO.getInstance());
    }

    public ProductoVentaCreateEditController(ProductoVenta instance, Window parent) {
        super(instance, parent, ProductoVentaDAO.getInstance());
    }

    @Override
    public ProductoVenta createNewInstance() {
        ProductoVenta p = new ProductoVenta(ProductoVentaDAO.getInstance().generateStringCode("Pl-"));
        p.setProductoInsumoList(new ArrayList<>());
        p.setProductovOrdenList(new ArrayList<>());
        return p;
    }

    /**
     *
     * @param parent the value of parent
     */
    @Override
    public void constructView(java.awt.Container parent) {
        setView(new ProductoVentaCreateEditView(instance, this, (JDialog) parent, true));
        getView().updateView();
        getView().setVisible(true);

    }

    public List<Cocina> getCocinaList() {
        return  CocinaDAO.getInstance().findAll();
    }

    public List<Seccion> getSeccionList() {
        return new SeccionDAO().findAll();
    }

    public List<Insumo> getInsumoList() {
        ArrayList<Insumo>  ret = new ArrayList<>(InsumoDAO.getInstance().findAll());
        Collections.sort(ret, (Insumo o1, Insumo o2) -> o1.getNombre().compareTo(o2.getNombre()));
        return ret;
    }

    public void print(Graphics g) {
        getView().print(g);
    }

}
