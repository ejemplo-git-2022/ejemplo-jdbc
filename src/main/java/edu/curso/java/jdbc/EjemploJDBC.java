package edu.curso.java.jdbc;

import java.sql.*;
import java.util.ArrayList;

public class EjemploJDBC {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Inicio del programa");
		PersonaDAO personaDAO = new PersonaDAO();
		
		Persona persona1 = new Persona();
		persona1.setNombre("Juan");
		persona1.setApellido("Perez");
		persona1.setEdad(20);
		
		Integer idGenerado;
		try {
			idGenerado = personaDAO.nuevaPersona(persona1);
			System.out.println(idGenerado);

		} catch (PersonaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
		
		System.out.println("Fin del programa");

	}

}
