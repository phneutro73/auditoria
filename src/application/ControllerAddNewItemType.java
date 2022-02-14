package application;

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

public class ControllerAddNewItemType {

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

		try {

			if (checkAllFields()) {

				AdministratorPageConnection adminDB = new AdministratorPageConnection();
				boolean success = adminDB.addItemType(fieldName.getText());

				if (success) {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
					ControllerAlertDialog control = new ControllerAlertDialog(120, 210, "Guardado correcto",
							"Los datos del tipo de artículo se han guardado correctamente.");
					loader.setController(control);
					Parent root = loader.load();

					Stage stage = new Stage();
					stage.initStyle(StageStyle.UNDECORATED);
					stage.setScene(new Scene(root));
					stage.show();
				} else {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
					ControllerAlertDialog control = new ControllerAlertDialog(120, 210, "Error",
							"Se ha producido un error inesperado. Por favor, inténtelo más tarde.");
					loader.setController(control);
					Parent root = loader.load();

					Stage stage = new Stage();
					stage.initStyle(StageStyle.UNDECORATED);
					stage.setScene(new Scene(root));
					stage.show();
				}

				Stage stage = (Stage) btnCancel.getScene().getWindow();
				stage.close();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void initialize() {

	}

	boolean checkAllFields() {

		try {

			if ((!fieldName.getText().isEmpty() && fieldName.getText() != null && fieldName.getText() != "")) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			System.out.println("ERROR: ControllerAddNewItemType.java - checkAllFields() - " + e.toString());
			return false;
		}

	}
}
