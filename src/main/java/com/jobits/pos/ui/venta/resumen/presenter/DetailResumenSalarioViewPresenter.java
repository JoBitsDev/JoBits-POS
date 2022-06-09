/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.resumen.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.resumen.SalarioResumenService;
import com.jobits.pos.core.domain.models.temporal.DayReviewWrapper;
import com.jobits.pos.recursos.R;
import com.jobits.pos.servicios.impresion.Impresion;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.utils.utils;

/**
 *
 * @author Home
 */
public class DetailResumenSalarioViewPresenter extends AbstractResumenViewPresenter<DetailResumenAutorizoViewModel> {

    SalarioResumenService service = PosDesktopUiModule.getInstance().getImplementation(SalarioResumenService.class);

    public DetailResumenSalarioViewPresenter() {
        super(new DetailResumenAutorizoViewModel(), false, "Resumen de Salarios General", "Resumen de Salarios Detallado");
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
        throw new UnsupportedOperationException("En desarrollo");
    }

}
