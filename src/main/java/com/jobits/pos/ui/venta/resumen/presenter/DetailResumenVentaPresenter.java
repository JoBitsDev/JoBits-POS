/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.resumen.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.core.domain.models.temporal.DayReviewWrapper;
import com.jobits.pos.core.domain.models.temporal.ResumenFacadeRequest;
import com.jobits.pos.core.domain.models.temporal.TipoResumen;
import com.jobits.pos.main.Application;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.filter.presenter.FilterType;
import com.jobits.pos.utils.utils;
import com.root101.clean.core.app.services.utils.TipoNotificacion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

/**
 * @author Home
 */
public class DetailResumenVentaPresenter extends AbstractResumenViewPresenter<DetailResumenVentaModel> {


    public DetailResumenVentaPresenter() {
        super(new DetailResumenVentaModel(), false, "Resumen Venta General", "Resumen Venta Detallada",
                new ArrayList<FilterType>(Arrays.asList(
                        FilterType.INSUMO,
                        FilterType.COCINA,
                        FilterType.SECCION,
                        FilterType.AREA)));
    }

    @Override
    protected void setListsToBean() {
        var ret = service.getVentaResumen(utils.toLocalDate(getBean().getSince_date()), utils.toLocalDate(getBean().getTo_date()), getBean().getFilters());
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
        Optional<Boolean> precios = Application.getInstance().getNotificationService().showDialog("Desea Imprimir con percios los productos", TipoNotificacion.DIALOG_CONFIRM);
        service.printResumen(ResumenFacadeRequest.of(TipoResumen.VENTA, utils.toLocalDate(getBean().getSince_date()),
                utils.toLocalDate(getBean().getTo_date()), getBean().getFilters(), precios.orElse(true), null));
    }

}
