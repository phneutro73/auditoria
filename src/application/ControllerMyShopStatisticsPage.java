package application;

import java.awt.Color;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.ResourceBundle;

import db.StatisticsPageConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.StyleableObjectProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import models.CurrentUser;
import models.ModelWorkersTodayTable;

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
	private Label lblShopName;

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
	private Label caption;

	@FXML
	private LineChart<String, Double> dailyEarningsChart;

	@FXML
	private PieChart pieChartItemTypes;

	@FXML
	private TableView<ModelWorkersTodayTable> shopStatisticsTable;

	@FXML
	private TableColumn<ModelWorkersTodayTable, String> scheduleShopStatisticsTable;

	@FXML
	private TableColumn<ModelWorkersTodayTable, String> userShopStatisticsTable;

	@FXML
	void initialize() {

		StatisticsPageConnection statisticsDB = new StatisticsPageConnection();

		lblShopName.setText(currentUser.getShopName());
		getShopStatistics(statisticsDB);
		getLineChar(statisticsDB);
		getWorkersToday(statisticsDB);
		getPieChart(statisticsDB);

	}

	private void getShopStatistics(StatisticsPageConnection statisticsDB) {
		Hashtable<String, Object> userStatistics = statisticsDB.getShopStatistics(currentUser.getShopId());
		lblMonthEarnings.setText(userStatistics.get("lastMonthEarnings").toString());
		lblTotalEarnings.setText(userStatistics.get("totalEarnings").toString());
		lblMonthSales.setText(userStatistics.get("numSales").toString());
		lblTotalSales.setText(userStatistics.get("numLastMonthSales").toString());
		lblWorkersNum.setText(userStatistics.get("numWorkers").toString());
		lblItemsStock.setText(userStatistics.get("numItemsStock").toString());
		lblItemsSoldToday.setText(userStatistics.get("numItemsSoldToday").toString());
	}

	private void getLineChar(StatisticsPageConnection statisticsDB) {

		Calendar c = Calendar.getInstance();
		LocalDate currentDate = LocalDate.now();

		LocalDate firstDayMonth = LocalDate.now().withDayOfMonth(1);
		String strFirstDayMonth = firstDayMonth.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		LocalDate aux = firstDayMonth;
		LocalDate lastDayMonth = currentDate.withDayOfMonth(currentDate.getMonth().length(currentDate.isLeapYear()));
		String strLastDayMonth = lastDayMonth.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

		XYChart.Series<String, Double> series = new XYChart.Series<String, Double>();
		Hashtable<String, Object> data = statisticsDB.getShopLineChart(currentUser.getId(), strFirstDayMonth,
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

	private void getWorkersToday(StatisticsPageConnection statisticsDB) {

		ObservableList<ModelWorkersTodayTable> obList = statisticsDB.getWorkersTodayTable(currentUser.getShopId());

		scheduleShopStatisticsTable.setCellValueFactory(new PropertyValueFactory<>("userName"));
		userShopStatisticsTable.setCellValueFactory(new PropertyValueFactory<>("scheduleName"));

		shopStatisticsTable.setItems(obList);
	}

	private void getPieChart(StatisticsPageConnection statisticsDB) {

		Hashtable<String, Object> chartData = statisticsDB.getShopItemTypesPieChart(currentUser.getShopId());
		List<Integer> quantities = (List<Integer>) chartData.get("quantities");
		List<String> names = (List<String>) chartData.get("names");

		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
		Double sumOthers = 0.0;

		for (int i = 0; i < quantities.size(); i++) {
			if (i < 4) {
				pieChartData.add(new PieChart.Data(names.get(i), quantities.get(i)));
			} else {
				sumOthers = sumOthers + quantities.get(i);
			}
		}

		if (sumOthers != 0.0) {
			pieChartData.add(new PieChart.Data("Otros", sumOthers));
		}
		pieChartItemTypes.setData(pieChartData);

		caption.setTextFill(lblShopName.getTextFill());
		caption.setStyle("-fx-font: 20 arial;");
		// TODO: Mostrar porcetajes - esto no funciona
		for (final PieChart.Data data : pieChartItemTypes.getData()) {
			data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					System.out.println("click pie chart");
					caption.setTranslateX(e.getSceneX() - caption.getLayoutX());
					caption.setTranslateY(e.getSceneY() - caption.getLayoutY());
					caption.setText(String.valueOf(data.getPieValue() + "%"));
				}
			});
		}

	}
}
