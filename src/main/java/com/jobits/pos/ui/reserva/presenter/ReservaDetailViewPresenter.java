/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.reserva.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.reservas.ReservaController;
import com.jobits.pos.controller.reservas.ReservaService;
import com.jobits.pos.controller.venta.OrdenController;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.domain.models.Mesa;
import com.jobits.pos.domain.models.Orden;
import com.jobits.pos.domain.models.ProductoVenta;
import com.jobits.pos.domain.models.ProductovOrden;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import static com.jobits.pos.ui.reserva.presenter.ReservaDetailViewModel.*;
import com.jobits.pos.ui.utils.NumberPad;
import com.jobits.pos.ui.venta.orden.presenter.ProductoVentaSelectorPresenter;
import static com.jobits.pos.ui.venta.orden.presenter.ProductoVentaSelectorViewModel.PROP_PRODUCTOVENTASELECCIONADO;
import java.beans.PropertyChangeEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Home
 */
public class ReservaDetailViewPresenter extends AbstractViewPresenter<ReservaDetailViewModel> {

    public ProductoVentaSelectorPresenter productoSelectorPresenter;

    public static final String ACTION_CANCELAR = "Cancelar";
    public static final String ACTION_ACEPTAR = "Aceptar";
    public static final String ACTION_ELIMINAR = "Eliminar";
    public static final String ACTION_MODO_AGREGO = "Agrego";

    private ReservaService service;

    public ReservaDetailViewPresenter(ReservaController service) {
        super(new ReservaDetailViewModel());
        this.service = service;
        setBeanData();
        refreshState();
        addListeners();
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
    }

    @Override
    protected Optional refreshState() {
        productoSelectorPresenter = new ProductoVentaSelectorPresenter(new OrdenController());
        getBean().setLista_mesas(new ArrayListModel<>(service.mesasDisponiblesParaReservar(getBean().getFecha())));
        return super.refreshState();
    }

    private void setBeanData() {
        Orden o = service.getReserva();
        getBean().setFecha(o.getVentafecha());
        getBean().setMesa_seleccionada(o.getMesacodMesa());
        getBean().setCliente(o.getClienteIdCliente());
        getBean().setLista_clientes(new ArrayListModel<>(service.getListaClientes()));
    }

    private void setListToBean() {
        getBean().setProducto_seleccionado(null);
        getBean().setLista_producto(new ArrayListModel<>(service.getReserva().getProductovOrdenList()));
    }

    private void addListeners() {
        getBean().addPropertyChangeListener(PROP_MESA_SELECCIONADA, (PropertyChangeEvent evt) -> {
            Mesa m = (Mesa) evt.getNewValue();
            if (m != null) {
                productoSelectorPresenter.setMesaSeleccionada(getBean().getMesa_seleccionada());
            }
            getBean().setShow_productos(m != null);
        });
        productoSelectorPresenter.addBeanPropertyChangeListener(PROP_PRODUCTOVENTASELECCIONADO, (PropertyChangeEvent evt) -> {
            ProductoVenta pv = (ProductoVenta) evt.getNewValue();
            if (pv != null) {
                ProductovOrden pvo = service.generarProductovOrden(pv, new NumberPad(null).showView());
                service.agregarProductoAOrden(getBean().isModo_agrego(), pvo, getBean().getProducto_seleccionado());
                setListToBean();
            }
        });
        getBean().addPropertyChangeListener(PROP_FECHA, (PropertyChangeEvent evt) -> {
            Date fecha = (Date) evt.getNewValue();
            if (fecha != null) {
                getBean().setLista_mesas(new ArrayListModel<>(service.mesasDisponiblesParaReservar(fecha)));
            }
        });
        getBean().addPropertyChangeListener(PROP_PRODUCTO_SELECCIONADO, (PropertyChangeEvent evt) -> {
            ProductovOrden p = (ProductovOrden) evt.getNewValue();
            if (p != null && p.getAgregadoA() == null) {
                getBean().setBotton_agrego_enabled(true);
            } else {
                getBean().setModo_agrego(false);
                getBean().setBotton_agrego_enabled(false);
            }
        }
        );
    }

    private void onEliminarClick() {
        if (getBean().getProducto_seleccionado() != null) {
            service.eliminarProDuctoDeOrden(getBean().getProducto_seleccionado());
            setListToBean();
        }
    }

    private void onAceptarClick() {
        if (getBean().getMesa_seleccionada() != null) {
            service.crearEditarReserva(
                    formatDate(),
                    getBean().getMesa_seleccionada(),
                    getBean().getCliente(),
                    getBean().getLista_producto());
        } else {
            JOptionPane.showMessageDialog(
                    null, "Debe selecionar la mesa que desea reservar", "AVISO", JOptionPane.ERROR_MESSAGE);
        }
        NavigationService.getInstance().navigateUp();
    }

    private Date formatDate() {
        int hora = getBean().getHora();
        int minutos = getBean().getMinutos();
        String pm_am = getBean().getPm_am();
        Date date = getBean().getFecha();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
        String input = sdf.format(date) + " " + hora + ":" + minutos + " " + pm_am;
        try {
            date = df.parse(input);
        } catch (ParseException ex) {
            Logger.getLogger(ReservaDetailViewPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date;
    }
}
