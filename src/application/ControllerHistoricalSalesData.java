package application;

import db.SalesPageConnection;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import models.CurrentUser;
import models.ModelHistoricalSalesDataTable;
import models.ModelTicketTable;

public class ControllerHistoricalSalesData {

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

	@FXML
	void initialize() {
		getData();
		historicalSalesTable.setFocusTraversable(false);
	}

	void getData() {

		SalesPageConnection salesDB = new SalesPageConnection();

		ObservableList<ModelHistoricalSalesDataTable> obList = salesDB.getHistoricalSalesTable();

		itemIdHistoricalSalesTable.setCellValueFactory(new PropertyValueFactory<>("itemId"));
		barCodeHistoricalSalesTable.setCellValueFactory(new PropertyValueFactory<>("itemBarCode"));
		userHistoricalSalesTable.setCellValueFactory(new PropertyValueFactory<>("user"));
		shopHistoricalSalesTable.setCellValueFactory(new PropertyValueFactory<>("shop"));
		priceHistoricalSalesTable.setCellValueFactory(new PropertyValueFactory<>("price"));
		dateHistoricalSalesTable.setCellValueFactory(new PropertyValueFactory<>("date"));

		historicalSalesTable.setItems(obList);

	}

}
