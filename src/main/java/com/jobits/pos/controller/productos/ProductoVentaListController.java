/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.productos;

import com.jobits.pos.ui.productos.ProductoVentaListView;
import com.jobits.pos.ui.productos.ProductoVentaReadOnlyView;
import java.awt.Dialog;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import com.jobits.pos.controller.AbstractDetailController;
import com.jobits.pos.controller.AbstractListController;
import com.jobits.pos.controller.login.LogInController;
import com.jobits.pos.exceptions.DevelopingOperationException;
import com.jobits.pos.exceptions.UnauthorizedAccessException;
import com.jobits.pos.domain.models.Carta;
import com.jobits.pos.domain.models.ProductoVenta;
import com.jobits.pos.domain.models.Seccion;
import com.jobits.pos.adapters.repo.CartaDAO;
import com.jobits.pos.adapters.repo.ProductoVentaDAO;
import com.jobits.pos.servicios.impresion.ComponentPrinter;
import com.jobits.pos.recursos.R;

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
