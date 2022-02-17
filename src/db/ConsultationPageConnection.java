package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.ModelItemTable;
import models.ModelRoleTable;

public class ConsultationPageConnection {

	static String connectionUrl = "jdbc:sqlserver://azure-informatica.database.windows.net:1433;" + "database=pr-infor;"
			+ "user=flpi@azure-informatica;" + "password=Flyingpigs1!;" + "encrypt=true;"
			+ "trustServerCertificate=false;" + "hostNameInCertificate=*.database.windows.net;" + "loginTimeout=30";

	public ObservableList<ModelItemTable> getItemsTable() {

		Connection conn = null;
		ResultSet rsItems = null;
		ObservableList<ModelItemTable> obList = FXCollections.observableArrayList();

		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			Statement stmt = conn.createStatement();
			rsItems = stmt.executeQuery("[sp_list_items]");

			while (rsItems.next()) {
				obList.add(new ModelItemTable(rsItems.getInt("id"), rsItems.getString("cb"),
						rsItems.getString("nombre"), rsItems.getString("tipo_id"), rsItems.getString("talla"),
						rsItems.getInt("cantidad_total"), rsItems.getInt("cantidad_en_tienda"),
						rsItems.getInt("cantidad_reservas"), rsItems.getDouble("precio")));
			}

		}

		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}

		return obList;
	}

}
