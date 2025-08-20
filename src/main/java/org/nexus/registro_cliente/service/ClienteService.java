package org.nexus.registro_cliente.service;

import ch.qos.logback.core.net.server.Client;
import org.nexus.registro_cliente.repository.ClienteRepository;
import org.nexus.registro_cliente.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements IClienteService {
    //Inyeccion de dependencias
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes;
    }

    @Override
    public Cliente buscarClienteporId(Integer codigo) {
        Cliente cliente = clienteRepository.findById(codigo).orElse(null);

        return cliente;
    }

    @Override
    public void guardarCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @Override
    public void eliminarCliente(Cliente cliente) {
        clienteRepository.delete(cliente);
    }
}