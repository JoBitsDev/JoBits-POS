/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.filter.presenter;

import com.jobits.pos.controller.filter.FilterWrapper;
import com.jobits.pos.core.domain.models.*;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;

import java.util.Optional;

/**
 * @author Home
 */
public class AbstractFilterTypePresenter extends AbstractViewPresenter<AbstractFilterTypeModel> {

    public static final String ACTION_AUTO_ELIMINAR_FILTRO = "Eliminar Filtro";
    public static final String ACTION_SET_FILTER_OPERACTION = "Seleccionar Operacion";

    public AbstractFilterTypePresenter(AbstractFilterTypeModel bean) {
        super(bean);
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_AUTO_ELIMINAR_FILTRO) {
            @Override
            public Optional doAction() {
                onSelfDestruct();
                return Optional.empty();
            }

        });
        registerOperation(new AbstractViewAction(ACTION_SET_FILTER_OPERACTION) {
            @Override
            public Optional doAction() {
                onSetOperation();
                return Optional.empty();
            }
        });
    }

    private void onSelfDestruct() {
        firePropertyChange(ACTION_AUTO_ELIMINAR_FILTRO, null, this);
    }

    private void onSetOperation() {
        getBean().setTipo_operacion(!getBean().isTipo_operacion());
    }

    public FilterType getTipoFiltro() {
        return getBean().getTipo_filtro();
    }

    public FilterWrapper createPredicate() {
        FilterWrapper ret = null;
        switch (getBean().getTipo_filtro()) {
            case COCINA:
                ret = new FilterWrapper(com.jobits.pos.controller.filter.FilterType.COCINA, ((Cocina) getBean().getElemento_seleccionado()).getCodCocina());
                break;
            case INSUMO:
                ret = new FilterWrapper(com.jobits.pos.controller.filter.FilterType.INSUMO, ((Insumo) getBean().getElemento_seleccionado()).getCodInsumo());
                break;
            case AREA:
                ret = new FilterWrapper(com.jobits.pos.controller.filter.FilterType.AREA, ((Area) getBean().getElemento_seleccionado()).getCodArea());
                break;
            case IPV:
                ret = new FilterWrapper(com.jobits.pos.controller.filter.FilterType.IPV, ((Cocina) getBean().getElemento_seleccionado()).getCodCocina());
                break;
            case PRODUCTO:
                ret = new FilterWrapper(com.jobits.pos.controller.filter.FilterType.PRODUCTO, ((ProductoVenta) getBean().getElemento_seleccionado()).getCodigoProducto());
                break;
            case SECCION:
                ret = new FilterWrapper(com.jobits.pos.controller.filter.FilterType.SECCION, ((Seccion) getBean().getElemento_seleccionado()).getNombreSeccion());
                break;
            case COCINA_E:
                ret = new FilterWrapper(com.jobits.pos.controller.filter.FilterType.COCINA_E, ((Cocina) getBean().getElemento_seleccionado()).getCodCocina());
                break;
            case IPV_E:
                ret = new FilterWrapper(com.jobits.pos.controller.filter.FilterType.IPV_E, ((Cocina) getBean().getElemento_seleccionado()).getCodCocina());
                break;
        }
        return ret;
    }

}
