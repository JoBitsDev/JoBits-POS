/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.resumen.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.resumen.SalarioResumenService;
import com.jobits.pos.core.domain.models.ProductovOrden;
import com.jobits.pos.core.domain.models.temporal.DayReviewWrapper;
import com.jobits.pos.ui.filter.presenter.FilterViewPresenter;
import static com.jobits.pos.ui.filter.presenter.FilterViewPresenter.PROP_FILTERED;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractResumenViewPresenter;
import com.jobits.pos.utils.utils;
import java.beans.PropertyChangeEvent;
import java.util.function.Predicate;

/**
 *
 * @author Home
 */
public class DetailResumenSalarioViewPresenter extends AbstractResumenViewPresenter<DetailResumenAutorizoViewModel> {

    SalarioResumenService service = PosDesktopUiModule.getInstance().getImplementation(SalarioResumenService.class);

    public DetailResumenSalarioViewPresenter() {
        super(new DetailResumenAutorizoViewModel(), false, "Resumen de Salarios General", "Resumen de Salarios Detallado");
        setFilterPresenter();
    }

    @Override
    protected void setListsToBean() {
        service.createVentaResumen(getBean().getSince_date(), getBean().getTo_date());
        getBean().setListaMain(new ArrayListModel<>(service.getResumenGeneral()));
        getBean().setListaDetail(new ArrayListModel<>(service.getResumenDetallado()));
    }

    public float getTotal() {
        float total = 0;
        for (DayReviewWrapper x : getBean().getListaMain()) {
            total += x.getTotal();
        }
        return utils.setDosLugaresDecimalesFloat(total);
    }

    @Override
    protected void registerOperations() {
    }

    private void setFilterPresenter() {
        getBean().setFilter_presenter(new FilterViewPresenter<>());
        getBean().getFilter_presenter().addPropertyChangeListener(PROP_FILTERED, (PropertyChangeEvent evt) -> {
            ArrayListModel a = getBean().getListaDetail();
            a.stream().filter((Predicate) evt.getNewValue());
            getBean().setListaDetail(a);
        });
    }

}
