package edu.curso.java.jdbc;

import java.sql.*;

public class EjemploJDBC {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection connection = null;
		
		try {
			//REGISTRAR EL DRIVER JDBC DE MYSQL
			Class.forName("com.mysql.jdbc.Driver");
			String urlConnection = "jdbc:mysql://localhost:3306/ejemplojdbc";
			connection = DriverManager.getConnection(urlConnection, "root", "root");
			
			String consultaSQL = "SELECT idPersona, nombre, apellido, edad FROM personas";
			PreparedStatement comandoSQLConsulta = connection.prepareStatement(consultaSQL);
			ResultSet resultado = comandoSQLConsulta.executeQuery();
			
			while(resultado.next() == true) {
				System.out.println("***********************************");
				System.out.println("Id persona: " + resultado.getInt("idPersona"));
				System.out.println("nombre: " + resultado.getString("nombre"));
				System.out.println("apellido: " + resultado.getString("apellido"));
				System.out.println("edad: " + resultado.getInt("edad"));				
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
		
	}

}
