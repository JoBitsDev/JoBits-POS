/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.areaventa;

import com.jobits.pos.controller.AbstractDetailController;
import com.jobits.pos.domain.models.Area;
import com.jobits.pos.domain.models.Mesa;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author ERIK QUESADA
 */
public interface AreaVentaService {

    public void createInstance();

    public void destroy(Area area_seleccionada);

    public AbstractDetailController<Area> getDetailControllerForEdit(Area area_seleccionada);

    public void removeMesa(Mesa selectedValue);

    public List<Area> getItems();

    public void createMesa(Area area_seleccionada, String id);

}
