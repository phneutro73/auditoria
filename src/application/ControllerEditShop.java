package application;

import java.time.LocalTime;
import java.util.Hashtable;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import db.AdministratorPageConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ControllerEditShop {

	int shopId;

	public ControllerEditShop(int shopId) {
		this.shopId = shopId;
	}

	@FXML
	private Label subtitle;

	@FXML
	private GridPane grdAddSchedule;

	@FXML
	private Label lblName;

	@FXML
	private Label lblDirection;

	@FXML
	private JFXTextField fieldName;

	@FXML
	private JFXTextField fieldDirection;

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
	void saveNewShop(ActionEvent event) {
		// TODO
	}

	@FXML
	void initialize() {
		subtitle.setText("Modificar tienda");
		AdministratorPageConnection adminDB = new AdministratorPageConnection();
		getShop(adminDB);
	}

	boolean checkAllFields() {

		try {

			if ((!fieldName.getText().isEmpty() && fieldName.getText() != null && fieldName.getText() != "")
					&& (!fieldDirection.getText().isEmpty() && fieldDirection.getText() != null
							&& fieldDirection.getText() != "")) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			System.out.println("ERROR: controller_administrator.java - checkAllFields() - " + e.toString());
			return false;
		}
	}

	void getShop(AdministratorPageConnection adminDB) {
		// TODO
	}
}
