package application;

import java.io.IOException;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import db.ConsultationPageConnection;
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
import models.ModelItemTable;
import models.ModelReservationTable;
import models.ModelScheduleTable;

public class ControllerReservationPage {

	double height;
	double width;
	int itemId;
	CurrentUser currentUser;

	public ControllerReservationPage(double height, double width, int itemId, CurrentUser currentUser) {
		this.height = height;
		this.width = width;
		this.itemId = itemId;
		this.currentUser = currentUser;
	}

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
	private Label subtitle;

	@FXML
	private AnchorPane btnBack;

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
	private JFXTextField fieldEmail;

	@FXML
	private JFXTextField fieldDni;

	@FXML
	private JFXComboBox<String> fieldShopName;

	@FXML
	private JFXCheckBox chkPrivacy;

	@FXML
	private JFXButton btnReservation;

	boolean isExpanded;

	@FXML
	void initialize() {
		
		ConsultationPageConnection consultDB = new ConsultationPageConnection();
		isExpanded = false;
		getItemTable(consultDB);
		
		List<String> tiendas = consultDB.listShopsWithItem(itemId);
		fieldShopName.getItems().removeAll(fieldShopName.getItems());
		fieldShopName.getItems().addAll(tiendas);
		fieldShopName.getSelectionModel().select("-");	
		
	}

	@FXML
	void expandMenu(MouseEvent event) {
		if (isExpanded) {
			drawer.setPrefWidth(60);
			isExpanded = false;
		} else {
			drawer.setPrefWidth(190);
			isExpanded = true;
		}
	}

	@FXML
	void goBack(MouseEvent event) throws IOException {
		goToConsultationPage(event);
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
		}
	}

	@FXML
	void itemSelected(MouseEvent event) {
		hideMenu(event);
	}

	@FXML
	void reservation(ActionEvent event) throws IOException {
		
		Object reservas = reservationItemTable.getCellData(0);
		Object cant = quantityItemTable.getCellData(0);
		
		if (Integer.parseInt(reservas.toString()) < Integer.parseInt(cant.toString()) ) {
			if(checkAllFields()) {
				if(chkPrivacy.isSelected()) {
					ConsultationPageConnection consultDB = new ConsultationPageConnection();
					boolean success = consultDB.createReservation(fieldShopName.getSelectionModel().getSelectedItem(), itemId, fieldDni.getText(), fieldEmail.getText());
					
					if (success) {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
						ControllerAlertDialog control = new ControllerAlertDialog(120, 210, "Guardado correcto",
								"La reserva se ha realizado correctamente.");
						loader.setController(control);
						Parent root = loader.load();

						Stage stage = new Stage();
						stage.initStyle(StageStyle.UNDECORATED);
						stage.setScene(new Scene(root));
						stage.show();
					} else {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
						ControllerAlertDialog control = new ControllerAlertDialog(140, 210, "Error",
								"Se ha producido un error. Por favor, inténtelo de nuevo.");
						loader.setController(control);
						Parent root = loader.load();

						Stage stage = new Stage();
						stage.initStyle(StageStyle.UNDECORATED);
						stage.setScene(new Scene(root));
						stage.show();
					}
				} else {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
					ControllerAlertDialog control = new ControllerAlertDialog(120, 210, "Error",
							"Es necesario aceptar los términos y condiciones de privacidad.");
					loader.setController(control);
					Parent root = loader.load();

					Stage stage = new Stage();
					stage.initStyle(StageStyle.UNDECORATED);
					stage.setScene(new Scene(root));
					stage.show();
				}
			} else {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
				ControllerAlertDialog control = new ControllerAlertDialog(120, 210, "Error",
						"Es necesario que rellene todos los campos.");
				loader.setController(control);
				Parent root = loader.load();

				Stage stage = new Stage();
				stage.initStyle(StageStyle.UNDECORATED);
				stage.setScene(new Scene(root));
				stage.show();
			}
		} else {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
			ControllerAlertDialog control = new ControllerAlertDialog(140, 230, "Error",
					"Todos los artículos están reservados, tendrá que dejar que pase el tiempo de espera (20 mins).");
			loader.setController(control);
			Parent root = loader.load();

			Stage stage = new Stage();
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setScene(new Scene(root));
			stage.show();
		}
	}

	boolean checkAllFields() {
		if ((!fieldDni.getText().isEmpty() && fieldDni.getText() != null && fieldDni.getText() != "")
				&& (!fieldEmail.getText().isEmpty() && fieldEmail.getText() != null && fieldEmail.getText() != "")
				&& (fieldShopName.getValue().toString() != "-")) {
			return true;
		} else {
			return false;
		}
	}
	
	void getItemTable(ConsultationPageConnection consultDB) {
		
		ObservableList<ModelItemTable> obList = consultDB.getItemDetailTable(itemId, currentUser.getShopId());
		
		idItemTable.setCellValueFactory(new PropertyValueFactory<ModelItemTable, String>("id"));
		cbItemTable.setCellValueFactory(new PropertyValueFactory<ModelItemTable, String>("barCode"));
		nameItemTable.setCellValueFactory(new PropertyValueFactory<ModelItemTable, String>("itemName"));
		typeItemTable.setCellValueFactory(new PropertyValueFactory<ModelItemTable, String>("itemType"));
		sizeItemTable.setCellValueFactory(new PropertyValueFactory<ModelItemTable, String>("itemSize"));
		quantityItemTable.setCellValueFactory(new PropertyValueFactory<ModelItemTable, String>("quantity"));
		inShopItemTable.setCellValueFactory(new PropertyValueFactory<ModelItemTable, String>("inShop"));
		reservationItemTable.setCellValueFactory(new PropertyValueFactory<ModelItemTable, String>("reservations"));
		priceItemTable.setCellValueFactory(new PropertyValueFactory<ModelItemTable, String>("price"));
		
		itemTable.setItems(obList);
		
	}

}
