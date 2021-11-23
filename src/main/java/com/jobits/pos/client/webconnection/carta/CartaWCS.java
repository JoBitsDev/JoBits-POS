/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.carta;

import com.jobits.pos.client.webconnection.BaseConnection;
import com.jobits.pos.controller.seccion.CartaListService;
import com.jobits.pos.core.domain.models.Carta;
import java.util.List;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class CartaWCS extends BaseConnection implements CartaListService {

    CartaWCI service = retrofit.create(CartaWCI.class);

    public CartaWCS() {
        super();
    }

    @Override
    public Carta create(Carta t) throws RuntimeException {
        return handleCall(service.create(t));
    }

    @Override
    public Carta edit(Carta t) throws RuntimeException {
        return handleCall(service.edit(t));
    }

    @Override
    public Carta destroy(Carta t) throws RuntimeException {
        return destroyById(t.getCodCarta());
    }

    @Override
    public Carta destroyById(Object o) throws RuntimeException {
        return handleCall(service.destroyById(o));
    }

    @Override
    public Carta findBy(Object o) throws RuntimeException {
        return handleCall(service.findBy(o));
    }

    @Override
    public List<Carta> findAll() throws RuntimeException {
        return handleCall(service.findAll());

    }

}
