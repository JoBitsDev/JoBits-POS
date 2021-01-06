/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.trabajadores.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.trabajadores.PuestoTrabajoDetailController;
import com.jobits.pos.controller.trabajadores.PuestoTrabajoDetailService;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.domain.models.Cocina;
import com.jobits.pos.domain.models.PuestoTrabajo;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.TipoNotificacion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.recursos.RegularExpressions;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import javax.swing.JOptionPane;

/**
 *
 * @author Home
 */
public class PuestoTrabajoDetailViewPresenter extends AbstractViewPresenter<PuestoTrabajoDetailViewModel> {

    public static final String ACTION_CANCELAR = "Cancelar";
    public static String ACTION_AGREGAR = "";

    private PuestoTrabajoDetailService service;
    PuestoTrabajo puesto;

    public PuestoTrabajoDetailViewPresenter(PuestoTrabajoDetailController service) {
        super(new PuestoTrabajoDetailViewModel());
        this.service = service;
        if (service.isCreatingMode()) {
            puesto = service.createNewInstance();
            getBean().setCrear_editar_button_text("Crear");
        } else {
            puesto = service.getInstance();
            getBean().setCrear_editar_button_text("Editar");
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
        if (getBean().getNombre_puesto_trabajo() == null || getBean().getNombre_puesto_trabajo().equals("")) {
            JOptionPane.showMessageDialog(Application.getInstance().getMainWindow(), "El campo nombre es obligatorio");
            return;
        } else {
            String nombre = getBean().getNombre_puesto_trabajo();
            if (nombre.length() > 30 || !nombre.matches(RegularExpressions.ONLY_WORDS_SEPARATED_WITH_SPACES)) {
                JOptionPane.showMessageDialog(Application.getInstance().getMainWindow(), "Nombre no permitido");
                return;
            } else {
                puesto.setNombrePuesto(nombre);
            }
        }

        puesto.setAreacodArea(getBean().getArea_trabajo_seleccionada());

        if (getBean().getArea_pago_seleccionada() == null) {
            puesto.setAreaPago(null);
        } else {
            puesto.setAreaPago(getBean().getArea_pago_seleccionada().getCodCocina());
        }
        if (getBean().getNivel_acceso_seleccionado() == null) {
            puesto.setNivelAcceso(0);
        } else {
            puesto.setNivelAcceso(getBean().getNivel_acceso_seleccionado().getNivel());
        }

        puesto.setPagoPorVentas(getBean().isPago_por_ventas());
        puesto.setPropina(getBean().isPropina());
        puesto.setPuestosDisponibles(getBean().getPuestos_disponibles());
        puesto.setAPartirDe(getBean().getPago_a_partir());
        puesto.setSalarioFijo(getBean().getSalario_fijo());
        puesto.setSalarioPorcientoDeArea(getBean().getPago_porciento_a_partir());
        puesto.setSalarioPorcientoVentaTotal(getBean().getSalario_venta());

        if (puesto.getPersonalList() == null) {
            puesto.setPersonalList(new ArrayList<>());
        }

        if (service.isCreatingMode()) {
            service.create(puesto);
        } else {
            service.update(puesto);
        }
        NavigationService.getInstance().navigateUp();//TODO: faltan los insumos
    }

    private void onCancelarClick() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Desea descartar los cambios?",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            NavigationService.getInstance().navigateUp();
        }
    }

    private void fillForm() {
        getBean().getArea_pago_list().clear();
        getBean().getArea_pago_list().addAll(new ArrayListModel<>(service.getAreasPago()));
        getBean().getArea_trabajo_list().clear();
        getBean().getArea_trabajo_list().addAll(new ArrayListModel<>(service.getAreaList()));
        getBean().getNivel_acceso_list().clear();
        getBean().getNivel_acceso_list().addAll(new ArrayListModel<>(Arrays.asList(R.NivelAcceso.values())));

        getBean().setNombre_puesto_trabajo(puesto.getNombrePuesto());
        getBean().setPuestos_disponibles(puesto.getPuestosDisponibles());

        if (!service.isCreatingMode()) {
            getBean().setNombre_enabled(false);
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
