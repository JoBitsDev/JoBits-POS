/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.configuracion.ConfiguracionService;
import com.jobits.pos.controller.venta.OrdenService;
import com.jobits.pos.controller.venta.VentaDetailService;
import com.jobits.pos.controller.venta.resumen.VentaResumenUseCase;
import com.jobits.pos.core.domain.VentaResourcesWrapper;
import com.jobits.pos.core.domain.VentaResumenWrapper;
import com.jobits.pos.core.domain.models.Personal;
import com.jobits.pos.main.Application;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.gastos.presenter.GastosViewPresenter;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.trabajadores.presenter.AsistenciaPersonalPresenter;
import com.jobits.pos.ui.utils.LongProcessActionServiceImpl;
import com.jobits.pos.ui.venta.orden.presenter.VentaOrdenListViewPresenter;
import com.jobits.pos.utils.utils;
import com.root101.clean.core.app.services.UserResolver;
import com.root101.clean.core.app.services.utils.TipoNotificacion;
import com.root101.clean.core.domain.services.ResourceHandler;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.jobits.pos.ui.trabajadores.presenter.AsistenciaPersonalPresenter.PROP_VALUE_CHANGED;
import static com.jobits.pos.ui.venta.presenter.VentaDetailViewModel.*;

/**
 * JoBits
 *
 * @author Jorge
 */
public class VentaDetailViewPresenter extends AbstractViewPresenter<VentaDetailViewModel> {

    public static final String ACTION_IMPRIMIR_Z = "Imprimir z", ACTION_IMPRIMIR_AUTORIZOS = "Imprimir Autorizo", ACTION_TERMINAR_EXPORTAR = "Terminar y exportar", ACTION_TERMINAR_VENTAS = "Terminar Ventas", ACTION_REABRIR_VENTA = "Reabrir Venta", ACTION_REFRESCAR_VENTA = "Refrescar Venta", ACTION_CREAR_NUEVO_TURNO = "Crear Nuevo Turno", ACTION_CAMBIAR_TURNO = "Cambiar Turno", //Area
            ACTION_IMPRIMIR_RESUMEN_AREA = "Imprimir resumen area", //Dpte
            ACTION_IMPRIMIR_RESUMEN_USUARIO = "Imprimir resumen dependiente", ACTION_IMPIMIR_RESUMEN_MESA = "Imprimir resumen mesa", ACTION_IMPRIMIR_RESUMEN_USUARIO_COMISION = "Imprimir comision dependiente", ACTION_IMPRIMIR_RESUMEN_COMISION_PORCENTUAL = "Imprimir comision Porcentual", //pto elab
            ACTION_IMPRIMIR_RESUMEN_PTO = "Imprimir Pto elaboracion";
    public static final String PROP_HIDE_PANEL = "Ocultar Paneles";
    OrdenService ordenService;
    VentaResourcesWrapper ventaResources;
    VentaResumenWrapper resumen;
    private VentaDetailService service;
    private VentaResumenUseCase ventaResumenService = PosDesktopUiModule.getInstance().getImplementation(VentaResumenUseCase.class);
    private VentaOrdenListViewPresenter ventaOrdenPresenter;
    private AsistenciaPersonalPresenter asistenciaPersonalPresenter;
    private GastosViewPresenter gastosPresenter;

