package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLDatabaseConnection {
	public static void main(String[] args) {

		String connectionUrl = "jdbc:sqlserver://azure-informatica.database.windows.net:1433;" + "database=pr-infor;"
				+ "user=flpi@azure-informatica;" + "password=Flyingpigs1!;" + "encrypt=true;"
				+ "trustServerCertificate=false;" + "hostNameInCertificate=*.database.windows.net;" + "loginTimeout=30";

		try (Connection connection = DriverManager.getConnection(connectionUrl);) {
			System.out.println("Connected to DB");
		}
		// Handle any errors that may have occurred.
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
