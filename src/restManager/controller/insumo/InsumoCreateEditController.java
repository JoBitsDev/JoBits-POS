/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.insumo;

import GUI.Views.Insumo.InsumoCreateEditView;
import java.awt.Dialog;
import java.awt.Window;
import java.util.Collections;
import java.util.List;
import restManager.controller.AbstractDetailController;
import restManager.controller.productoventa.ProductoVentaCreateEditController;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.Almacen;
import restManager.persistencia.Insumo;
import restManager.persistencia.ProductoInsumo;
import restManager.persistencia.ProductoVenta;
import restManager.persistencia.models.AlmacenDAO;
import restManager.persistencia.models.InsumoDAO;
import restManager.persistencia.models.ProductoInsumoDAO;
import restManager.persistencia.models.ProductoVentaDAO;
import restManager.resources.R;

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
        getModel().getEntityManager().refresh(instance);
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
        ProductoVentaCreateEditController controller = new ProductoVentaCreateEditController();
        for (ProductoInsumo p : insumo.getProductoInsumoList()) {
            p.setCosto(insumo.getCostoPorUnidad() * p.getCantidad());
            p.getProductoVenta().setGasto(controller.getCosto(p.getProductoVenta()));
            getModel().getEntityManager().merge(p);
            getModel().getEntityManager().merge(p.getProductoVenta());
        }
        getModel().commitTransaction();
    }
}
