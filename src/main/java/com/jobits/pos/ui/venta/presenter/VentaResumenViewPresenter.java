/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.presenter;

import com.jobits.pos.ui.venta.orden.presenter.OrdenDetailViewPresenter;
import com.jobits.pos.ui.venta.orden.presenter.OrdenDetailViewModel;
import com.jobits.pos.controller.venta.OrdenController;
import com.jobits.pos.controller.venta.VentaDetailController;
import com.jobits.pos.controller.venta.VentaDetailService;
import com.jobits.pos.domain.VentaDAO1;
import com.jobits.pos.main.Application;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.utils.utils;
import com.jobits.pos.ui.venta.orden.OrdenDetailFragmentView;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Optional;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class VentaResumenViewPresenter extends AbstractViewPresenter<VentaResumenViewModel> {

    public static final String ACTION_IMPRIMIR_Z = "Imprimir z",
            ACTION_IMPRIMIR_AUTORIZOS = "Imprimir Autorizo",
            ACTION_TERMINAR_EXPORTAR = "Terminar y exportar",
            ACTION_TERMINAR_VENTAS = "Terminar Ventas",
            ACTION_REABRIR_VENTA = "Reabrir Venta",
            //Area
            ACTION_IMPRIMIR_RESUMEN_AREA = "Imprimir resumen area",
            //Dpte
            ACTION_IMPRIMIR_RESUMEN_USUARIO = "Imprimir resumen dependiente",
            ACTION_IMPRIMIR_RESUMEN_USUARIO_COMISION = "Imprimir comision dependiente",
            //pto elab
            ACTION_IMPRIMIR_RESUMEN_PTO = "Imprimir Pto elaboracion",
            ACTION_ABRIR_ORDEN = "Editar Orden";

    private VentaDetailService service;
    private OrdenDetailViewPresenter ordenPresenter;

    public VentaResumenViewPresenter(VentaDetailController controller, OrdenController ordenController) {
        super(new VentaResumenViewModel());
        this.service = controller;
        ordenPresenter = new OrdenDetailViewPresenter(ordenController);
        ordenPresenter.addBeanPropertyChangeListener(OrdenDetailViewModel.PROP_ORDEN_STATUS_UPDATE, (PropertyChangeEvent evt) -> {
            updateBeanData();
        });
        updateBeanData();
        controller.initIPV(controller.getInstance());
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
        registerOperation(new AbstractViewAction(ACTION_ABRIR_ORDEN) {
            @Override
            public Optional doAction() {
                onAbrirOrdenAction();
                return Optional.empty();
            }

        });

    }

    private void onAbrirOrdenAction() {
        if (getBean().getOrden_seleccionada() == null) {
            service.createNewOrden();
            getBean().setLista_orden(service.getOrdenesActivas());
            //throw new IllegalArgumentException("No hay una orden seleccionada");
        }

        ordenPresenter.setOrden(getBean().getOrden_seleccionada());
        Application.getInstance().getNavigator().navigateTo(OrdenDetailFragmentView.VIEW_NAME, ordenPresenter);
    }

    private void onImprimirZClick() {
        service.printZ();
    }

    private void onImprimirAutorizosClick() {
        service.printGastosCasa();
    }

    private void onTerminarClick() {
        if (service.terminarVentas()) {
            Application.getInstance().getNavigator().navigateUp();
        }
    }

    private void onTerminarYExportarClick() {
        service.terminarYExportar(getBean().getFile_for_export());
        Application.getInstance().getNavigator().navigateUp();
    }

    private void onReabrirVentaCLick() {
        service.reabrirVentas();
    }

    private void onImprimirResumenVentaAreaClick() {
        service.printAreaResumen(getBean().getResumem_area_seleccionada().getArea());

    }

    private void onImprimirResumenVentaUsuarioClick() {
        service.printPersonalResumenRow(getBean().getResumen_usuario_seleccionado().getPersonal());

    }

    private void onImprimirResumenVentaUsuarioComisionClick() {
        service.printPagoPorVentaPersonal(getBean().getResumen_usuario_seleccionado().getPersonal());

    }

    private void onImprimirResumenPtoElabClick() {
        service.printCocinaResumen(getBean().getResumen_pto_seleccionado().getCodigoPto());
    }

    private void updateBeanData() {
        com.jobits.pos.domain.models.Venta v = service.getInstance();
        getBean().setLista_orden(service.getOrdenesActivas());
        getBean().setLista_resumen_area_venta(service.getResumenPorAreaVenta());
        getBean().setLista_resumen_pto_venta(service.getResumenPorPtoVenta());
        getBean().setLista_resumen_usuario_venta(service.getResumenPorUsuarioVenta());
        getBean().setPropina_total("" + utils.setDosLugaresDecimalesFloat(service.getTotalPropina()));
        getBean().setReabrir_ventas_enabled(service.canReabrirVenta());
        getBean().setTotal_autorizos(service.getTotalAutorizos());
        getBean().setTotal_gasto_insumos(service.getTotalGastadoInsumos());
        getBean().setTotal_gasto_otros(service.getTotalGastos());
        getBean().setTotal_gasto_salario(service.getTotalPagoTrabajadores());
        getBean().setVenta_neta(service.getTotalVendidoNeto());
        getBean().setVenta_total(service.getTotalVendido());
        getBean().setCambiar_turno_enabled(service.canCambiarTurno());
        getBean().setFecha(R.DATE_FORMAT.format(v.getFecha()));
    }

}
