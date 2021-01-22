/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.usecase.mesa;

import com.jobits.pos.core.domain.models.Area;
import com.jobits.pos.core.domain.models.Mesa;
import java.util.List;

/**
 *
 * @author Jorge
 */
public interface MesaUseCase {

    public List<Mesa> getListaMesas(Area delArea);
    
    public List<Area> getListaAreasDisponibles();

}
