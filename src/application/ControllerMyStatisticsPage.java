package application;

import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.ResourceBundle;

import db.StatisticsPageConnection;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import models.CurrentUser;

public class ControllerMyStatisticsPage {

	double height;
	double width;
	CurrentUser currentUser;

	public ControllerMyStatisticsPage(double height, double width, CurrentUser currentUser) {
		this.height = height;
		this.width = width;
		this.currentUser = currentUser;
	}

	@FXML
	private AnchorPane parent;

	@FXML
	private Label subtitle;

	@FXML
	private Label lblUserName;

	@FXML
	private Label lblMonthSales;

	@FXML
	private Label lblTotalSales;

	@FXML
	private Label lblMonthEarnings;

	@FXML
	private Label lblTotalEarnings;

	@FXML
	private LineChart<String, Number> dailyEarningsChart;

	@FXML
	private Label ranking1;

	@FXML
	private Label ranking2;

	@FXML
	private Label ranking3;

	@FXML
	private Label ranking4;

	@FXML
	private Label ranking5;

	@FXML
	private Label ranking6;

	@FXML
	private Label ranking7;

	@FXML
	private Label ranking8;

	@FXML
	private Label ranking9;

	@FXML
	private Label ranking10;

	@FXML
	void initialize() {

		StatisticsPageConnection statisticsDB = new StatisticsPageConnection();

		initLineChar();
		getRanking(statisticsDB);
		getUserStatistics(statisticsDB);

	}

	private void initLineChar() {

		Calendar c = Calendar.getInstance();
		int monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		LocalDate currentDate = LocalDate.now();

		int currentMonth = currentDate.getMonthValue();
		String strCurrentMonth = String.valueOf(currentMonth);
		while (strCurrentMonth.length() < 2) {
			strCurrentMonth = '0' + strCurrentMonth;
		}

		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
		for (int i = 1; i <= monthMaxDays; i++) {
			String strChartDay = String.valueOf(i);
			while (strChartDay.length() < 2) {
				strChartDay = '0' + strChartDay;
			}
			String chartDate = strChartDay + '/' + strCurrentMonth;
			series.getData().add(new XYChart.Data<String, Number>(chartDate, 0.0));
		}

		dailyEarningsChart.getData().addAll(series);
		dailyEarningsChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent");
	}

	private void getRanking(StatisticsPageConnection statisticsDB) {
		List<String> ranking = statisticsDB.getUserRanking(currentUser.getShopId(), 10);
		ranking1.setText(ranking.get(0));
		ranking2.setText(ranking.get(1));
		ranking3.setText(ranking.get(2));
		ranking4.setText(ranking.get(3));
		ranking5.setText(ranking.get(4));
		ranking6.setText(ranking.get(5));
		ranking7.setText(ranking.get(6));
		ranking8.setText(ranking.get(7));
		ranking9.setText(ranking.get(8));
		ranking10.setText(ranking.get(9));

	}

	private void getUserStatistics(StatisticsPageConnection statisticsDB) {
		Hashtable<String, Object> userStatistics = statisticsDB.getUserStatistics(currentUser.getId());
		lblMonthEarnings.setText(userStatistics.get("lastMonthEarnings").toString());
		lblTotalEarnings.setText(userStatistics.get("totalEarnings").toString());
		lblMonthSales.setText(userStatistics.get("numSales").toString());
		lblTotalSales.setText(userStatistics.get("numLastMonthSales").toString());
	}
}
