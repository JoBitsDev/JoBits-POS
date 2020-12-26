/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.reservas;

import com.jobits.pos.domain.models.Orden;
import java.util.Collection;

/**
 *
 * @author Home
 */
public interface ReservaListService {

    public Collection<? extends Orden>  getListaReservas();

    public void deleteReserva(Orden orden);

}
