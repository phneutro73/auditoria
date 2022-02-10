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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import models.ModelItemTypeTable;
import models.ModelRoleTable;
import models.ModelScheduleTable;
import models.ModelShopTable;
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

    @FXML
    private VBox body;
    
    @FXML
    private VBox vBoxButtons;

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
	private JFXTextField txtScheduleSearch;

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
	private JFXTextField txtRoleSearch;

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

	// tab shop
	@FXML
	private JFXTextField txtShopSearch;

	@FXML
	private TableView<ModelShopTable> shopTable;

	@FXML
	private TableColumn<ModelShopTable, String> idShopTable;

	@FXML
	private TableColumn<ModelShopTable, String> nameShopTable;

	@FXML
	private TableColumn<ModelShopTable, String> directionShopTable;

	@FXML
	private TableColumn<ModelShopTable, String> numWorkersShopTable;

	@FXML
	private JFXButton btnAddShop;

	@FXML
	private JFXButton btnEditShop;

	@FXML
	private JFXButton btnDeleteShop;

	// tab item type
	@FXML
	private JFXTextField txtItemTypeSearch;

	@FXML
	private TableView<ModelItemTypeTable> itemTypeTable;

	@FXML
	private TableColumn<ModelItemTypeTable, String> idItemTypeTable;

	@FXML
	private TableColumn<ModelItemTypeTable, String> nameItemTypeTable;

	@FXML
	private JFXButton btnAddItemType;

	@FXML
	private JFXButton btnEditItemType;

	@FXML
	private JFXButton btnDeleteItemType;

	boolean isExpanded = false;
	int idUserSelected;
	int idScheduleSelected;
	int idRoleSelected;
	int idShopSelected;
	int idItemTypeSelected;

	@FXML
	void initialize() {

		try {

			AdministratorPageConnection adminDB = new AdministratorPageConnection();

			getTableActiveUsers(adminDB);
			getTableSchedules(adminDB);
			getTableRoles(adminDB);
			getTableShops(adminDB);
			getTableItemTypes(adminDB);

			btnEditUser.setDisable(true);
			btnDeleteUser.setDisable(true);
			btnEditSchedule.setDisable(true);
			btnDeleteSchedule.setDisable(true);
			btnEditRole.setDisable(true);
			btnDeleteRole.setDisable(true);
			btnEditShop.setDisable(true);
			btnDeleteShop.setDisable(true);
			btnEditItemType.setDisable(true);
			btnDeleteItemType.setDisable(true);

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
	void addShop(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AddNewShopPage.fxml"));
		ControllerAddNewShop control = new ControllerAddNewShop();
		loader.setController(control);
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.showAndWait();
		initialize();

	}

	@FXML
	void editShop(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AddNewShopPage.fxml"));
		ControllerEditShop control = new ControllerEditShop(idShopSelected);
		loader.setController(control);
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.showAndWait();
		initialize();

	}

	@FXML
	void deleteShop(ActionEvent event) throws IOException {

		AdministratorPageConnection adminDB = new AdministratorPageConnection();

		String[] params = { String.valueOf(idShopSelected) };
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/YesNoAlertDialog.fxml"));
		ControllerYesNoAlertDialog control = new ControllerYesNoAlertDialog(0, 0, "Atención",
				"Esta acción es permanente, no se podrá deshacer. Preste atención y revise los datos.",
				"¿Está seguro de que desea eliminar la tienda con el siguiente ID: " + String.valueOf(idShopSelected)
						+ "?",
				"SÍ", "No", "adminDeleteShop", params);
		loader.setController(control);
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setScene(new Scene(root));
		stage.showAndWait();
		initialize();

	}

	@FXML
	void addItemType(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AddNewItemType.fxml"));
		ControllerAddNewItemType control = new ControllerAddNewItemType();
		loader.setController(control);
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.showAndWait();
		initialize();

	}

	@FXML
	void editItemType(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AddNewItemType.fxml"));
		ControllerEditItemType control = new ControllerEditItemType(0, 0, idItemTypeSelected);
		loader.setController(control);
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.showAndWait();
		initialize();

	}

	@FXML
	void deleteItemType(ActionEvent event) throws IOException {

		AdministratorPageConnection adminDB = new AdministratorPageConnection();

		String[] params = { String.valueOf(idShopSelected) };
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/YesNoAlertDialog.fxml"));
		ControllerYesNoAlertDialog control = new ControllerYesNoAlertDialog(0, 0, "Atención",
				"Esta acción es permanente, no se podrá deshacer. Preste atención y revise los datos.",
				"¿Está seguro de que desea eliminar el tipo de artículo con el siguiente ID: "
						+ String.valueOf(idShopSelected) + "?",
				"SÍ", "No", "adminDeleteItemType", params);
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
			hideMenu(event);
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
			hideMenu(event);
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
			hideMenu(event);
			idRoleSelected = roleTable.getSelectionModel().getSelectedItem().getId();
			btnEditRole.setDisable(false);
			btnDeleteRole.setDisable(false);
		} catch (Exception e) {
			btnEditRole.setDisable(true);
			btnDeleteRole.setDisable(true);
		}
	}

	@FXML
	void shopSelection(MouseEvent event) {
		try {
			hideMenu(event);
			idShopSelected = shopTable.getSelectionModel().getSelectedItem().getId();
			btnEditShop.setDisable(false);
			btnDeleteShop.setDisable(false);
		} catch (Exception e) {
			btnEditShop.setDisable(true);
			btnDeleteShop.setDisable(true);
		}
	}

	@FXML
	void itemTypeSelection(MouseEvent event) {
		try {
			hideMenu(event);
			idItemTypeSelected = itemTypeTable.getSelectionModel().getSelectedItem().getId();
			btnEditItemType.setDisable(false);
			btnDeleteItemType.setDisable(false);
		} catch (Exception e) {
			btnEditItemType.setDisable(true);
			btnDeleteItemType.setDisable(true);
		}
	}

	void getTableActiveUsers(AdministratorPageConnection adminDB) {

		ObservableList<ModelUserTable> obList = adminDB.getUsersTable();

		idUserTable.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameUserTable.setCellValueFactory(new PropertyValueFactory<>("name"));
		surnameUserTable.setCellValueFactory(new PropertyValueFactory<>("surname"));
		dniUserTable.setCellValueFactory(new PropertyValueFactory<>("dni"));
		dateUserTable.setCellValueFactory(new PropertyValueFactory<>("dob"));
		roleUserTable.setCellValueFactory(new PropertyValueFactory<>("roleName"));

		userTable.setItems(obList);
		
		FilteredList<ModelUserTable> filteredUserData = new FilteredList<>(obList, b -> true);
		txtUserSearch.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredUserData.setPredicate(userSearchModel ->  {
				if (newValue.isEmpty() || newValue == null) {
					return true;
				}
				String searchUserKeyword = newValue.toLowerCase();
				
				if (userSearchModel.getStrId() != null && userSearchModel.getStrId().toLowerCase().indexOf(searchUserKeyword) > -1) {
					return true;
				} else if (userSearchModel.getName() != null && userSearchModel.getName().toLowerCase().indexOf(searchUserKeyword) > -1) {
					return true;
				} else if (userSearchModel.getSurname() != null && userSearchModel.getSurname().toLowerCase().indexOf(searchUserKeyword) > -1) {
					return true;
				} else if (userSearchModel.getDni() != null && userSearchModel.getDni().toLowerCase().indexOf(searchUserKeyword) > -1) {
					return true;
				} else if (userSearchModel.getDob() != null && userSearchModel.getDob().toLowerCase().indexOf(searchUserKeyword) > -1) {
					return true;
				} else if (userSearchModel.getRoleName() != null && userSearchModel.getRoleName().toLowerCase().indexOf(searchUserKeyword) > -1) {
					return true;
				} else {
					return false;
				}
			});
		});
		
		SortedList<ModelUserTable> sortedUserData = new SortedList<>(filteredUserData);
		sortedUserData.comparatorProperty().bind(userTable.comparatorProperty());
		userTable.setItems(sortedUserData);

	}

	void getTableSchedules(AdministratorPageConnection adminDB) {

		ObservableList<ModelScheduleTable> obList = adminDB.getSchedulesTable();

		idScheduleTable.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameScheduleTable.setCellValueFactory(new PropertyValueFactory<>("scheduleName"));
		checkInScheduleTable.setCellValueFactory(new PropertyValueFactory<>("checkInTime"));
		checkOutScheduleTable.setCellValueFactory(new PropertyValueFactory<>("checkOutTime"));

		scheduleTable.setItems(obList);
		
		FilteredList<ModelScheduleTable> filteredScheduleData = new FilteredList<>(obList, b -> true);
		txtScheduleSearch.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredScheduleData.setPredicate(scheduleSearchModel ->  {
				if (newValue.isEmpty() || newValue == null) {
					return true;
				}
				String searchScheduleKeyword = newValue.toLowerCase();
				
				if (scheduleSearchModel.getStrId() != null && scheduleSearchModel.getStrId().toLowerCase().indexOf(searchScheduleKeyword) > -1) {
					return true;
				} else if (scheduleSearchModel.getScheduleName() != null && scheduleSearchModel.getScheduleName().toLowerCase().indexOf(searchScheduleKeyword) > -1) {
					return true;
				} else if (scheduleSearchModel.getCheckInTime() != null && scheduleSearchModel.getCheckInTime().toLowerCase().indexOf(searchScheduleKeyword) > -1) {
					return true;
				} else if (scheduleSearchModel.getCheckOutTime() != null && scheduleSearchModel.getCheckOutTime().toLowerCase().indexOf(searchScheduleKeyword) > -1) {
					return true;
				} else {
					return false;
				}
			});
		});
		
		SortedList<ModelScheduleTable> sortedScheduleData = new SortedList<>(filteredScheduleData);
		sortedScheduleData.comparatorProperty().bind(scheduleTable.comparatorProperty());
		scheduleTable.setItems(sortedScheduleData);

	}

	void getTableRoles(AdministratorPageConnection adminDB) {

		ObservableList<ModelRoleTable> obList = adminDB.getRolesTable();

		idRoleTable.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameRoleTable.setCellValueFactory(new PropertyValueFactory<>("roleName"));
		numUsersRoleTable.setCellValueFactory(new PropertyValueFactory<>("numUsersRole"));

		roleTable.setItems(obList);
		
		FilteredList<ModelRoleTable> filteredRolesData = new FilteredList<>(obList, b -> true);
		txtRoleSearch.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredRolesData.setPredicate(roleSearchModel ->  {
				if (newValue.isEmpty() || newValue == null) {
					return true;
				}
				String searchRoleKeyword = newValue.toLowerCase();
				
				if (roleSearchModel.getStrId() != null && roleSearchModel.getStrId().toLowerCase().indexOf(searchRoleKeyword) > -1) {
					return true;
				} else if (roleSearchModel.getRoleName() != null && roleSearchModel.getRoleName().toLowerCase().indexOf(searchRoleKeyword) > -1) {
					return true;
				} else {
					return false;
				}
			});
		});
		
		SortedList<ModelRoleTable> sortedRoleData = new SortedList<>(filteredRolesData);
		sortedRoleData.comparatorProperty().bind(roleTable.comparatorProperty());
		roleTable.setItems(sortedRoleData);

	}

	void getTableShops(AdministratorPageConnection adminDB) {
		// TODO
	}

	void getTableItemTypes(AdministratorPageConnection adminDB) {
		// TODO
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

}
