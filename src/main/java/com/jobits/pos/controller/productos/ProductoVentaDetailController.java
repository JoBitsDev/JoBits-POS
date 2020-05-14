/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.productos;

import com.jobits.pos.ui.productos.ProductoVentaDetailView;
import com.jobits.pos.ui.utils.LongProcessAction;
import java.awt.Graphics;
import java.awt.Window;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JDialog;
import com.jobits.pos.controller.AbstractDetailController;
import com.jobits.pos.controller.insumo.InsumoCreateEditController;

import com.jobits.pos.domain.models.Cocina;
import com.jobits.pos.domain.models.Insumo;
import com.jobits.pos.domain.models.ProductoInsumo;
import com.jobits.pos.domain.models.ProductoVenta;
import com.jobits.pos.domain.models.Seccion;
import com.jobits.pos.adapters.repo.CocinaDAO;
import com.jobits.pos.adapters.repo.InsumoDAO;
import com.jobits.pos.adapters.repo.ProductoVentaDAO;
import com.jobits.pos.adapters.repo.SeccionDAO;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class ProductoVentaDetailController extends AbstractDetailController<ProductoVenta> {

    public ProductoVentaDetailController() {
        super(ProductoVentaDAO.getInstance());
        instance = createNewInstance();

    }

    public ProductoVentaDetailController(ProductoVenta instance) {
        super(instance, ProductoVentaDAO.getInstance());
    }

    public ProductoVentaDetailController(Window parent) {
        super(parent, ProductoVentaDAO.getInstance());
    }

    public ProductoVentaDetailController(ProductoVenta instance, Window parent) {
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
        //setView(new ProductoVentaDetailView(instance, this, (JDialog) parent, true));
        //getView().updateView();
        //getView().setVisible(true);

    }

    public List<Cocina> getCocinaList() {
        return CocinaDAO.getInstance().findAll();
    }

    public List<Seccion> getSeccionList() {
        List<Seccion> ret = SeccionDAO.getInstance().findAll();
        Collections.sort(ret, (o1, o2) -> {
            return o1.getNombreSeccion().compareTo(o2.getNombreSeccion());
        });
        return ret;
    }

    public List<Insumo> getInsumoList() {
        ArrayList<Insumo> ret = new ArrayList<>(InsumoDAO.getInstance().findAll());
        Collections.sort(ret, (Insumo o1, Insumo o2) -> o1.getNombre().compareTo(o2.getNombre()));
        return ret;
    }

    public void print(Graphics g) {
        getView().print(g);
    }

    public float getCosto(ProductoVenta v) {
        float ret = 0;
        for (ProductoInsumo productoInsumo : v.getProductoInsumoList()) {
            ret += productoInsumo.getCosto();
        }

        return ret;

    }

    public void updateCosto() {
        getInstance().setGasto(getCosto(getInstance()));
    }

    public void agregarIngrediente() {

        InsumoCreateEditController controller = new InsumoCreateEditController();
        controller.setParent(getView());
        new LongProcessAction() {
            @Override
            protected void longProcessMethod() {
                controller.setInstance(controller.createNewInstance());
            }

            @Override
            protected void whenDone() {
                controller.constructView(getView());
             //   ((ProductoVentaDetailView) getView()).getCrossReferencePanel().addItemToComboBox(controller.getInstance());
            }

        }.performAction(getView());
        // getView().setCreatingM

    }

}
