/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.trabajadores.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.trabajadores.PersonalDetailController;
import com.jobits.pos.controller.trabajadores.PersonalDetailService;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.domain.models.Personal;
import com.jobits.pos.domain.models.PuestoTrabajo;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.TipoNotificacion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import javax.swing.JOptionPane;

/**
 *
 * @author Home
 */
public class PersonalDetailViewPresenter extends AbstractViewPresenter<PersonalDetailViewModel> {

    public static final String ACTION_CANCELAR = "Cancelar";
    public static String ACTION_AGREGAR = "";

    private PersonalDetailService service;

    public PersonalDetailViewPresenter(PersonalDetailController service) {
        super(new PersonalDetailViewModel());
        this.service = service;
        if (service.isCreatingMode()) {
            getBean().setCrear_editar_button_text("Crear");
        } else {
            getBean().setCrear_editar_button_text("Editar");
        }
        fillForm();
        addListeners();
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

    private void addListeners() {
    }

    private void onAgregarClick() {
        service.fillPersonalData(
                getBean().getNombre_trabajador(),
                getBean().getApellidos_trabajador(),
                getBean().getFecha_nacimiento(),
                getBean().getPuesto_trabajo_seleccionado(),
                getBean().getUsuario_trabajador(),
                getBean().getContrasena_nueva(),
                getBean().getContrasena_nueva_repetida(),
                getBean().getContrasena_antigua(),
                getBean().getTelefono_movil(),
                getBean().getTelefono_fijo(),
                getBean().getDireccion(),
                getBean().getCarnet_identidad(),
                getBean().getSexo_seleccionado());
        NavigationService.getInstance().navigateUp();//TODO: faltan los insumos
        Application.getInstance().getNotificationService().notify(R.RESOURCE_BUNDLE.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
    }

    private void onCancelarClick() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Desea descartar los cambios?",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            NavigationService.getInstance().navigateUp();
        }
    }

    private void fillForm() {
        getBean().getSexo_list().clear();
        getBean().getSexo_list().addAll(new ArrayListModel<>(Arrays.asList("Masculino", "Femenino")));
        getBean().getPuestos_trabajo_list().clear();
        getBean().getPuestos_trabajo_list().addAll(new ArrayListModel<>(service.getPuestoTrabajoList()));

        Personal personal = service.getInstance();
        if (personal.getDatosPersonales() != null) {
            getBean().setNombre_trabajador(personal.getDatosPersonales().getNombre());
            getBean().setApellidos_trabajador(personal.getDatosPersonales().getApellidos());
            getBean().setFecha_nacimiento(personal.getDatosPersonales().getFechaNacimineto());
            getBean().setUsuario_trabajador(personal.getUsuario());
            getBean().setContrasena_antigua(personal.getContrasenna());
            getBean().setPuesto_trabajo_seleccionado(personal.getPuestoTrabajonombrePuesto());

            if (personal.getDatosPersonales().getTelefonoMovil() == null) {
                getBean().setTelefono_movil(null);
            } else {
                getBean().setTelefono_movil(personal.getDatosPersonales().getTelefonoMovil().toString());
            }
            if (personal.getDatosPersonales().getTelefonoFijo() == null) {
                getBean().setTelefono_fijo(null);
            } else {
                getBean().setTelefono_fijo(personal.getDatosPersonales().getTelefonoFijo().toString());
            }
            if (personal.getDatosPersonales().getCarnet() == null) {
                getBean().setCarnet_identidad(null);
            } else {
                getBean().setCarnet_identidad(personal.getDatosPersonales().getCarnet());
            }
            if (personal.getDatosPersonales().getDireccion() == null) {
                getBean().setDireccion(null);
            } else {
                getBean().setDireccion(personal.getDatosPersonales().getDireccion());
            }

            if (personal.getDatosPersonales().getSexo() != null) {
                if (personal.getDatosPersonales().getSexo() == 'M') {
                    getBean().setSexo_seleccionado("Masculino");
                } else {
                    getBean().setSexo_seleccionado("Femenino");
                }
            }
        }
    }
}
