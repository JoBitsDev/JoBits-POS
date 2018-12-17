/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.productoventa;

import GUI.Views.productoventa.ProductoVentaCreateEditView;
import GUI.Views.productoventa.ProductoVentaListView;
import GUI.Views.productoventa.ProductoVentaReadOnlyView;
import GUI.Views.trabajadores.PersonalListView;
import java.awt.Frame;
import java.awt.Window;
import restManager.controller.AbstractDetailController;
import restManager.controller.AbstractListController;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.ProductoVenta;
import restManager.persistencia.models.AbstractModel;
import restManager.persistencia.models.ProductoVentaDAO;
import restManager.printservice.ComponentPrinter;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class ProductoVentaListController extends AbstractListController<ProductoVenta> {

    public ProductoVentaListController() {
        super(ProductoVentaDAO.getInstance());
    }

    public ProductoVentaListController(Frame parent) {
        this();
        constructView(parent);
    }

    @Override
    public AbstractDetailController<ProductoVenta> getDetailControllerForNew() {
        return new ProductoVentaCreateEditController(getView());
    }

    @Override
    public AbstractDetailController<ProductoVenta> getDetailControllerForEdit(ProductoVenta selected) {
        return new ProductoVentaCreateEditController(selected, getView());
    }

    @Override
    public void constructView(Window parent) {
        setView(new ProductoVentaListView(this, (Frame) parent, true));
        getView().updateView();
        getView().setVisible(true);

    }

    public void printProductoVenta(ProductoVenta objectAtSelectedRow) {
        ProductoVentaReadOnlyView printView = new ProductoVentaReadOnlyView(objectAtSelectedRow, this, getView(), true);
        printView.updateView();
        printView.pack();
        ComponentPrinter.printComponent(printView.getjXPanelRoot(),objectAtSelectedRow.toString(), false);
    }
    
    public void printAllProductoVenta(){
        items.forEach((x) -> {
            printProductoVenta(x);
        });
    }
    
   

}
