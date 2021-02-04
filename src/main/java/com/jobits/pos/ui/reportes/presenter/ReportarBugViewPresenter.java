/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.reportes.presenter;

import com.jobits.pos.controller.reportes.ReportarBugService;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.TipoNotificacion;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import java.util.Calendar;
import java.util.Optional;

/**
 *
 * @author Home
 */
public class ReportarBugViewPresenter extends AbstractViewPresenter<ReportarBugViewModel> {

    public static final String ACTION_REPORTAR = "Reportar";
    public static final String ACTION_CERRAR = "Cerrar";

    private final ReportarBugService service = PosDesktopUiModule.getInstance().getImplementation(ReportarBugService.class);

    public ReportarBugViewPresenter() {
        super(new ReportarBugViewModel());
        if (Application.getInstance().getLoggedUser() != null) {
            getBean().setUsuario_loggeado(Application.getInstance().getLoggedUser().getUsuario());
        }
        updateBeanData();
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_REPORTAR) {
            @Override
            public Optional doAction() {
                onReportarClick();
                updateBeanData();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_CERRAR) {
            @Override
            public Optional doAction() {
                Application.getInstance().getNavigator().navigateUp();
                return Optional.empty();
            }
        });

    }

    private void onReportarClick() {
        String user = getBean().getUsuario_loggeado();
        String titile = getBean().getTitulo_reporte();
        String description = getBean().getDescripcion_reporte();
        String fecha = getBean().getFecha();
        service.guardarReporte(user, titile, description, fecha);
        Application.getInstance().getNotificationService().notify("Se ha registrado el reporte", TipoNotificacion.SUCCESS);
    }

    private void updateBeanData() {
        String fecha = Integer.toString(Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
                + "-" + Integer.toString(Calendar.getInstance().get(Calendar.MONTH) + 1)
                + "-" + Integer.toString(Calendar.getInstance().get(Calendar.YEAR))
                + " " + Integer.toString(Calendar.getInstance().get(Calendar.HOUR))
                + ":" + Integer.toString(Calendar.getInstance().get(Calendar.MINUTE));
        getBean().setFecha(fecha);
        getBean().setDescripcion_reporte(null);
        getBean().setTitulo_reporte(null);
    }

}
