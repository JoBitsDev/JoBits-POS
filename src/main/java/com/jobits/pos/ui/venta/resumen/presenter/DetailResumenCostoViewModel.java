/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.resumen.presenter;

import com.jobits.pos.core.domain.models.ProductoInsumo;
import com.jobits.pos.core.domain.models.ProductovOrden;
import com.jobits.pos.core.domain.models.temporal.DayReviewWrapper;
import com.jobits.pos.ui.filter.presenter.FilterViewPresenter;
import com.jobits.pos.ui.viewmodel.AbstractResumenViewModel;

/**
 *
 * @author Home
 */
public class DetailResumenCostoViewModel extends AbstractResumenViewModel<DayReviewWrapper, ProductoInsumo> {

    private FilterViewPresenter<ProductoInsumo> filter_presenter;

    public static final String PROP_FILTER_PRESENTER = "filter_presenter";

    /**
     * Get the value of filter_presenter
     *
     * @return the value of filter_presenter
     */
    public FilterViewPresenter<ProductoInsumo> getFilter_presenter() {
        return filter_presenter;
    }

    /**
     * Set the value of filter_presenter
     *
     * @param filter_presenter new value of filter_presenter
     */
    public void setFilter_presenter(FilterViewPresenter<ProductoInsumo> filter_presenter) {
        FilterViewPresenter<ProductoInsumo> oldFilter_presenter = this.filter_presenter;
        this.filter_presenter = filter_presenter;
        firePropertyChange(PROP_FILTER_PRESENTER, oldFilter_presenter, filter_presenter);
    }
}
