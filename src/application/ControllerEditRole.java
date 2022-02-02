package application;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import db.AdministratorPageConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ControllerEditRole {

	double height;
	double width;
	int roleId;

	public ControllerEditRole(double height, double width, int roleId) {
		super();
		this.height = height;
		this.width = width;
		this.roleId = roleId;
	}

	@FXML
	private Label title;

	@FXML
	private GridPane grdAddRole;

	@FXML
	private Label lblName;

	@FXML
	private JFXTextField fieldName;

	@FXML
	private JFXButton btnCancel;

	@FXML
	private JFXButton btnAccept;

	@FXML
	void cancelExit(ActionEvent event) {
		Stage stage = (Stage) btnCancel.getScene().getWindow();
		stage.close();
	}

	@FXML
	void saveNewUser(ActionEvent event) {

		if (checkAllFields()) {
			// Update

			Stage stage = (Stage) btnCancel.getScene().getWindow();
			stage.close();

		} else {
			// No se han rellenado todos los campos
		}

	}

	@FXML
	void initialize() {

		try {

			title.setText("Modificar usuario");
			AdministratorPageConnection adminDB = new AdministratorPageConnection();
			// TODO: Obtener los datos dado el ID del rol

			fieldName.setText("");

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	boolean checkAllFields() {

		try {

			if ((!fieldName.getText().isEmpty() && fieldName.getText() != null && fieldName.getText() != "")) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			System.out.println("ERROR: ControllerAddNewRole.java - checkAllFields() - " + e.toString());
			return false;
		}

	}

}
