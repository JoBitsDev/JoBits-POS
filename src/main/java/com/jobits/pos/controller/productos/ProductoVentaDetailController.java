/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.productos;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.ui.utils.LongProcessActionServiceImpl;
import java.awt.Graphics;
import java.awt.Window;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.jobits.pos.controller.AbstractDetailController;
import com.jobits.pos.controller.insumo.InsumoDetailController;

import com.jobits.pos.domain.models.Cocina;
import com.jobits.pos.domain.models.Insumo;
import com.jobits.pos.domain.models.ProductoInsumo;
import com.jobits.pos.domain.models.ProductoVenta;
import com.jobits.pos.domain.models.Seccion;
import com.jobits.pos.adapters.repo.impl.CocinaDAO;
import com.jobits.pos.adapters.repo.impl.InsumoDAO;
import com.jobits.pos.adapters.repo.impl.ProductoVentaDAO;
import com.jobits.pos.adapters.repo.impl.SeccionDAO;
import com.jobits.pos.domain.models.ProductoInsumoPK;
import com.jobits.pos.utils.utils;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class ProductoVentaDetailController extends AbstractDetailController<ProductoVenta> implements ProductoVentaDetailService {

    private boolean creatingMode = true;

    public ProductoVentaDetailController() {
        super(ProductoVentaDAO.getInstance());
        instance = createNewInstance();

    }

    public ProductoVentaDetailController(ProductoVenta instance) {
        super(instance, ProductoVentaDAO.getInstance());
        creatingMode = false;
    }

    public boolean isCreatingMode() {
        return creatingMode;
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
    }

    @Override
    public List<Cocina> getCocinaList() {
        return CocinaDAO.getInstance().findAll();
    }

    @Override
    public List<Seccion> getSeccionList() {
        List<Seccion> ret = SeccionDAO.getInstance().findAll();
        Collections.sort(ret, (o1, o2) -> {
            return o1.getNombreSeccion().compareTo(o2.getNombreSeccion());
        });
        return ret;
    }

    @Override
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

    @Override
    public void registrarNuevoInsumo() {
        InsumoDetailController controller = new InsumoDetailController();
        controller.setParent(getView());
        new LongProcessActionServiceImpl() {
            @Override
            protected void longProcessMethod() {
                controller.setInstance(controller.createNewInstance());
            }

            @Override
            protected void whenDone() {
                controller.constructView(getView());
            }
        }.performAction(getView());
    }

    @Override
    public void agregarInsumoaProducto(Insumo insumo_disponible_sel, float cantidad) {
        if (insumo_disponible_sel == null) {
            throw new IllegalArgumentException("Selecione un insumo");
        }
        ProductoInsumo ret = new ProductoInsumo();
        ProductoInsumoPK pk = new ProductoInsumoPK(getInstance().getCodigoProducto(), insumo_disponible_sel.getCodInsumo());
        ret.setProductoInsumoPK(pk);
        ret.setInsumo(insumo_disponible_sel);
        ret.setProductoVenta(getInstance());
        ret.setCantidad(cantidad);
        ret.setCosto(utils.setDosLugaresDecimalesFloat(cantidad * insumo_disponible_sel.getCostoPorUnidad()));
        getInstance().getProductoInsumoList().add(ret);
    }

    @Override
    public void discardChanges() {
        instance = getModel().find(getInstance().getCodigoProducto());
    }

    @Override
    public void eliminarInsumoProducto(ProductoInsumo insumo_contenido_seleccionado) {
        if (insumo_contenido_seleccionado == null) {
            throw new IllegalArgumentException("Selecione un insumo");
        }
        getInstance().getProductoInsumoList().remove(insumo_contenido_seleccionado);//TODO donde se actualiza el valor total del insumo
    }

    @Override
    public void fillProductoVentaData(String nombre, String precioCosto, String pagoPorVenta, String precioVenta, Cocina cocina, Seccion categegoria, ArrayListModel<ProductoInsumo> lista_insumos_contenidos, String rutaImagenProducto) {
        if (nombre == null) {
            throw new IllegalArgumentException("Introduzca el nombre del producto");
        }
        if (precioCosto == null || precioCosto.equals("")) {
            instance.setGasto(0f);
        } else {
            instance.setGasto(Float.valueOf(precioCosto));
        }
        if (pagoPorVenta == null) {
            instance.setPagoPorVenta((float) 0);
        } else {
            instance.setPagoPorVenta(Float.parseFloat(pagoPorVenta));
        }
        instance.setNombre(nombre);
        instance.setPrecioVenta(Float.parseFloat(precioVenta));
        instance.setCocinacodCocina(cocina);
        instance.setSeccionnombreSeccion(categegoria);
        instance.setProductoInsumoList(lista_insumos_contenidos);
        instance.setDescripcion(rutaImagenProducto);
        instance.setVisible(true);
        if (isCreatingMode()) {
            create(instance);
        } else {
            update(instance);
        }
    }

}
