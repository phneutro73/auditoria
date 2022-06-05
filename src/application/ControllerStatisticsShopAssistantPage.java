package application;

import com.jfoenix.controls.JFXButton;

import db.SalesPageConnection;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.CurrentUser;

public class ControllerStatisticsShopAssistantPage {

	double height;
	double width;
	CurrentUser currentUser;

	public ControllerStatisticsShopAssistantPage(double height, double width, CurrentUser currentUser) {
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
	private AnchorPane logOutButton;

	@FXML
	private JFXButton btnMyStatistics;

	@FXML
	private JFXButton btnMyShopStatistics;

	@FXML
	private JFXButton btnMyInformation;

	boolean isExpanded = false;

	@FXML
	void expandMenu(MouseEvent event) {
		if (isExpanded) {
			drawer.setPrefWidth(60);
			isExpanded = false;

			// vBoxButtons.setMinWidth(108);
			// vBoxButtons.setMaxWidth(108);
			// vBoxButtons.prefWidth(108);
		} else {
			drawer.setPrefWidth(190);
			isExpanded = true;

			// vBoxButtons.setMinWidth(108);
			// vBoxButtons.setMaxWidth(108);
			// vBoxButtons.prefWidth(108);
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
	void goToMyInformationPage(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/MyInformationPage.fxml"));
		ControllerMyInformationPage control = new ControllerMyInformationPage(0, 0, currentUser);
		loader.setController(control);
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.showAndWait();
		initialize();
	}

	@FXML
	void goToMyShopStatisticsPage(ActionEvent event) {
		// TODO
	}

	@FXML
	void goToMyStatisticsPage(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/MyStatisticsPage.fxml"));
		ControllerMyStatisticsPage control = new ControllerMyStatisticsPage();
		loader.setController(control);
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.showAndWait();
		initialize();
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
	void hideMenu(MouseEvent event) {
		if (isExpanded) {
			drawer.setPrefWidth(60);
			isExpanded = false;

			// vBoxButtons.setMinWidth(108);
			// vBoxButtons.setMaxWidth(108);
			// vBoxButtons.prefWidth(108);

		}
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

	@FXML
	void initialize() {
		btnMyStatistics.setFocusTraversable(false);
		btnMyShopStatistics.setFocusTraversable(false);
		btnMyInformation.setFocusTraversable(false);
	}
}
