package org.nexus.registro_cliente;

import org.nexus.registro_cliente.entity.Cliente;
import org.nexus.registro_cliente.service.IClienteService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class RegistroClienteApplication implements CommandLineRunner{
	//Inyeccion de dependencias
	@Autowired
	private IClienteService clienteService;

	private static final Logger logger = LoggerFactory.getLogger(RegistroClienteApplication.class);

	String salto=System.lineSeparator();

	public static void main(String[] args){
		logger.info("iniciando la aplicacion");
		SpringApplication.run(RegistroClienteApplication.class, args);
		logger.info("Aplicacion finalizada");
	}

	@Override
	public void run(String... args) throws Exception {
registroClientesApp();

	}

	private void registroClientesApp(){
		logger.info(" ++++++Bienvenido a la aplicacion de Registro de Cliente++++++");
		var salir = false;
		var consola = new Scanner(System.in);
		while (!salir){
			var opcion = mostrarMenu(consola);
			salir = ejecutarOpciones(consola, opcion);
			logger.info(salto);
		}
	}

	private int mostrarMenu(Scanner consola){
		logger.info("""
             ***Aplicacion***
             1. Listar Clientes
             2. Buscar Clientes
             3. Agregar Clientes
             4. Modificar Clientes
             5. Eliminar Clientes
             6. Salir
             """);
		var opcion = Integer.parseInt(consola.nextLine());
		return opcion;
	}

	private boolean ejecutarOpciones(Scanner consola, int opcion){
		var salir = false;
		switch (opcion){
			case 1 -> {
				logger.info(salto+"***Lista de Clientes***"+salto);
				List<Cliente> clientes = clienteService.listarClientes();
				clientes.forEach(cliente -> logger.info(cliente.toString()+salto));
			}
			case 2 -> {
				logger.info(salto+"****Buscar Cliente***"+salto);
				logger.info(salto+"Ingrese el id del cliente a buscar"+salto);
				var codigo = Integer.parseInt(consola.nextLine());
				Cliente cliente = clienteService.buscarClienteporId(codigo);
				if (cliente != null){
					logger.info("Cliente encontrado: " + cliente + salto);
				}else {
					logger.info("Cliente no encontrado " + cliente +salto);
				}
			}
			case 3 -> {
				logger.info(salto+"***Agregar Cliente***"+salto);
				logger.info(salto+"Ingrese el nombre de el nuevo cliente"+salto);
				var nombre = consola.nextLine();
				logger.info(salto+"Ingrese el apellido de el nuevo cliente"+salto);
				var apellido = consola.nextLine();
				logger.info(salto+"Ingrese el telefono de el nuevo cliente"+salto);
				var telefono = consola.nextLine();
				logger.info(salto+"Ingrese el correo de el nuevo cliente"+salto);
				var correo = consola.nextLine();
				logger.info(salto+"Ingrese el genero de el nuevo cliente"+salto);
				var genero = consola.nextLine();
				logger.info(salto+"Ingrese la edad de el nuevo cliente"+salto);
				var edad = Integer.parseInt(consola.nextLine());
				var cliente = new Cliente();
				cliente.setNombre(nombre);
				cliente.setApellido(apellido);
				cliente.setTelefono(telefono);
				cliente.setCorreo(correo);
				cliente.setGenero(genero);
				cliente.setEdad(edad);
				clienteService.guardarCliente(cliente);
				logger.info("Cliente agregado: " + cliente +salto);
			}
			case 4 -> {
				logger.info(salto+"***Modificar Cliente***"+salto);
				//buscar por codigo
				logger.info("Ingrese el codigo del cliente a modificar");
				var codigo = Integer.parseInt(consola.nextLine());
				Cliente cliente = clienteService.buscarClienteporId(codigo);
				//guardar si no es null
				if (cliente != null){
					logger.info(salto+"Ingrese el nuevo nombre de el cliente"+salto);
					var nombre = consola.nextLine();
					logger.info(salto+"Ingrese el nuevo apellido de el cliente"+salto);
					var apellido = consola.nextLine();
					logger.info(salto+"Ingrese el nuevo telefono de el cliente"+salto);
					var telefono = consola.nextLine();
					logger.info(salto+"Ingrese el nuevo correo de el cliente"+salto);
					var correo = consola.nextLine();
					logger.info(salto+"Ingrese el nuevo genero de el cliente"+salto);
					var genero = consola.nextLine();
					logger.info(salto+"Ingrese la nueva edad de el cliente"+salto);
					var edad = Integer.parseInt(consola.nextLine());
					cliente.setNombre(nombre);
					cliente.setApellido(apellido);
					cliente.setTelefono(telefono);
					cliente.setCorreo(correo);
					cliente.setGenero(genero);
					cliente.setEdad(edad);
					clienteService.guardarCliente(cliente);
					logger.info("Cliente modificado: " + cliente +salto);
				}else {
					logger.info("Cliente no encontrado " + cliente + salto);
				}
			}
			case 5 ->{
				logger.info(salto + "***Eliminar cliente***");
				logger.info(salto + "Ingrese el codigo del cliente a eliminar");
				var codigo = Integer.parseInt(consola.nextLine());
				var cliente = clienteService.buscarClienteporId(codigo);
				if (cliente != null){
					clienteService.eliminarCliente(cliente);
					logger.info("Cliente eliminado: " + cliente + salto);
				}else {
					logger.info("Cliente no encontrado " + cliente +salto);
				}

			}
			case 6 ->{
				logger.info("Hasta pronto "+salto+salto);
				salir = true;
			}
			default -> logger.info("Opcion invalida");
		}
		return false;
	}
}
