/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.insumo.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.core.domain.models.Insumo;
import com.jobits.pos.core.domain.models.InsumoElaborado;
import com.jobits.pos.core.domain.models.ProductoInsumo;
import com.jobits.pos.core.domain.models.ProductoVenta;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.viewmodel.AbstractViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author Home
 */
public class InsumoDetailViewModel extends AbstractViewModel {

    private String nombre_insumo;

    public static final String PROP_NOMBRE_INSUMO = "nombre_insumo";

    private ArrayListModel<R.UM> list_unidades_medida = new ArrayListModel<>();

    public static final String PROP_LIST_UNIDADES_MEDIDA = "list_unidades_medida";

    private R.UM unidad_medida_selected;

    public static final String PROP_UNIDAD_MEDIDA_SELECTED = "unidad_medida_selected";

    private float costo_unitario;

    public static final String PROP_COSTO_UNITARIO = "costo_unitario";

    private float estimacion_de_stock;

    public static final String PROP_ESTIMACION_DE_STOCK = "estimacion_de_stock";

    private float cantidad_creada;

    public static final String PROP_CANTIDAD_CREADA = "cantidad_creada";

    private String valor_del_costo_text = "0.00";

    public static final String PROP_VALOR_DEL_COSTO_TEXT = "valor_del_costo_text";

    private boolean tabbed_pane_enabled = false;

    public static final String PROP_TABBED_PANE_ENABLED = "tabbed_pane_enabled";

    private String crear_editar_button_text;

    public static final String PROP_CREAR_EDITAR_BUTTON_TEXT = "crear_editar_button_text";

    private ArrayListModel<Insumo> lista_insumos_disponibles = new ArrayListModel<>();

    public static final String PROP_LISTA_INSUMOS_DISPONIBLES = "lista_insumos_disponibles";

    private Insumo insumo_disponible_selecionado;

    public static final String PROP_INSUMO_DISPONIBLE_SELECIONADO = "insumo_disponible_selecionado";

    private ArrayListModel<InsumoElaborado> lista_insumos_contenidos = new ArrayListModel<>();

    public static final String PROP_LISTA_INSUMOS_CONTENIDOS = "lista_insumos_contenidos";

    private InsumoElaborado insumo_contenido_seleccionado;

    public static final String PROP_INSUMO_CONTENIDO_SELECCIONADO = "insumo_contenido_seleccionado";

    private ArrayListModel<ProductoVenta> lista_productos_disponibles = new ArrayListModel<>();

    public static final String PROP_LISTA_PRODUCTOS_DISPONIBLES = "lista_productos_disponibles";

    private ProductoVenta producto_disponible_seleccionado;

    public static final String PROP_PRODUCTO_DISPONIBLE_SELECCIONADO = "producto_disponible_seleccionado";

    private ArrayListModel<ProductoInsumo> lista_productos_contenidos = new ArrayListModel<>();

    public static final String PROP_LISTA_PRODUCTOS_CONTENIDOS = "lista_productos_contenidos";

    private ProductoInsumo producto_contenido_seleccionado;

    public static final String PROP_PRODUCTO_CONTENIDO_SELECCIONADO = "producto_contenido_seleccionado";

    /**
     * Get the value of producto_contenido_seleccionado
     *
     * @return the value of producto_contenido_seleccionado
     */
    public ProductoInsumo getProducto_contenido_seleccionado() {
        return producto_contenido_seleccionado;
    }

    /**
     * Set the value of producto_contenido_seleccionado
     *
     * @param producto_contenido_seleccionado new value of
     * producto_contenido_seleccionado
     */
    public void setProducto_contenido_seleccionado(ProductoInsumo producto_contenido_seleccionado) {
        ProductoInsumo oldProducto_contenido_seleccionado = this.producto_contenido_seleccionado;
        this.producto_contenido_seleccionado = producto_contenido_seleccionado;
        firePropertyChange(PROP_PRODUCTO_CONTENIDO_SELECCIONADO, oldProducto_contenido_seleccionado, producto_contenido_seleccionado);
    }

