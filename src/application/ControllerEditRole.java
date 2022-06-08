package application;

import java.util.Hashtable;

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
	private AnchorPane parent;

	@FXML
	private Label title;

	@FXML
	private Label subtitle;

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
	void saveRole(ActionEvent event) {

		try {
			if (checkAllFields()) {

				AdministratorPageConnection adminDB = new AdministratorPageConnection();
				boolean success = adminDB.updateRole(roleId, fieldName.getText());

				if (success) {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
					ControllerAlertDialog control = new ControllerAlertDialog(120, 210, "Acción completada",
							"Los datos se han guardado correctamente");
					loader.setController(control);
					Parent root = loader.load();

					Stage stage = new Stage();
					stage.initStyle(StageStyle.UNDECORATED);
					stage.setScene(new Scene(root));
					stage.show();
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
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	@FXML
	void initialize() {

		try {

			if (height == 0) {
				height = 194;
			}
			if (width == 0) {
				width = 600;
			}
			parent.setPrefSize(width, height);

			subtitle.setText("Modificar puesto");
			AdministratorPageConnection adminDB = new AdministratorPageConnection();
			getRole(adminDB);

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

	void getRole(AdministratorPageConnection adminDB) {
		Hashtable<String, String> role = adminDB.getRole(roleId);
		if (role.containsKey("name")) {
			String roleName = role.get("name");
			fieldName.setText(roleName);
		}
	}
}
