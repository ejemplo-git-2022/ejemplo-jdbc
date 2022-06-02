package edu.curso.java.jdbc;

import java.sql.*;

public class EjemploJDBC {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		PersonaDAO personaDAO = new PersonaDAO();
		Persona persona1 = new Persona();
		persona1.setNombre("Juan");
		persona1.setApellido("Perez");
		persona1.setEdad(20);
		
		Integer idGenerado = personaDAO.nuevaPersona(persona1);
		
		System.out.println(idGenerado);
		
		
	}

}
