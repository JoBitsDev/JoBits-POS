/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.insumo;

import com.jobits.pos.ui.insumo.InsumoCreateEditView;
import java.awt.Dialog;
import java.awt.Window;
import java.util.Collections;
import java.util.List;
import com.jobits.pos.controller.AbstractDetailController;
import com.jobits.pos.controller.productos.ProductoVentaDetailController;
import com.jobits.pos.exceptions.DevelopingOperationException;
import com.jobits.pos.domain.models.Almacen;
import com.jobits.pos.domain.models.Insumo;
import com.jobits.pos.domain.models.ProductoInsumo;
import com.jobits.pos.domain.models.ProductoVenta;
import com.jobits.pos.adapters.repo.AlmacenDAO;
import com.jobits.pos.adapters.repo.InsumoDAO;
import com.jobits.pos.adapters.repo.ProductoInsumoDAO;
import com.jobits.pos.adapters.repo.ProductoVentaDAO;
import com.jobits.pos.recursos.R;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class InsumoCreateEditController extends AbstractDetailController<Insumo> {

    private final String PREFIX_FOR_ID = "In-";

    public InsumoCreateEditController() {
        super(InsumoDAO.getInstance());
        instance = createNewInstance();
    }

    public InsumoCreateEditController(Insumo instance) {
        super(instance, InsumoDAO.getInstance());
    }

    public InsumoCreateEditController(Window parent) {
        super(parent, InsumoDAO.getInstance());
    }

    public InsumoCreateEditController(Insumo instance, Window parent) {
        super(instance, parent, InsumoDAO.getInstance());
    }

    @Override
    public Insumo createNewInstance() {
        Insumo ret = new Insumo(super.getModel().generateStringCode(PREFIX_FOR_ID));
        ret.setNombre("");
        ret.setElaborado(false);
        ret.setCostoPorUnidad(Float.valueOf("0"));
        ret.setStockEstimation(Float.valueOf("0"));
        ret.setUm(R.UM.U.toString());
        return ret;
    }

    @Override
    public void createUpdateInstance() {
        setDismissOnAction(false);
        super.createUpdateInstance(); //To change body of generated methods, choose Tools | Templates.
        if (!instance.getProductoInsumoList().isEmpty()) {
            if (showConfirmDialog(getView(), "Desea actualizar el costo en los productos de venta")) {
                updateInsumoOnFichas(getInstance());
            }
        }
        getView().dispose();
    }

    /**
     *
     * @param parent the value of parent
     */
    @Override
    public void constructView(java.awt.Container parent) {
        setView(new InsumoCreateEditView(this, (Dialog) parent, true, getInstance()));
        getView().updateView();
        getView().setVisible(true);
    }

    public List<Almacen> getAlmacenList() {
        return AlmacenDAO.getInstance().findAll();
    }

    public List<ProductoVenta> getProductoList() {
        List<ProductoVenta> ret = ProductoVentaDAO.getInstance().findAll();
        Collections.sort(ret, (ProductoVenta o1, ProductoVenta o2) -> o1.getNombre().compareTo(o2.getNombre()));
        return ret;
    }

    public void updateInsumoOnFichas(Insumo insumo) {
        getModel().startTransaction();
        ProductoVentaDetailController controller = new ProductoVentaDetailController();
        for (ProductoInsumo p : insumo.getProductoInsumoList()) {
            p.setCosto(insumo.getCostoPorUnidad() * p.getCantidad());
            p.getProductoVenta().setGasto(controller.getCosto(p.getProductoVenta()));
            getModel().getEntityManager().merge(p);
            getModel().getEntityManager().merge(p.getProductoVenta());
        }
        getModel().commitTransaction();
    }
}
