/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.clientes;

import com.jobits.pos.adapters.repo.impl.ClienteDAO;
import com.jobits.pos.domain.models.Cliente;
import java.util.Collection;

public class ClientesListServiceImpl implements ClientesListService {

    public ClientesListServiceImpl() {
    }

    @Override
    public void eliminar(Cliente elemento_seleccionado) {
        ClienteDAO.getInstance().startTransaction();
        ClienteDAO.getInstance().remove(elemento_seleccionado);
        ClienteDAO.getInstance().commitTransaction();
    }

    @Override
    public Collection<? extends Cliente> getListaClientes() {
        return ClienteDAO.getInstance().findAll();
    }

}