    /**
     * Get the value of lista_productos_contenidos
     *
     * @return the value of lista_productos_contenidos
     */
    public ArrayListModel<ProductoInsumo> getLista_productos_contenidos() {
        return lista_productos_contenidos;
    }

    /**
     * Set the value of lista_productos_contenidos
     *
     * @param lista_productos_contenidos new value of lista_productos_contenidos
     */
    public void setLista_productos_contenidos(ArrayListModel<ProductoInsumo> lista_productos_contenidos) {
        ArrayListModel<ProductoInsumo> oldLista_productos_contenidos = this.lista_productos_contenidos;
        this.lista_productos_contenidos = lista_productos_contenidos;
        firePropertyChange(PROP_LISTA_PRODUCTOS_CONTENIDOS, oldLista_productos_contenidos, lista_productos_contenidos);
    }

    /**
     * Get the value of producto_disponible_seleccionado
     *
     * @return the value of producto_disponible_seleccionado
     */
    public ProductoVenta getProducto_disponible_seleccionado() {
        return producto_disponible_seleccionado;
    }

    /**
     * Set the value of producto_disponible_seleccionado
     *
     * @param producto_disponible_seleccionado new value of
     * producto_disponible_seleccionado
     */
    public void setProducto_disponible_seleccionado(ProductoVenta producto_disponible_seleccionado) {
        ProductoVenta oldProducto_disponible_seleccionado = this.producto_disponible_seleccionado;
        this.producto_disponible_seleccionado = producto_disponible_seleccionado;
        firePropertyChange(PROP_PRODUCTO_DISPONIBLE_SELECCIONADO, oldProducto_disponible_seleccionado, producto_disponible_seleccionado);
    }

    /**
     * Get the value of lista_productos_disponibles
     *
     * @return the value of lista_productos_disponibles
     */
    public ArrayListModel<ProductoVenta> getLista_productos_disponibles() {
        return lista_productos_disponibles;
    }

    /**
     * Set the value of lista_productos_disponibles
     *
     * @param lista_productos_disponibles new value of
     * lista_productos_disponibles
     */
    public void setLista_productos_disponibles(ArrayListModel<ProductoVenta> lista_productos_disponibles) {
        ArrayListModel<ProductoVenta> oldLista_productos_disponibles = this.lista_productos_disponibles;
        this.lista_productos_disponibles = lista_productos_disponibles;
        firePropertyChange(PROP_LISTA_PRODUCTOS_DISPONIBLES, oldLista_productos_disponibles, lista_productos_disponibles);
    }

    /**
     * Get the value of insumo_contenido_seleccionado
     *
     * @return the value of insumo_contenido_seleccionado
     */
    public InsumoElaborado getInsumo_contenido_seleccionado() {
        return insumo_contenido_seleccionado;
    }

    /**
     * Set the value of insumo_contenido_seleccionado
     *
     * @param insumo_contenido_seleccionado new value of
     * insumo_contenido_seleccionado
     */
    public void setInsumo_contenido_seleccionado(InsumoElaborado insumo_contenido_seleccionado) {
        InsumoElaborado oldInsumo_contenido_seleccionado = this.insumo_contenido_seleccionado;
        this.insumo_contenido_seleccionado = insumo_contenido_seleccionado;
        firePropertyChange(PROP_INSUMO_CONTENIDO_SELECCIONADO, oldInsumo_contenido_seleccionado, insumo_contenido_seleccionado);
    }

    /**
     * Get the value of lista_insumos_contenidos
     *
     * @return the value of lista_insumos_contenidos
     */
    public ArrayListModel<InsumoElaborado> getLista_insumos_contenidos() {
        return lista_insumos_contenidos;
    }

