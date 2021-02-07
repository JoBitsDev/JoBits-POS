/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.resumen.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.resumen.VentaResumenService;
import com.jobits.pos.core.domain.models.temporal.DiaVentaWrapper;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractResumenViewPresenter;

/**
 *
 * @author Home
 */
public class DetailResumenVentaPresenter extends AbstractResumenViewPresenter<DetailResumenVentaModel> {

    VentaResumenService service = PosDesktopUiModule.getInstance().getImplementation(VentaResumenService.class);

    public DetailResumenVentaPresenter() {
        super(new DetailResumenVentaModel(), false, "Resumen Venta General", "Resumen Venta Detallada");
    }

    @Override
    protected void setListsToBean() {
        service.createVentaResumen(getBean().getSince_date(), getBean().getTo_date());
        getBean().setListaMain(new ArrayListModel<>(service.getResumenGeneral()));
        getBean().setListaDetail(new ArrayListModel<>(service.getResumenDetallado()));
    }

    public float getTotal() {
        float total = 0;
        for (DiaVentaWrapper x : getBean().getListaMain()) {
            total += x.getTotal();
        }
        return total;
    }

    @Override
    protected void registerOperations() {
    }

}
