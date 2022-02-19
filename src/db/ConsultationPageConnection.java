package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.ModelItemTable;
import models.ModelRoleTable;

public class ConsultationPageConnection {

	static String connectionUrl = "jdbc:sqlserver://azure-informatica.database.windows.net:1433;" + "database=pr-infor;"
			+ "user=flpi@azure-informatica;" + "password=Flyingpigs1!;" + "encrypt=true;"
			+ "trustServerCertificate=false;" + "hostNameInCertificate=*.database.windows.net;" + "loginTimeout=30";

	public ObservableList<ModelItemTable> getItemsTable(int shopId) {

		Connection conn = null;
		ResultSet rsItems = null;
		ObservableList<ModelItemTable> obList = FXCollections.observableArrayList();

		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			Statement stmt = conn.createStatement();
			rsItems = stmt.executeQuery("[sp_list_items] " + shopId);

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

	public boolean deleteItem(int itemId) {

		boolean success = false;
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("[sp_delete_item] " + itemId);
			success = true;

		} catch (SQLException e) {
			success = false;
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			success = true;
		}

		return success;
	}

	public ObservableList<ModelItemTable> getItemDetailTable(int itemId, int shopId) {

		Connection conn = null;
		ResultSet rsItems = null;
		ObservableList<ModelItemTable> obList = FXCollections.observableArrayList();

		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			Statement stmt = conn.createStatement();
			rsItems = stmt.executeQuery(
					"[sp_list_item_detail]" + "		@ID = " + itemId + "," + "		@SHOP_ID = " + shopId);

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

	public List<String> listShopsWithItem(int itemId) {
		Connection conn = null;
		List<String> shops = new ArrayList<>();
		shops.add("-");
		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT nombre_tienda FROM tiendas t INNER JOIN prenda_tienda pt ON t.id = pt.tienda_id WHERE pt.prenda_id = "
							+ itemId);

			while (rs.next()) {
				String shop = rs.getString("nombre_tienda");
				shops.add(shop);
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

		return shops;

	}

	public boolean createReservation(String shop, int itemId, String dni, String email) {

		boolean success = true;
		Connection conn = null;

		try {

			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT TOP 1 id FROM tiendas WHERE nombre_tienda = '" + shop + "'");
			int shopId = -1;
			while (rs.next()) {
				shopId = rs.getInt("id");
			}

			String query = "[sp_create_reservation]" + "		@SHOP_ID = " + shopId + "," + "		@ITEM_ID = "
					+ itemId + "," + "		@ID_NUMBER = '" + dni + "'," + "		@EMAIL = '" + email + "'";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.executeUpdate();
			success = true;

		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			success = true;
		}

		return success;
	}

	public List<String> listItemTypes() {
		Connection conn = null;
		List<String> types = new ArrayList<>();
		types.add("-");
		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT nombre FROM tipo_prenda");

			while (rs.next()) {
				String type = rs.getString("nombre");
				types.add(type);
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

		return types;

	}

	public Hashtable<String, Object> getItem(int itemId) {
		Connection conn = null;
		Hashtable<String, Object> item = new Hashtable<String, Object>();

		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT TOP 1 CB FROM prendas WHERE id = " + itemId);
			String itemBarCode = "";
			while (rs.next()) {
				itemBarCode = rs.getString("CB");
			}
			stmt = conn.createStatement();
			rs = stmt.executeQuery("[sp_search_item] '" + itemBarCode + "'");

			int id = 0;
			String name = null;
			int typeId = 0;
			String strType = null;
			String size = null;
			Double price = 0.0;

			while (rs.next()) {
				id = rs.getInt("id");
				name = rs.getString("nombre");
				typeId = rs.getInt("tipo_id");
				size = rs.getString("talla");
				price = rs.getDouble("precio");

				Statement stmt2 = conn.createStatement();
				ResultSet rsSchedule = stmt2
						.executeQuery("SELECT TOP 1 nombre FROM tipo_prenda WHERE id = " + typeId);
				strType = "-";
				while (rsSchedule.next()) {
					strType = rsSchedule.getString("nombre");
				}
				
			}

			item.put("name", name);
			item.put("itemBarCode", itemBarCode);
			item.put("type", strType);
			item.put("size", size);
			item.put("price", price);

		} catch (SQLException e) {
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

		return item;

	}

}
