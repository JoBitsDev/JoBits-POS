/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.cliente;

import com.jobits.pos.client.webconnection.CRUDBaseConnection;
import com.jobits.pos.cliente.core.domain.ClienteDomain;
import com.jobits.pos.cliente.core.usecase.ClienteUseCase;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class ClienteWCS extends CRUDBaseConnection<ClienteDomain> implements ClienteUseCase {

    ClienteWCI service = retrofit.create(ClienteWCI.class);

    public ClienteWCS() {
        super();
    }


    @Override
    public ClienteDomain findClientByPhone(String phone) {
        return handleCall(service.findByPhone(phone));
    }


}
