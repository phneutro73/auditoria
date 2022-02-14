package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import models.CurrentUser;

public class LoginPageConnection {
	static String connectionUrl = "jdbc:sqlserver://azure-informatica.database.windows.net:1433;" + "database=pr-infor;"
			+ "user=flpi@azure-informatica;" + "password=Flyingpigs1!;" + "encrypt=true;"
			+ "trustServerCertificate=false;" + "hostNameInCertificate=*.database.windows.net;" + "loginTimeout=30";

	public byte[] getSalt(String email) {
		Connection conn = null;
		byte[] salt = null;
		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT password_salt FROM logings WHERE email = '" + email + "'");

			while (rs.next()) {
				salt = rs.getBytes("password_salt");

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

		return salt;
	}

	public byte[] getHash(String email) {
		Connection conn = null;
		byte[] salt = null;
		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT password_hash FROM logings WHERE email = '" + email + "'");

			while (rs.next()) {
				salt = rs.getBytes("password_hash");

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

		return salt;
	}

	public CurrentUser getCurrentUser(String email) {
		Connection conn = null;
		Hashtable<String, Object> user = new Hashtable<String, Object>();
		int id = -1;
		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT user_id FROM logings WHERE email = '" + email + "'");

			while (rs.next()) {
				id = rs.getInt("user_id");
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

		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("[sp_search_user] " + id);

			String name = null;
			String surname = null;
			String idNumber = null;
			int roleId = 0;
			String userName = null;
			int shopId = 0;

			while (rs.next()) {
				name = rs.getString("name");
				surname = rs.getString("surname");
				idNumber = rs.getString("id_number");
				roleId = rs.getInt("role_id");
				userName = rs.getString("user_name");
				shopId = rs.getInt("tienda_id");
			}

			user.put("id", String.valueOf(id));
			user.put("name", name);
			user.put("surname", surname);
			user.put("idNumber", idNumber);
			user.put("roleId", roleId);
			user.put("email", email);
			user.put("userName", userName);
			user.put("shopId", shopId);

			CurrentUser currentUser = new CurrentUser(id, name, surname, idNumber, userName, email, roleId, shopId);
			return currentUser;

		} catch (Exception e) {
			e.printStackTrace();
			CurrentUser error = new CurrentUser(-1, null, null, null, null, null, -1, -1);
			return error;
		}

	}
}
