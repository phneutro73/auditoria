package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import models.ModelHistoricalSalesDataTable;

public class ControllerHistoricalDataSales {

	@FXML
	private AnchorPane parent;

	@FXML
	private Label subtitle;

	@FXML
	private TableView<ModelHistoricalSalesDataTable> historicalSalesTable;

	@FXML
	private TableColumn<ModelHistoricalSalesDataTable, String> itemIdHistoricalSalesTable;

	@FXML
	private TableColumn<ModelHistoricalSalesDataTable, String> barCodeHistoricalSalesTable;

	@FXML
	private TableColumn<ModelHistoricalSalesDataTable, String> userHistoricalSalesTable;

	@FXML
	private TableColumn<ModelHistoricalSalesDataTable, String> shopHistoricalSalesTable;

	@FXML
	private TableColumn<ModelHistoricalSalesDataTable, String> priceHistoricalSalesTable;

	@FXML
	private TableColumn<ModelHistoricalSalesDataTable, String> dateHistoricalSalesTable;

	void initialize() {
		getData();
	}

	void getData() {

	}

}
