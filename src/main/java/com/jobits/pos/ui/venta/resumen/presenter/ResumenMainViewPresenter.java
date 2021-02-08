/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.resumen.presenter;

import com.jobits.pos.main.Application;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import static com.jobits.pos.ui.venta.resumen.presenter.ResumenMainViewModel.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Optional;

/**
 *
 * @author Home
 */
public class ResumenMainViewPresenter extends AbstractViewPresenter<ResumenMainViewModel> {

    public static final String ACTION_TO_MAIN_PANEL = "Panel Principal";
    public static final String ACTION_TO_DETAILS_VENTA = "Detalles de Venta";

    private DetailResumenVentaPresenter presenterVenta = new DetailResumenVentaPresenter();

    public ResumenMainViewPresenter() {
        super(new ResumenMainViewModel());
        initListeners();
        refreshState();
    }

    public DetailResumenVentaPresenter getPresenterVenta() {
        return presenterVenta;
    }

    @Override
    protected void registerOperations() {
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
    }

    private void toMainPanel() {
        getBean().setControls_visibility(false);
        firePropertyChange("TO_MAIN_PANEL", null, true);
    }

    private void toDetailsVenta() {
        getBean().setControls_visibility(true);
        firePropertyChange("TO_DETAILS_VENTA", null, true);
    }

    private void refreshPresenters() {
        presenterVenta.getBean().setSince_date(getBean().getFecha_desde());
        presenterVenta.getBean().setTo_date(getBean().getFecha_hasta());
        presenterVenta.setListsToBean();
    }

    @Override
    protected Optional refreshState() {
        Application.getInstance().getBackgroundWorker().processInBackground("Cargando Resumen...", () -> {
            refreshPresenters();
            getBean().setTotal_venta(String.valueOf(presenterVenta.getTotal()));
        });
        firePropertyChange("REFRESH_STATE_EXECUTED", null, true);
        return super.refreshState(); //To change body of generated methods, choose Tools | Templates.
    }

    private void initListeners() {
        getBean().addPropertyChangeListener((PropertyChangeEvent evt) -> {
            switch (evt.getPropertyName()) {
                case PROP_FECHA_DESDE:
                case PROP_FECHA_HASTA:
                    refreshState();
                    break;
            }
        });
    }
}
