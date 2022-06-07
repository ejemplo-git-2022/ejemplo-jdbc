package edu.curso.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PersonaDAO {
	
	private Connection crearNuevaConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		String urlConnection = "jdbc:mysql://localhost:3306/ejemplojdbc";
		return DriverManager.getConnection(urlConnection, "root", "root");
	}
	
	public Integer nuevaPersona(Persona persona) throws PersonaException  {
		
		Connection connection = null;
		Integer idGenerado = null;
		try {
			connection = this.crearNuevaConnection();
			
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
		
			
		} catch (SQLException e) {
			System.out.println("Hay un error con el SQL");
			throw new PersonaException("No pudimos dar de alta la persona", e);
			
		} catch (Exception e) {
			System.out.println("Hay un error generico...");
			
			throw new PersonaException("No pudimos dar de alta la persona", e);
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
			connection = this.crearNuevaConnection();

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
			connection = this.crearNuevaConnection();

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
			connection = this.crearNuevaConnection();

			
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
			connection = this.crearNuevaConnection();
			
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
