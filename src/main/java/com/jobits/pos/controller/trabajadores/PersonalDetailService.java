/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.trabajadores;

import com.jobits.pos.domain.models.Personal;
import com.jobits.pos.domain.models.PuestoTrabajo;
import java.awt.Container;
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

    List<PuestoTrabajo> getPuestoTrabajoList();

    boolean isCreatingMode();

    void pagarTrabajador();
    
}
