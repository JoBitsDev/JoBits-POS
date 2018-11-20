/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.productoventa;

import GUI.Views.productoventa.ProductoVentaListView;
import GUI.Views.trabajadores.PersonalListView;
import java.awt.Frame;
import java.awt.Window;
import restManager.controller.AbstractDetailController;
import restManager.controller.AbstractListController;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.ProductoVenta;
import restManager.persistencia.models.AbstractModel;
import restManager.persistencia.models.ProductoVentaDAO;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class ProductoVentaListController extends AbstractListController<ProductoVenta> {

    public ProductoVentaListController() {
        super(new ProductoVentaDAO());
    }

    public ProductoVentaListController(Frame parent) {
        this();
        constructView(parent);
    }

    @Override
    public AbstractDetailController<ProductoVenta> getDetailControllerForNew() {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AbstractDetailController<ProductoVenta> getDetailControllerForEdit(ProductoVenta selected) {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void constructView(Window parent) {
        setView(new ProductoVentaListView(this, (Frame) parent, true));
        getView().updateView();
        getView().setVisible(true);

    }
    
   

}
