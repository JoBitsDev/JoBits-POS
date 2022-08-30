/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.trabajadores;

import com.jobits.pos.client.webconnection.BaseConnection;
import com.jobits.pos.controller.trabajadores.PersonalUseCase;
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
public class PersonalWCS extends BaseConnection implements PersonalUseCase {

    PersonalWCI service = retrofit.create(PersonalWCI.class);

    public PersonalWCS() {
        super();
    }

    @Override
    public Personal create(Personal t) throws RuntimeException {
        return handleCall(service.create(t));
    }

    @Override
    public Personal edit(Personal t) throws RuntimeException {
        return handleCall(service.edit(t));
    }

    @Override
    public Personal destroy(Personal t) throws RuntimeException {
        return destroyById(t.getUsuario());
    }

    @Override
    public Personal destroyById(Object o) throws RuntimeException {
        return handleCall(service.destroyById(o));
    }

    @Override
    public Personal findBy(Object o) throws RuntimeException {
        return handleCall(service.findBy(o));
    }

    @Override
    public List<Personal> findAll() throws RuntimeException {
        return handleCall(service.findAll());

    }

    @Override
    public Personal pagarTrabajador(String usuario, LocalDate date) {
        return handleCall(service.pagar(usuario,date));
    }

}
