/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.area;

import com.jobits.pos.client.webconnection.BaseConnection;
import com.jobits.pos.controller.areaventa.AreaVentaService;
import com.jobits.pos.core.domain.models.Area;
import com.jobits.pos.core.domain.models.Mesa;

import java.util.List;

/**
 * JoBits
 *
 * @author Jorge
 */
public class AreaVentaWCS extends BaseConnection implements AreaVentaService {

    AreaVentaWCI service = retrofit.create(AreaVentaWCI.class);

    public AreaVentaWCS() {
        super();
    }

    @Override
    public Area create(Area t) throws RuntimeException {
        return handleCall(service.create(t));
    }

    @Override
    public Area edit(Area t) throws RuntimeException {
        return handleCall(service.edit(t));
    }

    @Override
    public Area destroy(Area t) throws RuntimeException {
        return destroyById(t.getCodArea());
    }

    @Override
    public Area destroyById(Object o) throws RuntimeException {
        return handleCall(service.destroyById(o));
    }

    @Override
    public Area findBy(Object o) throws RuntimeException {
        return handleCall(service.findBy(o));
    }

    @Override
    public List<Area> findAll() throws RuntimeException {
        return handleCall(service.findAll());

    }

    @Override
    public Area removeMesa(String codArea, String codMesa) {
        return handleCall(service.removeMesa(codArea, codMesa));
    }

    @Override
    public Area addMesa(String codArea, Mesa newmesa) {
        return handleCall(service.addMesa(codArea, newmesa));
    }

}
