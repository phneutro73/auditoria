package db;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministratorPageConnection {
	static String connectionUrl = "jdbc:sqlserver://azure-informatica.database.windows.net:1433;" + "database=pr-infor;"
			+ "user=flpi@azure-informatica;" + "password=Flyingpigs1!;" + "encrypt=true;"
			+ "trustServerCertificate=false;" + "hostNameInCertificate=*.database.windows.net;" + "loginTimeout=30";

	public List<String> listRoles() {
		Connection conn = null;
		List<String> roles = new ArrayList<>();
		roles.add("-");
		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT role_name FROM roles");

			while (rs.next()) {
				String role = rs.getString("role_name");
				roles.add(role);
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

		return roles;
	}

	public List<String> listSchedules() {
		Connection conn = null;
		List<String> schedules = new ArrayList<>();
		schedules.add("-");
		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT schedule_name FROM schedules");

			while (rs.next()) {
				String schedule = rs.getString("schedule_name");
				schedules.add(schedule);
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

		return schedules;

	}
	
	public Hashtable<String,Integer> scheduleMap(){
		Hashtable<String,Integer> scheduleMap = new Hashtable<String,Integer>();
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM schedules");

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("schedule_name");
				scheduleMap.put(name,id);
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
		return scheduleMap;
	}
	public boolean addUser(String name, String surname, String dob, String username, String idNumber, String email,
			byte[] salt, byte[] hash, int mon, int tue, int wed, int thu, int fri, int sat, int sun) {
		boolean succes = false;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			String query = ("[sp_create_user]" + "		@NAME = '" + name + "',"
					+ "		@SURNAME = '" + surname + "'," + "		@DOB = '" + dob + "'," + "		@USERNAME = '"
					+ username + "'," + "		@ID_NUMBER = '" + idNumber + "'," + "		@EMAIL = '" + email + "',"
					+ "		@PASSWORD_SALT = ?," + "		@PASSWORD_HASH = ?,"
					+ "		@Mon_SCHEDULE = " + mon + "," + "		@Tue_SCHEDULE = " + tue + ","
					+ "		@Wed_SCHEDULE = " + wed + "," + "		@Thu_SCHEDULE = " + thu + ","
					+ "		@Fri_SCHEDULE = " + fri + "," + "		@Sat_SCHEDULE = " + sat + ","
					+ "		@Sun_SCHEDULE = " + sun + "");
			
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setBytes(1, salt);
			statement.setBytes(2, hash);
			statement.executeUpdate();
			succes = true;
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
		return succes;
	}
}
