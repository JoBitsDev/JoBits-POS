/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.resumen.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.resumen.GastoResumenService;
import com.jobits.pos.core.domain.models.temporal.DayReviewWrapper;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.utils.utils;

/**
 *
 * @author Home
 */
public class DetailResumenGastoViewPresenter extends AbstractResumenViewPresenter<DetailResumenGastoViewModel> {

    GastoResumenService service = PosDesktopUiModule.getInstance().getImplementation(GastoResumenService.class);

    public DetailResumenGastoViewPresenter() {
        super(new DetailResumenGastoViewModel(), false, "Resumen de Gastos General", "Resumen de Gastos Detallado");
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

}
