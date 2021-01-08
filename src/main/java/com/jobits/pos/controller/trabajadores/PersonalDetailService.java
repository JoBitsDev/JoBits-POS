/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.trabajadores;

import com.jobits.pos.domain.models.Personal;
import com.jobits.pos.domain.models.PuestoTrabajo;
import java.awt.Container;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Home
 */
public interface PersonalDetailService {

    void acumularSalarioTrabajador(float salarioAcumular);

    /**
     *
     * @param parent the value of parent
     */
    void constructView(Container parent);

    Personal createNewInstance();

    public Personal getInstance();

    List<PuestoTrabajo> getPuestoTrabajoList();

    boolean isCreatingMode();

    void pagarTrabajador();

    public void create(Personal personal);

    public void update(Personal personal);

    public void fillPersonalData(
            String nombre,
            String apellidos,
            Date fechaNac,
            PuestoTrabajo puestoTrabajo,
            String usuario,
            String contrasennaNueva,
            String contrasennaNuevaRepetida,
            String contrasennaAntigua,
            String telefonoMovil,
            String telefonoFijo,
            String direccion,
            String carnetID,
            String sexo);

}
