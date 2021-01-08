/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.almacen;

import com.jobits.pos.controller.AbstractDetailController;
import com.jobits.pos.domain.models.Transaccion;
import java.awt.Container;
import java.util.List;

/**
 *
 * @author Home
 */
public interface TransaccionListService {

    void constructView(Container parent);

    void destroy(Transaccion selected);

    AbstractDetailController<Transaccion> getDetailControllerForEdit(Transaccion selected);

    AbstractDetailController<Transaccion> getDetailControllerForNew();

    List<Transaccion> getItems();

    void imprimirTransaccionesSeleccionadas(List<Transaccion> selectedsObjects);
    
}
