package org.nexus.registro_cliente.controller;


import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.nexus.registro_cliente.entity.Cliente;
import org.nexus.registro_cliente.service.IClienteService;
import org.primefaces.PrimeFaces;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

//alcance de tipo VIEW
@ViewScoped
//componente generico
@Component
//Getter y Seters de lombok
@Data
public class IndexController {



    @Autowired
    IClienteService clienteService;
    private List<Cliente> clientes;
    private Cliente clienteSeleccionado;
    private static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @PostConstruct
    public void init(){
        cargarDatos();
    }

    private void cargarDatos() {
        this.clientes = this.clienteService.listarClientes();
        this.clientes.forEach(cliente -> logger.info(cliente.toString()));
    }
    public void agregarCliente(){
        this.clienteSeleccionado = new Cliente();
    }
    public void guardarCliente(){
        logger.info("Cliente a guardar" + this.clienteSeleccionado);
        //agregar
        if (this.clienteSeleccionado.getCodigoCliente()==null){
            this.clienteService.guardarCliente(this.clienteSeleccionado);
            this.clientes.add(this.clienteSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente agregado"));

        }
        //modificar
        else{
            this.clienteService.guardarCliente(this.clienteSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Cliente actualizado"));
        }
        //ocultar la ventana modal
        PrimeFaces.current().executeScript("PF('ventanaModalCliente').hide()");
        //actualizar tabla utilizada tecnologica incorporada - AJAX
        PrimeFaces.current().ajax().update("formulario-clientes:mensaje-emergente","formulario-clientes:tabla-clientes");
        //Limpiar el objeto cliente seleccionado
        this.clienteSeleccionado =null;
    }
    public void eliminarCliente(){
        logger.info("Cliente a eliminar" +this.clienteSeleccionado);
        this.clienteService.eliminarCliente(this.clienteSeleccionado);
        //eliminar el registro de la lista de CLientes
        this.clientes.remove(this.clienteSeleccionado);
        //Reiniciar el objeto Cliente seleccionado
        this.clienteSeleccionado =null;
        FacesContext.getCurrentInstance().addMessage(
                null,new FacesMessage("Cliente eliminado"));
        PrimeFaces.current().ajax().update("formulario-clientes:mensaje-emergente",
                "formulario-clientes:tabla-clientes");
    }
}