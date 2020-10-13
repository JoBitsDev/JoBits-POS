/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.orden.presenter;

import com.jobits.pos.adapters.repo.impl.SeccionDAO;
import com.jobits.pos.controller.productos.ProductoVentaListService;
import com.jobits.pos.domain.models.Mesa;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import java.util.Optional;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class ProductoVentaSelectorPresenter extends AbstractViewPresenter<ProductoVentaSelectorViewModel> {

    private ProductoVentaListService service;
    private Mesa mesaSeleccionada;

    public ProductoVentaSelectorPresenter() {//TODO: pifia la logica esta metida en esta clase
        super(new ProductoVentaSelectorViewModel());
        // this.service = productoVentaService;
    }

    public Mesa getMesaSeleccionada() {
        return mesaSeleccionada;
    }

    public void setMesaSeleccionada(Mesa mesaSeleccionada) {
        this.mesaSeleccionada = mesaSeleccionada;
        refreshState();
    }

    @Override
    protected void registerOperations() {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Optional refreshState() {
        getBean().setLista_elementos(SeccionDAO.getInstance().findVisibleSecciones(mesaSeleccionada));
        return Optional.empty();
    }

}
