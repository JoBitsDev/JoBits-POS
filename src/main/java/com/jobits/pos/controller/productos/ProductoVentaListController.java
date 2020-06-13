/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.productos;

import java.util.ArrayList;
import java.util.List;
import com.jobits.pos.controller.AbstractDetailController;
import com.jobits.pos.controller.OldAbstractListController;
import com.jobits.pos.controller.login.LogInController;
import com.jobits.pos.domain.models.Carta;
import com.jobits.pos.domain.models.ProductoVenta;
import com.jobits.pos.domain.models.Seccion;
import com.jobits.pos.adapters.repo.impl.CartaDAO;
import com.jobits.pos.adapters.repo.impl.ProductoVentaDAO;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.NotificationService;
import com.jobits.pos.notification.TipoNotificacion;
import com.jobits.pos.recursos.R;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class ProductoVentaListController extends OldAbstractListController<ProductoVenta> {

    Carta selectedCarta = null;

    public ProductoVentaListController() {
        super(ProductoVentaDAO.getInstance());
    }

    @Override
    public AbstractDetailController<ProductoVenta> getDetailControllerForNew() {
        validate(R.NivelAcceso.ECONOMICO);
        throw new IllegalStateException();
        //return new ProductoVentaCreateEditController(getView());
    }

    @Override
    public AbstractDetailController<ProductoVenta> getDetailControllerForEdit(ProductoVenta selected) {
        validate(R.NivelAcceso.ECONOMICO);
        throw new IllegalStateException();
        //return new ProductoVentaCreateEditController(selected, getView());
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

    }

    public Carta[] getCartaList() {
        List<Carta> cartas = CartaDAO.getInstance().findAll();
        Carta[] ret = new Carta[cartas.size()];
        for (int i = 0; i < cartas.size(); i++) {
            ret[i] = cartas.get(i);
        }
        return ret;
    }

    private void validate(R.NivelAcceso nivel) {
        if (!new LogInController().constructoAuthorizationView(getView(), nivel)) {
            Application.getInstance().getNotificationService().notify("Acceso denegado", TipoNotificacion.ERROR);
            throw new IllegalAccessError("Access denied");
        }
    }

    public boolean canSetVisible(ProductoVenta get) {
        if (get.getCocinacodCocina() == null || get.getSeccionnombreSeccion() == null) {
            Application.getInstance().getNotificationService().notify("El producto de venta no puede ponerse visible "
                    + "\n si no se encuentra dentro de una seccion y un punto de elaboracion", TipoNotificacion.ERROR);
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
//TODO fire change
    }

}
