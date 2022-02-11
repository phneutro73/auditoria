package application;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
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
import models.ModelTicketTable;

public class ControllerSalesPage {

	double height;
	double width;
	int currentUser;

	public ControllerSalesPage(double height, double width, int currentUser) {
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
	private TableView<ModelTicketTable> shoppingTable;

	@FXML
	private TableColumn<ModelTicketTable, String> idItemShoppingTable;

	@FXML
	private TableColumn<ModelTicketTable, String> barCodeShoppingTable;

	@FXML
	private TableColumn<ModelTicketTable, String> nameShoppingTable;

	@FXML
	private TableColumn<ModelTicketTable, String> itemTypeShoppingTable;

	@FXML
	private TableColumn<ModelTicketTable, String> sizeShoppingTable;

	@FXML
	private TableColumn<ModelTicketTable, String> priceShoppingTable;

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

	@FXML
	void initialize() {
		btnDeleteItem.setDisable(true);
		isExpanded = false;
	}

	@FXML
	void accept(ActionEvent event) {

	}

	@FXML
	void addItem(ActionEvent event) {

	}

	@FXML
	void cancel(ActionEvent event) {

	}

	@FXML
	void deleteItem(MouseEvent event) {

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
		ControllerAddItemsPage control = new ControllerAddItemsPage(0, 0, 0);
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
		controller_administrator control = new controller_administrator(0, 0, 0);
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
		ControllerConsultationPage control = new ControllerConsultationPage(0, 0, 0);
		loader.setController(control);
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
		((Node) (event.getSource())).getScene().getWindow().hide();
	}

	@FXML
	void goToHistoricalSales(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/HistoricalDataSalesPage.fxml"));
		ControllerHistoricalDataSales control = new ControllerHistoricalDataSales();
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
	void onMouseEntered(MouseEvent event) {

	}

	@FXML
	void onMouseExited(MouseEvent event) {

	}

	@FXML
	void selectItem(MouseEvent event) {
		hideMenu(event);
	}
}
