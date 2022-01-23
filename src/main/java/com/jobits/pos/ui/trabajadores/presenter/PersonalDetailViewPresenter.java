/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.trabajadores.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.trabajadores.PuestoTrabajoUseCase;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.core.domain.models.DatosPersonales;
import com.jobits.pos.core.domain.models.Personal;
import com.jobits.pos.core.domain.models.PuestoTrabajo;
import com.jobits.pos.main.Application;
import com.root101.clean.core.app.services.utils.TipoNotificacion;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.root101.clean.core.domain.services.ResourceHandler;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import com.jobits.pos.controller.trabajadores.PersonalUseCase;

/**
 *
 * @author Home
 */
public class PersonalDetailViewPresenter extends AbstractViewPresenter<PersonalDetailViewModel> {

    public static final String ACTION_CANCELAR = "Cancelar";
    public static String ACTION_AGREGAR = "";

    private final PersonalUseCase personalService = PosDesktopUiModule.getInstance().getImplementation(PersonalUseCase.class);
    private final PuestoTrabajoUseCase puestoService = PosDesktopUiModule.getInstance().getImplementation(PuestoTrabajoUseCase.class);
    private Personal personal;
    private final boolean creatingMode;

    public PersonalDetailViewPresenter(Personal personal) {
        super(new PersonalDetailViewModel());
        creatingMode = personal == null;
        if (creatingMode) {
            this.personal = new Personal();
            this.personal.setOrdenList(new ArrayList<>());
            this.personal.setDatosPersonales(new DatosPersonales());
        } else {
            this.personal = personal;
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
//            service.fillPersonalData(personal,
//                    getBean().getNombre_trabajador(),
//                    getBean().getApellidos_trabajador(),
//                    getBean().getFecha_nacimiento(),
//                    getBean().getPuesto_trabajo_seleccionado(),
//                    getBean().getUsuario_trabajador(),
//                    getBean().getContrasena_nueva(),
//                    getBean().getContrasena_nueva_repetida(),
//                    getBean().getContrasena_antigua(),
//                    getBean().getTelefono_movil(),
//                    getBean().getTelefono_fijo(),
//                    getBean().getDireccion(),
//                    getBean().getCarnet_identidad(),
//                    getBean().getSexo_seleccionado());

            String nombre = getBean().getNombre_trabajador();
            String apellidos = getBean().getApellidos_trabajador();
            Date fechaNac = getBean().getFecha_nacimiento();
            PuestoTrabajo puestoTrabajo = getBean().getPuesto_trabajo_seleccionado();
            String usuario = getBean().getUsuario_trabajador();
            String contrasennaNueva = getBean().getContrasena_nueva();
            String contrasennaNuevaRepetida = getBean().getContrasena_nueva_repetida();
            String contrasennaAntigua = getBean().getContrasena_antigua();
            String telefonoMovil = getBean().getTelefono_movil();
            String telefonoFijo = getBean().getTelefono_fijo();
            String direccion = getBean().getDireccion();
            String carnetID = getBean().getCarnet_identidad();
            String sexo = getBean().getSexo_seleccionado();

            if (contrasennaNueva != null) {
                if (!contrasennaNueva.equals(contrasennaNuevaRepetida)) {
                    throw new IllegalArgumentException("Repita correctamente la nueva contrasena");
                }
            }

            personal.getDatosPersonales().setNombre(nombre);
            personal.getDatosPersonales().setApellidos(apellidos);
            personal.getDatosPersonales().setFechaNacimineto(fechaNac);
            personal.getDatosPersonales().setDireccion(direccion);
            personal.getDatosPersonales().setCarnet(carnetID);

            personal.setPuestoTrabajonombrePuesto(puestoTrabajo);
            personal.setUsuario(usuario);
            personal.getDatosPersonales().setPersonalusuario(usuario);

            personal.setPagoPendiente((float) 0);
            personal.setOnline(false);

            if (contrasennaNueva == null || contrasennaNueva.equals("")) {
                if (contrasennaAntigua != null && !contrasennaAntigua.equals("")) {
                    personal.setContrasennaPlain(contrasennaAntigua);
                }
            } else {
                personal.setContrasennaPlain(contrasennaNueva);
            }
            if (telefonoMovil == null) {
                personal.getDatosPersonales().setTelefonoMovil(null);
            } else {
                personal.getDatosPersonales().setTelefonoMovil(Integer.parseInt(telefonoMovil));
            }
            if (telefonoFijo == null) {
                personal.getDatosPersonales().setTelefonoFijo(null);
            } else {
                personal.getDatosPersonales().setTelefonoFijo(Integer.parseInt(telefonoFijo));
            }
            if (sexo != null) {
                if (sexo.equals("Masculino")) {
                    personal.getDatosPersonales().setSexo('M');
                } else {
                    personal.getDatosPersonales().setSexo('F');
                }
            }

            if (creatingMode) {
                personal.setUltimodiaPago(new Date());
                personalService.create(personal);
            } else {
                personalService.edit(personal);
            }
            NavigationService.getInstance().navigateUp();//TODO: faltan los insumos
            Application.getInstance().getNotificationService().notify(ResourceHandler.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
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
            getBean().setCrear_editar_button_text("Editar");
        }
        getBean().getSexo_list().clear();
        getBean().getSexo_list().addAll(new ArrayListModel<>(Arrays.asList("Masculino", "Femenino")));
        getBean().getPuestos_trabajo_list().clear();
        getBean().getPuestos_trabajo_list().addAll(new ArrayListModel<>(puestoService.findAll()));

        if (personal.getDatosPersonales() != null) {
            getBean().setNombre_trabajador(personal.getDatosPersonales().getNombre());
            getBean().setApellidos_trabajador(personal.getDatosPersonales().getApellidos());
            getBean().setFecha_nacimiento(personal.getDatosPersonales().getFechaNacimineto());
            getBean().setUsuario_trabajador(personal.getUsuario());
            getBean().setContrasena_antigua(personal.getContrasenna());
            getBean().setPuesto_trabajo_seleccionado(personal.getPuestoTrabajonombrePuesto());
            getBean().setCarnet_identidad(personal.getDatosPersonales().getCarnet());
            getBean().setDireccion(personal.getDatosPersonales().getDireccion());
            if (personal.getDatosPersonales().getTelefonoMovil() != null) {
                getBean().setTelefono_movil(personal.getDatosPersonales().getTelefonoMovil().toString());
            }
            if (personal.getDatosPersonales().getTelefonoFijo() != null) {
                getBean().setTelefono_fijo(personal.getDatosPersonales().getTelefonoFijo().toString());
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
