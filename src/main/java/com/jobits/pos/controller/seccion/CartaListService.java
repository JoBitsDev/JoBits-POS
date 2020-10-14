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
public interface CartaListService {

    public void createInstance();

    public AbstractDetailController<Carta> getDetailControllerForNew();

    public AbstractDetailController<Carta> getDetailControllerForEdit(Carta selected);

    public void createSeccion(Carta selectedValue);

    public void removeSeccionFromCarta(Seccion selectedValue);

    public void destroy(Carta selected);

}
