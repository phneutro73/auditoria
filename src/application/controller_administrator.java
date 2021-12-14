package application;

import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import db.AdminnistratorPageConnection;

public class controller_administrator {

	@FXML
	private Label textTitleAdministrator;

	// Drawer

	@FXML
	private VBox drawer;

	@FXML
	private Label lblUserName;

	@FXML
	private Label lblUserSurname;

	@FXML
	private JFXButton btnSales;

	@FXML
	private JFXButton btnConsultation;

	@FXML
	private JFXButton btnAddArticle;

	@FXML
	private JFXButton btnStatistics;

	@FXML
	private JFXButton btnAdministrator;

	// Add user

	@FXML
	private Tab tabAddNewUser;

	@FXML
	private AnchorPane tabAddUser;

	@FXML
	private Label lblName;

	@FXML
	private Label lblSurname;

	@FXML
	private Label lblDat;

	@FXML
	private Label lblDni;

	@FXML
	private Label lblEmail;

	@FXML
	private Label lblUser;

	@FXML
	private Label lblPassword;

	@FXML
	private Label lblEmail2;

	@FXML
	private Label lblPassword2;

	@FXML
	private Label lblRole;

	@FXML
	private TextField fieldEmail2;

	@FXML
	private TextField fieldUser;

	@FXML
	private TextField fieldEmail;

	@FXML
	private DatePicker fieldDat;

	@FXML
	private TextField fieldDNI;

	@FXML
	private TextField fieldSurname;

	@FXML
	private TextField fieldName;

	@FXML
	private ComboBox<String> fieldRole;

	@FXML
	private PasswordField fieldPassword;

	@FXML
	private PasswordField fieldPassword2;

	@FXML
	private JFXCheckBox chkAccept;

	@FXML
	private JFXButton btnTerm;

	@FXML
	private Button btnSave;

	@FXML
	private Label txtResult;

	@FXML
	private Label lblMonday;

	@FXML
	private Label lblTuesday;

	@FXML
	private Label lblWednesday;

	@FXML
	private Label lblThursday;

	@FXML
	private Label lblFirday;

	@FXML
	private Label lblSaturday;

	@FXML
	private Label lblSunday;

	@FXML
	private ComboBox<String> cmbMondayInit;

	@FXML
	private ComboBox<String> cmbMondayEnd;

	@FXML
	private ComboBox<String> cmbTuesdayInit;

	@FXML
	private ComboBox<String> cmbTuesdayEnd;

	@FXML
	private ComboBox<String> cmbWednesdayInit;

	@FXML
	private ComboBox<String> cmbWednesdayEnd;

	@FXML
	private ComboBox<String> cmbThursdayInit;

	@FXML
	private ComboBox<String> cmbThursdayEnd;

	@FXML
	private ComboBox<String> cmbFridayInit;

	@FXML
	private ComboBox<String> cmbFridayEnd;

	@FXML
	private ComboBox<String> cmbSaturdayInit;

	@FXML
	private ComboBox<String> cmbSaturdayEnd;

	@FXML
	private ComboBox<String> cmbSundayInit;

	@FXML
	private ComboBox<String> cmbSundayEnd;

	// Delete user
	
	@FXML
	private Tab tabDeleteUser;

	@FXML
	private Label lblUserDelete;

	@FXML
	private ComboBox<String> cmbUserDelete;

	@FXML
	private Button btnDeleteUser;

	@FXML
	private Label txtWarningDelete1;

	@FXML
	private Label lblDniDelete;

	@FXML
	private TextField fieldDniDelete;

	@FXML
	private Label txtWarningDelete2;

	@FXML
	private Label txtResultDelete;

