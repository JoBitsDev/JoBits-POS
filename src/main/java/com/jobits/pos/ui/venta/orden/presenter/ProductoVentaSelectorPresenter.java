/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.orden.presenter;

import com.jobits.pos.adapters.repo.impl.SeccionDAO;
import com.jobits.pos.controller.productos.ProductoVentaListService;
import com.jobits.pos.controller.venta.OrdenService;
import com.jobits.pos.domain.models.Mesa;
import com.jobits.pos.domain.models.ProductoVenta;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
        addBeanPropertyChangeListener(ProductoVentaSelectorViewModel.PROP_PRODUCTOVENTASELECCIONADO, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getNewValue() != null && codOrdenEnlazada != null) {
                    service.addProduct(codOrdenEnlazada, (ProductoVenta) evt.getNewValue());
                    getBean().setProductoVentaSeleccionado(null);
                    firePropertyChange(PROP_PRODUCTO_SELECCIONADO, null, null);
                }
            }
        });
    }

    public Mesa getMesaSeleccionada() {
        return mesaSeleccionada;
    }

    public void setMesaSeleccionada(Mesa mesaSeleccionada) {
        this.mesaSeleccionada = mesaSeleccionada;
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
