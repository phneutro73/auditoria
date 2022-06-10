package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import models.CurrentUser;

public class LoginPageConnection {
	static String connectionUrl = "jdbc:sqlserver://pr-infor.database.windows.net:1433;" + "database=pr-infor;"
			+ "user=admin2022@pr-infor;" + "password=Flipi2022;" + "encrypt=true;" + "trustServerCertificate=false;"
			+ "hostNameInCertificate=*.database.windows.net;" + "loginTimeout=30";

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
		// TODO: ver forma de hacer una sóla llamada a bbdd
		Connection conn = null;
		Hashtable<String, Object> user = new Hashtable<String, Object>();
		Hashtable<Integer, String> schedule = new Hashtable<Integer, String>();
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
			Date dob = null;
			String idNumber = null;
			int scheduleId = 0;
			int weekday = 0;
			int roleId = 0;
			String userName = null;
			int shopId = 0;
			String roleName = null;
			String shopName = null;
			String scheduleName = null;

			while (rs.next()) {
				id = rs.getInt("id");
				name = rs.getString("name");
				surname = rs.getString("surname");
				dob = rs.getDate("dob");
				idNumber = rs.getString("id_number");
				scheduleId = rs.getInt("schedule_id");
				weekday = rs.getInt("weekday");
				roleId = rs.getInt("role_id");
				userName = rs.getString("user_name");
				shopId = rs.getInt("tienda_id");

				Statement stmt2 = conn.createStatement();
				ResultSet rsSchedule = stmt2
						.executeQuery("SELECT TOP 1 schedule_name FROM schedules WHERE id = " + scheduleId);
				scheduleName = "-";
				while (rsSchedule.next()) {
					scheduleName = rsSchedule.getString("schedule_name");
				}
				schedule.put(weekday, scheduleName);
				ResultSet rsRole = stmt2.executeQuery("SELECT TOP 1 role_name FROM roles WHERE id = " + roleId);
				while (rsRole.next()) {
					roleName = rsRole.getString("role_name");
				}
				ResultSet rsShop = stmt2.executeQuery("SELECT TOP 1 nombre_tienda FROM tiendas WHERE id = " + shopId);
				while (rsShop.next()) {
					shopName = rsShop.getString("nombre_tienda");
				}
			}
			user.put("id", id);
			user.put("name", name);
			user.put("surname", surname);
			user.put("dob", dob);
			user.put("idNumber", idNumber);
			user.put("schedule", schedule);
			user.put("userName", userName);
			user.put("roleId", roleName);

			CurrentUser currentUser = new CurrentUser((int) user.get("id"), (String) user.get("name"),
					(String) user.get("surname"), (String) user.get("idNumber"), (String) user.get("userName"), email,
					roleId, shopId, (String) schedule.get(0), (String) schedule.get(1), (String) schedule.get(2),
					(String) schedule.get(3), (String) schedule.get(4), (String) schedule.get(5),
					(String) schedule.get(6));
			return currentUser;

		} catch (Exception e) {
			e.printStackTrace();
			CurrentUser error = new CurrentUser(-1, null, null, null, null, null, -1, -1, null, null, null, null, null,
					null, null);
			return error;
		}

	}
}