    /**
     * Set the value of lista_insumos_contenidos
     *
     * @param lista_insumos_contenidos new value of lista_insumos_contenidos
     */
    public void setLista_insumos_contenidos(ArrayListModel<InsumoElaborado> lista_insumos_contenidos) {
        ArrayListModel<InsumoElaborado> oldLista_insumos_contenidos = this.lista_insumos_contenidos;
        this.lista_insumos_contenidos = lista_insumos_contenidos;
        firePropertyChange(PROP_LISTA_INSUMOS_CONTENIDOS, oldLista_insumos_contenidos, lista_insumos_contenidos);
    }

    /**
     * Get the value of insumo_disponible_selecionado
     *
     * @return the value of insumo_disponible_selecionado
     */
    public Insumo getInsumo_disponible_selecionado() {
        return insumo_disponible_selecionado;
    }

    /**
     * Set the value of insumo_disponible_selecionado
     *
     * @param insumo_disponible_selecionado new value of
     * insumo_disponible_selecionado
     */
    public void setInsumo_disponible_selecionado(Insumo insumo_disponible_selecionado) {
        Insumo oldInsumo_disponible_selecionado = this.insumo_disponible_selecionado;
        this.insumo_disponible_selecionado = insumo_disponible_selecionado;
        firePropertyChange(PROP_INSUMO_DISPONIBLE_SELECIONADO, oldInsumo_disponible_selecionado, insumo_disponible_selecionado);
    }

    /**
     * Get the value of lista_insumos_disponibles
     *
     * @return the value of lista_insumos_disponibles
     */
    public ArrayListModel<Insumo> getLista_insumos_disponibles() {
        return lista_insumos_disponibles;
    }

    /**
     * Set the value of lista_insumos_disponibles
     *
     * @param lista_insumos_disponibles new value of lista_insumos_disponibles
     */
    public void setLista_insumos_disponibles(ArrayListModel<Insumo> lista_insumos_disponibles) {
        ArrayListModel<Insumo> oldLista_insumos_disponibles = this.lista_insumos_disponibles;
        this.lista_insumos_disponibles = lista_insumos_disponibles;
        firePropertyChange(PROP_LISTA_INSUMOS_DISPONIBLES, oldLista_insumos_disponibles, lista_insumos_disponibles);
    }

    /**
     * Get the value of crear_editar_button_text
     *
     * @return the value of crear_editar_button_text
     */
    public String getCrear_editar_button_text() {
        return crear_editar_button_text;
    }

    /**
     * Set the value of crear_editar_button_text
     *
     * @param crear_editar_button_text new value of crear_editar_button_text
     */
    public void setCrear_editar_button_text(String crear_editar_button_text) {
        String oldCrear_editar_button_text = this.crear_editar_button_text;
        this.crear_editar_button_text = crear_editar_button_text;
        firePropertyChange(PROP_CREAR_EDITAR_BUTTON_TEXT, oldCrear_editar_button_text, crear_editar_button_text);
    }

    /**
     * Get the value of tabbed_pane_enabled
     *
     * @return the value of tabbed_pane_enabled
     */
    public boolean isTabbed_pane_enabled() {
        return tabbed_pane_enabled;
    }

    /**
     * Set the value of tabbed_pane_enabled
     *
     * @param tabbed_pane_enabled new value of tabbed_pane_enabled
     */
    public void setTabbed_pane_enabled(boolean tabbed_pane_enabled) {
        boolean oldTabbed_pane_enabled = this.tabbed_pane_enabled;
        this.tabbed_pane_enabled = tabbed_pane_enabled;
        firePropertyChange(PROP_TABBED_PANE_ENABLED, oldTabbed_pane_enabled, tabbed_pane_enabled);
    }

    /**
     * Get the value of valor_del_costo_text
     *
     * @return the value of valor_del_costo_text
     */
    public String getValor_del_costo_text() {
        return valor_del_costo_text;
    }

    /**
     * Set the value of valor_del_costo_text
     *
     * @param valor_del_costo_text new value of valor_del_costo_text
     */
    public void setValor_del_costo_text(String valor_del_costo_text) {
        String oldValor_del_costo_text = this.valor_del_costo_text;
        this.valor_del_costo_text = valor_del_costo_text;
        firePropertyChange(PROP_VALOR_DEL_COSTO_TEXT, oldValor_del_costo_text, valor_del_costo_text);
    }

