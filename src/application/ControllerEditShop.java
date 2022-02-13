package application;

import java.time.LocalTime;
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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
		
		try {
			
			if (checkAllFields()) {
				AdministratorPageConnection adminDB = new AdministratorPageConnection();
				boolean success = adminDB.updateShop(shopId, fieldName.getText(), fieldDirection.getText());

				if (success) {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
					ControllerAlertDialog control = new ControllerAlertDialog(120, 210, "Guardado correcto",
							"Los datos de la tienda se han guardado correctamente");
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
		
		try {
			Hashtable<String, String> shop = adminDB.getShop(shopId);
			String shopName = shop.get("shopName");
			String shopDirection = shop.get("shopDirection");
			fieldName.setText(shopName);
			fieldDirection.setText(shopDirection);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
