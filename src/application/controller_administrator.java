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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
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
import db.AdministratorPageConnection;

public class controller_administrator {

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
	private TableView<String> userTable;

	@FXML
	private TableColumn<String, String> id_userTable;

	@FXML
	private TableColumn<String, String> name_userTable;

	@FXML
	private TableColumn<String, String> surname_userTable;

	@FXML
	private TableColumn<String, String> dni_userTable;

	@FXML
	private TableColumn<String, String> date_userTable;

	@FXML
	private TableColumn<String, String> role_userTable;

	@FXML
	private JFXButton btnAddUser;

	@FXML
	private JFXButton btnEditUser;

	@FXML
	private JFXButton btnDeleteUser;

	// tab schedules
	@FXML
	private TableView<String> scheduleTable;

	@FXML
	private TableColumn<String, String> id_scheduleTable;

	@FXML
	private TableColumn<String, String> name_scheduleTable;

	@FXML
	private TableColumn<String, String> checkIn_scheduleTable;

	@FXML
	private TableColumn<String, String> checkOut_scheduleTable;

	@FXML
	private JFXButton btnAddSchedule;

	@FXML
	private JFXButton btnEditSchedule;

	@FXML
	private JFXButton btnDeleteSchedule;

	// tab role
	@FXML
	private TableView<String> roleTable;

	@FXML
	private TableColumn<String, String> id_roleTable;

	@FXML
	private TableColumn<String, String> name_roleTable;

	@FXML
	private TableColumn<String, String> numUsers_roleTable;

	@FXML
	private JFXButton btnAddRole;

	@FXML
	private JFXButton btnEditRole;

	@FXML
	private JFXButton btnDeleteRole;

	boolean isExpanded = false;

	@FXML
	void initialize() {

		try {

			AdministratorPageConnection adminDB = new AdministratorPageConnection();

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
		stage.show();

	}

	@FXML
	void editUser(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AddNewUserPage.fxml"));
		ControllerEditUser control = new ControllerEditUser(600.0, 900.0, 1);
		loader.setController(control);
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();

	}

	@FXML
	void deleteUser(ActionEvent event) {

	}

	boolean checkAllFieldsDelete() {
		return false;
	}

	@FXML
	void addRole(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AddNewRolePage.fxml"));
		ControllerAddNewRole control = new ControllerAddNewRole();
		loader.setController(control);
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();

	}

	@FXML
	void addSchedule(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AddNewSchedulePage.fxml"));
		ControllerAddNewSchedule control = new ControllerAddNewSchedule();
		loader.setController(control);
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();

	}

	@FXML
	void deleteRole(ActionEvent event) {

	}

	@FXML
	void deleteSchedule(ActionEvent event) {

	}

	@FXML
	void editRole(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AddNewRolePage.fxml"));
		// TODO: cambiar el último parámetro con el id del rol que se quiere modificar
		ControllerEditRole control = new ControllerEditRole(parent.getHeight(), parent.getWidth(), 0);
		loader.setController(control);
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();

	}

	@FXML
	void editSchedule(ActionEvent event) {

	}

}
