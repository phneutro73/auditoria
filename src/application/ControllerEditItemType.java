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

public class ControllerEditItemType {

	double height;
	double width;
	int itemTypeId;

	public ControllerEditItemType(double height, double width, int itemTypeId) {
		super();
		this.height = height;
		this.width = width;
		this.itemTypeId = itemTypeId;
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

		try {

			if (checkAllFields()) {
				AdministratorPageConnection adminDB = new AdministratorPageConnection();
				boolean success = adminDB.updateItemType(itemTypeId, fieldName.getText());

				if (success) {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
					ControllerAlertDialog control = new ControllerAlertDialog(120, 210, "Guardado correcto",
							"Los datos del tipo de artículo se han guardado correctamente");
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
			e.printStackTrace();
		}

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
		Hashtable<String, String> itemType = adminDB.getItemType(itemTypeId);
		if (itemType.containsKey("itemTypeName")) {
			String itemTypeName = itemType.get("itemTypeName");
			fieldName.setText(itemTypeName);
		}
	}
}
