/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.gastos.pagos;

import com.jobits.pos.ui.gastos.pagos.Gastos_Pagos_Dashboard;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.jobits.pos.controller.AbstractDialogController;
import com.jobits.pos.controller.login.LogInController;
import com.jobits.pos.exceptions.DevelopingOperationException;
import com.jobits.pos.exceptions.ValidatingException;
import com.jobits.pos.domain.models.ContabilidadCuenta;
import com.jobits.pos.domain.models.Factura;
import com.jobits.pos.domain.models.Pago;
import com.jobits.pos.adapters.repo.ContabilidadCuentaDAO;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.utils.utils;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class CuentaController extends AbstractDialogController<ContabilidadCuenta> {

    Container parent;
    private float sumaDebitosActuales = 0, sumaCreditosActuales = 0;

    public CuentaController() {
        super(ContabilidadCuentaDAO.getInstance());
        setDismissOnAction(false);
    }

    public CuentaController(Container parent) {
        this();
        this.parent = parent;
        constructView(parent);
    }

    @Override
    protected void create() {
        if (selected.getNombre().isEmpty()) {
            throw new ValidatingException(getView(), "El nombre no puede estar vacio");
        }
        ContabilidadCuenta aux = selected.getIdCuentaPadre();
        while (aux != null) {
            if (aux.getNumeroCuenta().equals(selected.getNumeroCuenta())) {
                throw new ValidatingException(getView(), "Ya existe una cuenta con ese identificador");
            }
            aux = aux.getIdCuentaPadre();
        }
        super.create(); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public void destroy(ContabilidadCuenta selected) {
        if (new LogInController().constructoAuthorizationView(getView(), R.NivelAcceso.ADMINISTRADOR)) {
            super.destroy();
        }
    }

    @Override
    public void constructView(Container parent) {
        setView(new Gastos_Pagos_Dashboard(this));
        getView().updateView();
        getView().setVisible(true);
    }

    public Container getParent() {
        return parent;
    }

    public void setParent(Container parent) {
        this.parent = parent;
    }

    public List<BalanceComprobacionItem> getBalanceComprobacion(Calendar del, Calendar al, ContabilidadCuenta c, int nivel, boolean exclusivo) {
        ArrayList<BalanceComprobacionItem> ret = new ArrayList<>();
        List<ContabilidadCuenta> cuentas = new ArrayList<>(getItems());
        Collections.sort(cuentas, (ContabilidadCuenta o1, ContabilidadCuenta o2) -> {
            return o1.toString().compareTo(o2.toString());
        });

        // obteniendo root items
        for (int i = 0; i < cuentas.size();) {
            if (cuentas.get(i).getIdCuentaPadre() != null) {
                cuentas.remove(i);
            } else {
                if (c != null) {
                    if (!cuentas.get(i).getIdCuenta().equals(c.getIdCuenta())) {
                        cuentas.remove(i);
                    } else {
                        i++;
                    }
                } else {
                    i++;
                }
            }
        }

        sumaCreditosActuales = 0;
        sumaDebitosActuales = 0;
        for (ContabilidadCuenta x : cuentas) {
            getModel().refresh(x);
            BalanceComprobacionItem item = calcularBalanceComprobacion(ret, nivel, exclusivo, x, del, al);
            sumaDebitosActuales += item.getBalanceDebito();
            sumaCreditosActuales += item.getBalanceCredito();
        }

        Collections.sort(ret);
        return ret;
    }

    public List<ContabilidadCuenta> getCuentasList() {
        return ContabilidadCuentaDAO.getInstance().findAll();
    }

    public float getSumaDebitosActuales() {
        return sumaDebitosActuales;
    }

    public float getSumaCreditosActuales() {
        return sumaCreditosActuales;
    }

    private BalanceComprobacionItem calcularBalanceComprobacion(List<BalanceComprobacionItem> lista, int nivel, boolean exclusivo, ContabilidadCuenta x, Calendar del, Calendar al) {
        ArrayList<BalanceComprobacionItem> aux = new ArrayList<>();
        BalanceComprobacionItem current = calcularBalanceComprobacion(x, del, al);
        if (x.getCuentasHijo() != null) {
            for (ContabilidadCuenta y : x.getCuentasHijo()) {
                BalanceComprobacionItem temporal;
                if (nivel - 1 < 0) {
                    temporal = calcularBalanceComprobacion(aux, nivel - 1, exclusivo, y, del, al);
                } else {
                    temporal = calcularBalanceComprobacion(lista, nivel - 1, exclusivo, y, del, al);
                }
                current.addBalanceCredito(temporal.getBalanceCredito());
                current.addBalanceDebito(temporal.getBalanceDebito());

            }
        }
        if (exclusivo) {
            if (nivel == 0) {
                lista.add(current);
            }
        } else {
            lista.add(current);
        }

        return current;
    }

    private BalanceComprobacionItem calcularBalanceComprobacion(ContabilidadCuenta x, Calendar del, Calendar al) {
        BalanceComprobacionItem ret;

        float sumaDebitos = 0,
                sumaCreditos = 0;

        for (Factura f : x.getFacturasDeudoras()) {
            if (utils.estaEnRangoSinTiempo(f.getFecha(), del.getTime(), al.getTime())) {
                sumaDebitos += f.getMontoAPagar();
                for (Pago p : f.getPagoList()) {
                    if (p.getEsCobro()) {
                        sumaCreditos += p.getMontoPagado();
                    }
                }
            }
        }
        for (Factura f : x.getFacturasAcreedoras()) {
            if (utils.estaEnRangoSinTiempo(f.getFecha(), del.getTime(), al.getTime())) {
                sumaCreditos += f.getMontoAPagar();
                for (Pago p : f.getPagoList()) {
                    if (!p.getEsCobro()) {
                        sumaDebitos += p.getMontoPagado();
                    }
                }
            }
        }
        for (Pago p : x.getPagoList()) {
            if (utils.estaEnRangoSinTiempo(p.getFecha(), del.getTime(), al.getTime())) {
                if (p.getEsCobro()) {
                    sumaDebitos += p.getMontoPagado();
                } else {
                    sumaCreditos += p.getMontoPagado();
                }
            }
        }

        switch (x.getTipoCuenta()) {
            case "CREDITO":
                ret = new BalanceComprobacionItem(x, 0, sumaCreditos - sumaDebitos);
                break;
            case "DEBITO":
                ret = new BalanceComprobacionItem(x, sumaDebitos - sumaCreditos, 0);
                break;
            default:
                ret = new BalanceComprobacionItem(x, 0, 0);

        }

        return ret;
    }

    public class BalanceComprobacionItem implements Comparable<BalanceComprobacionItem> {

        private final ContabilidadCuenta cuenta;
        private float balanceDebito, balanceCredito;

        public BalanceComprobacionItem(ContabilidadCuenta cuenta, float balanceDebito, float balanceCredito) {
            this.cuenta = cuenta;
            this.balanceDebito = balanceDebito;
            this.balanceCredito = balanceCredito;
        }

        public ContabilidadCuenta getCuenta() {
            return cuenta;
        }

        public float getBalanceDebito() {
            return balanceDebito;
        }

        public float getBalanceCredito() {
            return balanceCredito;
        }

        private boolean enCero() {
            return balanceCredito == 0 && balanceDebito == 0;
        }

        public void setBalanceDebito(float balanceDebito) {
            this.balanceDebito = balanceDebito;
        }

        public void setBalanceCredito(float balanceCredito) {
            this.balanceCredito = balanceCredito;
        }

        public void addBalanceCredito(float balanceCredito) {
            this.balanceCredito += balanceCredito;
        }

        public void addBalanceDebito(float balanceDebito) {
            this.balanceDebito += balanceDebito;
        }

        @Override
        public int compareTo(BalanceComprobacionItem o) {
            return cuenta.toString().compareTo(o.getCuenta().toString());
        }

    }
}
