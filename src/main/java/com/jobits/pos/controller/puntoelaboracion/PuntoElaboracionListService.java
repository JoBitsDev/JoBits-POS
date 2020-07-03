/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.puntoelaboracion;

import com.jobits.pos.domain.models.Cocina;
import java.util.Collection;

/**
 *
 * @author Jorge
 */
public interface PuntoElaboracionListService {

    public void setSelected(Cocina cocinaSeleccionada);

    public void update();

    public void createInstance();

    public void update(Cocina elemento_seleccionado);

    public void destroy(Cocina elemento_seleccionado);

    public Collection<? extends Cocina> getItems();
    
}
