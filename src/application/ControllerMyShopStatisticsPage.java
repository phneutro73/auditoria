package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
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
	private Label monthSales;

	@FXML
	private Label totalSales;

	@FXML
	private Label monthEarnings;

	@FXML
	private Label totalEarnings;

	@FXML
	private Label workersNum;

	@FXML
	private Label itemsSold;

	@FXML
	private Label itemsStock;

	@FXML
	private Label itemsSoldToday;

	@FXML
	private LineChart<String, Double> dailyEarnignsChar;

	// TODO: Hacer el modelo de la tabla
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
