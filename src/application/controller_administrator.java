package application;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.Hashtable;
import java.util.List;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.ModelRoleTable;
import models.ModelScheduleTable;
import models.ModelUserTable;
import db.AdministratorPageConnection;

public class controller_administrator {

	double height;
	double width;
	int currentUser;

	public controller_administrator(double height, double width, int currentUser) {
		this.height = height;
		this.width = width;
		this.currentUser = currentUser;
	}

	@FXML
	private AnchorPane parent;

	@FXML
	private VBox drawer;

	// Menu buttons
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

	// Tab usuario
	@FXML
	private Tab tabAddNewUser;

	@FXML
	private AnchorPane tabAddUser;

	@FXML
	private JFXTextField txtUserSearch;

	@FXML
	private TableView<ModelUserTable> userTable;

	@FXML
	private TableColumn<ModelUserTable, String> idUserTable;

	@FXML
	private TableColumn<ModelUserTable, String> nameUserTable;

	@FXML
	private TableColumn<ModelUserTable, String> surnameUserTable;

	@FXML
	private TableColumn<ModelUserTable, String> dniUserTable;

	@FXML
	private TableColumn<ModelUserTable, String> dateUserTable;

	@FXML
	private TableColumn<ModelUserTable, String> roleUserTable;

	@FXML
	private JFXButton btnAddUser;

	@FXML
	private JFXButton btnEditUser;

	@FXML
	private JFXButton btnDeleteUser;

	// tab schedules
	@FXML
	private TableView<ModelScheduleTable> scheduleTable;

	@FXML
	private TableColumn<ModelScheduleTable, String> idScheduleTable;

	@FXML
	private TableColumn<ModelScheduleTable, String> nameScheduleTable;

	@FXML
	private TableColumn<ModelScheduleTable, String> checkInScheduleTable;

	@FXML
	private TableColumn<ModelScheduleTable, String> checkOutScheduleTable;

	@FXML
	private JFXButton btnAddSchedule;

	@FXML
	private JFXButton btnEditSchedule;

	@FXML
	private JFXButton btnDeleteSchedule;

	// tab role
	@FXML
	private TableView<ModelRoleTable> roleTable;

	@FXML
	private TableColumn<ModelRoleTable, String> idRoleTable;

	@FXML
	private TableColumn<ModelRoleTable, String> nameRoleTable;

	@FXML
	private TableColumn<ModelRoleTable, String> numUsersRoleTable;

	@FXML
	private JFXButton btnAddRole;

	@FXML
	private JFXButton btnEditRole;

	@FXML
	private JFXButton btnDeleteRole;

	boolean isExpanded = false;
	int idUserSelected;
	int idScheduleSelected;
	int idRoleSelected;

