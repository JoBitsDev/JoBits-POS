/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.almacen;

import com.jobits.pos.domain.models.Almacen;
import java.util.List;

/**
 *
 * @author ERIK QUESADA
 */
public interface AlmacenListService {

    public void createNewStorage(String storageName);

    public void update(Almacen elemento_seleccionado);

    public void destroy(Almacen elemento_seleccionado, int confirm);

    public List<Almacen> getItems();

}
