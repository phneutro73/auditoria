package application;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
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
	private JFXTextField fieldEmail;

	@FXML
	private JFXTextField fieldEmail2;

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
	private Label flblWednesday;

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
		// TODO: Rellenar todos los campos
		fieldName.setText(currentUser.getName());
		fieldSurname.setText(currentUser.getSurname());
		fieldDNI.setText(currentUser.getIdNumber());
		// fieldDob
		fieldEmail.setText(currentUser.getEmail());
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
}
