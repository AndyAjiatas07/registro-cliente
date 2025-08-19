package org.nexus.registro_cliente;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class RegistroClienteApplication implements CommandLineRunner{

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
             5. Salir
             """);
		var opcion = Integer.parseInt(consola.nextLine());
		return opcion;
	}

	private boolean ejecutarOpciones(Scanner consola, int opcion){
		return false;
	}
}
