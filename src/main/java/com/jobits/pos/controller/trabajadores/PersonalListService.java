/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.trabajadores;

import com.jobits.pos.domain.models.Personal;
import java.util.Collection;

/**
 *
 * @author ERIK QUESADA
 */
public interface PersonalListService {

    public void destroy(Personal elemento_seleccionado);

    public Collection<? extends Personal> getItems();
    
}
