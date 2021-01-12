/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.gastos.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.core.domain.models.GastoVenta;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.viewmodel.AbstractViewModel;
import java.util.Arrays;

/**
 *
 * @author Home
 */
public class GastosViewModel extends AbstractViewModel {

    private ArrayListModel<R.TipoGasto> lista_categoria_gastos = new ArrayListModel<>(Arrays.asList(R.TipoGasto.values()));

    public static final String PROP_LISTA_CATEGORIA_GASTOS = "lista_categoria_gastos";

    private R.TipoGasto categoria_gasto_seleccionada;

    public static final String PROP_CATEGORIA_GASTO_SELECCIONADA = "categoria_gasto_seleccionada";

    private float monto_gasto;

    public static final String PROP_MONTO_GASTO = "monto_gasto";

    private String descripcion_gasto;

    public static final String PROP_DESCRIPCION_GASTO = "descripcion_gasto";

    private ArrayListModel<GastoVenta> lista_gasto_venta = new ArrayListModel<>();

    public static final String PROP_LISTA_GASTO_VENTA = "lista_gasto_venta";

    private GastoVenta gasto_venta_seleccionado;

    public static final String PROP_GASTO_VENTA_SELECCIONADO = "gasto_venta_seleccionado";

    private String total_gastos;

    public static final String PROP_TOTAL_GASTOS = "total_gastos";

    private ArrayListModel<String> lista_tipo_gasto = new ArrayListModel<>();

    public static final String PROP_LISTA_TIPO_GASTO = "lista_tipo_gasto";

    private String tipo_gasto_seleccionado;

    public static final String PROP_TIPO_GASTO_SELECCIONADO = "tipo_gasto_seleccionado";

    /**
     * Get the value of tipo_gasto_seleccionado
     *
     * @return the value of tipo_gasto_seleccionado
     */
    public String getTipo_gasto_seleccionado() {
        return tipo_gasto_seleccionado;
    }

    /**
     * Set the value of tipo_gasto_seleccionado
     *
     * @param tipo_gasto_seleccionado new value of tipo_gasto_seleccionado
     */
    public void setTipo_gasto_seleccionado(String tipo_gasto_seleccionado) {
        String oldTipo_gasto_seleccionado = this.tipo_gasto_seleccionado;
        this.tipo_gasto_seleccionado = tipo_gasto_seleccionado;
        firePropertyChange(PROP_TIPO_GASTO_SELECCIONADO, oldTipo_gasto_seleccionado, tipo_gasto_seleccionado);
    }

    /**
     * Get the value of lista_tipo_gasto
     *
     * @return the value of lista_tipo_gasto
     */
    public ArrayListModel<String> getLista_tipo_gasto() {
        return lista_tipo_gasto;
    }

    /**
     * Set the value of lista_tipo_gasto
     *
     * @param lista_tipo_gasto new value of lista_tipo_gasto
     */
    public void setLista_tipo_gasto(ArrayListModel<String> lista_tipo_gasto) {
        ArrayListModel<String> oldLista_tipo_gasto = this.lista_tipo_gasto;
        this.lista_tipo_gasto = lista_tipo_gasto;
        firePropertyChange(PROP_LISTA_TIPO_GASTO, oldLista_tipo_gasto, lista_tipo_gasto);
    }

    /**
     * Get the value of total_gastos
     *
     * @return the value of total_gastos
     */
    public String getTotal_gastos() {
        return total_gastos;
    }

    /**
     * Set the value of total_gastos
     *
     * @param total_gastos new value of total_gastos
     */
    public void setTotal_gastos(String total_gastos) {
        String oldTotal_gastos = this.total_gastos;
        this.total_gastos = total_gastos;
        firePropertyChange(PROP_TOTAL_GASTOS, oldTotal_gastos, total_gastos);
    }

    /**
     * Get the value of gasto_venta_seleccionado
     *
     * @return the value of gasto_venta_seleccionado
     */
    public GastoVenta getGasto_venta_seleccionado() {
        return gasto_venta_seleccionado;
    }

