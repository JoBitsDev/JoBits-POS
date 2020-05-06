/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.gastos.pagos;

import java.awt.Container;
import java.util.Date;
import com.jobits.pos.controller.AbstractDialogController;
import com.jobits.pos.exceptions.DevelopingOperationException;
import com.jobits.pos.exceptions.ValidatingException;
import com.jobits.pos.domain.models.Pago;
import com.jobits.pos.adapters.repo.AbstractModel;
import com.jobits.pos.adapters.repo.PagoDAO;

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
        if (selected.getIdCuentaARebajar() == null) {
            err = "Debe seleccionar una cuenta para rebajar el importe";

        } else {
            if (selected.getIdCuentaARebajar().getTipoCuenta().equals("CREDITO")) {
                err = "La cuenta seleccionada solo puede ser de debito";
            }
        }
        if (err != null) {
            throw new ValidatingException(getView(), err);
        }
        boolean previousValue = this.showDialogs;
        setShowDialogs(false);
        selected.setFecha(new Date());
        super.create(selected); //To change body of generated methods, choose Tools | Templates.
        setShowDialogs(previousValue);
    }

    @Override
    public void constructView(Container parent) {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

}
