/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.domain.models.Area;
import com.jobits.pos.domain.models.Mesa;
import com.jobits.pos.ui.viewmodel.AbstractListViewModel;
import com.jobits.pos.ui.viewmodel.AbstractViewModel;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class MesaListViewModel extends AbstractListViewModel<Mesa> {

    private ArrayListModel<Area> lista_areas;

    public static final String PROP_LISTA_AREAS = "lista_areas";

    private Area area_seleccionada;

    public static final String PROP_AREA_SELECCIONADA = "area_seleccionada";

    /**
     * Get the value of area_seleccionada
     *
     * @return the value of area_seleccionada
     */
    public Area getArea_seleccionada() {
        return area_seleccionada;
    }

    /**
     * Set the value of area_seleccionada
     *
     * @param area_seleccionada new value of area_seleccionada
     */
    public void setArea_seleccionada(Area area_seleccionada) {
        Area oldArea_seleccionada = this.area_seleccionada;
        this.area_seleccionada = area_seleccionada;
        firePropertyChange(PROP_AREA_SELECCIONADA, oldArea_seleccionada, area_seleccionada);
    }

    /**
     * Get the value of lista_areas
     *
     * @return the value of lista_areas
     */
    public ArrayListModel<Area> getLista_areas() {
        return lista_areas;
    }

    /**
     * Set the value of lista_areas
     *
     * @param lista_areas new value of lista_areas
     */
    public void setLista_areas(ArrayListModel<Area> lista_areas) {
        ArrayListModel<Area> oldLista_areas = this.lista_areas;
        this.lista_areas = lista_areas;
        firePropertyChange(PROP_LISTA_AREAS, oldLista_areas, lista_areas);
    }

}
