/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.puntoelaboracion;

import com.jobits.pos.client.webconnection.BaseConnection;
import com.jobits.pos.controller.puntoelaboracion.PuntoElaboracionService;
import com.jobits.pos.core.domain.models.Cocina;
import java.util.List;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class PuntoElaboracionWCS extends BaseConnection implements PuntoElaboracionService {

    PuntoElaboracionWCI service = retrofit.create(PuntoElaboracionWCI.class);

    public PuntoElaboracionWCS() {
        super();
    }

    @Override
    public Cocina create(Cocina t) throws RuntimeException {
        return handleCall(service.create(t));
    }

    @Override
    public Cocina edit(Cocina t) throws RuntimeException {
        return handleCall(service.edit(t));
    }

    @Override
    public Cocina destroy(Cocina t) throws RuntimeException {
        return destroyById(t.getCodCocina());
    }

    @Override
    public Cocina destroyById(Object o) throws RuntimeException {
        return handleCall(service.destroyById(o));
    }

    @Override
    public Cocina findBy(Object o) throws RuntimeException {
        return handleCall(service.findBy(o));
    }

    @Override
    public List<Cocina> findAll() throws RuntimeException {
        return handleCall(service.findAll());

    }

    @Override
    public Cocina destroyInCascade(String codCocina) {
        return handleCall(service.destroyInCascade(codCocina));

    }

    @Override
    public Cocina edit(String codCocina, String name) {
        return handleCall(service.edit(codCocina, name));
    }

}
