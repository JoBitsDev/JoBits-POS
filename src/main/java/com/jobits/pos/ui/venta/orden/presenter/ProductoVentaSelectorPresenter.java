/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.orden.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.adapters.repo.impl.SeccionDAO;
import com.jobits.pos.controller.productos.ProductoVentaListService;
import com.jobits.pos.controller.venta.OrdenService;
import com.jobits.pos.domain.models.Mesa;
import com.jobits.pos.domain.models.ProductoVenta;
import com.jobits.pos.domain.models.Seccion;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class ProductoVentaSelectorPresenter extends AbstractViewPresenter<ProductoVentaSelectorViewModel> {

    private OrdenService service;
    private Mesa mesaSeleccionada;
    private String codOrdenEnlazada;

    public static final String PROP_PRODUCTO_SELECCIONADO = "PROP_PRODUCTO_SELECCIONADO";

    ProductoVentaSelectorPresenter(OrdenService ordenService) {
        super(new ProductoVentaSelectorViewModel());
        this.service = ordenService;
        addBeanPropertyChangeListener(ProductoVentaSelectorViewModel.PROP_PRODUCTOVENTASELECCIONADO, (PropertyChangeEvent evt) -> {
            if (evt.getNewValue() != null && codOrdenEnlazada != null) {
                service.addProduct(codOrdenEnlazada, (ProductoVenta) evt.getNewValue());
                getBean().setProductoVentaSeleccionado(null);
                firePropertyChange(PROP_PRODUCTO_SELECCIONADO, null, null);
            }
        });
        addBeanPropertyChangeListener(ProductoVentaSelectorViewModel.PROP_PV_FILTRADO, (PropertyChangeEvent evt) -> {
            if (evt.getNewValue() != null) {
                List<ProductoVenta> pvList = new ArrayList<>();
                List<Seccion> secList = new ArrayList<>();
                List<Seccion> auxList = SeccionDAO.getInstance().findVisibleSecciones(mesaSeleccionada);

                auxList.forEach((x) -> {
                    boolean add = false;
                    for (ProductoVenta pv : x.getProductoVentaList()) {
                        if (pv.toString().toLowerCase().contains(getBean().getPv_filtrado().toLowerCase())) {
                            pvList.add(pv);
                            add = true;
                        }
                    }
                    if (add) {
                        secList.add(x);
                    }
                });
                getBean().setLista_elementos(secList);
                if (evt.getNewValue().equals("")) {
                    getBean().setListaProductos(new ArrayList<>());
                } else {
                    getBean().setListaProductos(pvList);
                }
            } else {
                getBean().setElemento_seleccionado(null);
                getBean().setListaProductos(new ArrayList<>());
            }
        });
    }

    public Mesa getMesaSeleccionada() {
        return mesaSeleccionada;
    }

    public void setMesaSeleccionada(Mesa mesaSeleccionada) {
        this.mesaSeleccionada = mesaSeleccionada;
        getBean().setCampo_busqueda_enabled(false);
        getBean().setCampo_busqueda_enabled(mesaSeleccionada != null);
//        getBean().getListaProductos().clear();
        refreshState();
    }

    public String getCodOrdenEnlazada() {
        return codOrdenEnlazada;
    }

    public void setCodOrdenEnlazada(String codOrdenEnlazada) {
        this.codOrdenEnlazada = codOrdenEnlazada;
    }

    @Override
    protected void registerOperations() {
    }

    @Override
    protected Optional refreshState() {
        getBean().setLista_elementos(SeccionDAO.getInstance().findVisibleSecciones(mesaSeleccionada));//TODO: pifia logica en los presenters
        return Optional.empty();
    }

}
