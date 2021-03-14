/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.resumen.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.resumen.AutorizoResumenService;
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
public class DetailResumenAutorizoViewPresenter extends AbstractResumenViewPresenter<DetailResumenAutorizoViewModel> {

    AutorizoResumenService service = PosDesktopUiModule.getInstance().getImplementation(AutorizoResumenService.class);

    public DetailResumenAutorizoViewPresenter() {
        super(new DetailResumenAutorizoViewModel(), false, "Resumen Autorizo General", "Resumen Autorizo Detallado",
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
    protected void registerOperations() {
    }

}
