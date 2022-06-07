package application;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.CurrentUser;

public class ControllerStatisticsAdministratorPage {

	double height;
	double width;
	CurrentUser currentUser;

	public ControllerStatisticsAdministratorPage(double height, double width, CurrentUser currentUser) {
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
	private JFXButton btnWorkersStatistics;

	@FXML
	private JFXButton btnSmallPandaStatistics;

	@FXML
	private JFXButton btnMyInformation;

	@FXML
	void expandMenu(MouseEvent event) {

	}

	@FXML
	void goToAddItemsPage(MouseEvent event) {

	}

	@FXML
	void goToAdministratorPage(MouseEvent event) {

	}

	@FXML
	void goToConsultationPage(MouseEvent event) {

	}

	@FXML
	void goToMyInformationPage(ActionEvent event) {

	}

	@FXML
	void goToMyShopStatisticsPage(ActionEvent event) {

	}

	@FXML
	void goToMyStatisticsPage(ActionEvent event) {

	}

	@FXML
	void goToSalesPage(MouseEvent event) {

	}

	@FXML
	void goToSmallPandaStatisticsPage(ActionEvent event) {

	}

	@FXML
	void goToWorkersStatisticsPage(ActionEvent event) {

	}

	@FXML
	void hideMenu(MouseEvent event) {

	}

	@FXML
	void logOut(MouseEvent event) {

	}

	@FXML
	void initialize() {

	}
}
