/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.trabajadores.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.trabajadores.PuestoTrabajoDetailService;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.core.domain.models.Cocina;
import com.jobits.pos.core.domain.models.PuestoTrabajo;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.TipoNotificacion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Home
 */
public class PuestoTrabajoDetailViewPresenter extends AbstractViewPresenter<PuestoTrabajoDetailViewModel> {

    public static final String ACTION_CANCELAR = "Cancelar";
    public static String ACTION_AGREGAR = "";

    private final PuestoTrabajoDetailService service = PosDesktopUiModule.getInstance().getImplementation(PuestoTrabajoDetailService.class);
    private final boolean creatingMode;

    PuestoTrabajo puesto;

    public PuestoTrabajoDetailViewPresenter(PuestoTrabajo puesto) {
        super(new PuestoTrabajoDetailViewModel());
        creatingMode = puesto == null;
        if (creatingMode) {
            this.puesto = service.createNewInstance();
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

            service.fillPuestoTrabajoData(
                    puesto,
                    getBean().getNombre_puesto_trabajo(),
                    getBean().getArea_trabajo_seleccionada(),
                    codAreaPago,
                    nivelAcceso,
                    getBean().isPago_por_ventas(),
                    getBean().isPropina(),
                    getBean().getPuestos_disponibles(),
                    getBean().getPago_a_partir(),
                    getBean().getSalario_fijo(),
                    getBean().getPago_porciento_a_partir(),
                    getBean().getSalario_venta());
            if (creatingMode) {
                service.create(puesto);
            } else {
                service.update(puesto);
            }
            Application.getInstance().getNotificationService().notify(R.RESOURCE_BUNDLE.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
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
        getBean().getArea_pago_list().addAll(new ArrayListModel<>(service.getAreasPago()));
        getBean().getArea_trabajo_list().clear();
        getBean().getArea_trabajo_list().addAll(new ArrayListModel<>(service.getAreaList()));
        getBean().getNivel_acceso_list().clear();
        getBean().getNivel_acceso_list().addAll(new ArrayListModel<>(Arrays.asList(R.NivelAcceso.values())));

        getBean().setNombre_puesto_trabajo(puesto.getNombrePuesto());
        getBean().setPuestos_disponibles(puesto.getPuestosDisponibles());

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
        if (puesto.getAreaPago() == null) {
            getBean().setArea_pago_seleccionada(null);
        } else {
            List<Cocina> list = getBean().getArea_pago_list();
            String codCocina = puesto.getAreaPago();
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
