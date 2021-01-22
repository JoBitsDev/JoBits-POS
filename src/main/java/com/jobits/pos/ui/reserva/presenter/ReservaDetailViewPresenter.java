/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.reserva.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.venta.OrdenService;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.TipoNotificacion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.reserva.core.domain.Reserva;
import com.jobits.pos.reserva.core.usecase.CategoriaUseCase;
import com.jobits.pos.reserva.core.usecase.ClienteUseCase;
import com.jobits.pos.reserva.core.usecase.ReservaUseCase;
import com.jobits.pos.reserva.core.usecase.UbicacionUseCase;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.venta.orden.presenter.ProductoVentaSelectorPresenter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Home
 */
public class ReservaDetailViewPresenter extends AbstractViewPresenter<ReservaDetailViewModel> {

    public ProductoVentaSelectorPresenter productoSelectorPresenter;

    ReservaUseCase reservasUseCase = PosDesktopUiModule.getInstance().getImplementation(ReservaUseCase.class);
    UbicacionUseCase ubicacionUseCase = PosDesktopUiModule.getInstance().getImplementation(UbicacionUseCase.class);
    CategoriaUseCase categoriasUseCase = PosDesktopUiModule.getInstance().getImplementation(CategoriaUseCase.class);
    ClienteUseCase clienteUseCase = PosDesktopUiModule.getInstance().getImplementation(ClienteUseCase.class);

    public static final String ACTION_CANCELAR = "Cancelar";
    public static final String ACTION_ACEPTAR = "Aceptar";
    public static final String ACTION_ELIMINAR = "Eliminar";
    public static final String ACTION_MODO_AGREGO = "Agrego";
    public static final String ACTION_AGREGAR_CLIENTE = "Agregar Cliente";

    private final boolean creatingMode;

    List<LocalTime> hours = new ArrayList<>();
    List<LocalTime> mins = new ArrayList<>();

    private Reserva reserva = new Reserva();

    public ReservaDetailViewPresenter(Reserva reserva, boolean creatingMode) {
        super(new ReservaDetailViewModel());
        this.reserva = reserva;
        this.creatingMode = creatingMode;
        initMinutes();
        productoSelectorPresenter = new ProductoVentaSelectorPresenter(PosDesktopUiModule.getInstance().getImplementation(OrdenService.class));
        addListeners();
        refreshState();
        setListToBean();
    }

