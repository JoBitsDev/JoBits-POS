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
    Personal personal;

    public PersonalDetailViewPresenter(PersonalDetailController service) {
        super(new PersonalDetailViewModel());
        this.service = service;

        if (service.isCreatingMode()) {
            personal = service.createNewInstance();
            getBean().setCrear_editar_button_text("Crear");
        } else {
            personal = service.getInstance();
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

    private boolean onAgregarClick() {
        if (getBean().getContrasena_nueva() != null) {
            if (!getBean().getContrasena_nueva().equals(getBean().getContrasena_nueva_repetida())) {
                JOptionPane.showMessageDialog(Application.getInstance().getMainWindow(), "Repita correctamente la nueva contrasena");
                return false;
            }
        }
        String nombre = getBean().getNombre_trabajador(),
                apellidos = getBean().getApellidos_trabajador(),
                usuario = getBean().getUsuario_trabajador(),
                newPass = getBean().getContrasena_nueva(),
                oldPass = getBean().getContrasena_antigua();
        PuestoTrabajo puesto = getBean().getPuesto_trabajo_seleccionado();
        if (nombre == null
                || apellidos == null
                || usuario == null
                || newPass == null && oldPass == null
                || puesto == null
                || nombre.equals("")
                || apellidos.equals("")
                || usuario.equals("")) {
            JOptionPane.showMessageDialog(Application.getInstance().getMainWindow(), "Hay campos obligatorios sin rellenar");
            return false;
        }
//        if (!getBean().getTelefono_movil().matches("[0-9]+") && getBean().getTelefono_movil().length() != 8) {
//            JOptionPane.showMessageDialog(Application.getInstance().getMainWindow(), "");
//        }

        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Desea guardar los cambios",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {

            personal.getDatosPersonales().setNombre(getBean().getNombre_trabajador());
            personal.getDatosPersonales().setApellidos(getBean().getApellidos_trabajador());
            personal.getDatosPersonales().setFechaNacimineto(getBean().getFecha_nacimiento());

            personal.setPuestoTrabajonombrePuesto(getBean().getPuesto_trabajo_seleccionado());
            personal.setUsuario(getBean().getUsuario_trabajador());
            personal.getDatosPersonales().setPersonalusuario(personal.getUsuario());

            personal.setUltimodiaPago(new Date());
            personal.setPagoPendiente((float) 0);
            personal.setOnline(false);

            if (getBean().getContrasena_nueva() == null || getBean().getContrasena_nueva().equals("")) {
                if (getBean().getContrasena_antigua() != null && !getBean().getContrasena_antigua().equals("")) {
                    personal.setContrasenna(getBean().getContrasena_antigua());
                }
            } else {
                personal.setContrasenna(getBean().getContrasena_nueva());
            }
            if (getBean().getTelefono_movil() == null) {
                personal.getDatosPersonales().setTelefonoMovil(null);
            } else {
                personal.getDatosPersonales().setTelefonoMovil(Integer.parseInt(getBean().getTelefono_movil()));
            }
            if (getBean().getTelefono_fijo() == null) {
                personal.getDatosPersonales().setTelefonoFijo(null);
            } else {
                personal.getDatosPersonales().setTelefonoFijo(Integer.parseInt(getBean().getTelefono_fijo()));
            }
            if (getBean().getDireccion() == null) {
                personal.getDatosPersonales().setDireccion(null);
            } else {
                personal.getDatosPersonales().setDireccion(getBean().getDireccion());
            }
            if (getBean().getCarnet_identidad() == null) {
                personal.getDatosPersonales().setCarnet(null);
            } else {
                personal.getDatosPersonales().setCarnet(getBean().getCarnet_identidad());
            }
            if (getBean().getSexo_seleccionado() != null) {
                if (getBean().getSexo_seleccionado().equals("Masculino")) {
                    personal.getDatosPersonales().setSexo('M');
                } else {
                    personal.getDatosPersonales().setSexo('F');
                }
            }

            if (service.isCreatingMode()) {
                service.create(personal);
            } else {
                service.update(personal);
            }

            NavigationService.getInstance().navigateUp();//TODO: faltan los insumos
        }
        return true;
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
