/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.carta;

import com.jobits.pos.client.webconnection.BaseConnection;
import com.jobits.pos.controller.seccion.SeccionListService;
import com.jobits.pos.core.domain.models.Seccion;
import java.util.List;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class SeccionWCS extends BaseConnection implements SeccionListService {

    SeccionWCI service = retrofit.create(SeccionWCI.class);

    public SeccionWCS() {
        super();
    }

    @Override
    public Seccion edit(Seccion t) throws RuntimeException {
        return handleCall(service.edit(t));
    }

    @Override
    public Seccion destroy(Seccion t) throws RuntimeException {
        return destroyById(t.getNombreSeccion());
    }

    @Override
    public Seccion destroyById(Object o) throws RuntimeException {
        return handleCall(service.destroyById(o));
    }

    @Override
    public Seccion findBy(Object o) throws RuntimeException {
        return handleCall(service.findBy(o));
    }

    @Override
    public List<Seccion> findAll() throws RuntimeException {
        return handleCall(service.findAll());

    }

    @Override
    public List<Seccion> findSeccionesByMesa(String codMesa) {
        return handleCall(service.findSeccionesByMesa(codMesa));
    }

    @Override
    public Seccion moveSeccionToCarta(String seccionNombre, String codigoCarta) {
        return handleCall(service.moveSeccionToCarta(seccionNombre, codigoCarta));

    }

}
