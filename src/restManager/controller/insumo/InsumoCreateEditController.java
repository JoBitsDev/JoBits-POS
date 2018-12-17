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
import java.util.Comparator;
import java.util.List;
import restManager.controller.AbstractDetailController;
import restManager.persistencia.Almacen;
import restManager.persistencia.Insumo;
import restManager.persistencia.ProductoVenta;
import restManager.persistencia.jpa.ProductoVentaJpaController;
import restManager.persistencia.models.AlmacenDAO;
import restManager.persistencia.models.InsumoDAO;
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
        ret.setCantidadExistente(Float.valueOf("0"));
        ret.setCostoPorUnidad(Float.valueOf("0"));
        ret.setStockEstimation(Float.valueOf("0"));
        ret.setUm(R.UM.U.toString());
        return ret;
    }

    @Override
    public void constructView(Window parent) {
        setView(new InsumoCreateEditView(this, (Dialog) parent, true, getInstance()));
        getView().updateView();
        getView().setVisible(true);
        setView(null);
    }

    public List<Almacen> getAlmacenList() {
        return new AlmacenDAO().findAll();
    }

    public List<ProductoVenta> getProductoList() {
        List <ProductoVenta> ret = ProductoVentaDAO.getInstance().findAll();
        Collections.sort(ret, (ProductoVenta o1, ProductoVenta o2) -> o1.getNombre().compareTo(o2.getNombre()));
        return ret;
    }
}
