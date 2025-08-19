package org.nexus.registro_cliente.service;

import org.nexus.registro_cliente.repository.ClienteRepository;
import org.nexus.registro_cliente.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.stereotype.Service;

import java.util.List;


public class ClienteService implements IClienteService {

    public List<Cliente> listarClientes() {
        return List.of();
    }

    public Cliente buscarClienteporId(Integer codigo) {
        return null;
    }

    public void guardarCliente(Cliente cliente) {

    }

    public void eliminarCliente(Cliente cliente) {

    }
}