package application;

import java.util.Hashtable;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import db.AdministratorPageConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ControllerEditItemType {

	double height;
	double width;
	int roleId;

	public ControllerEditItemType(double height, double width, int roleId) {
		super();
		this.height = height;
		this.width = width;
		this.roleId = roleId;
	}

	@FXML
	private AnchorPane parent;

	@FXML
	private Label title;

	@FXML
	private Label subtitle;

	@FXML
	private GridPane grdAddItemType;

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
	void saveItemType(ActionEvent event) {
		// TODO
	}

	@FXML
	void initialize() {
		subtitle.setText("Modificar tipo de artículo");
		AdministratorPageConnection adminDB = new AdministratorPageConnection();
		getItemType(adminDB);
	}

	boolean checkAllFields() {

		try {

			if ((!fieldName.getText().isEmpty() && fieldName.getText() != null && fieldName.getText() != "")) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			System.out.println("ERROR: ControllerEditItemType.java - checkAllFields() - " + e.toString());
			return false;
		}

	}

	void getItemType(AdministratorPageConnection adminDB) {
		// TODO
		/*
		 * Hashtable<String, String> role = adminDB.getRole(roleId); String roleName =
		 * role.get("name"); fieldName.setText(roleName);
		 */
	}
}
