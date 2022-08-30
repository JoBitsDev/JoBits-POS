/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.trabajadores;

import com.jobits.pos.client.webconnection.BaseConnection;
import com.jobits.pos.controller.trabajadores.PuestoTrabajoUseCase;
import com.jobits.pos.core.domain.models.PuestoTrabajo;
import java.util.List;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class PuestoTrabajoWCS extends BaseConnection implements PuestoTrabajoUseCase {

    PuestoTrabajoWCI service = retrofit.create(PuestoTrabajoWCI.class);

    public PuestoTrabajoWCS() {
        super();
    }

    @Override
    public PuestoTrabajo create(PuestoTrabajo t) throws RuntimeException {
        return handleCall(service.create(t));
    }

    @Override
    public PuestoTrabajo edit(PuestoTrabajo t) throws RuntimeException {
        return handleCall(service.edit(t));
    }

    @Override
    public PuestoTrabajo destroy(PuestoTrabajo t) throws RuntimeException {
        return destroyById(t.getNombrePuesto());
    }

    @Override
    public PuestoTrabajo destroyById(Object o) throws RuntimeException {
        return handleCall(service.destroyById(o));
    }

    @Override
    public PuestoTrabajo findBy(Object o) throws RuntimeException {
        return handleCall(service.findBy(o));
    }

    @Override
    public List<PuestoTrabajo> findAll() throws RuntimeException {
        return handleCall(service.findAll());
        
    }

}
