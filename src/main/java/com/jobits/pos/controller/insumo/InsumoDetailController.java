/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.insumo;

import com.jobits.pos.ui.insumo.OLDInsumoCreateEditView;
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
import com.jobits.pos.adapters.repo.impl.AlmacenDAO;
import com.jobits.pos.adapters.repo.impl.InsumoDAO;
import com.jobits.pos.adapters.repo.impl.InsumoElaboradoDAO;
import com.jobits.pos.adapters.repo.impl.ProductoInsumoDAO;
import com.jobits.pos.adapters.repo.impl.ProductoVentaDAO;
import com.jobits.pos.domain.models.InsumoElaborado;
import com.jobits.pos.domain.models.InsumoElaboradoPK;
import com.jobits.pos.domain.models.ProductoInsumoPK;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.utils.utils;
import java.awt.Frame;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class InsumoDetailController extends AbstractDetailController<Insumo> implements InsumoDetailService {

    private final String PREFIX_FOR_ID = "In-";
    private boolean creatingMode = true;

    public InsumoDetailController() {
        super(InsumoDAO.getInstance());
        instance = createNewInstance();
    }

    public InsumoDetailController(Insumo instance) {
        super(instance, InsumoDAO.getInstance());
    }
//
//    public InsumoDetailController(Window parent) {
//        super(parent, InsumoDAO.getInstance());
//    }
//
//    public InsumoDetailController(Insumo instance, Window parent) {
//        super(instance, parent, InsumoDAO.getInstance());
//    }

    @Override
    public Insumo createNewInstance() {
        Insumo ret = new Insumo(super.getModel().generateStringCode(PREFIX_FOR_ID));
        ret.setNombre("");
        ret.setElaborado(false);
        ret.setCostoPorUnidad(Float.valueOf("0"));
        ret.setStockEstimation(Float.valueOf("0"));
        ret.setUm(R.UM.U.toString());
        ret.setInsumoDerivadoList(new ArrayList<>());
        ret.setProductoInsumoList(new ArrayList<>());
        return ret;
    }

    @Override
    public void createUpdateInstance() {
        setDismissOnAction(false);
        switch (state) {
            case CREATING:
                create(instance);
                break;
            case EDITING:
                update(instance);
                break;
        }
        //super.createUpdateInstance(); //To change body of generated methods, choose Tools | Templates.
        if (!instance.getProductoInsumoList().isEmpty()) {
            if (showConfirmDialog(getView(), "Desea actualizar el costo en los productos de venta")) {
                updateProductoOnInsumo(getInstance());
            }
        }
        if (!instance.getInsumoDerivadoList().isEmpty()) {
            updateInsumoOnInsumo(getInstance());
        }
    }

    /**
     *
     * @param parent the value of parent
     */
    @Override
    public void constructView(java.awt.Container parent) {
//        if (parent instanceof JDialog) {
//            setView(new OLDInsumoCreateEditView(this, (JDialog) parent, true, getInstance()));
//
//        } else {
//            setView(new OLDInsumoCreateEditView(this, (JFrame) parent, true, getInstance()));
//        }
//        getView().updateView();
//        getView().setVisible(true);
    }

    @Override
    public List<Almacen> getAlmacenList() {
        return AlmacenDAO.getInstance().findAll();
    }

    @Override
    public List<ProductoVenta> getProductoList() {
        List<ProductoVenta> ret = ProductoVentaDAO.getInstance().findAll();
        Collections.sort(ret, (ProductoVenta o1, ProductoVenta o2) -> o1.getNombre().compareTo(o2.getNombre()));
        return ret;
    }

    public void updateProductoOnInsumo(Insumo insumo) {
        getModel().startTransaction();
        ProductoVentaDetailController controller = new ProductoVentaDetailController();
        for (ProductoInsumo p : insumo.getProductoInsumoList()) {
            p.setCosto(insumo.getCostoPorUnidad() * p.getCantidad());
            p.getProductoVenta().setGasto(controller.getCosto(p.getProductoVenta()));
            getModel().getEntityManager().merge(p);
            getModel().getEntityManager().merge(p.getProductoVenta());
        }
        getModel().commitTransaction();
    }//TODO: Problemas de persistencia de datos

    private void updateInsumoOnInsumo(Insumo instance) {
        getModel().startTransaction();
//        ProductoVentaDetailController controller = new ProductoVentaDetailController();
        for (InsumoElaborado p : instance.getInsumoDerivadoList()) {
//            p.setCosto(instance.getCostoPorUnidad() * p.getCantidad());
//            p.getProductoVenta().setGasto(controller.getCosto(p.getProductoVenta()));
            getModel().getEntityManager().persist(p);
            getModel().getEntityManager().persist(p.getInsumo());
        }
        getModel().commitTransaction();
    }//TODO: Problemas de persistencia de datos

    @Override
    public void agregarInsumoElaboradoaInsumo(Insumo insumo_disponible_seleccionado, float cantidad) {
        InsumoElaborado ret = new InsumoElaborado();
        InsumoElaboradoPK pk = new InsumoElaboradoPK(instance.getCodInsumo(), insumo_disponible_seleccionado.getCodInsumo());
        ret.setInsumoElaboradoPK(pk);
        ret.setInsumo(instance);
        ret.setInsumo_derivado_nombre(insumo_disponible_seleccionado);
        ret.setCantidad(cantidad);
        ret.setCosto(utils.setDosLugaresDecimalesFloat(cantidad * insumo_disponible_seleccionado.getCostoPorUnidad()));
        getInstance().getInsumoDerivadoList().add(ret);
    }

    @Override
    public void eliminarInsumoElaboradoDeInsumo(InsumoElaborado insumo_contenido_seleccionado) {
        getInstance().getInsumoDerivadoList().remove(insumo_contenido_seleccionado);//TODO donde se actualiza el valor total del insumo
    }

    @Override
    public void agregarProductoVentaAInsumo(ProductoVenta producto_disponible_seleccionado, float cantidad) {
        ProductoInsumo ret = new ProductoInsumo();
        ProductoInsumoPK pk = new ProductoInsumoPK(producto_disponible_seleccionado.getCodigoProducto(), getInstance().getCodInsumo());
        ret.setProductoInsumoPK(pk);
        ret.setProductoVenta(producto_disponible_seleccionado);
        ret.setInsumo(getInstance());
        ret.setCantidad(cantidad);
        ret.setCosto(utils.setDosLugaresDecimalesFloat(cantidad * getInstance().getCostoPorUnidad()));
        getInstance().getProductoInsumoList().add(ret);
    }

    @Override
    public void eliminarProductoVentaDeInsumo(ProductoInsumo producto_contenido_seleccionado) {
        getInstance().getProductoInsumoList().remove(producto_contenido_seleccionado);
    }

}
