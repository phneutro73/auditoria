package application;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;

import java.util.Hashtable;
import java.util.List;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;


import com.jfoenix.controls.JFXButton;
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

import db.AdministratorPageConnection;

public class controller_administrator {

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

	@FXML
	private Label textTitleAdministrator;

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
	private Label lblMonday;

	@FXML
	private TextField fieldName;

	@FXML
	private TextField fieldSurname;

	@FXML
	private TextField fieldDNI;

	@FXML
	private DatePicker fieldDat;

	@FXML
	private TextField fieldEmail;

	@FXML
	private TextField fieldEmail2;

	@FXML
	private TextField fieldUser;

	@FXML
	private PasswordField fieldPassword;

	@FXML
	private PasswordField fieldPassword2;

	@FXML
	private ComboBox<String> fieldRole;

	@FXML
	private ComboBox<String> cmbMonday;

	@FXML
	private Button btnSave;

	@FXML
	private Label lblTuesday;

	@FXML
	private Label lblWednesday;

	@FXML
	private Label lblThursday;

	@FXML
	private Label lblFriday;

	@FXML
	private Label lblSunday;

	@FXML
	private Label lblSaturday;

	@FXML
	private ComboBox<String> cmbTuesday;

	@FXML
	private ComboBox<String> cmbWednesday;

	@FXML
	private ComboBox<String> cmbThursday;

	@FXML
	private ComboBox<String> cmbFriday;

	@FXML
	private ComboBox<String> cmbSaturday;

	@FXML
	private ComboBox<String> cmbSunday;

	@FXML
	private Label txtResult;


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
			
			boolean noEmptyFields = checkAllFields();
			boolean noEmptySchedule = checkScheduleField();
			if (noEmptyFields && noEmptySchedule) {
				boolean checkEmail = checkSecondField("email");
				boolean checkPassword = checkSecondField("password");

				if (checkEmail && checkPassword) {
					
					AdministratorPageConnection adminDB = new AdministratorPageConnection();
					byte[][] pass = encryptPassword(fieldPassword.getText());
					String[] schedule = new String[] {cmbMonday.getValue(),cmbTuesday.getValue(),cmbWednesday.getValue(),cmbThursday.getValue(),cmbFriday.getValue(),cmbSaturday.getValue(),cmbSunday.getValue()};
					int[] numScedule = parseSchedule(schedule,adminDB);
					adminDB.addUser(fieldName.getText(), fieldSurname.getText(), fieldDat.getValue().toString(), fieldUser.getText(), fieldDNI.getText(), fieldEmail.getText(),pass[0] , pass[1], numScedule[0], numScedule[1], numScedule[2], numScedule[3], numScedule[4], numScedule[5], numScedule[6]);
					
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
	
	byte[][] encryptPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		byte[][] pass = new byte[2][];
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] hash = factory.generateSecret(spec).getEncoded();
		System.out.println(salt[0]);
		System.out.println(hash[0]);
		pass[0] = salt; 
		pass[1] = hash; 
		return pass;
	}
	
	int[] parseSchedule(String[] schedule,AdministratorPageConnection adminDB) {
		int[] numeric_schedule = new int[7];
		Hashtable<String,Integer> scheduleMap = adminDB.scheduleMap();
		for (int i = 0; i < schedule.length; i++) {
			 numeric_schedule[i] = scheduleMap.get(schedule[i]);
			}
		return numeric_schedule;
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
					&& (!fieldRole.getValue().toString().isEmpty() && fieldRole.getValue() != null
							&& fieldRole.getValue() != "" && fieldRole.getValue() != "-")) {
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
	
	boolean checkScheduleField() {
		
		try {
			if (cmbMonday.getValue().toString() != "-" && 
					cmbTuesday.getValue().toString() != "-" && 
					cmbWednesday.getValue().toString() != "-" && 
					cmbThursday.getValue().toString() != "-" && 
					cmbFriday.getValue().toString() != "-" && 
					cmbSaturday.getValue().toString() != "-" && 
					cmbSunday.getValue().toString() != "-") {
				return true;
			} else  {
				return false;
			}
			
		} catch (Exception e) {
			System.out.println("ERROR: controller_administrator.java - checkScheduleField() - " + e.toString());
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
			
			AdministratorPageConnection adminDB = new AdministratorPageConnection();
			
			drawer.setBackground(
					new Background(new BackgroundFill(Color.rgb(226, 242, 245), CornerRadii.EMPTY, Insets.EMPTY)));

			btnAdministrator.setStyle("-fx-background-color: #CBE1E6; -fx-alignment: center-left;");
			btnSales.setDisableVisualFocus(true);
			
			List<String> schedules = adminDB.listSchedules();
			fieldRole.getItems().removeAll(fieldRole.getItems());
			fieldRole.getItems().addAll(adminDB.listRoles());
			fieldRole.getSelectionModel().select("-");
			
			cmbMonday.getItems().removeAll(fieldRole.getItems());
			cmbMonday.getItems().addAll(schedules);
			cmbMonday.getSelectionModel().select("-");
			
			cmbTuesday.getItems().removeAll(fieldRole.getItems());
			cmbTuesday.getItems().addAll(schedules);
			cmbTuesday.getSelectionModel().select("-");
			
			cmbWednesday.getItems().removeAll(fieldRole.getItems());
			cmbWednesday.getItems().addAll(schedules);
			cmbWednesday.getSelectionModel().select("-");
			
			cmbThursday.getItems().removeAll(fieldRole.getItems());
			cmbThursday.getItems().addAll(schedules);
			cmbThursday.getSelectionModel().select("-");
			
			cmbFriday.getItems().removeAll(fieldRole.getItems());
			cmbFriday.getItems().addAll(schedules);
			cmbFriday.getSelectionModel().select("-");
			
			cmbSaturday.getItems().removeAll(fieldRole.getItems());
			cmbSaturday.getItems().addAll(schedules);
			cmbSaturday.getSelectionModel().select("-");
			
			cmbSunday.getItems().removeAll(fieldRole.getItems());
			cmbSunday.getItems().addAll(schedules);
			cmbSunday.getSelectionModel().select("-");
			
			
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
