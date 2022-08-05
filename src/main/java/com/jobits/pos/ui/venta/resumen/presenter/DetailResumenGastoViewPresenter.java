/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.resumen.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.resumen.GastoResumenService;
import com.jobits.pos.core.domain.models.temporal.DayReviewWrapper;
import com.jobits.pos.recursos.R;
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
        var ret = service.createVentaResumen(utils.toLocalDate(getBean().getSince_date()), utils.toLocalDate(getBean().getTo_date()));
        getBean().setListaMain(new ArrayListModel<>(ret.getMainList()));
        getBean().setListaDetail(new ArrayListModel<>(ret.getDetailList()));
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
        throw new UnsupportedOperationException("En Desarrollo"); //To change body of generated methods, choose Tools | Templates.
    }

}
