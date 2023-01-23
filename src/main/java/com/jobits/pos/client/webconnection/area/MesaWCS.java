/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.area;

import com.jobits.pos.client.webconnection.BaseConnection;
import com.jobits.pos.controller.areaventa.MesaService;
import com.jobits.pos.core.domain.models.Area;
import com.jobits.pos.core.domain.models.Mesa;

import java.util.List;

/**
 * JoBits
 *
 * @author Jorge
 */
public class MesaWCS extends BaseConnection implements MesaService {

    MesaWCI service = retrofit.create(MesaWCI.class);

    public MesaWCS() {
        super();
    }

    @Override
    public Mesa edit(Mesa t) throws RuntimeException {
        return handleCall(service.edit(t));
    }

    @Override
    public Mesa findBy(Object o) throws RuntimeException {
        return handleCall(service.findBy(o));
    }

    @Override
    public List<Mesa> findAll() throws RuntimeException {
        return handleCall(service.findAll());

    }

    @Override
    public List<Mesa> getListaMesasDisponibles() {
        return handleCall(service.findAll());
    }

    @Override
    public List<Mesa> getListaMesas(String delArea) {
        return handleCall(service.getListaMesas(delArea));
    }

    @Override
    public List<Area> getListaAreasDisponibles() {
        return handleCall(service.getListaAreasDisponibles());
    }

}
