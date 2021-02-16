/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.filter.presenter;

import com.jobits.pos.core.ui.filter.AbstractFilterTypeModel;
import com.jobits.pos.core.ui.filter.AbstractFilterTypePresenter;
import com.jgoodies.common.collect.ArrayListModel;
import com.jhw.swing.material.standars.MaterialIcons;
import com.jobits.pos.controller.filter.FilterService;
import com.jobits.pos.core.domain.FilterBuilder;
import com.jobits.pos.core.ui.filter.FilterType;
import com.jobits.pos.core.domain.models.Cocina;
import com.jobits.pos.core.domain.models.Insumo;
import com.jobits.pos.core.repo.impl.CocinaDAO;
import static com.jobits.pos.core.ui.filter.FilterType.COCINA;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import java.util.Arrays;
import java.util.Optional;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Home
 */
public class FilterViewPresenter<T> extends AbstractViewPresenter<FilterViewModel> {

    private ArrayListModel<AbstractFilterTypePresenter> lista_presenters = new ArrayListModel<>();

    FilterService service = PosDesktopUiModule.getInstance().getImplementation(FilterService.class);

    public static final String ACTION_ADD_FILTER = "Agregar Filtro";
    public static final String ACTION_FILTRAR = "Filtrar";
    public static final String ACTION_SET_PANEL_VISIBLE = "Panel Visible";
    public static final String PROP_FILTER_ADDED = "Filtro Agregado";
    public static final String PROP_FILTERED = "Filtrado";

    public FilterViewPresenter() {
        super(new FilterViewModel());
        getBean().setFiltros_disponibles(new ArrayListModel<>(Arrays.asList(FilterType.values())));
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

    }

    private void onFilterClick() {
        FilterBuilder<T> fb = new FilterBuilder<>();
        for (AbstractFilterTypePresenter p : lista_presenters) {
            fb.or(p.createPredicate());
        }
        firePropertyChange(PROP_FILTERED, null, fb.build());
    }

    private void onAddFilter() {
        JComboBox<FilterType> jComboBox1 = new JComboBox<>();
        jComboBox1.setModel(new DefaultComboBoxModel<>(getBean().getFiltros_disponibles().toArray(new FilterType[0])));
        Object[] options = {"Seleccionar", "Cancelar"};
        int confirm = JOptionPane.showOptionDialog(
                null,
                jComboBox1,
                "Filtros disponibles",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.YES_NO_OPTION,
                MaterialIcons.SEARCH,
                options,
                options[0]);
        switch (confirm) {
            case JOptionPane.YES_OPTION:
                buildCardPresenter((FilterType) jComboBox1.getSelectedItem());
                break;
            case JOptionPane.NO_OPTION:
                break;
            default:
                break;
        }
    }

    private void onSetPanelVisibile() {
        getBean().setShow_panel(!getBean().isShow_panel());
    }

    private void buildCardPresenter(FilterType filtroSelecionado) {
        AbstractFilterTypeModel filterModel = new AbstractFilterTypeModel<>();
        switch (filtroSelecionado) {
            case COCINA:
                filterModel.setLista_elementos(new ArrayListModel(service.getListaCocinas()));
                break;
            case INSUMO:
                filterModel.setLista_elementos(new ArrayListModel(service.getListaInsumos()));
                break;
        }
        filterModel.setTipo_filtro(filtroSelecionado);
        lista_presenters.add(new AbstractFilterTypePresenter(filterModel));
        firePropertyChange(PROP_FILTER_ADDED, null, lista_presenters.get(lista_presenters.size() - 1));
    }
}
