package edu.curso.java.jdbc;

import java.sql.*;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EjemploJDBC {

	private final static Logger log = LogManager.getLogger(EjemploJDBC.class);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		log.info("Inicio del programa");
		PersonaDAO personaDAO = new PersonaDAO();
		
		Persona persona1 = new Persona();
		persona1.setNombre("Juan");
		persona1.setApellido("Perez");
		persona1.setEdad(20);
		
		Integer idGenerado;
		try {
			idGenerado = personaDAO.nuevaPersona(persona1);
			log.debug("Id generado: " + idGenerado);

		} catch (PersonaException e) {
			log.error("Hay un error al ejecutar el alta", e);
		}
		
		log.info("Fin del programa");

	}

}
