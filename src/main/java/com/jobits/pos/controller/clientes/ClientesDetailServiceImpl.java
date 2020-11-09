/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.clientes;

import com.jobits.pos.adapters.repo.impl.ClienteDAO;
import com.jobits.pos.domain.models.Cliente;
import com.jobits.pos.domain.models.Orden;
import java.util.ArrayList;
import java.util.Collection;

public class ClientesDetailServiceImpl implements ClientesDetailService {

    @Override
    public void addOrdenToClientOrdenList(Cliente elemento_seleccionado, Orden ordenToAdd) {
        if (ordenToAdd == null || elemento_seleccionado == null) {
            throw new IllegalArgumentException("Elemento nulo pasado por parametro");
        }
        elemento_seleccionado.getOrdenList().add(ordenToAdd);
        ordenToAdd.setClientecodCliente(elemento_seleccionado);
        ClienteDAO.getInstance().startTransaction();
        ClienteDAO.getInstance().edit(elemento_seleccionado);
        ClienteDAO.getInstance().commitTransaction();
    }

    @Override
    public void crearCliente(Cliente nuevoCliente) {
        ClienteDAO.getInstance().startTransaction();
        if (nuevoCliente.getOrdenList() == null) {
            nuevoCliente.setOrdenList(new ArrayList<>());
        }
        ClienteDAO.getInstance().create(nuevoCliente);
        ClienteDAO.getInstance().commitTransaction();
        
    }

    @Override
    public void editarCliente(Cliente clienteEditado) {
        ClienteDAO.getInstance().startTransaction();
        ClienteDAO.getInstance().edit(clienteEditado);
        ClienteDAO.getInstance().commitTransaction();
    }

    @Override
    public Collection<? extends Cliente> getListaClientes() {
        return ClienteDAO.getInstance().findAll();
    }

}
