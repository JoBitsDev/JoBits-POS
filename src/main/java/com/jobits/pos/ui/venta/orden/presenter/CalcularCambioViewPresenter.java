/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.orden.presenter;

import com.jobits.pos.core.domain.models.Orden;
import com.jobits.pos.main.Application;
import com.jobits.pos.recursos.R;
import com.jobits.pos.servicios.impresion.Impresion;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.utils.utils;
import static com.jobits.pos.ui.venta.orden.presenter.CalcularCambioViewModel.*;
import java.beans.PropertyChangeEvent;
import java.util.Optional;

/**
 *
 * @author ERIK QUESADA
 */
public class CalcularCambioViewPresenter extends AbstractViewPresenter<CalcularCambioViewModel> {

    public static String ACTION_ABRIR_CAJA = "Abrir Caja";
    public static String ACTION_CERRAR = "Cerrar";
    Orden o;

    public CalcularCambioViewPresenter(Orden o) {
        super(new CalcularCambioViewModel());
        this.o = o;
        refreshState();
        addListeners();
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_ABRIR_CAJA) {
            @Override
            public Optional doAction() {
                Impresion.getDefaultInstance().forceDrawerKick();
                Application.getInstance().getNavigator().navigateUp();
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

    private void addListeners() {
        getBean().addPropertyChangeListener(PROP_ENTRADA_MONEDA_NACIONAL, (PropertyChangeEvent evt) -> {
            actualizarVuelto();
        }
        );
        getBean().addPropertyChangeListener(PROP_ENTRADA_SEGUNDA_MONEDA, (PropertyChangeEvent evt) -> {
            actualizarVuelto();
        }
        );
    }

    @Override
    protected Optional refreshState() {
        getBean().setCodigo_orden(o.toString());
        getBean().setTotal_a_pagar(R.formatoMoneda.format(o.getOrdenvalorMonetario()));
        getBean().setCambio(R.formatoMoneda.format(0));
        getBean().setEntrada_moneda_nacional(0);
        getBean().setEntrada_segunda_moneda(0);

        return super.refreshState(); //To change body of generated methods, choose Tools | Templates.
    }

    private void actualizarVuelto() {
        float mn = getBean().getEntrada_moneda_nacional();
        float cuc = getBean().getEntrada_segunda_moneda();
        float montoADevolver = o.getOrdenvalorMonetario();

        if (R.COIN_SUFFIX.contains("CUC")) {
//            float cuc = (float) jSpinnerCUC.getValue();
            montoADevolver -= cuc;
//            float mn = (int) jSpinnerMN.getValue();
            mn /= R.COINCHANGE;
            montoADevolver -= mn;
        } else {
//            float cuc = (float) jSpinnerCUC.getValue();
            utils.setDosLugaresDecimales(cuc *= R.COINCHANGE);
            montoADevolver -= cuc;

//            float mn = (int) jSpinnerMN.getValue();
            montoADevolver -= mn;
        }
        if (montoADevolver > 0) {
            getBean().setCambio("Falta " + utils.setDosLugaresDecimales(montoADevolver));
        } else {
            getBean().setCambio(utils.setDosLugaresDecimales(montoADevolver));
        }

    }
}
