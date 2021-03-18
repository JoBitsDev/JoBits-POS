/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.almacen.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.root101.swing.material.standards.MaterialIcons;
import com.jobits.pos.core.repo.impl.VentaDAO;
import com.jobits.pos.controller.almacen.impl.AlmacenManageController.OperationType;//TODO; enum de implementacion en view
import com.jobits.pos.controller.almacen.AlmacenManageService;
import com.jobits.pos.core.domain.TransaccionSimple;
import com.jobits.pos.core.domain.VentaDAO1;
import com.jobits.pos.core.domain.models.Almacen;
import com.jobits.pos.core.domain.models.Cocina;
import com.jobits.pos.core.domain.models.Insumo;
import com.jobits.pos.core.domain.models.InsumoAlmacen;
import com.jobits.pos.core.domain.models.InsumoElaborado;
import com.jobits.pos.core.domain.models.TransaccionTransformacion;
import com.jobits.pos.core.domain.models.Venta;
import com.jobits.pos.exceptions.UnExpectedErrorException;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.TipoNotificacion;
import com.jobits.pos.recursos.R;
import static com.jobits.pos.ui.almacen.presenter.FacturaViewModel.*;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.utils.utils;
import java.beans.PropertyChangeEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author Home
 */
public class FacturaViewPresenter extends AbstractViewPresenter<FacturaViewModel> {

    private AlmacenManageService service;
    public static final String ACTION_AGREGAR_INSUMO = "Agregar Insumo";
    public static final String ACTION_ELIMINAR_INSUMO = "Eliminar Insumo";

    public static final String ACTION_CERRAR_POPUP = "Cerrar Popup";
    public static final String ACTION_CONFIRMAR_TRANSACCION = "Confirmar";

    public static final String ACTION_ELIMINAR_INSUMO_TRANSFORMADO = "Agregar Insumo Transformado";
    public static final String ACTION_AGREGAR_INSUMO_TRANSFORMADO = "Eliminar Insumo Transformado";

    public static final String PROP_SWAP_TO_ENTRADAS = "Entradas";
    public static final String PROP_SWAP_TO_TRANSFORMAR = "Transformar";

