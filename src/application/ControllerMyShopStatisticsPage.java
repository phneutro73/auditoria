package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import models.CurrentUser;

public class ControllerMyShopStatisticsPage {

	double height;
	double width;
	CurrentUser currentUser;

	public ControllerMyShopStatisticsPage(double height, double width, CurrentUser currentUser) {
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
	private Label lblMonthSales;

	@FXML
	private Label lblTotalSales;

	@FXML
	private Label lblMonthEarnings;

	@FXML
	private Label lblTotalEarnings;

	@FXML
	private Label lblWorkersNum;

	@FXML
	private Label lblItemsStock;

	@FXML
	private Label lblItemsSoldToday;

	@FXML
	private LineChart<String, Double> dailyEarnignsChart;

	@FXML
	private PieChart pieChartItemTypes;

	// TODO: Modelo de la tabla
	@FXML
	private TableView<?> shopStatisticsTable;

	@FXML
	private TableColumn<?, ?> scheduleShopStatisticsTable;

	@FXML
	private TableColumn<?, ?> userShopStatisticsTable;

	@FXML
	void initialize() {

	}
}
