/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.gastos_pagos;

import java.awt.Container;
import java.util.Date;
import restManager.controller.AbstractDialogController;
import restManager.exceptions.DevelopingOperationException;
import restManager.exceptions.ValidatingException;
import restManager.persistencia.Pago;
import restManager.persistencia.models.AbstractModel;
import restManager.persistencia.models.PagoDAO;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class PagoController extends AbstractDialogController<Pago> {

    public PagoController() {
        super(PagoDAO.getInstance());
    }

    @Override
    public void create(Pago selected) {
        String err = null;
        if (selected.getFactura() == null) {
            err = "El pago debe pertenecer a una factura";
        }
        if (selected.getMontoPagado() <= 0) {
            err = "El monto a pagar debe ser mayor que 0";
        }
        if (selected.getNoCheque() == null && selected.getNoRecibo() == null && !selected.getACuenta()) {
            err = "Se debe especificar un metodo de pago";
        }
        if (err != null) {
            throw new ValidatingException(getView(), err);
        }
        selected.setFecha(new Date());
        super.create(selected); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void constructView(Container parent) {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

}
