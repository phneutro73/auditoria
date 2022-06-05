package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
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
	private LineChart<String, Double> dailyEarnignsChar;

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
		XYChart.Series<String, Double> series = new XYChart.Series<String, Double>();
		series.getData().add(new XYChart.Data<String, Double>("01/06", 0.0));
		series.getData().add(new XYChart.Data<String, Double>("02/06", 9.0));
		series.getData().add(new XYChart.Data<String, Double>("03/06", 25.0));
		series.getData().add(new XYChart.Data<String, Double>("04/06", 12.5));
		series.getData().add(new XYChart.Data<String, Double>("05/06", 14.4));
		series.getData().add(new XYChart.Data<String, Double>("06/06", 59.7));
		series.getData().add(new XYChart.Data<String, Double>("07/06", 12.7));
		series.getData().add(new XYChart.Data<String, Double>("08/06", 37.0));
		series.getData().add(new XYChart.Data<String, Double>("09/06", 3.4));
		series.getData().add(new XYChart.Data<String, Double>("10/06", 14.6));
		series.getData().add(new XYChart.Data<String, Double>("11/06", 69.0));
		series.getData().add(new XYChart.Data<String, Double>("12/06", 15.9));
		series.getData().add(new XYChart.Data<String, Double>("13/06", 10.0));
		series.getData().add(new XYChart.Data<String, Double>("14/06", 2.0));
		series.getData().add(new XYChart.Data<String, Double>("15/06", 12.0));
		series.getData().add(new XYChart.Data<String, Double>("16/06", 12.0));
		series.getData().add(new XYChart.Data<String, Double>("17/06", 15.0));

		dailyEarnignsChar.getData().addAll(series);
	}
}