    /**
     * @param controller
     * @param ordenController
     * @param idVenta         no pasar la lista vacia
     */
    public VentaDetailViewPresenter(VentaDetailService controller, OrdenService ordenController, int idVenta) {
        super(new VentaDetailViewModel());
        this.service = controller;
        this.ordenService = ordenController;
        getBean().setVenta_seleccionada(idVenta);
        addListeners();
        updateBeanData();
        asistenciaPersonalPresenter.addPropertyChangeListener(PROP_VALUE_CHANGED, (PropertyChangeEvent evt) -> {
            updateBeanData();
        });
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
//                service.fetchNewDataFromServer(getBean().getVenta_seleccionada().getId());
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
        registerOperation(new AbstractViewAction(ACTION_IMPRIMIR_RESUMEN_COMISION_PORCENTUAL) {
            @Override
            public Optional doAction() {
                onImprimirResumenComisionPorcentualClick();
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
        registerOperation(new AbstractViewAction(ACTION_CAMBIAR_TURNO) {
            @Override
            public Optional doAction() {
                onCambiarTurnoClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_IMPIMIR_RESUMEN_MESA) {
            @Override
            public Optional doAction() {
                onImpimirResumenMesaClick();
                return Optional.empty();
            }

        });
        getBean().addPropertyChangeListener(PROP_PROPINA_TOTAL, (PropertyChangeEvent evt) -> {
            onCambiarPropina();
        });

    }

    private void onImprimirZClick() {
        service.printZ(getBean().getVenta_seleccionada());
    }

    private void onImprimirAutorizosClick() {
        service.printGastosCasa(getBean().getVenta_seleccionada());
    }

    private void onCambiarPropina() {
        var v = service.findBy(getBean().getVenta_seleccionada());
        v.setVentapropina(Float.parseFloat(getBean().getPropina_total()));
        service.setPropina(v.getId(), v.getVentapropina());
    }

    private void onTerminarClick() {
        if ((boolean) Application.getInstance().getNotificationService().showDialog("Desea terminar el día de trabajo?", TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            service.terminarVentas(getBean().getVenta_seleccionada());
            Application.getInstance().getNotificationService().notify(ResourceHandler.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
            Application.getInstance().getNavigator().navigateUp();
        }
    }

    private void onTerminarYExportarClick() {
        if ((boolean) Application.getInstance().getNotificationService().showDialog("Desea terminar el día de trabajo?", TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            //  service.terminarYExportar(getBean().getFile_for_export(), getBean().getVenta_seleccionada());
            Application.getInstance().getNotificationService().notify(ResourceHandler.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
            Application.getInstance().getNavigator().navigateUp();
        }
    }

    private void onReabrirVentaCLick() {
        service.reabrirVentas(getBean().getVenta_seleccionada());
        Application.getInstance().getNotificationService().notify(ResourceHandler.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
    }

    private void onImprimirResumenVentaAreaClick() {
        var area = getBean().getArea_seleccionada();
        area = area.split("\\[")[1].trim();
        area = area.substring(0, area.length() - 1);
        area = area.trim();
        ventaResumenService.printAreaResumen(area, getBean().getVenta_seleccionada());
    }

    private void onImprimirResumenVentaUsuarioClick() {
        boolean imprimirValores = (boolean) Application.getInstance().getNotificationService().showDialog("Presione SI para imprimir los valores," + "\nNo para imprimir solo las cantidades", TipoNotificacion.DIALOG_CONFIRM).orElse(false);

        ventaResumenService.printPersonalResumenRow(getBean().getPersonal_seleccionado(), getBean().getVenta_seleccionada(), imprimirValores);

    }

    private void onImprimirResumenVentaUsuarioComisionClick() {
        boolean imprimirValores = (boolean) Application.getInstance().getNotificationService().showDialog("Presione SI para imprimir los valores," + "\nNo para imprimir solo las cantidades", TipoNotificacion.DIALOG_CONFIRM).orElse(false);

        service.printPagoPorVentaPersonal(getBean().getPersonal_seleccionado(), getBean().getVenta_seleccionada(), imprimirValores);

    }

    private void onImprimirResumenPtoElabClick() {
        boolean imprimirValores = (boolean) Application.getInstance().getNotificationService().showDialog("Presione SI para imprimir los valores," + "\nNo para imprimir solo las cantidades", TipoNotificacion.DIALOG_CONFIRM).orElse(false);
        var punto = getBean().getCocina_seleccionada().split("\\(")[1];
        punto = punto.substring(0, punto.length() - 1);

        ventaResumenService.printCocinaResumen(punto, getBean().getVenta_seleccionada(), imprimirValores);
    }

    private void onImpimirResumenMesaClick() {
        var mesa = getBean().getMesa_seleccionada().split("]")[0];
        mesa = mesa.substring(1);
        mesa = mesa.trim();
        ventaResumenService.printMesaResumen(mesa, getBean().getVenta_seleccionada());
    }

    private void onImprimirResumenComisionPorcentualClick() {
        var mesa = getBean().getMesa_seleccionada().split("]")[0];
        mesa = mesa.substring(1);
        mesa = mesa.trim();
        service.printComisionPorcentualResumen(mesa, getBean().getVenta_seleccionada());
    }

    private void onCambiarTurnoClick() {
        var listaVentas = service.getVentasDeFecha(getBean().getVenta_seleccionada());
        if (listaVentas.size() > 1) {
            int seleccion = JOptionPane.showOptionDialog(null, "Seleccione la venta", "Seleccion", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, listaVentas.toArray(), getBean().getVenta_seleccionada());
            if (seleccion != JOptionPane.CLOSED_OPTION) {
                getBean().setVenta_seleccionada(listaVentas.get(seleccion).getId());
                updateBeanData();
            }
        } else {
            Application.getInstance().getNotificationService().showDialog("No existen turnos para cambiar", TipoNotificacion.ERROR);
        }

    }

    private void onCrearNuevoTurnoClick() {
        if (service.canOpenNuevoTurno(getBean().getVenta_seleccionada()) == 1) {
            if ((boolean) Application.getInstance().getNotificationService().showDialog("Desea terminar el turno?", TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
                new LongProcessActionServiceImpl("Creando IPVs.........") {
                    @Override
                    protected void longProcessMethod() {//TODO: No se muestran las ecepciones que se lanzan dentro de este metodo
                        var newTurno = service.cambiarTurno(getBean().getVenta_seleccionada());
                        //ventas = service.getVentasDeFecha(getBean().getVenta_seleccionada().getFecha());
                        getBean().setVenta_seleccionada(newTurno.getId());
                        updateBeanData();
                    }
                }.performAction(null);
                Application.getInstance().getNotificationService().notify(ResourceHandler.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
            }
        } else {
            Application.getInstance().getNotificationService().showDialog("No se pueden crear mas turnos", TipoNotificacion.ERROR);
        }

    }

    private void updateBeanData() {
        if (getBean().getVenta_seleccionada() == -1) {
            return;
        }
        var v = service.findBy(getBean().getVenta_seleccionada());
        if (ventaOrdenPresenter != null) {
            ventaOrdenPresenter.setCodVenta(getBean().getVenta_seleccionada());
        } else {
            ventaOrdenPresenter = new VentaOrdenListViewPresenter(service, ordenService, getBean().getVenta_seleccionada());
        }
        if (asistenciaPersonalPresenter != null) {
            asistenciaPersonalPresenter.setIdVenta(getBean().getVenta_seleccionada());
        } else {
            asistenciaPersonalPresenter = new AsistenciaPersonalPresenter(getBean().getVenta_seleccionada());
        }
        gastosPresenter = new GastosViewPresenter(v);

//            getBean().setLista_resumen_area_venta(service.getResumenPorAreaVenta(getBean().getVenta_seleccionada()));
//            getBean().setLista_resumen_pto_venta(service.getResumenPorPtoVenta(getBean().getVenta_seleccionada()));
//            getBean().setLista_resumen_usuario_venta(service.getResumenPorUsuarioVenta(getBean().getVenta_seleccionada()));
//            getBean().setTotal_resumen_area(service.getTotalResumenArea(getBean().getVenta_seleccionada()));
//            getBean().setTotal_resumen_cocina(service.getTotalResumenCocina(getBean().getVenta_seleccionada()));
//            getBean().setTotal_resumen_dependiente(service.getTotalResumenDependiente(getBean().getVenta_seleccionada()));
        getBean().setReabrir_ventas_enabled(service.canReabrirVenta(getBean().getVenta_seleccionada()) == 1);

        resumen = ventaResumenService.getResumenVenta(getBean().getVenta_seleccionada());
        getBean().setPropina_total(resumen.getTotalPropina());
        getBean().setTotal_autorizos(resumen.getTotalAutorizos());
        getBean().setTotal_gasto_insumos(resumen.getTotalGastadoInsumos());
        getBean().setTotal_gasto_otros(resumen.getTotalGastos());
        getBean().setTotal_gasto_salario(resumen.getTotalPagoTrabajadores());
        getBean().setVenta_neta(resumen.getTotalVendidoNeto());
        getBean().setVenta_total(resumen.getTotalVendido());

        getBean().setFecha(v.getFecha().toString());
        getBean().setCambiar_turno_enabled(service.canOpenNuevoTurno(v.getFecha()) == 1);

        ventaResources = ventaResumenService.getVentaResources(getBean().getVenta_seleccionada());
        List<String> listMesa = new ArrayListModel<>(ventaResources.getMesasPorVenta().keySet());
        getBean().setLista_mesas(new ArrayListModel<>(listMesa));
        if (!getBean().getLista_mesas().isEmpty()) {
            getBean().setMesa_seleccionada(getBean().getLista_mesas().get(0));
        }
        List<String> listCocina = new ArrayListModel<>(ventaResources.getCocinasPorVenta().keySet());
        getBean().setLista_cocinas(new ArrayListModel<>(listCocina));
        if (!getBean().getLista_cocinas().isEmpty()) {
            getBean().setCocina_seleccionada(getBean().getLista_cocinas().get(0));
        }
        List<String> listPersonal = new ArrayListModel<>(ventaResources.getPersonalPorVenta().keySet());
        getBean().setLista_dependientes(new ArrayListModel<>(listPersonal));
        if (!getBean().getLista_dependientes().isEmpty()) {
            getBean().setPersonal_seleccionado(getBean().getLista_dependientes().get(0));
        }
        List<String> listAreas = new ArrayListModel<>(ventaResources.getAreasPorVenta().keySet());
        getBean().setLista_areas(new ArrayListModel<>(listAreas));
        if (!getBean().getLista_areas().isEmpty()) {
            getBean().setArea_seleccionada(getBean().getLista_areas().get(0));
        }

        ConfiguracionService service = PosDesktopUiModule.getInstance().getImplementation(ConfiguracionService.class);

        boolean value = UserResolver.resolveUser(Personal.class).getPuestoTrabajonombrePuesto().getNivelAcceso() < 3 && service.getConfiguracion(R.SettingID.GENERAL_CAJERO_PERMISOS_ESP).getValor() != 1;

        firePropertyChange(PROP_HIDE_PANEL, !value, value);

    }

    private void addListeners() {
        addBeanPropertyChangeListener(PROP_VENTA_SELECCIONADA, (PropertyChangeEvent evt) -> {
            if (evt.getNewValue() != null) {
                updateBeanData();
            }
        });
        addBeanPropertyChangeListener(PROP_MESA_SELECCIONADA, (PropertyChangeEvent evt) -> {
            String value = (String) evt.getNewValue();
            if (value != null) {
                var wrapper = ventaResources.getMesasPorVenta().get(value);
                getBean().setLista_productos_por_mesa(new ArrayListModel(wrapper.getLista_contenida()));

                getBean().setTotal_resumen_mesa(utils.setDosLugaresDecimales(wrapper.getTotal()));
            }
        });
        addBeanPropertyChangeListener(PROP_AREA_SELECCIONADA, (PropertyChangeEvent evt) -> {
            String value = (String) evt.getNewValue();
            if (value != null) {
                var wrapper = ventaResources.getAreasPorVenta().get(value);
                getBean().setLista_productos_por_area(new ArrayListModel(wrapper.getLista_contenida()));

                getBean().setTotal_resumen_area(utils.setDosLugaresDecimales(wrapper.getTotal()));
            }
        });
        addBeanPropertyChangeListener(PROP_PERSONAL_SELECCIONADO, (PropertyChangeEvent evt) -> {
            String value = (String) evt.getNewValue();
            if (value != null) {
                var wrapper = ventaResources.getPersonalPorVenta().get(value);
                getBean().setLista_productos_por_dependientes(new ArrayListModel(wrapper.getLista_contenida()));

                getBean().setTotal_resumen_dependiente(utils.setDosLugaresDecimales(wrapper.getTotal()));
            }
        });
        addBeanPropertyChangeListener(PROP_COCINA_SELECCIONADA, (PropertyChangeEvent evt) -> {
            String value = (String) evt.getNewValue();
            if (value != null) {
                var wrapper = ventaResources.getCocinasPorVenta().get(value);
                getBean().setLista_productos_por_cocina(new ArrayListModel(wrapper.getLista_contenida()));

                getBean().setTotal_resumen_cocina(utils.setDosLugaresDecimales(wrapper.getTotal()));
            }
        });
    }

    @Override
    protected Optional refreshState() {
        //       long start = System.currentTimeMillis();
        updateBeanData();
        //     System.out.println(System.currentTimeMillis() - start);
        return Optional.empty();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VentaDetailViewPresenter other = (VentaDetailViewPresenter) obj;
        if (!Objects.equals(this.getBean().getVenta_seleccionada(), other.getBean().getVenta_seleccionada())) {
            return false;
        }
        return true;
    }

}
