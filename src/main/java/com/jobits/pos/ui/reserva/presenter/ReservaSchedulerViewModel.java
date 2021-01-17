/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.reserva.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.ui.reserva.model.Category;
import com.jobits.pos.ui.viewmodel.AbstractViewModel;
import com.jobits.ui.scheduler.Appointment;
import com.jobits.ui.scheduler.Resource;
import java.util.List;

/**
 *
 * @author Home
 */
public class ReservaSchedulerViewModel extends AbstractViewModel {

    public static ArrayListModel<Resource> lista_ubicaciones = new ArrayListModel<>();

    public static final String PROP_LISTA_UBICACIONES = "lista_ubicaciones";

    public static ArrayListModel<Appointment> lista_reservas = new ArrayListModel<>();

    public static final String PROP_LISTA_RESERVAS = "lista_reservas";

    public static ArrayListModel<Category> list_categorias = new ArrayListModel<>();

    public static final String PROP_LIST_CATEGORIAS = "list_categorias";

    /**
     * Get the value of list_categorias
     *
     * @return the value of list_categorias
     */
    public ArrayListModel<Category> getList_categorias() {
        return list_categorias;
    }

    /**
     * Set the value of list_categorias
     *
     * @param list_categorias new value of list_categorias
     */
    public void setList_categorias(List<Category> list_categorias) {
        ArrayListModel<Category> oldList_categorias = this.list_categorias;
        this.list_categorias.clear();
        this.list_categorias.addAll(list_categorias);
        firePropertyChange(PROP_LIST_CATEGORIAS, oldList_categorias, list_categorias);
    }

    /**
     * Get the value of lista_reservas
     *
     * @return the value of lista_reservas
     */
    public ArrayListModel<Appointment> getLista_reservas() {
        return lista_reservas;
    }

    /**
     * Set the value of lista_reservas
     *
     * @param lista_reservas new value of lista_reservas
     */
    public void setLista_reservas(List<Appointment> lista_reservas) {
        ArrayListModel<Appointment> oldLista_reservas = this.lista_reservas;
        this.lista_reservas.clear();
        this.lista_reservas.addAll(lista_reservas);
        firePropertyChange(PROP_LISTA_RESERVAS, oldLista_reservas, lista_reservas);
    }

    /**
     * Get the value of lista_ubicaciones
     *
     * @return the value of lista_ubicaciones
     */
    public ArrayListModel<Resource> getLista_ubicaciones() {
        return lista_ubicaciones;
    }

    /**
     * Set the value of lista_ubicaciones
     *
     * @param lista_ubicaciones new value of lista_ubicaciones
     */
    public void setLista_ubicaciones(List<Resource> lista_ubicaciones) {
        ArrayListModel<Resource> oldLista_ubicaciones = this.lista_ubicaciones;
        this.lista_ubicaciones.clear();
        this.lista_ubicaciones.addAll(lista_ubicaciones);
        firePropertyChange(PROP_LISTA_UBICACIONES, oldLista_ubicaciones, lista_ubicaciones);
    }

}
