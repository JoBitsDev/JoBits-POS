/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.almacen.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.root101.swing.material.standards.MaterialIcons;
import com.jobits.pos.core.repo.impl.VentaDAO;
import com.jobits.pos.controller.puntoelaboracion.PuntoElaboracionListService;
import com.jobits.pos.core.domain.TransaccionSimple;
import com.jobits.pos.core.domain.models.Cocina;
import com.jobits.pos.core.domain.models.Insumo;
import com.jobits.pos.core.domain.models.InsumoElaborado;
import com.jobits.pos.core.domain.models.Venta;
import com.jobits.pos.exceptions.UnExpectedErrorException;
import com.jobits.pos.inventario.core.almacen.domain.Almacen;
import com.jobits.pos.inventario.core.almacen.domain.Operacion;
import com.jobits.pos.inventario.core.almacen.domain.Transaccion;
import com.jobits.pos.inventario.core.almacen.domain.TransaccionTransformacion;
import com.jobits.pos.inventario.core.almacen.usecase.AlmacenManageService;
import com.jobits.pos.inventario.core.almacen.usecase.impl.AlmacenManageController.OperationType;
import static com.jobits.pos.inventario.core.almacen.usecase.impl.AlmacenManageController.OperationType.ENTRADA;
import static com.jobits.pos.inventario.core.almacen.usecase.impl.AlmacenManageController.OperationType.REBAJA;
import static com.jobits.pos.inventario.core.almacen.usecase.impl.AlmacenManageController.OperationType.SALIDA;
import static com.jobits.pos.inventario.core.almacen.usecase.impl.AlmacenManageController.OperationType.TRANSFORMAR;
import static com.jobits.pos.inventario.core.almacen.usecase.impl.AlmacenManageController.OperationType.TRASPASO;
import com.jobits.pos.main.Application;
import com.root101.clean.core.app.services.utils.TipoNotificacion;
import com.jobits.pos.recursos.R;
import static com.jobits.pos.ui.almacen.presenter.FacturaViewModel.*;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.utils.utils;
import com.root101.clean.core.domain.services.ResourceHandler;
import java.beans.PropertyChangeEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author Home
 */
public class FacturaViewPresenter extends AbstractViewPresenter<FacturaViewModel> {

    private AlmacenManageService service;
    private PuntoElaboracionListService cocinaService = PosDesktopUiModule.getInstance().getImplementation(PuntoElaboracionListService.class);

    private Operacion operationToAccept;

    public static final String ACTION_AGREGAR_INSUMO = "Agregar Insumo";
    public static final String ACTION_ELIMINAR_INSUMO = "Eliminar Insumo";

    public static final String ACTION_CERRAR_POPUP = "Cerrar Popup";
    public static final String ACTION_CONFIRMAR_TRANSACCION = "Confirmar";

    public static final String ACTION_ELIMINAR_INSUMO_TRANSFORMADO = "Agregar Insumo Transformado";
    public static final String ACTION_AGREGAR_INSUMO_TRANSFORMADO = "Eliminar Insumo Transformado";

    public static final String PROP_SWAP_TO_ENTRADAS = "Entradas";
    public static final String PROP_SWAP_TO_TRANSFORMAR = "Transformar";

    public static final String ACTION_SET_IS_MERMA = "Set Merma";

    public FacturaViewPresenter(AlmacenManageService controller, Almacen almacen) {
        super(new FacturaViewModel());
        this.service = controller;
        addListeners();
        getBean().getLista_insumos_disponibles().clear();
        getBean().setAlmacen(almacen);
        getBean().getLista_insumos_disponibles().addAll(
                new ArrayListModel<>(service.getInsumoAlmacenList(getBean().getAlmacen()).stream().map((t) -> {
                    return t.getInsumo();
                }).collect(Collectors.toList())));
        setVisiblePanels(getBean().getOperacion_selected());
    }

