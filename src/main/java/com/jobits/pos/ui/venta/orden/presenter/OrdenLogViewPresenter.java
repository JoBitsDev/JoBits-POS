/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.orden.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.adapters.repo.impl.OrdenLogRepo;
import com.jobits.pos.controller.logs.OrdenLogController;
import com.jobits.pos.controller.logs.OrdenLogService;
import com.jobits.pos.main.Application;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Home
 */
public class OrdenLogViewPresenter extends AbstractViewPresenter<OrdenLogViewModel> {

    public static String ACTION_CERRAR = "Cerrar";

    String codOrden;

    public OrdenLogViewPresenter(String codOrden) {
        super(new OrdenLogViewModel());
        this.codOrden = codOrden;
        fillBeanData();
//        getBean().setCodigo_orden(codOrden);
//        getBean().setLog_list(new ArrayListModel<>(logList));
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_CERRAR) {
            @Override
            public Optional doAction() {
                Application.getInstance().getNavigator().navigateUp();
                return Optional.empty();
            }
        });
    }

    private void fillBeanData() {
        getBean().setCodigo_orden(codOrden);
        getBean().getLog_list().clear();
        OrdenLogService service = new OrdenLogController();
        getBean().getLog_list().addAll(service.loadLogFile(codOrden));//TODO: falta controller

    }

}
