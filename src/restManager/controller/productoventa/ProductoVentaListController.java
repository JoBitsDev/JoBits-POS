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
import java.util.ArrayList;
import java.util.List;
import restManager.controller.AbstractDetailController;
import restManager.controller.AbstractListController;
import restManager.controller.login.LogInController;
import restManager.exceptions.DevelopingOperationException;
import restManager.exceptions.UnauthorizedAccessException;
import restManager.persistencia.Carta;
import restManager.persistencia.ProductoVenta;
import restManager.persistencia.Seccion;
import restManager.persistencia.models.CartaDAO;
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

    Carta selectedCarta = null;

    public ProductoVentaListController() {
        super(ProductoVentaDAO.getInstance());
    }

    public ProductoVentaListController(Dialog parent) {
        this();
        constructView(parent);
    }

    @Override
    public AbstractDetailController<ProductoVenta> getDetailControllerForNew() {
        validate(R.NivelAcceso.ECONOMICO);
        return new ProductoVentaCreateEditController(getView());
    }

    @Override
    public AbstractDetailController<ProductoVenta> getDetailControllerForEdit(ProductoVenta selected) {
        validate(R.NivelAcceso.ECONOMICO);
        return new ProductoVentaCreateEditController(selected, getView());
    }

    @Override
    public void destroy(ProductoVenta selected) {
        validate(R.NivelAcceso.ADMINISTRADOR);
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
        validate(R.NivelAcceso.CAJERO);
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

    public Carta[] getCartaList() {
        List<Carta> cartas = CartaDAO.getInstance().findAll();
        Carta [] ret = new Carta[cartas.size()];
        for (int i = 0; i < cartas.size(); i++) {
            ret[i] = cartas.get(i);
        }
        return ret;
    }

    private void validate(R.NivelAcceso nivel) {
        if (!new LogInController().constructoAuthorizationView(getView(), nivel)) {
            throw new UnauthorizedAccessException(getView());
        }
    }

    public boolean canSetVisible(ProductoVenta get) {
        if (get.getCocinacodCocina() == null || get.getSeccionnombreSeccion() == null) {
            showErrorDialog(getView(), "El producto de venta no puede ponerse visible "
                    + "\n si no se encuentra dentro de una seccion y un punto de elaboracion");
            return false;
        }
        return true;
    }

    @Override
    public List<ProductoVenta> getItems() {
        if (selectedCarta != null) {
            ArrayList<ProductoVenta> ret = new ArrayList<>();
            for (Seccion seccion : selectedCarta.getSeccionList()) {
                ret.addAll(seccion.getProductoVentaList());
            }
            return ret;
        } else {
            return super.getItems();
        }
    }

    public void setSelectedCarta(Carta carta) {
        selectedCarta = carta;
        getView().updateView();
    }

}
