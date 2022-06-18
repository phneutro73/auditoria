package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.ModelUserTable;
import models.ModelWorkersTodayTable;

public class StatisticsPageConnection {

	static String connectionUrl = "jdbc:sqlserver://pr-infor.database.windows.net:1433;" + "database=pr-infor;"
			+ "user=admin2022@pr-infor;" + "password=Flipi2022;" + "encrypt=true;" + "trustServerCertificate=false;"
			+ "hostNameInCertificate=*.database.windows.net;" + "loginTimeout=30";

	public List<String> getUserRanking(int shopId, int limit) {
		Connection conn = null;
		List<String> userNames = new ArrayList<>();
		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("[sp_get_user_ranking] " + shopId + ", " + limit);

			while (rs.next()) {
				String aux = rs.getString("USER_NAME");
				userNames.add(aux);
			}
			while (userNames.size() <= limit) {
				userNames.add("-");
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
		return userNames;
	}

	public Hashtable<String, Object> getUserStatistics(int userId) {
		Connection conn = null;
		Hashtable<String, Object> userStatistics = new Hashtable<String, Object>();
		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("[sp_get_statistics_user] " + userId);

			Double totalEarnings = 0.0;
			int numSales = 0;
			Double lastMonthEarnings = 0.0;
			int numLastMonthSales = 0;

			while (rs.next()) {
				totalEarnings = rs.getDouble("GANANCIAS_TOTALES");
				numSales = rs.getInt("NUM_VENTAS");
				lastMonthEarnings = rs.getDouble("GANANCIAS_ULT_MES");
				numLastMonthSales = rs.getInt("NUM_VENTAS_ULT_MES");
			}

			userStatistics.put("totalEarnings", totalEarnings);
			userStatistics.put("numSales", numSales);
			userStatistics.put("lastMonthEarnings", lastMonthEarnings);
			userStatistics.put("numLastMonthSales", numLastMonthSales);

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
		return userStatistics;
	}

	public Hashtable<String, Object> getUserLineChart(int userId, String firstDay, String lastDay) {
		Connection conn = null;
		Hashtable<String, Object> data = new Hashtable<String, Object>();
		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(
					"[sp_get_statistics_user_earnings_chart] " + userId + ", '" + firstDay + "', '" + lastDay + "'");

			List<String> dates = new ArrayList<>();
			List<Double> values = new ArrayList<>();

			while (rs.next()) {
				String date = rs.getString("date");
				Double value = (Double) rs.getDouble("earnings");
				dates.add(date);
				values.add(value);
			}

			data.put("dates", dates);
			data.put("values", values);

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
		return data;
	}

	public Hashtable<String, Object> getShopStatistics(int shopId) {
		Connection conn = null;
		Hashtable<String, Object> userStatistics = new Hashtable<String, Object>();
		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("[sp_get_statistics_shop] " + shopId);

			Double totalEarnings = 0.0;
			int numSales = 0;
			Double lastMonthEarnings = 0.0;
			int numLastMonthSales = 0;
			int numWorkers = 0;
			int numItemsStock = 0;
			int numItemsSoldToday = 0;

			while (rs.next()) {
				totalEarnings = rs.getDouble("GANANCIAS_TOTALES");
				numSales = rs.getInt("NUM_VENTAS");
				lastMonthEarnings = rs.getDouble("GANANCIAS_ULT_MES");
				numLastMonthSales = rs.getInt("NUM_VENTAS_ULT_MES");
				numWorkers = rs.getInt("NUM_TRABAJADORES");
				numItemsStock = rs.getInt("NUM_PRENDAS");
				numItemsSoldToday = rs.getInt("NUM_PRENDAS_HOY");
			}

			userStatistics.put("totalEarnings", totalEarnings);
			userStatistics.put("numSales", numSales);
			userStatistics.put("lastMonthEarnings", lastMonthEarnings);
			userStatistics.put("numLastMonthSales", numLastMonthSales);
			userStatistics.put("numWorkers", numWorkers);
			userStatistics.put("numItemsStock", numItemsStock);
			userStatistics.put("numItemsSoldToday", numItemsSoldToday);

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
		return userStatistics;
	}

	public Hashtable<String, Object> getShopLineChart(int shopId, String firstDay, String lastDay) {
		Connection conn = null;
		Hashtable<String, Object> data = new Hashtable<String, Object>();
		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(
					"[sp_get_statistics_shop_earnings_chart] " + shopId + ", '" + firstDay + "', '" + lastDay + "'");

			List<String> dates = new ArrayList<>();
			List<Double> values = new ArrayList<>();

			while (rs.next()) {
				String date = rs.getString("date");
				Double value = (Double) rs.getDouble("earnings");
				dates.add(date);
				values.add(value);
			}

			data.put("dates", dates);
			data.put("values", values);

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
		return data;
	}

	public ObservableList<ModelWorkersTodayTable> getWorkersTodayTable(int shopId) {

		Connection conn = null;
		ResultSet rsUsers = null;
		ObservableList<ModelWorkersTodayTable> obList = FXCollections.observableArrayList();

		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			Statement stmt = conn.createStatement();
			rsUsers = stmt.executeQuery("[sp_get_schedule_workers_today] " + shopId);

			while (rsUsers.next()) {
				obList.add(
						new ModelWorkersTodayTable(rsUsers.getString("user_name"), rsUsers.getString("schedule_name")));
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
		return obList;
	}
}
