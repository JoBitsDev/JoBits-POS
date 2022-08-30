/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.presenter;

import com.jobits.pos.controller.licencia.LicenceService;
import com.jobits.pos.controller.licencia.impl.Licence;
import com.jobits.pos.controller.licencia.impl.LicenceController;
import com.jobits.pos.core.repo.impl.VentaDAO;
import com.jobits.pos.core.usecase.algoritmo.Yimpl;
import com.jobits.pos.controller.venta.OrdenService;
import com.jobits.pos.controller.venta.VentaDetailService;
import com.jobits.pos.core.domain.VentaDAO1;
import com.jobits.pos.core.domain.models.Venta;
import com.jobits.pos.core.domain.models.temporal.DayReviewWrapper;
import com.jobits.pos.exceptions.UnauthorizedAccessException;
import com.jobits.pos.main.Application;
import com.root101.clean.core.app.services.utils.TipoNotificacion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractListViewPresenter;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.utils.utils;
import com.jobits.pos.ui.venta.VentaDetailView;
import java.beans.PropertyChangeEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.swing.JOptionPane;
import com.jobits.pos.core.module.PosCoreModule;
import com.jobits.pos.core.usecase.algoritmo.Y;
import com.root101.clean.core.domain.services.ResourceHandler;
import org.jobits.db.core.domain.TipoConexion;
import org.jobits.db.pool.ConnectionPoolHandler;
import com.jobits.pos.controller.venta.VentaCalendarResumeUseCase;
import com.jobits.pos.core.domain.models.temporal.ResumenVentaEstadisticas;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class VentaCalendarViewPresenter extends AbstractListViewPresenter<VentaCalendarViewModel> {

    private final VentaCalendarResumeUseCase service;

    public static final String ACTION_IMPORTAR_VENTA = "Importar venta",
            ACTION_Y = "Y";

    private List<ResumenVentaEstadisticas> listaVentasTotales = new ArrayList();
    private List<List<Venta>> ventas = new ArrayList<>();
    private List<DayReviewWrapper<ResumenVentaEstadisticas>> ventasList = new ArrayList<>();

    public VentaCalendarViewPresenter(VentaCalendarResumeUseCase controller) {
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
                    int v = getIdVentaFromCalendar();
                    if (v != -1) {
                        Application.getInstance().getBackgroundWorker().processInBackground("Ejecutando Y", () -> {
                            onEjecutarY(v);
                        });
                        updateBeanData();
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
                            PosDesktopUiModule.getInstance().getImplementation(OrdenService.class), getBean().getDia_seleccionado().getLista_contenida().get(0).getIdVenta());
            Application.getInstance().getNavigator().navigateTo(VentaDetailView.VIEW_NAME, presenter);
        }
    }

    @Override
    protected void onEliminarClick() {
        int selected = getIdVentaFromCalendar();
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Esta seguro que desea eliminar la venta seleccionada",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            service.destroyById(selected);
            updateBeanData();
            Application.getInstance().getNotificationService().notify(ResourceHandler.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
        }
    }

    private int getIdVentaFromCalendar() {
        if (getBean().getDia_seleccionado() != null) {
            if (getBean().getDia_seleccionado().getLista_contenida().size() == 1) {
                return getBean().getDia_seleccionado().getLista_contenida().get(0).getIdVenta();
            } else {
                int seleccion = JOptionPane.showOptionDialog(null, "Seleccione la venta", "Seleccion",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
                        null, getBean().getDia_seleccionado().getLista_contenida().toArray(), getBean().getDia_seleccionado().getLista_contenida().get(0));
                if (seleccion != JOptionPane.CLOSED_OPTION) {
                    return getBean().getDia_seleccionado().getLista_contenida().get(seleccion).getIdVenta();
                }
            }
        }
        return -1;
    }

    private void onEjecutarY(int venta) {
        Y alg = PosDesktopUiModule.getInstance().getImplementation(Y.class);
        alg.runMainThread(venta);

    }

    private void onImportarVentaClick() {
        VentaDetailService control = PosDesktopUiModule.getInstance().getImplementation(VentaDetailService.class);
        Application.getInstance().getBackgroundWorker().processInBackground("Importando venta", () -> {
         //   control.importarVenta(getBean().getArchivo_a_importar());
            updateBeanData();
        });

    }

    @Override
    protected void setListToBean() {
        ventasList = service.findVentasByMonthView(getBean().getMes_seleccionado() + 1, getBean().getYear_seleccionado());
        getBean().setLista_elementos(ventasList);
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
        for (DayReviewWrapper<ResumenVentaEstadisticas> venta : ventasList) {
            for (ResumenVentaEstadisticas x : venta.getLista_contenida()) {
                listaVentasTotales.add(x);
                suma += x.getTotalVendido();
                gInsumos += x.getTotalCostoVenta();
                gGastos += x.getTotalGastos();
                gTrabajadores += x.getTotalPagoTrabajadores();
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
        int hora_pico_promedio = VentaDAO1.getModalPickHourEstadisticas(listaVentasTotales);
        getBean().setHora_pico_intervalo(hora_pico_promedio > 12 ? (hora_pico_promedio - 12) + " PM" : hora_pico_promedio + " AM");
        setYvisibility();
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

    private void setYvisibility() {
        boolean visible = true;
        LicenceService controller = PosDesktopUiModule.getInstance().getImplementation(LicenceService.class);
        switch (controller.getEstadoLicencia(Licence.TipoLicencia.SECUNDARIA)) {
            case LicenceController.ERROR_ESCRITURA:
            case LicenceController.ERROR_LECTURA_LICENCIA:
            case LicenceController.LICENCIA_INVALIDA:
            case LicenceController.LICENCIA_NO_ENCONTRADA:
                visible = false;
                break;
        }
        getBean().setY_visible(visible);
    }
}
