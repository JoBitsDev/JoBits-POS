/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.insumo;

import com.jobits.pos.domain.models.Insumo;
import java.util.Collection;

/**
 *
 * @author Jorge
 */
public interface InsumoListService {

    public void destroy(Insumo elemento_seleccionado);

    public Collection<? extends Insumo> getItems();
    
}
