/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.gastos_pagos;

import GUI.Views.gastos_pagos.Gastos_Pagos_Dashboard;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import restManager.controller.AbstractDialogController;
import restManager.controller.login.LogInController;
import restManager.exceptions.DevelopingOperationException;
import restManager.exceptions.ValidatingException;
import restManager.persistencia.ContabilidadCuenta;
import restManager.persistencia.Factura;
import restManager.persistencia.models.ContabilidadCuentaDAO;
import restManager.resources.R;
import restManager.util.utils;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class CuentaController extends AbstractDialogController<ContabilidadCuenta> {

    Container parent;

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

    public List<BalanceComprobacionItem> getBalanceComprobacion(Calendar del, Calendar al) {
        ArrayList<BalanceComprobacionItem> ret = new ArrayList<>();
        List<ContabilidadCuenta> cuentas = getItems();
        Collections.sort(cuentas, (ContabilidadCuenta o1, ContabilidadCuenta o2) -> {
            return o1.toString().compareTo(o2.toString());
        });
        
        // obteniendo root items
        for (int i = 0; i < cuentas.size();) {
            if (cuentas.get(i).getIdCuentaPadre() != null) {
                cuentas.remove(i);
            } else {
                i++;
            }
        }

        for (ContabilidadCuenta x : cuentas) {
            calcularBalanceComprobacion(ret, x, del, al);
        }

        Collections.sort(ret);
        return ret;
    }

    public List<ContabilidadCuenta> getCuentasList() {
        return ContabilidadCuentaDAO.getInstance().findAll();
    }

    private List<BalanceComprobacionItem> calcularBalanceComprobacion(List<BalanceComprobacionItem> lista, ContabilidadCuenta x, Calendar del, Calendar al) {
        int initSize = lista.size();
        if (x.getCuentasHijo() != null) {
        for (ContabilidadCuenta y : x.getCuentasHijo()) {
            calcularBalanceComprobacion(lista, y, del, al);
        }
        }
        BalanceComprobacionItem current = calcularBalanceComprobacion(x, del, al);
        for (int i = initSize; i < lista.size(); i++) {
            current.setBalanceCredito(lista.get(i).getBalanceCredito() + current.getBalanceCredito());
            current.setBalanceDebito(lista.get(i).getBalanceDebito() + current.getBalanceDebito());
        }
        lista.add(current);
        return lista;
    }

    private BalanceComprobacionItem calcularBalanceComprobacion(ContabilidadCuenta x, Calendar del, Calendar al) {
        BalanceComprobacionItem ret;

        float sumaDebitos = 0,
                sumaCreditos = 0;

        for (Factura f : x.getFacturasDeudoras()) {
            if (utils.estaEnRangoSinTiempo(f.getFecha(), del.getTime(), al.getTime())) {
                sumaDebitos += f.getMontoAPagar();
            }
        }
        for (Factura f : x.getFacturasAcreedoras()) {
            if (utils.estaEnRangoSinTiempo(f.getFecha(), del.getTime(), al.getTime())) {
                sumaCreditos += f.getMontoAPagar();
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

        @Override
        public int compareTo(BalanceComprobacionItem o) {
            return cuenta.toString().compareTo(o.getCuenta().toString());
        }

    }
}
