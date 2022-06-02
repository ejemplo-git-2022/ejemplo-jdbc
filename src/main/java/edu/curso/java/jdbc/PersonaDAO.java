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
		
	}
	
	public void borrarPersonaPorId(Integer id) {
		
	}
	
	public Persona recuperarPersonaPorId(Integer id) {
		return null;
	}
	
	public ArrayList<Persona> recuperarPersonas() {
		return null;
	}

}
