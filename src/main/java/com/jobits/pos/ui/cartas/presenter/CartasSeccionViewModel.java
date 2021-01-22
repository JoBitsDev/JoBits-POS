/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.cartas.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.core.domain.models.Carta;
import com.jobits.pos.core.domain.models.Seccion;
import com.jobits.pos.ui.viewmodel.AbstractViewModel;
import java.util.List;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class CartasSeccionViewModel extends AbstractViewModel {

    private ArrayListModel<Carta> lista_menu = new ArrayListModel<>();

    public static final String PROP_LISTA_MENU = "lista_menu";

    private ArrayListModel<Seccion> lista_secciones = new ArrayListModel<>();

    public static final String PROP_LISTA_SECCIONES = "lista_secciones";

    private Carta menu_seleccionado;

    public static final String PROP_MENU_SELECCIONADO = "menu_seleccionado";

    private Seccion seccion_seleccionada;

    public static final String PROP_SECCION_SELECCIONADA = "seccion_seleccionada";

    /**
     * Get the value of seccion_seleccionada
     *
     * @return the value of seccion_seleccionada
     */
    public Seccion getSeccion_seleccionada() {
        return seccion_seleccionada;
    }

    /**
     * Set the value of seccion_seleccionada
     *
     * @param seccion_seleccionada new value of seccion_seleccionada
     */
    public void setSeccion_seleccionada(Seccion seccion_seleccionada) {
        Seccion oldSeccion_seleccionada = this.seccion_seleccionada;
        this.seccion_seleccionada = seccion_seleccionada;
        firePropertyChange(PROP_SECCION_SELECCIONADA, oldSeccion_seleccionada, seccion_seleccionada, false);

    }

    /**
     * Get the value of menu_seleccionado
     *
     * @return the value of menu_seleccionado
     */
    public Carta getMenu_seleccionado() {
        return menu_seleccionado;
    }

    /**
     * Set the value of menu_seleccionado
     *
     * @param menu_seleccionado new value of menu_seleccionado
     */
    public void setMenu_seleccionado(Carta menu_seleccionado) {
        Carta oldMenu_seleccionado = new Carta();
        this.menu_seleccionado = menu_seleccionado;
        firePropertyChange(PROP_MENU_SELECCIONADO, oldMenu_seleccionado, menu_seleccionado, false);
        setSeccion_seleccionada(null);
        getLista_secciones().clear();
        if (menu_seleccionado != null) {
            getLista_secciones().addAll(getMenu_seleccionado().getSeccionList());
        }
    }

    /**
     * Get the value of lista_secciones
     *
     * @return the value of lista_secciones
     */
    public ArrayListModel<Seccion> getLista_secciones() {
        return lista_secciones;
    }

    /**
     * Set the value of lista_secciones
     *
     * @param lista_secciones new value of lista_secciones
     */
    public void setLista_secciones(List<Seccion> lista_secciones) {
        ArrayListModel<Seccion> oldLista_secciones = this.lista_secciones;
        this.lista_secciones.clear();
        this.lista_secciones.addAll(lista_secciones);
        firePropertyChange(PROP_LISTA_SECCIONES, oldLista_secciones, lista_secciones, false);
    }

    /**
     * Get the value of lista_menu
     *
     * @return the value of lista_menu
     */
    public ArrayListModel<Carta> getLista_menu() {
        return lista_menu;
    }

    /**
     * Set the value of lista_menu
     *
     * @param lista_menu new value of lista_menu
     */
    public void setLista_menu(List<Carta> lista_menu) {
        ArrayListModel<Carta> oldLista_menu = this.lista_menu;
        this.lista_menu.clear();
        this.lista_menu.addAll(lista_menu);
        firePropertyChange(PROP_LISTA_MENU, oldLista_menu, lista_menu, false);
    }

}
