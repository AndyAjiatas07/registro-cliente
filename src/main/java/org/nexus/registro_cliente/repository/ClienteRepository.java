package org.nexus.registro_cliente.repository;
import org.nexus.registro_cliente.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
    //puede sustituir al DAO
    //Una interfaz que tiene todos los metodos genericos del CRUD o mantenimiento

}
