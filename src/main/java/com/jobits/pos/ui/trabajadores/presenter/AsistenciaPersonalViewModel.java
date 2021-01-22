/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.trabajadores.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.core.domain.models.AsistenciaPersonal;
import com.jobits.pos.core.domain.models.Personal;
import com.jobits.pos.ui.viewmodel.AbstractViewModel;

/**
 *
 * @author Home
 */
public class AsistenciaPersonalViewModel extends AbstractViewModel {

    private ArrayListModel<Personal> lista_personal_disponible = new ArrayListModel<>();

    public static final String PROP_LISTA_PERSONAL_DISPONIBLE = "lista_personal_disponible";

    private Personal personal_disponible_seleccionado;

    public static final String PROP_PERSONAL_DISPONIBLE_SELECCIONADO = "personal_disponible_seleccionado";

    private ArrayListModel<AsistenciaPersonal> lista_personal_contenido = new ArrayListModel<>();

    public static final String PROP_LISTA_PERSONAL_CONTENIDO = "lista_personal_contenido";

    private AsistenciaPersonal personal_contenido_selecionado;

    public static final String PROP_PERSONAL_CONTENIDO_SELECIONADO = "personal_contenido_selecionado";

    /**
     * Get the value of personal_contenido_selecionado
     *
     * @return the value of personal_contenido_selecionado
     */
    public AsistenciaPersonal getPersonal_contenido_selecionado() {
        return personal_contenido_selecionado;
    }

    /**
     * Set the value of personal_contenido_selecionado
     *
     * @param personal_contenido_selecionado new value of
     * personal_contenido_selecionado
     */
    public void setPersonal_contenido_selecionado(AsistenciaPersonal personal_contenido_selecionado) {
        AsistenciaPersonal oldPersonal_contenido_selecionado = this.personal_contenido_selecionado;
        this.personal_contenido_selecionado = personal_contenido_selecionado;
        firePropertyChange(PROP_PERSONAL_CONTENIDO_SELECIONADO, oldPersonal_contenido_selecionado, personal_contenido_selecionado);
    }

    /**
     * Get the value of lista_personal_contenido
     *
     * @return the value of lista_personal_contenido
     */
    public ArrayListModel<AsistenciaPersonal> getLista_personal_contenido() {
        return lista_personal_contenido;
    }

    /**
     * Set the value of lista_personal_contenido
     *
     * @param lista_personal_contenido new value of lista_personal_contenido
     */
    public void setLista_personal_contenido(ArrayListModel<AsistenciaPersonal> lista_personal_contenido) {
        ArrayListModel<AsistenciaPersonal> oldLista_personal_contenido = this.lista_personal_contenido;
        this.lista_personal_contenido = lista_personal_contenido;
        firePropertyChange(PROP_LISTA_PERSONAL_CONTENIDO, oldLista_personal_contenido, lista_personal_contenido);
    }

    /**
     * Get the value of personal_disponible_seleccionado
     *
     * @return the value of personal_disponible_seleccionado
     */
    public Personal getPersonal_disponible_seleccionado() {
        return personal_disponible_seleccionado;
    }

    /**
     * Set the value of personal_disponible_seleccionado
     *
     * @param personal_disponible_seleccionado new value of
     * personal_disponible_seleccionado
     */
    public void setPersonal_disponible_seleccionado(Personal personal_disponible_seleccionado) {
        Personal oldPersonal_disponible_seleccionado = this.personal_disponible_seleccionado;
        this.personal_disponible_seleccionado = personal_disponible_seleccionado;
        firePropertyChange(PROP_PERSONAL_DISPONIBLE_SELECCIONADO, oldPersonal_disponible_seleccionado, personal_disponible_seleccionado);
    }

    /**
     * Get the value of lista_personal_disponible
     *
     * @return the value of lista_personal_disponible
     */
    public ArrayListModel<Personal> getLista_personal_disponible() {
        return lista_personal_disponible;
    }

    /**
     * Set the value of lista_personal_disponible
     *
     * @param lista_personal_disponible new value of lista_personal_disponible
     */
    public void setLista_personal_disponible(ArrayListModel<Personal> lista_personal_disponible) {
        ArrayListModel<Personal> oldLista_personal_disponible = this.lista_personal_disponible;
        this.lista_personal_disponible = lista_personal_disponible;
        firePropertyChange(PROP_LISTA_PERSONAL_DISPONIBLE, oldLista_personal_disponible, lista_personal_disponible);
    }

}