    public FacturaViewPresenter(AlmacenManageService controller, Operacion op) {
        super(new FacturaViewModel());
        this.service = controller;
        this.operationToAccept = op;
        addListeners();
        getBean().getLista_insumos_disponibles().clear();
        getBean().setAlmacen(op.getAlmacen()
        );
        getBean().getLista_insumos_disponibles().addAll(
                new ArrayListModel<>(service.getInsumoAlmacenList(getBean().getAlmacen()).stream().map((t) -> {
                    return t.getInsumo();
                }).collect(Collectors.toList())));
        setVisiblePanels(getBean().getOperacion_selected());
        updateBean();
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
                TransaccionSimple selected = getBean().getElemento_seleccionado();
                if (selected != null) {
                    getBean().getLista_elementos().remove(selected);
                    if (getBean().getLista_elementos().isEmpty()) {
                        getBean().setComponent_locked(true);
                    }
                }
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_CERRAR_POPUP) {
            @Override
            public Optional doAction() {
                if ((boolean) Application.getInstance().getNotificationService().
                        showDialog("Esta seguro que desea cancelar?",
                                TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
                    Application.getInstance().getNavigator().navigateUp();
                }
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_CONFIRMAR_TRANSACCION) {
            @Override
            public Optional doAction() {
                if ((boolean) Application.getInstance().getNotificationService().
                        showDialog("Esta seguro que desea confirmar?",
                                TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
                    confirmarTransaccion(getBean().getOperacion_selected());
                    Application.getInstance().getNavigator().navigateUp();
                    Application.getInstance().getNotificationService().notify(ResourceHandler.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
                }
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_ELIMINAR_INSUMO_TRANSFORMADO) {
            @Override
            public Optional doAction() {
                TransaccionTransformacion selected = getBean().getInsumo_transformado_contenido_seleccionado();
                if (selected != null) {
                    getBean().getLista_insumos_transformados_contenidos().remove(selected);
                }
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
        registerOperation(new AbstractViewAction(ACTION_SET_IS_MERMA) {
            @Override
            public Optional doAction() {
                getBean().setRebaja_merma(!getBean().isRebaja_merma());
                return Optional.empty();
            }
        }
        );

    }

    private void addListeners() {
        getBean().addPropertyChangeListener(PROP_CANTIDAD_ENTRADA, (PropertyChangeEvent evt) -> {
            Insumo insumo = getBean().getInsumo_selecionado();
            float cant = (float) evt.getNewValue();
            if (insumo != null) {
                if (getBean().getOperacion_selected() == OperationType.ENTRADA) {
                    if (cant != 0) {
                        if (insumo.getCostoPorUnidad() != 0) {
                            getBean().setMonto_entrada(String.valueOf(
                                    utils.setDosLugaresDecimalesFloat(
                                            cant * insumo.getCostoPorUnidad())));
                        }
                    }
                }
            }
        });
        getBean().addPropertyChangeListener(PROP_INSUMO_SELECIONADO, (PropertyChangeEvent evt) -> {
            Insumo insumo = getBean().getInsumo_selecionado();

            getBean().setCantidad_entrada(0);
            getBean().setMonto_entrada(R.formatoMoneda.format(0));

            if (insumo == null) {
                getBean().setUnidad_medida_insumo("<U/M>");
                if (getBean().getOperacion_selected() == OperationType.TRANSFORMAR) {
                    getBean().getLista_insumo_elaborado_disponible().clear();
                }
            } else {
                getBean().setUnidad_medida_insumo(insumo.getUm());
                if (getBean().getOperacion_selected() == OperationType.TRANSFORMAR) {
                    List<Insumo> listaInsumos = new ArrayList<>();
                    for (InsumoElaborado x : insumo.getInsumoDerivadoList()) {
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
                    getBean().getLista_destino().addAll(new ArrayListModel(cocinaService.findAll()));
                    getBean().getLista_elementos().clear();
                    getBean().setComponent_locked(true);
                }
                break;
            case REBAJA:
                if (newOp) {
                    getBean().setCausa_rebaja(null);
//                    getBean().setRebaja_merma(false);
                    getBean().getLista_elementos().clear();
                    getBean().setComponent_locked(true);
                }
                break;
            case TRASPASO:
                if (newOp) {
                    getBean().setDestino_seleccionado(null);
                    getBean().getLista_destino().clear();
                    getBean().getLista_destino().addAll(new ArrayListModel(service.findAll()));
                    getBean().getLista_elementos().clear();
                    getBean().setComponent_locked(true);
                    break;
                }
            case TRANSFORMAR:
                if (newOp) {
                    getBean().setDestino_seleccionado(null);
                    getBean().getLista_destino().clear();
                    getBean().getLista_destino().addAll(new ArrayListModel(service.findAll()));
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
                    "Seleccione una cantidad de: " + getBean().getInsumo_selecionado().getNombre());
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
                        TransaccionSimple transaccionRebaja
                                = new TransaccionSimple(
                                        getBean().getInsumo_selecionado(),
                                        getBean().getCantidad_entrada(),
                                        getBean().getCausa_rebaja(),
                                        getBean().isRebaja_merma());
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
        if (operationToAccept != null) {
            service.updateAndExecuteOperation(getBean().getAlmacen().getCodAlmacen(), operationToAccept);
            Application.getInstance().getNavigator().navigateUp();
        }
        if (currentOperation == OperationType.TRANSFORMAR) {
            if (validateTransformationInputs()) {
                service.crearOperacion(
                        Operacion.Tipo.TRANSFORMACION,
                        Collections.singletonList(
                                new TransaccionSimple(
                                        getBean().getInsumo_selecionado(),
                                        getBean().getCantidad_entrada(),
                                        (Almacen) getBean().getDestino_seleccionado(),
                                        getBean().getLista_insumos_transformados_contenidos())),
                        getBean().getNumero_recibo(),
                        getBean().getFecha_factura(),
                        getBean().getAlmacen().getCodAlmacen(),
                        0
                );
                Application.getInstance().getNavigator().navigateUp();
            }
        } else {
            if (validateInputs()) {
                switch (currentOperation) {
                    case ENTRADA:
                        service.crearOperacion(Operacion.Tipo.ENTRADA, getBean().getLista_elementos(),
                                getBean().getNumero_recibo(), getBean().getFecha_factura(), getBean().getAlmacen().getCodAlmacen(), -1);
                        break;
                    case SALIDA:
                        Date fecha = getBean().getFecha_factura();
                        service.crearOperacion(Operacion.Tipo.SALIDA, getBean().getLista_elementos(),
                                getBean().getNumero_recibo(), fecha, getBean().getAlmacen().getCodAlmacen(), selectIdFecha(fecha));
                        break;
                    case REBAJA:
                        service.crearOperacion(
                                Operacion.Tipo.REBAJA,
                                getBean().getLista_elementos(),
                                getBean().getNumero_recibo(),
                                getBean().getFecha_factura(), getBean().getAlmacen().getCodAlmacen(), 0);
                        break;
                    case TRASPASO:
                        service.crearOperacion(
                                Operacion.Tipo.TRASPASO,
                                getBean().getLista_elementos(),
                                getBean().getNumero_recibo(),
                                getBean().getFecha_factura(), getBean().getAlmacen().getCodAlmacen(), 0);
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
                    "Seleccione una cantidad de: " + getBean().getInsumo_selecionado().getNombre());
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

    private void updateBean() {
        if (operationToAccept == null) {
            throw new IllegalArgumentException("Bad Call");
        }
        var o = operationToAccept;
        getBean().setFecha_factura(o.getFecha());
        OperationType opType = getTipoOperacionAndFillData(o);
        getBean().setOperacion_selected(opType);
        getBean().setNumero_recibo(o.getNoRecibo());
        List<TransaccionSimple> elem = new ArrayList<>();
        for (Transaccion t : o.getTransaccionList()) {
            elem.add(TransaccionSimple.of(t));
        }
        getBean().setLista_elementos(elem);

    }

    private OperationType getTipoOperacionAndFillData(Operacion o) {
        if (o.getTransaccionList().isEmpty()) {
            throw new IllegalArgumentException("Operacion no valida");
        }
        Transaccion t = o.getTransaccionList().get(0);
        if (t.getTransaccionEntrada() != null) {
            setVisiblePanels(OperationType.ENTRADA);
            return OperationType.ENTRADA;
        }
        if (t.getTransaccionSalida() != null) {
            setVisiblePanels(OperationType.SALIDA);
            getBean().setComponent_locked(false);
            getBean().setDestino_seleccionado(t.getTransaccionSalida().getCocinacodCocina());
            return OperationType.SALIDA;
        }
        if (t.getTransaccionMerma() != null) {
            setVisiblePanels(OperationType.REBAJA);
            return OperationType.REBAJA;
        }
        if (t.getTransaccionTraspaso() != null) {
            setVisiblePanels(OperationType.TRASPASO);
            getBean().setDestino_seleccionado(t.getTransaccionTraspaso().getAlmacenDestino());
            return OperationType.TRASPASO;
        }

        if (t.getTransaccionTransformacionList() != null) {
            setVisiblePanels(OperationType.TRANSFORMAR);
            Almacen a = service.findBy(t.getTransaccionTransformacionList().get(0).getCodAlmacenDestino());
            getBean().setDestino_seleccionado(a);
            return OperationType.TRANSFORMAR;
        }

        throw new IllegalStateException("Transacciones no reconocidas");
    }

}
