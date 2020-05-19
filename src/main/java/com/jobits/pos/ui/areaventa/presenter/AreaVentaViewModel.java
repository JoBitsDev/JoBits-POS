/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.areaventa.presenter;

import com.jobits.pos.ui.menu.presenter.*;
import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.domain.models.Area;
import com.jobits.pos.domain.models.Carta;
import com.jobits.pos.domain.models.Mesa;
import com.jobits.pos.domain.models.Seccion;
import com.jobits.pos.ui.viewmodel.AbstractViewModel;
import java.util.List;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class AreaVentaViewModel extends AbstractViewModel {

    private ArrayListModel<Area> lista_area = new ArrayListModel<>();

    public static final String PROP_LISTA_AREA = "lista_area";

    private ArrayListModel<Mesa> lista_mesas = new ArrayListModel<>();

    public static final String PROP_LISTA_MESAS = "lista_mesas";

    private Area area_seleccionada;

    public static final String PROP_AREA_SELECCIONADA = "area_seleccionada";

    private Mesa mesa_seleccionada;

    public static final String PROP_MESA_SELECCIONADA = "mesa_seleccionada";

    /**
     * Get the value of mesa_seleccionada
     *
     * @return the value of mesa_seleccionada
     */
    public Mesa getMesa_seleccionada() {
        return mesa_seleccionada;
    }

    /**
     * Set the value of mesa_seleccionada
     *
     * @param mesa_seleccionada new value of mesa_seleccionada
     */
    public void setMesa_seleccionada(Mesa mesa_seleccionada) {
        Mesa oldSeccion_seleccionada = this.mesa_seleccionada;
        this.mesa_seleccionada = mesa_seleccionada;
        firePropertyChange(PROP_MESA_SELECCIONADA, oldSeccion_seleccionada, mesa_seleccionada, false);

    }

    /**
     * Get the value of menu_seleccionado
     *
     * @return the value of menu_seleccionado
     */
    public Area getArea_seleccionada() {
        return area_seleccionada;
    }

    /**
     * Set the value of menu_seleccionado
     *
     * @param menu_seleccionado new value of menu_seleccionado
     */
    public void setArea_seleccionada(Area menu_seleccionado) {
        Area oldMenu_seleccionado = new Area();
        this.area_seleccionada = menu_seleccionado;
        firePropertyChange(PROP_AREA_SELECCIONADA, oldMenu_seleccionado, menu_seleccionado, false);
        setMesa_seleccionada(null);
        getLista_mesas().clear();
        getLista_mesas().addAll(getArea_seleccionada().getMesaList());
    }

    /**
     * Get the value of lista_mesas
     *
     * @return the value of lista_mesas
     */
    public ArrayListModel<Mesa> getLista_mesas() {
        return lista_mesas;
    }

    /**
     * Set the value of lista_mesas
     *
     * @param lista_mesas new value of lista_mesas
     */
    private void setLista_mesas(ArrayListModel<Mesa> lista_mesas) {
        ArrayListModel<Mesa> oldLista_secciones = this.lista_mesas;
        this.lista_mesas = lista_mesas;
        firePropertyChange(PROP_LISTA_MESAS, oldLista_secciones, lista_mesas, false);
    }

    /**
     * Get the value of lista_area
     *
     * @return the value of lista_area
     */
    public ArrayListModel<Area> getLista_area() {
        return lista_area;
    }

    /**
     * Set the value of lista_area
     *
     * @param lista_area new value of lista_area
     */
    private void setLista_area(ArrayListModel<Area> lista_area) {
        ArrayListModel<Area> oldLista_menu = this.lista_area;
        this.lista_area = lista_area;
        firePropertyChange(PROP_LISTA_AREA, oldLista_menu, lista_area, false);
    }

}
