/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.clientes;

import com.jobits.pos.adapters.repo.impl.ClienteDAO;
import com.jobits.pos.domain.models.Cliente;
import com.jobits.pos.domain.models.Insumo;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ClientesListController implements ClientesListService {

    public ClientesListController() {
    }

    @Override
    public void eliminar(Cliente elemento_seleccionado) {
        ClienteDAO.getInstance().startTransaction();
        ClienteDAO.getInstance().remove(elemento_seleccionado);
        ClienteDAO.getInstance().commitTransaction();
    }

    @Override
    public Collection<? extends Cliente> getListaClientes() {
        List<Cliente> retSorted = ClienteDAO.getInstance().findAll();
        Collections.sort(retSorted);
        return retSorted;
    }

}
