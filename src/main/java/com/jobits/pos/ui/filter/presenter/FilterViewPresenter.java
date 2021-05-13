/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.filter.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.filter.FilterService;
import com.jobits.pos.core.domain.FilterBuilder;
import static com.jobits.pos.ui.filter.presenter.AbstractFilterTypePresenter.ACTION_AUTO_ELIMINAR_FILTRO;
import static com.jobits.pos.ui.filter.presenter.FilterType.COCINA;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;

/**
 *
 * @author Home
 */
public class FilterViewPresenter<T> extends AbstractViewPresenter<FilterViewModel> {

    private ArrayListModel<AbstractFilterTypePresenter> lista_presenters = new ArrayListModel<>();

    FilterService service = PosDesktopUiModule.getInstance().getImplementation(FilterService.class);

    public static final String ACTION_ADD_FILTER = "Agregar Filtro";
    public static final String ACTION_REMOVE_ALL_FILTERS = "Reiniciar Filtros";
    public static final String ACTION_FILTRAR = "Filtrar";
    public static final String ACTION_SET_PANEL_VISIBLE = "Panel Visible";

    public static final String PROP_FILTER_ADDED = "Filtro Agregado";
    public static final String PROP_FILTER_DELETED = "Filtro Eliminado";
    public static final String PROP_FILTERS_RESETED = "Filtros Reiniciados";
    public static final String PROP_FILTERED = "Filtrado";

    public FilterViewPresenter(ArrayList<FilterType> filtrosDisponibles) {
        super(new FilterViewModel());
        getBean().setFiltros_disponibles(new ArrayListModel<>(filtrosDisponibles));
        if (!getBean().getFiltros_disponibles().isEmpty()) {
            getBean().setFiltro_seleccionado(getBean().getFiltros_disponibles().get(0));
        }
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_ADD_FILTER) {
            @Override
            public Optional doAction() {
                onAddFilter();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_FILTRAR) {
            @Override
            public Optional doAction() {
                onFilterClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_SET_PANEL_VISIBLE) {
            @Override
            public Optional doAction() {
                onSetPanelVisibile();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_REMOVE_ALL_FILTERS) {
            @Override
            public Optional doAction() {
                onResetFilters();
                return Optional.empty();
            }
        });

    }

    private void onFilterClick() {
        FilterBuilder<T> fb = new FilterBuilder<>();
        for (AbstractFilterTypePresenter p : lista_presenters) {
            Predicate predicado = p.createPredicate();
            if (p.getBean().isTipo_operacion()) {
                fb.or(predicado);
            } else {
                fb.and(predicado);
            }
        }
        firePropertyChange(PROP_FILTERED, null, fb.build());
    }

    private void onAddFilter() {
        if (getBean().getFiltro_seleccionado() != null) {
            AbstractFilterTypeModel filterModel = new AbstractFilterTypeModel<>();
            switch (getBean().getFiltro_seleccionado()) {
                case COCINA:
                    filterModel.setLista_elementos(new ArrayListModel(service.getListaCocinas()));
                    break;
                case INSUMO:
                    filterModel.setLista_elementos(new ArrayListModel(service.getListaInsumos()));
                    break;
                case AREA:
                    filterModel.setLista_elementos(new ArrayListModel(service.getListaAreas()));
                    break;
                case IPV:
                    filterModel.setLista_elementos(new ArrayListModel(service.getListaCocinas()));
                    break;
                case PRODUCTO:
                    filterModel.setLista_elementos(new ArrayListModel(service.getListaProductos()));
                    break;
                case SECCION:
                    filterModel.setLista_elementos(new ArrayListModel(service.getListaSecciones()));
                    break;
                case COCINA_E:
                    filterModel.setLista_elementos(new ArrayListModel(service.getListaCocinas()));
                    break;
                case PRODUCTO_E:
                    filterModel.setLista_elementos(new ArrayListModel(service.getListaProductos()));
                    break;
                case IPV_E:
                    filterModel.setLista_elementos(new ArrayListModel(service.getListaCocinas()));
                    break;
            }
            filterModel.setTipo_filtro(getBean().getFiltro_seleccionado());
            AbstractFilterTypePresenter p = new AbstractFilterTypePresenter(filterModel);
            p.addPropertyChangeListener(ACTION_AUTO_ELIMINAR_FILTRO, (PropertyChangeEvent evt) -> {
                firePropertyChange(PROP_FILTER_DELETED, null, evt.getNewValue());
                lista_presenters.remove((AbstractFilterTypePresenter) evt.getNewValue());
            });
            lista_presenters.add(p);
            firePropertyChange(PROP_FILTER_ADDED, null, lista_presenters.get(lista_presenters.size() - 1));
        }
    }

    private void onSetPanelVisibile() {
        getBean().setShow_panel(!getBean().isShow_panel());
    }

    private void onResetFilters() {
        firePropertyChange(PROP_FILTERS_RESETED, null, this);
        lista_presenters.clear();
    }
}
