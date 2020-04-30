package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	static {
		try {
			System.out.println("Procurando o driver...");
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver encontrado com sucesso!");
		} catch (ClassNotFoundException ex) {
			System.err.println("O driver não foi encontrado.");
			System.out.println(ex);
		}
	}

	public static Connection conectar() {
		try {
			String server = "127.0.0.1:3306";

			String database = "portal_realnews";

			String url = "jdbc:mysql://" + server + "/" + database;

			String parameters = "?useTimezone=true&serverTimezone=UTC";

			String username = "admin";

			String password = "@Mpx458mg9";
			return DriverManager.getConnection(url + parameters, username, password);
		} catch (SQLException ex) {
			System.err.println("Não foi possível conectar!");
			ex.printStackTrace();
			return null;
		}
	}

}
