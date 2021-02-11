/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.presenter;

import com.jobits.pos.core.repo.impl.VentaDAO;
import com.jobits.pos.core.usecase.algoritmo.Y;
import com.jobits.pos.controller.venta.OrdenService;
import com.jobits.pos.controller.venta.VentaDetailService;
import com.jobits.pos.controller.venta.VentaListService;
import org.jobits.app.repo.UbicacionConexionModel;
import com.jobits.pos.core.domain.VentaDAO1;
import com.jobits.pos.core.domain.models.Venta;
import com.jobits.pos.exceptions.UnauthorizedAccessException;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.TipoNotificacion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractListViewPresenter;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.utils.utils;
import com.jobits.pos.ui.venta.VentaDetailView;
import com.jobits.pos.ui.venta.VentaResumenView;
import java.beans.PropertyChangeEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.swing.JOptionPane;
import com.jobits.pos.controller.venta.VentaResumenServiceOld;

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

    private List<Venta> listaVentasTotales = new ArrayList();
    private List<List<Venta>> ventas = new ArrayList<>();

    public VentaCalendarViewPresenter(VentaListService controller) {
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
                    Venta v = getVentaFromCalendar();
                    if (v != null) {
                        Application.getInstance().getBackgroundWorker().processInBackground("Ejecutando Y", () -> {
                            onEjecutarY(v);
                        });
                    }
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
            VentaDetailService ventaController = PosDesktopUiModule.getInstance().getImplementation(VentaDetailService.class);
            VentaDetailViewPresenter presenter
                    = new VentaDetailViewPresenter(ventaController,
                            PosDesktopUiModule.getInstance().getImplementation(OrdenService.class), getBean().getDia_seleccionado());
            Application.getInstance().getNavigator().navigateTo(VentaDetailView.VIEW_NAME, presenter);
        }
    }

    @Override
    protected void onEliminarClick() {
        Venta v = getVentaFromCalendar();
        service.destroy(v);
        updateBeanData();
        Application.getInstance().getNotificationService().notify(R.RESOURCE_BUNDLE.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
    }

    private Venta getVentaFromCalendar() {
        if (getBean().getDia_seleccionado() != null) {
            if (getBean().getDia_seleccionado().size() == 1) {
                return getBean().getDia_seleccionado().get(0);
            } else {
                int seleccion = JOptionPane.showOptionDialog(null, "Seleccione la venta", "Seleccion",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
                        null, getBean().getDia_seleccionado().toArray(), getBean().getDia_seleccionado().get(0));
                if (seleccion != JOptionPane.CLOSED_OPTION) {
                    return getBean().getDia_seleccionado().get(seleccion);
                }
            }
        }
        return null;
    }

    private void onResumenDetalladoClick() {
//        if (getBean().getResumen_desde() == null || getBean().getResumen_hasta() == null) {
//            throw new NoSelectedException();
//        }
//        if (getBean().getResumen_hasta().compareTo(getBean().getResumen_hasta()) < 0) {
//            throw new ValidatingException();
//        }
        Application.getInstance().getBackgroundWorker().processInBackground(() -> {
//            VentaDetailController ventaController = service.createDetailResumenView(getBean().getResumen_desde(), getBean().getResumen_hasta());//TODO devolver valor e invocar al navigator
            VentaResumenViewPresenter presenter = new VentaResumenViewPresenter(PosDesktopUiModule.getInstance().getImplementation(VentaResumenServiceOld.class));
            Application.getInstance().getNavigator().navigateTo(VentaResumenView.VIEW_NAME, presenter);
        });
    }

    private void onEjecutarY(Venta venta) {
        if (R.CURRENT_CONNECTION.getTipoUbicacion() != UbicacionConexionModel.TipoUbicacion.MASTER) {
            throw new UnauthorizedAccessException("Esta operacion solo se puede ejecutar conectado a una ubicaion master");
        }
        Y alg = new Y(venta);
        Venta old = alg.getVentaReal();
        Venta newVenta = new Venta(alg.getVentaReal().getId());
        // if (!new BackUpService().ExisteVentaEnLocal(old)) {
        //   throw new ValidatingException("Primero debe realizar una copia de seguridad del dia seleccionado en su ordenador");
        // }
        try {
            service.destroy(old, true);
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
            e.printStackTrace();
            Application.getInstance().getNotificationService().showDialog("La operacion no se ha podido completar correctamente. Contacte con soporte", TipoNotificacion.ERROR);

        }
        if (VentaDAO.getInstance().find(old.getId()) == null) {
            VentaDAO.getInstance().startTransaction();
            VentaDAO.getInstance().create(old);
            VentaDAO.getInstance().commitTransaction();
            Application.getInstance().getNotificationService().showDialog("La operacion no se ha podido completar correctamente. Contacte con soporte", TipoNotificacion.ERROR);
        }

    }

    private void onImportarVentaClick() {
        VentaDetailService control = PosDesktopUiModule.getInstance().getImplementation(VentaDetailService.class);
        Application.getInstance().getBackgroundWorker().processInBackground("Importando venta", () -> {
            control.importarVenta(getBean().getArchivo_a_importar());
            updateBeanData();
        });

    }

    @Override
    protected void setListToBean() {
        ventas = service.findVentas(getBean().getMes_seleccionado() + 1, getBean().getYear_seleccionado());
        getBean().setLista_elementos(ventas);
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
        for (List<Venta> venta : ventas) {
            for (Venta x : venta) {
                listaVentasTotales.add(x);
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
        }
        double promedio = suma / cantidad;

        //TODO logica en el presenter
        getBean().setTotal_ventas_intervalo(utils.setDosLugaresDecimales((float) suma));
        getBean().setPromedio_ventas_intervalo(utils.setDosLugaresDecimales((float) promedio));
        getBean().setGasto_insumo_intervalo(utils.setDosLugaresDecimales((float) gInsumos));
        getBean().setGasto_trabajadores_intervalo(utils.setDosLugaresDecimales((float) (gTrabajadores)));
        getBean().setGasto_otros_intervalo(utils.setDosLugaresDecimales((float) gGastos));
        int hora_pico_promedio = VentaDAO1.getModalPickHour(listaVentasTotales);
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
        LocalDate date = LocalDate.of(getBean().getYear_seleccionado(), getBean().getMes_seleccionado() + 1, 1);
        return date.getDayOfWeek().getValue() - 1;
    }
}
