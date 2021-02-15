/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.resumen.presenter;

import com.jobits.pos.main.Application;
import com.jobits.pos.notification.TipoNotificacion;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import static com.jobits.pos.ui.venta.resumen.presenter.ResumenMainViewModel.*;
import com.jobits.pos.utils.utils;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Home
 */
public class ResumenMainViewPresenter extends AbstractViewPresenter<ResumenMainViewModel> {

    public static final String ACTION_CARGAR_RESUMEN = "Cargar Resumen";
    public static final String ACTION_TO_MAIN_PANEL = "Panel Principal";
    public static final String ACTION_TO_DETAILS_VENTA = "Detalles de Venta";
    public static final String ACTION_TO_DETAILS_AUTORIZO = "Detalles de Autorizo";
    public static final String ACTION_TO_DETAILS_COSTO = "Detalles de Costos";
    public static final String ACTION_TO_DETAILS_SALARIO = "Detalles de Salarios";
    public static final String ACTION_TO_DETAILS_GASTO = "Detalles de Gastos";

    private DetailResumenVentaPresenter presenterVenta = new DetailResumenVentaPresenter();
    private DetailResumenAutorizoViewPresenter presenterAutorizo = new DetailResumenAutorizoViewPresenter();
    private DetailResumenCostoViewPresenter presenterCosto = new DetailResumenCostoViewPresenter();
    private DetailResumenSalarioViewPresenter presenterSalario = new DetailResumenSalarioViewPresenter();
    private DetailResumenGastoViewPresenter presenterGasto = new DetailResumenGastoViewPresenter();

    public ResumenMainViewPresenter() {
        super(new ResumenMainViewModel());
        initListeners();
        refreshState();
    }

    public DetailResumenVentaPresenter getPresenterVenta() {
        return presenterVenta;
    }

    public DetailResumenAutorizoViewPresenter getPresenterAutorizo() {
        return presenterAutorizo;
    }

    public DetailResumenCostoViewPresenter getPresenterCosto() {
        return presenterCosto;
    }

    public DetailResumenSalarioViewPresenter getPresenterSalario() {
        return presenterSalario;
    }

    public DetailResumenGastoViewPresenter getPresenterGasto() {
        return presenterGasto;
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_CARGAR_RESUMEN) {
            @Override
            public Optional doAction() {
                cargarResumen();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_TO_MAIN_PANEL) {
            @Override
            public Optional doAction() {
                toMainPanel();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_TO_DETAILS_VENTA) {
            @Override
            public Optional doAction() {
                toDetailsVenta();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_TO_DETAILS_AUTORIZO) {
            @Override
            public Optional doAction() {
                toDetailsAutorizo();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_TO_DETAILS_COSTO) {
            @Override
            public Optional doAction() {
                toDetailsCosto();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_TO_DETAILS_SALARIO) {
            @Override
            public Optional doAction() {
                toDetailsSalario();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_TO_DETAILS_GASTO) {
            @Override
            public Optional doAction() {
                toDetailsGasto();
                return Optional.empty();
            }
        });
    }

    private void toMainPanel() {
        getBean().setControls_visibility(false);
        firePropertyChange("TO_MAIN_PANEL", null, true);
    }

    private void toDetailsVenta() {
        getBean().setControls_visibility(true);
        firePropertyChange("TO_DETAILS_VENTA", null, true);
    }

    private void toDetailsAutorizo() {
        getBean().setControls_visibility(true);
        firePropertyChange("TO_DETAILS_AUTORIZO", null, true);
    }

    private void toDetailsCosto() {
        getBean().setControls_visibility(true);
        firePropertyChange("TO_DETAILS_COSTO", null, true);
    }

    private void toDetailsSalario() {
        getBean().setControls_visibility(true);
        firePropertyChange("TO_DETAILS_SALARIO", null, true);
    }

    private void toDetailsGasto() {
        getBean().setControls_visibility(true);
        firePropertyChange("TO_DETAILS_GASTO", null, true);
    }

    private void refreshPresenters() {
        presenterVenta.getBean().setSince_date(getBean().getFecha_desde());
        presenterVenta.getBean().setTo_date(getBean().getFecha_hasta());
        presenterVenta.setListsToBean();
        presenterAutorizo.getBean().setSince_date(getBean().getFecha_desde());
        presenterAutorizo.getBean().setTo_date(getBean().getFecha_hasta());
        presenterAutorizo.setListsToBean();
        presenterCosto.getBean().setSince_date(getBean().getFecha_desde());
        presenterCosto.getBean().setTo_date(getBean().getFecha_hasta());
        presenterCosto.setListsToBean();
        presenterSalario.getBean().setSince_date(getBean().getFecha_desde());
        presenterSalario.getBean().setTo_date(getBean().getFecha_hasta());
        presenterSalario.setListsToBean();
        presenterGasto.getBean().setSince_date(getBean().getFecha_desde());
        presenterGasto.getBean().setTo_date(getBean().getFecha_hasta());
        presenterGasto.setListsToBean();
    }

    @Override
    protected Optional refreshState() {
        return super.refreshState(); //To change body of generated methods, choose Tools | Templates.
    }

    private void cargarResumen() {
        Application.getInstance().getBackgroundWorker().processInBackground("Cargando Resumen...", () -> {
            refreshPresenters();
            float venta = presenterVenta.getTotal();
            float costo = presenterCosto.getTotal();
            float salario = presenterSalario.getTotal();
            float gasto = presenterGasto.getTotal();
            getBean().setTotal_autorizos(String.valueOf(presenterAutorizo.getTotal()));
            getBean().setTotal_venta(String.valueOf(venta));
            getBean().setTotal_costos(String.valueOf(costo));
            getBean().setTotal_salarios(String.valueOf(salario));
            getBean().setTotal_gastos(String.valueOf(gasto));
            float utilidad = venta - (costo + salario + gasto);
            getBean().setTotal_utilidades(String.valueOf(utilidad));
            getBean().setCosto_peso(String.valueOf(utils.setDosLugaresDecimalesFloat(utilidad / venta)));
            if (utilidad > 0) {
                getBean().setProfits_icon(new ImageIcon(getClass().getResource("/restManager/resources/icons pack/ganancia_verde_2.png")));
            } else if (utilidad < 0) {
                getBean().setProfits_icon(new ImageIcon(getClass().getResource("/restManager/resources/icons pack/perdida_rojo_2.png")));
            } else if (utilidad == 0) {
                getBean().setProfits_icon(new ImageIcon(getClass().getResource("/restManager/resources/icons pack/neutral_negro.png")));
            }

        });
        firePropertyChange("REFRESH_STATE_EXECUTED", null, true);
    }

    private void initListeners() {
        getBean().addPropertyChangeListener((PropertyChangeEvent evt) -> {
            switch (evt.getPropertyName()) {
                case PROP_FECHA_DESDE:
                    getBean().setFecha_hasta((Date) evt.getNewValue());
                    break;
                case PROP_FECHA_HASTA:
                    LocalDate inicio = Instant.ofEpochMilli(getBean().getFecha_desde().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate fin = Instant.ofEpochMilli(((Date) evt.getNewValue()).getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
                    if (fin.isBefore(inicio)) {
                        Application.getInstance().getNotificationService().showDialog(
                                "La fecha final no puede ser anterior a la fecha inicial", TipoNotificacion.ERROR);
                        getBean().setFecha_hasta(getBean().getFecha_desde());
                    }
                    break;
            }
        });
    }
}
