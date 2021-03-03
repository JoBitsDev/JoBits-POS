/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.resumen.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.ui.filter.presenter.FilterType;
import com.jobits.pos.ui.filter.presenter.FilterViewPresenter;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * JoBits
 *
 * @author Jorge
 * @param <T>
 *
 */
public abstract class AbstractResumenViewPresenter<T extends AbstractResumenViewModel>
        extends AbstractViewPresenter<T> {

    public AbstractResumenViewPresenter(T bean, boolean detailedView, String mainViewName,
            String detailViewName, ArrayList<FilterType> filtrosDisponibles) {
        super(bean);
        addBeanPropertyChangeListener(AbstractResumenViewModel.PROP_DETAILSELECTED, (PropertyChangeEvent evt) -> {
            getBean().setTitulo_vista((boolean) evt.getNewValue() ? detailViewName : mainViewName);
        });
        getBean().setDetailSelected(detailedView);
        getBean().setTitulo_vista(detailedView ? detailViewName : mainViewName);
        setFilterPresenter(filtrosDisponibles);
    }

    public AbstractResumenViewPresenter(T bean, boolean detailedView, String mainViewName, String detailViewName) {
        super(bean);
        addBeanPropertyChangeListener(AbstractResumenViewModel.PROP_DETAILSELECTED, (PropertyChangeEvent evt) -> {
            getBean().setTitulo_vista((boolean) evt.getNewValue() ? detailViewName : mainViewName);
        });
        getBean().setDetailSelected(detailedView);
        getBean().setTitulo_vista(detailedView ? detailViewName : mainViewName);
    }

    private void setFilterPresenter(ArrayList<FilterType> filtrosDisponibles) {
        getBean().setFilter_presenter(new FilterViewPresenter<>(filtrosDisponibles));
        getBean().getFilter_presenter().addPropertyChangeListener(FilterViewPresenter.PROP_FILTERED, (PropertyChangeEvent evt) -> {
            if (evt.getNewValue() != null) {
                List a = getBean().getListaDetail();
                a = (List) a.stream().filter((Predicate) evt.getNewValue()).collect(Collectors.toList());
                getBean().setListaDetail(new ArrayListModel<>(a));
            }
        });
    }

    protected abstract void setListsToBean();

    public abstract float getTotal();

    public FilterViewPresenter getFilterPresenter() {
        return getBean().getFilter_presenter();
    }
}
