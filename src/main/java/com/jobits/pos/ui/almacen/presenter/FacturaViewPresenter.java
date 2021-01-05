/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.almacen.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.almacen.AlmacenManageController;
import com.jobits.pos.controller.almacen.AlmacenManageController.CheckBoxType;
import com.jobits.pos.domain.TransaccionSimple;
import com.jobits.pos.domain.models.Almacen;
import com.jobits.pos.domain.models.Cocina;
import com.jobits.pos.domain.models.Insumo;
import com.jobits.pos.domain.models.InsumoAlmacen;
import com.jobits.pos.domain.models.InsumoElaborado;
import com.jobits.pos.domain.models.TransaccionTransformacion;
import com.jobits.pos.exceptions.UnExpectedErrorException;
import com.jobits.pos.main.Application;
import com.jobits.pos.recursos.R;
import static com.jobits.pos.ui.almacen.presenter.FacturaViewModel.*;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.utils.utils;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.swing.JOptionPane;

/**
 *
 * @author Home
 */
public class FacturaViewPresenter extends AbstractViewPresenter<FacturaViewModel> {

    private AlmacenManageController controller;
    public static final String ACTION_AGREGAR_INSUMO = "Agregar Insumo";
    public static final String ACTION_ELIMINAR_INSUMO = "Eliminar Insumo";

    public static final String ACTION_CERRAR_POPUP = "Cerrar Popup";
    public static final String ACTION_CONFIRMAR_TRANSACCION = "Confirmar";

    public static final String ACTION_ELIMINAR_INSUMO_TRANSFORMADO = "Agregar Insumo Transformado";
    public static final String ACTION_AGREGAR_INSUMO_TRANSFORMADO = "Eliminar Insumo Transformado";

