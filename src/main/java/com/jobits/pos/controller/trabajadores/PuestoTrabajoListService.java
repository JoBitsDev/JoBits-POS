/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.trabajadores;

import com.jobits.pos.domain.models.PuestoTrabajo;
import java.util.Collection;

/**
 *
 * @author ERIK QUESADA
 */
public interface PuestoTrabajoListService {

    public void destroy(PuestoTrabajo elemento_seleccionado);

    public Collection<? extends PuestoTrabajo> getItems();
    
}
