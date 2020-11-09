/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.clientes;

import com.jobits.pos.domain.models.Cliente;
import java.util.Collection;

/**
 *
 * @author Home
 */
public interface ClientesDetailService {

    public void crearCliente(Cliente nuevoCliente);

    public void editarCliente(Cliente clienteEditado);

    public void addOrdenToClientOrdenList(Cliente elemento_seleccionado);

    public Collection<? extends Cliente> getListaClientes();

}
