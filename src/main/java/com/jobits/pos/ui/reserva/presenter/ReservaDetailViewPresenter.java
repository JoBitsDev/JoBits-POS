/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.reserva.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.cliente.core.domain.ClienteDomain;
import com.jobits.pos.cliente.core.usecase.ClienteUseCase;
import com.jobits.pos.controller.venta.OrdenService;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.main.Application;
import com.root101.clean.core.app.services.utils.TipoNotificacion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.reserva.core.domain.Reserva;
import com.jobits.pos.reserva.core.usecase.CategoriaUseCase;
import com.jobits.pos.reserva.core.usecase.ReservaUseCase;
import com.jobits.pos.reserva.core.usecase.UbicacionUseCase;
import com.jobits.pos.ui.clientes.presenter.ClientesDetailViewPresenter;
import com.jobits.pos.ui.clientes.presenter.ClientesListViewPresenter;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import static com.jobits.pos.ui.reserva.presenter.ReservaDetailViewModel.*;
import com.jobits.pos.ui.venta.orden.presenter.ProductoVentaSelectorPresenter;
import com.root101.clean.core.domain.services.ResourceHandler;
import java.beans.PropertyChangeEvent;
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
    private ClientesListViewPresenter clienteListPresenter;
    private ClientesDetailViewPresenter clienteDetailPresenter;

    ReservaUseCase reservasUseCase = PosDesktopUiModule.getInstance().getImplementation(ReservaUseCase.class);
    UbicacionUseCase ubicacionUseCase = PosDesktopUiModule.getInstance().getImplementation(UbicacionUseCase.class);
    CategoriaUseCase categoriasUseCase = PosDesktopUiModule.getInstance().getImplementation(CategoriaUseCase.class);
    ClienteUseCase clienteUseCase = PosDesktopUiModule.getInstance().getImplementation(ClienteUseCase.class);

    public static final String ACTION_CANCELAR = "Cancelar";
    public static final String ACTION_ACEPTAR = "Aceptar";
    public static final String ACTION_ELIMINAR = "Eliminar";
    public static final String ACTION_MODO_AGREGO = "Agrego";
    public static final String PROP_TO_MAIN_VIEW = "To Main View";

    public static final String ACTION_TO_CLIENTES_LIST = "To Clientes List";
    public static final String ACTION_TO_CREATE_CLIENTE = "To Create Cliente";
    public static final String ACTION_SELECT_CLIENTE = "Select Cliente";

    public static final String PROP_TO_CLIENTES_LIST = "To Clientes List";
    public static final String PROP_TO_CREATE_CLIENTE = "To Create Cliente";
    public static final String PROP_SELECT_CLIENTE = "Select Cliente";

    public static final String ACTION_CREATE_CLIENTE = "Create Cliente";

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
        clienteListPresenter = new ClientesListViewPresenter();
        addListeners();
        refreshState();
        setListToBean();
    }

    public ProductoVentaSelectorPresenter getProductoSelectorPresenter() {
        return productoSelectorPresenter;
    }

    public ClientesListViewPresenter getClienteListPresenter() {
        return clienteListPresenter;
    }

    public ClientesDetailViewPresenter getClienteDetailPresenter() {
        return clienteDetailPresenter;
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_CANCELAR) {
            @Override
            public Optional doAction() {
                if ((boolean) Application.getInstance().getNotificationService().
                        showDialog("Esta seguro que desea cancelar?",
                                TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
                    NavigationService.getInstance().navigateUp();
                }
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
        registerOperation(new AbstractViewAction(ACTION_TO_CLIENTES_LIST) {
            @Override
            public Optional doAction() {
                onClientesListClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_TO_CREATE_CLIENTE) {
            @Override
            public Optional doAction() {
                onCreateClienteClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_CREATE_CLIENTE) {
            @Override
            public Optional doAction() {
                onCrearClienteClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_SELECT_CLIENTE) {
            @Override
            public Optional doAction() {
                onSelectClienteClick();
                return Optional.empty();
            }

        });
    }

    private void onSelectClienteClick() {
        ClienteDomain cliente = clienteListPresenter.getBean().getElemento_seleccionado();
        if (cliente != null) {
            getBean().setCliente(cliente);
            firePropertyChange(PROP_SELECT_CLIENTE, null, null);
        } else {
            throw new IllegalArgumentException("Seleccione un cliente");
        }
    }

    private void onCrearClienteClick() {
        clienteDetailPresenter.onAgregarClick();
        clienteListPresenter.refreshView();
        firePropertyChange(PROP_TO_CLIENTES_LIST, null, null);
    }

    private void onCreateClienteClick() {
        clienteDetailPresenter = new ClientesDetailViewPresenter(null);
        firePropertyChange(PROP_TO_CREATE_CLIENTE, null, null);
    }

    private void onClientesListClick() {
        clienteListPresenter = new ClientesListViewPresenter();
        firePropertyChange(PROP_TO_CLIENTES_LIST, null, null);
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
        getBean().setCliente(clienteUseCase.findBy(reserva.getClienteidcliente()));
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
        addBeanPropertyChangeListener(PROP_CLIENTE, (PropertyChangeEvent evt) -> {
            ClienteDomain cliente = (ClienteDomain) evt.getNewValue();
            if (cliente != null) {
                getBean().setNombre_reserva(cliente.toString());
                getBean().setNombre_cliente(cliente.toString());
            }
        });

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

    private void onAceptarClick() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Esta seguro que desea continuar?",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
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
            if (getBean().getCliente() != null) {
                reserva.setClienteidcliente(getBean().getCliente().getId());
            }
            reserva.setCategoriaidcategoria(getBean().getCategoria_seleccionada());
            if (creatingMode) {
                reservasUseCase.create(reserva);
            } else {
                reservasUseCase.edit(reserva);
            }
            NavigationService.getInstance().navigateUp();
            Application.getInstance().getNotificationService().notify(ResourceHandler.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
        }
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
