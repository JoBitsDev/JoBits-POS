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
import com.jobits.pos.core.usecase.algoritmo.Y;
import com.jobits.pos.controller.venta.OrdenService;
import com.jobits.pos.controller.venta.VentaDetailService;
import com.jobits.pos.controller.venta.VentaListService;
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
import com.root101.clean.core.domain.services.ResourceHandler;
import org.jobits.db.core.domain.TipoConexion;
import org.jobits.db.pool.ConnectionPoolHandler;

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
            ACTION_Y = "Y";

    private List<Venta> listaVentasTotales = new ArrayList();
    private List<List<Venta>> ventas = new ArrayList<>();
    private List<DayReviewWrapper<Venta>> ventasList = new ArrayList<>();

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
                            PosDesktopUiModule.getInstance().getImplementation(OrdenService.class), getBean().getDia_seleccionado().getLista_contenida().get(0).getId());
            Application.getInstance().getNavigator().navigateTo(VentaDetailView.VIEW_NAME, presenter);
        }
    }

    @Override
    protected void onEliminarClick() {
        Venta selected = getVentaFromCalendar();
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Esta seguro que desea eliminar la venta seleccionada",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            service.destroy(selected);
            updateBeanData();
            Application.getInstance().getNotificationService().notify(ResourceHandler.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
        }
    }

    private Venta getVentaFromCalendar() {
        if (getBean().getDia_seleccionado() != null) {
            if (getBean().getDia_seleccionado().getLista_contenida().size() == 1) {
                return getBean().getDia_seleccionado().getLista_contenida().get(0);
            } else {
                int seleccion = JOptionPane.showOptionDialog(null, "Seleccione la venta", "Seleccion",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
                        null, getBean().getDia_seleccionado().getLista_contenida().toArray(), getBean().getDia_seleccionado().getLista_contenida().get(0));
                if (seleccion != JOptionPane.CLOSED_OPTION) {
                    return getBean().getDia_seleccionado().getLista_contenida().get(seleccion);
                }
            }
        }
        return null;
    }

    private void onEjecutarY(Venta venta) {
        if (ConnectionPoolHandler.getConnectionPoolService(PosCoreModule.getInstance().getModuleName()).getCurrentUbicacion().getTipoUbicacion() != TipoConexion.MASTER) {
            throw new UnauthorizedAccessException("Esta operacion solo se puede ejecutar conectado a una ubicaion master");
        }
        Y alg = new Y(venta);
        Venta old = alg.getVentaReal();
        Venta newVenta = new Venta(alg.getVentaReal().getId());
        // if (!new BackUpService().ExisteVentaEnLocal(old)) {
        //   throw new ValidatingException("Primero debe realizar una copia de seguridad del dia seleccionado en su ordenador");
        // }
        try {
            VentaDAO.getInstance().startTransaction();
            service.destroy(old);
            newVenta.setAsistenciaPersonalList(new ArrayList<>());
            newVenta.setFecha(old.getFecha());
            newVenta.setOrdenList(alg.ejecutarAlgoritmo());
            newVenta.setGastoVentaList(new ArrayList<>());
            newVenta.setVentagastosPagotrabajadores(VentaDAO1.getValorTotalPagoTrabajadores(newVenta));
            newVenta.setVentagastosGastos((float) 0.0);
            newVenta.setVentaTotal((double) VentaDAO1.getValorTotalVentas(newVenta));
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
        ventasList = service.findVentasByMonth(getBean().getMes_seleccionado() + 1, getBean().getYear_seleccionado());
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
        for (DayReviewWrapper<Venta> venta : ventasList) {
            for (Venta x : venta.getLista_contenida()) {
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
        LicenceService controller = PosCoreModule.getInstance().getImplementation(LicenceService.class);
        switch(controller.getEstadoLicencia(Licence.TipoLicencia.SECUNDARIA)){
            case LicenceController.ERROR_ESCRITURA:
            case LicenceController.ERROR_LECTURA_LICENCIA:
            case LicenceController.LICENCIA_INVALIDA:
            case LicenceController.LICENCIA_NO_ENCONTRADA:
                visible = false;break;
        }
        getBean().setY_visible(visible);
    }
}
