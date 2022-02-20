package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import db.AddItemsPageConnection;
import db.SalesPageConnection;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.CurrentUser;
import models.ModelAllReservationsTable;
import models.ModelTicketTable;

public class ControllerSalesPage {

	double height;
	double width;
	CurrentUser currentUser;

	public ControllerSalesPage(double height, double width, CurrentUser currentUser) {
		this.height = height;
		this.width = width;
		this.currentUser = currentUser;
	}

	@FXML
	private AnchorPane parent;

	@FXML
	private VBox drawer;

	@FXML
	private HBox btnMenu;

	@FXML
	private HBox btnSales;

	@FXML
	private HBox btnConsultation;

	@FXML
	private HBox btnAddItem;

	@FXML
	private HBox btnStatistics;

	@FXML
	private HBox btnAdministrator;

	@FXML
	private VBox body;

	@FXML
	private JFXButton historicalSales;

	@FXML
	private JFXTextField fieldBarCode;

	@FXML
	private JFXTextField fieldQuantity;

	@FXML
	private JFXTextField fieldName;

	@FXML
	private JFXTextField fieldPrice;

	@FXML
	private JFXTextField fieldSize;

	@FXML
	private JFXComboBox<String> fieldItemType;

	@FXML
	private JFXButton btnAcceptSearch;

	@FXML
	private TableView<ModelTicketTable> ticketTable;

	@FXML
	private TableColumn<ModelTicketTable, String> idItemTicketTable;

	@FXML
	private TableColumn<ModelTicketTable, String> barCodeTicketTable;

	@FXML
	private TableColumn<ModelTicketTable, String> nameTicketTable;

	@FXML
	private TableColumn<ModelTicketTable, String> itemTypeTicketTable;

	@FXML
	private TableColumn<ModelTicketTable, String> sizeTicketTable;

	@FXML
	private TableColumn<ModelTicketTable, String> priceTicketTable;

	@FXML
	private VBox vBoxButtons;

	@FXML
	private JFXButton btnDeleteItem;

	@FXML
	private Label lblTotal;

	@FXML
	private JFXButton btnAccept;

	@FXML
	private JFXButton btnCancel;

	boolean isExpanded;
	int itemIdAdd = -1;
	int itemIdSelected = -1;
	int numItems = 0;

	@FXML
	void initialize() {

		SalesPageConnection salesDB = new SalesPageConnection();
		btnDeleteItem.setDisable(true);
		isExpanded = false;
		getTicket(salesDB);
		getItemTypes(salesDB);
		listenerBarCodeField(salesDB);

	}