    public ProductoVentaSelectorPresenter getProductoSelectorPresenter() {
        return productoSelectorPresenter;
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_CANCELAR) {
            @Override
            public Optional doAction() {
                NavigationService.getInstance().navigateUp();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_ACEPTAR) {
            @Override
            public Optional doAction() {
                onAceptarClick();
                return Optional.empty();
            }

        });
        registerOperation(new AbstractViewAction(ACTION_MODO_AGREGO) {
            @Override
            public Optional doAction() {
                getBean().setModo_agrego(!getBean().isModo_agrego());
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_ELIMINAR) {
            @Override
            public Optional doAction() {
                onEliminarClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_AGREGAR_CLIENTE) {
            @Override
            public Optional doAction() {
                onAgregarClienteClick();
                return Optional.empty();
            }
        });
    }

    @Override
    protected Optional refreshState() {
        getBean().setNombre_reserva(reserva.getNotasreserva());
        LocalDate ld = reserva.getFechareserva();
        Date date = new Date(ld.getYear() - 1900, ld.getMonthValue() - 1, ld.getDayOfMonth());//chek this shit
        getBean().setFecha(date);
        LocalTime lt = reserva.getHorareserva();
        if (lt.get(ChronoField.AMPM_OF_DAY) == 1) {
            getBean().setAm_pm_seleccionado(LocalTime.NOON);
            initEveningHours();
        } else {
            getBean().setAm_pm_seleccionado(LocalTime.MIDNIGHT);
            initMorningHours();
        }
        getBean().setLista_horas(new ArrayListModel<>(hours));
        getBean().setHora_seleccionada(LocalTime.of(lt.getHour(), 0));
        getBean().setLista_minutos(new ArrayListModel<>(mins));
        getBean().setMinuto_seleccionado(LocalTime.of(0, lt.getMinute()));
        getBean().setDuracion(reserva.getDuracionMinutos());
        getBean().setLista_ubicaciones(new ArrayListModel<>(ubicacionUseCase.findAll()));
        getBean().setUbicacion_seleccionada(reserva.getUbicacionidubicacion());
        getBean().setLista_clientes(new ArrayListModel<>(clienteUseCase.findAll()));
        getBean().setCliente(reserva.getClienteidcliente());
        getBean().setLista_categorias(new ArrayListModel<>(categoriasUseCase.findAll()));
        getBean().setCategoria_seleccionada(reserva.getCategoriaidcategoria());
        return super.refreshState();
    }

    private void initMinutes() {
        LocalTime baseTime = LocalTime.MIN;
        for (int i = 0; i < 60; i++) {
            mins.add(baseTime);
            baseTime = baseTime.plusMinutes(1);
        }
    }

    private void setListToBean() {
//        getBean().setProducto_seleccionado(null);
//        getBean().setLista_producto(new ArrayListModel<>(service.getReserva().getProductovOrdenList()));
    }

    private void addListeners() {
//        getBean().addPropertyChangeListener(PROP_MESA_SELECCIONADA, (PropertyChangeEvent evt) -> {
//            Mesa m = (Mesa) evt.getNewValue();
//            if (m != null) {
//                productoSelectorPresenter.setMesaSeleccionada(getBean().getMesa_seleccionada());
//            }
//            getBean().setShow_productos(m != null);
//        });
//        productoSelectorPresenter.addBeanPropertyChangeListener(PROP_PRODUCTOVENTASELECCIONADO, (PropertyChangeEvent evt) -> {
//            ProductoVenta pv = (ProductoVenta) evt.getNewValue();
//            if (pv != null) {
//                ProductovOrden pvo = service.generarProductovOrden(pv, new NumberPad(null).showView());
//                service.agregarProductoAOrden(getBean().isModo_agrego(), pvo, getBean().getProducto_seleccionado());
//                setListToBean();
//            }
//        });
//        getBean().addPropertyChangeListener(PROP_FECHA, (PropertyChangeEvent evt) -> {
//            Date fecha = (Date) evt.getNewValue();
//            if (fecha != null) {
//                getBean().setLista_mesas(new ArrayListModel<>(service.mesasDisponiblesParaReservar(fecha)));
//            }
//        });
//        getBean().addPropertyChangeListener(PROP_PRODUCTO_SELECCIONADO, (PropertyChangeEvent evt) -> {
//            ProductovOrden p = (ProductovOrden) evt.getNewValue();
//            if (p != null && p.getAgregadoA() == null) {
//                getBean().setBotton_agrego_enabled(true);
//            } else {
//                getBean().setModo_agrego(false);
//                getBean().setBotton_agrego_enabled(false);
//            }
//        }
//        );
    }

    private void onEliminarClick() {
//        service.eliminarProDuctoDeOrden(getBean().getProducto_seleccionado());
//        setListToBean();
    }

    private void onAgregarClienteClick() {
//        if ((boolean) Application.getInstance().getNotificationService().
//                showDialog("Desea registrar a: " + getBean().getNombre_cliente(),
//                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
//            service.crearCliente(getBean().getNombre_cliente(),
//                    getBean().getApellido_cliente(),
//                    getBean().getTelefono_cliente());
//            getBean().setLista_clientes(new ArrayListModel<>(service.getListaClientes()));
//            getBean().setNombre_cliente(null);
//            getBean().setApellido_cliente(null);
//            getBean().setTelefono_cliente(null);
//            Application.getInstance().getNotificationService().showDialog(R.RESOURCE_BUNDLE.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
//        }
    }

    private void onAceptarClick() {
        Date date = getBean().getFecha();
        LocalTime hora = getBean().getHora_seleccionada();
        LocalTime minutos = getBean().getMinuto_seleccionado();
        if (getBean().getAm_pm_seleccionado().get(ChronoField.AMPM_OF_DAY) == 1) {
            hora.plusHours(12);
        }
        reserva.setNotasreserva(getBean().getNombre_reserva());
        reserva.setFechareserva(LocalDate.of(date.getYear() + 1900, date.getMonth() + 1, date.getDate()));
        reserva.setHorareserva(LocalTime.of(hora.getHour(), minutos.getMinute()));
        reserva.setDuracionMinutos(getBean().getDuracion());
        reserva.setUbicacionidubicacion(getBean().getUbicacion_seleccionada());
        reserva.setClienteidcliente(getBean().getCliente());
        reserva.setCategoriaidcategoria(getBean().getCategoria_seleccionada());
        if (creatingMode) {
            reservasUseCase.create(reserva);
        } else {
            reservasUseCase.edit(reserva);
        }
        NavigationService.getInstance().navigateUp();
        Application.getInstance().getNotificationService().notify(R.RESOURCE_BUNDLE.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
    }

    private void initMorningHours() {
        LocalTime baseTime = LocalTime.MIDNIGHT;
        for (int i = 0; i < 12; i++) {
            hours.add(baseTime);
            baseTime = baseTime.plusHours(1);
        }
    }

    private void initEveningHours() {
        LocalTime baseTime = LocalTime.NOON;
        for (int i = 12; i < 24; i++) {
            hours.add(baseTime);
            baseTime = baseTime.plusHours(1);
        }
    }

}
