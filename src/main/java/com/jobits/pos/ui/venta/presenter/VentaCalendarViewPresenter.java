/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.presenter;

import com.jobits.pos.adapters.repo.impl.VentaDAO;
import com.jobits.pos.algoritmo.Y;
import com.jobits.pos.controller.venta.OrdenController;
import com.jobits.pos.controller.venta.VentaDetailController;
import com.jobits.pos.controller.venta.VentaListController;
import com.jobits.pos.controller.venta.VentaListService;
import com.jobits.pos.domain.UbicacionConexionModel;
import com.jobits.pos.domain.VentaDAO1;
import com.jobits.pos.domain.models.Venta;
import com.jobits.pos.exceptions.NoSelectedException;
import com.jobits.pos.exceptions.UnauthorizedAccessException;
import com.jobits.pos.exceptions.ValidatingException;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.TipoNotificacion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.LongProcessMethod;
import com.jobits.pos.ui.presenters.AbstractListViewPresenter;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.utils.utils;
import com.jobits.pos.ui.venta.VentaDetailView;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class VentaCalendarViewPresenter extends AbstractListViewPresenter<VentaCalendarViewModel> {

    private final VentaListService service;

    public static final String ACTION_IMPORTAR_VENTA = "Importar venta",
            ACTION_Y = "Y",
            ACTION_RESUMEN_DETALLADO = "Resumen detallado";

    public VentaCalendarViewPresenter(VentaListController controller) {
        super(new VentaCalendarViewModel(), "Ventas");
        this.service = controller;
        updateBeanData();
        addListeners();

    }

    @Override
    protected void registerOperations() {
        super.registerOperations(); //To change body of generated methods, choose Tools | Templates.
        registerOperation(new AbstractViewAction(ACTION_Y) {
            @Override
            public Optional doAction() {
                boolean confirmed = (boolean) Application.getInstance().getNotificationService().showDialog("Confirme la operación", TipoNotificacion.DIALOG_CONFIRM).orElse(false);
                if (confirmed) {
                    Application.getInstance().getBackgroundWorker().processInBackground("Ejecutando Y", () -> {
                        onEjecutarY(getBean().getDia_seleccionado());
                    });
                }
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_IMPORTAR_VENTA) {
            @Override
            public Optional doAction() {
                onImportarVentaClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_RESUMEN_DETALLADO) {
            @Override
            public Optional doAction() {
                onResumenDetalladoClick();
                return Optional.empty();
            }
        });
    }

    @Override
    protected void onAgregarClick() {
        throw new IllegalStateException("Bad call on create in venta calendar view");
    }

    @Override
    protected void onEditarClick() {//TODO vista
        if (getBean().getDia_seleccionado() != null) {
            VentaDetailController ventaController = new VentaDetailController(getBean().getDia_seleccionado());
            VentaResumenViewPresenter presenter
                    = new VentaResumenViewPresenter(ventaController,
                            new OrdenController(ventaController.getInstance()));
            Application.getInstance().getNavigator().navigateTo(VentaDetailView.VIEW_NAME, presenter);
        }
    }

    @Override
    protected void onEliminarClick() {
        if (getBean().getDia_seleccionado() != null) {
            service.destroy(getBean().getDia_seleccionado());
            updateBeanData();
        }
    }

    private void onResumenDetalladoClick() {
        if (getBean().getResumen_desde() == null || getBean().getResumen_hasta() == null) {
            throw new NoSelectedException();
        }
        if (getBean().getResumen_hasta().compareTo(getBean().getResumen_hasta()) < 0) {
            throw new ValidatingException();
        }
        Application.getInstance().getBackgroundWorker().processInBackground(() -> {
            VentaDetailController ventaController = service.createDetailResumenView(getBean().getResumen_desde(), getBean().getResumen_hasta());//TODO devolver valor e invocar al navigator
            VentaResumenViewPresenter presenter = new VentaResumenViewPresenter(ventaController, null);
            Application.getInstance().getNavigator().navigateTo(VentaDetailView.VIEW_NAME, presenter);
        });
    }

    private void onEjecutarY(Venta venta) {
        if (R.CURRENT_CONNECTION.getTipoUbicacion() != UbicacionConexionModel.TipoUbicacion.MASTER) {
            throw new UnauthorizedAccessException("Esta operacion solo se puede ejecutar conectado a una ubicaion master");
        }
        Y alg = new Y(venta);
        Venta old = alg.getVentaReal();
        Venta newVenta = new Venta();
        // if (!new BackUpService().ExisteVentaEnLocal(old)) {
        //   throw new ValidatingException("Primero debe realizar una copia de seguridad del dia seleccionado en su ordenador");
        // }
        try {
            service.destroy(VentaDAO.getInstance().find(old.getFecha()), true);
            newVenta.setAsistenciaPersonalList(new ArrayList<>());
            newVenta.setFecha(old.getFecha());
            newVenta.setOrdenList(alg.ejecutarAlgoritmo());
            newVenta.setGastoVentaList(new ArrayList<>());
            newVenta.setVentagastosPagotrabajadores(VentaDAO1.getValorTotalPagoTrabajadores(newVenta));
            newVenta.setVentagastosGastos((float) 0.0);
            newVenta.setVentaTotal((double) VentaDAO1.getValorTotalVentas(newVenta));
            VentaDAO.getInstance().startTransaction();
            VentaDAO.getInstance().create(newVenta);
            VentaDAO.getInstance().commitTransaction();
        } catch (Exception e) {
            VentaDAO.getInstance().startTransaction();
            VentaDAO.getInstance().create(old);
            VentaDAO.getInstance().commitTransaction();
            Application.getInstance().getNotificationService().showDialog("La operacion no se ha podido completar correctamente. Contacte con soporte", TipoNotificacion.ERROR);

        }
        if (VentaDAO.getInstance().find(old.getFecha()) == null) {
            VentaDAO.getInstance().startTransaction();
            VentaDAO.getInstance().create(old);
            VentaDAO.getInstance().commitTransaction();
            Application.getInstance().getNotificationService().showDialog("La operacion no se ha podido completar correctamente. Contacte con soporte", TipoNotificacion.ERROR);
        }

    }

    private void onImportarVentaClick() {
        VentaDetailController control = new VentaDetailController();
        if (getBean().getArchivo_a_importar() != null) {
            Application.getInstance().getBackgroundWorker().processInBackground("Importando venta", () -> {
                control.importarVenta(getBean().getArchivo_a_importar());
                updateBeanData();
            });
        }

    }

    @Override
    protected void setListToBean() {
        getBean().setLista_elementos(service.findVentas(getBean().getMes_seleccionado(), getBean().getYear_seleccionado()));

    }

    private void updateBeanData() {
        setListToBean();
        getBean().setElemento_seleccionado(null);
        getBean().setDia_seleccionado(null);
        getBean().setMonth_offset(calculateMonthOffset());
        double suma = 0;
        double gInsumos = 0;
        double gGastos = 0;
        double gTrabajadores = 0;
        int cantidad = 0;
        for (Venta x : getBean().getLista_elementos()) {
            if (x.getVentaTotal() != null) {
                suma += x.getVentaTotal();
                if (x.getVentagastosEninsumos() != null) {
                    gInsumos += x.getVentagastosEninsumos();
                }
                if (x.getVentagastosGastos() != null) {
                    gGastos += x.getVentagastosGastos();
                }
                if (x.getVentagastosPagotrabajadores() != null) {
                    gTrabajadores += x.getVentagastosPagotrabajadores();
                }
                cantidad++;
            }
        }
        double promedio = suma / cantidad;

        //TODO logica en el presenter
        getBean().setTotal_ventas_intervalo(utils.setDosLugaresDecimales((float) suma));
        getBean().setPromedio_ventas_intervalo(utils.setDosLugaresDecimales((float) promedio));
        getBean().setGasto_insumo_intervalo(utils.setDosLugaresDecimales((float) gInsumos));
        getBean().setGasto_trabajadores_intervalo(utils.setDosLugaresDecimales((float) (gTrabajadores)));
        getBean().setGasto_otros_intervalo(utils.setDosLugaresDecimales((float) gGastos));
        int hora_pico_promedio = VentaDAO1.getModalPickHour(getBean().getLista_elementos());
        getBean().setHora_pico_intervalo(hora_pico_promedio > 12 ? (hora_pico_promedio - 12) + " PM" : hora_pico_promedio + " AM");
        getBean().setY_visible(service.isYVisible());
    }

    private void addListeners() {
        getBean().addPropertyChangeListener(VentaCalendarViewModel.PROP_RESUMEN_DESDE, (PropertyChangeEvent evt) -> {
            getBean().setResumen_hasta((Date) evt.getNewValue());
        });
        getBean().addPropertyChangeListener(VentaCalendarViewModel.PROP_MES_SELECCIONADO, (PropertyChangeEvent evt) -> {
            updateBeanData();
        });
        getBean().addPropertyChangeListener(VentaCalendarViewModel.PROP_YEAR_SELECCIONADO, (PropertyChangeEvent evt) -> {
            updateBeanData();
        });
    }

    private int calculateMonthOffset() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.YEAR, getBean().getYear_seleccionado());
        cal.set(Calendar.MONTH, getBean().getMes_seleccionado());
        int monthOffset = cal.get(Calendar.DAY_OF_WEEK) - 2;//TODO trabajar desde el bean
        if (monthOffset == -1) {
            monthOffset = 6;
        }
        return monthOffset;
    }
}