    public FacturaViewPresenter(AlmacenManageService controller) {
        super(new FacturaViewModel());
        this.service = controller;
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
                Application.getInstance().getNavigator().navigateUp();
                Application.getInstance().getNotificationService().notify(R.RESOURCE_BUNDLE.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
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
            InsumoAlmacen insumo = getBean().getInsumo_selecionado();
            float cant = (float) evt.getNewValue();
            if (insumo != null) {
                if (getBean().getOperacion_selected() == OperationType.ENTRADA) {
                    if (cant != 0) {
                        if (insumo.getInsumo() != null) {
                            if (insumo.getInsumo().getCostoPorUnidad() != 0) {
                                getBean().setMonto_entrada(String.valueOf(
                                        utils.setDosLugaresDecimalesFloat(
                                                cant * insumo.getInsumo().getCostoPorUnidad())));
                            }
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
                if (getBean().getOperacion_selected() == OperationType.TRANSFORMAR) {
                    getBean().getLista_insumo_elaborado_disponible().clear();
                }
            } else {
                getBean().setUnidad_medida_insumo(insumo.getInsumo().getUm());
                if (getBean().getOperacion_selected() == OperationType.TRANSFORMAR) {
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
            setVisiblePanels((OperationType) evt.getNewValue());
            setDefaultValues((OperationType) evt.getNewValue(), true);
        });
    }

    private void setVisiblePanels(OperationType aux) {
        switch (aux) {
            case ENTRADA:
                getBean().setMonto_enabled(true);
                getBean().setButton_agregar_insumo_enabled(true);
                getBean().setDestino_enabled(false);
                getBean().setRazon_rebaja_enabled(false);
                firePropertyChange(PROP_SWAP_TO_ENTRADAS, null, null);
                break;
            case SALIDA:
                getBean().setMonto_enabled(false);
                getBean().setButton_agregar_insumo_enabled(true);
                getBean().setDestino_enabled(true);
                getBean().setRazon_rebaja_enabled(false);
                firePropertyChange(PROP_SWAP_TO_ENTRADAS, null, null);
                break;
            case REBAJA:
                getBean().setMonto_enabled(false);
                getBean().setButton_agregar_insumo_enabled(true);
                getBean().setDestino_enabled(false);
                getBean().setRazon_rebaja_enabled(true);
                firePropertyChange(PROP_SWAP_TO_ENTRADAS, null, null);
                break;
            case TRASPASO:
                getBean().setMonto_enabled(false);
                getBean().setButton_agregar_insumo_enabled(true);
                getBean().setDestino_enabled(true);
                getBean().setRazon_rebaja_enabled(false);
                firePropertyChange(PROP_SWAP_TO_ENTRADAS, null, null);
                break;
            case TRANSFORMAR:
                getBean().setMonto_enabled(false);
                getBean().setButton_agregar_insumo_enabled(false);
                getBean().setDestino_enabled(true);
                getBean().setRazon_rebaja_enabled(false);
                firePropertyChange(PROP_SWAP_TO_TRANSFORMAR, null, null);
                break;
            default:
                throw new UnExpectedErrorException("Tipo de operacion no soportada");
        }
    }

    private void setDefaultValues(OperationType aux, boolean newOp) {
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
                    getBean().getLista_destino().addAll(new ArrayListModel(service.getCocinaList()));
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
                    getBean().getLista_destino().addAll(new ArrayListModel(service.getItems()));
                    getBean().getLista_elementos().clear();
                    getBean().setComponent_locked(true);
                    break;
                }
            case TRANSFORMAR:
                if (newOp) {
                    getBean().setDestino_seleccionado(null);
                    getBean().getLista_destino().clear();
                    getBean().getLista_destino().addAll(new ArrayListModel(service.getItems()));
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

    private void addTransaction(OperationType currentOperation) {
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

    private void confirmarTransaccion(OperationType currentOperation) {
        if (currentOperation == OperationType.TRANSFORMAR) {
            if (validateTransformationInputs()) {
                service.crearTransformacion(getBean().getInsumo_selecionado(),
                        getBean().getCantidad_entrada(),
                        getBean().getLista_insumos_transformados_contenidos(),
                        (Almacen) getBean().getDestino_seleccionado());
                Application.getInstance().getNavigator().navigateUp();
            }
        } else {
            if (validateInputs()) {
                switch (currentOperation) {
                    case ENTRADA:
                        service.crearOperacionEntrada(getBean().getLista_elementos(),
                                getBean().getNumero_recibo(), getBean().getFecha_factura());
                        break;
                    case SALIDA:
                        Date fecha = getBean().getFecha_factura();
                        service.crearOperacionSalida(getBean().getLista_elementos(),
                                getBean().getNumero_recibo(), fecha, selectIdFecha(fecha));
                        break;
                    case REBAJA:
                        service.crearOperacionRebaja(getBean().getLista_elementos(),
                                getBean().getNumero_recibo(), getBean().getFecha_factura());
                        break;
                    case TRASPASO:
                        service.crearOperacionTraspaso(getBean().getLista_elementos(),
                                getBean().getNumero_recibo(), getBean().getFecha_factura());
                        break;
                    default:
                        throw new UnExpectedErrorException("Tipo de operacion no soportada");
                }
//                if (service.crearOperacion(getBean().getLista_elementos(),
//                        currentOperation,
//                        getBean().getFecha_factura(),
//                        getBean().getNumero_recibo(),
//                        getBean().getFecha_factura())) {
//                    Application.getInstance().getNavigator().navigateUp();
//                }
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

    private Integer selectIdFecha(Date fecha) {
        List<Venta> list = VentaDAO.getInstance().find(fecha);
        if (!list.isEmpty()) {
            if (list.size() > 1) {
                JList<Venta> jList = new JList<>(list.toArray(new Venta[list.size()]));
                jList.setSelectedIndex(-1);
                Object[] options = {"Seleccionar", "Cancelar"};
                //                     yes        no  
                SimpleDateFormat sdf = new SimpleDateFormat("d/MM/yyyy");
                int confirm = JOptionPane.showOptionDialog(
                        null,
                        jList,
                        "Ventas del " + sdf.format(fecha),
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.YES_NO_OPTION,
                        MaterialIcons.RESTORE,
                        options,
                        options[0]);
                switch (confirm) {
                    case JOptionPane.YES_OPTION:
                        Venta v = (Venta) jList.getSelectedValue();
                        if (v.getVentaTotal() == null) {
                            return ((Venta) jList.getSelectedValue()).getId();
                        } else {
                            if (JOptionPane.showConfirmDialog(null,
                                    "La venta se encuentra cerrada \n "
                                    + "Desea relizar aun la transaccion?") == JOptionPane.YES_OPTION) {
                                return ((Venta) jList.getSelectedValue()).getId();
                            } else {
                                return null;
                            }
                        }
                    case JOptionPane.NO_OPTION:
                        return null;
                    default:
                        break;
                }
            } else {
                return list.get(0).getId();
            }

        } else {
            throw new IllegalArgumentException("No hay ventas registradas el dia de la factura");
        }
        return null;
    }

}
