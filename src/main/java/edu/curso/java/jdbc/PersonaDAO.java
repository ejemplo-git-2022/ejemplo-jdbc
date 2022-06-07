package edu.curso.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PersonaDAO {
	
	public Integer nuevaPersona(Persona persona) {
		
		Connection connection = null;
		Integer idGenerado = null;
		try {
			//REGISTRAR EL DRIVER JDBC DE MYSQL
			Class.forName("com.mysql.jdbc.Driver");
			String urlConnection = "jdbc:mysql://localhost:3306/ejemplojdbc";
			connection = DriverManager.getConnection(urlConnection, "root", "root");
			
			String insertSQL = "INSERT INTO personas (nombre, apellido, edad) VALUEs (?, ?, ?)";
			PreparedStatement insertSQLConsulta = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
			insertSQLConsulta.setString(1, persona.getNombre());
			insertSQLConsulta.setString(2, persona.getApellido());
			insertSQLConsulta.setInt(3, persona.getEdad());
			insertSQLConsulta.executeUpdate();
			
			ResultSet consultaDeId = insertSQLConsulta.getGeneratedKeys();
			if(consultaDeId.next()) {
				idGenerado = consultaDeId.getInt(1);
				
				System.out.println("ID generado: " + idGenerado);
			}
			
			/*String consultaSQL = "SELECT idPersona, nombre, apellido, edad FROM personas";
			PreparedStatement comandoSQLConsulta = connection.prepareStatement(consultaSQL);
			ResultSet resultado = comandoSQLConsulta.executeQuery();
			
			while(resultado.next() == true) {
				System.out.println("***********************************");
				System.out.println("Id persona: " + resultado.getInt("idPersona"));
				System.out.println("nombre: " + resultado.getString("nombre"));
				System.out.println("apellido: " + resultado.getString("apellido"));
				System.out.println("edad: " + resultado.getInt("edad"));				
			}*/
			
			
		} catch (SQLException e) {
			System.out.println("Hay un error con el SQL");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Hay un error generico...");
			e.printStackTrace();
		} finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (Exception e2) {}
			}
		}
		return idGenerado;
	}
	
	public void actualizarPersona(Persona persona) {
		Connection connection = null;
		Integer idGenerado = null;
		try {
			//REGISTRAR EL DRIVER JDBC DE MYSQL
			Class.forName("com.mysql.jdbc.Driver");
			String urlConnection = "jdbc:mysql://localhost:3306/ejemplojdbc";
			connection = DriverManager.getConnection(urlConnection, "root", "root");
			
			String updateSQL = "UPDATE personas SET nombre = ?, apellido = ?, edad = ? WHERE idPersona = ?";
			PreparedStatement insertSQLConsulta = connection.prepareStatement(updateSQL);
			insertSQLConsulta.setString(1, persona.getNombre());
			insertSQLConsulta.setString(2, persona.getApellido());
			insertSQLConsulta.setInt(3, persona.getEdad());
			insertSQLConsulta.setInt(4, persona.getIdPersona());
			insertSQLConsulta.executeUpdate();
		
		} catch (SQLException e) {
			System.out.println("Hay un error con el SQL");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Hay un error generico...");
			e.printStackTrace();
		} finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (Exception e2) {}
			}
		}
	}
	
	public void borrarPersonaPorId(Integer id) {
		Connection connection = null;
		try {
			//REGISTRAR EL DRIVER JDBC DE MYSQL
			Class.forName("com.mysql.jdbc.Driver");
			String urlConnection = "jdbc:mysql://localhost:3306/ejemplojdbc";
			connection = DriverManager.getConnection(urlConnection, "root", "root");
			String deleteSQL = "DELETE FROM personas WHERE idPersona = ?";
			PreparedStatement deleteSQLConsulta = connection.prepareStatement(deleteSQL);
			deleteSQLConsulta.setInt(1, id);
			deleteSQLConsulta.executeUpdate(); // SI en ROW Count es > 0 se pudo borrar un registro
		} catch (SQLException e) {
			System.out.println("Hay un error con el SQL");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Hay un error generico...");
			e.printStackTrace();
		} finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (Exception e2) {}
			}
		}
	}
	
	public Persona recuperarPersonaPorId(Integer id) {
		Persona persona = null;
		
		Connection connection = null;
		try {
			//REGISTRAR EL DRIVER JDBC DE MYSQL
			Class.forName("com.mysql.jdbc.Driver");
			String urlConnection = "jdbc:mysql://localhost:3306/ejemplojdbc";
			connection = DriverManager.getConnection(urlConnection, "root", "root");
			
			String consultaSQL = "SELECT idPersona, nombre, apellido, edad FROM personas WHERE idPersona = ?";
			PreparedStatement comandoSQLConsulta = connection.prepareStatement(consultaSQL);
			comandoSQLConsulta.setInt(1, id);
			ResultSet resultado = comandoSQLConsulta.executeQuery();
			
			if(resultado.next() == true) {
				persona= new Persona();				
				persona.setIdPersona(resultado.getInt("idPersona"));
				persona.setNombre(resultado.getString("nombre"));
				persona.setApellido(resultado.getString("apellido"));
				persona.setEdad(resultado.getInt("edad"));
			}
			
		} catch (SQLException e) {
			System.out.println("Hay un error con el SQL");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Hay un error generico...");
			e.printStackTrace();
		} finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (Exception e2) {}
			}
		}
			
		return persona;	
	}
	
	public ArrayList<Persona> recuperarPersonas() {
		ArrayList<Persona> personas = new ArrayList<Persona>();
		Persona persona = null;
				
		Connection connection = null;
		Integer idGenerado = null;
		try {
			//REGISTRAR EL DRIVER JDBC DE MYSQL
			Class.forName("com.mysql.jdbc.Driver");
			String urlConnection = "jdbc:mysql://localhost:3306/ejemplojdbc";
			connection = DriverManager.getConnection(urlConnection, "root", "root");
			
			String consultaSQL = "SELECT idPersona, nombre, apellido, edad FROM personas";
			PreparedStatement comandoSQLConsulta = connection.prepareStatement(consultaSQL);
			ResultSet resultado = comandoSQLConsulta.executeQuery();
			
			while(resultado.next() == true) {
				persona= new Persona();				
				persona.setIdPersona(resultado.getInt("idPersona"));
				persona.setNombre(resultado.getString("nombre"));
				persona.setApellido(resultado.getString("apellido"));
				persona.setEdad(resultado.getInt("edad"));
				personas.add(persona);
			}
			
		} catch (SQLException e) {
			System.out.println("Hay un error con el SQL");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Hay un error generico...");
			e.printStackTrace();
		} finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (Exception e2) {}
			}
		}
		
		
		return personas;
	}

}
