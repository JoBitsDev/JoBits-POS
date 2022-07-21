/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.resumen.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.controller.resumen.VentaResumenService;
import com.jobits.pos.core.domain.models.temporal.DayReviewWrapper;
import com.jobits.pos.main.Application;
import com.jobits.pos.recursos.R;
import com.jobits.pos.servicios.impresion.Impresion;
import com.jobits.pos.servicios.impresion.formatter.VentaResumenFormatter;
import com.jobits.pos.ui.filter.presenter.FilterType;
import com.jobits.pos.utils.utils;
import com.root101.clean.core.app.services.utils.TipoNotificacion;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

/**
 *
 * @author Home
 */
public class DetailResumenVentaPresenter extends AbstractResumenViewPresenter<DetailResumenVentaModel> {

    VentaResumenService service = PosDesktopUiModule.getInstance().getImplementation(VentaResumenService.class);

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
        Impresion i = new Impresion();
        Optional<Boolean> precios = Application.getInstance().getNotificationService().showDialog("Desea Imprimir con percios los productos",TipoNotificacion.DIALOG_CONFIRM);
        i.print(VentaResumenFormatter.of(getBean().getSince_date(),
                getBean().getTo_date(), getBean().getListaDetail(), precios.orElse(true)), null);
    }

}
