/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.gastos.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.gasto.GastoOperacionService;
import com.jobits.pos.controller.login.impl.LogInController;
import com.jobits.pos.core.domain.models.Venta;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.TipoNotificacion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.servicios.impresion.Impresion;
import com.jobits.pos.servicios.impresion.formatter.GastosFormatter;
import com.jobits.pos.ui.autorizo.AuthorizerImpl;
import static com.jobits.pos.ui.gastos.presenter.GastosViewModel.*;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.utils.utils;
import java.beans.PropertyChangeEvent;
import java.util.Arrays;
import java.util.Optional;
import javax.swing.JOptionPane;

/**
 *
 * @author Home
 */
public class GastosViewPresenter extends AbstractViewPresenter<GastosViewModel> {

    public static final String ACTION_IMPRIMIR_GASTOS = "Imprimir Gastos",
            ACTION_LIMPIAR = "Limpiar",
            ACTION_AGREGAR_GASTO = "Agregar Gasto",
            ACTION_ELIMINAR_GASTO = "Eliminar Gasto";

    private final GastoOperacionService service = PosDesktopUiModule.getInstance().getImplementation(GastoOperacionService.class);

    public GastosViewPresenter(Venta v) {
        super(new GastosViewModel());
        addListeners();
        setVenta(v);
    }

    public void setVenta(Venta v) {
        service.setDiaVenta(v);
        refreshState();
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_AGREGAR_GASTO) {
            @Override
            public Optional doAction() {
                onAgregarClick();
                return Optional.empty();
            }

        });
        registerOperation(new AbstractViewAction(ACTION_ELIMINAR_GASTO) {
            @Override
            public Optional doAction() {
                onEliminarClick();
                return Optional.empty();
            }

        });
        registerOperation(new AbstractViewAction(ACTION_IMPRIMIR_GASTOS) {
            @Override
            public Optional doAction() {
                onImprimirClick();
                return Optional.empty();
            }

        });
        registerOperation(new AbstractViewAction(ACTION_LIMPIAR) {
            @Override
            public Optional doAction() {
                onLimpiarClick();
                return Optional.empty();
            }

        });
    }

    @Override
    protected Optional refreshState() {
        getBean().setLista_categoria_gastos(new ArrayListModel<>(Arrays.asList(R.TipoGasto.values())));
        getBean().setCategoria_gasto_seleccionada(R.TipoGasto.UNSPECIFIED);
        getBean().setLista_gasto_venta(new ArrayListModel<>(service.getLista()));
        getBean().setTotal_gastos(utils.setDosLugaresDecimales(service.getValorTotalGastos()));
        return super.refreshState(); //To change body of generated methods, choose Tools | Templates.
    }

    private void onAgregarClick() {
        service.createNewGasto(
                getBean().getCategoria_gasto_seleccionada(),
                getBean().getTipo_gasto_seleccionado(),
                getBean().getMonto_gasto(),
                getBean().getDescripcion_gasto());
        refreshState();
        onLimpiarClick();
        Application.getInstance().getNotificationService().notify(R.RESOURCE_BUNDLE.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
    }

    private void onEliminarClick() {
        if (new LogInController(new AuthorizerImpl()).constructoAuthorizationView(R.NivelAcceso.ECONOMICO)) {
            service.removeGasto(getBean().getGasto_venta_seleccionado());
            refreshState();
            Application.getInstance().getNotificationService().notify(R.RESOURCE_BUNDLE.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
        }
    }

    private void onLimpiarClick() {
        getBean().setCategoria_gasto_seleccionada(R.TipoGasto.UNSPECIFIED);
        getBean().setLista_tipo_gasto(new ArrayListModel<>());
        getBean().setTipo_gasto_seleccionado(null);
        getBean().setMonto_gasto(0);
        getBean().setDescripcion_gasto(null);
    }

    private void onImprimirClick() {
        if (!getBean().getLista_gasto_venta().isEmpty()) {
            Impresion i = new Impresion();
            i.print(new GastosFormatter(getBean().getLista_gasto_venta()), null);
        } else {
            JOptionPane.showMessageDialog(Application.getInstance().getMainWindow(), "No hay gastos para imprimir");
        }
    }

    private void addListeners() {
        getBean().addPropertyChangeListener(PROP_CATEGORIA_GASTO_SELECCIONADA, (PropertyChangeEvent evt) -> {
            getBean().setLista_tipo_gasto(new ArrayListModel<>(service.getNombres(((R.TipoGasto) evt.getNewValue()).getNombre())));
        });
    }

}