	@FXML
	void accept(ActionEvent event) throws IOException {

		if (ticketTable.getItems().size() > 0) {
			SalesPageConnection salesDB = new SalesPageConnection();

			boolean isError = false;
			for (int i = 0; i < ticketTable.getItems().size(); i++) {
				int id = ticketTable.getItems().get(i).getItemId();
				boolean success = salesDB.makeASale(id, currentUser.getId(), 1, currentUser.getShopId());

				if (!success) {
					isError = true;
				}
			}

			if (isError) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
				ControllerAlertDialog control = new ControllerAlertDialog(120, 210, "Error",
						"Se ha producido un error al guardar los datos de la venta.");
				loader.setController(control);
				Parent root = loader.load();

				Stage stage = new Stage();
				stage.initStyle(StageStyle.UNDECORATED);
				stage.setScene(new Scene(root));
				stage.show();

				initialize();

			} else {

				FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
				ControllerAlertDialog control = new ControllerAlertDialog(120, 210, "Error",
						"Venta realizada correctamente.");
				loader.setController(control);
				Parent root = loader.load();

				Stage stage = new Stage();
				stage.initStyle(StageStyle.UNDECORATED);
				stage.setScene(new Scene(root));
				stage.show();

				initialize();

			}

		}

	}

	@FXML
	void addItem(ActionEvent event) throws IOException {

		if (itemIdAdd != -1) {

			SalesPageConnection salesDB = new SalesPageConnection();
			boolean isInShop = salesDB.checkIsInShop(itemIdSelected, currentUser.getShopId());

			if (isInShop) {

				boolean success = salesDB.addItemToTicket(itemIdAdd, currentUser.getId(), 1);

				if (success) {

					FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
					ControllerAlertDialog control = new ControllerAlertDialog(120, 210, "Error",
							"El artículo se ha añadido correctamente.");
					loader.setController(control);
					Parent root = loader.load();

					Stage stage = new Stage();
					stage.initStyle(StageStyle.UNDECORATED);
					stage.setScene(new Scene(root));
					stage.show();

					numItems += 1;

					initialize();

				} else {

					FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
					ControllerAlertDialog control = new ControllerAlertDialog(120, 210, "Error",
							"Se ha producido un error. Por favor, inténtelo más tarde.");
					loader.setController(control);
					Parent root = loader.load();

					Stage stage = new Stage();
					stage.initStyle(StageStyle.UNDECORATED);
					stage.setScene(new Scene(root));
					stage.show();

					initialize();

				}
			} else {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
				ControllerAlertDialog control = new ControllerAlertDialog(120, 210, "Error",
						"No hay stock del artículo en tienda. Revise los datos.");
				loader.setController(control);
				Parent root = loader.load();

				Stage stage = new Stage();
				stage.initStyle(StageStyle.UNDECORATED);
				stage.setScene(new Scene(root));
				stage.show();

				initialize();
			}

		}
	}

	@FXML
	void cancel(ActionEvent event) throws IOException {

		String[] params = { String.valueOf(currentUser.getId()) };
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/YesNoAlertDialog.fxml"));
		ControllerYesNoAlertDialog control = new ControllerYesNoAlertDialog(0, 0, "Atención",
				"Esta acción es permanente, no se podrá deshacer. Preste atención y revise los datos.",
				"¿Está seguro de que desea cancelar esta venta?", "SÍ", "No", "ticketDelete", params);
		loader.setController(control);
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setScene(new Scene(root));
		stage.showAndWait();
		numItems = 0;
		lblTotal.setText("€");
		initialize();

	}

	@FXML
	void deleteItem(MouseEvent event) throws IOException {

		String[] params = { String.valueOf(itemIdSelected), String.valueOf(currentUser.getId()) };
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/YesNoAlertDialog.fxml"));
		ControllerYesNoAlertDialog control = new ControllerYesNoAlertDialog(0, 0, "Atención",
				"Esta acción es permanente, no se podrá deshacer. Preste atención y revise los datos.",
				"¿Está seguro de que desea eliminar el artículo con el siguiente ID: " + String.valueOf(itemIdSelected)
						+ " del ticket?",
				"SÍ", "No", "ticketDeleteItem", params);
		loader.setController(control);
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setScene(new Scene(root));
		stage.showAndWait();
		initialize();

	}

	@FXML
	void expandMenu(MouseEvent event) {
		if (isExpanded) {
			drawer.setPrefWidth(60);
			isExpanded = false;

			vBoxButtons.setMinWidth(108);
			vBoxButtons.setMaxWidth(108);
			vBoxButtons.prefWidth(108);
		} else {
			drawer.setPrefWidth(190);
			isExpanded = true;

			vBoxButtons.setMinWidth(108);
			vBoxButtons.setMaxWidth(108);
			vBoxButtons.prefWidth(108);
		}
	}

	@FXML
	void goToAddItemsPage(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AddItemsPage.fxml"));
		ControllerAddItemsPage control = new ControllerAddItemsPage(0, 0, currentUser);
		loader.setController(control);
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
		((Node) (event.getSource())).getScene().getWindow().hide();
	}

	@FXML
	void goToAdministratorPage(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/administrator_page.fxml"));
		controller_administrator control = new controller_administrator(0, 0, currentUser);
		loader.setController(control);
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
		((Node) (event.getSource())).getScene().getWindow().hide();
	}

	@FXML
	void goToConsultationPage(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/ConsultationPage.fxml"));
		ControllerConsultationPage control = new ControllerConsultationPage(0, 0, currentUser);
		loader.setController(control);
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
		((Node) (event.getSource())).getScene().getWindow().hide();
	}

	@FXML
	void goToHistoricalSales(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/HistoricalSalesDataPage.fxml"));
		ControllerHistoricalSalesData control = new ControllerHistoricalSalesData();
		loader.setController(control);
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
	}

	@FXML
	void hideMenu(MouseEvent event) {
		if (isExpanded) {
			drawer.setPrefWidth(60);
			isExpanded = false;

			vBoxButtons.setMinWidth(108);
			vBoxButtons.setMaxWidth(108);
			vBoxButtons.prefWidth(108);

		}
	}

	@FXML
	void selectItem(MouseEvent event) {
		try {
			hideMenu(event);
			itemIdSelected = ticketTable.getSelectionModel().getSelectedItem().getItemId();
			btnDeleteItem.setDisable(false);
			btnDeleteItem.setFocusTraversable(false);
		} catch (Exception e) {
			itemIdSelected = -1;
			btnDeleteItem.setDisable(true);
			btnDeleteItem.setFocusTraversable(true);
		}
	}

	void getTicket(SalesPageConnection salesDB) {

		ObservableList<ModelTicketTable> obList = salesDB.getTicketTable(currentUser.getId());

		idItemTicketTable.setCellValueFactory(new PropertyValueFactory<>("itemId"));
		barCodeTicketTable.setCellValueFactory(new PropertyValueFactory<>("itemBarCode"));
		nameTicketTable.setCellValueFactory(new PropertyValueFactory<>("itemName"));
		itemTypeTicketTable.setCellValueFactory(new PropertyValueFactory<>("itemType"));
		sizeTicketTable.setCellValueFactory(new PropertyValueFactory<>("size"));
		priceTicketTable.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));

		ticketTable.setItems(obList);

		if (ticketTable.getItems().size() != 0) {
			if (lblTotal.getText().equals("€")) {
				lblTotal.setText(Double.toString(obList.get(0).getItemPrice()) + " €");
			} else {
				double price = Double.parseDouble(lblTotal.getText().substring(0, lblTotal.getText().length() - 2));
				double totalPrice;
				if (numItems > ticketTable.getItems().size()) {
					totalPrice = price - obList.get(0).getItemPrice();
				} else {
					totalPrice = price + obList.get(0).getItemPrice();
				}
				lblTotal.setText(Double.toString(totalPrice) + " €");
			}
		} else {
			lblTotal.setText("€");
		}

	}

	void getItemTypes(SalesPageConnection salesDB) {
		List<String> itemTypes = salesDB.listItemTypes();
		fieldItemType.getItems().removeAll(fieldItemType.getItems());
		fieldItemType.getItems().addAll(itemTypes);
		fieldItemType.getSelectionModel().select("-");
	}

	void listenerBarCodeField(SalesPageConnection salesDB) {

		fieldBarCode.textProperty().addListener((observable, oldValue, newValue) -> {
			Hashtable<String, Object> item = salesDB.getItemWithBarCode(newValue);

			if (!item.isEmpty()) {
				fieldName.setText((String) item.get("name"));
				fieldItemType.getSelectionModel().select((String) item.get("type"));
				fieldSize.setText((String) item.get("size"));
				fieldPrice.setText((String) item.get("price").toString());
				itemIdAdd = Integer.parseInt(item.get("id").toString());
			} else {
				fieldName.setText("");
				fieldItemType.getSelectionModel().select("-");
				fieldSize.setText("");
				fieldPrice.setText("");
				itemIdAdd = -1;
			}
		});

	}
}
