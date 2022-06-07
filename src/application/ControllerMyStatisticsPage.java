package application;

import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.ResourceBundle;

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
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private AnchorPane parent;

	@FXML
	private Label subtitle;

	@FXML
	private Label monthSales;

	@FXML
	private Label totalSales;

	@FXML
	private Label monthEarnings;

	@FXML
	private Label totalEarnings;

	@FXML
	private Label weekWorkHours;

	@FXML
	private Label mondayWorkHours;

	@FXML
	private Label tuesdayWorkHours;

	@FXML
	private Label wednesdayWorkHours;

	@FXML
	private Label thursdayWorkHours;

	@FXML
	private Label fridayWorkHours;

	@FXML
	private Label saturdayWorkHours;

	@FXML
	private Label sundayWorkHours;

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
		initLineChar();
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
}
