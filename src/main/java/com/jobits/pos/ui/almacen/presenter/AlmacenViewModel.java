/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.almacen.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.core.domain.models.Almacen;
import com.jobits.pos.core.domain.models.Insumo;
import com.jobits.pos.core.domain.models.InsumoAlmacen;
import com.jobits.pos.ui.viewmodel.AbstractListViewModel;

/**
 *
 * @author Home
 */
public class AlmacenViewModel extends AbstractListViewModel<Almacen> {

    private ArrayListModel<Insumo> lista_insumos_disponibles = new ArrayListModel();

    public static final String PROP_LISTA_INSUMOS_DISPONIBLES = "lista_insumos_disponibles";

    private Insumo insumo_disponible_seleccionado;

    public static final String PROP_INSUMO_DISPONIBLE_SELECCIONADO = "insumo_disponible_seleccionado";

    private String valor_monetario_text;

    public static final String PROP_VALOR_MONETARIO_TEXT = "valor_monetario_text";

    private ArrayListModel<InsumoAlmacen> lista_insumos_contenidos = new ArrayListModel();

    public static final String PROP_LISTA_INSUMOS_CONTENIDOS = "lista_insumos_contenidos";

    private InsumoAlmacen insumo_contenido_seleccionado;

    public static final String PROP_INSUMO_CONTENIDO_SELECCIONADO = "insumo_contenido_seleccionado";

    private String search_keyWord;

    public static final String PROP_SEARCH_KEYWORD = "search_keyWord";

    private boolean panel_visible = true;

    public static final String PROP_PANEL_VISIBLE = "panel_visible";

    /**
     * Get the value of panel_visible
     *
     * @return the value of panel_visible
     */
    public boolean isPanel_visible() {
        return panel_visible;
    }

    /**
     * Set the value of panel_visible
     *
     * @param panel_visible new value of panel_visible
     */
    public void setPanel_visible(boolean panel_visible) {
        boolean oldPanel_visible = this.panel_visible;
        this.panel_visible = panel_visible;
        firePropertyChange(PROP_PANEL_VISIBLE, oldPanel_visible, panel_visible);
    }

    /**
     * Get the value of search_keyWord
     *
     * @return the value of search_keyWord
     */
    public String getSearch_keyWord() {
        return search_keyWord;
    }

    /**
     * Set the value of search_keyWord
     *
     * @param search_keyWord new value of search_keyWord
     */
    public void setSearch_keyWord(String search_keyWord) {
        String oldSearch_keyWord = this.search_keyWord;
        this.search_keyWord = search_keyWord;
        firePropertyChange(PROP_SEARCH_KEYWORD, oldSearch_keyWord, search_keyWord);
    }

    /**
     * Get the value of insumo_contenido_seleccionado
     *
     * @return the value of insumo_contenido_seleccionado
     */
    public InsumoAlmacen getInsumo_contenido_seleccionado() {
        return insumo_contenido_seleccionado;
    }

    /**
     * Set the value of insumo_contenido_seleccionado
     *
     * @param insumo_contenido_seleccionado new value of
     * insumo_contenido_seleccionado
     */
    public void setInsumo_contenido_seleccionado(InsumoAlmacen insumo_contenido_seleccionado) {
        InsumoAlmacen oldInsumo_contenido_seleccionado = this.insumo_contenido_seleccionado;
        this.insumo_contenido_seleccionado = insumo_contenido_seleccionado;
        firePropertyChange(PROP_INSUMO_CONTENIDO_SELECCIONADO, oldInsumo_contenido_seleccionado, insumo_contenido_seleccionado);
    }

    /**
     * Get the value of lista_insumos_contenidos
     *
     * @return the value of lista_insumos_contenidos
     */
    public ArrayListModel<InsumoAlmacen> getLista_insumos_contenidos() {
        return lista_insumos_contenidos;
    }

    /**
     * Set the value of lista_insumos_contenidos
     *
     * @param lista_insumos_contenidos new value of lista_insumos_contenidos
     */
    public void setLista_insumos_contenidos(ArrayListModel<InsumoAlmacen> lista_insumos_contenidos) {
        ArrayListModel<InsumoAlmacen> oldLista_insumos_contenidos = this.lista_insumos_contenidos;
        this.lista_insumos_contenidos = lista_insumos_contenidos;
        firePropertyChange(PROP_LISTA_INSUMOS_CONTENIDOS, oldLista_insumos_contenidos, lista_insumos_contenidos);
    }

    /**
     * Get the value of valor_monetario_text
     *
     * @return the value of valor_monetario_text
     */
    public String getValor_monetario_text() {
        return valor_monetario_text;
    }

    /**
     * Set the value of valor_monetario_text
     *
     * @param valor_monetario_text new value of valor_monetario_text
     */
    public void setValor_monetario_text(String valor_monetario_text) {
        String oldValor_monetario_text = this.valor_monetario_text;
        this.valor_monetario_text = valor_monetario_text;
        firePropertyChange(PROP_VALOR_MONETARIO_TEXT, oldValor_monetario_text, valor_monetario_text);
    }

    /**
     * Get the value of insumo_disponible_seleccionado
     *
     * @return the value of insumo_disponible_seleccionado
     */
    public Insumo getInsumo_disponible_seleccionado() {
        return insumo_disponible_seleccionado;
    }

    /**
     * Set the value of insumo_disponible_seleccionado
     *
     * @param insumo_disponible_seleccionado new value of
     * insumo_disponible_seleccionado
     */
    public void setInsumo_disponible_seleccionado(Insumo insumo_disponible_seleccionado) {
        Insumo oldInsumo_disponible_seleccionado = this.insumo_disponible_seleccionado;
        this.insumo_disponible_seleccionado = insumo_disponible_seleccionado;
        firePropertyChange(PROP_INSUMO_DISPONIBLE_SELECCIONADO, oldInsumo_disponible_seleccionado, insumo_disponible_seleccionado);
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
     * @param lista_insumos new value of lista_insumos_disponibles
     */
    public void setLista_insumos_disponibles(ArrayListModel<Insumo> lista_insumos) {
        ArrayListModel<Insumo> oldLista_insumos = this.lista_insumos_disponibles;
        this.lista_insumos_disponibles = lista_insumos;
        firePropertyChange(PROP_LISTA_INSUMOS_DISPONIBLES, oldLista_insumos, lista_insumos);
    }

}
