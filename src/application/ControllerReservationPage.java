package application;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.CurrentUser;
import models.ModelReservationTable;

public class ControllerReservationPage {

	double height;
	double width;
	int barCode;
	CurrentUser currentUser;

	public ControllerReservationPage(double height, double width, int barCode, CurrentUser currentUser) {
		this.height = height;
		this.width = width;
		this.barCode = barCode;
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
	private TableView<ModelReservationTable> itemTable;

	@FXML
	private TableColumn<ModelReservationTable, String> idItemTable;

	@FXML
	private TableColumn<ModelReservationTable, String> cbItemTable;

	@FXML
	private TableColumn<ModelReservationTable, String> nameItemTable;

	@FXML
	private TableColumn<ModelReservationTable, String> typeItemTable;

	@FXML
	private TableColumn<ModelReservationTable, String> sizeItemTable;

	@FXML
	private TableColumn<ModelReservationTable, String> quantityItemTable;

	@FXML
	private TableColumn<ModelReservationTable, String> inShopItemTable;

	@FXML
	private TableColumn<ModelReservationTable, String> reservationItemTable;

	@FXML
	private TableColumn<ModelReservationTable, String> priceItemTable;

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
		isExpanded = false;
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
	void reservation(ActionEvent event) {

	}

	boolean checkAllFields() {
		if ((!fieldDni.getText().isEmpty() && fieldDni.getText() != null && fieldDni.getText() != "")
				&& (!fieldEmail.getText().isEmpty() && fieldEmail.getText() != null && fieldEmail.getText() != "")
				&& (fieldShopName.getValue().toString() != "")) {
			return true;
		} else {
			return false;
		}
	}

}
