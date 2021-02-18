/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.resumen.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.core.domain.models.ProductovOrden;
import com.jobits.pos.ui.filter.presenter.FilterViewPresenter;
import com.jobits.pos.ui.viewmodel.AbstractViewModel;
import java.util.Date;

/**
 *
 * JoBits
 *
 * @author Jorge
 * @param <Main> la lista que se va a mostrar cuando este en el general
 * @param <Detail> la lista que se va a mostrar cuando este en el detail
 *
 */
public abstract class AbstractResumenViewModel<Main, Detail> extends AbstractViewModel {

    protected ArrayListModel<Main> listaMain = new ArrayListModel<>();

    public static final String PROP_LISTAMAIN = "listaMain";

    protected Main mainSelected;

    public static final String PROP_MAINSELECTED = "mainSelected";

    protected ArrayListModel<Detail> listaDetail = new ArrayListModel<>();

    public static final String PROP_LISTADETAIL = "listaDetail";

    protected Detail selected_detail;

    public static final String PROP_SELECTED_DETAIL = "selected_detail";

    protected boolean detailSelected = false;

    public static final String PROP_DETAILSELECTED = "detailSelected";

    protected String titulo_vista;

    public static final String PROP_TITULO_VISTA = "titulo_vista";

    private Date since_date = new Date();

    public static final String PROP_SINCE_DATE = "since_date";

    private Date to_date = new Date();

    public static final String PROP_TO_DATE = "to_date";

    private FilterViewPresenter<Detail> filter_presenter;

    public static final String PROP_FILTER_PRESENTER = "filter_presenter";

    /**
     * Get the value of filter_presenter
     *
     * @return the value of filter_presenter
     */
    public FilterViewPresenter<Detail> getFilter_presenter() {
        return filter_presenter;
    }

    /**
     * Set the value of filter_presenter
     *
     * @param filter_presenter new value of filter_presenter
     */
    public void setFilter_presenter(FilterViewPresenter<Detail> filter_presenter) {
        FilterViewPresenter<Detail> oldFilter_presenter = this.filter_presenter;
        this.filter_presenter = filter_presenter;
        firePropertyChange(PROP_FILTER_PRESENTER, oldFilter_presenter, filter_presenter);
    }

    /**
     * Get the value of to_date
     *
     * @return the value of to_date
     */
    public Date getTo_date() {
        return to_date;
    }

    /**
     * Set the value of to_date
     *
     * @param to_date new value of to_date
     */
    public void setTo_date(Date to_date) {
        Date oldTo_date = this.to_date;
        this.to_date = to_date;
        firePropertyChange(PROP_TO_DATE, oldTo_date, to_date);
    }

    /**
     * Get the value of since_date
     *
     * @return the value of since_date
     */
    public Date getSince_date() {
        return since_date;
    }

    /**
     * Set the value of since_date
     *
     * @param since_date new value of since_date
     */
    public void setSince_date(Date since_date) {
        Date oldSince_date = this.since_date;
        this.since_date = since_date;
        firePropertyChange(PROP_SINCE_DATE, oldSince_date, since_date);
    }

    /**
     * Get the value of titulo_vista
     *
     * @return the value of titulo_vista
     */
    public String getTitulo_vista() {
        return titulo_vista;
    }

    /**
     * Set the value of titulo_vista
     *
     * @param titulo_vista new value of titulo_vista
     */
    public void setTitulo_vista(String titulo_vista) {
        String oldTitulo_vista = this.titulo_vista;
        this.titulo_vista = titulo_vista;
        firePropertyChange(PROP_TITULO_VISTA, oldTitulo_vista, titulo_vista);
    }

    /**
     * Get the value of detailSelected
     *
     * @return the value of detailSelected
     */
    public boolean isDetailSelected() {
        return detailSelected;
    }

    /**
     * Set the value of detailSelected
     *
     * @param detailSelected new value of detailSelected
     */
    public void setDetailSelected(boolean detailSelected) {
        boolean oldDetailSelected = this.detailSelected;
        this.detailSelected = detailSelected;
        firePropertyChange(PROP_DETAILSELECTED, oldDetailSelected, detailSelected);
    }

    /**
     * Get the value of selected_detail
     *
     * @return the value of selected_detail
     */
    public Detail getSelected_detail() {
        return selected_detail;
    }

    /**
     * Set the value of selected_detail
     *
     * @param selected_detail new value of selected_detail
     */
    public void setSelected_detail(Detail selected_detail) {
        Detail oldSelected_detail = this.selected_detail;
        this.selected_detail = selected_detail;
        firePropertyChange(PROP_SELECTED_DETAIL, oldSelected_detail, selected_detail);
    }

    /**
     * Get the value of listaDetail
     *
     * @return the value of listaDetail
     */
    public ArrayListModel<Detail> getListaDetail() {
        return listaDetail;
    }

    /**
     * Set the value of listaDetail
     *
     * @param listaDetail new value of listaDetail
     */
    public void setListaDetail(ArrayListModel<Detail> listaDetail) {
        ArrayListModel oldListaDetail = this.listaDetail;
        this.listaDetail.clear();
        this.listaDetail.addAll(listaDetail);
        firePropertyChange(PROP_LISTADETAIL, oldListaDetail, listaDetail);
    }

    /**
     * Get the value of mainSelected
     *
     * @return the value of mainSelected
     */
    public Main getMainSelected() {
        return mainSelected;
    }

    /**
     * Set the value of mainSelected
     *
     * @param mainSelected new value of mainSelected
     */
    public void setMainSelected(Main mainSelected) {
        Main oldMainSelected = this.mainSelected;
        this.mainSelected = mainSelected;
        firePropertyChange(PROP_MAINSELECTED, oldMainSelected, mainSelected);
    }

    /**
     * Get the value of listaMain
     *
     * @return the value of listaMain
     */
    public ArrayListModel<Main> getListaMain() {
        return listaMain;
    }

    /**
     * Set the value of listaMain
     *
     * @param listaMain new value of listaMain
     */
    public void setListaMain(ArrayListModel<Main> listaMain) {
        ArrayListModel oldListaMain = this.listaMain;
        this.listaMain.clear();
        this.listaMain.addAll(listaMain);
        firePropertyChange(PROP_LISTAMAIN, oldListaMain, listaMain);
    }

}
