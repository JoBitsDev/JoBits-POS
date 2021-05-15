/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.configuracion.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.ui.viewmodel.AbstractViewModel;
import com.jobits.ui.themes.ThemeType;

/**
 *
 * @author Home
 */
public class VisualesViewModel extends AbstractViewModel {

    private ArrayListModel<String> lista_temas = new ArrayListModel<>(ThemeType.stringValues());

    public static final String PROP_LISTA_TEMAS = "lista_temas";

    private String themeType_Seleccionado = ThemeType.NIMBUS.getThemeName();

    public static final String PROP_THEMETYPE_SELECCIONADO = "themeType_Seleccionado";

    /**
     * Get the value of themeType_Seleccionado
     *
     * @return the value of themeType_Seleccionado
     */
    public String getThemeType_Seleccionado() {
        return themeType_Seleccionado;
    }

    /**
     * Set the value of themeType_Seleccionado
     *
     * @param themeType_Seleccionado new value of themeType_Seleccionado
     */
    public void setThemeType_Seleccionado(String themeType_Seleccionado) {
        String oldThemeType_Seleccionado = this.themeType_Seleccionado;
        this.themeType_Seleccionado = themeType_Seleccionado;
        firePropertyChange(PROP_THEMETYPE_SELECCIONADO, oldThemeType_Seleccionado, themeType_Seleccionado);
    }

    /**
     * Get the value of lista_temas
     *
     * @return the value of lista_temas
     */
    public ArrayListModel<String> getLista_temas() {
        return lista_temas;
    }

    /**
     * Set the value of lista_temas
     *
     * @param lista_temas new value of lista_temas
     */
    public void setLista_temas(ArrayListModel<String> lista_temas) {
        ArrayListModel<String> oldLista_temas = this.lista_temas;
        this.lista_temas = lista_temas;
        firePropertyChange(PROP_LISTA_TEMAS, oldLista_temas, lista_temas);
    }

}