    /**
     * Get the value of cantidad_creada
     *
     * @return the value of cantidad_creada
     */
    public float getCantidad_creada() {
        return cantidad_creada;
    }

    /**
     * Set the value of cantidad_creada
     *
     * @param cantidad_creada new value of cantidad_creada
     */
    public void setCantidad_creada(float cantidad_creada) {
        float oldCantidad_creada = this.cantidad_creada;
        this.cantidad_creada = cantidad_creada;
        firePropertyChange(PROP_CANTIDAD_CREADA, oldCantidad_creada, cantidad_creada);
    }

    /**
     * Get the value of estimacion_de_stock
     *
     * @return the value of estimacion_de_stock
     */
    public float getEstimacion_de_stock() {
        return estimacion_de_stock;
    }

    /**
     * Set the value of estimacion_de_stock
     *
     * @param estimacion_de_stock new value of estimacion_de_stock
     */
    public void setEstimacion_de_stock(float estimacion_de_stock) {
        float oldEstimacion_de_stock = this.estimacion_de_stock;
        this.estimacion_de_stock = estimacion_de_stock;
        firePropertyChange(PROP_ESTIMACION_DE_STOCK, oldEstimacion_de_stock, estimacion_de_stock);
    }

    /**
     * Get the value of costo_unitario
     *
     * @return the value of costo_unitario
     */
    public float getCosto_unitario() {
        return costo_unitario;
    }

    /**
     * Set the value of costo_unitario
     *
     * @param costo_unitario new value of costo_unitario
     */
    public void setCosto_unitario(float costo_unitario) {
        float oldCosto_unitario = this.costo_unitario;
        this.costo_unitario = costo_unitario;
        firePropertyChange(PROP_COSTO_UNITARIO, oldCosto_unitario, costo_unitario);
    }

    /**
     * Get the value of unidad_medida_selected
     *
     * @return the value of unidad_medida_selected
     */
    public R.UM getUnidad_medida_selected() {
        return unidad_medida_selected;
    }

    /**
     * Set the value of unidad_medida_selected
     *
     * @param unidad_medida_selected new value of unidad_medida_selected
     */
    public void setUnidad_medida_selected(R.UM unidad_medida_selected) {
        R.UM oldUnidad_medida_selected = this.unidad_medida_selected;
        this.unidad_medida_selected = unidad_medida_selected;
        firePropertyChange(PROP_UNIDAD_MEDIDA_SELECTED, oldUnidad_medida_selected, unidad_medida_selected);
    }

    /**
     * Get the value of list_unidades_medida
     *
     * @return the value of list_unidades_medida
     */
    public ArrayListModel<R.UM> getList_unidades_medida() {
        return list_unidades_medida;
    }

    /**
     * Set the value of list_unidades_medida
     *
     * @param list_unidades_medida new value of list_unidades_medida
     */
    public void setList_unidades_medida(ArrayListModel<R.UM> list_unidades_medida) {
        ArrayListModel<R.UM> oldList_unidades_medida = this.list_unidades_medida;
        this.list_unidades_medida = list_unidades_medida;
        firePropertyChange(PROP_LIST_UNIDADES_MEDIDA, oldList_unidades_medida, list_unidades_medida);
    }

    /**
     * Get the value of nombre_insumo
     *
     * @return the value of nombre_insumo
     */
    public String getNombre_insumo() {
        return nombre_insumo;
    }

    /**
     * Set the value of nombre_insumo
     *
     * @param nombre_insumo new value of nombre_insumo
     */
    public void setNombre_insumo(String nombre_insumo) {
        String oldNombre_insumo = this.nombre_insumo;
        this.nombre_insumo = nombre_insumo;
        firePropertyChange(PROP_NOMBRE_INSUMO, oldNombre_insumo, nombre_insumo);
    }

}
