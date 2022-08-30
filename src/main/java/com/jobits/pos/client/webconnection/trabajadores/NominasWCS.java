/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.trabajadores;

import com.jobits.pos.client.webconnection.BaseConnection;
import com.jobits.pos.controller.trabajadores.NominasUseCase;
import com.jobits.pos.controller.trabajadores.PersonalUseCase;
import com.jobits.pos.core.domain.AsistenciaPersonalEstadisticas;
import com.jobits.pos.core.domain.models.Personal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class NominasWCS extends BaseConnection implements NominasUseCase {

    NominasWCI service = retrofit.create(NominasWCI.class);

    public NominasWCS() {
        super();
    }

    @Override
    public List<AsistenciaPersonalEstadisticas> getPersonalActivo(LocalDate desde, LocalDate hasta) {
        return handleCall(service.getPersonalActivo(desde, hasta));
    }

    @Override
    public void imprimirEstadisticas(List<AsistenciaPersonalEstadisticas> list) {
          handleCall(service.imprimirEstadisticas(list));
    }

    @Override
    public List<AsistenciaPersonalEstadisticas> pagar(List<AsistenciaPersonalEstadisticas> list,
            LocalDate hasta, boolean imprmimr) {
        return handleCall(service.pagar(list, hasta, imprmimr));
    }

    

}
