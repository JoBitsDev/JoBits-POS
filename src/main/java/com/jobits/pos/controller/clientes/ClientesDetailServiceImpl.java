/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.clientes;

import com.jobits.pos.adapters.repo.impl.ClienteDAO;
import com.jobits.pos.controller.AbstractDetailController;
import com.jobits.pos.domain.models.Cliente;
import com.jobits.pos.domain.models.Orden;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class ClientesDetailServiceImpl extends AbstractDetailController<Cliente> implements ClientesDetailService {

    private boolean creatingMode = true;

    public ClientesDetailServiceImpl() {
        super(ClienteDAO.getInstance());
        instance = createNewInstance();
    }

    public ClientesDetailServiceImpl(Cliente instance) {
        super(instance, ClienteDAO.getInstance());
        creatingMode = false;
    }

    @Override
    public void addOrdenToClientOrdenList(Cliente elemento_seleccionado, Orden ordenToAdd) {
        if (ordenToAdd == null || elemento_seleccionado == null) {
            throw new IllegalArgumentException("Elemento nulo pasado por parametro");
        }
        for (Orden orden : elemento_seleccionado.getOrdenList()) {
            if (orden.getCodOrden().equals(ordenToAdd.getCodOrden())) {
                elemento_seleccionado.getOrdenList().remove(orden);
            }
        }
        elemento_seleccionado.getOrdenList().add(ordenToAdd);
        ordenToAdd.setClienteIdCliente(elemento_seleccionado);
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

    @Override
    public Cliente createNewInstance() {
        Cliente cliente = new Cliente();
        cliente.setNombreCliente("");
        cliente.setApellidosCliente("");
        cliente.setAliasCliente("");
        cliente.setTelefonoCliente("");
        cliente.setFechanacCliente(new Date());
        cliente.setDireccionCliente("");
        cliente.setMunicipioCliente("");
        cliente.setPrivinciaCliente("");
        cliente.setOrdenList(new ArrayList<>());
        return cliente;
    }

    @Override
    public void constructView(Container parent) {
    }
    

    @Override
    public boolean isCreatingMode() {
        return creatingMode;
    }
    

}
