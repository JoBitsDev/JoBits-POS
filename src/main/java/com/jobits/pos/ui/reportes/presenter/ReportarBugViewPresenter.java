/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.reportes.presenter;

import com.jobits.pos.controller.reportes.ReportarBugController;
import com.jobits.pos.controller.reportes.ReportarBugService;
import com.jobits.pos.cordinator.DisplayType;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.TipoNotificacion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.about.AcercaDeView;
import com.jobits.pos.ui.about.AcercaDeViewPresenter;
import static com.jobits.pos.ui.mainmenu.presenter.MenuBarClassPresenter.ACTION_SHOW_ACERCA_DE;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import java.util.Calendar;
import java.util.Optional;
import javax.swing.JOptionPane;

/**
 *
 * @author Home
 */
public class ReportarBugViewPresenter extends AbstractViewPresenter<ReportarBugViewModel> {

    public static final String ACTION_REPORTAR = "Reportar";
    public static final String ACTION_CERRAR = "Cerrar";

    ReportarBugService service;

    public ReportarBugViewPresenter(ReportarBugService service) {
        super(new ReportarBugViewModel());
        this.service = service;
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
