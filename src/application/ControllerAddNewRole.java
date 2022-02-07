package application;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import db.AdministratorPageConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ControllerAddNewRole {

	double height;
	double width;

	public ControllerAddNewRole(double height, double width) {
		super();
		this.height = height;
		this.width = width;
	}

	@FXML
	private AnchorPane parent;

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
	void saveRole(ActionEvent event) throws IOException {

		if (checkAllFields()) {
			AdministratorPageConnection adminDB = new AdministratorPageConnection();
			boolean succes = adminDB.addRole(fieldName.getText());

			if (succes) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
				ControllerAlertDialog control = new ControllerAlertDialog(120, 210, "Guardado correcto",
						"Los datos se han guardado correctamente.");
				loader.setController(control);
				Parent root = loader.load();

				Stage stage = new Stage();
				stage.initStyle(StageStyle.UNDECORATED);
				stage.setScene(new Scene(root));
				stage.show();

				stage = (Stage) btnAccept.getScene().getWindow();
				stage.close();

			} else {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
				ControllerAlertDialog control = new ControllerAlertDialog(140, 210, "Error",
						"Se ha producido un error. Por favor, inténtelo de nuevo.");
				loader.setController(control);
				Parent root = loader.load();

				Stage stage = new Stage();
				stage.initStyle(StageStyle.UNDECORATED);
				stage.setScene(new Scene(root));
				stage.show();
			}
			Stage stage = (Stage) btnCancel.getScene().getWindow();
			stage.close();

		} else {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
			ControllerAlertDialog control = new ControllerAlertDialog(120, 210, "Error",
					"Es necesario que rellene todos los campos.");
			loader.setController(control);
			Parent root = loader.load();

			Stage stage = new Stage();
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setScene(new Scene(root));
			stage.show();
		}

	}

	@FXML
	void initialize() {

		if (height == 0) {
			height = 194;
		}
		if (width == 0) {
			width = 600;
		}
		parent.setPrefSize(width, height);

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
