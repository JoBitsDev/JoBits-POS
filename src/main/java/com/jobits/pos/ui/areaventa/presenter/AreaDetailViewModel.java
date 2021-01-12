/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.areaventa.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.core.domain.models.Carta;
import com.jobits.pos.ui.viewmodel.AbstractViewModel;

/**
 *
 * @author Home
 */
public class AreaDetailViewModel extends AbstractViewModel {

    private String id_area= " ";

    public static final String PROP_ID_AREA = "id_area";

    private String nombre_area = " ";

    public static final String PROP_NOMBRE_AREA = "nombre_area";

    private int cant_mesas_area = 0;

    public static final String PROP_CANT_MESAS_AREA = "cant_mesas_area";

    private int porciento_servicio = 0;

    public static final String PROP_PORCIENTO_SERVICIO = "porciento_servicio";

    private Carta menu_seleccionado_area;

    public static final String PROP_MENU_SELECCIONADO_AREA = "menu_seleccionado_area";

    private ArrayListModel<Carta> lista_menu_area = new ArrayListModel<>();

    public static final String PROP_LISTA_MENU_AREA = "lista_menu_area";

    private Carta menu_selecionado;

    public static final String PROP_MENU_SELECIONADO = "menu_selecionado";

    private ArrayListModel<Carta> lista_all_menus = new ArrayListModel<>();

    public static final String PROP_LISTA_ALL_MENUS = "lista_all_menus";

    /**
     * Get the value of menu_seleccionado_area
     *
     * @return the value of menu_seleccionado_area
     */
    public Carta getMenu_seleccionado_area() {
        return menu_seleccionado_area;
    }

    /**
     * Set the value of menu_seleccionado_area
     *
     * @param menu_seleccionado_area new value of menu_seleccionado_area
     */
    public void setMenu_seleccionado_area(Carta menu_seleccionado_area) {
        Carta oldMenu_seleccionado_area = this.menu_seleccionado_area;
        this.menu_seleccionado_area = menu_seleccionado_area;
        firePropertyChange(PROP_MENU_SELECCIONADO_AREA, oldMenu_seleccionado_area, menu_seleccionado_area);
    }

    /**
     * Get the value of menu_selecionado
     *
     * @return the value of menu_selecionado
     */
    public Carta getMenu_selecionado() {
        return menu_selecionado;
    }

    /**
     * Set the value of menu_selecionado
     *
     * @param menu_selecionado new value of menu_selecionado
     */
    public void setMenu_selecionado(Carta menu_selecionado) {
        Carta oldMenu_selecionado = this.menu_selecionado;
        this.menu_selecionado = menu_selecionado;
        firePropertyChange(PROP_MENU_SELECIONADO, oldMenu_selecionado, menu_selecionado);
    }

    /**
     * Get the value of lista_all_menus
     *
     * @return the value of lista_all_menus
     */
    public ArrayListModel<Carta> getLista_all_menus() {
        return lista_all_menus;
    }

    /**
     * Set the value of lista_all_menus
     *
     * @param lista_all_menus new value of lista_all_menus
     */
    public void setLista_all_menus(ArrayListModel<Carta> lista_all_menus) {
        ArrayListModel<Carta> oldLista_all_menus = this.lista_all_menus;
        this.lista_all_menus = lista_all_menus;
        firePropertyChange(PROP_LISTA_ALL_MENUS, oldLista_all_menus, lista_all_menus);
    }

    /**
     * Get the value of lista_menu_area
     *
     * @return the value of lista_menu_area
     */
    public ArrayListModel<Carta> getLista_menu_area() {
        return lista_menu_area;
    }

    /**
     * Set the value of lista_menu_area
     *
     * @param lista_menu_area new value of lista_menu_area
     */
    public void setLista_menu_area(ArrayListModel<Carta> lista_menu_area) {
        ArrayListModel<Carta> oldLista_menu_area = this.lista_menu_area;
        this.lista_menu_area = lista_menu_area;
        firePropertyChange(PROP_LISTA_MENU_AREA, oldLista_menu_area, lista_menu_area);
    }

    /**
     * Get the value of porciento_servicio
     *
     * @return the value of porciento_servicio
     */
    public int getPorciento_servicio() {
        return porciento_servicio;
    }

    /**
     * Set the value of porciento_servicio
     *
     * @param porciento_servicio new value of porciento_servicio
     */
    public void setPorciento_servicio(int porciento_servicio) {
        int oldPorciento_servicio = this.porciento_servicio;
        this.porciento_servicio = porciento_servicio;
        firePropertyChange(PROP_PORCIENTO_SERVICIO, oldPorciento_servicio, porciento_servicio);
    }

    /**
     * Get the value of cant_mesas_area
     *
     * @return the value of cant_mesas_area
     */
    public int getCant_mesas_area() {
        return cant_mesas_area;
    }

    /**
     * Set the value of cant_mesas_area
     *
     * @param cant_mesas_area new value of cant_mesas_area
     */
    public void setCant_mesas_area(int cant_mesas_area) {
        int oldCant_mesas_area = this.cant_mesas_area;
        this.cant_mesas_area = cant_mesas_area;
        firePropertyChange(PROP_CANT_MESAS_AREA, oldCant_mesas_area, cant_mesas_area);
    }

    /**
     * Get the value of nombre_area
     *
     * @return the value of nombre_area
     */
    public String getNombre_area() {
        return nombre_area;
    }

    /**
     * Set the value of nombre_area
     *
     * @param nombre_area new value of nombre_area
     */
    public void setNombre_area(String nombre_area) {
        String oldNombre_area = this.nombre_area;
        this.nombre_area = nombre_area;
        firePropertyChange(PROP_NOMBRE_AREA, oldNombre_area, nombre_area);
    }

    /**
     * Get the value of id_area
     *
     * @return the value of id_area
     */
    public String getId_area() {
        return id_area;
    }

    /**
     * Set the value of id_area
     *
     * @param id_area new value of id_area
     */
    public void setId_area(String id_area) {
        String oldId_area = this.id_area;
        this.id_area = id_area;
        firePropertyChange(PROP_ID_AREA, oldId_area, id_area);
    }

}