	@FXML
	void saveUser(ActionEvent event) {

		try {

			boolean notEmptyFields = checkAllFields();
			if (notEmptyFields) {
				boolean checkEmail = checkSecondField("email");
				boolean checkPassword = checkSecondField("password");
				boolean isAccepted = chkAccept.isSelected();

				if (checkEmail && checkPassword && isAccepted) {
					// TODO: guardar en bbdd
					txtResult.setText("Guardado correctamente.");
					txtResult.setTextFill(Color.GREEN);
				} else {
					txtResult.setText("Se ha producido un error. Revise los campos.");
					txtResult.setTextFill(Color.RED);
				}
			} else {
				txtResult.setText("Rellene todos los campos.");
				txtResult.setTextFill(Color.RED);
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	boolean checkAllFields() {

		try {

			if ((!fieldName.getText().isEmpty() && fieldName.getText() != null && fieldName.getText() != "")
					&& (!fieldSurname.getText().isEmpty() && fieldSurname.getText() != null
							&& fieldName.getText() != "")
					&& (!fieldDNI.getText().isEmpty() && fieldDNI.getText() != null && fieldDNI.getText() != "")
					&& (!fieldDat.getValue().toString().isEmpty() && fieldDat.getValue().toString() != null
							&& fieldDat.getValue().toString() != "")
					&& (!fieldEmail.getText().isEmpty() && fieldEmail.getText() != null && fieldEmail.getText() != "")
					&& (!fieldEmail2.getText().isEmpty() && fieldEmail2.getText() != null
							&& fieldEmail2.getText() != "")
					&& (!fieldUser.getText().isEmpty() && fieldUser.getText() != null && fieldUser.getText() != "")
					&& (!fieldPassword.getText().isEmpty() && fieldPassword.getText() != null
							&& fieldPassword.getText() != "")
					&& (!fieldPassword2.getText().isEmpty() && fieldPassword2.getText() != null
							&& fieldPassword2.getText() != "")
					&& (!fieldRole.getValue().isEmpty() && fieldRole.getValue() != null && fieldRole.getValue() != ""
							&& fieldRole.getValue() != "-")) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			System.out.println("ERROR: controller_administrator.java - checkAllFields() - " + e.toString());
			return false;
		}
	}

	boolean checkSecondField(String field) {

		try {

			switch (field) {

			case "email":
				if (fieldEmail.getText().equals(fieldEmail2.getText())) {
					return true;
				} else {
					return false;
				}
			case "password":
				if (fieldPassword.getText().equals(fieldPassword2.getText())) {
					return true;
				} else {
					return false;
				}
			default:
				return false;
			}

		} catch (Exception e) {
			System.out.println("ERROR: controller_administrator.java - checkSecondField() - " + e.toString());
			return false;
		}

	}

	@FXML
	void deleteUser(ActionEvent event) {

		try {

			boolean notEmptyFields = checkAllFieldsDelete();

			if (notEmptyFields) {
				// TODO: Comprobar que el dni pertenezca al usuario
				boolean isDniUser = true;

				if (isDniUser) {
					// TODO: Eliminar usuario de la bbdd
					txtResultDelete.setText("Eliminado correctamente.");
					txtResultDelete.setTextFill(Color.GREEN);
				} else {
					txtResultDelete.setText("Los datos introducidos no concuerdan. Revise los campos.");
					txtResultDelete.setTextFill(Color.RED);
				}

			} else {
				txtResultDelete.setText("Todos los campos son obligatorios.");
				txtResultDelete.setTextFill(Color.RED);
			}

		} catch (Exception e) {
			System.out.println("ERROR: controller_administrator.java - checkSecondField() - " + e.toString());
			txtResultDelete.setText("Se ha producido un error");
			txtResultDelete.setTextFill(Color.RED);
		}

	}

	boolean checkAllFieldsDelete() {

		try {

			if ((!cmbUserDelete.getValue().toString().isEmpty() && cmbUserDelete.getValue() != null
					&& cmbUserDelete.getValue() != "" && cmbUserDelete.getValue() != "-")
					&& (!fieldDniDelete.getText().isEmpty() && fieldDniDelete.getText() != null
							&& fieldDniDelete.getText() != "")) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			System.out.println("ERROR: controller_administrator.java - checkAllFieldsDelete() - " + e.toString());
			return false;
		}

	}
	
	
	@FXML
	void initialize() {

		try {

			// TODO: Sacar el nombre y apellido del usuario y asignarlo a las variables
			// lblUserName y lblUserSurname
			
			AdminnistratorPageConnection adminDB = new AdminnistratorPageConnection();
			
			drawer.setBackground(
					new Background(new BackgroundFill(Color.rgb(226, 242, 245), CornerRadii.EMPTY, Insets.EMPTY)));

			btnAdministrator.setStyle("-fx-background-color: #CBE1E6");
			btnSales.setDisableVisualFocus(true);
			
			fieldRole.getItems().removeAll(fieldRole.getItems());
			fieldRole.getItems().addAll(adminDB.listRoles());
			fieldRole.getSelectionModel().select("-");

			List<String> list = new ArrayList<String>();
			list.add("-");

			for (int i = 0; i <= 24; i++) {
				String hour = Integer.toString(i);
				if (hour.length() < 2) {
					hour = "0" + hour;
				}
				for (int j = 0; j <= 46; j = j + 15) {
					String minute = Integer.toString(j);
					if (minute.length() < 2) {
						minute = "0" + minute;
					}
					list.add(hour + ":" + minute);
				}
			}

			cmbMondayInit.getItems().removeAll(cmbMondayInit.getItems());
			cmbMondayInit.getItems().addAll(list);
			cmbMondayInit.getSelectionModel().select("-");

			cmbMondayEnd.getItems().removeAll(cmbMondayEnd.getItems());
			cmbMondayEnd.getItems().addAll(list);
			cmbMondayEnd.getSelectionModel().select("-");

			cmbTuesdayInit.getItems().removeAll(cmbTuesdayInit.getItems());
			cmbTuesdayInit.getItems().addAll(list);
			cmbTuesdayInit.getSelectionModel().select("-");

			cmbTuesdayEnd.getItems().removeAll(cmbTuesdayEnd.getItems());
			cmbTuesdayEnd.getItems().addAll(list);
			cmbTuesdayEnd.getSelectionModel().select("-");

			cmbWednesdayInit.getItems().removeAll(cmbWednesdayInit.getItems());
			cmbWednesdayInit.getItems().addAll(list);
			cmbWednesdayInit.getSelectionModel().select("-");

			cmbWednesdayEnd.getItems().removeAll(cmbWednesdayEnd.getItems());
			cmbWednesdayEnd.getItems().addAll(list);
			cmbWednesdayEnd.getSelectionModel().select("-");

			cmbThursdayInit.getItems().removeAll(cmbThursdayInit.getItems());
			cmbThursdayInit.getItems().addAll(list);
			cmbThursdayInit.getSelectionModel().select("-");

			cmbThursdayEnd.getItems().removeAll(cmbThursdayEnd.getItems());
			cmbThursdayEnd.getItems().addAll(list);
			cmbThursdayEnd.getSelectionModel().select("-");

			cmbFridayInit.getItems().removeAll(cmbFridayInit.getItems());
			cmbFridayInit.getItems().addAll(list);
			cmbFridayInit.getSelectionModel().select("-");

			cmbFridayEnd.getItems().removeAll(cmbFridayEnd.getItems());
			cmbFridayEnd.getItems().addAll(list);
			cmbFridayEnd.getSelectionModel().select("-");

			cmbSaturdayInit.getItems().removeAll(cmbSaturdayInit.getItems());
			cmbSaturdayInit.getItems().addAll(list);
			cmbSaturdayInit.getSelectionModel().select("-");

			cmbSaturdayEnd.getItems().removeAll(cmbSaturdayEnd.getItems());
			cmbSaturdayEnd.getItems().addAll(list);
			cmbSaturdayEnd.getSelectionModel().select("-");

			cmbSundayInit.getItems().removeAll(cmbSundayInit.getItems());
			cmbSundayInit.getItems().addAll(list);
			cmbSundayInit.getSelectionModel().select("-");

			cmbSundayEnd.getItems().removeAll(cmbSundayEnd.getItems());
			cmbSundayEnd.getItems().addAll(list);
			cmbSundayEnd.getSelectionModel().select("-");

			List<String> listUsers = new ArrayList<String>();
			listUsers.add("-");

			// TODO: Sacar en una select los usuarios que hay en la bbdd y que los guarde en
			// la lista

			cmbUserDelete.getItems().removeAll(cmbUserDelete.getItems());
			cmbUserDelete.getItems().addAll(listUsers);
			cmbUserDelete.getSelectionModel().select("-");

		} catch (Exception e) {
			System.out.println("ERROR: controller_administrator.java - initialize() - " + e.toString());
		}

	}

}
