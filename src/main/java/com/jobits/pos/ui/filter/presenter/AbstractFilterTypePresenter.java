/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.filter.presenter;

import com.jobits.pos.core.domain.models.Cocina;
import com.jobits.pos.core.domain.models.Insumo;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.core.domain.VentaDAO1Filters;
import com.jobits.pos.core.domain.models.Area;
import com.jobits.pos.core.domain.models.ProductoVenta;
import com.jobits.pos.core.domain.models.Seccion;
import static com.jobits.pos.ui.filter.presenter.FilterViewPresenter.ACTION_ADD_FILTER;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import java.util.Optional;
import java.util.function.Predicate;

/**
 *
 * @author Home
 */
public class AbstractFilterTypePresenter extends AbstractViewPresenter<AbstractFilterTypeModel> {

    public static final String ACTION_AUTO_ELIMINAR_FILTRO = "Eliminar Filtro";

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
    }

    private void onSelfDestruct() {
        firePropertyChange(ACTION_AUTO_ELIMINAR_FILTRO, null, this);
    }

    public Predicate createPredicate() {
        Predicate ret = null;
        switch (getBean().getTipo_filtro()) {
            case COCINA:
                ret = VentaDAO1Filters.filtroCocina((Cocina) getBean().getElemento_seleccionado());
                break;
            case INSUMO:
                ret = VentaDAO1Filters.filtroInsumo((Insumo) getBean().getElemento_seleccionado());
                break;
            case AREA:
                ret = VentaDAO1Filters.filtroArea((Area) getBean().getElemento_seleccionado());
                break;
            case IPV:
                ret = VentaDAO1Filters.filtroIPV((Cocina) getBean().getElemento_seleccionado());
                break;
            case PRODUCTO:
                ret = VentaDAO1Filters.filtroProducto((ProductoVenta) getBean().getElemento_seleccionado());
                break;
            case SECCION:
                ret = VentaDAO1Filters.filtroSeccion((Seccion) getBean().getElemento_seleccionado());
                break;
        }
        return ret;
    }

}