    public FacturaViewPresenter(AlmacenManageController controller) {
        super(new FacturaViewModel());
        this.controller = controller;
        addListeners();
        getBean().getLista_insumos_disponibles().clear();
        getBean().getLista_insumos_disponibles().addAll(new ArrayListModel<>(controller.getInsumoAlmacenList(controller.getInstance())));
        setVisiblePanels(getBean().getOperacion_selected());
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_AGREGAR_INSUMO) {
            @Override
            public Optional doAction() {
                if (getBean().getInsumo_selecionado() == null) {
                    JOptionPane.showMessageDialog(Application.getInstance().getMainWindow(), "Seleccione un Insumo primero");
                } else {
                    addTransaction(getBean().getOperacion_selected());
                }
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_ELIMINAR_INSUMO) {
            @Override
            public Optional doAction() {
                getBean().getLista_elementos().remove(getBean().getElemento_seleccionado());
                if (getBean().getLista_elementos().isEmpty()) {
                    getBean().setComponent_locked(true);
                }
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_CERRAR_POPUP) {
            @Override
            public Optional doAction() {
                Application.getInstance().getNavigator().navigateUp();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_CONFIRMAR_TRANSACCION) {
            @Override
            public Optional doAction() {
                confirmarTransaccion(getBean().getOperacion_selected());
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_ELIMINAR_INSUMO_TRANSFORMADO) {
            @Override
            public Optional doAction() {
                getBean().getLista_insumos_transformados_contenidos().remove(
                        getBean().getInsumo_transformado_contenido_seleccionado());
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_AGREGAR_INSUMO_TRANSFORMADO) {
            @Override
            public Optional doAction() {
                if (getBean().getInsumo_elaborado_disponible_seleccionado() == null) {
                    JOptionPane.showMessageDialog(Application.getInstance().getMainWindow(), "Seleccione un Insumo primero derivado");
                } else {
                    TransaccionTransformacion nueva = new TransaccionTransformacion();
                    nueva.setCantidadCreada((float) 0);
                    nueva.setCantidadUsada((float) 0);
                    nueva.setDireccionInversa(false);
                    nueva.setInsumo(getBean().getInsumo_elaborado_disponible_seleccionado());
                    getBean().getLista_insumos_transformados_contenidos().add(nueva);
                }
                return Optional.empty();
            }
        }
        );

    }

    private void addListeners() {
        getBean().addPropertyChangeListener(PROP_CANTIDAD_ENTRADA, (PropertyChangeEvent evt) -> {
            if (getBean().getInsumo_selecionado() != null) {
                if (getBean().getOperacion_selected() == CheckBoxType.ENTRADA) {
                    if (getBean().getCantidad_entrada() != 0) {
                        if (getBean().getInsumo_selecionado().getValorMonetario() != 0) {
                            getBean().setMonto_entrada(R.formatoMoneda.format(getBean().getCantidad_entrada() * utils.setDosLugaresDecimalesFloat(
                                    getBean().getInsumo_selecionado().getValorMonetario() / getBean().getInsumo_selecionado().getCantidad())));
                        }
                    }
                }
            }
        });
        getBean().addPropertyChangeListener(PROP_INSUMO_SELECIONADO, (PropertyChangeEvent evt) -> {
            InsumoAlmacen insumo = getBean().getInsumo_selecionado();

            getBean().setCantidad_entrada(0);
            getBean().setMonto_entrada(R.formatoMoneda.format(0));

            if (insumo == null) {
                getBean().setUnidad_medida_insumo("<U/M>");
                if (getBean().getOperacion_selected() == CheckBoxType.TRANSFORMAR) {
                    getBean().getLista_insumo_elaborado_disponible().clear();
                }
            } else {
                getBean().setUnidad_medida_insumo(insumo.getInsumo().getUm());
                if (getBean().getOperacion_selected() == CheckBoxType.TRANSFORMAR) {
                    List<Insumo> listaInsumos = new ArrayList<>();
                    for (InsumoElaborado x : insumo.getInsumo().getInsumoDerivadoList()) {
                        listaInsumos.add(x.getInsumo_derivado_nombre());
                    }
                    getBean().getLista_insumo_elaborado_disponible().clear();
                    getBean().getLista_insumo_elaborado_disponible().addAll(listaInsumos);
//                    insumo.getInsumo().getInsumoDerivadoList().forEach((x) -> {
//                        listaInsumos.add(x.getInsumo_derivado_nombre());
//                    });
                }
            }

        });
        getBean().addPropertyChangeListener(PROP_OPERACION_SELECTED, (PropertyChangeEvent evt) -> {
            setVisiblePanels((CheckBoxType) evt.getNewValue());
            setDefaultValues((CheckBoxType) evt.getNewValue(), true);
        });
    }

    private void setVisiblePanels(CheckBoxType aux) {
        switch (aux) {
            case ENTRADA:
                getBean().setTabla_general_enabled(true);
                getBean().setMonto_enabled(true);
                getBean().setButton_agregar_insumo_enabled(true);
                getBean().setDestino_enabled(false);
                getBean().setRazon_rebaja_enabled(false);
                getBean().setTabla_insumos_transformar_enabled(false);
                break;
            case SALIDA:
                getBean().setTabla_general_enabled(true);
                getBean().setMonto_enabled(false);
                getBean().setButton_agregar_insumo_enabled(true);
                getBean().setDestino_enabled(true);
                getBean().setRazon_rebaja_enabled(false);
                getBean().setTabla_insumos_transformar_enabled(false);
                break;
            case REBAJA:
                getBean().setTabla_general_enabled(true);
                getBean().setMonto_enabled(false);
                getBean().setButton_agregar_insumo_enabled(true);
                getBean().setDestino_enabled(false);
                getBean().setRazon_rebaja_enabled(true);
                getBean().setTabla_insumos_transformar_enabled(false);
                break;
            case TRASPASO:
                getBean().setTabla_general_enabled(true);
                getBean().setMonto_enabled(false);
                getBean().setButton_agregar_insumo_enabled(true);
                getBean().setDestino_enabled(true);
                getBean().setRazon_rebaja_enabled(false);
                getBean().setTabla_insumos_transformar_enabled(false);
                break;
            case TRANSFORMAR:
                getBean().setTabla_general_enabled(false);
                getBean().setMonto_enabled(false);
                getBean().setButton_agregar_insumo_enabled(false);
                getBean().setDestino_enabled(true);
                getBean().setRazon_rebaja_enabled(false);
                getBean().setTabla_insumos_transformar_enabled(true);
                break;
            default:
                throw new UnExpectedErrorException("Tipo de operacion no soportada");
        }
    }

    private void setDefaultValues(CheckBoxType aux, boolean newOp) {
        getBean().setInsumo_selecionado(null);
        switch (aux) {
            case ENTRADA:
                if (newOp) {
                    getBean().getLista_elementos().clear();
                }
                break;
            case SALIDA:
                if (newOp) {
                    getBean().setDestino_seleccionado(null);
                    getBean().getLista_destino().clear();
                    getBean().getLista_destino().addAll(new ArrayListModel(controller.getCocinaList()));
                    getBean().getLista_elementos().clear();
                    getBean().setComponent_locked(true);
                }
                break;
            case REBAJA:
                if (newOp) {
                    getBean().setCausa_rebaja(null);
                    getBean().getLista_elementos().clear();
                    getBean().setComponent_locked(true);
                }
                break;
            case TRASPASO:
                if (newOp) {
                    getBean().setDestino_seleccionado(null);
                    getBean().getLista_destino().clear();
                    getBean().getLista_destino().addAll(new ArrayListModel(controller.getItems()));
                    getBean().getLista_elementos().clear();
                    getBean().setComponent_locked(true);
                    break;
                }
            case TRANSFORMAR:
                if (newOp) {
                    getBean().setDestino_seleccionado(null);
                    getBean().getLista_destino().clear();
                    getBean().getLista_destino().addAll(new ArrayListModel(controller.getItems()));
                    getBean().getLista_insumos_transformados_contenidos().clear();
                    getBean().getLista_insumo_elaborado_disponible().clear();
                    getBean().setInsumo_transformado_contenido_seleccionado(null);
                    getBean().setInsumo_elaborado_disponible_seleccionado(null);
                    break;
                }
                break;
            default:
                throw new UnExpectedErrorException("Tipo de operacion no soportada");
        }
    }

    private void addTransaction(CheckBoxType currentOperation) {
        if (getBean().getCantidad_entrada() == 0) {
            JOptionPane.showMessageDialog(Application.getInstance().getMainWindow(),
                    "Seleccione una cantidad de: " + getBean().getInsumo_selecionado().getInsumo().getNombre());
        } else {
            switch (currentOperation) {
                case ENTRADA:
                    if (!getBean().getMonto_entrada().equals("") && getBean().getMonto_entrada() != null) {
                        TransaccionSimple transaccionEntrada = new TransaccionSimple(
                                getBean().getInsumo_selecionado(),
                                getBean().getCantidad_entrada(),
                                Float.parseFloat(getBean().getMonto_entrada())
                        );
                        getBean().getLista_elementos().add(transaccionEntrada);
                        setDefaultValues(currentOperation, false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Defina el monto primero");
                    }
                    break;
                case SALIDA:
                    if (getBean().getDestino_seleccionado() == null) {
                        JOptionPane.showMessageDialog(Application.getInstance().getMainWindow(), "Seleccione un Destino");
                    } else {
                        TransaccionSimple transaccionSalida = new TransaccionSimple(
                                getBean().getInsumo_selecionado(),
                                getBean().getCantidad_entrada(),
                                (Cocina) getBean().getDestino_seleccionado());
                        getBean().getLista_elementos().add(transaccionSalida);
                        setDefaultValues(currentOperation, false);
                    }
                    break;

                case REBAJA:
                    if (getBean().getCausa_rebaja() == null || getBean().getCausa_rebaja().equals("")) {
                        JOptionPane.showMessageDialog(Application.getInstance().getMainWindow(), "Introduca la Causa de Rebaja");
                    } else {
                        TransaccionSimple transaccionRebaja = new TransaccionSimple(
                                getBean().getInsumo_selecionado(),
                                getBean().getCantidad_entrada(),
                                getBean().getCausa_rebaja());
                        getBean().getLista_elementos().add(transaccionRebaja);
                        setDefaultValues(currentOperation, false);
                    }
                    break;

                case TRASPASO:
                    if (getBean().getDestino_seleccionado() == null) {
                        JOptionPane.showMessageDialog(Application.getInstance().getMainWindow(), "Seleccione un Destino");
                    } else {
                        TransaccionSimple transaccionTraspaso = new TransaccionSimple(
                                getBean().getInsumo_selecionado(),
                                getBean().getCantidad_entrada(),
                                (Almacen) getBean().getDestino_seleccionado());
                        getBean().getLista_elementos().add(transaccionTraspaso);
                        setDefaultValues(currentOperation, false);
                    }
                    break;
                default:
                    throw new UnExpectedErrorException("Tipo de operacion no soportada");
            }
        }
        if (!getBean().getLista_elementos().isEmpty()) {
            getBean().setComponent_locked(false);
        }

    }

    private void confirmarTransaccion(CheckBoxType currentOperation) {
        if (currentOperation == CheckBoxType.TRANSFORMAR) {
            if (validateTransformationInputs()) {
                controller.crearTransformacion(getBean().getInsumo_selecionado(),
                        getBean().getCantidad_entrada(),
                        getBean().getLista_insumos_transformados_contenidos(),
                        (Almacen) getBean().getDestino_seleccionado());
                Application.getInstance().getNavigator().navigateUp();
            }
        } else {
            if (validateInputs()) {
                if (controller.crearOperacion(getBean().getLista_elementos(),
                        currentOperation,
                        getBean().getFecha_factura(),
                        getBean().getNumero_recibo(),
                        getBean().getFecha_factura())) {
                    Application.getInstance().getNavigator().navigateUp();
                }
            }
        }
    }

    private boolean validateTransformationInputs() {
        if (getBean().getInsumo_selecionado() == null) {
            JOptionPane.showMessageDialog(Application.getInstance().getMainWindow(), "Seleccione un Insumo a Transformar");
            return false;
        } else if (getBean().getCantidad_entrada() == 0) {
            JOptionPane.showMessageDialog(Application.getInstance().getMainWindow(),
                    "Seleccione una cantidad de: " + getBean().getInsumo_selecionado().getInsumo().getNombre());
            return false;
        } else if (getBean().getDestino_seleccionado() == null) {
            JOptionPane.showMessageDialog(Application.getInstance().getMainWindow(), "Seleccione un Destino");
            return false;
        } else if (getBean().getLista_insumos_transformados_contenidos().isEmpty()) {
            JOptionPane.showMessageDialog(Application.getInstance().getMainWindow(), "La lista de insumos transformados esta vacia");
            return false;
        }
        return true;
    }

    private boolean validateInputs() {
        if (getBean().getLista_elementos().isEmpty()) {
            JOptionPane.showMessageDialog(null, "La lista de transacciones esta vacia");
            return false;
        }
        return true;
    }

}
