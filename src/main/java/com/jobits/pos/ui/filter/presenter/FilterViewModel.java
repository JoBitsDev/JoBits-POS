/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.filter.presenter;

import com.jobits.pos.core.ui.filter.AbstractFilterTypePresenter;
import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.core.ui.filter.FilterType;
import com.jobits.pos.ui.viewmodel.AbstractViewModel;

/**
 *
 * @author Home
 */
public class FilterViewModel extends AbstractViewModel {

    private ArrayListModel<FilterType> filtros_disponibles = new ArrayListModel<>();

    public static final String PROP_FILTROS_DISPONIBLES = "filtros_disponibles";

    private boolean show_panel = false;

    public static final String PROP_SHOW_PANEL = "show_panel";

    private ArrayListModel<AbstractFilterTypePresenter> lista_presenters = new ArrayListModel<>();

    public static final String PROP_LISTA_PRESENTERS = "lista_presenters";

    /**
     * Get the value of lista_presenters
     *
     * @return the value of lista_presenters
     */
    public ArrayListModel<AbstractFilterTypePresenter> getLista_presenters() {
        return lista_presenters;
    }

    /**
     * Set the value of lista_presenters
     *
     * @param lista_presenters new value of lista_presenters
     */
    public void setLista_presenters(ArrayListModel<AbstractFilterTypePresenter> lista_presenters) {
        ArrayListModel<AbstractFilterTypePresenter> oldLista_presenters = this.lista_presenters;
        this.lista_presenters = lista_presenters;
        firePropertyChange(PROP_LISTA_PRESENTERS, oldLista_presenters, lista_presenters);
    }

    /**
     * Get the value of show_panel
     *
     * @return the value of show_panel
     */
    public boolean isShow_panel() {
        return show_panel;
    }

    /**
     * Set the value of show_panel
     *
     * @param show_panel new value of show_panel
     */
    public void setShow_panel(boolean show_panel) {
        boolean oldShow_panel = this.show_panel;
        this.show_panel = show_panel;
        firePropertyChange(PROP_SHOW_PANEL, oldShow_panel, show_panel);
    }

    /**
     * Get the value of filtros_disponibles
     *
     * @return the value of filtros_disponibles
     */
    public ArrayListModel<FilterType> getFiltros_disponibles() {
        return filtros_disponibles;
    }

    /**
     * Set the value of filtros_disponibles
     *
     * @param filtros_disponibles new value of filtros_disponibles
     */
    public void setFiltros_disponibles(ArrayListModel<FilterType> filtros_disponibles) {
        ArrayListModel<FilterType> oldFiltros_disponibles = this.filtros_disponibles;
        this.filtros_disponibles = filtros_disponibles;
        firePropertyChange(PROP_FILTROS_DISPONIBLES, oldFiltros_disponibles, filtros_disponibles);
    }

}
