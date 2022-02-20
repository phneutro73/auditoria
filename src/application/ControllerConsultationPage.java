package application;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import db.AdministratorPageConnection;
import db.ConsultationPageConnection;
import db.SalesPageConnection;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import models.ModelItemTable;
import models.ModelUserTable;

public class ControllerConsultationPage {

	double height;
	double width;
	CurrentUser currentUser;

	public ControllerConsultationPage(double height, double width, CurrentUser currentUser) {
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
	private VBox vBoxButtons;

	@FXML
	private JFXTextField txtItemSearch;

	@FXML
	private TableView<ModelItemTable> itemTable;

	@FXML
	private TableColumn<ModelItemTable, String> idItemTable;

	@FXML
	private TableColumn<ModelItemTable, String> cbItemTable;

	@FXML
	private TableColumn<ModelItemTable, String> nameItemTable;

	@FXML
	private TableColumn<ModelItemTable, String> typeItemTable;

	@FXML
	private TableColumn<ModelItemTable, String> sizeItemTable;

	@FXML
	private TableColumn<ModelItemTable, String> quantityItemTable;

	@FXML
	private TableColumn<ModelItemTable, String> inShopItemTable;

	@FXML
	private TableColumn<ModelItemTable, String> reservationItemTable;

	@FXML
	private TableColumn<ModelItemTable, String> priceItemTable;

	@FXML
	private JFXButton btnEditItem;

	@FXML
	private JFXButton btnDeleteItem;

	@FXML
	private JFXButton btnDetailsItem;

	@FXML
	private JFXButton btnReservations;

	@FXML
	private AnchorPane logOutButton;

	boolean isExpanded;
	int idItemSelected;

	@FXML
	void initialize() {
		
		if (currentUser.getRoleId() != 2) {
			btnAdministrator.setVisible(false);
		}

		ConsultationPageConnection consultDB = new ConsultationPageConnection();

		getItems(consultDB);

		isExpanded = false;
		txtItemSearch.setFocusTraversable(false);
		itemTable.setFocusTraversable(false);
		btnReservations.setFocusTraversable(false);

		btnEditItem.setDisable(true);
		btnDeleteItem.setDisable(true);
		btnDetailsItem.setDisable(true);

	}

	@FXML
	void deleteItem(MouseEvent event) throws IOException {

		String[] params = { String.valueOf(idItemSelected) };
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/YesNoAlertDialog.fxml"));
		ControllerYesNoAlertDialog control = new ControllerYesNoAlertDialog(0, 0, "Atención",
				"Esta acción es permanente, no se podrá deshacer. Preste atención y revise los datos.",
				"¿Está seguro de que desea eliminar el artículo con el siguiente ID: " + String.valueOf(idItemSelected)
						+ "?",
				"SÍ", "No", "consultDeleteItem", params);
		loader.setController(control);
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setScene(new Scene(root));
		stage.showAndWait();
		initialize();

	}

	@FXML
	void detailsItem(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/ReservationPage.fxml"));
		ControllerReservationPage control = new ControllerReservationPage(0, 0, idItemSelected, currentUser);
		loader.setController(control);
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
		((Node) (event.getSource())).getScene().getWindow().hide();
	}

	@FXML
	void editItem(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/EditItemPage.fxml"));
		ControllerEditItem control = new ControllerEditItem(idItemSelected, currentUser);
		loader.setController(control);
		Parent root = loader.load();

		Stage stage = new Stage();
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
	void goToAddItems(MouseEvent event) throws IOException {
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
	void goToSalesPage(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/SalesPage.fxml"));
		ControllerSalesPage control = new ControllerSalesPage(0, 0, currentUser);
		loader.setController(control);
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
		((Node) (event.getSource())).getScene().getWindow().hide();
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
	void itemSelected(MouseEvent event) {
		try {
			hideMenu(event);
			idItemSelected = itemTable.getSelectionModel().getSelectedItem().getId();
			btnEditItem.setDisable(false);
			btnEditItem.setFocusTraversable(false);
			btnDeleteItem.setDisable(false);
			btnDeleteItem.setFocusTraversable(false);
			btnDetailsItem.setDisable(false);
			btnDetailsItem.setFocusTraversable(false);
		} catch (Exception e) {
			btnEditItem.setDisable(true);
			btnDeleteItem.setDisable(true);
			btnDetailsItem.setDisable(true);
		}
	}

	@FXML
	void seeAllReservations(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/SeeAllReservations.fxml"));
		ControllerSeeAllReservations control = new ControllerSeeAllReservations(currentUser);
		loader.setController(control);
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.showAndWait();
		initialize();
	}

	@FXML
	void logOut(MouseEvent event) throws IOException {
		SalesPageConnection salesDB = new SalesPageConnection();

		boolean unfinishedSales = salesDB.unfinishedTicket(currentUser.getId());

		if (unfinishedSales) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
			ControllerAlertDialog control = new ControllerAlertDialog(120, 210, "Error",
					"Tiene una venta en curso. Es necesario que la finalice o cancele.");
			loader.setController(control);
			Parent root = loader.load();

			Stage stage = new Stage();
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setScene(new Scene(root));
			stage.show();
		} else {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/login.fxml"));
			controller_login control = new controller_login();
			loader.setController(control);
			Parent root = loader.load();

			Stage stage = new Stage();
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setScene(new Scene(root));
			stage.show();
			((Node) (event.getSource())).getScene().getWindow().hide();
		}
	}

	void getItems(ConsultationPageConnection consultDB) {

		ObservableList<ModelItemTable> obList = consultDB.getItemsTable(currentUser.getShopId());

		idItemTable.setCellValueFactory(new PropertyValueFactory<>("id"));
		cbItemTable.setCellValueFactory(new PropertyValueFactory<>("barCode"));
		nameItemTable.setCellValueFactory(new PropertyValueFactory<>("itemName"));
		typeItemTable.setCellValueFactory(new PropertyValueFactory<>("itemType"));
		sizeItemTable.setCellValueFactory(new PropertyValueFactory<>("itemSize"));
		quantityItemTable.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		inShopItemTable.setCellValueFactory(new PropertyValueFactory<>("inShop"));
		reservationItemTable.setCellValueFactory(new PropertyValueFactory<>("reservations"));
		priceItemTable.setCellValueFactory(new PropertyValueFactory<>("price"));

		itemTable.setItems(obList);

		FilteredList<ModelItemTable> filteredItemData = new FilteredList<>(obList, b -> true);
		txtItemSearch.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredItemData.setPredicate(ItemSearchModel -> {
				if (newValue.isEmpty() || newValue == null) {
					return true;
				}
				String searcItemKeyword = newValue.toLowerCase();

				if (ItemSearchModel.getStrId() != null
						&& ItemSearchModel.getStrId().toLowerCase().indexOf(searcItemKeyword) > -1) {
					return true;
				} else if (ItemSearchModel.getBarCode() != null
						&& ItemSearchModel.getBarCode().toLowerCase().indexOf(searcItemKeyword) > -1) {
					return true;
				} else if (ItemSearchModel.getItemName() != null
						&& ItemSearchModel.getItemName().toLowerCase().indexOf(searcItemKeyword) > -1) {
					return true;
				} else if (ItemSearchModel.getItemType() != null
						&& ItemSearchModel.getItemType().toLowerCase().indexOf(searcItemKeyword) > -1) {
					return true;
				} else if (ItemSearchModel.getItemSize() != null
						&& ItemSearchModel.getItemSize().toLowerCase().indexOf(searcItemKeyword) > -1) {
					return true;
				} else if (ItemSearchModel.getStrPrice() != null
						&& ItemSearchModel.getStrPrice().toLowerCase().indexOf(searcItemKeyword) > -1) {
					return true;
				} else {
					return false;
				}
			});
		});

		SortedList<ModelItemTable> sortedItemData = new SortedList<>(filteredItemData);
		sortedItemData.comparatorProperty().bind(itemTable.comparatorProperty());
		itemTable.setItems(sortedItemData);

	}

}
