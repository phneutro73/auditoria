package application;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
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
	private LineChart<String, Double> dailyEarningsChart;

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

		lblUserName.setText(currentUser.getUserName());
		initLineChar(statisticsDB);
		getRanking(statisticsDB);
		getUserStatistics(statisticsDB);

	}

	private void initLineChar(StatisticsPageConnection statisticsDB) {

		Calendar c = Calendar.getInstance();
		LocalDate currentDate = LocalDate.now();

		LocalDate firstDayMonth = LocalDate.now().withDayOfMonth(1);
		String strFirstDayMonth = firstDayMonth.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		LocalDate aux = firstDayMonth;
		LocalDate lastDayMonth = currentDate.withDayOfMonth(currentDate.getMonth().length(currentDate.isLeapYear()));
		String strLastDayMonth = lastDayMonth.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

		XYChart.Series<String, Double> series = new XYChart.Series<String, Double>();
		Hashtable<String, Object> data = statisticsDB.getUserLineChart(currentUser.getId(), strFirstDayMonth,
				strLastDayMonth);
		List<String> dates = (List<String>) data.get("dates");
		List<Double> values = (List<Double>) data.get("values");

		while (aux.compareTo(lastDayMonth) <= 0) {
			String strAux = aux.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
			String chartDate = aux.format(DateTimeFormatter.ofPattern("dd-MMM"));
			Double chartValue = 0.0;

			if (!dates.isEmpty() && !values.isEmpty() && dates.get(0).equals(strAux)) {
				chartValue = Double.valueOf(values.get(0));
				dates.remove(0);
				values.remove(0);
			}

			series.getData().add(new XYChart.Data<String, Double>(chartDate, chartValue));
			aux = aux.plusDays(1);
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
