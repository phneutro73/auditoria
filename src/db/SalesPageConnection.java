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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.ModelHistoricalSalesDataTable;
import models.ModelItemTable;
import models.ModelTicketTable;

public class SalesPageConnection {
	static String connectionUrl = "jdbc:sqlserver://pr-infor.database.windows.net:1433;" + "database=pr-infor;"
			+ "user=admin2022@pr-infor;" + "password=Flipi2022;" + "encrypt=true;" + "trustServerCertificate=false;"
			+ "hostNameInCertificate=*.database.windows.net;" + "loginTimeout=30";

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
				int typeId = rs.getInt("tipo_id");
				String type = rs.getString("nombre_tipo");
				String size = rs.getString("talla");
				double price = rs.getDouble("precio");

				item.put("id", id);
				item.put("name", name);
				item.put("barCode", barCode);
				item.put("typeId", typeId);
				item.put("type", type);
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

	public ObservableList<ModelTicketTable> getTicketTable(int userId) {

		Connection conn = null;
		ResultSet rsItems = null;
		ObservableList<ModelTicketTable> obList = FXCollections.observableArrayList();

		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			Statement stmt = conn.createStatement();
			rsItems = stmt.executeQuery("[sp_list_ticket] " + userId);

			while (rsItems.next()) {
				obList.add(new ModelTicketTable(rsItems.getInt("id_prenda"), rsItems.getString("CB"),
						rsItems.getString("nombre"), rsItems.getString("nombre_tipo"), rsItems.getString("talla"),
						rsItems.getDouble("precio")));
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

	public boolean addItemToTicket(int itemId, int userId, int quantity) {

		boolean success = false;
		Connection conn = null;

		try {

			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			String query = "[sp_add_item_ticket]" + "		@ITEM_ID = " + itemId + "," + "		@USER_ID = " + userId
					+ "," + "		@QUANTITY= " + quantity;
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
		}

		return success;

	}

	public boolean deleteTicketItem(int ticketId, int userId) {

		boolean success = false;
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(
					"[sp_delete_ticket_item]" + "		@TICKET_ID = " + ticketId + "," + "		@USER_ID = " + userId);
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
		}

		return success;
	}

	public boolean deleteTicket(int userId) {

		boolean success = false;
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("[sp_delete_ticket]" + userId);
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
		}

		return success;

	}

	public boolean makeASale(int itemId, int userId, int quantity, int shopId) {

		boolean success = false;
		Connection conn = null;

		try {

			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			String query = "[sp_add_sale]" + "		@ITEM_ID = " + itemId + "," + "		@USER_ID = " + userId + ","
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
		}

		return success;

	}

	public boolean checkIsInShop(int itemId, int shopId) {
		boolean isInShop = false;
		Connection conn = null;
		ResultSet rsItems = null;

		try {

			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			Statement stmt = conn.createStatement();
			rsItems = stmt.executeQuery(
					"[sp_check_item_shop]" + "		@ITEM_ID = " + itemId + "," + "		@SHOP_ID = " + shopId);

			while (rsItems.next()) {
				if (rsItems.getInt("cantidad") > 0) {
					isInShop = true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			isInShop = false;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}

		return isInShop;

	}

	public ObservableList<ModelHistoricalSalesDataTable> getHistoricalSalesTable() {

		Connection conn = null;
		ResultSet rsItems = null;
		ObservableList<ModelHistoricalSalesDataTable> obList = FXCollections.observableArrayList();

		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			Statement stmt = conn.createStatement();
			rsItems = stmt.executeQuery("[sp_list_historical_sales]");

			while (rsItems.next()) {
				obList.add(new ModelHistoricalSalesDataTable(rsItems.getInt("id_prenda"), rsItems.getString("CB"),
						rsItems.getString("user_name"), rsItems.getString("nombre_tienda"), rsItems.getDouble("precio"),
						rsItems.getDate("venta_datetime")));
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

	public boolean unfinishedTicket(int userId) {
		Connection conn = null;
		ResultSet rsItems = null;
		boolean isTicket = false;

		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			Statement stmt = conn.createStatement();
			rsItems = stmt.executeQuery("[sp_list_ticket] " + userId);

			while (rsItems.next()) {
				isTicket = true;
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

		return isTicket;
	}

}
