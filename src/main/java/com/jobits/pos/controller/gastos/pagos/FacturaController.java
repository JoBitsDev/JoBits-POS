/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.gastos.pagos;

import java.awt.Container;
import java.util.Date;
import java.util.List;
import com.jobits.pos.controller.AbstractDialogController;
import com.jobits.pos.controller.login.LogInController;
import com.jobits.pos.exceptions.DevelopingOperationException;
import com.jobits.pos.exceptions.ValidatingException;
import com.jobits.pos.domain.models.ContabilidadCuenta;
import com.jobits.pos.domain.models.Factura;
import com.jobits.pos.domain.models.Pago;
import com.jobits.pos.adapters.repo.impl.AbstractRepository;
import com.jobits.pos.adapters.repo.impl.ContabilidadCuentaDAO;
import com.jobits.pos.adapters.repo.impl.FacturaDAO;
import com.jobits.pos.recursos.R;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class FacturaController extends AbstractDialogController<Factura> {

    public FacturaController() {
        super(FacturaDAO.getInstance());
    }

    @Override
    protected void create() {
        String error = null;
        if (selected.getIdCuentaAcreedora() == null || selected.getIdCuentaDeudora() == null) {
            error = "Las cuentas contables relacionadas con la factura no pueden estar vacias";
        } else if (selected.getIdCuentaAcreedora().equals(selected.getIdCuentaDeudora()) 
                || selected.getIdCuentaAcreedora().getTipoCuenta().equals(selected.getIdCuentaDeudora().getTipoCuenta())) {
            error = "Las cuenta cargada y abonada no pueden ser las mismas ni del mismo tipo";
        }

        if (selected.getNombre().isEmpty()) {
            error = "La factura debe tener un nombre";
        }
        if (selected.getNoSerieFactura().isEmpty()) {
            selected.setNoSerieFactura(selected.getIdCuentaDeudora().getNumeroCuenta());
        }

        if (error != null) {
            throw new ValidatingException(getView(), error);
        }
        selected.setFecha(new Date());
        super.create(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroy(Factura selected) {
        if (new LogInController().constructoAuthorizationView(getView(), R.NivelAcceso.ADMINISTRADOR)) {
            super.destroy(selected);
        }
    }

    @Override
    public void constructView(Container parent) {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    public List<ContabilidadCuenta> getCuentaList() {
        return ContabilidadCuentaDAO.getInstance().findAll();
    }

    public void createObligacionPago(Factura f, Pago p) {
        PagoController pagos = new PagoController();
        if (p.getMontoPagado() > f.getMontoAPagar() - f.getMontoPagado()) {
            throw new ValidatingException(getView(), "La obligacion de pago es mayor que el monto por pagar de la factura");
        }
        pagos.create(p);
        f.setMontoPagado(f.getMontoPagado() + p.getMontoPagado());
        update(f, false);
        getModel().refresh(f);
    }

    public void createObligacionCobro(Factura f, Pago p) {
                PagoController pagos = new PagoController();
        if (p.getMontoPagado() > f.getMontoAPagar() - f.getMontoPagado()) {
            throw new ValidatingException(getView(), "La obligacion de cobro es mayor que el monto por cobrar de la factura");
        }
        pagos.create(p);
        f.setMontoPagado(f.getMontoPagado() + p.getMontoPagado());
        update(f, false);
        getModel().refresh(f);
    }
}
