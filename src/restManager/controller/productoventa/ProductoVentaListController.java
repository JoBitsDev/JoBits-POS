/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.productoventa;

import GUI.Views.productoventa.ProductoVentaListView;
import GUI.Views.productoventa.ProductoVentaReadOnlyView;
import java.awt.Dialog;
import java.awt.Frame;
import restManager.controller.AbstractDetailController;
import restManager.controller.AbstractListController;
import restManager.exceptions.DevelopingOperationException;
import restManager.exceptions.UnauthorizedAccessException;
import restManager.persistencia.ProductoVenta;
import restManager.persistencia.models.ProductoVentaDAO;
import restManager.printservice.ComponentPrinter;
import restManager.resources.R;

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

    public ProductoVentaListController(Dialog parent) {
        this();
        constructView(parent);
    }

    @Override
    public AbstractDetailController<ProductoVenta> getDetailControllerForNew() {
        validate();
        return new ProductoVentaCreateEditController(getView());
    }

    @Override
    public AbstractDetailController<ProductoVenta> getDetailControllerForEdit(ProductoVenta selected) {
        validate();
        return new ProductoVentaCreateEditController(selected, getView());
    }

    @Override
    public void destroy(ProductoVenta selected) {
        validate();
        super.destroy(selected);
    }

    /**
     *
     * @param parent the value of parent
     */
    @Override
    public void constructView(java.awt.Container parent) {
        setView(new ProductoVentaListView(this, (Dialog) parent, true));
        getView().updateView();
        getView().setVisible(true);

    }

    public void printProductoVenta(ProductoVenta objectAtSelectedRow) {
        validate();
        ProductoVentaReadOnlyView printView = new ProductoVentaReadOnlyView(objectAtSelectedRow, this, getView(), true);
        printView.updateView();
        printView.pack();
        ComponentPrinter.printComponent(printView.getjXPanelRoot(), objectAtSelectedRow.toString(), false);
    }

    public void printAllProductoVenta() {
        items.forEach((x) -> {
            printProductoVenta(x);
        });
    }

    private void validate() {
        if (R.loggedUser.getPuestoTrabajonombrePuesto().getNivelAcceso() < 3) {
            throw new UnauthorizedAccessException(getView());
        }
    }

    public boolean canSetVisible(ProductoVenta get) {
        if(get.getCocinacodCocina() == null || get.getSeccionnombreSeccion() == null){
            showErrorDialog(getView(), "El Producto de venta no puede ponerse visible "
                    + "\n si no se encuentra dentro de una seccion y una cocina");
            return false;
        }
        return true;
    }

}
