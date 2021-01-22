/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.gasto.GastoOperacionController;
import com.jobits.pos.controller.venta.OrdenService;
import com.jobits.pos.controller.venta.VentaDetailService;
import com.jobits.pos.core.domain.models.Venta;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.TipoNotificacion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.gastos.presenter.GastosViewPresenter;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.trabajadores.presenter.AsistenciaPersonalPresenter;
import com.jobits.pos.ui.utils.LongProcessActionServiceImpl;
import com.jobits.pos.utils.utils;
import com.jobits.pos.ui.venta.orden.presenter.VentaOrdenListViewPresenter;
import static com.jobits.pos.ui.venta.presenter.VentaDetailViewModel.PROP_VENTA_SELECCIONADA;
import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Optional;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class VentaDetailViewPresenter extends AbstractViewPresenter<VentaDetailViewModel> {

    public static final String ACTION_IMPRIMIR_Z = "Imprimir z",
            ACTION_IMPRIMIR_AUTORIZOS = "Imprimir Autorizo",
            ACTION_TERMINAR_EXPORTAR = "Terminar y exportar",
            ACTION_TERMINAR_VENTAS = "Terminar Ventas",
            ACTION_REABRIR_VENTA = "Reabrir Venta",
            ACTION_REFRESCAR_VENTA = "Refrescar Venta",
            ACTION_CREAR_NUEVO_TURNO = "Crear Nueva Turno",
            //Area
            ACTION_IMPRIMIR_RESUMEN_AREA = "Imprimir resumen area",
            //Dpte
            ACTION_IMPRIMIR_RESUMEN_USUARIO = "Imprimir resumen dependiente",
            ACTION_IMPRIMIR_RESUMEN_USUARIO_COMISION = "Imprimir comision dependiente",
            //pto elab
            ACTION_IMPRIMIR_RESUMEN_PTO = "Imprimir Pto elaboracion";

    private VentaDetailService service;
    OrdenService ordenService;
    private VentaOrdenListViewPresenter ventaOrdenPresenter;
    private AsistenciaPersonalPresenter asistenciaPersonalPresenter;
    private GastosViewPresenter gastosPresenter;
    private List<Venta> ventas;

    /**
     *
     * @param controller
     * @param ordenController
     * @param ventas no pasar la lista vacia
     */
    public VentaDetailViewPresenter(
            VentaDetailService controller, OrdenService ordenController, List<Venta> ventas) {
        super(new VentaDetailViewModel());
        this.service = controller;
        this.ordenService = ordenController;
        this.ventas = ventas;

        addListeners();
        setListToBean();
//        this.ventaOrdenPresenter = new VentaOrdenListViewPresenter(controller, ordenController, getBean().getVenta_seleccionada().getId());
//        updateBeanData();
        new LongProcessActionServiceImpl("Creando IPVs.........") {
            @Override
            protected void longProcessMethod() {
                controller.initIPV(getBean().getVenta_seleccionada());
            }
        }.performAction(null);

    }

    public VentaDetailService getService() {
        return service;
    }

    public VentaOrdenListViewPresenter getVentaOrdenListViewPresenter() {
        return ventaOrdenPresenter;
    }

    public AsistenciaPersonalPresenter getAsistenciaPersonalPresenter() {
        return asistenciaPersonalPresenter;
    }

    public GastosViewPresenter getGastosPresenter() {
        return gastosPresenter;
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_IMPRIMIR_Z) {
            @Override
            public Optional doAction() {
                onImprimirZClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_REFRESCAR_VENTA) {
            @Override
            public Optional doAction() {
                service.fetchNewDataFromServer(getBean().getVenta_seleccionada().getId());
                updateBeanData();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_IMPRIMIR_AUTORIZOS) {
            @Override
            public Optional doAction() {
                onImprimirAutorizosClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_REABRIR_VENTA) {
            @Override
            public Optional doAction() {
                onReabrirVentaCLick();
                return Optional.empty();
            }

        });
        registerOperation(new AbstractViewAction(ACTION_TERMINAR_EXPORTAR) {
            @Override
            public Optional doAction() {
                onTerminarYExportarClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_TERMINAR_VENTAS) {
            @Override
            public Optional doAction() {
                onTerminarClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_IMPRIMIR_RESUMEN_AREA) {
            @Override
            public Optional doAction() {
                onImprimirResumenVentaAreaClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_IMPRIMIR_RESUMEN_USUARIO) {
            @Override
            public Optional doAction() {
                onImprimirResumenVentaUsuarioClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_IMPRIMIR_RESUMEN_PTO) {
            @Override
            public Optional doAction() {
                onImprimirResumenPtoElabClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_IMPRIMIR_RESUMEN_USUARIO_COMISION) {
            @Override
            public Optional doAction() {
                onImprimirResumenVentaUsuarioComisionClick();
                return Optional.empty();
            }

        });
        registerOperation(new AbstractViewAction(ACTION_CREAR_NUEVO_TURNO) {
            @Override
            public Optional doAction() {
                onCrearNuevoTurnoClick();
                return Optional.empty();
            }

        });

    }

    private void onImprimirZClick() {
        service.printZ(getBean().getVenta_seleccionada().getId());
    }

    private void onImprimirAutorizosClick() {
        service.printGastosCasa(getBean().getVenta_seleccionada().getId());
    }

    private void onTerminarClick() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Desea terminar el día de trabajo?",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            service.terminarVentas(getBean().getVenta_seleccionada().getId());
            Application.getInstance().getNotificationService().notify(R.RESOURCE_BUNDLE.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
            Application.getInstance().getNavigator().navigateUp();
        }
    }

    private void onTerminarYExportarClick() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Desea terminar el día de trabajo?",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            service.terminarYExportar(getBean().getFile_for_export(), getBean().getVenta_seleccionada().getId());
            Application.getInstance().getNotificationService().notify(R.RESOURCE_BUNDLE.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
            Application.getInstance().getNavigator().navigateUp();
        }
    }

    private void onReabrirVentaCLick() {
        service.reabrirVentas(getBean().getVenta_seleccionada().getId());
        Application.getInstance().getNotificationService().notify(R.RESOURCE_BUNDLE.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
    }

    private void onImprimirResumenVentaAreaClick() {
        service.printAreaResumen(getBean().getResumem_area_seleccionada().getArea(), getBean().getVenta_seleccionada().getId());

    }

    private void onImprimirResumenVentaUsuarioClick() {
        boolean imprimirValores = (boolean) Application.getInstance().getNotificationService().
                showDialog("Presione SI para imprimir los valores,"
                        + "\nNo para imprimir solo las cantidades",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false);
        service.printPersonalResumenRow(getBean().getResumen_usuario_seleccionado().getPersonal(),
                getBean().getVenta_seleccionada().getId(), imprimirValores);

    }

    private void onImprimirResumenVentaUsuarioComisionClick() {
        boolean imprimirValores = (boolean) Application.getInstance().getNotificationService().
                showDialog("Presione SI para imprimir los valores,"
                        + "\nNo para imprimir solo las cantidades",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false);
        service.printPagoPorVentaPersonal(getBean().getResumen_usuario_seleccionado().getPersonal(),
                getBean().getVenta_seleccionada().getId(), imprimirValores);

    }

    private void onImprimirResumenPtoElabClick() {
        boolean imprimirValores = (boolean) Application.getInstance().getNotificationService().
                showDialog("Presione SI para imprimir los valores,"
                        + "\nNo para imprimir solo las cantidades",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false);
        service.printCocinaResumen(getBean().getResumen_pto_seleccionado().getCodigoPto(),
                getBean().getVenta_seleccionada().getId(), imprimirValores);
    }

    private void onCrearNuevoTurnoClick() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Desea terminar el turno?", TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            new LongProcessActionServiceImpl("Creando IPVs.........") {
                @Override
                protected void longProcessMethod() {
                    Venta venta = service.cambiarTurno(getBean().getVenta_seleccionada(), Application.getInstance().getLoggedUser());
                    if (venta != null) {
                        getBean().getList_ventas().add(venta);
                        getBean().setVenta_seleccionada(venta);
                    }
                }
            }.performAction(null);
            Application.getInstance().getNotificationService().notify(R.RESOURCE_BUNDLE.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
        }
    }
    
    private void updateBeanData() {
        if (getBean().getVenta_seleccionada() != null) {
            int codVenta = getBean().getVenta_seleccionada().getId();
            if (ventaOrdenPresenter == null) {
                ventaOrdenPresenter = new VentaOrdenListViewPresenter(service, ordenService, codVenta);
            }
            ventaOrdenPresenter.setCodVenta(codVenta);
            service.fetchNewDataFromServer(codVenta);
            Venta v = service.getInstance(codVenta);
            if (asistenciaPersonalPresenter == null) {
                asistenciaPersonalPresenter = new AsistenciaPersonalPresenter(v);
            }
            asistenciaPersonalPresenter.setVenta(v);
            if (gastosPresenter == null) {
                gastosPresenter = new GastosViewPresenter(new GastoOperacionController(v));
            }
            gastosPresenter.setVenta(v);
            getBean().setVentaInstance(v);
            getBean().setLista_resumen_area_venta(service.getResumenPorAreaVenta(codVenta));
            getBean().setLista_resumen_pto_venta(service.getResumenPorPtoVenta(codVenta));
            getBean().setLista_resumen_usuario_venta(service.getResumenPorUsuarioVenta(codVenta));
            getBean().setPropina_total("" + utils.setDosLugaresDecimalesFloat(service.getTotalPropina(codVenta)));
            getBean().setReabrir_ventas_enabled(service.canReabrirVenta(codVenta));
            getBean().setTotal_autorizos(service.getTotalAutorizos(codVenta));
            getBean().setTotal_gasto_insumos(service.getTotalGastadoInsumos(codVenta));
            getBean().setTotal_gasto_otros(service.getTotalGastos(codVenta));
            getBean().setTotal_gasto_salario(service.getTotalPagoTrabajadores(codVenta));
            getBean().setVenta_neta(service.getTotalVendidoNeto(codVenta));
            getBean().setVenta_total(service.getTotalVendido(codVenta));
            getBean().setFecha(R.DATE_FORMAT.format(v.getFecha()));
            getBean().setCambiar_turno_enabled(service.canOpenNuevoTurno(getBean().getVenta_seleccionada().getFecha()));
        }

    }

    private void addListeners() {
        addBeanPropertyChangeListener(PROP_VENTA_SELECCIONADA, (PropertyChangeEvent evt) -> {
            if (evt.getNewValue() != null) {
                updateBeanData();
            }
        });
    }

    private void setListToBean() {
        if (!ventas.isEmpty()) {
            getBean().setList_ventas(new ArrayListModel<>(ventas));
            getBean().setVenta_seleccionada(getBean().getList_ventas().get(ventas.size() - 1));
        }
    }

}
