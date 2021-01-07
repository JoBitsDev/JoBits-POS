/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.seccion;

import com.jobits.pos.controller.AbstractDetailController;
import com.jobits.pos.domain.models.Carta;
import com.jobits.pos.domain.models.Seccion;

/**
 *
 * @author Home
 */
public interface SeccionListService {

    public void createInstance(String nombre);

    public void createInstanceOffline(Seccion seccion, Carta a);

    public void update(Seccion selected);

    public void destroy(Seccion selected);

    public AbstractDetailController<Seccion> getDetailControllerForNew();

    public AbstractDetailController<Seccion> getDetailControllerForEdit(Seccion selected);

}
