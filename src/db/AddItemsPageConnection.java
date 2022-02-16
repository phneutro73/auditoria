package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class AddItemsPageConnection {
	static String connectionUrl = "jdbc:sqlserver://azure-informatica.database.windows.net:1433;" + "database=pr-infor;"
			+ "user=flpi@azure-informatica;" + "password=Flyingpigs1!;" + "encrypt=true;"
			+ "trustServerCertificate=false;" + "hostNameInCertificate=*.database.windows.net;" + "loginTimeout=30";

	public Hashtable<String, Object> getItemWithBarCode(String barCode) {
		Connection conn = null;
		Hashtable<String, Object> item = new Hashtable<String, Object>();

		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("[sp_search_item] '" + barCode + "'");

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("nombre");
				int type = rs.getInt("tipo_id");
				String size = rs.getString("talla");
				double price = rs.getDouble("precio");

				item.put("id", id);
				item.put("name", name);
				item.put("barCode", barCode);
				item.put("typeId", type);
				item.put("size", size);
				item.put("price", price);
			}
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

	public boolean addNewItem(String barCode, String name, String type, String size, double price, int quantity,
			String shop) {

		boolean success = false;
		Connection conn = null;

		try {

			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT TOP 1 id FROM tipo_prenda WHERE nombre = '" + type + "'");
			int typeId = -1;
			while (rs.next()) {
				typeId = rs.getInt("id");
			}
			rs = stmt.executeQuery("SELECT TOP 1 id FROM tiendas WHERE nombre_tienda = '" + shop + "'");
			int shopId = -1;
			while (rs.next()) {
				shopId = rs.getInt("id");
			}

			String query = "[sp_add_new_item]" + "		@BAR_CODE = '" + barCode + "'," + "		@NAME = '" + name + "',"
					+ "		@TYPE_ID= " + typeId + "," + "		@SIZE= '" + size + "'," + "		@PRICE= " + price + ","
					+ "		@QUANTITY = " + quantity + "," + "		@SHOP_ID = " + shopId;
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

	public List<String> listShops() {
		Connection conn = null;
		List<String> shops = new ArrayList<>();
		shops.add("-");
		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT nombre_tienda FROM tiendas");

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

	public boolean updateItemQuantity(int itemId, String shop, int quantity) {

		boolean success = false;
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

			String query = "[sp_update_item_quantity]" + "		@ITEM_ID = " + itemId + "," + "		@SHOP_ID = "
					+ shopId + "," + "		@QUANTITY = " + quantity;
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
	
	public int getTypeId(String type) {

		Connection conn = null;
		int typeId = -1;

		try {

			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT TOP 1 id FROM tipo_prenda WHERE nombre = '" + type + "'");
			
			while (rs.next()) {
				typeId = rs.getInt("id");
			}

		} catch (Exception e) {
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
		
		return typeId;

	}

}
