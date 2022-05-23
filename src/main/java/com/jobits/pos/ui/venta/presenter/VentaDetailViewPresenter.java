/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.venta.OrdenService;
import com.jobits.pos.controller.venta.VentaDetailService;
import com.jobits.pos.core.domain.models.Area;
import com.jobits.pos.core.domain.models.Cocina;
import com.jobits.pos.core.domain.models.Mesa;
import com.jobits.pos.core.domain.models.Personal;
import com.jobits.pos.core.domain.models.Venta;
import com.jobits.pos.core.domain.models.temporal.VentaResumen;
import com.jobits.pos.core.repo.impl.ConfiguracionDAO;
import com.jobits.pos.main.Application;
import com.root101.clean.core.app.services.utils.TipoNotificacion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.gastos.presenter.GastosViewPresenter;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.trabajadores.presenter.AsistenciaPersonalPresenter;
import static com.jobits.pos.ui.trabajadores.presenter.AsistenciaPersonalPresenter.PROP_VALUE_CHANGED;
import com.jobits.pos.ui.utils.LongProcessActionServiceImpl;
import com.jobits.pos.utils.utils;
import com.jobits.pos.ui.venta.orden.presenter.VentaOrdenListViewPresenter;
import static com.jobits.pos.ui.venta.presenter.VentaDetailViewModel.*;
import com.root101.clean.core.app.services.UserResolver;
import com.root101.clean.core.domain.services.ResourceHandler;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.swing.JOptionPane;

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
            ACTION_CREAR_NUEVO_TURNO = "Crear Nuevo Turno",
            ACTION_CAMBIAR_TURNO = "Cambiar Turno",
            //Area
            ACTION_IMPRIMIR_RESUMEN_AREA = "Imprimir resumen area",
            //Dpte
            ACTION_IMPRIMIR_RESUMEN_USUARIO = "Imprimir resumen dependiente",
            ACTION_IMPIMIR_RESUMEN_MESA = "Imprimir resumen mesa",
            ACTION_IMPRIMIR_RESUMEN_USUARIO_COMISION = "Imprimir comision dependiente",
            ACTION_IMPRIMIR_RESUMEN_COMISION_PORCENTUAL = "Imprimir comision Porcentual",
            //pto elab
            ACTION_IMPRIMIR_RESUMEN_PTO = "Imprimir Pto elaboracion";
    public static final String PROP_HIDE_PANEL = "Ocultar Paneles";
    private VentaDetailService service;
    OrdenService ordenService;
    private VentaOrdenListViewPresenter ventaOrdenPresenter;
    private AsistenciaPersonalPresenter asistenciaPersonalPresenter;
    private GastosViewPresenter gastosPresenter;

    /**
     *
     * @param controller
     * @param ordenController
     * @param ventas no pasar la lista vacia
     */
    public VentaDetailViewPresenter(
            VentaDetailService controller, OrdenService ordenController, int idVenta) {
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
        service.edit(v);
    }

    private void onTerminarClick() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Desea terminar el día de trabajo?",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            service.terminarVentas(getBean().getVenta_seleccionada());
            Application.getInstance().getNotificationService().notify(ResourceHandler.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
            Application.getInstance().getNavigator().navigateUp();
        }
    }

    private void onTerminarYExportarClick() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Desea terminar el día de trabajo?",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            service.terminarYExportar(getBean().getFile_for_export(), getBean().getVenta_seleccionada());
            Application.getInstance().getNotificationService().notify(ResourceHandler.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
            Application.getInstance().getNavigator().navigateUp();
        }
    }

    private void onReabrirVentaCLick() {
        service.reabrirVentas(getBean().getVenta_seleccionada());
        Application.getInstance().getNotificationService().notify(ResourceHandler.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
    }

    private void onImprimirResumenVentaAreaClick() {
        service.printAreaResumen(getBean().getArea_seleccionada(), getBean().getVenta_seleccionada());
    }

    private void onImprimirResumenVentaUsuarioClick() {
        boolean imprimirValores = (boolean) Application.getInstance().getNotificationService().
                showDialog("Presione SI para imprimir los valores,"
                        + "\nNo para imprimir solo las cantidades",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false);

        service.printPersonalResumenRow(getBean().getPersonal_seleccionado(),
                getBean().getVenta_seleccionada(), imprimirValores);

    }

    private void onImprimirResumenVentaUsuarioComisionClick() {
        boolean imprimirValores = (boolean) Application.getInstance().getNotificationService().
                showDialog("Presione SI para imprimir los valores,"
                        + "\nNo para imprimir solo las cantidades",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false);

        service.printPagoPorVentaPersonal(getBean().getPersonal_seleccionado(),
                getBean().getVenta_seleccionada(), imprimirValores);

    }

    private void onImprimirResumenPtoElabClick() {
        boolean imprimirValores = (boolean) Application.getInstance().getNotificationService().
                showDialog("Presione SI para imprimir los valores,"
                        + "\nNo para imprimir solo las cantidades",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false);

        service.printCocinaResumen(getBean().getCocina_seleccionada().getCodCocina(),
                getBean().getVenta_seleccionada(), imprimirValores);
    }

    private void onImpimirResumenMesaClick() {
        service.printMesaResumen(getBean().getMesa_seleccionada(), getBean().getVenta_seleccionada());
    }

    private void onImprimirResumenComisionPorcentualClick() {
        service.printComisionPorcentualResumen(getBean().getMesa_seleccionada(), getBean().getVenta_seleccionada());
    }

    private void onCambiarTurnoClick() {
        var listaVentas = service.getVentasDeFecha(getBean().getVenta_seleccionada());
        if (listaVentas.size() > 1) {
            int seleccion = JOptionPane.showOptionDialog(null, "Seleccione la venta", "Seleccion",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, listaVentas.toArray(), getBean().getVenta_seleccionada());
            if (seleccion != JOptionPane.CLOSED_OPTION) {
                getBean().setVenta_seleccionada(listaVentas.get(seleccion).getId());
                updateBeanData();
            }
        } else {
            Application.getInstance().getNotificationService().showDialog("No existen turnos para cambiar", TipoNotificacion.ERROR);
        }

    }

    private void onCrearNuevoTurnoClick() {
        if (service.canOpenNuevoTurno(getBean().getVenta_seleccionada())) {
            if ((boolean) Application.getInstance().getNotificationService().
                    showDialog("Desea terminar el turno?", TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
                new LongProcessActionServiceImpl("Creando IPVs.........") {
                    @Override
                    protected void longProcessMethod() {//TODO: No se muestran las ecepciones que se lanzan dentro de este metodo
                        service.cambiarTurno(getBean().getVenta_seleccionada(), Application.getInstance().getLoggedUser());
                        //ventas = service.getVentasDeFecha(getBean().getVenta_seleccionada().getFecha());
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
        if (getBean().getVenta_seleccionada() != -1) {
            var v = service.findBy(getBean().getVenta_seleccionada());
            VentaResumen r = VentaResumen.from(v);
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
            getBean().setPropina_total("" + utils.setDosLugaresDecimalesFloat(r.getTotalPropina()));
            getBean().setReabrir_ventas_enabled(r.canReabrirVenta());

            getBean().setTotal_autorizos(service.getTotalAutorizos(getBean().getVenta_seleccionada()));
            getBean().setTotal_gasto_insumos(service.getTotalGastadoInsumos(getBean().getVenta_seleccionada()));
            getBean().setTotal_gasto_otros(service.getTotalGastos(getBean().getVenta_seleccionada()));
            getBean().setTotal_gasto_salario(service.getTotalPagoTrabajadores(getBean().getVenta_seleccionada()));

            getBean().setVenta_neta(service.getTotalVendidoNeto(getBean().getVenta_seleccionada()));
            getBean().setVenta_total(service.getTotalVendido(getBean().getVenta_seleccionada()));
            getBean().setFecha(R.DATE_FORMAT.format(v.getFecha()));
            getBean().setCambiar_turno_enabled(service.canOpenNuevoTurno(v.getFecha()));

            getBean().setLista_mesas(new ArrayListModel<>(service.getMesasPorVenta(getBean().getVenta_seleccionada())));
            if (!getBean().getLista_mesas().isEmpty()) {
                getBean().setMesa_seleccionada(getBean().getLista_mesas().get(0));
            }
            getBean().setLista_cocinas(new ArrayListModel<>(service.getCocinasPorVenta(getBean().getVenta_seleccionada())));
            if (!getBean().getLista_cocinas().isEmpty()) {
                getBean().setCocina_seleccionada(getBean().getLista_cocinas().get(0));
            }
            getBean().setLista_dependientes(new ArrayListModel<>(service.getPersonalPorVenta(getBean().getVenta_seleccionada())));
            if (!getBean().getLista_dependientes().isEmpty()) {
                getBean().setPersonal_seleccionado(getBean().getLista_dependientes().get(0));
            }
            getBean().setLista_areas(new ArrayListModel<>(service.getAreasPorVenta(getBean().getVenta_seleccionada())));
            if (!getBean().getLista_areas().isEmpty()) {
                getBean().setArea_seleccionada(getBean().getLista_areas().get(0));
            }

            boolean value = UserResolver.resolveUser(Personal.class).getPuestoTrabajonombrePuesto().getNivelAcceso() < 3
                    && ConfiguracionDAO.getInstance().find(R.SettingID.GENERAL_CAJERO_PERMISOS_ESP).getValor() != 1;

            firePropertyChange(PROP_HIDE_PANEL, !value, value);
        }

    }

    private void addListeners() {
        addBeanPropertyChangeListener(PROP_VENTA_SELECCIONADA, (PropertyChangeEvent evt) -> {
            if (evt.getNewValue() != null) {
                updateBeanData();
            }
        });
        addBeanPropertyChangeListener(PROP_MESA_SELECCIONADA, (PropertyChangeEvent evt) -> {
            Mesa mesa = (Mesa) evt.getNewValue();
            if (mesa != null) {
                getBean().setLista_productos_por_mesa(new ArrayListModel(
                        service.getResumenPorMesa(getBean().getVenta_seleccionada(), mesa)));

                getBean().setTotal_resumen_mesa(
                        service.getTotalResumenMesa(getBean().getVenta_seleccionada(), mesa));
            }
        });
        addBeanPropertyChangeListener(PROP_AREA_SELECCIONADA, (PropertyChangeEvent evt) -> {
            Area area = (Area) evt.getNewValue();
            if (area != null) {
                getBean().setLista_productos_por_area(new ArrayListModel(
                        service.getResumenPorArea(getBean().getVenta_seleccionada(), area)));

                getBean().setTotal_resumen_area(
                        service.getTotalResumenArea(getBean().getVenta_seleccionada(), area));
            }
        });
        addBeanPropertyChangeListener(PROP_PERSONAL_SELECCIONADO, (PropertyChangeEvent evt) -> {
            Personal personal = (Personal) evt.getNewValue();
            if (personal != null) {
                getBean().setLista_productos_por_dependientes(new ArrayListModel(
                        service.getResumenPorPersonal(getBean().getVenta_seleccionada(), personal)));

                getBean().setTotal_resumen_dependiente(
                        service.getTotalResumenDependiente(getBean().getVenta_seleccionada(), personal));
            }
        });
        addBeanPropertyChangeListener(PROP_COCINA_SELECCIONADA, (PropertyChangeEvent evt) -> {
            Cocina cocina = (Cocina) evt.getNewValue();
            if (cocina != null) {
                getBean().setLista_productos_por_cocina(
                        new ArrayListModel(service.getResumenPorCocina(
                                getBean().getVenta_seleccionada(), cocina)));

                getBean().setTotal_resumen_cocina(service.getTotalResumenCocina(
                        getBean().getVenta_seleccionada(), cocina));
            }
        });
    }

    @Override
    protected Optional refreshState() {
        // long start = System.currentTimeMillis();
        updateBeanData();
        //System.out.println(System.currentTimeMillis() - start);
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
