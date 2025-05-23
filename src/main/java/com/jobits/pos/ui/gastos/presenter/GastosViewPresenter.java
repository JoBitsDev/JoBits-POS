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
import com.jobits.pos.core.domain.models.temporal.DefaultGasto;
import com.jobits.pos.main.Application;
import com.jobits.pos.recursos.R;
import com.jobits.pos.servicios.impresion.Impresion;
import com.jobits.pos.servicios.impresion.formatter.GastosFormatter;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.utils.utils;
import com.root101.clean.core.app.services.utils.TipoNotificacion;
import com.root101.clean.core.domain.services.ResourceHandler;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.util.Arrays;
import java.util.Optional;

import static com.jobits.pos.ui.gastos.presenter.GastosViewModel.PROP_CATEGORIA_GASTO_SELECCIONADA;
import static com.jobits.pos.ui.gastos.presenter.GastosViewModel.PROP_DEFAULT_GASTO_SELECCIONADO;

/**
 * @author Home
 */
public class GastosViewPresenter extends AbstractViewPresenter<GastosViewModel> {

    public static final String ACTION_IMPRIMIR_GASTOS = "Imprimir Gastos",
            ACTION_LIMPIAR = "Limpiar",
            ACTION_AGREGAR_DEFAULT_GASTO = "Agregar Default Gasto",
            ACTION_ELIMINAR_DEFAULT_GASTO = "Eliminar Default Gasto",
            ACTION_AGREGAR_GASTO = "Agregar Gasto",
            ACTION_ELIMINAR_GASTO = "Eliminar Gasto";

    private final GastoOperacionService service = PosDesktopUiModule.getInstance().getImplementation(GastoOperacionService.class);
    private Venta venta;

    public GastosViewPresenter(Venta v) {
        super(new GastosViewModel());
        this.venta = v;
        addListeners();
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
        registerOperation(new AbstractViewAction(ACTION_AGREGAR_DEFAULT_GASTO) {
            @Override
            public Optional doAction() {
                onAgregarDefaultGasto();
                return Optional.empty();
            }

        });
        registerOperation(new AbstractViewAction(ACTION_ELIMINAR_DEFAULT_GASTO) {
            @Override
            public Optional doAction() {
                onEliminarDefaultGasto();
                return Optional.empty();
            }

        });
    }

    @Override
    protected Optional refreshState() {
        getBean().setLista_categoria_gastos(new ArrayListModel<>(Arrays.asList(R.TipoGasto.values())));
        getBean().setCategoria_gasto_seleccionada(R.TipoGasto.UNSPECIFIED);
        getBean().setLista_gasto_venta(new ArrayListModel<>(venta.getGastoVentaList()));
        getBean().setTotal_gastos(utils.setDosLugaresDecimales(service.getValorTotalGastos(venta.getId())));
        getBean().setDefault_gasto_list(new ArrayListModel<>(service.getDefaultGastosList()));
        getBean().setTipo_gasto(null);
        return super.refreshState(); //To change body of generated methods, choose Tools | Templates.
    }

    private void onAgregarClick() {
        var ret = service.createGasto(
                getBean().getCategoria_gasto_seleccionada(),
                getBean().getTipo_gasto(),
                Float.parseFloat(getBean().getMonto_gasto()),
                getBean().getDescripcion_gasto(), venta.getId());
        venta.getGastoVentaList().add(ret);
        refreshState();
        onLimpiarClick();
        Application.getInstance().getNotificationService().notify(ResourceHandler.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
    }

    private void onEliminarClick() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Esta seguro que desea eliminar el gasto seleccionado?",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            if (new LogInController().constructoAuthorizationView(R.NivelAcceso.ECONOMICO)) {//TODO: inyectar
                var aux = getBean().getGasto_venta_seleccionado().getGastoVentaPK();
                var ret = service.removeGasto(aux.getGastocodGasto(), aux.getVentafecha());
                getBean().setGasto_venta_seleccionado(null);
                venta.getGastoVentaList().remove(ret);
                refreshState();
                Application.getInstance().getNotificationService().notify(ResourceHandler.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
            }
        }
    }

    private void onLimpiarClick() {
        getBean().setCategoria_gasto_seleccionada(R.TipoGasto.UNSPECIFIED);
        getBean().setLista_tipo_gasto(new ArrayListModel<>());
        getBean().setTipo_gasto_seleccionado(null);
        getBean().setMonto_gasto(null);
        getBean().setDescripcion_gasto(null);
    }

    private void onImprimirClick() {
        if (!getBean().getLista_gasto_venta().isEmpty()) {
            Impresion i = Impresion.getDefaultInstance();
            i.print(new GastosFormatter(getBean().getLista_gasto_venta()), null);
        } else {
            JOptionPane.showMessageDialog(Application.getInstance().getMainWindow(), "No hay gastos para imprimir");
        }
    }

    private void onAgregarDefaultGasto() {
        String alias = getBean().getDefault_gasto_seleccionado() == null
                ? JOptionPane.showInputDialog(null, "Introduzca el identificador de la plantilla")
                : JOptionPane.showInputDialog(null, "Introduzca el nuevo identificador de la plantilla",
                getBean().getDefault_gasto_seleccionado().getAlias());
        DefaultGasto gasto = service.agregarDefaultGasto(
                alias,
                getBean().getCategoria_gasto_seleccionada(),
                getBean().getTipo_gasto_seleccionado(),
                Float.parseFloat(getBean().getMonto_gasto()),
                getBean().getDescripcion_gasto());
        getBean().setDefault_gasto_list(new ArrayListModel<>(service.getDefaultGastosList()));
        getBean().setDefault_gasto_seleccionado(gasto);
    }

    private void onEliminarDefaultGasto() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Esta seguro que desea eliminar la plantilla seleccionada?",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            service.eliminarDefaultGasto(getBean().getDefault_gasto_seleccionado());
            getBean().setDefault_gasto_list(new ArrayListModel<>(service.getDefaultGastosList()));
            getBean().setDefault_gasto_seleccionado(null);
        }
    }

    private void addListeners() {
        getBean().addPropertyChangeListener((PropertyChangeEvent evt) -> {
            switch (evt.getPropertyName()) {
                case PROP_CATEGORIA_GASTO_SELECCIONADA:
                    getBean().setLista_tipo_gasto(new ArrayListModel<>(service.getNombresByTipo(((R.TipoGasto) evt.getNewValue()).getNombre())));
                    break;
                case PROP_DEFAULT_GASTO_SELECCIONADO:
                    DefaultGasto gasto = (DefaultGasto) evt.getNewValue();
                    if (gasto != null) {
                        getBean().setCategoria_gasto_seleccionada(gasto.getCat());
                        getBean().setLista_tipo_gasto(new ArrayListModel<>(service.getNombresByTipo(gasto.getCat().getNombre())));
                        getBean().setTipo_gasto_seleccionado(gasto.getNombre());
                        getBean().setDescripcion_gasto(gasto.getDescripcion());
                        getBean().setMonto_gasto(String.valueOf(gasto.getMonto()));
                    }
                    break;
            }
        });
    }

}
