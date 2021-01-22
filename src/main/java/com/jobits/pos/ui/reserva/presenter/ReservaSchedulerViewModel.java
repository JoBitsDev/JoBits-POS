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
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Home
 */
public class ReservaSchedulerViewModel extends AbstractViewModel {

    private ArrayListModel<Resource> lista_ubicaciones = new ArrayListModel<>();

    public static final String PROP_LISTA_UBICACIONES = "lista_ubicaciones";

    private ArrayListModel<Appointment> lista_reservas = new ArrayListModel<>();

    public static final String PROP_LISTA_RESERVAS = "lista_reservas";

    private ArrayListModel<Category> list_categorias = new ArrayListModel<>();

    public static final String PROP_LIST_CATEGORIAS = "list_categorias";

    private String total_indices = "0";

    public static final String PROP_TOTAL_INDICES = "total_indices";

    private String indice_actual = "0";

    public static final String PROP_INDICE_ACTUAL = "indice_actual";

    private Date dia_seleccionado = new Date();

    public static final String PROP_DIA_SELECCIONADO = "dia_seleccionado";

    private LocalDate selected_date = LocalDate.now();

    /**
     * Get the value of selected_date
     *
     * @return the value of selected_date
     */
    public LocalDate getSelected_date() {
        return selected_date;
    }

    /**
     * Set the value of selected_date
     *
     * @param selected_date new value of selected_date
     */
    public void setSelected_date(LocalDate selected_date) {
        LocalDate oldDia_seleccionado = this.selected_date;
        this.selected_date = selected_date;
        firePropertyChange(PROP_DIA_SELECCIONADO, oldDia_seleccionado, selected_date);
    }

    /**
     * Get the value of dia_seleccionado
     *
     * @return the value of dia_seleccionado
     */
    public Date getDia_seleccionado() {
        return dia_seleccionado;
    }

    /**
     * Set the value of dia_seleccionado
     *
     * @param dia_seleccionado new value of dia_seleccionado
     */
    public void setDia_seleccionado(Date dia_seleccionado) {
        Date oldDia_seleccionado = this.dia_seleccionado;
        this.dia_seleccionado = dia_seleccionado;
        firePropertyChange(PROP_DIA_SELECCIONADO, oldDia_seleccionado, dia_seleccionado);
    }

    /**
     * Get the value of indice_actual
     *
     * @return the value of indice_actual
     */
    public String getIndice_actual() {
        return indice_actual;
    }

    /**
     * Set the value of indice_actual
     *
     * @param indice_actual new value of indice_actual
     */
    public void setIndice_actual(String indice_actual) {
        String oldIndice_actual = this.indice_actual;
        this.indice_actual = indice_actual;
        firePropertyChange(PROP_INDICE_ACTUAL, oldIndice_actual, indice_actual);
    }

    /**
     * Get the value of total_indices
     *
     * @return the value of total_indices
     */
    public String getTotal_indices() {
        return total_indices;
    }

    /**
     * Set the value of total_indices
     *
     * @param total_indices new value of total_indices
     */
    public void setTotal_indices(String total_indices) {
        String oldTotal_indices = this.total_indices;
        this.total_indices = total_indices;
        firePropertyChange(PROP_TOTAL_INDICES, oldTotal_indices, total_indices);
    }

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
        this.list_categorias.addAll(new ArrayListModel<>(list_categorias));
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
        this.lista_reservas.addAll(new ArrayListModel<>(lista_reservas));
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
        this.lista_ubicaciones.addAll(new ArrayListModel<>(lista_ubicaciones));
        firePropertyChange(PROP_LISTA_UBICACIONES, oldLista_ubicaciones, lista_ubicaciones);
    }

}
