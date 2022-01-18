package application;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ControllerAddNewRole {

	@FXML
	private GridPane grdAddRole;

	@FXML
	private Label lblName;

	@FXML
	private Label lblGrossSalary;

	@FXML
	private JFXTextField fieldName;

	@FXML
	private JFXTextField fieldGrossSalary;

	@FXML
	private Label lblGrossSalary2;

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
			if (isNumeric(fieldGrossSalary.getText())) {
				// Guardar

				Stage stage = (Stage) btnCancel.getScene().getWindow();
				stage.close();

			} else {
				// El salario no es un numero
			}
		} else {
			// No se han rellenado todos los campos
		}

	}

	@FXML
	void initialize() {

	}

	boolean checkAllFields() {

		try {

			if ((!fieldName.getText().isEmpty() && fieldName.getText() != null && fieldName.getText() != "")
					&& (!fieldGrossSalary.getText().isEmpty() && fieldGrossSalary.getText() != null
							&& fieldGrossSalary.getText() != "")) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			System.out.println("ERROR: ControllerAddNewRole.java - checkAllFields() - " + e.toString());
			return false;
		}

	}

	boolean isNumeric(String str) {

		try {
			Integer.parseInt(str);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

}
