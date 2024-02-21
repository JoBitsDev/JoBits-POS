/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.resumen.presenter;

import com.jobits.pos.controller.filter.FilterWrapper;
import com.jobits.pos.controller.resumen.ResumenFacadeInterface;
import com.jobits.pos.ui.filter.presenter.FilterType;
import com.jobits.pos.ui.filter.presenter.FilterViewPresenter;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * JoBits
 *
 * @param <T>
 * @author Jorge
 */
public abstract class AbstractResumenViewPresenter<T extends AbstractResumenViewModel>
        extends AbstractViewPresenter<T> {

    public static String IMPRESION_TICKET = "Imprimir en ticket";

    protected ResumenFacadeInterface service = PosDesktopUiModule.getInstance().getImplementation(ResumenFacadeInterface.class);

    String mainViewName;
    String detailViewName;

    public AbstractResumenViewPresenter(T bean, boolean detailedView, String mainViewName,
                                        String detailViewName, ArrayList<FilterType> filtrosDisponibles) {
        super(bean);
        addBeanPropertyChangeListener(AbstractResumenViewModel.PROP_DETAILSELECTED, (PropertyChangeEvent evt) -> {
            getBean().setTitulo_vista((boolean) evt.getNewValue() ? detailViewName : mainViewName);
        });
        this.mainViewName = mainViewName;
        this.detailViewName = detailViewName;
        setView(detailedView);
        setFilterPresenter(filtrosDisponibles);
    }

    public AbstractResumenViewPresenter(T bean, boolean detailedView, String mainViewName, String detailViewName) {
        super(bean);
        addBeanPropertyChangeListener(AbstractResumenViewModel.PROP_DETAILSELECTED, (PropertyChangeEvent evt) -> {
            getBean().setTitulo_vista((boolean) evt.getNewValue() ? detailViewName : mainViewName);
        });
        this.mainViewName = mainViewName;
        this.detailViewName = detailViewName;
        setView(detailedView);
    }

    protected abstract void setListsToBean();

    protected abstract void printToTicketPrinter();

    public abstract float getTotal();

    public FilterViewPresenter getFilterPresenter() {
        return getBean().getFilter_presenter();
    }

    private void setFilterPresenter(ArrayList<FilterType> filtrosDisponibles) {
        getBean().setFilter_presenter(new FilterViewPresenter<>(filtrosDisponibles));
        getBean().getFilter_presenter().addPropertyChangeListener(FilterViewPresenter.PROP_FILTERED, (PropertyChangeEvent evt) -> {
            if (evt.getNewValue() != null) {
                List<FilterWrapper> filters = (List<FilterWrapper>) evt.getNewValue();
                getBean().setFilters(filters);
                refreshState();
            }
        });
    }

    @Override
    protected Optional refreshState() {
        setListsToBean();
        return super.refreshState();
    }

    public void setView(boolean detailedView) {
        getBean().setDetailSelected(detailedView);
        getBean().setTitulo_vista(detailedView ? detailViewName : mainViewName);
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(IMPRESION_TICKET) {
            @Override
            public Optional doAction() {
                printToTicketPrinter();
                return Optional.empty();
            }
        });
    }

}
