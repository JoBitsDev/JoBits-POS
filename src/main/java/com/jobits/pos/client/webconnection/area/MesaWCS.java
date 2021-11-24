/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.area;

import com.jobits.pos.client.webconnection.BaseConnection;
import com.jobits.pos.controller.areaventa.MesaService;
import com.jobits.pos.core.domain.models.Mesa;
import java.util.List;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class MesaWCS extends BaseConnection implements MesaService {

    MesaWCI service = retrofit.create(MesaWCI.class);

    public MesaWCS() {
        super();
    }

    @Override
    public Mesa create(Mesa t) throws RuntimeException {
        return handleCall(service.create(t));
    }

    @Override
    public Mesa edit(Mesa t) throws RuntimeException {
        return handleCall(service.edit(t));
    }

    @Override
    public Mesa destroy(Mesa t) throws RuntimeException {
        return destroyById(t.getCodMesa());
    }

    @Override
    public Mesa destroyById(Object o) throws RuntimeException {
        return handleCall(service.destroyById(o));
    }

    @Override
    public Mesa findBy(Object o) throws RuntimeException {
        return handleCall(service.findBy(o));
    }

    @Override
    public List<Mesa> findAll() throws RuntimeException {
        return handleCall(service.findAll());

    }

}
