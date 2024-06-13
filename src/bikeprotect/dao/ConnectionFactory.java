package bikeprotect.dao;

import java.sql.*;


public class ConnectionFactory {
	
	public static Connection conectar() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String usuario = "postgres";
        String senha = "admin";
        return DriverManager.getConnection(url,usuario, senha);
    }
	
}