    /**
     * Set the value of gasto_venta_seleccionado
     *
     * @param gasto_venta_seleccionado new value of gasto_venta_seleccionado
     */
    public void setGasto_venta_seleccionado(GastoVenta gasto_venta_seleccionado) {
        GastoVenta oldGasto_venta_seleccionado = this.gasto_venta_seleccionado;
        this.gasto_venta_seleccionado = gasto_venta_seleccionado;
        firePropertyChange(PROP_GASTO_VENTA_SELECCIONADO, oldGasto_venta_seleccionado, gasto_venta_seleccionado);
    }

    /**
     * Get the value of lista_gasto_venta
     *
     * @return the value of lista_gasto_venta
     */
    public ArrayListModel<GastoVenta> getLista_gasto_venta() {
        return lista_gasto_venta;
    }

    /**
     * Set the value of lista_gasto_venta
     *
     * @param lista_gasto_venta new value of lista_gasto_venta
     */
    public void setLista_gasto_venta(ArrayListModel<GastoVenta> lista_gasto_venta) {
        ArrayListModel<GastoVenta> oldLista_gasto_venta = this.lista_gasto_venta;
        this.lista_gasto_venta = lista_gasto_venta;
        firePropertyChange(PROP_LISTA_GASTO_VENTA, oldLista_gasto_venta, lista_gasto_venta);
    }

    /**
     * Get the value of descripcion_gasto
     *
     * @return the value of descripcion_gasto
     */
    public String getDescripcion_gasto() {
        return descripcion_gasto;
    }

    /**
     * Set the value of descripcion_gasto
     *
     * @param descripcion_gasto new value of descripcion_gasto
     */
    public void setDescripcion_gasto(String descripcion_gasto) {
        String oldDescripcion_gasto = this.descripcion_gasto;
        this.descripcion_gasto = descripcion_gasto;
        firePropertyChange(PROP_DESCRIPCION_GASTO, oldDescripcion_gasto, descripcion_gasto);
    }

    /**
     * Get the value of monto_gasto
     *
     * @return the value of monto_gasto
     */
    public float getMonto_gasto() {
        return monto_gasto;
    }

    /**
     * Set the value of monto_gasto
     *
     * @param monto_gasto new value of monto_gasto
     */
    public void setMonto_gasto(float monto_gasto) {
        float oldMonto_gasto = this.monto_gasto;
        this.monto_gasto = monto_gasto;
        firePropertyChange(PROP_MONTO_GASTO, oldMonto_gasto, monto_gasto);
    }

    /**
     * Get the value of categoria_gasto_seleccionada
     *
     * @return the value of categoria_gasto_seleccionada
     */
    public R.TipoGasto getCategoria_gasto_seleccionada() {
        return categoria_gasto_seleccionada;
    }

    /**
     * Set the value of categoria_gasto_seleccionada
     *
     * @param categoria_gasto_seleccionada
     */
    public void setCategoria_gasto_seleccionada(R.TipoGasto categoria_gasto_seleccionada) {
        R.TipoGasto oldCategoria_gasto_seleccionada = this.categoria_gasto_seleccionada;
        this.categoria_gasto_seleccionada = categoria_gasto_seleccionada;
        firePropertyChange(PROP_CATEGORIA_GASTO_SELECCIONADA, oldCategoria_gasto_seleccionada, tipo_gasto_seleccionado);
    }

    /**
     * Get the value of lista_categoria_gastos
     *
     * @return the value of lista_categoria_gastos
     */
    public ArrayListModel<R.TipoGasto> getLista_categoria_gastos() {
        return lista_categoria_gastos;
    }

    /**
     * Set the value of lista_categoria_gastos
     *
     * @param lista_categoria_gastos new value of lista_categoria_gastos
     */
    public void setLista_categoria_gastos(ArrayListModel<R.TipoGasto> lista_categoria_gastos) {
        ArrayListModel<R.TipoGasto> oldLista_tipo_gastos = this.lista_categoria_gastos;
        this.lista_categoria_gastos = lista_categoria_gastos;
        firePropertyChange(PROP_LISTA_CATEGORIA_GASTOS, oldLista_tipo_gastos, lista_categoria_gastos);
    }

}
