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
			String sqlInsert = "INSERT INTO PERSONAS (nombre, apellido, edad) VALUES (?, ?, ?)";
			PreparedStatement comandoSQL = connection.prepareStatement(sqlInsert);
			comandoSQL.setString(1, "Juan");
			comandoSQL.setString(2, "Perez");
			comandoSQL.setInt(3, 34);
			comandoSQL.execute();
			
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
