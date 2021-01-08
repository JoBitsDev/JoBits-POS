/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.trabajadores;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.jobits.pos.controller.AbstractDetailController;
import com.jobits.pos.domain.models.DatosPersonales;
import com.jobits.pos.domain.models.Personal;
import com.jobits.pos.domain.models.PuestoTrabajo;
import com.jobits.pos.adapters.repo.autenticacion.PersonalDAO;
import com.jobits.pos.adapters.repo.impl.PuestoTrabajoDAO;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.TipoNotificacion;
import javax.swing.JOptionPane;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class PersonalDetailController extends AbstractDetailController<Personal> implements PersonalDetailService {

    private boolean creatingMode = true;

    public PersonalDetailController() {
        super(PersonalDAO.getInstance());
        instance = createNewInstance();
    }

    public PersonalDetailController(Personal instance) {
        super(instance, PersonalDAO.getInstance());
        creatingMode = false;
    }

    /**
     *
     * @param parent the value of parent
     */
    @Override
    public void constructView(java.awt.Container parent) {
    }

    @Override
    public Personal getInstance() {
        return super.getInstance(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Personal createNewInstance() {
        Personal ret = new Personal();
        ret.setOrdenList(new ArrayList<>());
        ret.setDatosPersonales(new DatosPersonales());
        return ret;
    }

    @Override
    public List<PuestoTrabajo> getPuestoTrabajoList() {
        return PuestoTrabajoDAO.getInstance().findAll();
    }

    @Override
    public void pagarTrabajador() {
        instance.setUltimodiaPago(new Date());
        instance.setPagoPendiente((float) 0);
        update(selected, true);
    }

    @Override
    public void acumularSalarioTrabajador(float salarioAcumular) {
        instance.setPagoPendiente(instance.getPagoPendiente() + salarioAcumular);
        update(instance, true);
    }

    @Override
    public boolean isCreatingMode() {
        return creatingMode;
    }

    @Override
    public void fillPersonalData(String nombre, String apellidos, Date fechaNac, PuestoTrabajo puestoTrabajo, String usuario, String contrasennaNueva, String contrasennaNuevaRepetida, String contrasennaAntigua, String telefonoMovil, String telefonoFijo, String direccion, String carnetID, String sexo) {
        if (contrasennaNueva != null) {
            if (!contrasennaNueva.equals(contrasennaNuevaRepetida)) {
                throw new IllegalArgumentException("Repita correctamente la nueva contrasena");
            }
        }
        if (nombre == null
                || apellidos == null
                || usuario == null
                || contrasennaNueva == null && contrasennaAntigua == null
                || puestoTrabajo == null
                || nombre.equals("")
                || apellidos.equals("")
                || usuario.equals("")) {
            throw new IllegalArgumentException("Hay campos obligatorios sin rellenar");
        }

        instance.getDatosPersonales().setNombre(nombre);
        instance.getDatosPersonales().setApellidos(apellidos);
        instance.getDatosPersonales().setFechaNacimineto(fechaNac);
        instance.getDatosPersonales().setDireccion(direccion);
        instance.getDatosPersonales().setCarnet(carnetID);

        instance.setPuestoTrabajonombrePuesto(puestoTrabajo);
        instance.setUsuario(usuario);
        instance.getDatosPersonales().setPersonalusuario(usuario);

        instance.setUltimodiaPago(new Date());
        instance.setPagoPendiente((float) 0);
        instance.setOnline(false);

        if (contrasennaNueva == null || contrasennaNueva.equals("")) {
            if (contrasennaAntigua != null && !contrasennaAntigua.equals("")) {
                instance.setContrasenna(contrasennaAntigua);
            }
        } else {
            instance.setContrasenna(contrasennaNueva);
        }
        if (telefonoMovil == null) {
            instance.getDatosPersonales().setTelefonoMovil(null);
        } else {
            instance.getDatosPersonales().setTelefonoMovil(Integer.parseInt(telefonoMovil));
        }
        if (telefonoFijo == null) {
            instance.getDatosPersonales().setTelefonoFijo(null);
        } else {
            instance.getDatosPersonales().setTelefonoFijo(Integer.parseInt(telefonoFijo));
        }
        if (sexo != null) {
            if (sexo.equals("Masculino")) {
                instance.getDatosPersonales().setSexo('M');
            } else {
                instance.getDatosPersonales().setSexo('F');
            }
        }
        if (isCreatingMode()) {
            create(instance);
        } else {
            update(instance);
        }
    }

}