	@FXML
	void initialize() {

		try {

			AdministratorPageConnection adminDB = new AdministratorPageConnection();

			getTableActiveUsers(adminDB);
			getTableSchedules(adminDB);
			getTableRoles(adminDB);

			btnEditUser.setDisable(true);
			btnDeleteUser.setDisable(true);
			btnEditSchedule.setDisable(true);
			btnDeleteSchedule.setDisable(true);
			btnEditRole.setDisable(true);
			btnDeleteRole.setDisable(true);

		} catch (Exception e) {
			System.out.println("ERROR: controller_administrator.java - initialize() - " + e.toString() + "\n");
			e.printStackTrace();
		}

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
	void addUser(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AddNewUserPage.fxml"));
		ControllerAddNewUser control = new ControllerAddNewUser();
		loader.setController(control);
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.showAndWait();
		initialize();

	}

	@FXML
	void editUser(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AddNewUserPage.fxml"));
		ControllerEditUser control = new ControllerEditUser(600.0, 900.0, idUserSelected);
		loader.setController(control);
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.showAndWait();
		initialize();

	}

	@FXML
	void deleteUser(ActionEvent event) throws IOException {

		AdministratorPageConnection adminDB = new AdministratorPageConnection();

		String[] params = { String.valueOf(idUserSelected) };
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/YesNoAlertDialog.fxml"));
		ControllerYesNoAlertDialog control = new ControllerYesNoAlertDialog(0, 0, "Atención",
				"Esta acción es permanente, no se podrá deshacer. Preste atención y revise los datos.",
				"¿Está seguro de que desea eliminar al usuario con el siguiente ID: " + String.valueOf(idUserSelected)
						+ "?",
				"SÍ", "No", "adminDeleteUser", params);
		loader.setController(control);
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setScene(new Scene(root));
		stage.showAndWait();
		initialize();

	}

	@FXML
	void addSchedule(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AddNewSchedulePage.fxml"));
		ControllerAddNewSchedule control = new ControllerAddNewSchedule();
		loader.setController(control);
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.showAndWait();
		initialize();
	}

	@FXML
	void editSchedule(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AddNewSchedulePage.fxml"));
		ControllerEditSchedule control = new ControllerEditSchedule(idScheduleSelected);
		loader.setController(control);
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.showAndWait();
		initialize();

	}

	@FXML
	void deleteSchedule(ActionEvent event) throws IOException {

		AdministratorPageConnection adminDB = new AdministratorPageConnection();

		String[] params = { String.valueOf(idScheduleSelected) };
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/YesNoAlertDialog.fxml"));
		ControllerYesNoAlertDialog control = new ControllerYesNoAlertDialog(0, 0, "Atención",
				"Esta acción es permanente, no se podrá deshacer. Preste atención y revise los datos.",
				"¿Está seguro de que desea eliminar el horario con el siguiente ID: "
						+ String.valueOf(idScheduleSelected) + "?",
				"SÍ", "No", "adminDeleteSchedule", params);
		loader.setController(control);
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setScene(new Scene(root));
		stage.showAndWait();
		initialize();

	}

	@FXML
	void addRole(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AddNewRolePage.fxml"));
		ControllerAddNewRole control = new ControllerAddNewRole(0, 0);
		loader.setController(control);
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.showAndWait();
		initialize();
	}

	@FXML
	void editRole(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AddNewRolePage.fxml"));
		ControllerEditRole control = new ControllerEditRole(0, 0, idRoleSelected);
		loader.setController(control);
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.showAndWait();
		initialize();

	}

	@FXML
	void deleteRole(ActionEvent event) throws IOException {

		AdministratorPageConnection adminDB = new AdministratorPageConnection();

		String[] params = { String.valueOf(idRoleSelected) };
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/YesNoAlertDialog.fxml"));
		ControllerYesNoAlertDialog control = new ControllerYesNoAlertDialog(0, 0, "Atención",
				"Esta acción es permanente, no se podrá deshacer. Preste atención y revise los datos.",
				"¿Está seguro de que desea eliminar el puesto con el siguiente ID: " + String.valueOf(idRoleSelected)
						+ "?",
				"SÍ", "No", "adminDeleteRole", params);
		loader.setController(control);
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setScene(new Scene(root));
		stage.showAndWait();
		initialize();

	}

	@FXML
	void userSelection(MouseEvent event) {
		try {
			idUserSelected = userTable.getSelectionModel().getSelectedItem().getId();
			btnEditUser.setDisable(false);
			btnDeleteUser.setDisable(false);
		} catch (Exception e) {
			btnEditUser.setDisable(true);
			btnDeleteUser.setDisable(true);
		}

	}

	@FXML
	void scheduleSelection(MouseEvent event) {
		try {
			idScheduleSelected = scheduleTable.getSelectionModel().getSelectedItem().getId();
			btnEditSchedule.setDisable(false);
			btnDeleteSchedule.setDisable(false);
		} catch (Exception e) {
			btnEditSchedule.setDisable(true);
			btnDeleteSchedule.setDisable(true);
		}
	}

	@FXML
	void roleSelection(MouseEvent event) {
		try {
			idRoleSelected = roleTable.getSelectionModel().getSelectedItem().getId();
			btnEditRole.setDisable(false);
			btnDeleteRole.setDisable(false);
		} catch (Exception e) {
			btnEditSchedule.setDisable(true);
			btnDeleteSchedule.setDisable(true);
		}

	}

	void getTableActiveUsers(AdministratorPageConnection adminDB) {

		ObservableList<ModelUserTable> obList = adminDB.getUsersTable();

		idUserTable.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameUserTable.setCellValueFactory(new PropertyValueFactory<>("name"));
		surnameUserTable.setCellValueFactory(new PropertyValueFactory<>("surname"));
		dniUserTable.setCellValueFactory(new PropertyValueFactory<>("dni"));
		dateUserTable.setCellValueFactory(new PropertyValueFactory<>("dob"));
		roleUserTable.setCellValueFactory(new PropertyValueFactory<>("roleId"));

		userTable.setItems(obList);

	}

	void getTableSchedules(AdministratorPageConnection adminDB) {

		ObservableList<ModelScheduleTable> obList = adminDB.getSchedulesTable();

		idScheduleTable.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameScheduleTable.setCellValueFactory(new PropertyValueFactory<>("scheduleName"));
		checkInScheduleTable.setCellValueFactory(new PropertyValueFactory<>("checkInTime"));
		checkOutScheduleTable.setCellValueFactory(new PropertyValueFactory<>("checkOutTime"));

		scheduleTable.setItems(obList);

	}

	void getTableRoles(AdministratorPageConnection adminDB) {

		ObservableList<ModelRoleTable> obList = adminDB.getRolesTable();

		idRoleTable.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameRoleTable.setCellValueFactory(new PropertyValueFactory<>("roleName"));
		numUsersRoleTable.setCellValueFactory(new PropertyValueFactory<>("numUsersRole"));

		roleTable.setItems(obList);

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

}
