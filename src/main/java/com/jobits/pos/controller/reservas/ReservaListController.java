/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.reservas;

import com.jobits.pos.adapters.repo.impl.ClienteDAO;
import com.jobits.pos.adapters.repo.impl.OrdenDAO;
import com.jobits.pos.domain.models.Cliente;
import com.jobits.pos.domain.models.Orden;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Home
 */
public class ReservaListController implements ReservaListService {

    public ReservaListController() {
    }

    @Override
    public Collection<? extends Orden> getListaReservas() {
        List<Orden> ret = new ArrayList<>();
        OrdenDAO.getInstance().findAll().stream().filter(x -> (x.getVentaid() == null)).forEachOrdered(x -> {
            ret.add(x);
        });
        Collections.sort(ret);
        return ret;
    }

    @Override
    public void deleteReserva(Orden orden) {
        OrdenDAO.getInstance().startTransaction();
        OrdenDAO.getInstance().remove(orden);
        OrdenDAO.getInstance().commitTransaction();
    }

}
