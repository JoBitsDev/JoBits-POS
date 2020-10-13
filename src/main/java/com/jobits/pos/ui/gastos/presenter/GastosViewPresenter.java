/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.gastos.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.gasto.GastoOperacionController;
import com.jobits.pos.domain.models.GastoVenta;
import com.jobits.pos.exceptions.ValidatingException;
import com.jobits.pos.main.Application;
import com.jobits.pos.recursos.R;
import com.jobits.pos.servicios.impresion.Impresion;
import com.jobits.pos.servicios.impresion.formatter.GastosFormatter;
import static com.jobits.pos.ui.gastos.presenter.GastosViewModel.*;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.utils.utils;
import java.beans.PropertyChangeEvent;
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

    private GastoOperacionController service;

    public GastosViewPresenter(GastoOperacionController service) {
        super(new GastosViewModel());
        this.service = service;
        addBeanData();
        addListeners();
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

    private void addBeanData() {
        getBean().setCategoria_gasto_seleccionada(R.TipoGasto.UNSPECIFIED);
        getBean().getLista_gasto_venta().clear();
        getBean().getLista_gasto_venta().addAll(service.getLista());
        if (!getBean().getLista_gasto_venta().isEmpty()) {
            float a = 0;
            a = getBean().getLista_gasto_venta().stream().map((gastoVenta) -> gastoVenta.getImporte()).reduce(a, (accumulator, _item) -> accumulator + _item);
            getBean().setTotal_gastos(utils.setDosLugaresDecimales(a));
        } else {
            getBean().setTotal_gastos(utils.setDosLugaresDecimales(0));
        }
    }

    private void onAgregarClick() {
        if (getBean().getTipo_gasto_seleccionado() != null && getBean().getCategoria_gasto_seleccionada() != null) {
            service.createNewGasto(
                    getBean().getCategoria_gasto_seleccionada(),
                    getBean().getTipo_gasto_seleccionado(),
                    getBean().getMonto_gasto(),
                    getBean().getDescripcion_gasto());
            addBeanData();
            onLimpiarClick();
        } else {
            JOptionPane.showMessageDialog(Application.getInstance().getMainWindow(), "Rellene todos los campos obligatorios");
        }
    }

    private void onEliminarClick() {
        if (getBean().getGasto_venta_seleccionado() == null) {
            JOptionPane.showMessageDialog(Application.getInstance().getMainWindow(), "Seleccione un gasto primero");
        } else {
            service.removeGasto(getBean().getGasto_venta_seleccionado());
            addBeanData();
        }
    }

    private void onLimpiarClick() {
        getBean().setCategoria_gasto_seleccionada(R.TipoGasto.UNSPECIFIED);
        getBean().setTipo_gasto_seleccionado(null);
        getBean().setMonto_gasto(0);
        getBean().setDescripcion_gasto(null);
    }

    private void onImprimirClick() {
        
        if (!getBean().getLista_gasto_venta().isEmpty()) {
            Impresion i = new Impresion();
            i.print(new GastosFormatter(getBean().getLista_gasto_venta()), null);
        }else{
            JOptionPane.showMessageDialog(Application.getInstance().getMainWindow(), "No hay gastos para imprimir");
        }
    }

    private void addListeners() {
        getBean().addPropertyChangeListener(PROP_CATEGORIA_GASTO_SELECCIONADA, (PropertyChangeEvent evt) -> {
            if (getBean().getCategoria_gasto_seleccionada() != null) {
                getBean().getLista_tipo_gasto().clear();
                getBean().getLista_tipo_gasto().addAll(
                        service.getNombres(getBean().getCategoria_gasto_seleccionada().getNombre()));
            } else {
                getBean().getLista_tipo_gasto().clear();
            }
        });
    }

}
