/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.trabajadores;

import com.jobits.pos.domain.models.Area;
import com.jobits.pos.domain.models.Cocina;
import com.jobits.pos.domain.models.PuestoTrabajo;
import java.awt.Container;
import java.util.List;

/**
 *
 * @author Home
 */
public interface PuestoTrabajoDetailService {

    /**
     *
     * @param parent the value of parent
     */
    void constructView(Container parent);

    PuestoTrabajo createNewInstance();

    List<Area> getAreaList();

    List<Cocina> getAreasPago();

    boolean isCreatingMode();

    public void create(PuestoTrabajo puestoTrabajo);

    public void update(PuestoTrabajo puestoTrabajo);

}
