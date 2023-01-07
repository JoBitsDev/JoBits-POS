/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.trabajadores.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.areaventa.AreaVentaService;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.core.domain.models.Cocina;
import com.jobits.pos.core.domain.models.PuestoTrabajo;
import com.jobits.pos.main.Application;
import com.root101.clean.core.app.services.utils.TipoNotificacion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.root101.clean.core.domain.services.ResourceHandler;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.jobits.pos.controller.puntoelaboracion.PuntoElaboracionService;
import com.jobits.pos.controller.trabajadores.PuestoTrabajoUseCase;

/**
 * @author Home
 */
public class PuestoTrabajoDetailViewPresenter extends AbstractViewPresenter<PuestoTrabajoDetailViewModel> {

    public static final String ACTION_CANCELAR = "Cancelar";
    public static String ACTION_AGREGAR = "";

    private final PuestoTrabajoUseCase service = PosDesktopUiModule.getInstance().getImplementation(PuestoTrabajoUseCase.class);
    private final AreaVentaService areaService = PosDesktopUiModule.getInstance().getImplementation(AreaVentaService.class);
    private final PuntoElaboracionService cocinaService = PosDesktopUiModule.getInstance().getImplementation(PuntoElaboracionService.class);
    private final boolean creatingMode;

    PuestoTrabajo puesto;

    public PuestoTrabajoDetailViewPresenter(PuestoTrabajo puesto) {
        super(new PuestoTrabajoDetailViewModel());
        creatingMode = puesto == null;
        if (creatingMode) {
            this.puesto = new PuestoTrabajo();
            this.puesto.setAPartirDe(0f);
            this.puesto.setPuestosDisponibles(0);
            this.puesto.setSalarioFijo(0f);
            this.puesto.setSalarioPorcientoDeArea(0f);
            this.puesto.setSalarioPorcientoVentaTotal(0f);
        } else {
            this.puesto = puesto;
        }
        fillForm();
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_CANCELAR) {
            @Override
            public Optional doAction() {
                onCancelarClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_AGREGAR) {
            @Override
            public Optional doAction() {
                onAgregarClick();
                return Optional.empty();
            }
        });
    }

    private void onAgregarClick() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Esta seguro que desea continuar?",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            String codAreaPago = null;
            if (getBean().getArea_pago_seleccionada() != null) {
                codAreaPago = getBean().getArea_pago_seleccionada().getCodCocina();
            }
            int nivelAcceso = 0;
            if (getBean().getNivel_acceso_seleccionado() != null) {
                nivelAcceso = getBean().getNivel_acceso_seleccionado().getNivel();
            }
            puesto.setNombrePuesto(getBean().getNombre_puesto_trabajo());
            puesto.setAreacodArea(getBean().getArea_trabajo_seleccionada());
            puesto.setPuntoDeElaboracionDePago(codAreaPago);
            puesto.setNivelAcceso(nivelAcceso);
            puesto.setPagoPorVentas(getBean().isPago_por_ventas());
            puesto.setPropina(getBean().isPropina());
            puesto.setPuestosDisponibles(getBean().getPuestos_disponibles());
            puesto.setAPartirDe(getBean().getPago_a_partir());
            puesto.setSalarioFijo(getBean().getSalario_fijo());
            puesto.setSalarioPorcientoDeArea(getBean().getPago_porciento_a_partir());
            puesto.setSalarioPorcientoVentaTotal(getBean().getSalario_venta());
            if (creatingMode) {
                service.create(puesto);
            } else {
                service.edit(puesto);
            }
            Application.getInstance().getNotificationService().notify(ResourceHandler.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
            NavigationService.getInstance().navigateUp();//TODO: faltan los insumos
        }
    }

    private void onCancelarClick() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Esta seguro que desea cancelar?",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            NavigationService.getInstance().navigateUp();
        }
    }

    private void fillForm() {
        if (creatingMode) {
            getBean().setCrear_editar_button_text("Crear");
        } else {
            getBean().setNombre_enabled(false);
            getBean().setCrear_editar_button_text("Editar");
        }
        getBean().getArea_pago_list().clear();
        getBean().getArea_pago_list().addAll(new ArrayListModel<>(cocinaService.findAll()));
        getBean().getArea_trabajo_list().clear();
        getBean().getArea_trabajo_list().addAll(new ArrayListModel<>(areaService.findAll()));
        getBean().getNivel_acceso_list().clear();
        getBean().getNivel_acceso_list().addAll(new ArrayListModel<>(Arrays.asList(R.NivelAcceso.values())));

        getBean().setNombre_puesto_trabajo(puesto.getNombrePuesto());

        if (puesto.getPuestosDisponibles() == null) {
            getBean().setPuestos_disponibles(0);
        } else {
            getBean().setPuestos_disponibles(puesto.getPuestosDisponibles());
        }
        if (puesto.getSalarioFijo() == null) {
            getBean().setSalario_fijo(0);
        } else {
            getBean().setSalario_fijo(puesto.getSalarioFijo());
        }

        if (puesto.getSalarioPorcientoVentaTotal() == null) {
            getBean().setSalario_venta(0);
        } else {
            getBean().setSalario_venta(puesto.getSalarioPorcientoVentaTotal());
        }

        if (puesto.getSalarioPorcientoDeArea() == null) {
            getBean().setPago_porciento_a_partir(0);
        } else {
            getBean().setPago_porciento_a_partir(puesto.getSalarioPorcientoDeArea());
        }

        if (puesto.getAPartirDe() == null) {
            getBean().setPago_a_partir(0);
        } else {
            getBean().setPago_a_partir(puesto.getAPartirDe());
        }

        getBean().setPago_por_ventas(puesto.getPagoPorVentas() == null ? false : puesto.getPagoPorVentas());
        getBean().setPropina(puesto.getPropina() == null ? false : puesto.getPropina());

        if (puesto.getNivelAcceso() == null) {
            getBean().setNivel_acceso_seleccionado(null);
        } else {
            getBean().setNivel_acceso_seleccionado(getBean().getNivel_acceso_list().get(puesto.getNivelAcceso()));
        }
        if (puesto.getPuntoDeElaboracionDePago() == null) {
            getBean().setArea_pago_seleccionada(null);
        } else {
            List<Cocina> list = getBean().getArea_pago_list();
            String codCocina = puesto.getPuntoDeElaboracionDePago();
            list.stream().filter((cocina) -> (cocina.getCodCocina().equals(codCocina))).forEachOrdered((cocina) -> {
                getBean().setArea_pago_seleccionada(cocina);
            });
        }
        if (puesto.getAreacodArea() == null) {
            getBean().setArea_trabajo_seleccionada(null);
        } else {
            getBean().setArea_trabajo_seleccionada(puesto.getAreacodArea());
        }

    }

}
