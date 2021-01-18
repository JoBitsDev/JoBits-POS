/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.reserva.ubicaciones.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.ui.viewmodel.AbstractViewModel;
import java.awt.Color;

/**
 *
 * @author Home
 */
public class CategoriaDetailVIewModel extends AbstractViewModel {

    private String nombre_categoria;

    public static final String PROP_NOMBRE_CATEGORIA = "nombre_categoria";

    private ArrayListModel<Color> lista_colores = new ArrayListModel<>();

    public static final String PROP_LISTA_COLORES = "lista_colores";

    private Color color_seleccionado;

    public static final String PROP_COLOR_SELECCIONADO = "color_seleccionado";

    /**
     * Get the value of color_seleccionado
     *
     * @return the value of color_seleccionado
     */
    public Color getColor_seleccionado() {
        return color_seleccionado;
    }

    /**
     * Set the value of color_seleccionado
     *
     * @param color_seleccionado new value of color_seleccionado
     */
    public void setColor_seleccionado(Color color_seleccionado) {
        Color oldColor_seleccionado = this.color_seleccionado;
        this.color_seleccionado = color_seleccionado;
        firePropertyChange(PROP_COLOR_SELECCIONADO, oldColor_seleccionado, color_seleccionado);
    }

    /**
     * Get the value of lista_colores
     *
     * @return the value of lista_colores
     */
    public ArrayListModel<Color> getLista_colores() {
        return lista_colores;
    }

    /**
     * Set the value of lista_colores
     *
     * @param lista_colores new value of lista_colores
     */
    public void setLista_colores(ArrayListModel<Color> lista_colores) {
        ArrayListModel<Color> oldLista_colores = this.lista_colores;
        this.lista_colores = lista_colores;
        firePropertyChange(PROP_LISTA_COLORES, oldLista_colores, lista_colores);
    }

    /**
     * Get the value of nombre_categoria
     *
     * @return the value of nombre_categoria
     */
    public String getNombre_categoria() {
        return nombre_categoria;
    }

    /**
     * Set the value of nombre_categoria
     *
     * @param nombre_categoria new value of nombre_categoria
     */
    public void setNombre_categoria(String nombre_categoria) {
        String oldNombre_categoria = this.nombre_categoria;
        this.nombre_categoria = nombre_categoria;
        firePropertyChange(PROP_NOMBRE_CATEGORIA, oldNombre_categoria, nombre_categoria);
    }

}
