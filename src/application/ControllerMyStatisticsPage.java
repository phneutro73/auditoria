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

public class ControllerMyStatisticsPage {

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

		// series.getData().add(new XYChart.Data<String, Number>("02/06", null));
		/*
		 * series.getData().add(new XYChart.Data<String, Double>("01/06", 0.0));
		 * series.getData().add(new XYChart.Data<String, Double>("02/06", 9.0));
		 * series.getData().add(new XYChart.Data<String, Double>("03/06", 25.0));
		 * series.getData().add(new XYChart.Data<String, Double>("04/06", 12.5));
		 * series.getData().add(new XYChart.Data<String, Double>("05/06", 14.4));
		 * series.getData().add(new XYChart.Data<String, Double>("06/06", 59.7));
		 * series.getData().add(new XYChart.Data<String, Double>("07/06", 12.7));
		 * series.getData().add(new XYChart.Data<String, Double>("08/06", 37.0));
		 * series.getData().add(new XYChart.Data<String, Double>("09/06", 3.4));
		 * series.getData().add(new XYChart.Data<String, Double>("10/06", 14.6));
		 * series.getData().add(new XYChart.Data<String, Double>("11/06", 69.0));
		 * series.getData().add(new XYChart.Data<String, Double>("12/06", 15.9));
		 * series.getData().add(new XYChart.Data<String, Double>("13/06", 10.0));
		 * series.getData().add(new XYChart.Data<String, Double>("14/06", 2.0));
		 * series.getData().add(new XYChart.Data<String, Double>("15/06", 12.0));
		 * series.getData().add(new XYChart.Data<String, Double>("16/06", 12.0));
		 * series.getData().add(new XYChart.Data<String, Double>("17/06", 15.0));
		 * series.getData().add(new XYChart.Data<String, Double>("18/06", 15.0));
		 * series.getData().add(new XYChart.Data<String, Double>("19/06", 15.0));
		 * series.getData().add(new XYChart.Data<String, Double>("20/06", 15.0));
		 * series.getData().add(new XYChart.Data<String, Double>("21/06", 15.0));
		 * series.getData().add(new XYChart.Data<String, Double>("22/06", 15.0));
		 * series.getData().add(new XYChart.Data<String, Double>("23/06", 15.0));
		 * series.getData().add(new XYChart.Data<String, Double>("24/06", 15.0));
		 * series.getData().add(new XYChart.Data<String, Double>("25/06", 15.0));
		 * series.getData().add(new XYChart.Data<String, Double>("26/06", 15.0));
		 * series.getData().add(new XYChart.Data<String, Double>("27/06", 15.0));
		 * series.getData().add(new XYChart.Data<String, Double>("28/06", 15.0)); if
		 * (currentMonth != Month.FEBRUARY) { series.getData().add(new
		 * XYChart.Data<String, Double>("17/06", 15.0)); series.getData().add(new
		 * XYChart.Data<String, Double>("17/06", 15.0)); } Calendar c =
		 * Calendar.getInstance(); int monthMaxDays =
		 * c.getActualMaximum(Calendar.DAY_OF_MONTH);
		 */

		dailyEarningsChart.getData().addAll(series);
		dailyEarningsChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent");
	}
}
