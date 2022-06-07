package edu.curso.java.jdbc;

import java.sql.*;
import java.util.ArrayList;

public class EjemploJDBC {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Inicio del programa");
		PersonaDAO personaDAO = new PersonaDAO();
/*		Persona persona1 = new Persona();
		persona1.setNombre("Juan");
		persona1.setApellido("Perez");
		persona1.setEdad(20);
		
		Integer idGenerado = personaDAO.nuevaPersona(persona1);
		
		System.out.println(idGenerado);*/
		
		
		/*Persona personaRecupera = personaDAO.recuperarPersonaPorId(8);
		System.out.println(personaRecupera);
		
		personaRecupera.setNombre("Maria");
		personaDAO.actualizarPersona(personaRecupera);*/
		
		//personaDAO.borrarPersonaPorId(8);
		
		ArrayList<Persona> personas = personaDAO.recuperarPersonas();
		for (Persona p : personas) {
			System.out.println(p);
		}
		
		System.out.println("Fin del programa");

	}

}
