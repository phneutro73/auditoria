package db;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.ModelItemTypeTable;
import models.ModelRoleTable;
import models.ModelScheduleTable;
import models.ModelShopTable;
import models.ModelUserTable;

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

	public List<String> listActiveUsers() {
		Connection conn = null;
		List<String> activeUsers = new ArrayList<>();
		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("[sp_list_active_users]");

			while (rs.next()) {
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String idNumber = rs.getString("id_number");
				String completeName = name + " " + surname + ", " + idNumber;
				activeUsers.add(completeName);
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

		return activeUsers;
	}

	public Hashtable<String, Integer> usersMap() {
		Connection conn = null;
		Hashtable<String, Integer> activeUsers = new Hashtable<String, Integer>();

		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("[sp_list_active_users]");

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String idNumber = rs.getString("id_number");
				String completeName = name + " " + surname + ", " + idNumber;
				activeUsers.put(completeName, id);
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
		return activeUsers;

	}

	public void deleteUser(int userId) {
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("[sp_delete_user] " + userId);

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

	public Hashtable<String, Integer> scheduleMap() {
		Hashtable<String, Integer> scheduleMap = new Hashtable<String, Integer>();
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM schedules");

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("schedule_name");
				scheduleMap.put(name, id);
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
			byte[] salt, byte[] hash, int mon, int tue, int wed, int thu, int fri, int sat, int sun, int roleId) {
		boolean success = false;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			String query = ("[sp_create_user]" + "		@NAME = '" + name + "'," + "		@SURNAME = '" + surname
					+ "'," + "		@DOB = '" + dob + "'," + "		@USERNAME = '" + username + "',"
					+ "		@ID_NUMBER = '" + idNumber + "'," + "		@EMAIL = '" + email + "',"
					+ "		@PASSWORD_SALT = ?," + "		@PASSWORD_HASH = ?," + "		@Mon_SCHEDULE = " + mon
					+ "," + "		@Tue_SCHEDULE = " + tue + "," + "		@Wed_SCHEDULE = " + wed + ","
					+ "		@Thu_SCHEDULE = " + thu + "," + "		@Fri_SCHEDULE = " + fri + ","
					+ "		@Sat_SCHEDULE = " + sat + "," + "		@Sun_SCHEDULE = " + sun + ",		@ROLE_ID = "
					+ roleId);

			PreparedStatement statement = conn.prepareStatement(query);
			statement.setBytes(1, salt);
			statement.setBytes(2, hash);
			statement.executeUpdate();
			success = true;
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
		return success;
	}

	public ObservableList<ModelUserTable> getUsersTable() {

		Connection conn = null;
		ResultSet rsUsers = null;
		ObservableList<ModelUserTable> obList = FXCollections.observableArrayList();

		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			Statement stmt = conn.createStatement();
			rsUsers = stmt.executeQuery("[sp_list_active_users]");

			while (rsUsers.next()) {
				obList.add(new ModelUserTable(rsUsers.getInt("id"), rsUsers.getString("name"),
						rsUsers.getString("surname"), rsUsers.getString("id_number"), rsUsers.getString("dob"),
						rsUsers.getString("role_name")));
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

	public ObservableList<ModelScheduleTable> getSchedulesTable() {

		Connection conn = null;
		ResultSet rsSchedules = null;
		ObservableList<ModelScheduleTable> obList = FXCollections.observableArrayList();

		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			Statement stmt = conn.createStatement();
			rsSchedules = stmt.executeQuery("[sp_list_schedules]");

			while (rsSchedules.next()) {
				obList.add(new ModelScheduleTable(rsSchedules.getInt("id"), rsSchedules.getString("schedule_name"),
						rsSchedules.getString("check_in_time"), rsSchedules.getString("check_out_time")));
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

	public ObservableList<ModelRoleTable> getRolesTable() {

		Connection conn = null;
		ResultSet rsRoles = null;
		ObservableList<ModelRoleTable> obList = FXCollections.observableArrayList();

		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			Statement stmt = conn.createStatement();
			rsRoles = stmt.executeQuery("[sp_list_roles]");

			while (rsRoles.next()) {
				obList.add(new ModelRoleTable(rsRoles.getInt("id"), rsRoles.getString("role_name"),
						rsRoles.getInt("count_users_role")));
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

	public Hashtable<String, String> getRole(int roleId) {
		Connection conn = null;
		Hashtable<String, String> role = new Hashtable<String, String>();

		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("[sp_search_role] " + roleId);

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("role_name");
				role.put("id", String.valueOf(id));
				role.put("name", name);
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
		return role;
	}

	public boolean updateRole(int roleId, String roleName) {
		boolean success = false;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			String query = ("[sp_update_role]" + "		@ID = " + roleId + "," + "		@NAME = '" + roleName + "'");

			PreparedStatement statement = conn.prepareStatement(query);
			statement.executeUpdate();
			success = true;
		}

		catch (SQLException e) {
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

	public Hashtable<String, Object> getUser(int userId) {
		Connection conn = null;
		Hashtable<String, Object> user = new Hashtable<String, Object>();
		Hashtable<Integer, Integer> schedule = new Hashtable<Integer, Integer>();

		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("[sp_search_user] " + userId);

			int id = 0;
			String name = null;
			String surname = null;
			Date dob = null;
			String idNumber = null;
			int scheduleId = 0;
			int weekday = 0;
			int roleId = 0;
			String email = null;
			String userName = null;

			while (rs.next()) {
				id = rs.getInt("id");
				name = rs.getString("name");
				surname = rs.getString("surname");
				dob = rs.getDate("dob");
				idNumber = rs.getString("id_number");
				scheduleId = rs.getInt("schedule_id");
				weekday = rs.getInt("weekday");
				roleId = rs.getInt("role_id");
				schedule.put(weekday, scheduleId);
				email = rs.getString("email");
				userName = rs.getString("user_name");
			}

			user.put("id", String.valueOf(id));
			user.put("name", name);
			user.put("surname", surname);
			user.put("dob", dob);
			user.put("idNumber", idNumber);
			user.put("schedule", schedule);
			user.put("roleId", roleId);
			user.put("email", email);
			user.put("userName", userName);

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

		return user;

	}

	public boolean updateUser(int userId, String name, String surname, String dob, String username, String idNumber,
			String email, byte[] salt, byte[] hash, int mon, int tue, int wed, int thu, int fri, int sat, int sun,
			int roleId) {
		boolean success = false;
		Connection conn = null;

		try {

			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			String query = ("[sp_update_user]" + "		@ID = " + userId + "," + "		@NAME = '" + name + "',"
					+ "		@SURNAME = '" + surname + "'," + "		@DOB = '" + dob + "'," + "		@USERNAME = '"
					+ username + "'," + "		@ID_NUMBER = '" + idNumber + "'," + "		@EMAIL = '" + email + "',"
					+ "		@PASSWORD_SALT = ?," + "		@PASSWORD_HASH = ?," + "		@Mon_SCHEDULE = " + mon
					+ "," + "		@Tue_SCHEDULE = " + tue + "," + "		@Wed_SCHEDULE = " + wed + ","
					+ "		@Thu_SCHEDULE = " + thu + "," + "		@Fri_SCHEDULE = " + fri + ","
					+ "		@Sat_SCHEDULE = " + sat + "," + "		@Sun_SCHEDULE = " + sun + "," + "		@ROLE_ID = "
					+ roleId);

			PreparedStatement statement = conn.prepareStatement(query);
			statement.setBytes(1, salt);
			statement.setBytes(2, hash);
			statement.executeUpdate();
			success = true;

		} catch (SQLException e) {
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

	public boolean addSchedule(String name, String check_in_time, String check_out_time) {

		boolean success = true;
		Connection conn = null;

		try {

			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			String query = "[sp_create_schedule]" + "		@NAME = '" + name + "'," + "		@CHECK_IN_TIME = '"
					+ check_in_time + "'," + "		@CHECK_OUT_TIME = '" + check_out_time + "'";
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

	public Hashtable<String, String> getSchedule(int scheduleId) {
		Connection conn = null;
		Hashtable<String, String> schedule = new Hashtable<String, String>();

		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("[sp_search_schedule] " + scheduleId);

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("schedule_name");
				String checkInTime = rs.getString("check_in_time");
				String checkOutTime = rs.getString("check_out_time");
				schedule.put("id", String.valueOf(id));
				schedule.put("name", name);
				schedule.put("checkInTime", checkInTime);
				schedule.put("checkOutTime", checkOutTime);
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
		return schedule;
	}

	public boolean updateSchedule(int scheduleId, String name, String checkInTime, String checkOutTime) {
		boolean success = false;
		Connection conn = null;

		try {

			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			String query = "[sp_update_schedule]" + "		@ID = " + scheduleId + "," + "		@NAME = '" + name + "',"
					+ "		@CHECK_IN_TIME = '" + checkInTime + "'," + "		@CHECK_OUT_TIME = '" + checkOutTime
					+ "'";

			PreparedStatement statement = conn.prepareStatement(query);
			statement.executeUpdate();
			success = true;

		} catch (SQLException e) {
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

	public void deleteSchedule(int scheduleId) {
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("[sp_delete_schedule] " + scheduleId);

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

	}
	
	public boolean addRole(String name) {

		boolean success = true;
		Connection conn = null;

		try {

			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			String query = "[sp_create_role]" + "		@NAME = '" + name + "'";
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
	
	public void deleteRole(int roleId) {
		
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("[sp_delete_role] " + roleId);

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
	}
	
	public ObservableList<ModelShopTable> getShopsTable() {

		Connection conn = null;
		ResultSet rsShops = null;
		ObservableList<ModelShopTable> obList = FXCollections.observableArrayList();

		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			Statement stmt = conn.createStatement();
			rsShops = stmt.executeQuery("[sp_list_shops]");

			while (rsShops.next()) {
				obList.add(new ModelShopTable(rsShops.getInt("id"), rsShops.getString("nombre_tienda"),
						rsShops.getString("direccion_tienda"), rsShops.getInt("count_users_shop")));
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
	
	public ObservableList<ModelItemTypeTable> getItemTypesTable() {

		Connection conn = null;
		ResultSet rsItemTypes = null;
		ObservableList<ModelItemTypeTable> obList = FXCollections.observableArrayList();

		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			Statement stmt = conn.createStatement();
			rsItemTypes = stmt.executeQuery("[sp_list_shops]");

			while (rsItemTypes.next()) {
				obList.add(new ModelItemTypeTable(rsItemTypes.getInt("id"), rsItemTypes.getString("nombre")));
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
