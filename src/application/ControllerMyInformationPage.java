package application;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import db.AdministratorPageConnection;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import models.CurrentUser;

public class ControllerMyInformationPage {

	double height;
	double width;
	CurrentUser currentUser;

	public ControllerMyInformationPage(double height, double width, CurrentUser currentUser) {
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
	private Label subtitle;

	@FXML
	private AnchorPane txtEditInstructions;

	@FXML
	private Label lblPersonalInfo;

	@FXML
	private GridPane grdPersonalInfo;

	@FXML
	private Label lblName;

	@FXML
	private Label lblSurname;

	@FXML
	private Label lblDni;

	@FXML
	private Label lblDob;

	@FXML
	private Label lblEmail;

	@FXML
	private Label lblEmail2;

	@FXML
	private Label lblRole;

	@FXML
	private JFXTextField fieldName;

	@FXML
	private JFXTextField fieldSurname;

	@FXML
	private JFXTextField fieldDNI;

	@FXML
	private JFXDatePicker fieldDob;

	@FXML
	private JFXTextField fieldUser;

	@FXML
	private JFXTextField fieldEmail;

	@FXML
	private JFXComboBox<String> cmbRole;

	@FXML
	private Label lblShop;

	@FXML
	private JFXComboBox<String> cmbShop;

	@FXML
	private Label lblSchedule;

	@FXML
	private GridPane grdSchedule;

	@FXML
	private Label lblMonday;

	@FXML
	private Label lblTuesday;

	@FXML
	private Label lblWednesday;

	@FXML
	private Label lblThursday;

	@FXML
	private Label lblFriday;

	@FXML
	private Label lblSaturday;

	@FXML
	private Label lblSunday;

	@FXML
	private JFXComboBox<String> cmbMonday;

	@FXML
	private JFXComboBox<String> cmbTuesday;

	@FXML
	private JFXComboBox<String> cmbWednesday;

	@FXML
	private JFXComboBox<String> cmbThursday;

	@FXML
	private JFXComboBox<String> cmbFriday;

	@FXML
	private JFXComboBox<String> cmbSaturday;

	@FXML
	private JFXComboBox<String> cmbSunday;

	@FXML
	void initialize() {

		AdministratorPageConnection adminDB = new AdministratorPageConnection();

		cmbRole.getSelectionModel().select("-");
		cmbMonday.getSelectionModel().select("-");
		cmbTuesday.getSelectionModel().select("-");
		cmbWednesday.getSelectionModel().select("-");
		cmbThursday.getSelectionModel().select("-");
		cmbFriday.getSelectionModel().select("-");
		cmbSaturday.getSelectionModel().select("-");
		cmbSunday.getSelectionModel().select("-");
		cmbShop.getSelectionModel().select("-");

		getUser(adminDB);
		// TODO: Rellenar todos los campos
		/*
		 * fieldName.setText(currentUser.getName());
		 * fieldSurname.setText(currentUser.getSurname());
		 * fieldDNI.setText(currentUser.getIdNumber()); // fieldDob
		 * fieldEmail.setText(currentUser.getEmail());
		 */
		/*
		 * cmbMonday.getSelectionModel().select(schedule.get(0));
		 * cmbTuesday.getSelectionModel().select(schedule.get(1));
		 * cmbWednesday.getSelectionModel().select(schedule.get(2));
		 * cmbThursday.getSelectionModel().select(schedule.get(3));
		 * cmbFriday.getSelectionModel().select(schedule.get(4));
		 * cmbSaturday.getSelectionModel().select(schedule.get(5));
		 * cmbSunday.getSelectionModel().select(schedule.get(6));
		 */

		// TODO: Inhabilitar todos los campos
		fieldName.setDisable(true);
		fieldSurname.setDisable(true);
		fieldDNI.setDisable(true);
		fieldDob.setDisable(true);
		fieldUser.setDisable(true);
		fieldEmail.setDisable(true);
		cmbRole.setDisable(true);
		cmbShop.setDisable(true);
		cmbMonday.setDisable(true);
		cmbTuesday.setDisable(true);
		cmbWednesday.setDisable(true);
		cmbThursday.setDisable(true);
		cmbFriday.setDisable(true);
		cmbSunday.setDisable(true);
		cmbSaturday.setDisable(true);
		// TODO: Quitar el vbox que sobra (está porque se necesitaban los botones)
	}

	void getUser(AdministratorPageConnection adminDB) {
		Hashtable<String, Object> user = adminDB.getUser(currentUser.getId());

		String name;
		String surname;
		Date dob;
		LocalDate dobLD;
		String idNumber;
		Hashtable<Integer, String> schedule;
		String role;
		String email;
		String userName;
		String shop;

		if (user.containsKey("name")) {
			name = (String) user.get("name");
			fieldName.setText(name);
		}
		if (user.containsKey("surname")) {
			surname = (String) user.get("surname");
			fieldSurname.setText(surname);
		}
		if (user.containsKey("dob")) {
			dob = (Date) user.get("dob");
			dobLD = Instant.ofEpochMilli(dob.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
			fieldDob.setValue(dobLD);
		}
		if (user.containsKey("idNumber")) {
			idNumber = (String) user.get("idNumber");
			fieldDNI.setText(idNumber);
		}
		if (user.containsKey("schedule")) {
			schedule = (Hashtable<Integer, String>) user.get("schedule");

			String monday = schedule.get(0);
			cmbMonday.getItems().removeAll(cmbMonday.getItems());
			cmbMonday.getItems().add(monday);
			cmbMonday.getSelectionModel().select(monday);

			String tuesday = schedule.get(1);
			cmbTuesday.getItems().removeAll(cmbTuesday.getItems());
			cmbTuesday.getItems().add(tuesday);
			cmbTuesday.getSelectionModel().select(tuesday);

			String wednesday = schedule.get(2);
			cmbWednesday.getItems().removeAll(cmbWednesday.getItems());
			cmbWednesday.getItems().add(wednesday);
			cmbWednesday.getSelectionModel().select(wednesday);

			String thursday = schedule.get(3);
			cmbThursday.getItems().removeAll(cmbThursday.getItems());
			cmbThursday.getItems().add(thursday);
			cmbThursday.getSelectionModel().select(thursday);

			String friday = schedule.get(4);
			cmbFriday.getItems().removeAll(cmbFriday.getItems());
			cmbFriday.getItems().add(friday);
			cmbFriday.getSelectionModel().select(friday);

			String saturday = schedule.get(5);
			cmbSaturday.getItems().removeAll(cmbSaturday.getItems());
			cmbSaturday.getItems().add(saturday);
			cmbSaturday.getSelectionModel().select(saturday);

			String sunday = schedule.get(6);
			cmbSunday.getItems().removeAll(cmbSunday.getItems());
			cmbSunday.getItems().add(sunday);
			cmbSunday.getSelectionModel().select(sunday);
		}
		if (user.containsKey("roleName")) {
			role = (String) user.get("roleName");
			cmbRole.getItems().removeAll(cmbRole.getItems());
			cmbRole.getItems().add(role);
			cmbRole.getSelectionModel().select(role);
		}
		if (user.containsKey("email")) {
			email = (String) user.get("email");
			fieldEmail.setText(email);
		}
		if (user.containsKey("userName")) {
			userName = (String) user.get("userName");
			fieldUser.setText(userName);
		}
		if (user.containsKey("shopName")) {
			shop = (String) user.get("shopName");
			cmbShop.getItems().removeAll(cmbShop.getItems());
			cmbShop.getItems().addAll(shop);
			cmbShop.getSelectionModel().select(shop);

		}

	}
}
