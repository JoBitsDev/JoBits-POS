/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.resumen.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.resumen.CostosResumenService;
import com.jobits.pos.core.domain.models.temporal.DayReviewWrapper;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.filter.presenter.FilterType;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.utils.utils;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Home
 */
public class DetailResumenCostoViewPresenter extends AbstractResumenViewPresenter<DetailResumenCostoViewModel> {

    CostosResumenService service = PosDesktopUiModule.getInstance().getImplementation(CostosResumenService.class);

    public DetailResumenCostoViewPresenter() {
        super(new DetailResumenCostoViewModel(), false, "Resumen de Costos General", "Resumen de Costos Detallado",
                new ArrayList<FilterType>(Arrays.asList(FilterType.PRODUCTO_E,
                        FilterType.IPV_E,
                        FilterType.COCINA_E)));
    }

    @Override
    protected void setListsToBean() {
        service.createVentaResumen(getBean().getSince_date(), getBean().getTo_date());
        getBean().setListaMain(new ArrayListModel<>(service.getResumenGeneral()));
        getBean().setListaDetail(new ArrayListModel<>(service.getResumenDetallado()));
        getBean().setTotal_resumen(getTotal() + R.COIN_SUFFIX);
        setView(getBean().getListaMain().size() == 1);
    }

    @Override
    public float getTotal() {
        float total = 0;
        for (DayReviewWrapper x : getBean().getListaMain()) {
            total += x.getTotal();
        }
        return utils.setDosLugaresDecimalesFloat(total);
    }

    @Override
    protected void printToTicketPrinter() {
        throw new UnsupportedOperationException("En desarrollo"); //To change body of generated methods, choose Tools | Templates.
    }

    
}
